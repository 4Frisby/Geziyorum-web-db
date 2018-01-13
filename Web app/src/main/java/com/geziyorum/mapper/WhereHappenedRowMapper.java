package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.WhereHappened;

public class WhereHappenedRowMapper implements RowMapper<WhereHappened> {

	@Override
	public WhereHappened mapRow(ResultSet rs, int rowNum) throws SQLException {
		WhereHappened whereHappened = new WhereHappened();
		whereHappened.setCityId(rs.getInt("city_id"));
		whereHappened.setCountryId(rs.getInt("country_id"));
		whereHappened.setDistrictId(rs.getInt("district_id"));
		return whereHappened;
	}

}
