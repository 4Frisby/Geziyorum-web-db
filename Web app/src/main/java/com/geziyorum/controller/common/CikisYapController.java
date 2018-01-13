package com.geziyorum.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.dao.CommonDao;

@RestController
public class CikisYapController {
	
	@Autowired
	CommonDao commonDao;

	@RequestMapping(method = RequestMethod.POST, value="/cikisYap")
	public Object cikisYap(@RequestBody String token){
		//commonDao.cikisYap(token);
		return true;
	}	
	
}
