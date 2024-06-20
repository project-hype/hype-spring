package com.devjeans.hype.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {
	@Before("execution(* com.devjeans.hype.*.service..*.*(..))")
	public void logBefore() {
		log.info("*=====================================*");
	}

}