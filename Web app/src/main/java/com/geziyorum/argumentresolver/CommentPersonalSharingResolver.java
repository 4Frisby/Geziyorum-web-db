package com.geziyorum.argumentresolver;

import java.util.ArrayList;

public class CommentPersonalSharingResolver {
	private Integer personalSharingId;
	private String content;
	private String token;
	private Long sendTime;
	private ArrayList<String> bahsedilenlerUserName;
	
	public CommentPersonalSharingResolver(){
		
	}


	public Integer getPersonalSharingId() {
		return personalSharingId;
	}

	public void setPersonalSharingId(Integer personalSharingId) {
		this.personalSharingId = personalSharingId;
	}



	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getSendTime() {
		return sendTime;
	}

	public void setSendTime(Long sendTime) {
		this.sendTime = sendTime;
	}


	public ArrayList<String> getBahsedilenlerUserName() {
		return bahsedilenlerUserName;
	}


	public void setBahsedilenlerUserName(ArrayList<String> bahsedilenlerUserName) {
		this.bahsedilenlerUserName = bahsedilenlerUserName;
	}

	
	
	
	
	
}
