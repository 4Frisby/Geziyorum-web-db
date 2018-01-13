package com.geziyorum.controller.login;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.service.ForgotPasswordService;


@RestController
public class ForgotPasswordController {
	
	@Autowired
	ForgotPasswordService forgotPasswordService;
	
    @RequestMapping(method = RequestMethod.POST, value="/forgotPassword")
    public Object loginUser(@RequestBody String username) throws IOException{
    	forgotPasswordService.setUsername(username);
        return forgotPasswordService.startService();
      
    }

}
