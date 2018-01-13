package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.CommentedYourTripNotification;
import com.geziyorum.entity.MentionedYouNotification;

public class CommentedYourTripNotificationRowMapper implements RowMapper<CommentedYourTripNotification>{

	@Override
	public CommentedYourTripNotification mapRow(ResultSet rs, int rowNum) throws SQLException {
		CommentedYourTripNotification commentedYourTripNotification = new CommentedYourTripNotification(); 
		commentedYourTripNotification.setId(rs.getInt("id"));
		commentedYourTripNotification.setCommentTime(rs.getTimestamp("comment_time"));
		commentedYourTripNotification.setPersonalSharingId(rs.getInt("personal_sharing_id"));
		commentedYourTripNotification.setUserFromId(rs.getInt("user_id_from"));
		commentedYourTripNotification.setUserToId(rs.getInt("user_id_to"));
		commentedYourTripNotification.setCommentId(rs.getInt("comment_id"));
		return commentedYourTripNotification;
	}

}