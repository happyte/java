package com.zs.mvcapp.dao;

import java.util.List;

import com.zs.mvcapp.domain.CriteriaCustomer;
import com.zs.mvcapp.domain.Customer;

/**
 * 这些抽象方法都是业务逻辑中对应的操作
 * @author zhangshu
 *
 */
public interface CustomerDAO {
	public List<Customer> getAll();      //显示所有的用户
	public void save(Customer customer); //保存修改后的某个用户
	public Customer get(Integer id);     //修改用户前需要通过id查处对应的用户  
	public void delete(Integer id);      //根据id删除用户
	public long getCountWithName(String name); //查找相同名字的记录条数
	public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc); //重置domain对象，便于模糊查询
	public void update(Customer customer);    //更新用户

}
