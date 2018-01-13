package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.TripMedia;

public class TripMediaRowMapper implements RowMapper<TripMedia>{

	@Override
	public TripMedia mapRow(ResultSet rs, int rowNum) throws SQLException {
		TripMedia tripMedia = new TripMedia();
		tripMedia.setId(rs.getInt("id"));
		tripMedia.setAltitude(rs.getDouble("altitude"));
		tripMedia.setFileName(rs.getString("file_name"));
		tripMedia.setLatitude(rs.getDouble("altitude"));
		tripMedia.setLongitude(rs.getDouble("longitude"));
		tripMedia.setType(rs.getInt("type"));
		tripMedia.setTripId(rs.getInt("trip_id"));
		tripMedia.setNote(rs.getString("note"));
		tripMedia.setUserId(rs.getInt("user_id"));
		tripMedia.setCreatorUsername(rs.getString("creator_username"));
		return tripMedia;
		
	}

}
