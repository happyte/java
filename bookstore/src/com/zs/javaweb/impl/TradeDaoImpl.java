package com.zs.javaweb.impl;

import java.util.Collection;

import com.zs.javaweb.dao.BaseDao;
import com.zs.javaweb.dao.TradeDao;
import com.zs.javaweb.domain.Trade;

public class TradeDaoImpl extends BaseDao<Trade> implements TradeDao {

	@Override
	public void insert(Trade trade) {
		String sql = "INSERT INTO trade(userId,tradeTime) VALUES (?,?)";
		insert(sql, trade.getUserId(), trade.getTradeTime());
	}

	@Override
	public Collection<Trade> getTradeWithUserId(Integer userId) {
		String sql = "SELECT tradeId, userId, tradeTime FROM trade WHERE userId = ?";
		return queryForList(sql, userId);
	}

}
