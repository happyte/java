package com.zs.javaweb.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.zs.javaweb.dao.BaseDao;
import com.zs.javaweb.dao.TradeItemDao;
import com.zs.javaweb.domain.ShoppingCartItem;
import com.zs.javaweb.domain.TradeItem;

public class TradeItemDaoImpl extends BaseDao<TradeItem> implements TradeItemDao {

	@Override
	public void batchSave(Collection<TradeItem> items) {
		String sql = "INSERT INTO tradeitem(bookId,quantity,tradeId) VALUES (?, ?, ?)";
		List<TradeItem> tradeItems = new ArrayList<>(items);
		//有多少条购物车记录，就需要修改多少个Book对象，参数有3个
		Object[][] params = new Object[items.size()][3];
		for(int i = 0; i < tradeItems.size(); i++){
			params[i][0] = tradeItems.get(i).getBookId();
			params[i][1] = tradeItems.get(i).getQuantity();
			params[i][2] = tradeItems.get(i).getTradeId();
		}
		batch(sql, params);
	}

	@Override
	public Collection<TradeItem> getTradeItemWithTradeId(Integer tradeId) {
		String sql = "SELECT itemId, bookId, quantity, tradeId FROM tradeitem WHERE tradeId = ?";
		return queryForList(sql, tradeId);
	}

}
