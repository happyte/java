package com.zs.spring.annotaion;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zs.spring.annotaion.controller.UserController;
import com.zs.spring.annotaion.repository.UserRepository;
import com.zs.spring.annotaion.service.UserService;

public class Main {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-annotaion.xml");
//		TestObject testObject = (TestObject) ctx.getBean("testObject");
//		System.out.println(testObject);
		
//		UserRepository userRepository = (UserRepository) ctx.getBean("userRepository");
//		System.out.println(userRepository);
		
		UserController userController = (UserController) ctx.getBean("userController");
		userController.execute();
		
//		UserService userService = (UserService) ctx.getBean("userService");
//		System.out.println(userService);
	}
}
