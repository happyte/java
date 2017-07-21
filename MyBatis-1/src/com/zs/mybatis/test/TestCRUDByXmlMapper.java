package com.zs.mybatis.test;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.zs.mybatis.entities.Student;

public class TestCRUDByXmlMapper {

	@Test
	public void testAdd() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		String statement = "com.zs.mybatis.res.studentMapper.addStudent";
		Student student = new Student();
		student.setName("tree");
		student.setBirthday(new Date());
		int res = sqlSession.insert(statement, student);
		sqlSession.close();
		System.out.println("res:"+res);
	}
	
	@Test
	public void testUpdate(){
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		String statement = "com.zs.mybatis.res.studentMapper.getStudent";
		Student student = sqlSession.selectOne(statement, 1);
		System.out.println(student);
		student.setName("柠檬");
		String statement1 = "com.zs.mybatis.res.studentMapper.updateStudent";
		int res = sqlSession.update(statement1, student);
		sqlSession.close();
		System.out.println("res:"+res);
	}
	
	@Test
	public void testDelete(){
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		String statement = "com.zs.mybatis.res.studentMapper.deleteStudent";
		int res = sqlSession.delete(statement, 2);
		sqlSession.close();
		System.out.println("res:"+res);
	}
	
	@Test
	public void testGetAll(){
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		String statement = "com.zs.mybatis.res.studentMapper.getAllStudent";
		List<Student> students = sqlSession.selectList(statement);
		sqlSession.close();
		System.out.println("students:"+students);
	}
}
