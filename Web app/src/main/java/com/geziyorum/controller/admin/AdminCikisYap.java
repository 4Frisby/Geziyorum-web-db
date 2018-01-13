package com.geziyorum.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.geziyorum.dao.AdminDao;

@RestController
public class AdminCikisYap {
	
	@Autowired
	AdminDao adminDao;

	@RequestMapping(method = RequestMethod.POST, value="/adminCikisYap")
	public Object adminCikisYap(@RequestBody Integer adminId){
		if(adminDao.checkAdminAlreadyHasSession(adminId))
			adminDao.killExistingSession(adminId);
		
		return true;

	}
	
	
}
