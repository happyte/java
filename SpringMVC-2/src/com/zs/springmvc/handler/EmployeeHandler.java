package com.zs.springmvc.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeHandler {
	
	@RequestMapping("/emps")
	public String list(){
		return "list";
	}
}
