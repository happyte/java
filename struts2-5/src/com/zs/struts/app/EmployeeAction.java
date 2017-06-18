package com.zs.struts.app;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ModelDriven;


public class EmployeeAction implements RequestAware, ModelDriven<Employee> {
	
	private Employee employee;
	private Integer employeeId;
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
	
	public String delete(){
		dao.delete(employee.getEmployeeId());
		return "success";
	}
	
	public String save(){
		dao.save(employee);
		return "success";
	}
	
	public String edit(){
		//1.获取传入employeeId:empolyee.getEmpolyeeId()
		//2.根据employeeId获取employee对象
		//3.把栈顶对象的属性装配好:此时栈顶对象为employee,只有employeeId属性传进来
		/**
		 * struts2表单回显：从值栈栈顶开始查找匹配的属性，若找到，则添加到value属性中
		 */
		//不能直接用employee = dao.get(employee.getEmployeeId()),这样的话employee和值栈栈顶元素不是同一个了
		return "edit";
	}
	
	public String update(){
		return "success";
	}
	
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	
	@Override
	public Employee getModel() {
		//若判定标准为employeeId这个请求参数，若有该参数，则视为Edit,没有视为Create
		// 如果想通过employeeId来判断，需要在ModelDriven拦截器前先执行一个params拦截器
		if(employeeId == null)
			employee = new Employee();
		else
			employee = dao.get(employeeId);
		return employee;
	}
}
