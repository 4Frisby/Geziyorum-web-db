package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.Comment;

public class CommentRowMapper implements RowMapper<Comment> {

	@Override
	public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setContent(rs.getString("content"));
		comment.setPersonalSharingId(rs.getInt("personal_sharing_id"));
		comment.setSendTime(rs.getTimestamp("send_time"));
		comment.setUserId(rs.getInt("user_id"));
		comment.setHide(rs.getInt("hide"));
		
		return comment;
	}

}
