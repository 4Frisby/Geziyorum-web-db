package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.Trip;

public class TripRowMapper implements RowMapper<Trip> {

	@Override
	public Trip mapRow(ResultSet rs, int rowNum) throws SQLException {
		Trip trip = new Trip();
		trip.setCreatorUserId(rs.getInt("creator_user_id"));
		trip.setTripId(rs.getInt("id"));
		trip.setStarTime(rs.getTimestamp("start_time"));
		trip.setEndTime(rs.getTimestamp("end_time"));
		trip.setCreatedTime(rs.getTimestamp("created_time"));
		trip.setExplanation(rs.getString("explanation"));
		trip.setLocation(rs.getString("location"));
		trip.setFolderName(rs.getString("folder_name"));
		trip.setTripType(rs.getString("trip_type"));
		trip.setIsUpdated(rs.getInt("is_updated"));
		return trip;
	}

}
