package com.zs.springmvc.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zs.springmvc.crud.dao.EmployeeDao;
import com.zs.springmvc.crud.entities.Employee;

@Controller
public class SpringMVCTest {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@RequestMapping("/testConvert")
	public String testConvert(@RequestParam("employee") Employee employee){
		System.out.println("save employee:"+employee);
		employeeDao.save(employee);
		return "redirect:/emps";
	}
}
