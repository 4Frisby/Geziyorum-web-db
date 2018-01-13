package com.geziyorum.entity;

import java.sql.Timestamp;

public class TripRequest {
	private Integer id;
	private Integer creatorUserId;
	private Integer katilimciUserId;
	private Integer tripId;
	private String explanation;
	private Timestamp creationTime;
	
	public TripRequest(){
		
		
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCreatorUserId() {
		return creatorUserId;
	}

	public void setCreatorUserId(Integer creatorUserId) {
		this.creatorUserId = creatorUserId;
	}

	public Integer getKatilimciUserId() {
		return katilimciUserId;
	}

	public void setKatilimciUserId(Integer katilimciUserId) {
		this.katilimciUserId = katilimciUserId;
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	
	
	

}
