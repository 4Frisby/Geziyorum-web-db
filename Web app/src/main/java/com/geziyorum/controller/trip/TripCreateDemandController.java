package com.geziyorum.controller.trip;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenTrip;
import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.service.TripCreateDemandService;


@RestController
public class TripCreateDemandController {

	@Autowired
	TripCreateDemandService tripCreateDemandService;
	
	
	@RequestMapping(method = RequestMethod.POST , value="/createTripDemand")
	public Object friendRequest(@RequestBody TokenTrip tokenTrip) throws IOException{
		tripCreateDemandService.setTokenTrip(tokenTrip);
		return tripCreateDemandService.startService();
	}
	
	
	
	
}
