package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenUser;
import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.service.CommonPageGetFriendsCountService;

@RestController
public class GetFriendsCountController {

	@Autowired
	CommonPageGetFriendsCountService commonPageGetFriendsCountService;
	
	
    @RequestMapping(method = RequestMethod.POST, value="/getFriendsCount")
	public Object getFriendCount(@RequestBody TokenUsername tokenUsername) throws IOException{
    	
    	commonPageGetFriendsCountService.setTokenUsername(tokenUsername);
    	return commonPageGetFriendsCountService.startService();
    }
	
}
