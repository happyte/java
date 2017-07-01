package com.zs.spring.aop.impl;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
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
	
	//在方法执行之后执行的代码，无论是否抛出异常，后置通知一定会被执行
	@After("execution(public int com.zs.spring.aop.impl.ArithmeticCalculatorImpl.*(int, int))")
	public void afterMethod(JoinPoint joinpoint){
		String methodName = joinpoint.getSignature().getName();
		System.out.println("[after] The method"+methodName+" ends");
	}
	
	//在方法正常结束后执行的代码,返回通知是可以访问到方法的返回值的
	@AfterReturning(value="execution(public int com.zs.spring.aop.impl.ArithmeticCalculatorImpl.*(int, int))" ,
			returning="result")
	public void afterReturningMethod(JoinPoint joinpoint,Object result){
		String methodName = joinpoint.getSignature().getName();
		System.out.println("[after] The method"+methodName+" ends with result:"+result);
	}
	
	//异常通知
	@AfterThrowing(value="execution(public int com.zs.spring.aop.impl.ArithmeticCalculatorImpl.*(int, int))" ,
			throwing="ex")
	public void afterThrowing(JoinPoint joinpoint, Exception ex){
		String methodName = joinpoint.getSignature().getName();
		System.out.println("[after] The method"+methodName+" occurs with exception:"+ex);
	}
}
