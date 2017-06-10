package com.zs.javaweb.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.zs.javaweb.domain.Account;
import com.zs.javaweb.impl.AccountDaoImpl;

public class AccountDaoTest {
	private AccountDaoImpl accountDaoImpl = new AccountDaoImpl();

	@Test
	public void testGetAccount() {
		Account account = accountDaoImpl.getAccount(330225);
		System.out.println(account);
	}

	@Test
	public void testUpdateBalance() {
		accountDaoImpl.updateBalance(330225, 500);
	}

}
