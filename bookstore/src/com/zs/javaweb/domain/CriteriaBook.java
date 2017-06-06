package com.zs.javaweb.domain;

//封装查询条件的CriteriaBook类
public class CriteriaBook {
	//价格区间的最小值
	private int minPrice = 0;
	//价格区间的最大值为Intager.MAX_VALUE
	private int maxPrice = Integer.MAX_VALUE;
	//当前页码
	private int pageNo;
	
	public CriteriaBook(int minPrice, int maxPrice, int pageNo) {
		super();
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.pageNo = pageNo;
	}
	
	public CriteriaBook() {
		
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
	
}
