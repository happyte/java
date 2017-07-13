package com.zs.springmvc.crud.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zs.springmvc.crud.entities.Department;
import com.zs.springmvc.crud.entities.Employee;

//对应的持久化层
@Repository
public class EmployeeDao {
	private static Map<Integer, Employee> map = new HashMap<>();
	
	@Autowired
	private DepartmentDao departmentDao;
	
	static{
		map.put(1001, new Employee(1001, "AA", "AA@163.com", 1, new Department(101, "Human Affair")));
		map.put(1002, new Employee(1002, "BB", "BB@163.com", 0, new Department(102, "IT Affair")));
		map.put(1003, new Employee(1003, "CC", "CC@163.com", 1, new Department(103, "Science Affair") ));
		map.put(1004, new Employee(1004, "DD", "DD@163.com", 0, new Department(102, "IT Affair")));
		map.put(1005, new Employee(1005, "EE", "EE@163.com", 1, new Department(103, "Science Affair")));
	}
	
	private static Integer initId = 1006;
	
	public void save(Employee employee){
		if(employee.getId() == null){
			employee.setId(initId++);
		}
		//添加后的Employee如下
		//[id=null, lastName=happyte, email=123@126.com, gender=1, department=Department [id=101, departmentName=null]]
		employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
		map.put(employee.getId(), employee);
	}
	
	public Collection<Employee> getAll(){
		return map.values();
	}
	
	public Employee get(Integer id){
		return map.get(id);
	}
	
	public void delete(Integer id){
		map.remove(id);
	}
}
