package com.zs.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.ssm.bean.Department;
import com.zs.ssm.dao.DepartmentMapper;


@Service
public class DepartmentService {
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	//查询出所有的部门
	public List<Department> getAllDepartment(){
		return departmentMapper.selectByExample(null);
	}
}
