package com.geziyorum.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.ProfilePageDao;
import com.geziyorum.entity.ProfilePhoto;
import com.geziyorum.entity.User;
import com.geziyorum.methods.generals.Constraints;


@Service
public class ProfilePageSavePhotoService implements GeneralProcess{
	
	@Autowired
	ProfilePageDao profilePageDao; 
	
	@Autowired
	CommonDao commonDao;
	
	private MultipartFile file;
	private String token;
	private User user;
	private ProfilePhoto profilePhoto;

	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}
	@Override
	public Object validateService() throws IOException {
		commonDao.checkSessionExistByToken(getToken());
		setUser(commonDao.getUserInfoBySessionToken(getToken()));
		
		if(profilePageDao.checkUserHasProfilePicture(user)){
			setProfilePhoto(profilePageDao.getUserProfilePictureByUser(getUser()));

		}		
		return true;
	}
	@Override
	public Object processService() throws IOException {
		String ppFilePath = Constraints.FULLPATH + File.separator + 
				Constraints.PPFOLDER + File.separator;
		System.out.println("ppFilePath" + ppFilePath);
		String fileName = UUID.randomUUID().toString().replace("-","")+".jpg";
		System.out.println("fileName" + fileName );
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				String nameWithPath = ppFilePath + fileName;
				System.out.println("nameWithPath" + nameWithPath);
				File serverFile = new File(nameWithPath);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				System.out.println("dosyanın kaydedildiği dizin : " + serverFile );
				stream.write(bytes);
				stream.close();
				profilePageDao.deleteExistingProfilePhoto(getUser());
				profilePageDao.saveUserProfilePhoto(user, fileName);
				return "Dosya başarıyla yüklendi =" + fileName;
			} catch (Exception e) {
				return "Yüklemede hata alındı " + fileName + " => " + e.getMessage();
			}
		} else {
			return "Yüklemede hata alındı " + fileName
					+ " dosya boş.";
		}
	}
	

	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
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
	
	


}
