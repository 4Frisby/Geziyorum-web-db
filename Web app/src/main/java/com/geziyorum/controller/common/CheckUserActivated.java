package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.geziyorum.dao.CommonDao;

@RestController
public class CheckUserActivated {
	
	@Autowired
	CommonDao commonDao;
	
    @RequestMapping(method = RequestMethod.POST, value="/checkUserActivated")
	public Object checkUserActivated(@RequestBody String username) throws IOException{
    	Boolean isActive = false;
    	if(!commonDao.checkUserExistByUsername(username))
    		return "notExist";
    	else
        	return commonDao.checkUserActivated(username);
    	

    }

}
