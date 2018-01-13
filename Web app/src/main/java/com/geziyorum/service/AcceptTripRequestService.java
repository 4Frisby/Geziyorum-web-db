package com.geziyorum.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.argumentresolver.TokenId;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.TripDao;
import com.geziyorum.entity.TripRequest;
import com.geziyorum.entity.User;

@Service
public class AcceptTripRequestService implements GeneralProcess{

	@Autowired
	CommonDao commonDao;
	
	@Autowired
	TripDao tripDao;

	TokenId tokenId;
	User user;
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		commonDao.checkSessionExistByToken(tokenId.getToken());
		return true;
	}

	@Override
	public Object processService() throws IOException {
		TripRequest tripRequest = tripDao.getTripRequestByRequestId(tokenId.getId());		
		tripDao.deleteTripRequestById(tokenId.getId());
		tripDao.createTripUser(tripRequest.getTripId(),tripRequest.getKatilimciUserId());
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TokenId getTokenId() {
		return tokenId;
	}

	public void setTokenId(TokenId tokenId) {
		this.tokenId = tokenId;
	}


		
	
	

}
