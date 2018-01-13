package com.geziyorum.controller.trip;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenId;
import com.geziyorum.service.DenyTripRequestService;

@RestController
public class DenyTripRequestController {
	
	@Autowired
	DenyTripRequestService denyTripRequestService;

	@RequestMapping(method = RequestMethod.POST , value="/denyTripRequest")
	public Object friendRequest(@RequestBody TokenId tokenId) throws IOException{
		denyTripRequestService.setTokenId(tokenId);
		return denyTripRequestService.startService();
	}
}
