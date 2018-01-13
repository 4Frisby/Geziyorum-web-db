package com.geziyorum.controller.profile;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenId;
import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.dao.CommonDao;

@RestController
public class GizleController {
	
	@Autowired
	CommonDao commonDao;
	
	@RequestMapping(method = RequestMethod.POST , value="/gonderiGizle")
	public Object gonderiGizle(@RequestBody TokenId tokenId) throws IOException{
		commonDao.checkSessionExistByToken(tokenId.getToken());
		commonDao.gonderiGizle(tokenId.getId());
		return true;
	}

}
