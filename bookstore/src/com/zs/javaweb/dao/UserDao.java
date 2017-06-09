package com.zs.javaweb.dao;

import com.zs.javaweb.domain.User;

public interface UserDao {

	/**
	 * 根据用户名获得对应的用户对象
	 * @param username
	 * @return
	 */
	User getUser(String username);
}
