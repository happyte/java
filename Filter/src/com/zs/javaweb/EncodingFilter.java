package com.zs.javaweb;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.tracing.dtrace.ProviderAttributes;

@WebFilter("/encoding/*")
public class EncodingFilter extends HttpFilter {
	
	private String encoding;
	
	@Override
	protected void init() {
		encoding = getfConfig().getServletContext().getInitParameter("encoding");
		System.out.println("EncodingFilter init ...");
	}

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("encoding:"+encoding);
		System.out.println("EncodingFilter doFilter ...");
		filterChain.doFilter(request, response);
		
	}

}
