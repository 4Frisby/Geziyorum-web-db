package com.geziyorum.dao;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.geziyorum.argumentresolver.PersonalSharingTrip;
import com.geziyorum.argumentresolver.TripKatilimcilar;
import com.geziyorum.argumentresolver.TripRequestWithUserInfo;
import com.geziyorum.entity.CurrentLocation;
import com.geziyorum.entity.MediaMetadata;
import com.geziyorum.entity.Path;
import com.geziyorum.entity.Trip;
import com.geziyorum.entity.TripMedia;
import com.geziyorum.entity.TripRequest;
import com.geziyorum.entity.User;
import com.geziyorum.mapper.TripMediaRowMapper;
import com.geziyorum.mapper.TripRequestMapper;
import com.geziyorum.mapper.TripRowMapper;
import com.geziyorum.methods.generals.CommonFuncs;

@Repository("tripDao")
public class TripDaoImpl implements TripDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Integer getTripCountByUser(User user) {
		final String sql = "select count(*) from trip t, trip_users tu, users u where tu.trip_id = t.id and tu.user_id = u.id and u.id = ?";
		Integer count = this.jdbcTemplate.queryForObject(sql, Integer.class, user.getId());
		return count;
	}

	@Override
	public Boolean createTrip(User user, String folderName, String location, String explanation, Timestamp startTime, Timestamp endTime, String geziYapilisSekli) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		final String sql = "INSERT INTO trip(folder_name,creator_user_id,location,explanation,start_time,end_time,trip_type,created_time,is_updated) VALUES(?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql,new Object[] 
				{folderName
				,user.getId()
				,location
				,explanation
				,startTime
				,endTime
				,geziYapilisSekli
				,now
				,1});
		return true;
	}

	@Override
	public Boolean updateExistingTrip(User user, String folderName, String location, String explanation, Timestamp startTime, Timestamp endTime,Integer tripId, String geziYapilisSekli) {
		final String sql = "UPDATE trip SET folder_name = ?, creator_user_id = ?, location = ?, explanation = ?, start_time = ?, end_time=?, trip_type = ?, is_updated = 1 where id = ?";
		jdbcTemplate.update(sql,new Object[] 
				{folderName
				,user.getId()
				,location
				,explanation
				,startTime
				,endTime
				,geziYapilisSekli
				,tripId});
		return true;
	}	
	
	// trip cascade delete tanımlı olduğundan users da trip media'da otomatik silinir.
	@Override
	public Boolean deleteTrip(Trip trip) { 
		final String sql = "DELETE FROM trip WHERE id = ?";
		jdbcTemplate.update(sql,new Object[] 
				{trip.getTripId()});
		return true;
	}

	@Override
	public Trip getTripByTripId(Integer tripId) {
		final String sql = "Select * from trip where id = ?";
		Trip trip = this.jdbcTemplate.queryForObject(sql, new TripRowMapper(), tripId);
		return trip;
	}

	@Override
	public Boolean checkTripExistById(Integer tripId) throws IOException {
		final String sql = "select count(*) from trip where id = ?";
		Integer count = this.jdbcTemplate.queryForObject(sql, Integer.class, tripId);
		if(count >=1 )
			return true;
		else
			throw new IOException("Böyle bir gezi mevcut değildir");
	}

	@Override
	public Boolean createTripMedia(MediaMetadata mediaMetadata,Trip trip,Integer userId,String creatorUsername) {
		Integer type = CommonFuncs.getType(mediaMetadata.getType());
		final String sql = "INSERT INTO trip_media(type,file_name,longitude,latitude,altitude,privacy_type,trip_id,note,user_id,creator_username) VALUES(?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql,new Object[] 
				{type
				,mediaMetadata.getPath()
				,mediaMetadata.getLongitude()
				,mediaMetadata.getLatitude()
				,mediaMetadata.getAltitude()
				,mediaMetadata.getPrivacyType()
				,trip.getTripId()
				,mediaMetadata.getNote()
				,userId
				,creatorUsername
				});
		return true;
	}

	@Override
	public TripMedia getTripMedia(Trip trip) {
		final String sql = "Select * from trip t, trip_media tm where t.id = tm.trip_id and t.id = ?";
		TripMedia tripMedia = this.jdbcTemplate.queryForObject(sql, new TripMediaRowMapper(), trip.getTripId());
		return tripMedia;
	}

	@Override
	public Boolean deleteTripMediaByMediaId(TripMedia tripMedia) {
		final String sql = "DELETE FROM trip_media WHERE id = ?";
		jdbcTemplate.update(sql,new Object[] 
				{tripMedia.getId()});
		return true;
	}

	@Override
	public Boolean deleteTripMediaByTrip(Trip trip) {
		final String sql = "DELETE FROM trip_media tm, trip t WHERE tm.trip_id = t.id and t.id = ?";
		jdbcTemplate.update(sql,new Object[] 
				{trip.getTripId()});
		return null;
	}

	@Override
	public Trip getTripByFolderName(String folderName) {
		final String sql = "Select * from trip where folder_name = ?";
		Trip trip = this.jdbcTemplate.queryForObject(sql, new TripRowMapper(), folderName);
		return trip;
	}

	@Override
	public Boolean createTripUser(User user, Trip trip) {
		final String sql = "insert into trip_users(trip_id,user_id) values(?,?)";
		jdbcTemplate.update(sql,new Object[] 
				{trip.getTripId()
				,user.getId()});
		return true;

	}

	@Override
	public Boolean deleteTripUserByUser(User user) {
		final String sql = "DELETE FROM trip_users tu, users u WHERE u.id= tu.user_id and u.id = ?";
		jdbcTemplate.update(sql,new Object[] 
				{user.getId()});
		return true;
	}

	@Override
	public Boolean deleteAllTripUserByTrip(Trip trip) {
		final String sql = "DELETE FROM trip_users tu, trip t where t.id = tu.trip_id where t.id = ?";
		jdbcTemplate.update(sql,new Object[] 
				{trip.getTripId()});
		return true;
	}

	@Override
	public Integer getLatestTripId() {
		String sql = "SELECT max(id) FROM trip";
		Integer lastIndex = this.jdbcTemplate.queryForObject(sql, Integer.class);
		return lastIndex;
	}

	@Override
	public Boolean createTripPath(ArrayList<Path> pathList,Trip trip) {
		final String sql = "insert into trip_path(latitude,longitude,altitude,trip_id) values(?,?,?,?)";
		for(int i=0; i<pathList.size(); i++){
			Path path = pathList.get(i);
			jdbcTemplate.update(sql,new Object[] 
					{path.getLatitude(),
					 path.getLongitude(),
					 path.getAltitude(),
					 trip.getTripId()});
		}
		return true;
	}

	@Override
	public Integer getTripCount() {
		String sql = "SELECT count(*) FROM trip";
		Integer count = this.jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public Boolean createTripRequest(User user, ArrayList<Integer> userIDS,Integer trip_id, String explanation) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		for(Integer userId : userIDS){
			final String sql = "insert into trip_request(creator_user_id,katilimci_user_id,trip_id,explanation,creation_time) values(?,?,?,?,?)";
			jdbcTemplate.update(sql,new Object[] 
					{user.getId()
					,userId
					,trip_id
					,explanation
					,now
					});
		}

		return true;
	}

	@Override
	public Boolean createTripWithoutContent(User user, String tripAciklama,Timestamp createdTime) {
		final String sql = "insert into trip(creator_user_id,explanation,created_time,is_updated) values(?,?,?,?)";
		jdbcTemplate.update(sql,new Object[] 
				{user.getId()
				,tripAciklama
				,createdTime
				,0});
		return true;
	}
	
	@Override
	public Trip getTripByCreationTime(Timestamp time){
		String sql = "SELECT * FROM trip where created_time = ?";
		String x = time.toString();
		String[] a = x.split("\\.");
		String myTime = a[0];		
		Trip trip = this.jdbcTemplate.queryForObject(sql, new TripRowMapper(),myTime);
		return trip;
	}

	@Override
	public ArrayList<TripRequestWithUserInfo> getTripRequestByUserId(Integer userId) {
		String sql = "SELECT tr.*,u.name,u.surname,u.username FROM trip_request tr,users u where tr.creator_user_id = u.id and katilimci_user_id = ?";
		
		ArrayList<TripRequestWithUserInfo> tripRequestWithUserInfoList = new ArrayList<TripRequestWithUserInfo>();
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,userId);
		for(Map row : rows){
			TripRequestWithUserInfo tripRequestWithUserInfo = new TripRequestWithUserInfo();
			tripRequestWithUserInfo.setCreatorUserId( (Integer) row.get("creator_user_id"));
			tripRequestWithUserInfo.setExplanation((String) row.get("explanation"));
			tripRequestWithUserInfo.setId((Integer) row.get("trip_request_id"));
			tripRequestWithUserInfo.setKatilimciUserId((Integer) row.get("katilimci_user_id"));
			tripRequestWithUserInfo.setName((String) row.get("name"));
			tripRequestWithUserInfo.setSurname((String) row.get("surname"));
			tripRequestWithUserInfo.setUsername((String) row.get("username"));
			tripRequestWithUserInfo.setTripId((Integer) row.get("trip_id"));
			tripRequestWithUserInfo.setCreationTime((Timestamp) row.get("creation_time"));
			
			Integer existingTripId = (Integer) row.get("trip_id");
			tripRequestWithUserInfo.setDigerKatilimcilar(getKatilimcilar(existingTripId));
	
			tripRequestWithUserInfoList.add(tripRequestWithUserInfo);
		}

		return tripRequestWithUserInfoList;		
	}
	
	ArrayList<TripKatilimcilar> getKatilimcilar(Integer tripId){
		String sql = "select u.name,u.surname,u.username from trip_request tr, users u where tr.katilimci_user_id = u.id and trip_id = ?";
		ArrayList<TripKatilimcilar> tripKatilimcilarList = new ArrayList<TripKatilimcilar>();
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,tripId);
		for(Map row : rows){
			TripKatilimcilar tripKatilimci = new TripKatilimcilar();
			tripKatilimci.setName( (String) row.get("name"));
			tripKatilimci.setSurname( (String) row.get("surname"));
			tripKatilimci.setUsername((String) row.get("username"));
			tripKatilimcilarList.add(tripKatilimci);
		}		
		return tripKatilimcilarList;
	}

	@Override
	public Boolean deleteTripRequestById(Integer id) {
		final String sql = "DELETE FROM trip_request WHERE trip_request_id = ?";
		jdbcTemplate.update(sql,id);
		return true;
	}

	@Override
	public Boolean createTripUser(Integer tripId, Integer userId) {
		final String sql = "insert into trip_users(trip_id,user_id) values(?,?)";
		jdbcTemplate.update(sql,new Object[] 
				{
				 tripId
				,userId
				});
		return true;
	}

	@Override
	public Trip getUsersLatestTrip(Integer userId) {
		String sql = "select * from trip where creator_user_id = ? order by id desc LIMIT 1;";
		Trip trip = this.jdbcTemplate.queryForObject(sql, new TripRowMapper(),userId);
		return trip;
	}

	@Override
	public TripRequest getTripRequestByRequestId(Integer id) {
		String sql = "select * from trip_request where trip_request_id = ?;";
		TripRequest tripRequest = this.jdbcTemplate.queryForObject(sql, new TripRequestMapper(),id);
		return tripRequest;
	}

	@Override
	public Boolean createCurrentLocation(CurrentLocation cr) {
		final String sql = "insert into current_location(trip_id,username,longitude,latitude,altitude) values(?,?,?,?,?)";
		jdbcTemplate.update(sql,new Object[] 
				{cr.getTripId()
				,cr.getUsername()
				,cr.getLongitude()
				,cr.getLatitude()
				,cr.getAltitude()
				});
		return true;
	}

	@Override
	public Boolean updateCurrentLocation(CurrentLocation cr) {
		final String sql = "UPDATE current_location SET longitude = ?, latitude=?, altitude = ? where trip_id = ? and username = ?";
		jdbcTemplate.update(sql,new Object[] 
				{cr.getLongitude()
				,cr.getLatitude()
				,cr.getAltitude()
				,cr.getTripId()
				,cr.getUsername()
				});
		return true;
	}

	@Override
	public ArrayList<CurrentLocation> getCurrentLocationsOfUsers(Integer tripId) {
		String sql = "SELECT * FROM current_location where trip_id = ?";
		
		ArrayList<CurrentLocation> currentLocationList = new ArrayList<CurrentLocation>();
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,tripId);
		for(Map row : rows){
			CurrentLocation currentLocation = new CurrentLocation();
			currentLocation.setAltitude((Double) row.get("altitude"));
			currentLocation.setId((Integer) row.get("id"));
			currentLocation.setLatitude((Double) row.get("latitude"));
			currentLocation.setLongitude((Double) row.get("longitude"));
			currentLocation.setTripId((Integer) row.get("trip_id"));
			currentLocation.setUsername((String) row.get("username"));
			currentLocationList.add(currentLocation);
		}
		return currentLocationList;		
	}

	@Override
	public Boolean deleteOneUsersCurrentLocation(String username) {
		final String sql = "DELETE FROM current_location WHERE username= ?";
		jdbcTemplate.update(sql,username);
		return true;
	}

	@Override
	public Boolean deleteAllCurrentLocationOfTrip(Integer tripId) {
		final String sql = "DELETE FROM current_location WHERE trip_id= ?";
		jdbcTemplate.update(sql,tripId);
		return true;
	}

	@Override
	public Boolean checkIfHaveCurrentLocation(String username, Integer tripId) {
		String sql = "SELECT count(*) FROM current_location where username = ? and trip_id = ?";
		Integer count = this.jdbcTemplate.queryForObject(sql, Integer.class,username,tripId);
		if(count == 1)
			return true;
		else
			return false;
	}

	@Override
	public Boolean updateTripFolder(Integer id, String folderName) {
		final String sql = "UPDATE trip SET folder_name = ? where id = ?";
		jdbcTemplate.update(sql,new Object[] {folderName,id});
		return true;
	}

	@Override
	public Boolean checkTripUpdatedByOwner(Integer tripId) {
		String sql = "SELECT count(*) FROM trip where is_updated = 1 and id = ?";
		Integer count = this.jdbcTemplate.queryForObject(sql, Integer.class,tripId);
		if(count == 1)
			return true;
		else
			return false;
	}
	
	

}
