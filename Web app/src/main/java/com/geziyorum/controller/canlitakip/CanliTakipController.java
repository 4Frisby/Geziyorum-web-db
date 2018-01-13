package com.geziyorum.controller.canlitakip;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.CurrentLocationToken;
import com.geziyorum.argumentresolver.TokenId;
import com.geziyorum.dao.TripDao;

@RestController
public class CanliTakipController {

	@Autowired
	TripDao tripDao;
	
	
    @RequestMapping(method = RequestMethod.POST, value="/saveLocation")
	public Object saveLocation(@RequestBody CurrentLocationToken clt) throws IOException
	{
    	if(!tripDao.checkIfHaveCurrentLocation(clt.getCurrentLocation().getUsername(),clt.getCurrentLocation().getTripId()))
    		tripDao.createCurrentLocation(clt.getCurrentLocation());
    	else
    		tripDao.updateCurrentLocation(clt.getCurrentLocation());	
    	return tripDao.getCurrentLocationsOfUsers(clt.getCurrentLocation().getTripId());	
	}
	
    @RequestMapping(method = RequestMethod.POST, value="/deleteLocation")
    public Object deleteLocation(@RequestBody TokenId tokenId){
    	tripDao.deleteAllCurrentLocationOfTrip(tokenId.getId());
    	return true;
    }
    
    
    
}
