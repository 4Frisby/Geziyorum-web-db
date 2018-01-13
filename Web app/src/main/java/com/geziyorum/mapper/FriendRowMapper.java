package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.Friend;

public class FriendRowMapper implements RowMapper<Friend>{

	@Override
	public Friend mapRow(ResultSet rs, int rowNum) throws SQLException {
		Friend friend = new Friend();
		friend.setId(rs.getInt("id"));
		friend.setUserId1(rs.getInt("user_id1"));
		friend.setUserId2(rs.getInt("user_id2"));
		return friend;
	}

}
