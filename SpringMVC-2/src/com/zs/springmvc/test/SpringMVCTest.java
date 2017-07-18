package com.zs.springmvc.test;

import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@ResponseBody
	@RequestMapping("/testJson")
	public Collection<Employee> testJson(){
		return employeeDao.getAll();
	}
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@RequestMapping("/i18n")
	public String i18n(Locale locale){
		String val = messageSource.getMessage("i18n.user", null, locale);
		System.out.println(val); 
		return "i18n";
	}
}
