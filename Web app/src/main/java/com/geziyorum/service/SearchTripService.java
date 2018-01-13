package com.geziyorum.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.dao.AramaDao;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.entity.User;

@Service
public class SearchTripService implements GeneralProcess{

	@Autowired
	CommonDao commonDao;
	
	@Autowired
	AramaDao aramaDao;
	
	String token;
	String aramaMetini;
	String kimlerArasinda;
	String geziTipi;
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
		return aramaDao.searchTrip(aramaMetini,user.getId(),kimlerArasinda,geziTipi);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAramaMetini() {
		return aramaMetini;
	}

	public void setAramaMetini(String aramaMetini) {
		this.aramaMetini = aramaMetini;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getKimlerArasinda() {
		return kimlerArasinda;
	}

	public void setKimlerArasinda(String kimlerArasinda) {
		this.kimlerArasinda = kimlerArasinda;
	}

	public String getGeziTipi() {
		return geziTipi;
	}

	public void setGeziTipi(String geziTipi) {
		this.geziTipi = geziTipi;
	}
	
}
