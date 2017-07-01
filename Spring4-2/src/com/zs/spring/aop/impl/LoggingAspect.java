package com.zs.spring.aop.impl;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//放到IOC容器中，再声明为一个切面
@Aspect
@Component
public class LoggingAspect {
	//该方法是个前置通知，在目标方法前执行,*匹配所有该参数的方法
	@Before("execution(public int com.zs.spring.aop.impl.ArithmeticCalculatorImpl.*(int, int))")
	public void breforeMethod(JoinPoint joinpoint){
		String methodName = joinpoint.getSignature().getName();
		Object[] args = joinpoint.getArgs();
		System.out.println("[before] The method"+methodName+"begins with:"+Arrays.asList(args));
	}
}
