package com.zs.javaweb.web;

import java.util.List;

public class Page<T> {
	private int pageNo;    //当前页码
	private List<T> list;  //本页要显示的数据
	private int pageSize = 3;  //当前页要显示的记录数
	private long totalItemNumber; //总记录数，用于计算总页数
	
	//初始化当前页码
	public Page(int pageNo) {
		super();
		this.pageNo = pageNo;
	}

	//需要做非法判断,比如pageNo < 0,大于totalPageNumber
	public int getPageNo() {
		if (pageNo < 0)
			pageNo = 1;   //从第一页开始
		if (pageNo > getTotalPageNumber())
			pageNo = getTotalPageNumber();
		return pageNo;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public List<T> getList() {
		return list;
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}
	
	//通过总记录数计算出总页数
	public int getTotalPageNumber(){
		int totalPageNumber = (int)totalItemNumber/pageSize;
		if(totalItemNumber%pageSize != 0)
			totalPageNumber ++;
		return totalPageNumber;
	}
	
	//设置总记录数
	public void setTotalItemNumber(long totalItemNumber) {
		this.totalItemNumber = totalItemNumber;
	}
	
	public boolean isHasNext(){
		//当前页码 < 总页数
		if (getPageNo() < getTotalPageNumber())
			return true;
		else
			return false;
	}
	
	public boolean isHasPrev(){
		//当前页码 < 总页数
		if (getPageNo() > 1)
			return true;
		else 
			return false;
	}
	
	//返回前一页的页码
	public int getPrePage(){
		if(isHasPrev())
			return getPageNo() - 1;
		else
			return 1;
	}
	
	//返回下一页的页码
	public int getNextPage(){
		if(isHasNext())
			return getPageNo() + 1;
		else
			return getPageNo();
	}
	

}
