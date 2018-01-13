package com.geziyorum.controller.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TripToken;
import com.geziyorum.dao.CommonDao;

@RestController
public class GetAllTripMedia {

	
	@Autowired
	CommonDao commonDao;
	
	@RequestMapping(method = RequestMethod.POST , value="/getTripMediasForAdmin")
	public Object getTripMediasForAdmin(@RequestBody TripToken tripToken) throws IOException{
		
		return commonDao.getAllTripMediaForAdmin(tripToken.getTripId());
		
	}
	
}
