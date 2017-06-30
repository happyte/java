package com.zs.spring.relation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zs.spring.autowire.Address;

public class Main {
	public static void main(String[] args) {
		//1.创建Spring的IOC容器对象
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-relation.xml");
		Address address1 = (Address) ctx.getBean("address");
		System.out.println(address1);
		
		Address address2 = (Address) ctx.getBean("address2");
		System.out.println(address2);
	}
}
