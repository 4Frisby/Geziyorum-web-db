package com.geziyorum.service;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.entity.User;
import com.geziyorum.exception.SessionTokenNotFoundException;
import com.geziyorum.methods.generals.WantToSee;

@Service
public class UserPageGetUserProfileService implements GeneralProcess{
	@Autowired
	CommonDao commonDao;
	
	private User ownerUser;
	private TokenUsername tokenUsername;
	
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}
	@Override
	public Object validateService() throws IOException {
		if(!commonDao.checkSessionExistByToken(tokenUsername.getToken()))
			throw new SessionTokenNotFoundException("Session token bulunamadı.");
		
		if(!commonDao.checkUserExistByUsername(tokenUsername.getUsername()))
			throw new IOException("böyle bir kullancı mevcut değil");
		
		return true;
	}
	@Override
	public Object processService() throws IOException {
		setOwnerUser(commonDao.getUserByUsername(tokenUsername.getUsername()));
		
		return true;
	}
	public User getOwnerUser() {
		return ownerUser;
	}
	public void setOwnerUser(User ownerUser) {
		this.ownerUser = ownerUser;
	}
	public TokenUsername getTokenUsername() {
		return tokenUsername;
	}
	public void setTokenUsername(TokenUsername tokenUsername) {
		this.tokenUsername = tokenUsername;
	}
	
	
	
	
	
	
}
