package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenId;
import com.geziyorum.argumentresolver.TokenUserId;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.service.ShareTripService;



@RestController
public class ShareTripController {

	@Autowired
	ShareTripService shareTripService;
	
	 @RequestMapping(method = RequestMethod.POST, value="/shareTrip")
	public Object shareTrip(@RequestBody TokenId tokenId) throws IOException{
		 shareTripService.setTokenId(tokenId);
		 return shareTripService.startService();

	}
	
}
