package com.zs.javaweb;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXStreamWriter;
import com.sun.tracing.dtrace.ProviderAttributes;

//@WebFilter这类参数和web.xml是冲突的，现在@WebFilter这类参数还不知道如何使用，现在web.xml中使用
//@WebFilter("/hello.jsp")
public class UsernameFilter implements Filter {


    public UsernameFilter() {

    }


	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		//如果请求的参数不等于happyte,则返回login.jsp,以后判断String类型相等千万不要用==号了
		if (!request.getParameter("username").equals(fConfig.getInitParameter("username"))) {
			request.setAttribute("message", "账号错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		//验证正确放行
		chain.doFilter(request, response);
	}

	private FilterConfig fConfig;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
		System.out.println("usernamefilter init....");
	}

}
