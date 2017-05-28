package com.zs.javaweb;

import java.util.List;

public class User {
	//用户名
	private String username;
	//用户名的权限种类，因为不仅一个权限
	private List<Authority> authorities;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	public User(String username, List<Authority> authorities) {
		super();
		this.username = username;
		this.authorities = authorities;
	}
	public User() {

	}
	
}
