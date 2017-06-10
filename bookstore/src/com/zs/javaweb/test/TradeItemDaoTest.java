package com.zs.javaweb.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.zs.javaweb.domain.TradeItem;
import com.zs.javaweb.impl.TradeItemDaoImpl;

public class TradeItemDaoTest {
	
	private TradeItemDaoImpl tradeItemDaoImpl = new TradeItemDaoImpl();

	@Test
	public void testBatchSave() {
		Collection<TradeItem> items = new ArrayList<>();
		TradeItem item = new TradeItem(null, 4, 10, 4);
		items.add(item);
		item = new TradeItem(null, 5, 10, 4);
		items.add(item);
		tradeItemDaoImpl.batchSave(items);
	}

	@Test
	public void testGetTradeItemWithTradeId() {
		Collection<TradeItem> items = tradeItemDaoImpl.getTradeItemWithTradeId(3);
		System.out.println(items);
	}

}
