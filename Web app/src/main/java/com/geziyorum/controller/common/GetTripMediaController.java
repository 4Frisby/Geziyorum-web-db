package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TripToken;
import com.geziyorum.service.CommonPageGetTripMediaService;

@RestController
public class GetTripMediaController {

	@Autowired
	CommonPageGetTripMediaService commonPageGetTripMediaService;
	
	@RequestMapping(method = RequestMethod.POST , value="/getTripMedias")
	public Object getTripMedia(@RequestBody TripToken tripToken) throws IOException{
		
		commonPageGetTripMediaService.setTripToken(tripToken);
		return commonPageGetTripMediaService.startService();
	}
	
}
