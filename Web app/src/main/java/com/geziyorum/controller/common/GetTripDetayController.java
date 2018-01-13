package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenId;
import com.geziyorum.service.GetPersonalSharingDetayService;
import com.geziyorum.service.GetTripDetayService;

@RestController
public class GetTripDetayController {

	
	@Autowired
	GetTripDetayService getTripDetayService;
	
	@RequestMapping(method = RequestMethod.POST , value="/getTripDetay")
	public Object getPersonalSharingDetay(@RequestBody TokenId tokenId) throws IOException{
		getTripDetayService.setTokenId(tokenId);
		return getTripDetayService.startService();
	}
	
	
}
