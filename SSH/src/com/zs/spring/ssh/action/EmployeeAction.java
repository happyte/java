package com.zs.spring.ssh.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zs.spring.ssh.service.EmployeeService;

public class EmployeeAction extends ActionSupport implements RequestAware{

	private static final long serialVersionUID = 1L;
	
	private EmployeeService employeeService;
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public String list(){
		map.put("employees", employeeService.getAll());
		return "list";
	}
	
	private Map<String, Object> map;

	@Override
	public void setRequest(Map<String, Object> arg0) {
		map = arg0;
	}

}
