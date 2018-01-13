package com.geziyorum.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.dao.LoginPageDao;
import com.geziyorum.entity.Session;
import com.geziyorum.entity.User;
import com.geziyorum.exception.FailedLoginException;
import com.geziyorum.exception.WrongUserOrPasswordException;

@Service
public class UserLoginService implements GeneralProcess{

	@Autowired
	LoginPageDao userLoginDaoImpl;
	
	User user; 
	
	public UserLoginService(){

	}

	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
		
	}
	
	@Override
	public Object validateService() throws IOException {
		if(this.userLoginDaoImpl.checkUsernameOrPasswordTrue(user))
			throw new WrongUserOrPasswordException("Kullanıcı adı veya şifre hatası");	
		
		this.user = this.userLoginDaoImpl.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		if(this.userLoginDaoImpl.checkUserAlreadyHasSession(user.getId()))
			this.userLoginDaoImpl.killExistingSession(user.getId());
		
		if(userLoginDaoImpl.isUserVerified(user.getUsername()) == false)
			throw new IOException("Kullanıcı aktive edilmemiş. Lütfen onay mail'inden aktive ediniz.");
		
		return true;
	}

	@Override
	public Object processService() throws IOException {
			Session session = this.userLoginDaoImpl.createSessionByUserId(user);
			return session.getSessionToken();
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	
	
}
