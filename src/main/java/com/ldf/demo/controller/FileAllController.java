package com.ldf.demo.controller;

import com.ldf.demo.pojo.FileUpload;
import com.ldf.demo.pojo.User;
import com.ldf.demo.service.FileUploadService;
import com.sun.deploy.net.URLEncoder;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: 清峰
 * @date: 2020/11/2 19:45
 * @code: 愿世间永无Bug!
 * @description:
 */
@Controller
@RequestMapping("/files")
public class FileAllController {

    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private FileUpload fileUpload;

    @GetMapping("/fileAll")
    public String fileAll(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<FileUpload> fileUploadsList = fileUploadService.findAll();
        model.addAttribute("fileUploadsList", fileUploadsList);
        return "/fileAll";
    }

    @PostMapping("/upload")
    public String upload(HttpSession session, @RequestParam("file") MultipartFile file, RedirectAttributes attributes) throws IOException {
        System.out.println("准备上传");
        if (file.isEmpty()) {
            attributes.addFlashAttribute("msg", "上传的文件不能为空");
            return "redirect:/files/fileAll";
        }

        //获取原始文件名称
        String originalFilename = file.getOriginalFilename();

        //获取文件后缀名
        String extension = "." + FilenameUtils.getExtension(originalFilename);
        //获取新文件名称 命名：时间戳+UUID+后缀
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
                + UUID.randomUUID().toString().substring(0, 4)
                + extension;

        //获取资源路径 classpath的项目路径+/static/files  classpath就是resources的资源路径
        String path = ResourceUtils.getURL("classpath:").getPath() + "static/files/";
        //新建一个时间文件夹标识，用来分类
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //全路径(存放资源的路径) 资源路径+时间文件夹标识
        String dataDir = path + format;
        System.out.println(dataDir);

        //全路径存放在文件类中，判断文件夹是否存在不存在就创建
        File dataFile = new File(dataDir);  //也可以直接放进去进行拼接 File dataFile = new File(path,format);
        if (!dataFile.exists()) {
            dataFile.mkdirs();
        }

        //文件上传至指定路径
        file.transferTo(new File(dataFile, newFileName));

        //文件信息保存到数据库
        //获取文件格式
        String type = file.getContentType();
        //获取文件大小
        long size = file.getSize();

        fileUpload.setOldFileName(originalFilename);
        fileUpload.setNewFileName(newFileName);
        fileUpload.setExt(extension);
        fileUpload.setPath("/files/" + format);
        fileUpload.setGlobalPath(dataDir);
        fileUpload.setDownCounts(0);
        fileUpload.setType(type);
        fileUpload.setSize(size);
        fileUpload.setUploadTime(new Date());
        User user = (User) session.getAttribute("user");
        fileUpload.setUserId(user.getId());

//        boolean img = type.startsWith("image");//检测字符串是否以指定的前缀开始
//        if (img) {
//            fileUpload.setIsImg("是");
//        } else {
//            fileUpload.setIsImg("否");
//        }
        System.out.println(fileUpload);
        int b = fileUploadService.saveFile(fileUpload);
        System.out.println(b);
        if (b == 1) {
            attributes.addFlashAttribute("msg", "保存成功！");
        } else {
            attributes.addFlashAttribute("msg", "保存失败！");
        }
        System.out.println("上传结束");
        return "redirect:/files/fileAll";
    }

    @GetMapping("/download")
    public void download(Integer id, String openStyle, HttpServletResponse response) throws IOException {

        FileUpload fileUpload = fileUploadService.findFileById(id);
        //获取全路径
//        String globalPath = ResourceUtils.getURL("classpath:").getPath() + "static" + fileUpload.getPath() + "/";
        if (fileUpload != null) {
            String globalPath = fileUpload.getGlobalPath();
            System.out.println(globalPath);
            try{
                FileInputStream fis = new FileInputStream(new File(globalPath, fileUpload.getNewFileName()));
                //根据传过来的参数判断是下载，还是在线打开
                if ("attachment".equals(openStyle)) {
                    //并更新下载次数
                    fileUpload.setDownCounts(fileUpload.getDownCounts() + 1);
                    fileUploadService.updateFileDownCounts(id, fileUpload.getDownCounts());
                    //以附件形式下载  点击会提供对话框选择另存为：
                    response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileUpload.getOldFileName(), "utf-8"));

                }
                //获取输出流
                ServletOutputStream os = response.getOutputStream();
                //利用IO流工具类实现流文件的拷贝，（输出显示在浏览器上在线打开方式）
                IOUtils.copy(fis, os);
                IOUtils.closeQuietly(fis);
                IOUtils.closeQuietly(os);
                fis.close();
                os.close();
            }catch (Exception e){

            }
        }
    }

    @GetMapping("/delete")
    public String delete(HttpSession session,Integer id, RedirectAttributes attributes) throws FileNotFoundException {

        User user = (User) session.getAttribute("user");
        //先删除文件在删数据库中的信息
        FileUpload fileUpload = fileUploadService.findFileById(id);
        if (fileUpload.getUserId() != user.getId()) {
            attributes.addFlashAttribute("msg", "无法删除其他用户上传的信息！");
            return "redirect:/files/fileAll";
        }
        System.out.println("根据id查询到：" + fileUpload);
        //根据数据库的信息拼接文件的全路径  资源路径+static+数据库已存入的文件路径+"/"+新文件名
        // 如：D:/IDEAworkspace/3SpringBoot_Workspace/functionDemo/FileUploadDemo01/target/classes/static/files/2020-11-03/
//        String globalPath = ResourceUtils.getURL("classpath:").getPath() + "static" + fileUpload.getPath() + "/";
        String globalPath = fileUpload.getGlobalPath();
        System.out.println(globalPath);
        File file = new File(globalPath, fileUpload.getNewFileName());
        String[] list = file.list();
        System.out.println(list);
        if (file.exists() && file.isFile()) {
            file.delete();
        }

        int b = fileUploadService.deleteFileById(id);
        if (b == 1) {
            attributes.addFlashAttribute("msg", "删除成功！");
        } else {
            attributes.addFlashAttribute("msg", "删除失败！");
        }
        return "redirect:/files/fileAll";
    }

}
