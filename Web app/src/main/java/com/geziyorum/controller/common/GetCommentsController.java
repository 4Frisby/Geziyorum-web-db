package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenPersonalSharing;
import com.geziyorum.service.GetCommentsService;

@RestController
public class GetCommentsController {
	
	@Autowired
	GetCommentsService cetCommentsService;
	
	
	@RequestMapping(method = RequestMethod.POST , value="/getComments")
	public Object getLikeCountOfSharing(@RequestBody TokenPersonalSharing tokenPersonalSharing) throws IOException{
		
		cetCommentsService.setTokenPersonalSharing(tokenPersonalSharing);
		return cetCommentsService.startService();
	}
	

}
