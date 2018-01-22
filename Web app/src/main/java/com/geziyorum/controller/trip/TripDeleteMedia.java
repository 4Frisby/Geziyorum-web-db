package com.geziyorum.controller.trip;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TripIdDownloadList;
import com.geziyorum.service.TripDeleteMediaService;

@RestController
public class TripDeleteMedia {
	
	@Autowired
	TripDeleteMediaService tdms;
	
	   @RequestMapping(value="/deleteMediaFromTrip", method = RequestMethod.POST)
	   public Object deleteMediaFromTrip(@RequestBody TripIdDownloadList tripDeleteMediaList) throws IOException{
		   tdms.setTripId(tripDeleteMediaList.getTripId());
		   tdms.setDeleteFileNames(tripDeleteMediaList.getDownloadList());
		   tdms.processService();
		   return true;
	   }

}
