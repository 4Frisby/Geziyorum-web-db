package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.dao.CommonDao;

@RestController
public class GetPsInfo {

	@Autowired
	CommonDao commonDao;
	
	@RequestMapping(method = RequestMethod.POST , value="/getPsInfoByPSID")
	public Object getPsInfoByPSID(@RequestBody Integer psId) throws IOException{
		return commonDao.getPsByPsId(psId);
	}
	
}
