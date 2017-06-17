package com.zs.struts.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//模拟数据库封装Dao 方法
public class Dao {
	private static Map<Integer, Employee> emps = new HashMap<>();
	
	static{
		emps.put(1001, new Employee(1001, "james", "lebron", "zs511129@163.com"));
		emps.put(1002, new Employee(1002, "lio", "messi", "62819992@163.com"));
		emps.put(1003, new Employee(1003, "kobe", "brant", "hello@qq.com"));
	}
	
	//返回所有Employee对象
	public List<Employee> getEmployees(){
		return new ArrayList<>(emps.values());
	}
	
	//删除一个对象
	public void delete(Integer empId){
		emps.remove(empId);
	}
	
	//根据Id返回一个对象
	public Employee get(Integer empId){
		return emps.get(empId);
	}
	
	//保存一个新建对象
	public void save(Employee employee){
		long time = System.currentTimeMillis();
		employee.setEmployeeId(Math.abs((int)time));
		emps.put(employee.getEmployeeId(), employee);
	}
	
	//修改一个对象
	public void update(Employee employee){
		emps.put(employee.getEmployeeId(), employee);
	}
}
