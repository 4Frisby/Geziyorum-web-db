package com.geziyorum.controller.trip;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenId;
import com.geziyorum.service.AcceptTripRequestService;

@RestController
public class AcceptTripRequestController {
	
	@Autowired
	AcceptTripRequestService acceptTripRequestService;

	@RequestMapping(method = RequestMethod.POST , value="/acceptTripRequest")
	public Object friendRequest(@RequestBody TokenId tokenId) throws IOException{
		acceptTripRequestService.setTokenId(tokenId);
		return acceptTripRequestService.startService();
	}
}
