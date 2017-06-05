package com.zs.javaweb.test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import com.zs.javaweb.dao.BaseDao;
import com.zs.javaweb.impl.BookDAOImpl;

public class BookDaoTest {
	
	private BookDAOImpl bookDAOImpl = new BookDAOImpl();

	@Test
	public void testInsert() {
		String sql = "INSERT INTO trade (userId, tradeTime) VALUES(?,?)";
		long id = bookDAOImpl.insert(sql, 1,new Date(new java.util.Date().getTime()));
		System.out.println(id);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryForList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSingleVal() {
		fail("Not yet implemented");
	}

	@Test
	public void testBatch() {
		fail("Not yet implemented");
	}

}
