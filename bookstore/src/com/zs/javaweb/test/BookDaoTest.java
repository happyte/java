package com.zs.javaweb.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Test;

import com.zs.javaweb.domain.Book;
import com.zs.javaweb.impl.BookDAOImpl;

public class BookDaoTest {
	
	private BookDAOImpl bookDAOImpl = new BookDAOImpl();

	//单元测试成功，数据库中的外键中外键所指向的键值的on delete属性设置为SET NULL,首先要保证外键所指向的那个键值是允许为NULL的
	@Test
	public void testInsert() {
		String sql = "INSERT INTO trade (userId, tradetime) VALUES(?,?)";
		long id = bookDAOImpl.insert(sql, 1, new Date(new java.util.Date().getTime()));
		System.out.println("id:"+id);
	}

	//单元测试成功
	@Test
	public void testUpdate() {
		String sql = "UPDATE book SET salesamount = ? WHERE id = ?";
		bookDAOImpl.update(sql, 10, 2);
	}

	//单元测试成功
	@Test
	public void testQuery() {
		String sql = "SELECT id,author,title,price,publishingDate,salesAmount"
					 +",storeNumber,remark FROM book WHERE id = ?";
		Book book = bookDAOImpl.query(sql, 1);
		System.out.println(book);
	}

	//单元测试成功
	@Test
	public void testQueryForList() {
		String sql = "SELECT id,author,title,price,publishingDate,salesAmount"
				 +",storeNumber,remark FROM book WHERE id < ?";
		List<Book> books = bookDAOImpl.queryForList(sql, 3);
		System.out.println(books);
	}

	//单元测试成功
	@Test
	public void testGetSingleVal() {
		String sql = "SELECT count(id) FROM book";
		long count = bookDAOImpl.getSingleVal(sql);
		System.out.println("count:"+count);
	}

	//单元测试成功
	@Test
	public void testBatch() {
		String sql = "UPDATE book SET salesamount = ?, storeNumber = ? WHERE id = ?";
		bookDAOImpl.batch(sql, new Object[]{1,30,1}, new Object[]{2,20,2});
	}

}
