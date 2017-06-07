package com.zs.javaweb.impl;

import java.util.List;

import com.zs.javaweb.dao.BaseDao;
import com.zs.javaweb.dao.BookDao;
import com.zs.javaweb.domain.Book;
import com.zs.javaweb.domain.CriteriaBook;
import com.zs.javaweb.web.Page;

public class BookDAOImpl extends BaseDao<Book> implements BookDao {

	@Override
	public Book getBook(int id) {
		String sql = "SELECT id,author,title,price,publishingDate,salesAmount"
				 +",storeNumber,remark FROM book WHERE id = ?";
		return query(sql, id);
	}

	@Override
	public Page<Book> getPage(CriteriaBook cb) {
		
		return null;
	}

	@Override
	public long getTotalBookNumber(CriteriaBook cb) {
		String sql = "SELECT count(id) FROM book WHERE price >= ? AND price <= ?";
		return getSingleVal(sql, cb.getMinPrice(), cb.getMaxPrice());
	}

	//带限制条件的查询,查询满足minPrice-maxPrice,且返回pageSize页的翻页类Page对象
	@Override
	public List<Book> getPageList(CriteriaBook cb, int pageSize) {
		String sql = "SELECT id,author,title,price,publishingDate,salesAmount"
				   +",storeNumber,remark FROM book WHERE price >= ? AND price <= ?"
				   +"LIMIT ?,?";
		return queryForList(sql, cb.getMinPrice(),cb.getMaxPrice(),(cb.getPageNo()-1)*pageSize,pageSize);
	}

	@Override
	public int getStoreNumber(int id) {
		String sql = "SELECT storeNumber FROM book WHERE id = ?";
		return getSingleVal(sql, id);
	}

}
