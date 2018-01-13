package com.geziyorum.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.argumentresolver.TokenId;
import com.geziyorum.dao.CommonDao;


@Service
public class GetTripDetayService implements GeneralProcess {

	@Autowired
	CommonDao commonDao;
	
	TokenId tokenId;

	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		commonDao.checkSessionExistByToken(tokenId.getToken());
		return true;
	}

	@Override
	public Object processService() throws IOException {
		return commonDao.getTripById(tokenId.getId());		
	}

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public TokenId getTokenId() {
		return tokenId;
	}

	public void setTokenId(TokenId tokenId) {
		this.tokenId = tokenId;
	}
	
	
	
	
	
}
