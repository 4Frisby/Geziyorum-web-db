package com.geziyorum.entity;

import java.sql.Timestamp;

public class SomeoneSharing {
	private Integer id;
	private Integer ownerId;
	private Integer sharedUserId;
	private Integer personalSharingId;
	private Timestamp sharedTime;
	
	public SomeoneSharing(){
		
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

	public Integer getSharedUserId() {
		return sharedUserId;
	}

	public void setSharedUserId(Integer sharedUserId) {
		this.sharedUserId = sharedUserId;
	}

	public Integer getPersonalSharingId() {
		return personalSharingId;
	}

	public void setPersonalSharingId(Integer personalSharingId) {
		this.personalSharingId = personalSharingId;
	}

	public Timestamp getSharedTime() {
		return sharedTime;
	}

	public void setSharedTime(Timestamp sharedTime) {
		this.sharedTime = sharedTime;
	}
	
	
	

}
