package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.entity.SupportMessage;


@RestController
public class SupportMessageController {
	
	@Autowired
	CommonDao commonDao;
	
	@RequestMapping(method = RequestMethod.POST , value="/createSm")
	public Object createSm(@RequestBody SupportMessage sm) throws IOException{
		commonDao.createSupportMessage(sm);
		return true;
	}
	
	@RequestMapping(method = RequestMethod.POST , value="/getSmList")
	public Object createSm(@RequestBody String dummy) throws IOException{
		return commonDao.getSupportMessages();
	}	

}
