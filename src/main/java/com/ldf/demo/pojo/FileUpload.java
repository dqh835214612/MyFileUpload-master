package com.ldf.demo.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: 清峰
 * @date: 2020/11/2 20:04
 * @code: 愿世间永无Bug!
 * @description:
 */
@Component
public class FileUpload {

    private Integer id;
    private String oldFileName;
    private String newFileName;
    private String ext; //后缀
    private String path;
    private Long size;
    private String type;
    private String desc;
    private Date uploadTime;
    private Integer userId;
    private String globalPath;

    @Override
    public String toString() {
        return "FileUpload{" +
                "id=" + id +
                ", oldFileName='" + oldFileName + '\'' +
                ", newFileName='" + newFileName + '\'' +
                ", ext='" + ext + '\'' +
                ", path='" + path + '\'' +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", describe='" + desc + '\'' +
                ", uploadTime=" + uploadTime +
                ", userId=" + userId +
                ", globalPath='" + globalPath + '\'' +
                '}';
    }

    public String getGlobalPath() {
        return globalPath;
    }

    public void setGlobalPath(String globalPath) {
        this.globalPath = globalPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldFileName() {
        return oldFileName;
    }

    public void setOldFileName(String oldFileName) {
        this.oldFileName = oldFileName;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getPath() {
        return path;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
