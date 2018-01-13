package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.SomeoneSharing;

public class SomeoneSharingRowMapper implements RowMapper<SomeoneSharing> {

	@Override
	public SomeoneSharing mapRow(ResultSet rs, int rowNum) throws SQLException {
		SomeoneSharing someoneSharing = new SomeoneSharing();
		someoneSharing.setId(rs.getInt("id"));
		someoneSharing.setOwnerId(rs.getInt("owner_id"));
		someoneSharing.setPersonalSharingId(rs.getInt("personal_sharing_id"));
		someoneSharing.setSharedTime(rs.getTimestamp("shared_time"));
		someoneSharing.setSharedUserId(rs.getInt("shared_user_id"));
		return someoneSharing;
	}

}
