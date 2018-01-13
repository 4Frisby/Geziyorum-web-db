package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.service.CommonPagePersonalSharingGetirService;


@RestController
public class GetPersonalSharingListController {

	@Autowired
	CommonPagePersonalSharingGetirService commonPagePersonalSharingGetirService;
	
	@RequestMapping(value = "/getPersonalSharingList", method = RequestMethod.POST)
	public Object getList(@RequestBody TokenUsername tokenUsername) throws IOException{
		commonPagePersonalSharingGetirService.setTokenUsername(tokenUsername);
		return commonPagePersonalSharingGetirService.startService();
	}
	
	
	
	
	
	
}
