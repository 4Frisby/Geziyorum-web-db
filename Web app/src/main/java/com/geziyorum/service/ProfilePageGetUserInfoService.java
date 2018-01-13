package com.geziyorum.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.dao.CommonDao;

@Service
public class ProfilePageGetUserInfoService implements GeneralProcess{

	String token;
	@Autowired
	CommonDao commonDao; 
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public Object startService(){
		validateService();
		return processService();
	}

	@Override
	public Object validateService(){
		commonDao.checkSessionExistByToken(getToken());
		return true;
	}

	@Override
	public Object processService(){
		return commonDao.getUserInfoBySessionToken(getToken());
	}
	
}
