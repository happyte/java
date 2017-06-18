package com.zs.struts.app.model;

import java.util.Date;

public class Customer {
	private int age;
	private Date date;
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return date;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		System.out.println("customer:"+this);
		return "Customer [age=" + age + ", date=" + date + "]";
	}
	
	
}
