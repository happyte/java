package com.zs.spring.hibernate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.spring.hibernate.service.BookShopService;
import com.zs.spring.hibernate.service.Cashier;

@Service
public class CashierImpl implements Cashier {
	
	@Autowired
	private BookShopService bookShopService ;
	
	@Override
	public void checkout(String username, List<String> isbns) {
		for(String isbn:isbns){
			bookShopService.purchase(username, isbn);
		}
	}

}
