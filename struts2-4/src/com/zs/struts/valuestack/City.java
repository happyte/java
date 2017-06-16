package com.zs.struts.valuestack;

public class City {
	private Integer cityId;
	private String cityValue;
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityValue() {
		return cityValue;
	}
	public void setCityValue(String cityValue) {
		this.cityValue = cityValue;
	}
	public City(Integer cityId, String cityValue) {
		super();
		this.cityId = cityId;
		this.cityValue = cityValue;
	}
	public City() {

	}
	
}
