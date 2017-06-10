package com.zs.javaweb.impl;

import com.zs.javaweb.dao.BaseDao;
import com.zs.javaweb.dao.UserDao;
import com.zs.javaweb.domain.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao{

	@Override
	public User getUser(String username) {
		String sql = "SELECT userId, username, accountId " +
				"FROM userinfo WHERE username = ?";
		return query(sql, username); 
	}
}
