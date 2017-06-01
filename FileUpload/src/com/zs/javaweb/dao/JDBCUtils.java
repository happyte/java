package com.zs.javaweb.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class JDBCUtils {
	
	private static DataSource dataSource = null;
	
	static{
		//数据源放在静态代码块中，只能被初始化一次
		dataSource = new ComboPooledDataSource("javawebapp");
	}	
	
	/**
	 * 建立连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}	
	
	/**
	 * 释放连接
	 * @param connection
	 */
	public static void releaseConnection(Connection connection){
		try {
			if(connection != null){
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
