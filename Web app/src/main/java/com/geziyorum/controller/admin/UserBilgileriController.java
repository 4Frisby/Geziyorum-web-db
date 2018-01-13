package com.geziyorum.controller.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.UserProfilePhoto;
import com.geziyorum.dao.AdminDao;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.entity.Admin;
import com.geziyorum.entity.User;

@RestController
public class UserBilgileriController {
	
	@Autowired
	CommonDao commonDao;
	
	@Autowired
	AdminDao adminDao;
	
    @RequestMapping(method = RequestMethod.POST, value="/adminUserGetir")
    public Object loginUser(@RequestBody String username) throws Exception{
    	
    	if(adminDao.checkUserExistByUsername(username) == false){
    		throw new Exception("Böyle bir kullanıcı mevcut değildir.");
    	}
    	else{
    		UserProfilePhoto upp = new UserProfilePhoto();
    		User user = adminDao.getUserByUsername(username);
    		upp.setUser(user);	
    		upp.setPp(adminDao.getUserProfilePhotoByUsername(user));
    		return upp;
    		
    	}

    }
    
    
    
	

}
