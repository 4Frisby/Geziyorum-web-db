package com.geziyorum.service;

import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.argumentresolver.TokenGonderiSikayet;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.SikayetDao;
import com.geziyorum.entity.GonderiSikayet;
import com.geziyorum.entity.User;


@Service
public class CreateGonderiSikayetService implements GeneralProcess{

	@Autowired
	CommonDao commonDao;
	
	@Autowired
	SikayetDao sikayetDao;	
	
	String token;
	User sikayetciUser;
	
	Integer sikayetEdilenUserId;
	TokenGonderiSikayet tokenGonderiSikayet;
	GonderiSikayet gonderiSikayet = new GonderiSikayet();
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		setToken(tokenGonderiSikayet.getToken());
		setGonderiSikayet(tokenGonderiSikayet.getGonderiSikayet());
		commonDao.checkSessionExistByToken(token);	
		return true;
	}

	@Override
	public Object processService() throws IOException {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		gonderiSikayet.setSikayetciUsername(commonDao.getUserInfoBySessionToken(token).getUsername());
		gonderiSikayet.setSikayetEdilenUsername(commonDao.getUserById(sikayetEdilenUserId).getUsername());
		gonderiSikayet.setSikayetZamani(time);
		gonderiSikayet.setDegerlendirildi(0);
		sikayetDao.createGonderiSikayet(gonderiSikayet);
		return true;
	}

	public TokenGonderiSikayet getTokenGonderiSikayet() {
		return tokenGonderiSikayet;
	}

	public void setTokenGonderiSikayet(TokenGonderiSikayet tokenGonderiSikayet) {
		this.tokenGonderiSikayet = tokenGonderiSikayet;
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

	public GonderiSikayet getGonderiSikayet() {
		return gonderiSikayet;
	}

	public void setGonderiSikayet(GonderiSikayet gonderiSikayet) {
		this.gonderiSikayet = gonderiSikayet;
	}

	public Integer getSikayetEdilenUserId() {
		return sikayetEdilenUserId;
	}

	public void setSikayetEdilenUserId(Integer sikayetEdilenUserId) {
		this.sikayetEdilenUserId = sikayetEdilenUserId;
	}
	
	
	

}
