package com.zs.spring.xml.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.spring.xml.service.BookShopService;
import com.zs.spring.xml.service.Cashier;

public class CashierImpl implements Cashier {

	private BookShopService bookShopService ;
	
	public void setBookShopService(BookShopService bookShopService) {
		this.bookShopService = bookShopService;
	}
	
	@Override
	public void checkout(String username, List<String> isbns) {
		for(String isbn:isbns){
			bookShopService.purchase(username, isbn);
		}
	}

}
