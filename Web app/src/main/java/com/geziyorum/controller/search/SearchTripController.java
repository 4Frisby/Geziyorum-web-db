package com.geziyorum.controller.search;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.geziyorum.argumentresolver.TripSearchElements;
import com.geziyorum.service.SearchTripService;

@RestController
public class SearchTripController {
	
	@Autowired
	SearchTripService searchTripService;
	
    @RequestMapping(method = RequestMethod.POST, value="/searchTrip")
    public Object searchTrip(@RequestBody TripSearchElements tse) throws IOException{
    	searchTripService.setAramaMetini(tse.getAramaMetini());
    	searchTripService.setGeziTipi(tse.getGeziTipi());
    	searchTripService.setToken(tse.getToken());
    	searchTripService.setKimlerArasinda(tse.getKimlerArasinda());
    	return searchTripService.startService();
    }

}
