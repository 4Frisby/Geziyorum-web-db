package com.geziyorum.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.ProfilePageDao;
import com.geziyorum.dao.RegisterPageDao;
import com.geziyorum.entity.User;
import com.geziyorum.exception.MailAlreadyExistException;
import com.geziyorum.exception.UserAlreadyExistException;
import com.geziyorum.methods.generals.CommonFuncs;
import com.geziyorum.methods.generals.Constraints;

@Service
public class UserRegisterService implements GeneralProcess{
	
	@Autowired
	private RegisterPageDao registerPageDao;
	
	@Autowired
	ProfilePageDao profilePageDao;
	
	@Autowired
	CommonDao commonDao;
	
	User user;
	
	public UserRegisterService() throws UserAlreadyExistException, MailAlreadyExistException{
		
	}
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
		
	}
	
	@Override
	public Object validateService() throws MailAlreadyExistException, UserAlreadyExistException {
		if(registerPageDao.checkEmailTaken(user.getEmail()))
			throw new MailAlreadyExistException("Girmiş olduğunuz mail adresi zaten mevcuttur.");
		
		if(registerPageDao.checkUserNameTaken(user.getUsername()))
			throw new UserAlreadyExistException("Girmiş olduğunuz üyelik adı zaten mevcuttur");
		
		return true;
	}

	@Override
	public Object processService() throws UserAlreadyExistException, MailAlreadyExistException {
		
		registerPageDao.createUser(user);
		setUser(commonDao.getUserByUsername(getUser().getUsername()));
		String verifyCode = UUID.randomUUID().toString().replace("-","");
		registerPageDao.createVerifyCode(user.getUsername(),verifyCode);
		profilePageDao.createUserProfilePhoto(user);
		
        String sendText = "Üyeliğinizi tamamlayabilmek için lütfen bu adresten onaylayın. \n"
        		+ Constraints.SERVERMAINPAGE + File.separator + "verify" + File.separator + verifyCode;
        
		CommonFuncs.sendMail(user.getEmail(),"Gezi-yorum Aktivasyon",sendText);
		return true;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	
	
	
}
