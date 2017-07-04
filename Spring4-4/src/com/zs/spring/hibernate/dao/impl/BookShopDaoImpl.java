package com.zs.spring.hibernate.dao.impl;


import javax.security.auth.login.AccountException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zs.spring.hibernate.dao.BookShopDao;
import com.zs.spring.hibernate.exception.BookStockException;
import com.zs.spring.hibernate.exception.UserAccountException;


//持久化层
@Repository("bookShopDao")
public class BookShopDaoImpl implements BookShopDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int findBookPriceByIsbn(String isbn) {
		String hql = "SELECT b.price FROM Book b WHERE b.isbn = ?";
		Query query = getSession().createQuery(hql).setString(0, isbn);
		return (int) query.uniqueResult();
	}

	@Override
	public void updateBookStock(String isbn) {
		//检查书的库存
		String hql = "SELECT b.stock FROM Book b WHERE b.stock = ?";
		int stock = (int) getSession().createQuery(hql).setString(0, isbn).uniqueResult();
		if(stock == 0){
			throw new BookStockException("库存不足");
		}
		//更新库存
		String hql2 = "UPDATE Book b SET b.stock = b.stock-1 WHERE b.isbn = ?";
		getSession().createQuery(hql2).setString(0, isbn).executeUpdate();
	}

	@Override
	public void updateUserAccount(String username, int price){
		//检查余额
		String hql = "SELECT a.balance FROM Account a WHERE a.username = ?";
		int balance = (int) getSession().createQuery(hql).setString(0, username).uniqueResult();
		if(balance < price){
			throw new UserAccountException("库存不足");
		}
		String hql2 = "UPDATE Account a SET a.balance = a.balance - ? WHERE a.username = ?";
		getSession().createQuery(hql2).setInteger(0, price).setString(1, username).executeUpdate();
	}

}
