package com.geziyorum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.geziyorum.argumentresolver.SearchResultUser;
import com.geziyorum.argumentresolver.TripSearch;
import com.geziyorum.entity.AramaSession;
import com.geziyorum.mapper.AramaSessionRowMapper;

@Repository("aramaDao")
public class AramaDaoImpl implements AramaDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Boolean aramaKaydiOlustur(AramaSession aramaSession) {
		final String sql = "INSERT INTO arama_session(token,aranan_tip,arama_metini) VALUES(?,?,?)";
		jdbcTemplate.update(sql,new Object[] 
				{aramaSession.getToken()
				,aramaSession.getArananTip()
				,aramaSession.getAramaMetni()
				});
		return true;
	}

	@Override
	public Boolean checkAramaKaydi(String token) {
		final String sql = "SELECT count(*) from arama_session where token = ?";
		Integer ifExist = this.jdbcTemplate.queryForObject(sql, Integer.class, token);
		if(ifExist==1)
			return true;
		else
			return false;
	}

	@Override
	public AramaSession aramaKaydiGetir(String token) {
		final String sql = "SELECT * from arama_session where token = ?";
		AramaSession aramaSession = this.jdbcTemplate.queryForObject(sql, new AramaSessionRowMapper(),token);
		return aramaSession;
	}

	@Override
	public Boolean aramaKaydiSil(String token) {
		final String sql = "DELETE FROM arama_session where token = ?";
		jdbcTemplate.update(sql,token);
		return true;
	}

	@Override
	public ArrayList<SearchResultUser> findUserBySearchText(String aramaMetini) {
		aramaMetini = "%" + aramaMetini + "%";
		ArrayList<SearchResultUser> searchResultUserList = new ArrayList<SearchResultUser>();
		final String sql = "select u.username,u.name,u.surname,u.personal_info,pp.photo_name from users u, profile_photo pp"
				 +" where u.id = pp.user_id and u.hide = 0 "
				 + " and (u.username like ? or u.name like ? or u.surname like ?)"; // user_one = user, user_two = friend
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,new Object[]{aramaMetini,aramaMetini,aramaMetini});
		
		for(Map row : rows){
			SearchResultUser searchResultUser = new SearchResultUser(); 
			searchResultUser.setName((String)row.get("name"));
			searchResultUser.setSurname((String)row.get("surname"));
			searchResultUser.setPersonalInfo((String)row.get("personal_info"));
			searchResultUser.setPhotoName((String)row.get("photo_name"));		
			searchResultUser.setUsername((String)row.get("username"));			
			searchResultUserList.add(searchResultUser);
		}
		
		return searchResultUserList;
	}

	@Override
	public Boolean checkSearchedUserExist(String aramaMetini) {

		final String sql = "SELECT count(*) from users where hide = 0 and (username like ? or name like ? or surname like ?)";
	    aramaMetini = "%" + aramaMetini + "%";
		Integer ifExist = this.jdbcTemplate.queryForObject(sql,new Object[] {aramaMetini,aramaMetini,aramaMetini},Integer.class);
		if(ifExist>=1)
			return true;
		else
			return false;
	}

	@Override
	public ArrayList<TripSearch> searchTrip(String aramaMetini, Integer id, String kimlerArasinda, String geziTipi) {
		aramaMetini = "%" + aramaMetini + "%";
		ArrayList<TripSearch> tripSearchList = new ArrayList<TripSearch>();
		List<Map<String, Object>> rows;
		String sql ="select ps.id,u.username,pp.photo_name,t.location,t.explanation,t.folder_name" 
				+" from personal_sharing ps, trip t, profile_photo pp, users u"
				+" where t.id = ps.trip_id"
				+" and u.id = ps.owner_id"
				+" and pp.user_id = u.id" 
				+" and u.hide = 0 and ps.hide = 0"
                +" and (t.explanation like ? or t.location like ?)";
		
		if(kimlerArasinda.equals("Sadece Arkadaşlar")){
			sql += " and u.id in (select user_two from friend where user_one = ?)";
			sql += filtreUygula(geziTipi);
			rows = this.jdbcTemplate.queryForList(sql,new Object[] {aramaMetini,aramaMetini,id});	
		}
		else{
			sql += filtreUygula(geziTipi);
			rows = this.jdbcTemplate.queryForList(sql,new Object[] {aramaMetini,aramaMetini});	
		}
		for(Map row : rows){
			TripSearch tripSearch = new TripSearch(); 
			tripSearch.setExplanation((String) row.get("explanation"));
			tripSearch.setLocation((String) row.get("location"));
			tripSearch.setPersonalSharingId((Integer) row.get("id"));
			tripSearch.setPpName((String) row.get("photo_name"));
			tripSearch.setTripFolderName((String) row.get("folder_name"));
			tripSearch.setUsername((String) row.get("username"));	
			tripSearchList.add(tripSearch);
		}
		
		return tripSearchList;
	}
	
	public static String filtreUygula(String geziTipi){
		String sql= "";
		if(geziTipi.equals("Araba")){
			sql += " and t.trip_type like '%Araba%'";
		}else if(geziTipi.equals("Bisiklet")){
			sql += " and t.trip_type like '%Bisiklet%'";
		}else if(geziTipi.equals("Yürüyüş")){
			sql += " and t.trip_type like '%Yürüyüş%'";
		}else if(geziTipi.equals("Tempolu Yürüyüş")){
			sql += " and t.trip_type like '%Tempolu Yürüyüş%'";
		}
		return sql;
	}
	
	

}
