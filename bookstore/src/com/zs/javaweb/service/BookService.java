package com.zs.javaweb.service;

import com.zs.javaweb.domain.Book;
import com.zs.javaweb.domain.CriteriaBook;
import com.zs.javaweb.impl.BookDAOImpl;
import com.zs.javaweb.web.Page;

public class BookService {
	
	private BookDAOImpl bookDAOImpl = new BookDAOImpl();
	
	public Page<Book> getPage(CriteriaBook cb){
		return bookDAOImpl.getPage(cb);
	}
}
