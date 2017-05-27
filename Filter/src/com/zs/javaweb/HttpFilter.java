package com.zs.javaweb;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.http11.filters.VoidInputFilter;

//自定义Filter继承类,抽象类，重写了一部分继承方法
public abstract class HttpFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("HttpFilter doFilter....");
		//父类赋给子类，必须强转
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		doFilter(req, res, filterChain);
	}
	
	//抽象方法，子类必须实现
	public abstract void doFilter(HttpServletRequest arg0, HttpServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException;
	
	private FilterConfig fConfig;
	
	public FilterConfig getfConfig() {
		return fConfig;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("HttpFilter init....");
		this.fConfig = arg0;
	}
	
	protected void init(){
		
	}

}
