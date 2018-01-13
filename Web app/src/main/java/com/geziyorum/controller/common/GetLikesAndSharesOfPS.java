package com.geziyorum.controller.common;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.argumentresolver.UserLikeTime;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.entity.Likes;
import com.geziyorum.entity.PersonalSharing;
import com.geziyorum.entity.User;

@RestController
public class GetLikesAndSharesOfPS {
	
	@Autowired
	CommonDao commonDao;
	
	@RequestMapping(method = RequestMethod.POST , value="/getLikedUsersOfPs")
	public Object getLikedUsersOfPs(@RequestBody Integer psId) throws IOException{
		ArrayList<Likes> likes = commonDao.getPsLikes(psId);
		ArrayList<User> users = new ArrayList<User>();
		for(int i=0; i<likes.size(); i++){
			User user = new User();
			user = commonDao.getUserById(likes.get(i).getUserId());
			users.add(user);
		}
		return users;
	}
	
	@RequestMapping(method = RequestMethod.POST , value="/getSharedUsersOfPs")
	public Object getSharedUsersOfPs(@RequestBody Integer psId) throws IOException{
		return commonDao.getPsSharedUsers(psId);
	}
	

}
