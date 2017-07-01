package com.zs.spring.annotaion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zs.spring.annotaion.repository.UserRepository;
import com.zs.spring.annotaion.repository.UserRepositoryImpl;

@Service
public class UserService {
	@Autowired
	@Qualifier("userJdbcRepository")
	private UserRepository userRepository;
	
	public void add(){
		System.out.println("UserService add...");
		userRepository.save();
	}
}
