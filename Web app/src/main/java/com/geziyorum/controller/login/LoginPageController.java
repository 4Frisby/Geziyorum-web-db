package com.geziyorum.controller.login;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.entity.User;
import com.geziyorum.methods.generals.Constraints;
import com.geziyorum.service.UserLoginService;


@RestController
public class LoginPageController {

    @Autowired
    private UserLoginService userLoginService;
    
    @RequestMapping(method = RequestMethod.POST, value="/login")
    public Object loginUser(@RequestBody User user) throws IOException{
        userLoginService.setUser(user);
        return userLoginService.startService();
      
    }
    
 
    
}
