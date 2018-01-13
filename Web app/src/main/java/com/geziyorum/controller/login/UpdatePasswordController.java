package com.geziyorum.controller.login;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.UsernamePassword;
import com.geziyorum.dao.CommonDao;

@RestController
public class UpdatePasswordController {

	@Autowired
	CommonDao commonDao;
	
    @RequestMapping(method = RequestMethod.POST, value="/updatePassword")
    public Object registerUser(@RequestBody UsernamePassword unp) throws IOException{
    	return commonDao.updatePassword(unp.getNewPassword(),unp.getUsername());
    }
    
	
}
