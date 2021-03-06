package com.yedam.prj.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
@Service //컨테이너에 빈등록
@Aspect  //advice + aspect 설정
public class LogAdvice {
	
	@Pointcut("execution(* com.yedam..*Impl.*(..))")
	public void allpointcut() {}
	
	@Before("allpointcut()")
	public void printLog(JoinPoint jp) {
		//메서드 명
		String mehtodName = jp.getSignature().getName();
					
		//인수(argument)
		Object[] args = jp.getArgs();
		Object arg1 = (args != null ? args[0] : "");
		System.out.println("[사전처리] beforeLog" + mehtodName + "\n arg:" + arg1);
		
	}
}
