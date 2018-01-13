package com.geziyorum.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.entity.PersonalSharing;
import com.geziyorum.entity.SomeoneSharing;
import com.geziyorum.entity.TripAllContentWithWhoShared;
import com.geziyorum.entity.User;

@Service
public class CommonPageGetPaylasimListService  implements GeneralProcess{

	@Autowired
	CommonDao commonDao;
	
	User user;
	TokenUsername tokenUsername;
	ArrayList<TripAllContentWithWhoShared> tripAllContentWithWhoShared = new ArrayList<TripAllContentWithWhoShared>();
	
	
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
		return commonDao.getPaylasimListByUser(getUser());
	}

	public TokenUsername getTokenUsername() {
		return tokenUsername;
	}

	public void setTokenUsername(TokenUsername tokenUsername) {
		this.tokenUsername = tokenUsername;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	
	
	
}

