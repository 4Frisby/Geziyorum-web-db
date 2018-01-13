package com.geziyorum.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.dao.AramaDao;
import com.geziyorum.dao.CommonDao;

@Service
public class SearchUserService implements GeneralProcess{

	
	@Autowired
	CommonDao commonDao;
	
	@Autowired
	AramaDao aramaDao;
	
	String token;
	String aramaMetini;
	
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
		if(aramaDao.checkSearchedUserExist(aramaMetini)){
			return aramaDao.findUserBySearchText(aramaMetini);

		}else{
			return false;			
		}
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

	
	
	
}
