package com.zs.spring.aop.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ArithmeticCalculator arithmeticCalculator = ctx.getBean(ArithmeticCalculator.class);
		
		int result = arithmeticCalculator.add(3, 6);
		System.out.println(result);
		
		result = arithmeticCalculator.sub(6, 1);
		System.out.println(result);
	}
}
