package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenId;
import com.geziyorum.service.GetTripOlusturucuInfoService;

@RestController
public class GetTripOlusturucuInfoController {

	@Autowired
	GetTripOlusturucuInfoService getTripOlusturucuInfoService;
	
    @RequestMapping(method = RequestMethod.POST, value="/getTripOlusturucuInfo")
	public Object getTripOlusturucInfo(@RequestBody TokenId tokenId) throws IOException
	{
		getTripOlusturucuInfoService.setTokenId(tokenId);
		return getTripOlusturucuInfoService.startService();
	}

	
}
