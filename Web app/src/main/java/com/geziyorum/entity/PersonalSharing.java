package com.geziyorum.entity;

import java.sql.Timestamp;

public class PersonalSharing {
	private Integer id;
	private Integer ownerId;
	private Integer trip_id;
	private Timestamp sharedTime;
	private Integer hide;
	
	public PersonalSharing(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(Integer trip_id) {
		this.trip_id = trip_id;
	}

	public Timestamp getSharedTime() {
		return sharedTime;
	}

	public void setSharedTime(Timestamp sharedTime) {
		this.sharedTime = sharedTime;
	}

	public Integer getHide() {
		return hide;
	}

	public void setHide(Integer hide) {
		this.hide = hide;
	}

	
	
	
}
