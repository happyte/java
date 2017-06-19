package com.zs.struts.validation;

import com.opensymphony.xwork2.ActionSupport;

public class TestValidationAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private int age;
	private String password;
	private String password2;
	private Integer count;
	private String idCard;
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getCount() {
		return count;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getPassword() {
		return password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	
	@Override
	public String execute() throws Exception {
		System.out.println("----age:"+age);
		return SUCCESS;
	}
}
