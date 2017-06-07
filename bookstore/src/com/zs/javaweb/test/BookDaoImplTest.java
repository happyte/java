package com.zs.javaweb.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.zs.javaweb.domain.Book;
import com.zs.javaweb.domain.CriteriaBook;
import com.zs.javaweb.impl.BookDAOImpl;

public class BookDaoImplTest {

	private BookDAOImpl bookDAOImpl = new BookDAOImpl();
	
	//单元测试通过
	@Test
	public void testGetBook() {
		Book book = bookDAOImpl.getBook(5);
		System.out.println("book:"+book);
	}

	@Test
	public void testGetPage() {
		fail("Not yet implemented");
	}

	//单元测试通过
	@Test
	public void testGetTotalBookNumber() {
		CriteriaBook cb = new CriteriaBook(50, 60, 2);
		long num = bookDAOImpl.getTotalBookNumber(cb);
		System.out.println("num:"+num);
	}

	//单元测试通过，刚才没通过的原因是LIMIT前面需要有一个空格!
	@Test
	public void testGetPageList() {
		CriteriaBook cb = new CriteriaBook(50, 60, 2);
		List<Book> books = bookDAOImpl.getPageList(cb, 3);
		System.out.println("books:"+books);
	}

	//单元测试通过
	@Test
	public void testGetStoreNumber() {
		int storeNumber = bookDAOImpl.getStoreNumber(5);
		System.out.println("storeNumber:"+storeNumber);
	}

}
