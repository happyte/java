package com.zs.springmvc.crud.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zs.springmvc.crud.entities.Department;

@Repository
public class DepartmentDao {
	
	
	private static Map<Integer, Department> map = new HashMap<>();
	
	static{
		map.put(101, new Department(101, "Human Affair"));
		map.put(102, new Department(102, "IT Affair"));
		map.put(103, new Department(103, "Science Affair"));
	}
	
	
	//获取所有Department的集合
	public Collection<Department> getValues(){
		return map.values();
	}
	
	//根据Id获取指定的Department
	public Department getDepartment(Integer id){
		return map.get(id);
	}
}
