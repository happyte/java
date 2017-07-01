package com.zs.spring.aop;

import com.zs.spring.aop.impl.ArithmeticCalculator;
import com.zs.spring.aop.impl.ArithmeticCalculatorImpl;

public class Main {
	
	public static void main(String[] args) {
		ArithmeticCalculator target = new ArithmeticCalculatorImpl();
		ArithmeticCalculator proxy = new ArithmeticCalculatorLoggingProxy(target).getLoggingProxy();
		
		int result = proxy.add(1, 2);
		System.out.println("result:"+result);
		
		result = proxy.sub(5, 1);
		System.out.println("result:"+result);
		
	}
}
