package com.geziyorum.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.geziyorum.entity.User;
import com.geziyorum.exception.MailAlreadyExistException;
import com.geziyorum.exception.UserAlreadyExistException;

@Repository("mysql3")
public class RegisterPageDaoImpl implements RegisterPageDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Boolean createUser(User user) throws UserAlreadyExistException, MailAlreadyExistException {		
		java.util.Date date = new Date();
		Object now = new java.sql.Timestamp(date.getTime());
		try{
			final String sql = "INSERT INTO users(username,password,name,surname,email,created_at) VALUES(?,?,?,?,?,?)";
			jdbcTemplate.update(sql,new Object[] 
					{user.getUsername()
					,user.getPassword()
					,user.getName()
					,user.getSurname()
					,user.getEmail()
					,now});			
		}catch(Exception ex){
			throw ex;
		}
		return true;
	}

	@Override
	public Boolean checkUserNameTaken(String username) {
		final String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
		Integer ifExist = this.jdbcTemplate.queryForObject(sql, Integer.class, username);
		if(ifExist==1)
			return true;
		else
			return false;
		
	}

	@Override
	public Boolean checkEmailTaken(String email) {
		final String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
		Integer ifExist = this.jdbcTemplate.queryForObject(sql, Integer.class, email);
		if(ifExist==1)
			return true;
		else
			return false;
	}

	@Override
	public Boolean createVerifyCode(String username, String verifyCode) {
		final String sql = "INSERT INTO verify_user(username,verify_code) VALUES(?,?)";
		jdbcTemplate.update(sql,new Object[] 
				{username
				,verifyCode
				});	
		return true;
	}

	@Override
	public Boolean setVerified(String username) {
		final String sql = "update users set is_verified=1 where username = ?";
			this.jdbcTemplate.update(sql,username);
		return true;
	}

	@Override
	public Boolean checkVerifyCodeExist(String verifyCode) {
		final String sql = "SELECT COUNT(*) FROM verify_user WHERE verify_code = ?";
		Integer ifExist = this.jdbcTemplate.queryForObject(sql, Integer.class, verifyCode);
		if(ifExist==1)
			return true;
		else
			return false;
	}

	@Override
	public Boolean deleteVerifyCode(String verifyCode) {
		final String sql = "DELETE FROM verify_user where verify_code = ?";
		jdbcTemplate.update(sql,verifyCode);
		return true;
	}

	@Override
	public String getUserByVerifyCode(String verifyCode) {
		final String sql = "SELECT username FROM verify_user WHERE verify_code = ?";
		String username = this.jdbcTemplate.queryForObject(sql, String.class, verifyCode);
		return username;
	}

	@Override
	public Boolean checkForgotPasswordRequestExist(String username) {
		final String sql = "SELECT COUNT(*) FROM forgot_password WHERE username = ?";
		Integer ifExist = this.jdbcTemplate.queryForObject(sql, Integer.class, username);
		if(ifExist==1)
			return true;
		else
			return false;
	}
	
	@Override
	public Boolean checkForgotPasswordRequestExistByRandomKey(String randomKey){
		final String sql = "SELECT COUNT(*) FROM forgot_password WHERE random_key = ?";
		Integer ifExist = this.jdbcTemplate.queryForObject(sql, Integer.class, randomKey);
		if(ifExist==1)
			return true;
		else
			return false;
	}

	@Override
	public Boolean createForgotPasswordRequest(String username, String randomKey) {
		final String sql = "INSERT INTO forgot_password(username,random_key) VALUES(?,?)";
		jdbcTemplate.update(sql,new Object[] 
				{username
				,randomKey
				});	
		return true;
	}

	@Override
	public Boolean deleteForgotPasswordRequest(String username) {
		final String sql = "DELETE FROM forgot_password where username = ?";
		jdbcTemplate.update(sql,username);
		return true;
	}

	@Override
	public String getForgotPasswordUserByRandomKey(String randomKey) {
		final String sql = "SELECT username FROM forgot_password WHERE random_key = ?";
		String username = this.jdbcTemplate.queryForObject(sql, String.class, randomKey);
		return username;
	}

}
