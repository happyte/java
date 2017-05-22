package com.zs.javaweb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;


@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String checkCode = request.getParameter("CHECK_CODE_PARAM");
		String sessionCode = (String)request.getSession().getAttribute("CHECK_CODE_SESSION");
		System.out.println("CHECK_CODE_PARAM:"+checkCode);
		System.out.println("sessionCode:"+sessionCode);
		//如果session中的验证码与表单中提交的验证码一致，则授予处理
		if(checkCode.equals(sessionCode)){
			System.out.println("提交成功");
			return;
		}
		request.getSession().setAttribute("message", "验证码不一致");
		response.sendRedirect(request.getContextPath()+"/check/index.jsp");
//		request.setAttribute("message", "验证码不一致");
//		request.getRequestDispatcher("/check/index.jsp").forward(request, response);
	}

}
