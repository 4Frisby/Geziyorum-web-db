package com.geziyorum.dao;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.geziyorum.argumentresolver.TripRequestWithUserInfo;
import com.geziyorum.entity.CurrentLocation;
import com.geziyorum.entity.MediaMetadata;
import com.geziyorum.entity.Path;
import com.geziyorum.entity.Trip;
import com.geziyorum.entity.TripMedia;
import com.geziyorum.entity.TripRequest;
import com.geziyorum.entity.User;

public interface TripDao {
	
	
	
	Integer getTripCountByUser(User user);
	Integer getTripCount();
	Boolean createTripUser(User user,Trip trip);
	Boolean deleteTripUserByUser(User user);
	Boolean deleteAllTripUserByTrip(Trip trip);
	
	
	
	Boolean createTrip(User user, String folderName, String location, String explanation, Timestamp startTime, Timestamp endTime,String geziYapilisSekli);
	Boolean checkTripExistById(Integer tripId) throws IOException;
	Boolean deleteTrip(Trip trip);
	Trip getTripByTripId(Integer tripId);
	Trip getTripByFolderName(String folderName);
	Integer getLatestTripId();
	
	TripMedia getTripMedia(Trip trip);
	Boolean createTripMedia(MediaMetadata mediaMetadata,Trip trip, Integer userId,String ownerUsername);
	Boolean deleteTripMediaByMediaId(TripMedia mediaMetaData);
	Boolean deleteTripMediaByTrip(Trip trip);
	
	Boolean createTripPath(ArrayList<Path> pathList,Trip trip);
	
	Boolean createTripRequest(User user, ArrayList<Integer> userIDS,Integer trip_id, String explanation);
	Boolean createTripWithoutContent(User user,String tripName, Timestamp createdTime);
	Trip getTripByCreationTime(Timestamp time);
	
	
	ArrayList<TripRequestWithUserInfo> getTripRequestByUserId(Integer userId);
	
	Boolean updateExistingTrip(User user, String folderName, String location, String explanation, Timestamp startTime,
			Timestamp endTime, Integer tripId, String geziYapilisSekli);
	Boolean deleteTripRequestById(Integer id);
	
	Boolean createTripUser(Integer tripId, Integer userId);
	
	
	Trip getUsersLatestTrip(Integer userId);
	TripRequest getTripRequestByRequestId(Integer id);
	
	Boolean checkIfHaveCurrentLocation(String username,Integer tripId);
	Boolean createCurrentLocation(CurrentLocation cr);
	Boolean updateCurrentLocation(CurrentLocation cr);
	ArrayList<CurrentLocation> getCurrentLocationsOfUsers(Integer tripId);
	Boolean deleteOneUsersCurrentLocation(String username);
	Boolean deleteAllCurrentLocationOfTrip(Integer tripId);
	
	Boolean updateTripFolder(Integer id, String folderName);
	Boolean checkTripUpdatedByOwner(Integer tripId);
	
	
	
	
	
	
	

}
