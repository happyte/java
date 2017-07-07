package com.zs.spring.ssh.service;

import java.util.List;

import com.zs.spring.ssh.dao.EmployeeDao;
import com.zs.spring.ssh.entities.Employee;

public class EmployeeService {
	private EmployeeDao employeeDao;
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	public List<Employee> getAll(){
		return employeeDao.getAll();
	}
	
	public void delete(Integer id){
		employeeDao.delete(id);
	}
	
	public void saveOrUpdate(Employee employee){
		employeeDao.saveOrUpdate(employee);
	}
	
	public boolean validateByLastName(String lastName){
		return employeeDao.getEmployeeByLastName(lastName) == null;
	}
}
