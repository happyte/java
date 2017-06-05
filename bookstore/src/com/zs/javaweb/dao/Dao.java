package com.zs.javaweb.dao;

import java.util.List;

/**
 * Dao接口，定义Dao的基本操作，由BaseDao提供实现
 * @param <T>
 */
public interface Dao<T> {
	/**
	 * 执行INSERT操作，并返回插入后的记录ID
	 * @param sql  SQL语句
	 * @param args 填充占位符的可变参数
	 * @return 插入新记录的ID
	 */
	long insert(String sql, Object ... args);
	
	/**
	 * 包括INSERT(无返回值),UPDATE,DELETE操作
	 * @param sql
	 * @param args
	 */
	void update(String sql, Object ... args);
	
	/**
	 * 执行单条记录的查询，返回与记录对应的类的对象
	 * @param sql
	 * @param args
	 * @return
	 */
	T query(String sql, Object ... args);
	
	/**
	 * 执行多条记录的查询，返回与记录对应的类的对象的集合
	 * @param sql
	 * @param args
	 * @return
	 */
	List<T> queryForList(String sql, Object ... args);	
	
	/**
	 * 执行一个属性或值的查询，例如查询某条记录的一个字段
	 * @param sql
	 * @param args
	 * @return
	 */
	<V> V getSingleVal(String sql, Object ... args);
	
	/**
	 * 批量更新操作
	 * @param sql
	 * @param params
	 */
	void batch(String sql, Object[]... params);

}
