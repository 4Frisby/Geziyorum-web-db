package com.geziyorum.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.ProfilePageDao;
import com.geziyorum.entity.ProfilePhoto;
import com.geziyorum.entity.User;
import com.geziyorum.exception.ProfilePhotoNotFoundException;
import com.geziyorum.methods.generals.Constraints;

@Service
public class ProfilePageGetProfilePhotoFileService implements GeneralProcess{
	
	@Autowired
	ProfilePageDao profilePageDao; 
	@Autowired
	CommonDao commonDao;
	
	String token;
	MultipartFile file;
	User user;
	ProfilePhoto profilePhoto;
	
	@Override
	public Object startService() throws IOException {
		validateService();
		processService();
		return getFile();
	}
	@Override
	public Object validateService() throws IOException {

		return true;
	}
	@Override
	public Object processService() throws IOException {
		

		
		//Path path = Paths.get(Constraints.MEDIAPATH + profilePhoto.getPhotoName());
		String fullPath = Constraints.FULLPATH + File.separator + "11.jpg";
		String name ="11.jpg";
		
		//String originalFileName = profilePhoto.getPhotoName();
		String contentType = "image/jpg";
	    File file = new File(fullPath);
	    FileInputStream input = new FileInputStream(file);
	    setFile(new MockMultipartFile(name,
	            file.getName(), contentType, IOUtils.toByteArray(input)));	

		return true;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
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
	
	
}
