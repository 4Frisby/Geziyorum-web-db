package com.geziyorum.controller.trip;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.geziyorum.dao.TripDao;

@RestController
public class CheckOwnerSent {
	
	@Autowired
	TripDao tripDao;
	
	@RequestMapping(method = RequestMethod.POST , value="/checkOwnerSent")
	public Object checkOwnerSent(@RequestBody Integer tripId) throws IOException{
		return tripDao.checkTripUpdatedByOwner(tripId);
			// henüz yaratıcı gezisini yollamamışsa katılımcıya false döner.
	}
}
