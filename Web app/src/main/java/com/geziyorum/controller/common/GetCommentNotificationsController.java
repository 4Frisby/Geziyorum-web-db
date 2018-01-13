package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenPersonalSharing;
import com.geziyorum.service.GetCommentNotificationsCountService;
import com.geziyorum.service.GetCommentNotificationsService;
import com.geziyorum.service.GetCommentsService;

@RestController
public class GetCommentNotificationsController {

	@Autowired
	GetCommentNotificationsService getCommentNotificationsService;
	
	@Autowired
	GetCommentNotificationsCountService getCommentNotificationsCountService;
	
	
	@RequestMapping(method = RequestMethod.POST , value="/getCommentNotifications")
	public Object getCommentNotifications(@RequestBody String token) throws IOException{
		
		getCommentNotificationsService.setToken(token);
		return getCommentNotificationsService.startService();
	}
	
	@RequestMapping(method = RequestMethod.POST , value="/getCommentNotificationsCount")
	public Object getCommentNotificationsCount(@RequestBody String token) throws IOException{
		
		getCommentNotificationsCountService.setToken(token);
		return getCommentNotificationsCountService.startService();
	}
	
	
	
	
}
