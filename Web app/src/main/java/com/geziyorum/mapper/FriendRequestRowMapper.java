package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.FriendRequest;

public class FriendRequestRowMapper implements RowMapper<FriendRequest> {

	@Override
	public FriendRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		FriendRequest friendRequest = new FriendRequest();
		friendRequest.setId(rs.getInt("id"));
		friendRequest.setFromId(rs.getInt("from_id"));
		friendRequest.setSendTime(rs.getTimestamp("send_time"));
		friendRequest.setToId(rs.getInt("to_id"));
		return friendRequest;
	}

}
