package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.TripRequest;

public class TripRequestMapper implements RowMapper<TripRequest>{

	@Override
	public TripRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		TripRequest tripRequest = new TripRequest();
		tripRequest.setCreatorUserId(rs.getInt("creator_user_id"));
		tripRequest.setExplanation(rs.getString("explanation"));
		tripRequest.setId(rs.getInt("trip_request_id"));
		tripRequest.setKatilimciUserId(rs.getInt("katilimci_user_id"));
		tripRequest.setTripId(rs.getInt("trip_id"));
		tripRequest.setCreationTime(rs.getTimestamp("creation_time"));

		return tripRequest;
	}

	
	
}
