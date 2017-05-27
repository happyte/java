package com.zs.javaweb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//默认拦截login目录下的所有页面
@WebFilter("/login/*")
public class LoginFilter extends HttpFilter {
	private String userSessionKey;
	private String redirectPage;
	private String uncheckedUrls;
	
	@Override
	protected void init() {
		ServletContext servletContext = getfConfig().getServletContext();
		userSessionKey = servletContext.getInitParameter("userSessionKey");
		redirectPage = servletContext.getInitParameter("redirectPage");
		uncheckedUrls = servletContext.getInitParameter("uncheckedUrls");
	}

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("getContextPath:"+request.getContextPath());
		//1.获得当前的请求路径，判断是否在不需要拦截的页面中，如果是，放行
		String urlPath = request.getRequestURL().toString();  //localhost:8989/Filter/login/list.jsp
		String uriPath = request.getRequestURI();      // /Filter/login/list.jsp
		String servletPath = request.getServletPath(); // /login/list.jsp
		List<String> pathList = Arrays.asList(uncheckedUrls.split(","));
		if(pathList.contains(servletPath)){
			filterChain.doFilter(request, response);
			return;
		}
		//2.判断session中是否有userSessionKey键值，如果存在放行
		if(!(request.getSession().getAttribute("userSessionKey") == null)){
			filterChain.doFilter(request, response);
			return;
		}
		//3.不存在，重定向到redirectPage页面下,记住重定向是当前站点的根目录
		response.sendRedirect(request.getContextPath()+redirectPage);
	}

}
