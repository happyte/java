package com.zs.javaweb.service;


import com.zs.javaweb.domain.Book;
import com.zs.javaweb.domain.CriteriaBook;
import com.zs.javaweb.domain.ShoppingCart;
import com.zs.javaweb.impl.BookDAOImpl;
import com.zs.javaweb.web.Page;

public class BookService {
	
	private BookDAOImpl bookDAOImpl = new BookDAOImpl();
	
	public Page<Book> getPage(CriteriaBook cb){
		return bookDAOImpl.getPage(cb);
	}
	
	public Book getBook(int id){
		return bookDAOImpl.getBook(id);
	}
	
	public boolean addToCart(int id,ShoppingCart sc){
		Book book = bookDAOImpl.getBook(id);
		//判断该本书是存在的
		if(book != null){
			sc.add(book);
			return true;
		}
		return false;
	}
	
	public void remove(int id,ShoppingCart sc){
		Book book = bookDAOImpl.getBook(id);
		if(book != null){
			sc.removeItem(id);
		}
	}
	
	public void clear(ShoppingCart sc){
		sc.clear();
	}
}
