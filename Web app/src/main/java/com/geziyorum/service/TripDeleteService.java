package com.geziyorum.service;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.TripDao;
import com.geziyorum.entity.Trip;
import com.geziyorum.entity.User;
import com.geziyorum.methods.generals.Constraints;

@Service
public class TripDeleteService implements GeneralProcess{

	@Autowired
	CommonDao commonDao;
	@Autowired
	TripDao tripDao;
	
	Trip trip;
	String token;
	Integer tripId;
	User user;
	
	@Override
	public Object startService() throws IOException {
		validateService();
		startService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		commonDao.checkSessionExistByToken(getToken());
		setUser(commonDao.getUserInfoBySessionToken(getToken()));
		tripDao.checkTripExistById(getTripId());
		tripDao.getTripByTripId(getTripId());
		return true;
	}

	@Override
	public Object processService() throws IOException {
		String tripFolderPath = Constraints.FULLPATH + File.separator + 
				Constraints.TRIPFOLDER;
		Integer tripFolder = getTrip().getTripId();
		String absoluteFolder= tripFolderPath +File.separator+ Integer.toString(tripFolder);  // -- trip/id
		FileUtils.deleteDirectory(new File(absoluteFolder));
		tripDao.deleteTrip(trip);
		return true;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	
	
	
}
