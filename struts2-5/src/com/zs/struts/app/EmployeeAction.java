package com.zs.struts.app;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

public class EmployeeAction implements RequestAware {
	
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private Dao dao = new Dao();
	private Map<String, Object> requestMap = new HashMap<>();
	
	public String list(){
		requestMap.put("emps", dao.getEmployees());
		return "list";
	}
	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.requestMap = arg0;
	}
	//Parameters参数拦截栈会把参数从值栈的栈顶开始找
	public void setEmployeeId(Integer employeeId) {
		System.out.println("employeeId:"+employeeId);
		this.employeeId = employeeId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String delete(){
		dao.delete(employeeId);
		return "success";
	}
	
	public String save(){
		Employee employee = new Employee(null, firstName, lastName, email);
		dao.save(employee);
		return "success";
	}
}
