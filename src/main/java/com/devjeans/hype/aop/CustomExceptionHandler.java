package com.devjeans.hype.aop;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.log4j.Log4j;

/**
 * AOP: 전역 Controller Exception Handler
 * @author 조영욱
 * @since 2024.06.21
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.20   조영욱       최초 생성
 * </pre>
 */

@Aspect
@Log4j
@RestControllerAdvice
public class CustomExceptionHandler {

	/**
	 * 전역 컨트롤러 예외처리 메소드
	 * 클라이언트에게 예외/에러의 상세 내역을 출력하지 않게 한다.
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<String> handleCustomException(Exception e) {
		log.warn("===========================");
		log.warn("========== 예외필터 =========");
		log.warn(e);
		StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        log.warn(sw.toString());
		log.warn("===========================");
		
		// 클라이언트에게 메시지 반환
        return new ResponseEntity<String>("Invalied access. Please try again", HttpStatus.BAD_REQUEST);
    }
}
