package com.geziyorum.entity;

import java.sql.Timestamp;

public class MentionedYouNotification {
	private Integer id;
	private Integer userFromId;
	private Integer userToId;
	private Integer personalSharingId;
	private Timestamp mentionTime;
	private Integer commentId;
	
	public MentionedYouNotification(){
		
	}

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

	public Timestamp getMentionTime() {
		return mentionTime;
	}

	public void setMentionTime(Timestamp mentionTime) {
		this.mentionTime = mentionTime;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	
	
	
	
	

}
