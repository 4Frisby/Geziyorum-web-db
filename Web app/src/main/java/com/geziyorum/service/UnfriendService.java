package com.geziyorum.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.entity.User;

@Service
public class UnfriendService implements GeneralProcess {

	@Autowired
	CommonDao commonDao;
	
	TokenUsername tokenUsername;	
	User user1;
	User user2;
	
	
	@Override
	public Object startService() throws IOException {
		validateService();
		processService();
		return true;
	}

	@Override
	public Object validateService() throws IOException {
		commonDao.checkSessionExistByToken(tokenUsername.getToken());
		user2 = commonDao.getUserInfoBySessionToken(tokenUsername.getToken());
		commonDao.checkUserExistByUsername(tokenUsername.getUsername());
		user1 = commonDao.getUserByUsername(tokenUsername.getUsername());
		return true;
	}

	@Override
	public Object processService() throws IOException {
		commonDao.unfriend(user1,user2);
		return null;
	}

	public TokenUsername getTokenUsername() {
		return tokenUsername;
	}

	public void setTokenUsername(TokenUsername tokenUsername) {
		this.tokenUsername = tokenUsername;
	}

	
	
	
}
