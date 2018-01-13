package com.geziyorum.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.RegisterPageDao;
import com.geziyorum.entity.User;
import com.geziyorum.methods.generals.CommonFuncs;
import com.geziyorum.methods.generals.Constraints;

@Service
public class ForgotPasswordService implements GeneralProcess {

	@Autowired
	RegisterPageDao registerPageDao;
	
	@Autowired
	CommonDao commonDao;

	String username;
	
	User user;
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		setUser(commonDao.getUserByUsername(username));
		return true;
	}

	@Override
	public Object processService() throws IOException {
		if(registerPageDao.checkForgotPasswordRequestExist(user.getUsername())){
			registerPageDao.deleteForgotPasswordRequest(user.getUsername());
		}
		String randomKey = UUID.randomUUID().toString().replace("-","");
		registerPageDao.createForgotPasswordRequest(user.getUsername(), randomKey);
		
        String sendText = "Şifrenizi yeniden oluşturmak için lütfen bu adrese gidiniz. \n"
        		+ Constraints.SERVERMAINPAGE + File.separator + "forgotpassword" + File.separator + randomKey;
		
        CommonFuncs.sendMail(user.getEmail(),"Gezi-yorum Şifremi Unuttum",sendText);
        
		return true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	
}
