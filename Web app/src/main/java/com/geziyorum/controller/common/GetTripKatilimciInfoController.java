package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenId;
import com.geziyorum.service.GetTripKatilimciInfoService;
import com.geziyorum.service.GetTripOlusturucuInfoService;

@RestController
public class GetTripKatilimciInfoController {

	@Autowired
	GetTripKatilimciInfoService getTripKatilimciInfoService;
	
    @RequestMapping(method = RequestMethod.POST, value="/getKatilimciInfo")
	public Object getTripKatilimciInfo(@RequestBody TokenId tokenId) throws IOException
	{
		getTripKatilimciInfoService.setTokenId(tokenId);
		return getTripKatilimciInfoService.startService();
	}
	
}
