package com.zs.javaweb.domain;

public class ShoppingCartItem {
	
	private Book book;
	private int quantity;
	
	public Book getBook() {
		return book;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public ShoppingCartItem(Book book) {
		this.book = book;
		this.quantity = 1;  //初始默认1本
	}
	
	//该书的总费用
	public float getItemMoney(){
		return book.getPrice()*quantity;
	}
	
	//书的数量加1
	public void increment(){
		quantity ++;
	}
}
