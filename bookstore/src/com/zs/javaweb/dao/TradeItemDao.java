package com.zs.javaweb.dao;

import java.util.Collection;

import com.zs.javaweb.domain.TradeItem;

public interface TradeItemDao {
	
	/**
	 * 批量保存TradeItem对象
	 * @param items
	 */
	void batchSave(Collection<TradeItem> items);
	
	/**
	 * 根据tradeId获取跟它相关联的tradeItem集合
	 * @param tradeId
	 * @return
	 */
	Collection<TradeItem> getTradeItemWithTradeId(Integer tradeId);

}
