package com.geziyorum.argumentresolver;

import com.geziyorum.entity.GonderiSikayet;

public class TokenGonderiSikayet {
	GonderiSikayet gonderiSikayet;
	String token;
	Integer sikayetEdilenUserId;
	
	public TokenGonderiSikayet(){
		
	}

	public GonderiSikayet getGonderiSikayet() {
		return gonderiSikayet;
	}

	public void setGonderiSikayet(GonderiSikayet gonderiSikayet) {
		this.gonderiSikayet = gonderiSikayet;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getSikayetEdilenUserId() {
		return sikayetEdilenUserId;
	}

	public void setSikayetEdilenUserId(Integer sikayetEdilenUserId) {
		this.sikayetEdilenUserId = sikayetEdilenUserId;
	}
	
	
	
	
}
