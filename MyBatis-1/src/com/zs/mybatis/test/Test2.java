package com.zs.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.zs.mybatis.entities.Order;

public class Test2 {

	@Test
	public void testGetOrderById() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		String startment = "com.zs.mybatis.res.orderMapper.getOrderById";
		Order order = sqlSession.selectOne(startment, 1);
		sqlSession.close();
		System.out.println(order);
	}
	
	@Test
	public void testSelectOrder() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		String startment = "com.zs.mybatis.res.orderMapper.selectOrder";
		Order order = sqlSession.selectOne(startment, 1);
		sqlSession.close();
		System.out.println(order);
	}
	
	@Test
	public void testSelectOrderResultMap() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		String startment = "com.zs.mybatis.res.orderMapper.selectOrderResultMap";
		Order order = sqlSession.selectOne(startment, 1);
		sqlSession.close();
		System.out.println(order);
	}

}
