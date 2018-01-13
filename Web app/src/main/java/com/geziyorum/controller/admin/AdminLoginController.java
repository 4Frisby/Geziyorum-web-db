package com.geziyorum.controller.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.entity.Admin;
import com.geziyorum.service.AdminLoginService;

@RestController
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminLoginService;
    
    @RequestMapping(method = RequestMethod.POST, value="/adminLogin")
    public Object loginUser(@RequestBody Admin admin) throws IOException{
    	adminLoginService.setAdmin(admin);
        return adminLoginService.startService();
      
    }
}
