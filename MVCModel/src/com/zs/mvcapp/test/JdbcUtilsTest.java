package com.zs.mvcapp.test;

import com.zs.mvcapp.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class JdbcUtilsTest {

	@Test
	public void testGetConnection() throws SQLException {
		Connection connection = JdbcUtils.getConnection();
		System.out.println(connection); 
	}
}

