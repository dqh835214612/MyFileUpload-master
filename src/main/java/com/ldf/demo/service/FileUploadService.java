package com.ldf.demo.service;

import com.ldf.demo.pojo.FileUpload;

import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/11/2 20:07
 * @code: 愿世间永无Bug!
 * @description:
 */
public interface FileUploadService {
    List<FileUpload> findAll(int id);

    int saveFile(FileUpload fileUpload);

    int deleteFileById(Integer id);

    FileUpload findFileById(Integer id);


    void updateFileDownCounts(Integer id,int downCounts);
}
