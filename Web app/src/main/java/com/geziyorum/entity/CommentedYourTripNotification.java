package com.geziyorum.entity;

import java.sql.Timestamp;

public class CommentedYourTripNotification {
	
	private Integer id;
	private Integer userFromId;
	private Integer userToId;
	private Integer personalSharingId;
	private Timestamp commentTime;
	private Integer commentId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserFromId() {
		return userFromId;
	}
	public void setUserFromId(Integer userFromId) {
		this.userFromId = userFromId;
	}
	public Integer getUserToId() {
		return userToId;
	}
	public void setUserToId(Integer userToId) {
		this.userToId = userToId;
	}
	public Integer getPersonalSharingId() {
		return personalSharingId;
	}
	public void setPersonalSharingId(Integer personalSharingId) {
		this.personalSharingId = personalSharingId;
	}
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Timestamp getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Timestamp commentTime) {
		this.commentTime = commentTime;
	}
	
	
	
	

}
