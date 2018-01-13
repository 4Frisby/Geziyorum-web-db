package com.geziyorum.argumentresolver;

import java.sql.Timestamp;

public class FriendRequestsPojo {
	
	String ppName;
	String name;
	String surname;
	String username;
	Timestamp sendTime;
	Integer FriendRequestId;
	
	public FriendRequestsPojo(){
		
	}

	public String getPpName() {
		return ppName;
	}

	public void setPpName(String ppName) {
		this.ppName = ppName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getFriendRequestId() {
		return FriendRequestId;
	}

	public void setFriendRequestId(Integer friendRequestId) {
		FriendRequestId = friendRequestId;
	}

	public Timestamp getSendTime() {
		return sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	
	
	
	
	

}
