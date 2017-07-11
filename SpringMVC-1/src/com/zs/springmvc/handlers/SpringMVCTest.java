package com.zs.springmvc.handlers;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.zs.springmvc.entities.User;

@SessionAttributes(value={"user"},types={String.class})
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {
	 
	private static final String SUCCESS = "success";
	
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping(){
		System.out.println("testRequestMapping");
		return SUCCESS;
	}
	/**
	 * 只允许post方法请求
	 */
	@RequestMapping(value = "/testMethod", method = RequestMethod.POST)
	public String testMethod() {
		System.out.println("testMethod");
		return SUCCESS;
	}
	
	/**
	 * 指定url?后必须带上参数username,如果参数age=10是不合法的
	 */
	@RequestMapping(value="/testHeadersAndParameters",params={"username","age!=10"})
	public String testHeadersAndParameters(){
		System.out.println("testHeadersAndParameters");
		return SUCCESS;
	}
	
	/**
	 *  *可以匹配任意字符
	 */
	@RequestMapping(value="/testAntPath/*/abc")
	public String testAntPath(){
		System.out.println("testAntPath");
		return SUCCESS;
	}
	
	/**
	 * 读出url中的请求参数
	 */
	@RequestMapping(value="/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id){
		System.out.println("testPathVariable  id:"+id);
		return SUCCESS;
	}
	
	/**
	 * restfule api get方法
	 */
	@RequestMapping(value="/testRestGet/{id}",method=RequestMethod.GET)
	public String testGet(@PathVariable("id") Integer id){
		System.out.println("testGet  id:"+id);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRestPost",method=RequestMethod.POST)
	public String testPost(){
		System.out.println("testRestPost");
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testRestPut/{id}", method = RequestMethod.PUT)
	public String testRestPut(@PathVariable Integer id) {
		System.out.println("testRest Put: " + id);
		return SUCCESS;
	}

	@RequestMapping(value = "/testRestDelete/{id}", method = RequestMethod.DELETE)
	public String testRestDelete(@PathVariable Integer id) {
		System.out.println("testRest Delete: " + id);
		return SUCCESS;
	}
	
	/**
	 * 最重要的一个注解，获取request的请求参数，与之前的@PathVariable不同，前面是写到url中的
	 */
	@RequestMapping(value="/testRequestParam")
	public String testRequestParam(@RequestParam(value="username") String username,
								   @RequestParam(value="age",required=false) Integer age){
		System.out.println("username:"+username+" "+"age:"+age);
		return SUCCESS;
	}
	
	@RequestMapping("/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value="Accept-Language") String al) {
		System.out.println("testRequestHeader, Accept-Language: " + al);
		return SUCCESS;
	}
	
	/**
	 * 类似于struts2的值栈特点，把表单的值赋给栈顶对象
	 */
	@RequestMapping("/testPojo")
	public String testPojo(User user){
		System.out.println("user:"+user);
		return SUCCESS;
	}
	
	/**
	 * viewName 代表了要去jsp页面
	 * 目标方法的返回值可以是 ModelAndView 类型
	 * 其中可以包含视图和模型信息
	 * SpringMVC 会把 ModelAndView 的 model 中数据放入到 request 域对象中. 
	 */
	@RequestMapping("/testModelANDView")
	public ModelAndView testModelANDView(){
		String viewName = SUCCESS;
		ModelAndView modelAndView = new ModelAndView(viewName);
		modelAndView.addObject("time",new Date());
		return modelAndView;
	}
	
	/**
	 * 目标方法可以添加 Map 类型(实际上也可以是 Model 类型或 ModelMap 类型)的参数.
	 */
	@RequestMapping("/testMap")
	public String testMap(Map<String, Object> map){
		map.put("names", Arrays.asList("Tom", "Jerry", "Mike"));
		return SUCCESS;
	}
	
	/**
	 * 除了可以通过属性名指定需要放到会话中的属性外(实际上使用的是 value 属性值)
	 * 还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中(实际上使用的是 types 属性值)
	 * 注意: 该注解只能放在类的上面. 而不能修饰放方法. 
	 */
	@RequestMapping("/testSessionAttribute")
	public String testSessionAttribute(Map<String, Object> map){
		map.put("user",new User("happyte", "123", "zs511129@163.com", 20));
		map.put("school", "xiangzhong");
		return SUCCESS;
	}
}
