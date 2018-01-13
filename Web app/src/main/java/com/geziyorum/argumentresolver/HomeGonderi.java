package com.geziyorum.argumentresolver;

import java.sql.Timestamp;

public class HomeGonderi {

	private String olusturanUsername;
	private Integer olusturanUserId;
	private String paylasanUsername;
	private Integer gonderiId;
	private String folderName;
	private Timestamp sharedTime;
	private String explanation;
	private String location;
	private Timestamp startTime;
	private Timestamp endTime;
	private Integer tripId;
	
	public HomeGonderi(){
		
	}

	public String getOlusturanUsername() {
		return olusturanUsername;
	}

	public void setOlusturanUsername(String olusturanUsername) {
		this.olusturanUsername = olusturanUsername;
	}

	public String getPaylasanUsername() {
		return paylasanUsername;
	}

	public void setPaylasanUsername(String paylasanUsername) {
		this.paylasanUsername = paylasanUsername;
	}

	public Integer getGonderiId() {
		return gonderiId;
	}

	public void setGonderiId(Integer gonderiId) {
		this.gonderiId = gonderiId;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public Timestamp getSharedTime() {
		return sharedTime;
	}

	public void setSharedTime(Timestamp sharedTime) {
		this.sharedTime = sharedTime;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	public Integer getOlusturanUserId() {
		return olusturanUserId;
	}

	public void setOlusturanUserId(Integer olusturanUserId) {
		this.olusturanUserId = olusturanUserId;
	}
	
	
	
	
}
