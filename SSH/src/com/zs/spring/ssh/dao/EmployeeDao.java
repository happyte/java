package com.zs.spring.ssh.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.zs.spring.ssh.entities.Employee;

public class EmployeeDao {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public List<Employee> getAll(){
		String hql = "FROM Employee e LEFT OUTER JOIN FETCH e.department";
		return getSession().createQuery(hql).list();
	}
}
