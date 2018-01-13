package com.geziyorum.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.dao.AdminDao;
import com.geziyorum.dao.SikayetDao;

@Service
public class GetProfilSikayetlerService implements GeneralProcess{

	@Autowired
	AdminDao adminDao;
	
	@Autowired
	SikayetDao sikayetDao;
	
	String token;
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		adminDao.checkSessionExistByToken(token);
		return true;
	}

	@Override
	public Object processService() throws IOException {
		return sikayetDao.getProfilSikayetler();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	

}
