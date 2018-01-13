package com.geziyorum.entity;

public class City {

	private Integer id;
	private String name;
	private Integer cityCode;
	private Integer countryCode;
	
	
	public City(){
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getCityCode() {
		return cityCode;
	}


	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}


	public Integer getCountryCode() {
		return countryCode;
	}


	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}
	
	
}
