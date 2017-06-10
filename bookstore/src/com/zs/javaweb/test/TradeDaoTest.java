package com.zs.javaweb.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Collection;

import org.junit.Test;

import com.zs.javaweb.domain.Trade;
import com.zs.javaweb.impl.TradeDaoImpl;

public class TradeDaoTest {
	private TradeDaoImpl tradeDaoImpl = new TradeDaoImpl();

	//单元测试成功
	@Test
	public void testInsertTrade() {
		Trade trade = new Trade(null, 1, new Date(new java.util.Date().getTime()));
		tradeDaoImpl.insert(trade);
	}

	//单元测试通过
	@Test
	public void testGetTradeWithUserId() {
		Collection<Trade> trades = tradeDaoImpl.getTradeWithUserId(1);
		System.out.println(trades);
	}

}
