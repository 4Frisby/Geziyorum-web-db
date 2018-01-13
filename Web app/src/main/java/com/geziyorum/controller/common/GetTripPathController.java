package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.geziyorum.argumentresolver.TokenId;
import com.geziyorum.service.GetTripPathService;


@RestController
public class GetTripPathController {
	
	@Autowired
	GetTripPathService getTripPathService;
	
	@RequestMapping(method = RequestMethod.POST , value="/getTripPath")
	public Object getPersonalSharingDetay(@RequestBody TokenId tokenId) throws IOException{
		getTripPathService.setTokenId(tokenId);
		return getTripPathService.startService();
	}
}
