package com.zoe.phip.web.model;

/**
 * Created by chenzhisen on 2016/5/13.
 */
public class UploadRes implements java.io.Serializable{


    private String message;
    private String fileContent;

    public String getMessage() {
        return message;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }
}