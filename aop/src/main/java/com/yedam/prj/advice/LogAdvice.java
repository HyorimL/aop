package com.yedam.prj.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
@Service //�����̳ʿ� ����
@Aspect  //advice + aspect ����
public class LogAdvice {
	
	@Pointcut("execution(* com.yedam..*Impl.*(..))")
	public void allpointcut() {}
	
	@Before("allpointcut()")
	public void printLog(JoinPoint jp) {
		//�޼��� ��
		String mehtodName = jp.getSignature().getName();
					
		//�μ�(argument)
		Object[] args = jp.getArgs();
		Object arg1 = (args != null ? args[0] : "");
		System.out.println("[����ó��] beforeLog" + mehtodName + "\n arg:" + arg1);
		
	}
}
