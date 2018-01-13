package com.geziyorum.argumentresolver;

import java.sql.Timestamp;

import com.geziyorum.entity.User;

public class UserShareTime {
	
	User user;
	Timestamp shareTime;
	
	public UserShareTime(){
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getShareTime() {
		return shareTime;
	}

	public void setShareTime(Timestamp shareTime) {
		this.shareTime = shareTime;
	}
	

}
