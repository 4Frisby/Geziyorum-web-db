package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.PersonalSharing;

public class PersonalSharingRowMapper implements RowMapper<PersonalSharing> {

	@Override
	public PersonalSharing mapRow(ResultSet rs, int rowNum) throws SQLException {
		PersonalSharing personalSharing = new PersonalSharing();
		personalSharing.setId(rs.getInt("id"));
		personalSharing.setOwnerId(rs.getInt("owner_id"));
		personalSharing.setSharedTime(rs.getTimestamp("shared_time"));
		personalSharing.setTrip_id(rs.getInt("trip_id"));
		personalSharing.setHide(rs.getInt("hide"));
		return personalSharing;
	}

}
