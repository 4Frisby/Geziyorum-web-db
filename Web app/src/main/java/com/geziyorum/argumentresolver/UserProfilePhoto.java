package com.geziyorum.argumentresolver;

import com.geziyorum.entity.ProfilePhoto;
import com.geziyorum.entity.User;

public class UserProfilePhoto {
	
	User user;
	ProfilePhoto pp;
	
	
	public UserProfilePhoto(){
		
	}

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public ProfilePhoto getPp() {
		return pp;
	}


	public void setPp(ProfilePhoto pp) {
		this.pp = pp;
	}
	

}
