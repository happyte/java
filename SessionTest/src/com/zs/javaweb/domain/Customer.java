package com.zs.javaweb.domain;

public class Customer {
	String name;
	String address;
	String cardType;
	String cardNumber;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Customer(String name, String address, String cardType, String cardNumber) {
		super();
		this.name = name;
		this.address = address;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
	}

}
