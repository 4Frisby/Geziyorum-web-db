package com.geziyorum.argumentresolver;

import com.geziyorum.entity.TripRequest;

public class TokenTripRequest {

	private String token;
	private TripRequest tripRequest;
	
	public TokenTripRequest(){
		
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TripRequest getTripRequest() {
		return tripRequest;
	}

	public void setTripRequest(TripRequest tripRequest) {
		this.tripRequest = tripRequest;
	}
	
	
	
	
}
