package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.Country;

public class CountryRowMapper implements RowMapper<Country>{

	@Override
	public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
		Country country = new Country();
		country.setId(rs.getInt("id"));
		country.setCountryCode(rs.getInt("country_code"));
		country.setName(rs.getString("name"));
		return country;
	}

}
