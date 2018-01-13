package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.District;

public class DistrictRowMapper implements RowMapper<District> {

	@Override
	public District mapRow(ResultSet rs, int rowNum) throws SQLException {
		District district = new District();
		district.setCityCode(rs.getInt("city_code"));
		district.setCountryCode(rs.getInt("country_code"));
		district.setId(rs.getInt("id"));
		district.setName(rs.getString("name"));
		return district;
	}

}
