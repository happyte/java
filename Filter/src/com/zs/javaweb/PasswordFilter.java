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


@WebFilter(
		urlPatterns = { "/hello.jsp" }, 
		initParams = { 
				@WebInitParam(name = "password", value = "123")
		})
public class PasswordFilter implements Filter {


    public PasswordFilter() {

    }


	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(!request.getParameter("password").equals(fConfig.getInitParameter("password"))){
			request.setAttribute("message", "密码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	private FilterConfig fConfig;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
		System.out.println("password filter init...");
	}

}
