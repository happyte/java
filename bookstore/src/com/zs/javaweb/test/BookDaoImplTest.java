package com.zs.javaweb.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.zs.javaweb.domain.Book;
import com.zs.javaweb.domain.CriteriaBook;
import com.zs.javaweb.impl.BookDAOImpl;
import com.zs.javaweb.web.Page;

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
		CriteriaBook cb = new CriteriaBook(50, 60, 50);
		Page<Book> page = bookDAOImpl.getPage(cb);
		System.out.println("pageNo:"+ page.getPageNo());
		System.out.println("totalPageNumber:"+ page.getTotalPageNumber());
		System.out.println("list:"+ page.getList());
		System.out.println("prePage:" + page.getPrePage());
		System.out.println("nextPage:"+ page.getNextPage());
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
		CriteriaBook cb = new CriteriaBook(50, 60, 20);
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
