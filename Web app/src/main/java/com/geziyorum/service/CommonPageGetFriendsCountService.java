package com.geziyorum.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.argumentresolver.TokenUser;
import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.entity.User;

@Service
public class CommonPageGetFriendsCountService implements GeneralProcess{

	@Autowired
	CommonDao commonDao;
	
	TokenUsername tokenUsername;
	String userName;
	User user;
	
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		commonDao.checkSessionExistByToken(tokenUsername.getToken());
		commonDao.checkUserExistByUsername(tokenUsername.getUsername());
		user = commonDao.getUserByUsername(tokenUsername.getUsername());
		return true;
	}

	@Override
	public Object processService() throws IOException {
		return commonDao.getUsersFriendCount(user.getId());
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public TokenUsername getTokenUsername() {
		return tokenUsername;
	}

	public void setTokenUsername(TokenUsername tokenUsername) {
		this.tokenUsername = tokenUsername;
	}
	
	
	

}
