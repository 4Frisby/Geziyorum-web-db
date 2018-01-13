package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.City;

public class CityRowMapper implements RowMapper<City> {

	@Override
	public City mapRow(ResultSet rs, int rowNum) throws SQLException {
		City city = new City();
		city.setCityCode(rs.getInt("city_code"));
		city.setCountryCode(rs.getInt("country_code"));
		city.setId(rs.getInt("id"));
		city.setName(rs.getString("name"));
		return city;
	}

}
