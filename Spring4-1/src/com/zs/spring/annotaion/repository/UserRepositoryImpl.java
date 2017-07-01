package com.zs.spring.annotaion.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zs.spring.annotaion.TestObject;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired(required=false)
	private TestObject to;

	@Override
	public void save() {
		System.out.println("UserRepositoryImpl...");
		System.out.println(to);
	}

}
