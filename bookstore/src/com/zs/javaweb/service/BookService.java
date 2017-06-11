package com.zs.javaweb.service;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zs.javaweb.dao.AccountDao;
import com.zs.javaweb.domain.Book;
import com.zs.javaweb.domain.CriteriaBook;
import com.zs.javaweb.domain.ShoppingCart;
import com.zs.javaweb.domain.ShoppingCartItem;
import com.zs.javaweb.domain.Trade;
import com.zs.javaweb.domain.TradeItem;
import com.zs.javaweb.impl.AccountDaoImpl;
import com.zs.javaweb.impl.BookDAOImpl;
import com.zs.javaweb.impl.TradeDaoImpl;
import com.zs.javaweb.impl.TradeItemDaoImpl;
import com.zs.javaweb.impl.UserDaoImpl;
import com.zs.javaweb.web.BookStoreWebUtils;
import com.zs.javaweb.web.Page;

public class BookService {
	
	private BookDAOImpl bookDAOImpl = new BookDAOImpl();
	private AccountDaoImpl accountDaoImpl = new AccountDaoImpl();
	private TradeDaoImpl tradeDaoImpl = new TradeDaoImpl();
	private TradeItemDaoImpl tradeItemDaoImpl = new TradeItemDaoImpl();
	private UserDaoImpl userDaoImpl = new UserDaoImpl();
	
	public Page<Book> getPage(CriteriaBook cb){
		return bookDAOImpl.getPage(cb);
	}
	
	public Book getBook(int id){
		return bookDAOImpl.getBook(id);
	}
	
	public boolean addToCart(int id,ShoppingCart sc){
		Book book = bookDAOImpl.getBook(id);
		//判断该本书是存在的
		if(book != null){
			sc.add(book);
			return true;
		}
		return false;
	}
	
	public void remove(int id,ShoppingCart sc){
		Book book = bookDAOImpl.getBook(id);
		if(book != null){
			sc.removeItem(id);
		}
	}
	
	public void clear(ShoppingCart sc){
		sc.clear();
	}
	
	public void updateQuantity(ShoppingCart sc,int id, int quantity){
		sc.updateItemQuantity(id, quantity);
	}

	public void cash(HttpServletRequest request, String username, String accountId) {
		//1.批量更新book数据表的salesAmount和storeNumber
		ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
		bookDAOImpl.batchUpdateStoreNumberAndSalesAmount(sc.getItems());
		//故意出错，查看事务操作是否统一
		//int j = 10/0;
		//2.更新余额
		accountDaoImpl.updateBalance(Integer.parseInt(accountId), sc.getTotalMoney());
		//3.插入trade记录 
		Trade trade = new Trade(null, userDaoImpl.getUser(username).getUserId(), new Date(new java.util.Date().getTime()));
		tradeDaoImpl.insert(trade);
		//4.批量操作tradeItem
		Collection<TradeItem> tradeItems = new ArrayList<>();
		Collection<ShoppingCartItem> items = sc.getItems();
		List<ShoppingCartItem> shoppingCartItems = new ArrayList<>(items);
		for(int i = 0; i < shoppingCartItems.size(); i++){
			TradeItem item = new TradeItem();
			item.setBookId(shoppingCartItems.get(i).getBook().getId());
			item.setQuantity(shoppingCartItems.get(i).getQuantity());
			item.setTradeId(trade.getTradeId());
			tradeItems.add(item);
		}
		tradeItemDaoImpl.batchSave(tradeItems);
		//5.清空购物车记录
		sc.clear();
	}
}
