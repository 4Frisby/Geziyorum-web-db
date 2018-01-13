package com.geziyorum.controller.trip;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.geziyorum.service.UploadTripMediaService;

@RestController
public class UploadTripMedia {
	
	@Autowired
	UploadTripMediaService uploadTripMediaService;
	
	@RequestMapping(value = "/uploadTripMedia", method = RequestMethod.POST)
	public @ResponseBody Object uploadTripMedia( 
			 @RequestParam(value = "uzanti") String uzanti
			,@RequestParam(value = "file") MultipartFile file
			,@RequestParam(value = "token") String token
			,@RequestParam(value = "not") String not
			,@RequestParam(value = "kimGorebilir") Integer kimGorebilir
			,@RequestParam(value = "longitude") Double longitude
			,@RequestParam(value = "latitude") Double latitude
			,@RequestParam(value = "altitude") Double altitude
			,@RequestParam(value = "tripId") Integer tripId
			,@RequestParam(value = "dosyaTipi") Integer dosyaTipi) throws IOException {
		
		uploadTripMediaService.setUzanti(uzanti);
		uploadTripMediaService.setAltitude(altitude);
		uploadTripMediaService.setFile(file);
		uploadTripMediaService.setKimGorebilir(kimGorebilir);
		uploadTripMediaService.setLatitude(latitude);
		uploadTripMediaService.setLongitude(longitude);
		uploadTripMediaService.setNot(not);
		uploadTripMediaService.setToken(token);
		uploadTripMediaService.setUzanti(uzanti);
		uploadTripMediaService.setTripId(tripId);
		uploadTripMediaService.setDosyaTipi(dosyaTipi);
		uploadTripMediaService.startService();
			return true;

	}
	
	
	
	

}
