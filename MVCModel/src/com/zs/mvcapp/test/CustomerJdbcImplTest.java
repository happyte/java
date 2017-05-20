package com.zs.mvcapp.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.zs.mvcapp.dao.CustomerDAO;
import com.zs.mvcapp.dao.impl.CustomerDaoJdbcImpl;
import com.zs.mvcapp.domain.CriteriaCustomer;
import com.zs.mvcapp.domain.Customer;

public class CustomerJdbcImplTest {
	
	private CustomerDAO customerDAO = new CustomerDaoJdbcImpl();

	@Test
	public void testGetAll() {
		List<Customer> customers = customerDAO.getAll();
		System.out.println(customers);
	}

	@Test
	public void testSave() {
		Customer cust = new Customer(null, "haha", "Beijing", "13780056344");
		customerDAO.save(cust);
	}

	@Test
	public void testGetInteger() {
		Customer cust = customerDAO.get(5);
		System.out.println(cust);
	}

	@Test
	public void testDelete() {
		customerDAO.delete(5);
	}

	@Test
	public void testGetCountWithName() {
		long count = customerDAO.getCountWithName("lemon");
		System.out.println("count:"+count);
	}
	
	@Test
	public void testGetForListWithCriteriaCustomer(){
		CriteriaCustomer cc = new CriteriaCustomer("haha", null, null);
		List<Customer> customers = customerDAO.getForListWithCriteriaCustomer(cc);
		System.out.println("customers:"+customers);
	}

}
