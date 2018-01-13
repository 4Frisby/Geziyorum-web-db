package com.geziyorum.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.argumentresolver.TripToken;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.entity.TripMedia;
import com.geziyorum.entity.User;

@Service
public class CommonPageGetTripMediaService implements GeneralProcess{

	@Autowired
	CommonDao commonDao;
	
	TripToken tripToken;
	
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		commonDao.checkSessionExistByToken(tripToken.getToken());
		commonDao.checkTripExistById(tripToken.getTripId());
		return true;
	}

	@Override
	public Object processService() throws IOException {
		ArrayList<TripMedia> tripMedias = new ArrayList<TripMedia>();
		User gormekIsteyenUser = commonDao.getUserInfoBySessionToken(tripToken.getToken());
		ArrayList<User> ownerUserList = commonDao.getTripMediaOwners(tripToken.getTripId());
		for(User medyaSahibi : ownerUserList){
			Boolean isOwner = false;
			if(gormekIsteyenUser.getId() == medyaSahibi.getId())
				isOwner = true;			
			Boolean arkadasMi = commonDao.checkAreWeFriends(gormekIsteyenUser, medyaSahibi);
			tripMedias.addAll(commonDao.getTripMediaByTripId(tripToken.getTripId(),arkadasMi,medyaSahibi.getId(),isOwner));
		}


		return tripMedias;
		
	}

	public TripToken getTripToken() {
		return tripToken;
	}

	public void setTripToken(TripToken tripToken) {
		this.tripToken = tripToken;
	}
	
	
	


}
