package com.zs.javaweb;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		//利用反射创建对应的方法
		try {
			//不能用request.getClass()方法代替
			Method method = getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	UserDao userDao = new UserDao();
	
	public void login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//通过username 查找对应的User对象
		String username = request.getParameter("username");
		User user = userDao.getUser(username);
		request.getSession().setAttribute("user", user);
		//回到articles.jsp
		response.sendRedirect(request.getContextPath()+"/articles.jsp");
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//使session失效
		request.getSession().invalidate();
		//重定向到logout.jsp
		response.sendRedirect(request.getContextPath()+"/logout.jsp");
	}
}
