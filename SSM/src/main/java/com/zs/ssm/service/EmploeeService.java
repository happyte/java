package com.zs.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.ssm.bean.Employee;
import com.zs.ssm.dao.EmployeeMapper;

@Service
public class EmploeeService {
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	//查询出所有的员工
	public List<Employee> getAllEmployees(){
		return employeeMapper.selectByExampleWithDept(null);
	}
}
