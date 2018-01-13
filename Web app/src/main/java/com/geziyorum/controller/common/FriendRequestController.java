package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenId;
import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.service.CheckFriendRequestExistService;
import com.geziyorum.service.FriendAcceptService;
import com.geziyorum.service.FriendDenyService;
import com.geziyorum.service.FriendRequestService;
import com.geziyorum.service.GetFriendRequestsCountService;
import com.geziyorum.service.GetFriendRequestsService;


@RestController
public class FriendRequestController {
	
	@Autowired
	FriendRequestService friendRequestService;
	
	@Autowired
	FriendAcceptService friendAcceptService;
	
	@Autowired
	FriendDenyService friendDenyService;
	
	@Autowired
	CheckFriendRequestExistService checkFriendRequestExistService;
	
	@Autowired
	GetFriendRequestsService getFriendRequestsService;
	
	@Autowired
	GetFriendRequestsCountService getFriendRequestsCountService;

	@RequestMapping(method = RequestMethod.POST , value="/friendRequest")
	public Object friendRequest(@RequestBody TokenUsername tokenUsername) throws IOException{
		friendRequestService.setTokenUsername(tokenUsername);
		return friendRequestService.startService();
	}
	
	@RequestMapping(method = RequestMethod.POST , value="/acceptFriend")
	public Object acceptFriend(@RequestBody TokenId tokenId) throws IOException{
		friendAcceptService.setTokenId(tokenId);
		return friendAcceptService.startService();
	}
	
	@RequestMapping(method = RequestMethod.POST , value="/denyFriend")
	public Object denyFriend(@RequestBody TokenId tokenId) throws IOException{
		friendDenyService.setTokenId(tokenId);
		return friendDenyService.startService();
	}
	
	@RequestMapping(method = RequestMethod.POST , value="/checkFriendRequestExist")
	public Object checkFriendRequestExist(@RequestBody TokenUsername tokenUsername) throws IOException{
		checkFriendRequestExistService.setTokenUsername(tokenUsername);
		return checkFriendRequestExistService.startService();
	}
	
	@RequestMapping(method = RequestMethod.POST , value="/getFriendRequests")
	public Object getFriendRequests(@RequestBody TokenUsername tokenUsername) throws IOException{
		getFriendRequestsService.setTokenUsername(tokenUsername);
		return getFriendRequestsService.startService();
	}
	
	@RequestMapping(method = RequestMethod.POST , value="/getFriendRequestsCount")
	public Object getFriendRequestsCount(@RequestBody String token) throws IOException{
		getFriendRequestsCountService.setToken(token);
		return getFriendRequestsCountService.startService();
	}	
	
	
	
}
