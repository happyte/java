package com.zs.spring.ssh.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.zs.spring.ssh.entities.Employee;
import com.zs.spring.ssh.service.DepartmentService;
import com.zs.spring.ssh.service.EmployeeService;

public class EmployeeAction extends ActionSupport implements RequestAware,ModelDriven<Employee>,Preparable{

	private static final long serialVersionUID = 1L;
	
	private EmployeeService employeeService;
	
	private DepartmentService departmentService;
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	public String list(){
		map.put("employees", employeeService.getAll());
		return "list";
	}
	
	private Integer id;
	public void setId(Integer id) {
		this.id = id;
	}
	
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}
	
	public String delete(){
		try {
			employeeService.delete(id);
			//1就是response返回的响应 
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e) {
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "delete";
	}
	
	public String input(){
		map.put("departments", departmentService.getAll());
		return INPUT;
	}
	
	private Map<String, Object> map;

	@Override
	public void setRequest(Map<String, Object> arg0) {
		map = arg0;
	}
	
	public String save(){
		model.setCreateTime(new Date());
		employeeService.saveOrUpdate(model);
		return SUCCESS;
	}
	
	public void prepareSave(){
		model = new Employee();
	}

	@Override
	public void prepare() throws Exception {
	}
	
	private Employee model;

	@Override
	public Employee getModel() {
		System.out.println(model);
		return model;
	}

}
