package com.geziyorum.controller.profile;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenUser;
import com.geziyorum.service.ProfilePageSaveUserInfoService;

@RestController
public class ProfilePageSaveUserInfoController {

	@Autowired
	ProfilePageSaveUserInfoService profilePageSaveUserInfoService;
	
    @RequestMapping(method = RequestMethod.POST, value="/saveUserGeneralInfo")
    public Object getUserGeneralInfo(@RequestBody TokenUser tokenUser) throws IOException{
    	profilePageSaveUserInfoService.setToken(tokenUser.getToken());
    	profilePageSaveUserInfoService.setUser(tokenUser.getUser());
        return profilePageSaveUserInfoService.startService();

    }
}
