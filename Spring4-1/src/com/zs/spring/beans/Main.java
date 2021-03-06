package com.zs.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		//1.创建Spring的IOC容器对象
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2.从IOC容器中获取Bean实例
		//HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");
		HelloWorld helloWorld = ctx.getBean(HelloWorld.class);
		//3.调用方法
		helloWorld.hello();
		
		Car car = (Car) ctx.getBean("car");
		System.out.println(car);
		car = (Car) ctx.getBean("car2");
		System.out.println(car);
		
		Person person = ctx.getBean(Person.class);
		System.out.println(person);
		
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource);
	}
}
