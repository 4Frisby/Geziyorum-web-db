package com.geziyorum.entity;

public class AdminSession {

	private String sessionId;
	private Integer adminId;
	private java.sql.Timestamp createdTime;
	private java.sql.Timestamp lastlyActiveTime;	
	private String sessionToken;
	
	public AdminSession(){
		
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public java.sql.Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(java.sql.Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public java.sql.Timestamp getLastlyActiveTime() {
		return lastlyActiveTime;
	}

	public void setLastlyActiveTime(java.sql.Timestamp lastlyActiveTime) {
		this.lastlyActiveTime = lastlyActiveTime;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	

	
}
