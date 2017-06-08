package com.zs.javaweb.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class ShoppingCart {

	//key为book的id,value为该book对应的ShoppingCartItem对象
	private Map<Integer, ShoppingCartItem> books = new HashMap<>();
	
	//往购物车中添加书
	public void add(Book book){
		ShoppingCartItem item = books.get(book.getId());
		if(item == null){
			item = new ShoppingCartItem(book);//新建ShoppingCartItem对象
			books.put(book.getId(), item);
		}
		//该本书已经在购物车中，数量加1本
		else{
			item.increment();
		}
	}
	
	//购物车中是否有某条记录
	public boolean hasBook(Integer id){
		return books.containsKey(id);
	}
	
	//返回购物车本身
	public Map<Integer, ShoppingCartItem> getBooks(){
		return books;
	}
	
	//返回加入购物车中的ShoppingCartItem的集合
	public Collection<ShoppingCartItem> getItems(){
		return books.values(); //本身返回的就是Collection<V>
	}
	
	//返回购物车中的总数量
	public int getBookNumber(){
		int total = 0;
		for(ShoppingCartItem item:books.values()){
			total += item.getQuantity();
		}
		return total;
	}
	
	//判断购物车是否为空
	public boolean isEmpty(){
		return books.isEmpty();
	}
	
	//清空购物车
	public void clear(){
		books.clear();
	}
	
	//移除某次加入购物车的操作
	public void removeItem(Integer id){
		books.remove(id);
	}
	
	//修改某次加入购物车的数量
	public void updateItemQuantity(Integer id, int quantity){
		ShoppingCartItem item = books.get(id);
		if(item != null){
			item.setQuantity(quantity);
		}
	}
	
	//计算购物车中的总额
	public float getTotalMoney(){
		int totalMoney = 0;
		for(ShoppingCartItem item:books.values()){
			totalMoney += item.getItemMoney();
		}
		return totalMoney;
	}
	
}
