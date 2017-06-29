package com.zs.spring.beans;

public class Car {
	private String name;
	private double price;
	private int speed;

	public Car(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Car(String name, int speed) {
		super();
		this.name = name;
		this.speed = speed;
	}

	@Override
	public String toString() {
		return "Car [name=" + name + ", price=" + price + ", speed=" + speed + "]";
	}
	
}
