package com.zs.javaweb.dao;

import java.util.List;

public interface Dao<T> {
	/**
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	long insert(String sql, Object ... args);
	/**
	 * 
	 * @param sql
	 * @param args
	 */
	void update(String sql, Object ... args);
	/**
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	T query(String sql, Object ... args);
	/**
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	List<T> queryForList(String sql, Object ... args);	
	/**
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	<V> V getSingleVal(String sql, Object ... args);
	/**
	 * 
	 * @param sql
	 * @param params
	 */
	void batch(String sql, Object[]... params);

}
