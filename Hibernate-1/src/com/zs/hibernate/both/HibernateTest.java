package com.zs.hibernate.both;

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
	
	private SessionFactory sessionFactory = null;
	private Session session = null;
	private Transaction transaction = null;
	
	@Before
	public void init(){
		System.out.println("before...");
		//1). 创建 Configuration 对象: 对应 hibernate 的基本配置信息和 对象关系映射信息
		Configuration configuration = new Configuration().configure();
		//2). 创建一个 ServiceRegistry 对象: hibernate 4.x 新添加的对象
		//hibernate 的任何配置和服务都需要在该对象中注册后才能有效.
		ServiceRegistry serviceRegistry = 
				new ServiceRegistryBuilder().applySettings(configuration.getProperties())
						                    .buildServiceRegistry();
		//3).
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//2. 创建一个 Session 对象
		session = sessionFactory.openSession();
		//3. 开启事务
		transaction = session.beginTransaction();
	}
	
	@After
	public void destroy(){
		System.out.println("after...");
		//5. 提交事务 
		transaction.commit();
		//6. 关闭 Session
		session.close();
		//7. 关闭 SessionFactory 对象
		sessionFactory.close();
	}

	@Test
	public void testMany2OneSave(){
		
	}
	
	@Test
	public void testMany2OneGet(){
		
	}
	
	@Test
	public void testMany2OneUpdate(){
		
	}
	
	@Test
	public void testMany2OneDelete(){
		
	}
}
