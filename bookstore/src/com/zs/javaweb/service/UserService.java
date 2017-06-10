package com.zs.javaweb.service;

import com.zs.javaweb.domain.User;
import com.zs.javaweb.impl.UserDaoImpl;

public class UserService {
	
	private UserDaoImpl userDaoImpl = new UserDaoImpl();

	public User getUser(String username) {
		return userDaoImpl.getUser(username);
	}

}
