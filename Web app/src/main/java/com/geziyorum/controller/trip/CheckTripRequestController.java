package com.geziyorum.controller.trip;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.service.CheckTripRequestService;

@RestController
public class CheckTripRequestController {

	@Autowired
	CheckTripRequestService checkTripRequestService;
	
	@RequestMapping(method = RequestMethod.POST , value="/checkTripRequest")
	public Object friendRequest(@RequestBody TokenUsername tokenUsername) throws IOException{
		checkTripRequestService.setTokenUsername(tokenUsername);
		return checkTripRequestService.startService();
	}
	
	
}
