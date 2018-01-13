package com.geziyorum.service;

import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.argumentresolver.TokenProfilSikayet;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.SikayetDao;
import com.geziyorum.entity.ProfilSikayet;
import com.geziyorum.entity.User;

@Service
public class CreateProfilSikayetService implements GeneralProcess{

	@Autowired
	CommonDao commonDao;
	
	@Autowired
	SikayetDao sikayetDao;	
	
	String token;
	User sikayetciUser;
	
	
	TokenProfilSikayet tokenProfilSikayet;
	ProfilSikayet profilSikayet;
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		setToken(tokenProfilSikayet.getToken());
		setProfilSikayet(tokenProfilSikayet.getProfilSikayet());
		commonDao.checkSessionExistByToken(token);
		return true;
	}

	@Override
	public Object processService() throws IOException {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		profilSikayet.setDegerlendirildi(0);
		profilSikayet.setSikayetZamani(time);
		profilSikayet.setSikayetciUsername(commonDao.getUserInfoBySessionToken(token).getUsername());
		return sikayetDao.createProfilSikayet(profilSikayet);
	}

	public TokenProfilSikayet getTokenProfilSikayet() {
		return tokenProfilSikayet;
	}

	public void setTokenProfilSikayet(TokenProfilSikayet tokenProfilSikayet) {
		this.tokenProfilSikayet = tokenProfilSikayet;
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

	public ProfilSikayet getProfilSikayet() {
		return profilSikayet;
	}

	public void setProfilSikayet(ProfilSikayet profilSikayet) {
		this.profilSikayet = profilSikayet;
	}
	
	
	
	
}
