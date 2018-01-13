package com.geziyorum.controller.profile;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.geziyorum.service.ProfilePageChangeUserPasswordService;
import com.geziyorum.argumentresolver.TokenPasswordResolver;

@RestController
public class ProfilePageChangeUserPasswordController {

	@Autowired ProfilePageChangeUserPasswordService profilePageChangeUserPasswordService;
	
    @RequestMapping(method = RequestMethod.POST, value="/changeUserPassword")
    public Object getUserGeneralInfo(@RequestBody TokenPasswordResolver tokenPassword) throws IOException{
    	profilePageChangeUserPasswordService.setToken(tokenPassword.getToken());
    	profilePageChangeUserPasswordService.setOldPassword(tokenPassword.getOldPassword());
    	profilePageChangeUserPasswordService.setNewPassword(tokenPassword.getNewPassword());
    	
        Object response = profilePageChangeUserPasswordService.startService();
        return response;
    }
	
	
}
