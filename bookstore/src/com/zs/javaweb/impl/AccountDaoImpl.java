package com.zs.javaweb.impl;

import com.zs.javaweb.dao.AccountDao;
import com.zs.javaweb.dao.BaseDao;
import com.zs.javaweb.domain.Account;

public class AccountDaoImpl extends BaseDao<Account> implements AccountDao {

	@Override
	public Account getAccount(Integer accountId) {
		String sql = "SELECT accountId, balance FROM account WHERE accountId = ?";
		return query(sql, accountId); 
	}

	@Override
	public void updateBalance(Integer accountId, float amount) {
		String sql = "UPDATE account SET balance = balance - ? WHERE accountId = ? ";
		update(sql, amount, accountId);
	}

}
