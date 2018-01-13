package com.geziyorum.controller.common;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.TokenId;
import com.geziyorum.dao.CommonDao;

@RestController
public class DeleteCommentBildirimController {
	
	@Autowired
	CommonDao commonDao;
	
	@RequestMapping(method = RequestMethod.POST , value="/deleteCommentBildirim")
	public Object deleteCommentBildirim(@RequestBody TokenId tokenId) throws IOException{
		commonDao.checkSessionExistByToken(tokenId.getToken());
		commonDao.deleteCommentBildirimById(tokenId.getId());
		
		return true;
	}	
	
	@RequestMapping(method = RequestMethod.POST , value="/deleteAllCommentBildirim")
	public Object deleteAllCommentBildirim(@RequestBody String token) throws IOException{
		commonDao.checkSessionExistByToken(token);
		commonDao.deleteAllCommentBildirimByUserId(commonDao.getUserInfoBySessionToken(token).getId());
		
		return true;
	}	
	
	
}
