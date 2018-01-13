package com.geziyorum.controller.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.LoginPageDao;
import com.geziyorum.entity.PersonalSharing;
import com.geziyorum.entity.Session;
import com.geziyorum.entity.User;

@RestController
public class GetFakeSession {
	
	@Autowired
	LoginPageDao loginPageDao;
	
	@Autowired
	CommonDao commonDao;
	
	@RequestMapping(method = RequestMethod.POST , value="/getFakeSession")
	public Object getFakeSession(@RequestBody Integer psId) throws IOException{
		PersonalSharing ps = commonDao.getPersonalSharingByIdForAdmin(psId);
		User user = commonDao.getUserByIdForAdmin(ps.getOwnerId());
		
		if(loginPageDao.checkUserAlreadyHasSession(ps.getOwnerId())){
			return loginPageDao.getUsersToken(user.getId());
		}else{
			Session session = loginPageDao.createSessionByUserId(user);
			return session.getSessionToken();
		}
	}
	

}
