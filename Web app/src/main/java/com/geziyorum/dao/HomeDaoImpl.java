package com.geziyorum.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.geziyorum.argumentresolver.HomeGonderi;
import com.geziyorum.argumentresolver.PersonalSharingTrip;
import com.geziyorum.entity.User;
import com.geziyorum.mapper.FriendRowMapper;
import com.geziyorum.mapper.UserRowMapper;
import com.geziyorum.methods.generals.CommonFuncs;

@Repository("homeDao")
public class HomeDaoImpl implements HomeDao{

	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ArrayList<HomeGonderi> getGonderiListOfFriends(Integer userId) {
		ArrayList<HomeGonderi> homeGonderiList = new ArrayList<HomeGonderi>();
		final String sql = "select ps.id as gonderi_id,t.id as trip_id,t.explanation,t.location,t.start_time,t.end_time,u2.username as olusturan_username,u2.id as olusturan_user_id,u1.username as paylasan_username,t.folder_name,ss.shared_time from personal_sharing ps, someone_sharing ss, trip t, users u1, users u2 where ps.trip_id= t.id\n" + 
				" and u1.id = ss.shared_user_id \n" + 
				" and u1.hide = 0 and u2.hide = 0" +
				" and u2.id = ss.owner_id\n" + 
				" and ps.trip_id = t.id\n" + 
				" and ss.personal_sharing_id = ps.id\n" + 
				" and ps.hide = 0 \n" +
				" and ss.personal_sharing_id in\n" + 
				" (select personal_sharing_id \n" + 
				" from someone_sharing\n" + 
				" where shared_user_id \n" + 
				" in( select user_two from friend where user_one = ?)"+
				" and ss.shared_user_id in ( select user_two from friend where user_one = ?)  \n" + 
				" )\n" + 
				"union\n" + 
				"select ps.id as gonderi_id,t.id as trip_id,t.explanation,t.location,t.start_time,t.end_time,u.username as olusturan_username, u.id olusturan_user_id,u.username as paylasan_username,t.folder_name, ps.shared_time from personal_sharing ps, trip t, users u where ps.trip_id= t.id\n" + 
				" and u.id = ps.owner_id \n" + 
				" and u.hide = 0\n" + 
				" and t.id = ps.trip_id\n" + 
				" and ps.hide = 0 \n" +
				" and ps.owner_id\n" + 
				"  in( select user_two from friend where user_one = ?)\n" + 
				" order by shared_time desc";
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,new Object[] 
				{
						userId,
						userId,
						userId
				});
		for(Map row : rows){
			HomeGonderi homeGonderi = new HomeGonderi();
			homeGonderi.setFolderName((String) row.get("folder_name"));
			homeGonderi.setPaylasanUsername((String) row.get("paylasan_username"));
			homeGonderi.setOlusturanUsername((String) row.get("olusturan_username"));
			homeGonderi.setGonderiId((Integer) row.get("gonderi_id"));
			homeGonderi.setSharedTime((Timestamp) row.get("shared_time"));
			homeGonderi.setExplanation((String) row.get("explanation"));
			homeGonderi.setLocation((String) row.get("location"));
			homeGonderi.setStartTime((Timestamp) row.get("start_time"));
			homeGonderi.setEndTime((Timestamp) row.get("end_time"));
			homeGonderi.setTripId((Integer) row.get("trip_id"));
			homeGonderi.setOlusturanUserId((Integer) row.get("olusturan_user_id"));
			homeGonderiList.add(homeGonderi);
		}

		return homeGonderiList;
	}


}
