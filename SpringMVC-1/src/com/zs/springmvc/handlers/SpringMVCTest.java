package com.zs.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
