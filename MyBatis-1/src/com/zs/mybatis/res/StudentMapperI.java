package com.zs.mybatis.res;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zs.mybatis.entities.Student;

//使用注解的方式表明要执行的SQL
public interface StudentMapperI {

	@Insert("insert into student(name,birthday) values (#{name},#{birthday})")
	public int add(Student student);
	
	@Delete("delete from student where id = #{id}")
	public int deleteById(int id);
	
	@Update("update student set name = #{name},birthday= #{birthday} where id = #{id}")
	public int update(Student student);
	
	@Select("select * from student where id = #{id}")
	public Student getUserById(int id);
	
	@Select("select * from student")
	public List<Student> getAll();
	
}
