package com.geziyorum.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.ProfilePageDao;
import com.geziyorum.entity.User;
import com.geziyorum.exception.SessionTokenNotFoundException;

@Service
public class ProfilePageSaveUserInfoService implements GeneralProcess {
	
	@Autowired
	CommonDao commonDao;
	@Autowired
	ProfilePageDao profilePageDao; 
	
	String token;
	User user;

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

	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		if(!commonDao.checkSessionExistByToken(getToken()))
			throw new SessionTokenNotFoundException("Session token bulunamadı.");
		return true;
	}

	@Override
	public Object processService() throws IOException {
		User dbUser = commonDao.getUserInfoBySessionToken(getToken());
		dbUser.setName(getUser().getName());
		dbUser.setSurname(getUser().getSurname());
		dbUser.setPersonalInfo(getUser().getPersonalInfo());
		dbUser.setWebsite(getUser().getWebsite());
		dbUser.setEmail(getUser().getEmail());
		dbUser.setPhone(getUser().getPhone());
		profilePageDao.updateUserGeneralInfo(dbUser);
		return true;
	}
	
	
}
