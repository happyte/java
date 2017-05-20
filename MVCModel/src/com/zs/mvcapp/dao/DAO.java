package com.zs.mvcapp.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.sun.corba.se.pept.transport.EventHandler;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zs.mvcapp.domain.CriteriaCustomer;
import com.zs.mvcapp.domain.Customer;
import com.zs.mvcapp.utils.JdbcUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;

/**
 * 封装了基本的CRUD的方法，以供子类使用
 * @param <T>：当前DAO处理的实体类的类型是什么
 */
public class DAO<T> {
	private QueryRunner queryRunner = new QueryRunner();
	
	private Class<T> clazz;  //不明白这个代表的具体含义? 与反射相关
	
	public DAO() {
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
	 */
	public  <E> E getForValue(String sql,Object ... args) {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();
			return (E) queryRunner.query(connection, sql, new ScalarHandler(), args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.releaseConnection(connection);
		}
		return null;
	}
	
	/**
	 * 返回对应T所对应的List
	 * @return
	 */
	public List<T> getForList(String sql,Object ... args) {		
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();
			return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JdbcUtils.releaseConnection(connection);
		}
		return null;
	}
	
	/**
	 * 返回对应的T的一个实体类对象
	 * @param sql
	 * @param args
	 * @return
	 */
	public T get(String sql,Object ... args) {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();
			return queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.releaseConnection(connection);
		}
		return null;
	}
	
	/**
	 * 该方法封装了INSERT,DELETE,UPDATE
	 * @param sql:SQL语句
	 * @param args:SQL语句绑定的占位符
	 */
	public void update(String sql,Object ... args) {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();
			queryRunner.update(connection, sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.releaseConnection(connection);
		}
	}
}
