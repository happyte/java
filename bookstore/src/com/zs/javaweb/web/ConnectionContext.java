package com.zs.javaweb.web;

import java.sql.Connection;

//单例子模式 
public class ConnectionContext {
	
	private ConnectionContext() {
	}
	
	private static ConnectionContext connectionContext = new ConnectionContext();
	
	public static ConnectionContext getInstance(){
		return connectionContext;
	}
	//使用ThreadLocal解决Connection多个的问题，ThreadLocal不是用来解决线程共享的问题
	private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
	
	//把Connection绑定到ThreadLocal上
	public void bind(Connection connection){
		threadLocal.set(connection);
	}
	
	//取得绑定到ThreadLocal上的Connection
	public Connection get(){
		return threadLocal.get();
	}
	//清除绑定
	public void remove(){
		threadLocal.remove();
	}
	
}
