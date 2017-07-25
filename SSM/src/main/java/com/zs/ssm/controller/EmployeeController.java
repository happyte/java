package com.zs.ssm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Message getEmp(@PathVariable("id") Integer id){
		Employee employee = emplyoeeService.getEmp(id);
		return Message.success().add("emp", employee);
	}
	
	//处理ajax请求用户名是否重复
	@ResponseBody
	@RequestMapping(value="/checkuser")
	public Message checkuser(@RequestParam(value="empName") String empName){
		String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!empName.matches(regex)){
			return Message.fail().add("rex_va", "用户名为2-5位中文或者6-16位英文和数字的组合");
		}
		boolean flag = emplyoeeService.check(empName);
		if(flag){
			return Message.success();
		}
		else{
			return Message.fail().add("rex_va", "用户名不可用");
		}
	}
	
	//只处理REST的POST请求
	@ResponseBody
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public Message emp_save(@Valid Employee employee,BindingResult result){
		Map<String, Object> map = new HashMap<>();
		if(result.hasErrors()){
			List<FieldError> errors = result.getFieldErrors();
			for(FieldError error:errors){
				System.out.println("错误的字段:"+error.getField());
				System.out.println("错误的信息:"+error.getDefaultMessage());
				map.put(error.getField(), error.getDefaultMessage());
			}
			return Message.fail().add("errorMap", map);
		}
		else{
			emplyoeeService.save(employee);
			return Message.success();
		}
	}
	
	
	//返回Json数据，前端使用Ajax发送get请求并处理json数据
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
