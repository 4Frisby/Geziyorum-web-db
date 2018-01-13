package com.geziyorum.argumentresolver;

import com.geziyorum.entity.User;

public class TokenUser {
	private User user;
	private String token;
	
	public TokenUser(){
		
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
