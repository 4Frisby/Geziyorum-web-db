package com.geziyorum.controller.trip;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TripIdDownloadList;
import com.geziyorum.methods.generals.WantToSee;
import com.geziyorum.service.TripDownloadService;

@RestController
public class TripDownloadController {
	
	@Autowired
	TripDownloadService tripDownloadService;

	   @RequestMapping(value="/tripDownload", method = RequestMethod.POST)
	   public Object downloadTrip(@RequestBody TripIdDownloadList tnd) throws IOException{
		   tripDownloadService.setDownloadFileNames(tnd.getDownloadList());
		   tripDownloadService.setTripId(tnd.getTripId());
		   return tripDownloadService.startService();
	   
   }
	   
	   
	   
	
	
}
