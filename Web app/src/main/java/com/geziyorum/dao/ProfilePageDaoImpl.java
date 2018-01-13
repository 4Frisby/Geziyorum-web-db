package com.geziyorum.dao;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.geziyorum.entity.PersonalSharing;
import com.geziyorum.entity.ProfilePhoto;
import com.geziyorum.entity.User;
import com.geziyorum.mapper.ProfilePhotoRowMapper;


@Repository("users")
public class ProfilePageDaoImpl implements ProfilePageDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Boolean updateUserGeneralInfo(User user) {
		final String sql = "update users set name=?,surname=?,personal_info=?,website=?, "
				+ "email=?, phone=? where id = ?";
		jdbcTemplate.update(sql,new Object[] 
				{
						user.getName(),
						user.getSurname(),
						user.getPersonalInfo(),
						user.getWebsite(),
						user.getEmail(),
						user.getPhone(),
						user.getId(),	
				});
		return true;
	}
		
	@Override
	public ProfilePhoto getUserProfilePictureByUser(User user) {
		ProfilePhoto profilePhoto= new ProfilePhoto();
		final String sql ="select pp.* from profile_photo pp, users u where pp.user_id = u.id and u.id = ?";
		profilePhoto = this.jdbcTemplate.queryForObject(sql, new ProfilePhotoRowMapper(),user.getId());
		return profilePhoto;
	}


	@Override
	public Boolean updateUserPassword(User user) {
		final String sql = "update users set password=? where id = ?";
		jdbcTemplate.update(sql,new Object[] 
				{
						user.getPassword(),
						user.getId()
				});
		return true;
	}
	
	@Override
	public Boolean saveUserProfilePhoto(User user,String photoName) {
		final String sql = "INSERT INTO profile_photo(photo_name,user_id) VALUES(?,?)";
		jdbcTemplate.update(sql,new Object[] 
				{photoName
				,user.getId()});
		return true;
	}

	@Override
	public Boolean deleteExistingProfilePhoto(User user) {
		final String sql = "DELETE FROM profile_photo where user_id = ?";
		jdbcTemplate.update(sql,new Object[] {user.getId()});
		return true;
	}

	@Override
	public Boolean checkUserHasProfilePicture(User user) {
		final String sql ="select count(*) from profile_photo p, users u where p.user_id = u.id and u.id = ?";
		Integer isExist = this.jdbcTemplate.queryForObject(sql, Integer.class ,user.getId());
		if(isExist == 1)
			return true;
		else 
			return false;
	}

	@Override
	public Boolean createUserProfilePhoto(User user) {
		final String sql = "INSERT INTO profile_photo(user_id) VALUES(?)";
		jdbcTemplate.update(sql,user.getId());
		return true;
	}


}
