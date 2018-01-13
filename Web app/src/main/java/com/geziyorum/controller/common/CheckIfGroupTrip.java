package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenId;
import com.geziyorum.dao.CommonDao;

@RestController
public class CheckIfGroupTrip {
	
	@Autowired
	CommonDao commonDao;
	
    @RequestMapping(method = RequestMethod.POST, value="/checkIfGroupTrip")
	public Object checkIfGroup(@RequestBody TokenId tokenId) throws IOException
	{

		commonDao.checkSessionExistByToken(tokenId.getToken());
		return commonDao.checkIfGroupTrip(tokenId.getId());
		
	}
	
	
	

}
