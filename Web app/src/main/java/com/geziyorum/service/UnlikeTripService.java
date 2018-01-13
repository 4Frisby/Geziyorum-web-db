package com.geziyorum.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.argumentresolver.TokenId;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.entity.User;


@Service
public class UnlikeTripService implements GeneralProcess{

	@Autowired
	CommonDao commonDao;
	
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
		setUser(commonDao.getUserInfoBySessionToken(tokenId.getToken()));
		
		return true;
	}
	@Override
	public Object processService() throws IOException {
		Boolean ifLiked = commonDao.checkLikeTrip(user.getId(), tokenId.getId());
		if( ifLiked == true);
		commonDao.unlikeTrip(user.getId(), tokenId.getId());
		return true;
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
	
	
	
	
	
}
