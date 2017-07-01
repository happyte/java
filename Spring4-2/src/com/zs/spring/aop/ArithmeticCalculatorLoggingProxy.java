package com.zs.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import com.zs.spring.aop.impl.ArithmeticCalculator;

public class ArithmeticCalculatorLoggingProxy {
	private ArithmeticCalculator target;

	public ArithmeticCalculatorLoggingProxy(ArithmeticCalculator target) {
		super();
		this.target = target;
	}
	
	public ArithmeticCalculator getLoggingProxy(){
		ArithmeticCalculator proxy = null;
		//采用动态代理的方法
		//代理对象由哪个类加载器负责加载
		ClassLoader loader = target.getClass().getClassLoader();
		//代理对象的类型
		Class[] interfaces = new Class[]{ArithmeticCalculator.class};
		//当调用代理对象其中的方法时，执行的代码
		InvocationHandler h = new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				String methodName = method.getName();
				System.out.println("[before] The method"+methodName+"begins with:"+Arrays.asList(args));
				Object result = null;
				try {
					//调用target对象对应的方法
					result = method.invoke(target, args);
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
				System.out.println("[after] The method ends with:"+result);
				return result;
			}
		};
		proxy = (ArithmeticCalculator) Proxy.newProxyInstance(loader, interfaces, h);
		return proxy;
	}
}
