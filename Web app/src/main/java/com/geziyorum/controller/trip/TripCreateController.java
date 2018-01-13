package com.geziyorum.controller.trip;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.geziyorum.service.TripSaveService;

@Controller
public class TripCreateController {
	
	@Autowired
	TripSaveService tripService;
	
	@RequestMapping(value = "/uploadTripZip", method = RequestMethod.POST)
	public @ResponseBody Object newClient( @RequestParam(value = "token") String token
			,@RequestParam(value = "fileName") String fileName,@RequestParam(value = "file") MultipartFile file) throws IOException {
			tripService.setToken(token);
			tripService.setFileName(fileName);
			tripService.setFile(file);
			tripService.startService();
			return true;

	}

}
