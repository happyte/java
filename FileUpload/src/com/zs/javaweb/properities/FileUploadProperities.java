package com.zs.javaweb.properities;

import java.util.HashMap;
import java.util.Map;

public class FileUploadProperities {
	private Map<String, String> properities = new HashMap<>();
	
	private FileUploadProperities(){}
	//单例模式
	private static FileUploadProperities instance = new FileUploadProperities();
	
	public static FileUploadProperities getInstance(){
		return instance;
	}
	
	public void addProperty(String propertyName, String peropertyVaule){
		properities.put(propertyName, peropertyVaule);
	}
	
	public String getProperty(String propertyName){
		return properities.get(propertyName);
	}
	
}
