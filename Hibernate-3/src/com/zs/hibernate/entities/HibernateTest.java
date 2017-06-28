package com.zs.hibernate.entities;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionFactoryObserverChain;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;



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
	
	//HQL(迫切)左外连接
	@Test
	public void testLeftJoinFetch(){
		//不加SELECT DISTINCT查询出来的结果是重复的
		String hql = "SELECT DISTINCT d FROM Department d LEFT JOIN FETCH d.emps";
		Query query = session.createQuery(hql);
		List<Department> depts = query.list();
		System.out.println(depts.size());
		for(Department dept:depts){
			System.out.println(dept.getName()+"-"+dept.getEmps().size());
		}
	}
	
	//左外连接
	@Test
	public void testLeftJoin(){
		String hql = "SELECT DISTINCT d FROM Department d LEFT JOIN d.emps";
		Query query = session.createQuery(hql);
		List<Department> depts = query.list();
		System.out.println(depts.size());
		for(Department dept:depts){
			//开始的时候d.emps并没有初始化，迫切左外连接是初始化的
			System.out.println(dept.getName()+"-"+dept.getEmps().size());
		}
	}
	
	//HQL(迫切)内连接
	@Test
	public void testInnerJoinFetch(){
		//不加SELECT DISTINCT查询出来的结果是重复的
		String hql = "SELECT DISTINCT d FROM Department d INNER JOIN FETCH d.emps";
		Query query = session.createQuery(hql);
		List<Department> depts = query.list();
		System.out.println(depts.size());
		for(Department dept:depts){
			//d.emps为空不打印，迫切左外连接是全部打印的
			System.out.println(dept.getName()+"-"+dept.getEmps().size());
		}
	}
	
	//内连接 
	@Test
	public void testInnerJoin(){
		String hql = "SELECT DISTINCT d FROM Department d INNER JOIN d.emps";
		Query query = session.createQuery(hql);
		List<Department> depts = query.list();
		System.out.println(depts.size());
		for(Department dept:depts){
			//开始的时候d.emps并没有初始化，迫切左外连接是初始化的
			System.out.println(dept.getName()+"-"+dept.getEmps().size());
		}
	}
	
	@Test
	public void testQBC(){
		Criteria criteria = session.createCriteria(Employee.class);
		//添加查询条件
		criteria.add(Restrictions.eq("email", "FAKLS")); // where this_.EMAIL=?  and this_.SALARY>?
		criteria.add(Restrictions.gt("salary", 1000F));
		//唯一的一条记录
		Employee employee = (Employee) criteria.uniqueResult();
		System.out.println(employee);
	}
	
	@Test
	public void testQBC2(){
		Criteria criteria = session.createCriteria(Employee.class);
		//1.AND:使用Conjunction表示
		Conjunction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.like("email", "A",MatchMode.ANYWHERE));
		Department dept = new Department();
		dept.setId(3);
		conjunction.add(Restrictions.eq("dept", dept));
		
		//2.OR
		criteria.add(conjunction);
		System.out.println(conjunction);
		System.out.println(criteria.list());
	}
	
	@Test
	public void testQBC3(){
		Criteria criteria = session.createCriteria(Employee.class);
		//统计查询
		criteria.setProjection(Projections.max("salary"));
		System.out.println(criteria.uniqueResult());
	}
	
	//二级缓存测试
	@Test
	public void testHibernateSecondLevelCache(){
		Employee employee = (Employee) session.get(Employee.class, 20);
		System.out.println(employee.getName());
		
		transaction.commit();
		session.close();
		
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		
		Employee employee2 = (Employee) session.get(Employee.class, 20);
		System.out.println(employee2.getName());
	}
	
	//集合的二级缓存测试
	@Test
	public void testCollectionSecondLevelCache(){
		Department dept = (Department) session.get(Department.class, 5);
		System.out.println(dept.getName());
		System.out.println(dept.getEmps().size());
		
		transaction.commit();
		session.close();
		
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		
		Department dept2= (Department) session.get(Department.class, 5);
		System.out.println(dept2.getName());
		System.out.println(dept2.getEmps().size());
	}
	
	//HQL和QBC默认是不使用二级缓存的，需要加设置
	@Test
	public void testQueryCache(){
		Query query = session.createQuery("FROM Employee");
		query.setCacheable(true);
		
		List<Employee> emps = query.list();
		System.out.println(emps.size());
		
		emps = query.list();
		System.out.println(emps.size());
	}

}
