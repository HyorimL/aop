package com.yedam.prj.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
@Service
@Aspect
public class AfterAdvice {

	@AfterReturning(pointcut = "LogAdvice.allpointcut()", returning = "returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {
		String method = jp.getSignature().getName();
		String result = returnObj != null ? returnObj.toString() : "";
			
		System.out.println("[사후처리] 로직 수행 후 동작 " + method + " : " + result);
	}
}
