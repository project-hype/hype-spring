package com.devjeans.hype.util;

import lombok.Data;

@Data
public class UploadFile {

    private String uploadFileName;
    private String storedFilePath;

    public UploadFile(String uploadFileName, String storedFilePath) {
        this.uploadFileName = uploadFileName;
        this.storedFilePath = storedFilePath;
    }
}