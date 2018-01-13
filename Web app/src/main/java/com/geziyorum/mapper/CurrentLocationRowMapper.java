package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.CurrentLocation;

public class CurrentLocationRowMapper implements RowMapper<CurrentLocation> {

	@Override
	public CurrentLocation mapRow(ResultSet rs, int rowNum) throws SQLException {
		CurrentLocation cl = new CurrentLocation();
		cl.setAltitude(rs.getDouble("altitude"));
		cl.setId(rs.getInt("id"));
		cl.setLatitude(rs.getDouble("latitude"));
		cl.setLongitude(rs.getDouble("longitude"));
		cl.setTripId(rs.getInt("trip_id"));
		cl.setUsername(rs.getString("username"));
		return cl;
	}

}
