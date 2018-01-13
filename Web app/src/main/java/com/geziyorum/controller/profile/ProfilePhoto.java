package com.geziyorum.controller.profile;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.service.ProfilePageGetProfilePhotoPathService;

@RestController
public class ProfilePhoto {

	@Autowired
	ProfilePageGetProfilePhotoPathService profilePageGetProfilePhotoPathService;
	
	@RequestMapping(value = "/downloadProfilePhotoPath", method = RequestMethod.POST)
	public Object newClient( @RequestBody String username) throws IOException {
		profilePageGetProfilePhotoPathService.setUsername(username);
		return profilePageGetProfilePhotoPathService.startService();

	}
	
	
}
