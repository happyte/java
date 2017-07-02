package com.zs.spring.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;




public class JdbcTest {
	private ApplicationContext ctx = null;
	private JdbcTemplate jdbcTemplate;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
	}
	
	@Test
	public void test() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}
	
	@Test
	public void testUpdate(){
		String sql = "UPDATE departments SET name = ? WHERE id = ?";
		jdbcTemplate.update(sql,"happyte",1);
	}
	
	@Test
	public void testBatchUpdate(){
		String sql = "INSERT INTO departments(name) VALUES(?)";
		List<Object[]> batchArgs = new ArrayList<>();
		batchArgs.add(new Object[]{"HH"});
		batchArgs.add(new Object[]{"JJ"});
		batchArgs.add(new Object[]{"KK"});
		jdbcTemplate.batchUpdate(sql,batchArgs);
		
	}
	
	/**
	 * 无法级联查询
	 */
	@Test
	public void testQueryForObject(){
		String sql = "SELECT id,name,salary,email,dept_id as \"department.id\" FROM Employees WHERE id = ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper, 1);
		System.out.println(employee);
	}
	
	/**
	 * 查询多项数据
	 */
	@Test
	public void testQueryForList(){
		String sql = "SELECT id,name,salary,email FROM Employees WHERE id > ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		List<Employee> employees = jdbcTemplate.query(sql,rowMapper,10);
		System.out.println(employees);
	}
	
	@Test
	public void testQueryForObject2(){
		String sql = "SELECT count(id) FROM Employees";
		long count = jdbcTemplate.queryForObject(sql, Long.class);
		System.out.println(count);
	}

}
