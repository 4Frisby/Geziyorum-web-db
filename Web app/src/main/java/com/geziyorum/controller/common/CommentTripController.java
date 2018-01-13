package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.CommentPersonalSharingResolver;
import com.geziyorum.service.CommonPageCommentTripService;

@RestController
public class CommentTripController {
	
	
	@Autowired
	CommonPageCommentTripService commonPageCommentTripService;
	
	
    @RequestMapping(method = RequestMethod.POST, value="/commentTrip")
    public Object createComment(@RequestBody CommentPersonalSharingResolver commentTripResolver) throws IOException{
    	commonPageCommentTripService.setCommentPersonalSharingResolver(commentTripResolver);
    	return commonPageCommentTripService.startService();
    	
    }
	

}
