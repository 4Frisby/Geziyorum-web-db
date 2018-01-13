package com.geziyorum.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.ProfilePageDao;
import com.geziyorum.entity.User;
import com.geziyorum.exception.WrongPasswordException;

@Service
public class ProfilePageChangeUserPasswordService implements GeneralProcess {

	@Autowired
	ProfilePageDao profilePageDao; 
	
	@Autowired
	CommonDao commonDao;
	
	User user;
	String token;
	String oldPassword;
	String newPassword;

	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		setUser(commonDao.getUserInfoBySessionToken(token));
		if(!getUser().getPassword().equals(getOldPassword()))
			throw new WrongPasswordException("Eski şifrenizi hatalı girdiniz.");
		if(getOldPassword().equals(getNewPassword()) )
			throw new IOException("Eski ve yeni şifreniz farklı olmalıdır.");

		
			return true;
	}

	@Override
	public Object processService() throws IOException {
		getUser().setPassword(getNewPassword());
		profilePageDao.updateUserPassword(getUser());
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	

	
}
