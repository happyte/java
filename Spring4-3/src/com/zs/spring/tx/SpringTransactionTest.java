package com.zs.spring.tx;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTransactionTest {
	
	private ApplicationContext ctx = null;
	private BookShopDao bookShopDao = null;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//@Repository已经装配成一个bean了，所以无需在xml中设置,可以在Repository设置value
		bookShopDao = (BookShopDao) ctx.getBean("bookShopDao");
	}

	@Test
	public void testBookShopDapFindPriceByIsbn() {
		System.out.println(bookShopDao.findBookPriceByIsbn("1001"));
	}
	
	@Test
	public void testBookShopDaoUpdateBookStock(){
		bookShopDao.updateBookStock("1001");
	}
	
	@Test
	public void testBookShopUpdateUserAccount(){
		bookShopDao.updateUserAccount("happyte", 100);
	}

}
