package com.zs.ssm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zs.ssm.bean.Employee;
import com.zs.ssm.bean.Message;
import com.zs.ssm.service.EmploeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmploeeService emplyoeeService;
	
	@ResponseBody
	@RequestMapping("/emps")
	public Message getEmpsWithJson(@RequestParam(value="pageNo",defaultValue="1") Integer pageNo){
		// 这不是一个分页查询；
		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
		PageHelper.startPage(pageNo, 5);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<Employee> emps = emplyoeeService.getAllEmployees();
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo<Employee> page = new PageInfo<Employee>(emps, 5);
		return Message.success().add("pageInfo", page);
	}
	
//	@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pageNo",defaultValue="1") Integer pageNo,
					Model model){
		// 这不是一个分页查询；
		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
		PageHelper.startPage(pageNo, 5);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<Employee> emps = emplyoeeService.getAllEmployees();
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo<Employee> page = new PageInfo<Employee>(emps, 5);
		model.addAttribute("pageInfo", page);
		List<Employee> list = page.getList();
		return "list";
	}
}
