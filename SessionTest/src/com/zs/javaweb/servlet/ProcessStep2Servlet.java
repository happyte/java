package com.zs.javaweb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zs.javaweb.domain.Customer;

import sun.util.resources.en.CurrencyNames_en_US;

/**
 * Servlet implementation class ProcessStep2Servlet
 */
@WebServlet("/processStep2")
public class ProcessStep2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String cardType = request.getParameter("cardType");
		String cardNumber = request.getParameter("cardNumber");
		Customer customer = new Customer(name, address, cardType, cardNumber);
		HttpSession session = request.getSession();
		session.setAttribute("customer", customer);
		response.sendRedirect(request.getContextPath()+"/confirm.jsp");
	}

}
