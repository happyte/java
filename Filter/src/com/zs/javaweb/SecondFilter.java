package com.zs.javaweb;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//@WebFilter("/test.jsp")
public class SecondFilter implements Filter {

    public SecondFilter() {
    }


	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//System.out.println("3. Before SecondFilter's chain.doFilter");
		chain.doFilter(request, response);
		//System.out.println("4. After SecondFilter's chain.doFilter");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		//System.out.println("second init....");
	}

}
