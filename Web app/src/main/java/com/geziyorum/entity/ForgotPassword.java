package com.geziyorum.entity;

public class ForgotPassword {
	
	private Integer id;
	private String username;
	private String randomKey;
	
	public ForgotPassword(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRandomKey() {
		return randomKey;
	}

	public void setRandomKey(String randomKey) {
		this.randomKey = randomKey;
	}
	
	
	

}
