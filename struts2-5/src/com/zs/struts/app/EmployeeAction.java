package com.zs.struts.app;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ModelDriven;

public class EmployeeAction implements RequestAware, ModelDriven<Employee> {
	
	private Integer employeeId;
	private Employee employee;
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
	
	public String delete(){
		dao.delete(employeeId);
		return "success";
	}
	
	public String save(){
		dao.save(employee);
		return "success";
	}
	@Override
	public Employee getModel() {
		employee = new Employee();
		return employee;
	}
}
