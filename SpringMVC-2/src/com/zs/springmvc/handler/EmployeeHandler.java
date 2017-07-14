package com.zs.springmvc.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zs.springmvc.crud.dao.DepartmentDao;
import com.zs.springmvc.crud.dao.EmployeeDao;
import com.zs.springmvc.crud.entities.Employee;

@Controller
public class EmployeeHandler {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@RequestMapping("/emps")
	public String list(Map<String,Object> map){
		map.put("employees", employeeDao.getAll());
		return "list";
	}
	
	//跳转到添加页面
	@RequestMapping(value="/emp",method=RequestMethod.GET)
	public String input(Map<String,Object> map){
		map.put("departments", departmentDao.getValues());
		map.put("employee", new Employee());
		return "input";
	}
	
	//提交新添加用户，重定向到list页面
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public String save(Employee employee){
		System.out.println("employee:"+employee);
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	//这个拦截器并不会得出url中的id
	@ModelAttribute
	public void getEmployee(@RequestParam(value="id", required=false) Integer id,Map<String, Object> map){
			//只有表单回显修改后提交接受的参数id才是有值的
			System.out.println("modelAttribute id:"+id);
			if(id != null){
				Employee employee = employeeDao.get(id);
				map.put("employee", employee);
			}
	}
	
	//删除用户
	@RequestMapping(value="emp/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id){
		System.out.println("delete id:"+id);
		employeeDao.delete(id);
		return "redirect:/emps";
	}
	
	//表单回显
	@RequestMapping(value="emp/{id}",method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id,Map<String,Object> map){
		Employee employee = employeeDao.get(id);
		map.put("employee", employee);
		map.put("departments", departmentDao.getValues());
		return "input";
	}
	
	//修改用户信息
	@RequestMapping(value="emp",method=RequestMethod.PUT)
	public String update(Employee employee){
		employeeDao.save(employee);
		return "redirect:/emps";
	}
}
