package com.zs.javaweb;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//对test.jsp进行拦截
//@WebFilter("/test.jsp")
public class HelloFilter implements Filter {

   
    public HelloFilter() {
    }

	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		//获取全局初始化参数
		String encoding = this.fConfig.getServletContext().getInitParameter("encoding");
		String name = this.fConfig.getInitParameter("name");
		//System.out.println("encoding:"+encoding);
		//System.out.println("name:"+name);
		System.out.println("1. Before HelloFilter's chain.doFilter");
		chain.doFilter(request, response);
		System.out.println("2. After HelloFilter's chain.doFilter");
	}

	private FilterConfig fConfig;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
		System.out.println("fist init....");
	}

}
