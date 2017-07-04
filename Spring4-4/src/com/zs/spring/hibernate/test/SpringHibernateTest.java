package com.zs.spring.hibernate.test;


import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zs.spring.hibernate.dao.BookShopDao;
import com.zs.spring.hibernate.dao.impl.BookShopDaoImpl;
import com.zs.spring.hibernate.service.BookShopService;
import com.zs.spring.hibernate.service.Cashier;
import com.zs.spring.hibernate.service.impl.BookShopServiceImpl;
import com.zs.spring.hibernate.service.impl.CashierImpl;

public class SpringHibernateTest {
	
	private ApplicationContext ctx = null;
	private BookShopDao bookShopDao = null;
//	private BookShopService bookShopService = null;
//	private Cashier cashier = null;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		bookShopDao = ctx.getBean(BookShopDaoImpl.class);
//		bookShopService = ctx.getBean(BookShopServiceImpl.class);
//		cashier = ctx.getBean(CashierImpl.class);
	}

	@Test
	public void test() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}
	
	@Test
	public void testBookShopDapFindPriceByIsbn() {
		System.out.println(bookShopDao.findBookPriceByIsbn("1001"));
	}

}
