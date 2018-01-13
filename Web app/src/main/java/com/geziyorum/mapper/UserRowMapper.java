package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.geziyorum.entity.User;

public class UserRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		User user = new User();
		user.setId(resultSet.getInt("id"));
		user.setName(resultSet.getString("name"));
		user.setSurname(resultSet.getString("surname"));
		user.setEmail(resultSet.getString("email"));
		user.setPassword(resultSet.getString("password"));
		user.setCreatedAt(resultSet.getDate("created_at"));
		user.setUsername(resultSet.getString("username"));
		user.setPersonalInfo(resultSet.getString("personal_info"));
		user.setWebsite(resultSet.getString("website"));
		user.setCountryId(resultSet.getInt("location_country_id"));
		user.setCityId(resultSet.getInt("location_city_id"));
		user.setIsVerified(resultSet.getInt("is_verified"));
		user.setPhone(resultSet.getString("phone"));
		user.setHide(resultSet.getInt("hide"));
		
		return user;
	}

}
