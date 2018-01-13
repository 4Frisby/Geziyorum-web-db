package com.geziyorum.controller.trip;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.geziyorum.service.TripDeleteService;

@RestController
public class TripDeleteController {
	@Autowired
	TripDeleteService tripDeleteService;
	
	@RequestMapping(value = "/deleteTrip", method = RequestMethod.POST)
	public Object newClient(@RequestBody String token, Integer tripId) throws IOException {
			tripDeleteService.setToken(token);
			tripDeleteService.setTripId(tripId);
			return tripDeleteService.startService();
			

	}
	
}
