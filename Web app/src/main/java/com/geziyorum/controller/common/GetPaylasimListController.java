package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.service.CommonPageGetPaylasimListService;

@RestController
public class GetPaylasimListController {
	
	@Autowired
	CommonPageGetPaylasimListService commonPageGetPaylasimListService;
	
	 @RequestMapping(method = RequestMethod.POST, value="/getPaylasimList")
	public Object getPaylasimList(@RequestBody TokenUsername tokenUsername) throws IOException{
		 
		commonPageGetPaylasimListService.setTokenUsername(tokenUsername);
		return commonPageGetPaylasimListService.startService();
		 

	}
	

}
