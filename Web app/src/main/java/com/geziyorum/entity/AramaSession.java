package com.geziyorum.entity;

public class AramaSession {
	
	private String token;
	private Integer arananTip;
	private String aramaMetni;
	
	public AramaSession(){
		
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getArananTip() {
		return arananTip;
	}

	public void setArananTip(Integer arananTip) {
		this.arananTip = arananTip;
	}

	public String getAramaMetni() {
		return aramaMetni;
	}

	public void setAramaMetni(String aramaMetni) {
		this.aramaMetni = aramaMetni;
	}
	
	

}
