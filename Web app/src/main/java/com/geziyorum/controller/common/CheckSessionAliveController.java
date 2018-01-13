package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.geziyorum.dao.CommonDao;

@RestController
public class CheckSessionAliveController {

	@Autowired
	CommonDao commonDao;
	
	@RequestMapping(method = RequestMethod.POST, value="/checkSessionAlive")
	public Object likeTrip(@RequestBody String token) throws Exception{
		if(commonDao.checkSessionExistByToken(token) == false)
			throw new Exception("Oturum süreniz dolmuş veya çıkış yapılmıştır. Anasayfaya yönlendiriliyorsunuz...");
		else
			return true;
	}
	
}
