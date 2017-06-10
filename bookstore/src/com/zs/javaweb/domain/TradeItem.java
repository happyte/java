package com.zs.javaweb.domain;

public class TradeItem {
	private Integer itemId;
	private Integer bookId;
	private int quantity;
	private Integer tradeId;
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Integer getTradeId() {
		return tradeId;
	}
	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}
	public TradeItem(Integer itemId, Integer bookId, int quantity, Integer tradeId) {
		super();
		this.itemId = itemId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.tradeId = tradeId;
	}
	public TradeItem() {
	
	}
	@Override
	public String toString() {
		return "TradeItem [itemId=" + itemId + ", bookId=" + 
				bookId + ", quantity=" + quantity + ", tradeId=" 
				+ tradeId + "]";
	}
	
	
}
