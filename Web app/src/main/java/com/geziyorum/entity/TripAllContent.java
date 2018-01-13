package com.geziyorum.entity;

import java.util.ArrayList;

public class TripAllContent {
	Trip trip;
	ArrayList<TripMedia> tripMedia;
	ArrayList<TripPath> tripPath;
	
	public TripAllContent(){
		
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public ArrayList<TripMedia> getTripMedia() {
		return tripMedia;
	}

	public void setTripMedia(ArrayList<TripMedia> tripMedia) {
		this.tripMedia = tripMedia;
	}

	public ArrayList<TripPath> getTripPath() {
		return tripPath;
	}

	public void setTripPath(ArrayList<TripPath> tripPath) {
		this.tripPath = tripPath;
	}


	
	

}
