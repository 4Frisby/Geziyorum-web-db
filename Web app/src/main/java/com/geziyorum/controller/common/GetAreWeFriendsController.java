package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.UserFriendResolver;
import com.geziyorum.service.CommonPageGetAreWeFriendsService;

@RestController
public class GetAreWeFriendsController {

	
	@Autowired
	CommonPageGetAreWeFriendsService commonPageGetAreWeFriendsService;
	
    @RequestMapping(method = RequestMethod.POST, value="/checkAreWeFriends")
    public Object areWeFriends(@RequestBody UserFriendResolver userFriendResolver) throws IOException{
    	
    	commonPageGetAreWeFriendsService.setUserFriendResolver(userFriendResolver);
    	return commonPageGetAreWeFriendsService.startService();
    	
    	
    }
	
	
}
