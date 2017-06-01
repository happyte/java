package com.zs.javaweb.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class Dao<T> {
	
	private static QueryRunner queryRunner = new QueryRunner();
	
	private Class<T> clazz;
	
	public Dao() {
		Type superClass = getClass().getGenericSuperclass();
		if (superClass instanceof ParameterizedType ) {
			ParameterizedType parameterizedType = (ParameterizedType)superClass;
			Type [] typeArgs = parameterizedType.getActualTypeArguments();
			if (typeArgs != null && typeArgs.length > 0) {
				if (typeArgs[0] instanceof Class) {
					clazz = (Class<T>) typeArgs[0]; //clazz是Customer类对象
				}
			}
		}
	}
	
	/**
	 * 返回某个字段的值，或返回数据表中记录的个数，E代表任何类型,范型方法
	 * @param <E>
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException 
	 */
	public  <E> E getForValue(Connection connection,String sql,Object ... args) throws SQLException {
		return (E) queryRunner.query(connection, sql, new ScalarHandler(), args);
	}
	
	/**
	 * 返回对应T所对应的List
	 * @return
	 * @throws SQLException 
	 */
	public List<T> getForList(Connection connection, String sql,Object ... args) throws SQLException {		
		return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args); 
	}
	
	/**
	 * 返回对应的T的一个实体类对象
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException 
	 */
	public T get(Connection connection,String sql,Object ... args) throws SQLException {
		return queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);
	}
	
	/**
	 * 该方法封装了INSERT,DELETE,UPDATE
	 * @param sql:SQL语句
	 * @param args:SQL语句绑定的占位符
	 * @throws SQLException 
	 */
	public void update(Connection connection,String sql,Object ... args) throws SQLException {
		queryRunner.update(connection, sql, args);
	}
}
