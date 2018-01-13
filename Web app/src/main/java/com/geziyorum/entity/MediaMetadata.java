package com.geziyorum.entity;

public class MediaMetadata {
	
	private String path;
	private String type;
	private Double longitude;
	private Double altitude;
	private Double latitude;
	private String share_option;
	private String note;
	private Integer privacyType;
	


	public MediaMetadata(){
		
	}

	public Integer getPrivacyType() {
		return privacyType;
	}

	public void setPrivacyType(Integer privacyType) {
		this.privacyType = privacyType;
	}	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getShare_option() {
		return share_option;
	}

	public void setShare_option(String share_option) {
		this.share_option = share_option;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
	
	
	

}
