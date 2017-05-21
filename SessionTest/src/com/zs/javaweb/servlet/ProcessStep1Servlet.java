package com.zs.javaweb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProcessStep1Servlet
 */
@WebServlet("/processStep1")
public class ProcessStep1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String[] books = request.getParameterValues("book");
		HttpSession session = request.getSession();
		//参数传递给session
		session.setAttribute("books", books);
		//重定向到step2.jsp,不需要request参数因此重定向
		//request.getContextPath()获取当前WEB目录
		response.sendRedirect(request.getContextPath()+"/step2.jsp");
	}
}
