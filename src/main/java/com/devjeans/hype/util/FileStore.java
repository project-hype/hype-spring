package com.devjeans.hype.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 업로드 클래스
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

@Component
public class FileStore {

    private final String fileDir;
    private final String frontFileDir;
    
    public FileStore() throws Exception {
    	Properties properties = new Properties();
		properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
    	fileDir = properties.getProperty("file_directory");
    	frontFileDir = properties.getProperty("front_file_directory");
    }

		// MultipartFile을 받아서 파일을 저장한 다음에 UploadFile로 반환해줌
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException { // 한 개 업로드
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename(); // 사용자가 업로드한 파일이름
        String storedFilename = createStoreFileName(originalFilename); // 서버에 저장하는 파일명(uu아이디+.+확장자명)

        multipartFile.transferTo(new File(fileDir + storedFilename)); // 디렉토리에 파일이름이 합쳐진 것이 File로 만들어지고
        return new UploadFile(originalFilename, frontFileDir + storedFilename); // UploadFile 반환
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString(); // 서버에 저장하는 파일명(uuid)
        return uuid + "." + ext; // ext : 확장자명
    }

    private String extractExt(String originalFilename) { // 확장자명 꺼내기
        int pos = originalFilename.lastIndexOf("."); // 위치를 가져온다.
        return originalFilename.substring(pos + 1); // . 다음에 있는 확장자명 꺼냄
    }


}