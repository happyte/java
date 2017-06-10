package com.zs.javaweb.dao;

import java.util.Collection;
import java.util.List;

import com.zs.javaweb.domain.Book;
import com.zs.javaweb.domain.CriteriaBook;
import com.zs.javaweb.domain.ShoppingCart;
import com.zs.javaweb.domain.ShoppingCartItem;
import com.zs.javaweb.web.Page;

public interface BookDao {
	/**
	 * 根据id 获取指定的book
	 * @param id
	 * @return
	 */
	 Book getBook(int id);
	 
	 /**
	  * 根据传入的查询条件类CriteriaBook获取Page对象
	  * @param cb
	  * @return
	  */
	 Page<Book> getPage(CriteriaBook cb);
	 
	 /**
	  * 根据CriteriaBook的minPrice和maxPrice查询条件找出符合条件的书的数量
	  * @param cb
	  * @return
	  */
	 long getTotalBookNumber(CriteriaBook cb);
	
	 /**
	  * 根据CriteriaBook对象和当前页码查询当前页的Book集合
	  * @param cb
	  * @param pageSize
	  * @return
	  */
	 List<Book> getPageList(CriteriaBook cb, int pageSize);
	 
	 /**
	  * 根据id 查询Book对象的storeNumber字段
	  * @param id
	  * @return
	  */
	 int getStoreNumber(int id);
	 
	 /**
	  * 根据传入的ShoppingCartItem集合，来批量更新数据库中的storeNumber和salesamount
	  * @param items
	  */
	 void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items);
}
