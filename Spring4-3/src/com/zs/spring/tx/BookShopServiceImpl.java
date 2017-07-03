package com.zs.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookShopServiceImpl implements BookShopService {
	
	@Autowired
	private BookShopDao bookShopDao;

	@Transactional(propagation=Propagation.REQUIRES_NEW)
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
