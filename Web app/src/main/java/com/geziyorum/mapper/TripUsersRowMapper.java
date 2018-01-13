package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.TripUsers;

public class TripUsersRowMapper implements RowMapper<TripUsers> {

	@Override
	public TripUsers mapRow(ResultSet rs, int rowNum) throws SQLException {
		TripUsers tripUsers = new TripUsers();
		tripUsers.setId(rs.getInt("id"));
		tripUsers.setTripId(rs.getInt("trip_id"));
		tripUsers.setUserId(rs.getInt("user_id"));
		return tripUsers;
	}

}
