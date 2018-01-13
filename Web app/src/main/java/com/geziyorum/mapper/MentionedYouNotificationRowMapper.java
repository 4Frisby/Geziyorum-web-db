package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.MentionedYouNotification;

public class MentionedYouNotificationRowMapper implements RowMapper<MentionedYouNotification>{

	@Override
	public MentionedYouNotification mapRow(ResultSet rs, int rowNum) throws SQLException {
		MentionedYouNotification mentionedYou = new MentionedYouNotification(); 
		mentionedYou.setId(rs.getInt("id"));
		mentionedYou.setMentionTime(rs.getTimestamp("mentioned_time"));
		mentionedYou.setPersonalSharingId(rs.getInt("personal_sharing_id"));
		mentionedYou.setUserFromId(rs.getInt("user_id_from"));
		mentionedYou.setUserToId(rs.getInt("user_id_to"));
		mentionedYou.setCommentId(rs.getInt("comment_id"));
		return mentionedYou;
	}

}
