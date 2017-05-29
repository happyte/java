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

//自定义Filter继承类,抽象类，重写了一部分继承方法
public abstract class HttpFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		//System.out.println("HttpFilter doFilter....");
		//父类赋给子类，必须强转
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		doFilter(req, res, filterChain);
	}
	
	//抽象方法，子类必须实现
	public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException;
	
	private FilterConfig fConfig;
	
	public FilterConfig getfConfig() {
		return fConfig;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//System.out.println("HttpFilter filterConfig init....");
		this.fConfig = filterConfig;
		init();
	}
	
	//子类重写了这个方法的话，根本没有调用父类的这个方法，直接调用子类的这个方法
	protected void init(){
		System.out.println("HttpFilter init....");
	}

}
