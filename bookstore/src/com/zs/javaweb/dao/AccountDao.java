package com.zs.javaweb.dao;

import com.zs.javaweb.domain.Account;

public interface AccountDao {

	/**
	 * 根据accountId获取对应Account对象
	 * @param accountId
	 * @return
	 */
	Account getAccount(Integer accountId);
	
	/**
	 * 更新Account对象的余额
	 * @param accountId
	 * @param amount
	 */
	void updateBalance(Integer accountId, float amount);
}
