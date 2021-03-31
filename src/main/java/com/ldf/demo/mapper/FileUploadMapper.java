package com.ldf.demo.mapper;

import com.ldf.demo.pojo.FileUpload;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/11/2 20:07
 * @code: 愿世间永无Bug!
 * @description:
 */
@Repository
public interface FileUploadMapper {
    List<FileUpload> findAll(int id);

    int saveFile(FileUpload fileUpload);

    int deleteFileById(Integer id);

    FileUpload findFileById(Integer id);

    void updateFileDownCounts(@Param("id") Integer id,@Param("downCounts") int downCounts);

}
