package com.zs.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.ssm.bean.Employee;
import com.zs.ssm.bean.EmployeeExample;
import com.zs.ssm.bean.EmployeeExample.Criteria;
import com.zs.ssm.dao.EmployeeMapper;

@Service
public class EmploeeService {
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	//查询出所有的员工
	public List<Employee> getAllEmployees(){
		return employeeMapper.selectByExampleWithDept(null);
	}

	public void save(Employee employee) {
		employeeMapper.insertSelective(employee);
	}

	//查询用户名是否存在
	public boolean check(String empName) {
		EmployeeExample employeeExample = new EmployeeExample();
		Criteria criteria = employeeExample.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(employeeExample);
		return count == 0;
	}

	public Employee getEmp(Integer id) {
		return employeeMapper.selectByPrimaryKey(id);
	}
}
