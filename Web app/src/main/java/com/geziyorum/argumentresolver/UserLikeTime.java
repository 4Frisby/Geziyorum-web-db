package com.geziyorum.argumentresolver;

import java.sql.Timestamp;

import com.geziyorum.entity.User;

public class UserLikeTime {
	
	User user;
	Timestamp likeTime;
	
	public UserLikeTime(){
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getLikeTime() {
		return likeTime;
	}

	public void setLikeTime(Timestamp likeTime) {
		this.likeTime = likeTime;
	}
	
}
