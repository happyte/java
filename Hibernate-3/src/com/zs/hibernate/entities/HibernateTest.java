package com.zs.hibernate.entities;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class HibernateTest {
	
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void init(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = 
				new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				                            .buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	
	@After
	public void destroy(){
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	

	@Test
	public void testHQL() {
		String hql = "FROM Employee e WHERE e.salary > ? AND e.email LIKE ? AND e.dept = ?"
					  +"ORDER BY e.salary";
		Query query = session.createQuery(hql);
		Department dept = new Department();
		dept.setId(6);
		query.setFloat(0, 6000).setString(1, "%A%").setEntity(2, dept);
		List<Employee> emps = query.list();
		System.out.println(emps.size());
	}
	
	@Test
	public void testHQLNamedParameter(){
		//基于命名参数
		String hql = "FROM Employee e WHERE e.salary > :sal AND e.email LIKE :email";
		//绑定参数
		Query query = session.createQuery(hql);
		query.setFloat("sal", 6000);
		query.setString("email", "%A%");
		List<Employee> emps = query.list();
		System.out.println(emps.size());
	}
	
	//分页测试
	@Test
	public void testPageQuery(){
		String hql = "FROM Employee";
		Query query = session.createQuery(hql);
		int pageNo = 2;
		int pageSize = 5;
		List<Employee> emps = query.setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
		System.out.println(emps);
	}
	
	//在映射文件中定义命名查询语句,employee.hbm.xml文件的最后一行
	@Test
	public void testNamedQuery(){
		Query query = session.getNamedQuery("salaryEmps");
		List<Employee> emps = query.setFloat("minSal", 8000).setFloat("maxSal", 10000).list();
		System.out.println(emps);
	}
	
	//投影查询，查询结果仅包含实体的部分属性
	@Test
	public void testFieldQuery(){
		String hql = "SELECT e.salary, e.email, e.dept FROM Employee e WHERE e.dept = :dept ";
		Query query = session.createQuery(hql);
		Department dept = new Department();
		dept.setId(1);
		List<Object[]> result = query.setEntity("dept", dept).list();
		for(Object[] objs:result){
			System.out.println(Arrays.asList(objs));
		}
	}
	
	@Test
	public void testFieldQuery2(){
		String hql = "SELECT new Employee(e.salary, e.email, e.dept) FROM Employee e WHERE e.dept = :dept ";
		Query query = session.createQuery(hql);
		Department dept = new Department();
		dept.setId(1);
		List<Employee> emps = query.setEntity("dept", dept).list();
		for(Employee emp:emps){
			System.out.println(emp.getId()+", "+emp.getSalary()+", "+emp.getEmail()+", "+emp.getDept());
		}
	}
	
	//报表查询,用于对数据分组和统计,根据e.dept的ID大小排序,约束条件为minSal>3000
	@Test
	public void testGroupBy(){
		String hql = "SELECT min(e.salary), max(e.salary) "
					+ "FROM Employee e "
					+ "GROUP BY e.dept "
					+ "HAVING min(salary) > :minSal";
		Query query = session.createQuery(hql).setFloat("minSal", 3000);
		List<Object[]> result = query.list();
		for(Object[] objs:result){
			System.out.println(Arrays.asList(objs));
		}
	}

}
