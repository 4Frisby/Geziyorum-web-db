package com.geziyorum.controller.search;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenString;
import com.geziyorum.entity.AramaSession;
import com.geziyorum.service.SearchUserService;

@RestController
public class SearchUserController {
	
	@Autowired
	SearchUserService searchUserService;
	
    @RequestMapping(method = RequestMethod.POST, value="/searchUser")
    public Object getUserGeneralInfo(@RequestBody TokenString tokenString) throws IOException{
    	searchUserService.setToken(tokenString.getToken());
    	searchUserService.setAramaMetini(tokenString.getMetin());
    	return searchUserService.startService();
    }

}
