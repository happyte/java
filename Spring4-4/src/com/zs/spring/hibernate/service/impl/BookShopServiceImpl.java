package com.zs.spring.hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zs.spring.hibernate.dao.BookShopDao;
import com.zs.spring.hibernate.service.BookShopService;

@Service
public class BookShopServiceImpl implements BookShopService {
	
	@Autowired
	private BookShopDao bookShopDao;

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
