package com.zs.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {
	
	/**
	 * 使用RequestMapping来映射对应的url请求
	 */
	@RequestMapping("/hello")
	public String hello(){
		System.out.println("success");
		return "success";
	}
}
