package com.geziyorum.controller.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.geziyorum.dao.AdminDao;

@RestController
public class GetAdminInfo {
	
	@Autowired
	AdminDao adminDao;
	
	@RequestMapping(method = RequestMethod.POST , value="/getAdminInfo")
	public Object getAdminInfo(@RequestBody String token) throws IOException{
		return adminDao.getAdminByToken(token);
	}
}
