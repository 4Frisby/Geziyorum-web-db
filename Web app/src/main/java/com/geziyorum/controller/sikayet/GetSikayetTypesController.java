package com.geziyorum.controller.sikayet;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.geziyorum.service.GetSikayetTypesService;

@RestController
public class GetSikayetTypesController {
	
	@Autowired
	GetSikayetTypesService getSikayetTypesService;
	
	@RequestMapping(method = RequestMethod.POST , value="/getSikayetTypes")
	public Object getSikayetTypes(@RequestBody String token) throws IOException{
		getSikayetTypesService.setToken(token);
		return getSikayetTypesService.startService();
	}

}
