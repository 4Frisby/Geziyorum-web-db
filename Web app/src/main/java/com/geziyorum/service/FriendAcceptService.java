package com.geziyorum.service;

import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.argumentresolver.TokenId;
import com.geziyorum.argumentresolver.TokenUsername;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.entity.FriendRequest;
import com.geziyorum.entity.User;
import com.geziyorum.methods.generals.CommonFuncs;

@Service
public class FriendAcceptService implements GeneralProcess{

	@Autowired
	CommonDao commonDao;
	
	TokenId tokenId;
	FriendRequest friendRequest;
	
	@Override
	public Object startService() throws IOException {
		friendRequest = new FriendRequest();
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		commonDao.checkSessionExistByToken(tokenId.getToken());
		setFriendRequest(
				commonDao.getFriendRequestById(tokenId.getId())
				);
		return true;
		
	}

	@Override
	public Object processService() throws IOException {
		if(commonDao.denyFriendRequest(tokenId.getId())){
		   commonDao.acceptFriendRequest(friendRequest);
		   User talepYollayan = commonDao.getUserById(friendRequest.getFromId());
		   User kabulEden = commonDao.getUserById(friendRequest.getToId());
		   String aciklama =  "'/Geziyorum/@"+ kabulEden.getUsername() + "' kullanıcısı " + friendRequest.getSendTime()+ " tarihinde yollamış olduğunuz arkadaşlık isteğinizi kabul etti.";
		   CommonFuncs.sendMail(talepYollayan.getEmail(), "Arkadaşlık isteği kabul edildi",aciklama);
		}

		return true;
	}

	public TokenId getTokenId() {
		return tokenId;
	}

	public void setTokenId(TokenId tokenId) {
		this.tokenId = tokenId;
	}

	public FriendRequest getFriendRequest() {
		return friendRequest;
	}

	public void setFriendRequest(FriendRequest friendRequest) {
		this.friendRequest = friendRequest;
	}


	
	
	
}
