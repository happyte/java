package com.zs.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.zs.mybatis.entities.Class;

public class Test4 {

	@Test
	public void testGetClass3() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		String statement = "com.zs.mybatis.res.classMapper.getClass3";
		Class clazz = sqlSession.selectOne(statement, 2);
		sqlSession.close();
		System.out.println(clazz);
	}
	
	@Test
	public void testGetClass4() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		String statement = "com.zs.mybatis.res.classMapper.getClass4";
		Class clazz = sqlSession.selectOne(statement, 2);
		sqlSession.close();
		System.out.println(clazz);
	}

}
