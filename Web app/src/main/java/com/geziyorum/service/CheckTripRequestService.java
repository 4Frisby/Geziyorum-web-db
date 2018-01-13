package com.geziyorum.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.TripDao;

@Service
public class CheckTripRequestService implements GeneralProcess{

	
	@Autowired
	CommonDao commonDao;
	
	@Autowired
	TripDao tripDao;
	
	
	TokenUsername tokenUsername;
	
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		commonDao.checkSessionExistByToken(tokenUsername.getToken());
		return true;
	}

	@Override
	public Object processService() throws IOException {
		return tripDao.getTripRequestByUserId(commonDao.getUserByUsername(tokenUsername.getUsername()).getId());
	}

	public TokenUsername getTokenUsername() {
		return tokenUsername;
	}

	public void setTokenUsername(TokenUsername tokenUsername) {
		this.tokenUsername = tokenUsername;
	}
	
	
	
	

}
