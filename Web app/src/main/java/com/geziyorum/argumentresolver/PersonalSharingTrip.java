package com.geziyorum.argumentresolver;

import java.sql.Timestamp;

public class PersonalSharingTrip {
	
	private Integer personalSharingId;
	private Integer tripId;
	private Integer ownerId;
	private Timestamp sharedTime;
	private String whoCanSee;
	private String folderName;
	
	
	public PersonalSharingTrip(){
		
	}


	public Integer getPersonalSharingId() {
		return personalSharingId;
	}


	public void setPersonalSharingId(Integer personalSharingId) {
		this.personalSharingId = personalSharingId;
	}


	public Integer getTripId() {
		return tripId;
	}


	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}


	public Integer getOwnerId() {
		return ownerId;
	}


	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}





	public Timestamp getSharedTime() {
		return sharedTime;
	}


	public void setSharedTime(Timestamp sharedTime) {
		this.sharedTime = sharedTime;
	}


	public String getWhoCanSee() {
		return whoCanSee;
	}


	public void setWhoCanSee(String whoCanSee) {
		this.whoCanSee = whoCanSee;
	}


	public String getFolderName() {
		return folderName;
	}


	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	
	

	
	
	
	
	
}
