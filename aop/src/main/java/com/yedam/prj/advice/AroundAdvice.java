package com.yedam.prj.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
@Service
@Aspect
public class AroundAdvice {

	@Around("LogAdvice.allpointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		// �����Ͻ������� ó������
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		// �����Ͻ� �޼��� ȣ��
		Object obj = pjp.proceed();
		// �����Ͻ������� ó������
		stopwatch.stop();
		System.out.println("����ð�:" + stopwatch.getTotalTimeMillis());
		return obj;
	}
}
