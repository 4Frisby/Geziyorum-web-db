package com.geziyorum.controller.sikayet;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenGonderiSikayet;
import com.geziyorum.argumentresolver.TokenProfilSikayet;
import com.geziyorum.argumentresolver.TokenYorumSikayet;
import com.geziyorum.service.CreateGonderiSikayetService;
import com.geziyorum.service.CreateProfilSikayetService;
import com.geziyorum.service.CreateYorumSikayetService;

@RestController
public class CreateSikayetController {

	@Autowired
	CreateGonderiSikayetService createGonderiSikayetService;
	
	@Autowired
	CreateProfilSikayetService createProfilSikayetService;
	
	@Autowired
	CreateYorumSikayetService createYorumSikayetService;
	
	@RequestMapping(method = RequestMethod.POST , value="/createGonderiSikayet")
	public Object createGonderiSikayet(@RequestBody TokenGonderiSikayet tokenGonderiSikayet) throws IOException{
		createGonderiSikayetService.setSikayetEdilenUserId(tokenGonderiSikayet.getSikayetEdilenUserId());
		createGonderiSikayetService.setTokenGonderiSikayet(tokenGonderiSikayet);
		return createGonderiSikayetService.startService();
	}
	
	@RequestMapping(method = RequestMethod.POST , value="/createProfilSikayet")
	public Object createProfilSikayet(@RequestBody TokenProfilSikayet tokenProfilSikayet) throws IOException{
		createProfilSikayetService.setTokenProfilSikayet(tokenProfilSikayet);
		return createProfilSikayetService.startService();
	}
	
	@RequestMapping(method = RequestMethod.POST , value="/createYorumSikayet")
	public Object createYorumSikayet(@RequestBody TokenYorumSikayet tokenYorumSikayet) throws IOException{
		createYorumSikayetService.setTokenYorumSikayet(tokenYorumSikayet);
		return createYorumSikayetService.startService();
	}
	
	
	
}
