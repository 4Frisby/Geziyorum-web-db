package com.geziyorum.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.dao.AdminDao;
import com.geziyorum.dao.LoginPageDao;
import com.geziyorum.entity.Admin;
import com.geziyorum.exception.WrongUserOrPasswordException;

@Service
public class AdminLoginService implements GeneralProcess{

	@Autowired
	AdminDao adminDao;
	
	@Autowired
	LoginPageDao loginPageDao;	
	
	Admin admin;
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		if(adminDao.checkUsernameOrPasswordTrue(admin.getUsername(),admin.getPassword()))
			throw new WrongUserOrPasswordException("Kullanıcı adı veya şifre hatası");	
		
		setAdmin(adminDao.getAdminByUsernameAndPassword(admin.getUsername(), admin.getPassword()));
		if(adminDao.checkAdminAlreadyHasSession(admin.getAdminId()))
			adminDao.killExistingSession(admin.getAdminId());
		
		return true;
	}

	@Override
	public Object processService() throws IOException {
		return adminDao.createSessionByAdminId(admin);
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	

}
