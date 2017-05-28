package com.zs.javaweb;

public class Authority {
	//权限页面的名字
	private String dispalyName;
	//权限页面的url
	private String url;
	public String getDispalyName() {
		return dispalyName;
	}
	public void setDispalyName(String dispalyName) {
		this.dispalyName = dispalyName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Authority(String dispalyName, String url) {
		this.dispalyName = dispalyName;
		this.url = url;
	}
	public Authority() {
		
	}
	
}
