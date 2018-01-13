package com.geziyorum.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.HomeDao;
import com.geziyorum.entity.User;

@Service
public class GetFriendPaylasimListService implements GeneralProcess{

	@Autowired
	HomeDao homeDao;
	
	@Autowired
	CommonDao commonDao;	
	
	String token;
	User user;
	
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		commonDao.checkSessionExistByToken(token);
		setUser(commonDao.getUserInfoBySessionToken(token));
		return true;
	}

	@Override
	public Object processService() throws IOException {
		return homeDao.getGonderiListOfFriends(user.getId());
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
	
}
