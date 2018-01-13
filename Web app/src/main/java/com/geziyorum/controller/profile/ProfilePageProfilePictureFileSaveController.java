package com.geziyorum.controller.profile;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.geziyorum.service.ProfilePageSavePhotoService;



/**
 * Handles requests for the application file upload requests
 */
@Controller
public class ProfilePageProfilePictureFileSaveController {
	
	@Autowired ProfilePageSavePhotoService profilePageSavePhotoService;

	private static final Logger logger = LoggerFactory
			.getLogger(ProfilePageProfilePictureFileSaveController.class);

	
	@RequestMapping(value = "/uploadProfilePhoto", method = RequestMethod.POST)
	public @ResponseBody Object newClient( @RequestParam(value = "token") String token, @RequestParam(value = "file") MultipartFile file) throws IOException {
			profilePageSavePhotoService.setToken(token);
			profilePageSavePhotoService.setFile(file);
			Object response = profilePageSavePhotoService.startService();
			return true;

	}

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody Object uploadMultipleFileHandler(@RequestParam("name") String[] names,
			@RequestParam("file") MultipartFile[] files) {
			return true;
	}
}
