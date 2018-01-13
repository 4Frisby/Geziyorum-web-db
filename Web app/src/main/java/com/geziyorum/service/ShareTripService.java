package com.geziyorum.service;

import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.argumentresolver.TokenId;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.entity.PersonalSharing;
import com.geziyorum.entity.User;

@Service
public class ShareTripService implements GeneralProcess{

	@Autowired
	CommonDao commonDao;
	
	TokenId tokenId;
	User user;
	PersonalSharing ps;
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		
		commonDao.checkSessionExistByToken(tokenId.getToken());
		setUser(commonDao.getUserInfoBySessionToken(tokenId.getToken()));
		setPs(commonDao.getPersonalSharingById(tokenId.getId()));
		return true;
	}

	@Override
	public Object processService() throws IOException {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		return commonDao.shareTrip(ps.getOwnerId(), user.getId(), ps.getId(), now);
	}

	public TokenId getTokenId() {
		return tokenId;
	}

	public void setTokenId(TokenId tokenId) {
		this.tokenId = tokenId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PersonalSharing getPs() {
		return ps;
	}

	public void setPs(PersonalSharing ps) {
		this.ps = ps;
	}
	
	

	
	
}
