package com.geziyorum.argumentresolver;

import com.geziyorum.entity.CurrentLocation;

public class CurrentLocationToken {

	CurrentLocation currentLocation;
	String token;
	
	public CurrentLocationToken(){
		
	}

	public CurrentLocation getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(CurrentLocation currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
