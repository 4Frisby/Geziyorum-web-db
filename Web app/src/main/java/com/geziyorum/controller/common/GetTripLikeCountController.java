package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenPersonalSharing;
import com.geziyorum.service.CommonPageGetTripLikeCountService;

@RestController
public class GetTripLikeCountController {
	
	@Autowired
	CommonPageGetTripLikeCountService commonPageGetTripLikeCountService;
	
	@RequestMapping(method = RequestMethod.POST , value="/getPersonalSharingLikeCount")
	public Object getLikeCountOfSharing(@RequestBody TokenPersonalSharing tokenPersonalSharing) throws IOException{
		
		commonPageGetTripLikeCountService.setTokenPersonalSharing(tokenPersonalSharing);
		return commonPageGetTripLikeCountService.startService();
	}
	

}
