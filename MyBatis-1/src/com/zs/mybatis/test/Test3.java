package com.zs.mybatis.test;


import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.zs.mybatis.entities.Class;

public class Test3 {

	@Test
	public void testGetClass() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		String statement = "com.zs.mybatis.res.classMapper.getClass";
		Class clazz = sqlSession.selectOne(statement, 1);
		sqlSession.close();
		System.out.println(clazz);
	}
	
	@Test
	public void testGetClass2() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		String statement = "com.zs.mybatis.res.classMapper.getClass2";
		Class clazz = sqlSession.selectOne(statement, 1);
		sqlSession.close();
		System.out.println(clazz);
	}

}
