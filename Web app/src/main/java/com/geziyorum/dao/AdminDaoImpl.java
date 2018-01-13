package com.geziyorum.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.geziyorum.entity.Admin;
import com.geziyorum.entity.AdminSession;
import com.geziyorum.entity.ProfilePhoto;
import com.geziyorum.entity.User;
import com.geziyorum.mapper.AdminRowMapper;
import com.geziyorum.mapper.AdminSessionRowMapper;
import com.geziyorum.mapper.ProfilePhotoRowMapper;
import com.geziyorum.mapper.UserRowMapper;

@Repository("adminDao")
public class AdminDaoImpl implements AdminDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Boolean checkUsernameOrPasswordTrue(String username, String password) {
		final String sql = "SELECT COUNT(*) FROM admin WHERE username = ? and password = ?";
		Integer ifExist = this.jdbcTemplate.queryForObject(sql, Integer.class, username,password);
		if(ifExist==0)
			return true;
		else
			return false;
	}

	@Override
	public Admin getAdminByUsernameAndPassword(String username, String password) {
		Admin admin= new Admin();
		final String sql = "select * from admin where username = ? and password = ?";
		admin = this.jdbcTemplate.queryForObject(sql, new AdminRowMapper(),username,password);
		return admin;
	}

	@Override
	public AdminSession createSessionByAdminId(Admin admin) {
		AdminSession session= new AdminSession();
		java.util.Date date = new Date();
		Object now = new java.sql.Timestamp(date.getTime());
		final String sql = "INSERT INTO admin_session(token,admin_id,lastly_active_time,created_time) VALUES(?,?,?,?)";
		Object token = admin.getUsername() + admin.getPassword();
		jdbcTemplate.update(sql,new Object[] 
				{token
				,admin.getAdminId()
				,now
				,now});
		session = getSessionByAdminId(admin.getAdminId());
		return session;
	}

	@Override
	public AdminSession getSessionByAdminId(Integer id) {
		final String sql = "SELECT * FROM admin_session WHERE admin_id = ?";
		AdminSession session = this.jdbcTemplate.queryForObject(sql, new AdminSessionRowMapper(), id);
		return session;
	}

	@Override
	public Boolean checkAdminAlreadyHasSession(Integer id) {
		final String sql = "SELECT COUNT(*) FROM admin_session WHERE admin_id = ?";
		Integer ifExist = this.jdbcTemplate.queryForObject(sql, Integer.class, id);
		if(ifExist>=1)
			return true;
		else
			return false;
	}

	@Override
	public Boolean killExistingSession(Integer id) {
		final String sql = "DELETE FROM admin_session where admin_id = ?";
		jdbcTemplate.update(sql,id);
		return true;
	}

	@Override
	public Boolean checkSessionExistByToken(String token) {
		final String sql = "select count(*) from admin_session where token = ?";
		Integer ifExist = this.jdbcTemplate.queryForObject(sql, Integer.class, token);
		if(ifExist>=1)
			return true;
		else
			return false;
	}

	@Override
	public Boolean checkUserExistByUsername(String username) {
		final String sql = "select count(*) from users where username = ?";
		Integer ifExist = this.jdbcTemplate.queryForObject(sql, Integer.class, username);
		if (ifExist == 1)
			return true;
		else
			return false;
	}

	@Override
	public User getUserByUsername(String username) {
		String sql = "select * from users where username = ?";
		User user = this.jdbcTemplate.queryForObject(sql, new UserRowMapper(), username);
		return user;
	}

	@Override
	public ProfilePhoto getUserProfilePhotoByUsername(User user) {
		ProfilePhoto profilePhoto= new ProfilePhoto();
		final String sql ="select pp.* from profile_photo pp, users u where pp.user_id = u.id and u.id = ?";
		profilePhoto = this.jdbcTemplate.queryForObject(sql, new ProfilePhotoRowMapper(),user.getId());
		return profilePhoto;
	}

	@Override
	public Boolean checkUserSilinebilirMi(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean userSil(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin getAdminByToken(String token) {
		String sql = "select a.* from admin a,admin_session at where a.admin_id = at.admin_id and at.token = ?";
		Admin admin = this.jdbcTemplate.queryForObject(sql, new AdminRowMapper(), token);
		return admin;
	}

}
