package com.geziyorum.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.entity.User;

@Service
public class CheckFriendRequestExistService implements GeneralProcess{

	@Autowired
	CommonDao commonDao;
	
	TokenUsername tokenUsername;
	User senderUser;
	User recieverUser;
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		commonDao.checkSessionExistByToken(tokenUsername.getToken());
		senderUser = commonDao.getUserInfoBySessionToken(tokenUsername.getToken());
		recieverUser = commonDao.getUserByUsername(tokenUsername.getUsername());
		return true;
	}

	@Override
	public Object processService() throws IOException {
		return commonDao.CheckFriendRequestExist(senderUser,recieverUser);
	}

	public TokenUsername getTokenUsername() {
		return tokenUsername;
	}

	public void setTokenUsername(TokenUsername tokenUsername) {
		this.tokenUsername = tokenUsername;
	}

	
	
	
}
