package com.zs.javaweb.impl;

import java.util.Collection;

import com.zs.javaweb.dao.BaseDao;
import com.zs.javaweb.dao.TradeDao;
import com.zs.javaweb.domain.Trade;

public class TradeDaoImpl extends BaseDao<Trade> implements TradeDao {

	@Override
	public void insert(Trade trade) {
		String sql = "INSERT INTO trade(userId,tradeTime) VALUES (?,?)";
		long tradeId = insert(sql, trade.getUserId(), trade.getTradeTime());
		//这里要设置id,  否则后面要获得id的操作得去查找数据库了
		trade.setTradeId((int)tradeId);
	}

	@Override
	public Collection<Trade> getTradeWithUserId(Integer userId) {
		String sql = "SELECT tradeId, userId, tradeTime FROM trade WHERE userId = ?";
		return queryForList(sql, userId);
	}

}
