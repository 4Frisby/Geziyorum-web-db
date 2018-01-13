package com.geziyorum.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.geziyorum.entity.Session;
import com.geziyorum.entity.User;
import com.geziyorum.mapper.SessionRowMapper;
import com.geziyorum.mapper.UserRowMapper;

@Repository("mysql2")
public class LoginPageDaoImpl implements LoginPageDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public User getUserByUsernameAndPassword(String username, String password){
		User user= new User();
		final String sql = "select * from users where username = ? and password = ? and hide = 0";
		user = this.jdbcTemplate.queryForObject(sql, new UserRowMapper(),username,password);
		return user;
	}

	@Override
	public Session createSessionByUserId(User user) {
		Session session= new Session();
		java.util.Date date = new Date();
		Object now = new java.sql.Timestamp(date.getTime());
		final String sql = "INSERT INTO sessions(token,user_id,lastly_active_time,created_time) VALUES(?,?,?,?)";
		Object token = user.getUsername() + user.getPassword();
		jdbcTemplate.update(sql,new Object[] 
				{token
				,user.getId()
				,now
				,now});
		session = getSessionByUserId(user.getId());
		return session;
	}

	@Override
	public Boolean checkUsernameOrPasswordTrue(User user) {
		final String sql = "SELECT COUNT(*) FROM users WHERE username = ? and password = ? and hide = 0";
		Integer ifExist = this.jdbcTemplate.queryForObject(sql, Integer.class, user.getUsername(),user.getPassword());
		if(ifExist==0)
			return true;
		else
			return false;
	}
	
	@Override
	public Boolean checkUserAlreadyHasSession(Integer id) {
		final String sql = "SELECT COUNT(*) FROM sessions WHERE user_id = ?";
		Integer ifExist = this.jdbcTemplate.queryForObject(sql, Integer.class, id);
		if(ifExist>=1)
			return true;
		else
			return false;
	}

	@Override
	public Boolean killExistingSession(Integer id) {
		final String sql = "DELETE FROM sessions where user_id = ?";
		jdbcTemplate.update(sql,id);
		return null;
	}

	@Override
	public Session getSessionByUserId(Integer id) {
		final String sql = "SELECT * FROM sessions WHERE user_id = ?";
		Session session = this.jdbcTemplate.queryForObject(sql, new SessionRowMapper(), id);
		return session;
	}

	@Override
	public Boolean isUserVerified(String username) {
		final String sql = "SELECT COUNT(*) FROM users WHERE username = ? and is_verified = 1";
		Integer ifExist = this.jdbcTemplate.queryForObject(sql, Integer.class, username);
		if(ifExist==1)
			return true;
		else
			return false;
	}

	@Override
	public String getUsersToken(Integer userId) {
		final String sql = "select token from sessions where user_id = ?";
		String token = this.jdbcTemplate.queryForObject(sql, String.class, userId);
		return token;
	}





}
