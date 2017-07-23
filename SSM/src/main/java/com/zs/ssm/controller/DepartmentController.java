package com.zs.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.ssm.bean.Department;
import com.zs.ssm.bean.Message;
import com.zs.ssm.service.DepartmentService;

@Controller
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	@ResponseBody
	@RequestMapping("/getDepts")
	public Message getAllDepartment(){
		List<Department> depts = departmentService.getAllDepartment();
		return Message.success().add("depts", depts);
	}
}
