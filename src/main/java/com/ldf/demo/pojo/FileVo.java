package com.ldf.demo.pojo;

public class FileVo {
    private String fileName;
    private String date;
    private int id;
    private String path;

    @Override
    public String toString() {
        return "FileVo{" +
                "fileName='" + fileName + '\'' +
                ", date='" + date + '\'' +
                ", id=" + id +
                ", path='" + path + '\'' +
                ", isFile="  +
                '}';
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
