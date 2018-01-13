package com.geziyorum.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.FileSystem;
import com.geziyorum.dao.CommonDao;

@RestController
public class GetRootDB {

	@Autowired
	CommonDao commonDao;
	
	 @RequestMapping(method = RequestMethod.POST, value="/getRootDB")
	 public Object getRoot(@RequestBody String token){
		 
		 //commonDao.checkSessionExistByToken(token);
		 return new FileSystem();
		 
	 }
	 
	
	
}
