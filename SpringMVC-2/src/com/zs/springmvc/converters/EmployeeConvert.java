package com.zs.springmvc.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.zs.springmvc.crud.entities.Department;
import com.zs.springmvc.crud.entities.Employee;

//自定义类型转化器，把字符串转换成Employee对象 
@Component
public class EmployeeConvert implements Converter<String, Employee> {

	@Override
	public Employee convert(String source) {
		String[] vals = source.split("-");
		if(source != null && vals.length==4){
			String lastName = vals[0];
			String email = vals[1];
			Integer gender = Integer.parseInt(vals[2]);
			Integer departmentId = Integer.parseInt(vals[3]);
			Department department = new Department();
			department.setId(departmentId);
			Employee employee = new Employee(null, lastName, email, gender, department);
			System.out.println(source+"---convert---"+employee);
			return employee;
		}
		return null;
	}

}
