package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.service.UnfriendService;

@RestController
public class UnfriendController {
	
	@Autowired
	UnfriendService unfriendService;
	
	@RequestMapping(method = RequestMethod.POST , value="/unfriendFriend")
	public Boolean unFriend(@RequestBody TokenUsername tokenUsername) throws IOException{
		unfriendService.setTokenUsername(tokenUsername);
		unfriendService.startService();
		return true;
	}
	

}
