package com.geziyorum.dao;

import com.geziyorum.entity.Session;
import com.geziyorum.entity.User;
import com.geziyorum.exception.FailedLoginException;
import com.geziyorum.exception.WrongUserOrPasswordException;

public interface LoginPageDao {

	Session createSessionByUserId(User user);
	Session getSessionByUserId(Integer id);
	Boolean checkUsernameOrPasswordTrue(User user) throws WrongUserOrPasswordException;
	Boolean checkUserAlreadyHasSession(Integer id);
	Boolean killExistingSession(Integer id);
	User getUserByUsernameAndPassword(String username, String password) throws FailedLoginException;
	Boolean isUserVerified(String username);
	String getUsersToken(Integer userId);

}
