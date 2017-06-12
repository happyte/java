package com.zs.struts.helloworld;

public class Product {
	private Integer productId;
	private Integer productName;
	private Integer productDesc;
	private Integer productPrice;
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getProductName() {
		return productName;
	}
	public void setProductName(Integer productName) {
		this.productName = productName;
	}
	public Integer getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(Integer productDesc) {
		this.productDesc = productDesc;
	}
	public Integer getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	public Product(Integer productId, Integer productName, Integer productDesc, Integer productPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
	}
	
	public Product() {
		
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDesc=" + productDesc
				+ ", productPrice=" + productPrice + "]";
	}
	
	public String save(){
		System.out.println("save:"+this);
		return "details";
	}
	
}
