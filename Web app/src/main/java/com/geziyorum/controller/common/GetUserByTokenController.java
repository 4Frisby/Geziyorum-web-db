package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.geziyorum.dao.CommonDao;

@RestController
public class GetUserByTokenController {
	
	@Autowired
	CommonDao commonDao;
	
	@RequestMapping(method = RequestMethod.POST , value="/getUserInfoByToken")
	public Object getUserInfo(@RequestBody String token) throws IOException{
		return commonDao.getUserInfoBySessionToken(token);
	}

}
