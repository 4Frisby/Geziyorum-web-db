package com.geziyorum.controller.sikayet;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.dao.SikayetDao;

@RestController
public class UserSilController {

	@Autowired
	SikayetDao sikayetDao;
	
	
	@RequestMapping(method = RequestMethod.POST , value="/userSilinebilirMi")
	public Object userSilinebilirMi(@RequestBody String username) throws IOException{
		return sikayetDao.userSilinebilirMi(username);
	}
	
	@RequestMapping(method = RequestMethod.POST , value="/userSil")
	public Object userSil(@RequestBody String username) throws IOException{
		return sikayetDao.userSil(username);
	}
	
	
	
}
