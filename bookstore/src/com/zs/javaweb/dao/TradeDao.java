package com.zs.javaweb.dao;

import java.util.Collection;

import com.zs.javaweb.domain.Trade;

public interface TradeDao {
	
	/**
	 * 向数据表中插入Trade对象
	 * @param trade
	 */
	void insert(Trade trade);
	
	/**
	 * 根据userId获取Trade交易的集合
	 * @param userId
	 * @return
	 */
	Collection<Trade> getTradeWithUserId(Integer userId);
	
}
