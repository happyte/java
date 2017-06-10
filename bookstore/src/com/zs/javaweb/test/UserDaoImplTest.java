package com.zs.javaweb.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.zs.javaweb.domain.User;
import com.zs.javaweb.impl.UserDaoImpl;

public class UserDaoImplTest {
	private UserDaoImpl userDaoImpl = new UserDaoImpl();

	@Test
	public void testGetUser() {
		User user = userDaoImpl.getUser("happyte");
		System.out.println(user); 
	}

}
