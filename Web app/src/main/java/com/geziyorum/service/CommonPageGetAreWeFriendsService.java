package com.geziyorum.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.geziyorum.argumentresolver.UserFriendResolver;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.entity.User;

@Service
public class CommonPageGetAreWeFriendsService implements GeneralProcess{

	@Autowired
	CommonDao commonDao;
	
	UserFriendResolver userFriendResolver;
	User user1;
	User user2;
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		commonDao.checkSessionExistByToken(userFriendResolver.getToken());
		user1 = commonDao.getUserInfoBySessionToken(userFriendResolver.getToken());

		commonDao.checkUserExistByUsername(userFriendResolver.getOtherUserUsername());
		user2 = commonDao.getUserByUsername(userFriendResolver.getOtherUserUsername());
		return true;
	}

	@Override
	public Object processService() throws IOException {
		if(commonDao.checkAreWeFriends(user1,user2))
			return true;
		else 
			return false;
	}

	public UserFriendResolver getUserFriendResolver() {
		return userFriendResolver;
	}

	public void setUserFriendResolver(UserFriendResolver userFriendResolver) {
		this.userFriendResolver = userFriendResolver;
	}
	
	
	
	

}
