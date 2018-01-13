package com.geziyorum.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.argumentresolver.TokenTrip;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.TripDao;
import com.geziyorum.entity.User;

@Service
public class TripCreateDemandService implements GeneralProcess{
	
	@Autowired
	TripDao tripDao;
	
	@Autowired
	CommonDao commonDao;
	
	TokenTrip tokenTrip;
	User ownerUser;
	
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		commonDao.checkSessionExistByToken(tokenTrip.getToken());
		setOwnerUser(commonDao.getUserInfoBySessionToken(tokenTrip.getToken()));
		for(String user : tokenTrip.getUsernames()){
			if(!commonDao.checkUserExistByUsername(user))
				throw new IOException("Eklenen kullanıcı mevcut değildir.");
		}
		return true;
	}

	@Override
	public Object processService() throws IOException {
		ArrayList<Integer> userIDS = new ArrayList<Integer>();
		Integer tripId;
		
		for(String username : tokenTrip.getUsernames()){
			if(!ownerUser.getUsername().equals(username))
			userIDS.add(commonDao.getUserByUsername(username).getId());
		}
			
		Timestamp now = new Timestamp(System.currentTimeMillis());
		tripDao.createTripWithoutContent(getOwnerUser(),tokenTrip.getTripAciklama(),now);
		tripId = tripDao.getUsersLatestTrip(getOwnerUser().getId()).getTripId();
		tripDao.createTripUser(tripId,ownerUser.getId());
		tripDao.createTripRequest(getOwnerUser(),userIDS,tripId,tokenTrip.getArkadaslaraAciklama());
		return tripId;

	}

	public TokenTrip getTokenTrip() {
		return tokenTrip;
	}

	public void setTokenTrip(TokenTrip tokenTrip) {
		this.tokenTrip = tokenTrip;
	}

	public User getOwnerUser() {
		return ownerUser;
	}

	public void setOwnerUser(User ownerUser) {
		this.ownerUser = ownerUser;
	}
	
	
	
	

}
