package com.geziyorum.controller.home;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.service.GetFriendPaylasimListService;

@RestController
public class GetFriendPaylasimList {
	
	@Autowired
	GetFriendPaylasimListService getFriendPaylasimListService;

    @RequestMapping(method = RequestMethod.POST, value="/getFriendPaylasimList")
	public Object getFriendPaylasimList(@RequestBody String token) throws IOException
	{
    	getFriendPaylasimListService.setToken(token);
    	return getFriendPaylasimListService.startService();	
	}
	
}
