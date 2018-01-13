package com.geziyorum.entity;

import java.util.ArrayList;

public class TripAllContentWithWhoShared {
	Trip trip;
	ArrayList<TripMedia> tripMedia;
	ArrayList<TripPath> tripPath;
	String url;
	String mediaFolderName;
	String fileSeperator;
	SomeoneSharing SomeoneSharing;
	PersonalSharing personalSharing;
	
	
	public TripAllContentWithWhoShared(){
		
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


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getMediaFolderName() {
		return mediaFolderName;
	}


	public void setMediaFolderName(String mediaFolderName) {
		this.mediaFolderName = mediaFolderName;
	}


	public String getFileSeperator() {
		return fileSeperator;
	}


	public void setFileSeperator(String fileSeperator) {
		this.fileSeperator = fileSeperator;
	}


	public SomeoneSharing getSomeoneSharing() {
		return SomeoneSharing;
	}


	public void setSomeoneSharing(SomeoneSharing someoneSharing) {
		SomeoneSharing = someoneSharing;
	}


	public PersonalSharing getPersonalSharing() {
		return personalSharing;
	}


	public void setPersonalSharing(PersonalSharing personalSharing) {
		this.personalSharing = personalSharing;
	}
	
	
	
	
	
	
}
