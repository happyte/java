package com.zs.hibernate.n21;

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
		Customer customer = new Customer();
		customer.setCustomerName("AA");
		Order order1 = new Order();
		order1.setOrderName("ORDER-1");
		Order order2 = new Order();
		order2.setOrderName("ORDER-2");
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		session.save(customer);
		session.save(order1);
		session.save(order2);
	}
	
	@Test
	public void testMany2OneGet(){
		Order order1 = (Order) session.get(Order.class, 1);
		System.out.println(order1.getOrderName());
		System.out.println(order1.getCustomer().getClass().getName());
		Customer customer = order1.getCustomer();
		System.out.println(customer.getCustomerName());
	}
	
	@Test
	public void testMany2OneUpdate(){
		Order order = (Order) session.get(Order.class, 1);
		order.getCustomer().setCustomerName("AAA");
	}
	
	@Test
	public void testMany2OneDelete(){
		Customer customer = (Customer) session.get(Customer.class, 1);
		session.delete(customer);
	}
}
