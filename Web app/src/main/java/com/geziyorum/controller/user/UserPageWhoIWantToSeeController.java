package com.geziyorum.controller.user;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.service.UserPageGetUserProfileService;

@RestController
public class UserPageWhoIWantToSeeController {
	
	@Autowired
	UserPageGetUserProfileService userPageGetUserProfileService;
	
    @RequestMapping(method = RequestMethod.POST, value="/wantToSee")
	public Object returnWantToSee(@RequestBody TokenUsername tokenUsername) throws IOException{
    	userPageGetUserProfileService.setTokenUsername(tokenUsername);
    	userPageGetUserProfileService.startService();
    	return userPageGetUserProfileService.getOwnerUser();
	}
	
}
