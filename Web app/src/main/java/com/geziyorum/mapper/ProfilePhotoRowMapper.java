package com.geziyorum.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.geziyorum.entity.ProfilePhoto;

public class ProfilePhotoRowMapper implements RowMapper<ProfilePhoto> {

	@Override
	public ProfilePhoto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		ProfilePhoto profilePhoto = new ProfilePhoto();
		profilePhoto.setId(resultSet.getInt("id"));
		profilePhoto.setPhotoName(resultSet.getString("photo_name"));
		profilePhoto.setUserId(resultSet.getInt("user_id"));
		return profilePhoto;
	}


}
