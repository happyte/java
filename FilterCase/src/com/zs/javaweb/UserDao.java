package com.zs.javaweb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserDao {
	//模拟操作底层数据库
	
	//1.用户映射表
	private static Map<String, User> users = null;
	//2.存储所有权限列表
	private static List<Authority> authorities = null;
	
	//只初始一次
	static{
		authorities = new ArrayList<>();
		//添加所有的权限
		authorities.add(new Authority("Article-1","/article-1.jsp"));
		authorities.add(new Authority("Article-2","/article-2.jsp"));
		authorities.add(new Authority("Article-3","/article-3.jsp"));
		authorities.add(new Authority("Article-4","/article-4.jsp"));
		//初始化用户
		users = new HashMap<String, User>();
		User user1 = new User("AAA", authorities.subList(0, 2));
		User user2 = new User("BBB", authorities.subList(2, 4));
		users.put("AAA", user1);
		users.put("BBB", user2);
	}

	//1.根据用户名获得User对象,Map的get key方法
	public User getUser(String username){
		return users.get(username);
	}
	
	//2.获取初始化的所有权限
	public List<Authority> getAuthorities(){
		return authorities;
	}
	
	//3.更新用户权限
	public void update(String username, List<Authority> authorities){
		getUser(username).setAuthorities(authorities);
	}
}
