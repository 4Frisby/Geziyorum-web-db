package com.geziyorum.entity;

import java.sql.Timestamp;

public class Comment {
	private Integer id;
	private Integer userId;
	private Integer personalSharingId;
	private String content;
	private Timestamp sendTime;
	private Integer hide;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getHide() {
		return hide;
	}
	public void setHide(Integer hide) {
		this.hide = hide;
	}
	
	
}
