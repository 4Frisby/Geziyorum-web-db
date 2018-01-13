package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.Likes;

public class LikesRowMapper implements RowMapper<Likes> {

	@Override
	public Likes mapRow(ResultSet rs, int rowNum) throws SQLException {
		Likes like = new Likes();
		like.setId(rs.getInt("id"));
		like.setPersonalSharingId(rs.getInt("personal_sharing_id"));
		like.setUserId(rs.getInt("user_id"));
		return like;
	}

}
