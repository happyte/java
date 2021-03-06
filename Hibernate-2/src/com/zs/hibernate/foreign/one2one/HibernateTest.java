package com.zs.hibernate.foreign.one2one;

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
	public void testSave() {
		Department department = new Department();
		department.setDeptName("DEPT-AAA");
		
		Manager manager = new Manager();
		manager.setMgrName("MGR-A");
		
		department.setMgr(manager);
		manager.setDept(department);
		
		session.save(manager);
		session.save(department);
	}
	
	@Test
	public void testGet(){
		Department department = (Department) session.get(Department.class, 1);
		System.out.println(department.getDeptName());
		//manager0_.MGR_ID=department1_.DEPT_ID,查询条件应该是dept.manager_id = mgr.manager_id 
		//而不是dept.dept_id = mgr.manager_id 
		Manager manager = department.getMgr();
		System.out.println(manager.getMgrName());
	}

}
