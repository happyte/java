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
	public void testCascade(){
		Customer customer = (Customer) session.get(Customer.class, 3);
		customer.getOrders().clear();
	}
	
	@Test
	public void testDelete(){
		//在不设定级联关系的情况下, 且 1 这一端的对象有 n 的对象在引用, 不能直接删除 1 这一端的对象
		Customer customer = (Customer) session.get(Customer.class, 1);
		session.delete(customer); 
	}
	
	@Test
	public void testUpdat2(){
		Customer customer = (Customer) session.get(Customer.class, 1);
		customer.getOrders().iterator().next().setOrderName("GGG"); 
	}
	
	@Test
	public void testUpdate(){
		Customer customer = (Customer) session.get(Customer.class, 2);
		customer.getOrders().iterator().next().setOrderName("XXX");
	}
	
	@Test
	public void testOne2ManyGet(){
		Order order = (Order) session.get(Order.class, 3);
		order.getCustomer().setCustomerName("happyte");
	}
	
	@Test
	public void testMany2OneGet(){
		Customer customer = (Customer) session.get(Customer.class, 2);
		System.out.println(customer.getCustomerName());
		System.out.println(customer.getOrders().size());
		
	}
	
	@Test
	public void testMany2OneSave(){
		Customer customer = new Customer();
		customer.setCustomerName("AAA");
		
		Order order1 = new Order();
		order1.setOrderName("ORDER-1");
		
		Order order2 = new Order();
		order2.setOrderName("ORDER-2");
		
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);
		
		session.save(customer);
		session.save(order1);
		session.save(order2);
	}
	

}
