package com.geziyorum.argumentresolver;

import java.sql.Timestamp;

public class CommentNotification {

	private Integer id;
	private String commentYapanUsername;
	private String photoName;
	private String tripKapakUrl;
	private Timestamp commentTime;
	
	public CommentNotification(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getTripKapakUrl() {
		return tripKapakUrl;
	}

	public void setTripKapakUrl(String tripKapakUrl) {
		this.tripKapakUrl = tripKapakUrl;
	}

	public Timestamp getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Timestamp commentTime) {
		this.commentTime = commentTime;
	}

	public String getCommentYapanUsername() {
		return commentYapanUsername;
	}

	public void setCommentYapanUsername(String commentYapanUsername) {
		this.commentYapanUsername = commentYapanUsername;
	}
	
	
}
