package com.zs.hibernate.helloworld;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import javax.security.auth.Destroyable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;


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
	public void test() {
		System.out.println("test...");
		//4. 执行保存操作
//		News news = new News("Hibernate", "Java", new Date(new java.util.Date().getTime()));
//		session.save(news);
		News news2 = (News)session.get(News.class, 1);
		news2.setAuthor("Oracle");
		System.out.println(news2);
	}
	
	/**
	 * save方法使临时对象变为持久化对象，
	 * 分配id
	 * 在fluash缓存时会发送一条insert语句
	 * 在save方法之前的id是无效的
	 * 持久化对象的id是不能被修改的
	 */
	@Test
	public void testSave(){
		News news = new News();
		news.setAuthor("BB");
		news.setTitle("bb");
		news.setId(100);
		news.setDate(new Date(new java.util.Date().getTime()));
		System.out.println(news);
		session.save(news);
		System.out.println(news);
	}
	
	/**
	 * persist也会执行INSERT操作
	 * 和save方法的区别  
	 * 在INSERT之前执行了setId会抛出异常
	 */
	@Test
	public void testPersist(){
		News news = new News();
		news.setAuthor("EE");
		news.setTitle("ee");
		news.setId(101);
		news.setDate(new Date(new java.util.Date().getTime()));
		System.out.println(news);
		session.persist(news);
		System.out.println(news);
	}
	
	/**
	 * get VS load
	 * 执行get方法，会立即加载该对象
	 * 执行load方法，若不使用该对象，不会立即执行查询操作
	 * get立即检索，load延迟检索
	 * 
	 * 对数据库中没有的元素
	 * get返回null,load抛出异常
	 * 
	 * load可能会抛出懒加载异常
	 */
	@Test
	public void testGet(){
		News news = (News) session.get(News.class, 1);
		//System.out.println(news);
	}
	
	@Test
	public void testLoad(){
		News news = (News) session.load(News.class, 1);
		System.out.println(news.getClass().getName());
		//System.out.println(news);
	}
	
	/**
	 * 若更新一个持久化对象，不需要显示调用update方法
	 * 因为在调用Transaction方法的commit方法时，会先调用flush()方法
	 * 更新一个游离对象，需要显示的调用upate方法
	 * 
	 * 如何能够让update方法不盲目触发update语句
	 * 在.hbm.xml文件中设置select-before-update=true
	 * 
	 * 若对数据表中没有的记录调用update,会发生异常
	 * 
	 * 当update关联一个游离对象,如果session中已经存在了相同的uid,会抛出异常
	 */
	@Test
	public void testUpdate(){
		News news = (News) session.load(News.class, 1);
		news.setAuthor("Oracle");
	}
	
	/**
	 * 只要OID与数据库中的一致，那么就会删除掉
	 * 若OID在数据库中不存在，那么会抛出异常
	 */
	@Test
	public void testDelete(){
//		News news =  new News();
//		news.setId(1);
		
		News news = (News) session.get(News.class, 2);
		session.delete(news);
	}
	
	/**
	 * evict把持久化对象从session中移除
	 */
	@Test
	public void testEvict(){
		News news1 = (News) session.get(News.class, 3);
		News news2 = (News) session.get(News.class, 4);
		news1.setAuthor("XXX");
		news2.setAuthor("OOO");
		session.evict(news1);
	}
	
	@Test
	public void testDoWork(){
		session.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println(connection);
			}
		});
	}

}
