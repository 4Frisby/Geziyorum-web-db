package com.geziyorum.controller.login;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.entity.User;
import com.geziyorum.service.UserRegisterService;

@RestController
public class RegisterPageController {

	   @Autowired
	    private UserRegisterService userRegisterService;
	
    @RequestMapping(method = RequestMethod.POST, value="/userRegister")
    public Object registerUser(@RequestBody User user) throws IOException{

    	userRegisterService.setUser(user);
        return userRegisterService.startService();
    }
	
}
