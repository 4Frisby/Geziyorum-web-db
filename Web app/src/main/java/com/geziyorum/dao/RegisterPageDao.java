package com.geziyorum.dao;

import com.geziyorum.entity.User;
import com.geziyorum.exception.MailAlreadyExistException;
import com.geziyorum.exception.UserAlreadyExistException;

public interface RegisterPageDao {
	Boolean createUser(User user) throws UserAlreadyExistException,MailAlreadyExistException;
	Boolean checkUserNameTaken(String username);
	Boolean checkEmailTaken(String email);
	Boolean setVerified(String username);
	
	Boolean createVerifyCode(String username,String verifyCode);
	Boolean checkVerifyCodeExist(String verifyCode);
	Boolean deleteVerifyCode(String verifyCode);
	String getUserByVerifyCode(String verifyCode);
	
	
	Boolean checkForgotPasswordRequestExist(String username);
	Boolean checkForgotPasswordRequestExistByRandomKey(String randomKey);
	Boolean createForgotPasswordRequest(String username,String randomKey);
	Boolean deleteForgotPasswordRequest(String username);
	String getForgotPasswordUserByRandomKey(String randomKey);
	
}
