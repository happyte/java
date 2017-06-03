package com.zs.gof.six;

public class Nationality implements Cloneable {
	private String nation;

	public String getNation() {
		return nation;
	}
	//设置国籍属性
	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
