package com.geziyorum.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.dao.CommonDao;
import com.geziyorum.entity.User;

@Service
public class GetCommentNotificationsCountService implements GeneralProcess{

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
		return commonDao.getCommentNotificationCountByUserId(user.getId());
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
