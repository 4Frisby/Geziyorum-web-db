package com.geziyorum.service;

import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.argumentresolver.TokenYorumSikayet;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.SikayetDao;
import com.geziyorum.entity.User;
import com.geziyorum.entity.YorumSikayet;

@Service
public class CreateYorumSikayetService implements GeneralProcess{

	@Autowired
	CommonDao commonDao;
	
	@Autowired
	SikayetDao sikayetDao;	
	
	String token;
	User sikayetciUser;
	
	TokenYorumSikayet tokenYorumSikayet;
	YorumSikayet yorumSikayet = new YorumSikayet();
	
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		setToken(tokenYorumSikayet.getToken());
		commonDao.checkSessionExistByToken(token);
		setYorumSikayet(tokenYorumSikayet.getYorumSikayet());
		return true;
	}

	@Override
	public Object processService() throws IOException {
		
		Timestamp time = new Timestamp(System.currentTimeMillis());
		yorumSikayet.setDegerlendirildi(0);
		yorumSikayet.setSikayetZamani(time);
		yorumSikayet.setSikayetciUsername(commonDao.getUserInfoBySessionToken(token).getUsername());
		sikayetDao.createYorumSikayet(yorumSikayet);
		return true;
	}

	public TokenYorumSikayet getTokenYorumSikayet() {
		return tokenYorumSikayet;
	}

	public void setTokenYorumSikayet(TokenYorumSikayet tokenYorumSikayet) {
		this.tokenYorumSikayet = tokenYorumSikayet;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getSikayetciUser() {
		return sikayetciUser;
	}

	public void setSikayetciUser(User sikayetciUser) {
		this.sikayetciUser = sikayetciUser;
	}

	public YorumSikayet getYorumSikayet() {
		return yorumSikayet;
	}

	public void setYorumSikayet(YorumSikayet yorumSikayet) {
		this.yorumSikayet = yorumSikayet;
	}
	
	
	
}
