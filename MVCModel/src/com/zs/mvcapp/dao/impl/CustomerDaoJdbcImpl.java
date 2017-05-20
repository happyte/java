package com.zs.mvcapp.dao.impl;

import java.util.List;

import com.zs.mvcapp.dao.CustomerDAO;
import com.zs.mvcapp.dao.DAO;
import com.zs.mvcapp.domain.CriteriaCustomer;
import com.zs.mvcapp.domain.Customer;

public class CustomerDaoJdbcImpl extends DAO<Customer> implements CustomerDAO{

	@Override
	public List<Customer> getAll() {
		String sql = "SELECT id, name, address, phone FROM customers";
		return getForList(sql);
	}

	@Override
	public void save(Customer customer) {
		String sql = "INSERT into customers(name,address,phone) VALUES(?,?,?)";
		update(sql, customer.getName(), customer.getAddress(), customer.getPhone());
	}

	@Override
	public Customer get(Integer id) {
		String sql = "SELECT id, name, address, phone FROM customers WHERE id = ?";
		return get(sql, id);
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM customers WHERE id = ?";
		update(sql, id);
		
	}

	@Override
	public long getCountWithName(String name) {
		String sql = "SELECT count(id) FROM customers WHERE name = ?";
		return getForValue(sql, name);
	}

	@Override
	public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc) {
//		String sql = "SELECT id, name, address, phone FROM customers  WHERE " +
//					 "name LIKE ? AND address LIKE ? AND phone LIKE ?";
		String sql = "SELECT id, name, address, phone FROM customers  WHERE " +
				 "name LIKE" + cc.getName() + "AND address LIKE " + cc.getAddress() + "AND phone LIKE" + cc.getPhone();
//		return getForList(sql, cc.getName(), cc.getAddress(), cc.getPhone());
		return getForList(sql);
	}

	@Override
	public void update(Customer customer) {
		String sql = "UPDATE customers SET name = ?, address = ?, phone = ? WHERE id = ?";
		update(sql, customer.getName(), customer.getAddress(), customer.getPhone(), customer.getId());
	}
}
