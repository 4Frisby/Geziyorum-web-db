package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.service.CommonPageGetGonderiCountService;

@RestController
public class GetGonderiCountController {

	@Autowired
	CommonPageGetGonderiCountService commonPageGetGonderiCountService;
	
	@RequestMapping(method = RequestMethod.POST , value="/getGonderiCountOfUser")
	public Object getSharingCount(@RequestBody TokenUsername tokenUsername) throws IOException{
		commonPageGetGonderiCountService.setTokenUsername(tokenUsername);
		return commonPageGetGonderiCountService.startService();
	}
	
	
	
}
