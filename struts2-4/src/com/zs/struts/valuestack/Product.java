package com.zs.struts.valuestack;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

public class Product {
	private Integer productId;
	private String  productName;
	private String productDesc;
	private double productPrice;
	private List<Person> persons = new ArrayList<>();
	public List<Person> getPersons() {
		return persons;
	}
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDesc=" + productDesc
				+ ", productPrice=" + productPrice + "]";
	}
	public String save(){
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		//System.out.println("---------:"+ActionContext.getContext().getContextMap().get("request"));
		this.persons.add(new Person("BBB", 22));
		this.persons.add(new Person("AAA", 11));
		this.persons.add(new Person("DDD", 44));
		this.persons.add(new Person("CCC", 33));
		Test test = new Test();
		test.setProductDesc("blog");
		test.setProductName("happyte");
		test.setProductPrice(2000);
		valueStack.push(test);
		this.setProductId(1001);
		return "success";
	}
}
