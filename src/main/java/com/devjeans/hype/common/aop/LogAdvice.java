package com.devjeans.hype.common.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

/**
 * AOP: 전역 Service Log Advice
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
@Component
public class LogAdvice {
	/**
	 * 모든 Service의 메소드 호출 시 로그 출력
	 * 호출된 메소드, 파라미터, 실행시간 출력
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.devjeans.hype.*.service..*.*(..))")
	public Object logPrint(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		long start = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();

        long end = System.currentTimeMillis();

        log.info("----------------------------------------------------------------");
        log.info(type + "."+proceedingJoinPoint.getSignature().getName() + "()");
        log.info("Argument/Parameter : " + Arrays.toString(proceedingJoinPoint.getArgs()));
        log.info("Running Time : " + (end - start) + "ms");
        log.info("----------------------------------------------------------------");
        
        return result;
	}

}