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
import com.geziyorum.service.LikeTripService;
import com.geziyorum.service.UnlikeTripService;

@RestController
public class LikeTripController {
	
	@Autowired
	LikeTripService likeTripService;
	
	@Autowired
	UnlikeTripService unlikeTripService;
	
	@RequestMapping(method = RequestMethod.POST, value="/likeTrip")
	public Object likeTrip(@RequestBody TokenId tokenId) throws IOException{
		 likeTripService.setTokenId(tokenId);
		 return likeTripService.startService();
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/unlikeTrip")
	public Object unlikeTrip(@RequestBody TokenId tokenId) throws IOException{
		unlikeTripService.setTokenId(tokenId);
		 return unlikeTripService.startService();
	}
	
	
}
