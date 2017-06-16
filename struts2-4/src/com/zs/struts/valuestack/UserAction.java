package com.zs.struts.valuestack;

import java.util.Arrays;
import java.util.List;

public class UserAction {
	private Integer userId;
	private String username;
	private String password;
	private String desc;
	private boolean married;
	private String gender;
	private List<String> cities;
	private String age;
	
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		System.out.println("gender:"+gender);
		this.gender = gender;
	}
	public List<String> getCities() {
		return cities;
	}
	public void setCities(List<String> cities) {
		System.out.println("cities:"+cities);
		this.cities = cities;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		System.out.println("age:"+age);
		this.age = age;
	}
	public boolean isMarried() {
		return married;
	}
	public void setMarried(boolean married) {
		System.out.println("married:"+married);
		this.married = married;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		System.out.println("username:"+username);
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		System.out.println("password:"+password);
		this.password = password;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		System.out.println("desc:"+desc);
		this.desc = desc;
	}
	
	
	@Override
	public String toString() {
		return "UserAction [userId=" + userId + ", username=" + username + ", password=" + password + ", desc=" + desc
				+ ", married=" + married + ", gender=" + gender + ", cities=" + cities + ", age=" + age + "]";
	}
	public String save(){
		return "input";
	}
}
