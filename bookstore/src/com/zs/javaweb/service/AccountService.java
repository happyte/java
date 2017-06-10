package com.zs.javaweb.service;

import com.zs.javaweb.domain.Account;
import com.zs.javaweb.impl.AccountDaoImpl;

public class AccountService {

	private AccountDaoImpl accountDaoImpl = new AccountDaoImpl();

	public Account getAccount(Integer accountId) {
		return accountDaoImpl.getAccount(accountId);
	}
}
