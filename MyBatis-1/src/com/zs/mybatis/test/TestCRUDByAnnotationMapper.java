package com.zs.mybatis.test;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.zs.mybatis.entities.Student;
import com.zs.mybatis.res.StudentMapperI;

public class TestCRUDByAnnotationMapper {

	@Test
	public void testAdd() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		StudentMapperI mapper = sqlSession.getMapper(StudentMapperI.class);
		Student student = new Student();
		student.setName("tree");
		student.setBirthday(new Date());
		int res = mapper.add(student);
		sqlSession.close();
		System.out.println("res:"+res);
	}
	
	@Test
	public void testUpdate(){
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		StudentMapperI mapper = sqlSession.getMapper(StudentMapperI.class);
		Student student = mapper.getUserById(3);
		System.out.println(student);
		student.setName("哈喽");
		int res = mapper.update(student);
		sqlSession.close();
		System.out.println("res:"+res);
	}
	
	@Test
	public void testDelete(){
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		StudentMapperI mapper = sqlSession.getMapper(StudentMapperI.class);
		int res = mapper.deleteById(3);
		sqlSession.close();
		System.out.println("res:"+res);
	}
	
	@Test
	public void testGetAll(){
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		StudentMapperI mapper = sqlSession.getMapper(StudentMapperI.class);
		List<Student> students =mapper.getAll();
		sqlSession.close();
		System.out.println("students:"+students);
	}

}
