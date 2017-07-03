package com.zs.spring.xml.service.impl;

import com.zs.spring.xml.BookShopDao;
import com.zs.spring.xml.service.BookShopService;

public class BookShopServiceImpl implements BookShopService {
	
	private BookShopDao bookShopDao;
	
	public void setBookShopDao(BookShopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}

	@Override
	public void purchase(String username, String isbn) {
		//查询价格
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		//更新库存
		bookShopDao.updateBookStock(isbn);
		//更新余额
		bookShopDao.updateUserAccount(username, price);
	}

}
