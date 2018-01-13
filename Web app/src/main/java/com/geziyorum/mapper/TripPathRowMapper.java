package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.TripPath;

public class TripPathRowMapper implements RowMapper<TripPath> {

	@Override
	public TripPath mapRow(ResultSet rs, int rowNum) throws SQLException {
		TripPath tripPath = new TripPath();
		tripPath.setAltitude(rs.getDouble("altitude"));
		tripPath.setId(rs.getInt("id"));
		tripPath.setLatitude(rs.getDouble("latitude"));
		tripPath.setLongitude(rs.getDouble("longitude"));
		tripPath.setTripId(rs.getInt("trip_id"));
		return tripPath;
	}

}
