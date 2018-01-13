package com.geziyorum.controller.sikayet;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.service.GetGonderiSikayetlerService;
import com.geziyorum.service.GetProfilSikayetlerService;
import com.geziyorum.service.GetYorumSikayetlerService;

@RestController
public class GetSikayetlerController {

	@Autowired
	GetGonderiSikayetlerService getGonderiSikayetlerService;
	
	@Autowired
	GetProfilSikayetlerService getProfilSikayetlerService;
	
	@Autowired
	GetYorumSikayetlerService getYorumSikayetlerService;
	
	@RequestMapping(method = RequestMethod.POST , value="/getGonderiSikayetleri")
	public Object getGonderiSikayetleri(@RequestBody String token) throws IOException{
		getGonderiSikayetlerService.setToken(token);
		return getGonderiSikayetlerService.startService();
	}
	
	@RequestMapping(method = RequestMethod.POST , value="/getProfilSikayetleri")
	public Object getProfilSikayetleri(@RequestBody String token) throws IOException{
		getProfilSikayetlerService.setToken(token);
		return getProfilSikayetlerService.startService();
	}	
	
	@RequestMapping(method = RequestMethod.POST , value="/getYorumSikayetleri")
	public Object getYorumSikayetleri(@RequestBody String token) throws IOException{
		getYorumSikayetlerService.setToken(token);
		return getYorumSikayetlerService.startService();
	}	
	
}
