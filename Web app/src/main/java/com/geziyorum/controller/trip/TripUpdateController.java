package com.geziyorum.controller.trip;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.geziyorum.service.TripFirstUpdateService;
import com.geziyorum.service.TripOtherUpdatesService;

@Controller
public class TripUpdateController {

	@Autowired
	TripFirstUpdateService tripFirstUpdateService;
	
	@Autowired
	TripOtherUpdatesService tripOtherUpdatesService;
	
	@RequestMapping(value = "/uploadGroupTripZip", method = RequestMethod.POST)
	public @ResponseBody Object newClient( @RequestParam(value = "token") String token
			,@RequestParam(value = "fileName") String fileName,@RequestParam(value = "tripId") Integer tripId, 
			@RequestParam(value = "isFirstFileUpload") Boolean isFirstFileUpload, @RequestParam(value = "file") MultipartFile file) throws IOException {
			if(isFirstFileUpload){
				tripFirstUpdateService.setToken(token);
				tripFirstUpdateService.setFileName(fileName);
				tripFirstUpdateService.setFile(file);
				tripFirstUpdateService.setTripId(tripId);				
				tripFirstUpdateService.startService();				
			}else{
				tripOtherUpdatesService.setToken(token);
				tripOtherUpdatesService.setFileName(fileName);
				tripOtherUpdatesService.setFile(file);
				tripOtherUpdatesService.setTripId(tripId);					
				tripOtherUpdatesService.startService();
			}


			return true;

	}

	
	
	
}
