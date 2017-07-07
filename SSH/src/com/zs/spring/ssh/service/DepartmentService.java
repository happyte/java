package com.zs.spring.ssh.service;

import java.util.List;

import com.zs.spring.ssh.dao.DepartmentDao;
import com.zs.spring.ssh.entities.Department;
import com.zs.spring.ssh.entities.Employee;

public class DepartmentService {
	private DepartmentDao departmentDao;
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	public List<Department> getAll(){
		return departmentDao.getAll();
	}
	
}
