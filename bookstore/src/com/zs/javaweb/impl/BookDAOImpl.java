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
		//翻页类Page需要设置3个属性,totalItemNumber,pageNo,list
		Page<Book> page = new Page<>(cb.getPageNo()); //初始化时把cb的当前页码
		//设置查询到的总记录数
		page.setTotalItemNumber(getTotalBookNumber(cb));
		//为了防止当前页码不合法,这句话不设置的话，如果新建CriteriaBook对象页码不合法，getPageList(CriteriaBook cb, int pageSize)错误
		cb.setPageNo(page.getPageNo());
		page.setList(getPageList(cb, 3));  //为当前翻页类设置
		return page;
	}

	@Override
	public long getTotalBookNumber(CriteriaBook cb) {
		String sql = "SELECT count(id) FROM book WHERE price >= ? AND price <= ?";
		return getSingleVal(sql, cb.getMinPrice(), cb.getMaxPrice());
	}

	//带限制条件的查询,查询满足minPrice-maxPrice,且返回pageSize页的翻页类Page对象
	@Override
	public List<Book> getPageList(CriteriaBook cb, int pageSize) {
		String sql = "SELECT  id, author, title, price, publishingDate, salesAmount"
				   +", storeNumber, remark FROM book WHERE price >= ? AND price <= ? "
				   +"LIMIT ?,?";
		return queryForList(sql, cb.getMinPrice(),cb.getMaxPrice(),(cb.getPageNo()-1)*pageSize,pageSize);
	}

	@Override
	public int getStoreNumber(int id) {
		String sql = "SELECT storeNumber FROM book WHERE id = ?";
		return getSingleVal(sql, id);
	}

}
