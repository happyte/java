package com.zs.javaweb;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AuthorityFilter1
 */
@WebFilter("/*")
public class AuthorityFilter extends HttpFilter {

	//权限过滤操作
		@Override
		public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
				throws IOException, ServletException {
			List<String> uncheckedUrls = Arrays.asList("/login.jsp","/logout.jsp",
					"/authority-manager.jsp","/articles.jsp","/403.jsp",
					"/loginServlet","/authorityServlet");
			String servletPath = request.getServletPath();
			//1.如果servletPath属于uncheckedUrls其中的某个，放行
			if(uncheckedUrls.contains(servletPath)){
				filterChain.doFilter(request, response);
				return;
			}
			User user = (User)request.getSession().getAttribute("user");
			//2.如果user为空，则跳转到login.jsp
			if(user == null){
				response.sendRedirect(request.getContextPath()+"/login.jsp");
				return;
			}
			List<Authority> authorities = user.getAuthorities();
			//3.如果user非空，且有该页面的权限，放行
			//不比较displayName,重写了Authority的equal方法
			Authority authority = new Authority(null, servletPath);
			if(authorities.contains(authority)){
				filterChain.doFilter(request, response);
				return;
			}
			//4.没有该页面的权限，跳转到403.jsp
			response.sendRedirect(request.getContextPath()+"/403.jsp");
		}

}
