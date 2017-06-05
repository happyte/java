package com.zs.javaweb.dao;

import java.beans.Statement;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zs.javaweb.db.JDBCUtils;

public class BaseDao<T> implements Dao<T> {
	
	private QueryRunner queryRunner = new QueryRunner();
	
	private Class<T> clazz;
	
	public  BaseDao() {
		Type superClass = getClass().getGenericSuperclass();
		if (superClass instanceof ParameterizedType ) {
			ParameterizedType parameterizedType = (ParameterizedType)superClass;
			Type [] typeArgs = parameterizedType.getActualTypeArguments();
			if (typeArgs != null && typeArgs.length > 0) {
				if (typeArgs[0] instanceof Class) {
					clazz = (Class<T>) typeArgs[0]; //clazz是范型类对象
				}
			}
		}
	}

	//QueryRunner没有提供返回ID值的方法，需要自己写
	@Override
	public long insert(String sql, Object... args) {
		long id = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			if(args != null){
				for(int i = 0; i < args.length; i++){
					preparedStatement.setObject(i + 1, args[i]);
				}
			}
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if(resultSet.next()){
				id = resultSet.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtils.release(connection);
		}
		return id;
	}

	@Override
	public void update(String sql, Object... args) {
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			queryRunner.update(connection, sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.release(connection);
		}
	}

	@Override
	public T query(String sql, Object... args) {
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			return queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.release(connection);
		}
		return null;
	}

	@Override
	public List<T> queryForList(String sql, Object... args) {
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.release(connection);
		}
		return null;
	}

	@Override
	public <V> V getSingleVal(String sql, Object... args) {
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			return (V) queryRunner.query(connection, sql, new ScalarHandler(), args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.release(connection);
		}
		return null;
	}

	@Override
	public void batch(String sql, Object[]... params) {
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			queryRunner.batch(connection, sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.release(connection);
		}
	}
}
