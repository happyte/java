package com.zs.ssm.bean;

import java.util.HashMap;
import java.util.Map;

public class Message {
	//状态码，成功100，失败200
	private int code;
	//提示信息
	private String message;
	//
	private Map<String, Object> extendMap = new HashMap<>();
	//请求成功
	public static Message success(){
		Message message = new Message();
		message.setCode(100);
		message.setMessage("请求成功");
		return message;
	}
	//请求失败
	public static Message fail(){
		Message message = new Message();
		message.setCode(200);
		message.setMessage("请求失败");
		return message;
	}
	public Message add(String key,Object value){
		this.getExtendMap().put(key, value);
		return this;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getExtendMap() {
		return extendMap;
	}
	public void setExtendMap(Map<String, Object> extendMap) {
		this.extendMap = extendMap;
	}
	
}
