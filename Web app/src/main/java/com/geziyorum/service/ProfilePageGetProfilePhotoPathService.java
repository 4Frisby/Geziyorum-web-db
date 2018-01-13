package com.geziyorum.service;


import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.ProfilePageDao;
import com.geziyorum.entity.ProfilePhoto;
import com.geziyorum.entity.User;
import com.geziyorum.exception.ProfilePhotoNotFoundException;
import com.geziyorum.methods.generals.Constraints;


@Service
public class ProfilePageGetProfilePhotoPathService implements GeneralProcess {
	@Autowired
	ProfilePageDao profilePageDao; 
	@Autowired
	CommonDao commonDao;
	
	String username;
	String token;
	User user;
	ProfilePhoto profilePhoto;
	
	@Override
	public Object startService(){
		validateService();
		return processService();
	}
	@Override
	public Object validateService(){
		commonDao.checkSessionExistByToken(getToken());
		setUser(commonDao.getUserByUsername(getUsername()));
		setProfilePhoto(profilePageDao.getUserProfilePictureByUser(getUser()));

		return true;
	}
	@Override
	public Object processService(){	
		String fullPath = Constraints.RELATIVEPATH + File.separator + 
		Constraints.PPFOLDER + File.separator +   profilePhoto.getPhotoName();
		return fullPath;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ProfilePhoto getProfilePhoto() {
		return profilePhoto;
	}
	public void setProfilePhoto(ProfilePhoto profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
