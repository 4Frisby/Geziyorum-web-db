package com.geziyorum.controller.profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.service.ProfilePageGetUserInfoService;

@RestController
public class UserInfo {

	@Autowired
	ProfilePageGetUserInfoService profilePageGetUserInfoService;
	
    @RequestMapping(method = RequestMethod.POST, value="/getUserGeneralInfo")
    public Object getUserGeneralInfo(@RequestBody String token){
    	profilePageGetUserInfoService.setToken(token);
        Object response = profilePageGetUserInfoService.startService();
        return response;
    }

}


