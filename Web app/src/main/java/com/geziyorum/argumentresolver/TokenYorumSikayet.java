package com.geziyorum.argumentresolver;

import com.geziyorum.entity.YorumSikayet;

public class TokenYorumSikayet {

	String token;
	YorumSikayet yorumSikayet;
	
	public TokenYorumSikayet(){
		
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public YorumSikayet getYorumSikayet() {
		return yorumSikayet;
	}

	public void setYorumSikayet(YorumSikayet yorumSikayet) {
		this.yorumSikayet = yorumSikayet;
	}
	
	
}
