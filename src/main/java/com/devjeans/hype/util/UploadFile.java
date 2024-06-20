package com.devjeans.hype.util;

import lombok.Data;

/**
 * 파일 업로드 VO
 * @author 조영욱
 * @since 2024.06.19
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.19  	조영욱        최초 생성
 * </pre>
 */

@Data
public class UploadFile {

    private String uploadFileName;
    private String storedFilePath;

    public UploadFile(String uploadFileName, String storedFilePath) {
        this.uploadFileName = uploadFileName;
        this.storedFilePath = storedFilePath;
    }
}