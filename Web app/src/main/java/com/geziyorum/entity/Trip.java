package com.geziyorum.entity;

import java.sql.Timestamp;

public class Trip {
	private Integer tripId;
	private Integer creatorUserId;
	private String explanation;
	private String folderName;
	private String location;
	private Timestamp starTime;
	private Timestamp endTime;
	private Timestamp createdTime;
	private String tripType;
	private Integer isUpdated;
	
	
	public Trip(){
		
	}
	
	
	
	public Integer getIsUpdated() {
		return isUpdated;
	}



	public void setIsUpdated(Integer isUpdated) {
		this.isUpdated = isUpdated;
	}



	public Integer getTripId() {
		return tripId;
	}
	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}
	public Integer getCreatorUserId() {
		return creatorUserId;
	}
	public void setCreatorUserId(Integer creatorUserId) {
		this.creatorUserId = creatorUserId;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public Timestamp getStarTime() {
		return starTime;
	}
	public void setStarTime(Timestamp starTime) {
		this.starTime = starTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}
	
	
	
	
	
	
}
