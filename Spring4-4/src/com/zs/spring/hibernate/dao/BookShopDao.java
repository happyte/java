package com.zs.spring.hibernate.dao;

public interface BookShopDao {
	public int  findBookPriceByIsbn(String isbn);
	public void updateBookStock(String isbn);
	public void updateUserAccount(String username,int price);
}
