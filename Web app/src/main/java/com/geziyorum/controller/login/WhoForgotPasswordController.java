package com.geziyorum.controller.login;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.dao.CommonDao;
import com.geziyorum.methods.generals.WantToSee;

@RestController
public class WhoForgotPasswordController {
	@Autowired
	CommonDao commonDao;
	
    @RequestMapping(method = RequestMethod.POST, value="/getWhoForgotPassword")
    public Object registerUser(@RequestBody String token) throws IOException{
    	return commonDao.getUserByUsername(WantToSee.forgotPasswordUsername);
    }
    
    
    
	

}
