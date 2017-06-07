package com.zs.javaweb.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zs.javaweb.domain.Book;
import com.zs.javaweb.domain.CriteriaBook;
import com.zs.javaweb.service.BookService;
import com.zs.javaweb.web.Page;


@WebServlet("/bookServlet")
public class BookServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String methodName = request.getParameter("method");	
		try {
			Method method = getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void getBooks(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String pageNostr = request.getParameter("pageNostr");
		String minPriceStr = request.getParameter("minPrice");
		String maxPriceStr = request.getParameter("maxPrice");
		int pageNo = 1;
		int minPrice = 0;
		int maxPrice = Integer.MAX_VALUE;
		//有时没有传入minPrice和maxPrice参数时，会转换错误,所以使用try catch
		try {
			pageNo = Integer.parseInt(pageNostr);
		} catch (NumberFormatException e) {
			
		}
		try {
			minPrice = Integer.parseInt(minPriceStr);
		} catch (NumberFormatException e) {
			
		}
		try {
			maxPrice = Integer.parseInt(maxPriceStr);
		} catch (NumberFormatException e) {
			
		}
		CriteriaBook cb = new CriteriaBook(minPrice, maxPrice, pageNo);
		//中间使用一层BookSerive,现在只是调用BookDaoImpl的方法
		Page<Book> page = bookService.getPage(cb);
		request.setAttribute("bookpage", page);
		//来到展示图书的jsp页面books.jsp
		request.getRequestDispatcher("/books.jsp").forward(request, response);
		
	}

}