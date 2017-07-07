package com.zs.spring.ssh.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.zs.spring.ssh.entities.Employee;

public class EmployeeDao extends BaseDao {
	//显示所有用户
	public List<Employee> getAll(){
		String hql = "FROM Employee e LEFT OUTER JOIN FETCH e.department";
		return getSession().createQuery(hql).list();
	}
	//删除一个用户
	public void delete(Integer id){
		String hql = "DELETE FROM Employee e WHERE e.id = ?";
		getSession().createQuery(hql).setInteger(0, id).executeUpdate();
	}
	
	//添加一个用户
	public void saveOrUpdate(Employee employee){
		getSession().saveOrUpdate(employee);
	}
	
	//验证用户是否存在
	public Employee getEmployeeByLastName(String lastName){
		String hql = "FROM Employee e WHERE e.lastName = ?";
		return (Employee) getSession().createQuery(hql).setString(0, lastName).uniqueResult();
	}
}
