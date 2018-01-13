package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.argumentresolver.UsernamePassword;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.SikayetDao;

@RestController
public class GetSikayetOfListOfUser {
	
	@Autowired
	SikayetDao sikayetDao;
	
	@Autowired
	CommonDao commonDao;
	
    @RequestMapping(method = RequestMethod.POST, value="/getSikayetListOfUser")
    public Object getSikayetListOfUser(@RequestBody TokenUsername tokenUsername) throws IOException{
    	commonDao.checkSessionExistByToken(tokenUsername.getToken());
    	return sikayetDao.getSikayetListOfUser(tokenUsername.getUsername());
    }

}
