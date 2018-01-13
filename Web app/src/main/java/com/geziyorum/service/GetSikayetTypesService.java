package com.geziyorum.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.SikayetDao;

@Service
public class GetSikayetTypesService implements GeneralProcess{

	@Autowired
	CommonDao commonDao;
	
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
		commonDao.checkSessionExistByToken(token);
		return true;
	}

	@Override
	public Object processService() throws IOException {
		return sikayetDao.getSikayetList();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
	
	
}
