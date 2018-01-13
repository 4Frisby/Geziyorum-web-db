package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.service.CommonPageGetSharingCountService;

@RestController
public class GetSharingCountController {

	@Autowired
	CommonPageGetSharingCountService commonPageGetSharingCountService;
	
	@RequestMapping(method = RequestMethod.POST , value="/getSharingCountOfUser")
	public Object getSharingCount(@RequestBody TokenUsername tokenUsername) throws IOException{
		commonPageGetSharingCountService.setTokenUsername(tokenUsername);
		return commonPageGetSharingCountService.startService();
	}
	
	
	
}
