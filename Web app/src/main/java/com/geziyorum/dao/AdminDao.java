package com.geziyorum.dao;

import com.geziyorum.entity.Admin;
import com.geziyorum.entity.AdminSession;
import com.geziyorum.entity.ProfilePhoto;
import com.geziyorum.entity.User;

public interface AdminDao {

	Boolean checkUsernameOrPasswordTrue(String username, String password);
	Admin getAdminByUsernameAndPassword(String username, String password);
	AdminSession createSessionByAdminId(Admin admin);
	AdminSession getSessionByAdminId(Integer id);
	Boolean checkAdminAlreadyHasSession(Integer id);
	Boolean killExistingSession(Integer id);
	Admin getAdminByToken(String token);
	
	
	Boolean checkSessionExistByToken(String token);
	
	
	Boolean checkUserExistByUsername(String username);
	User getUserByUsername(String username);
	ProfilePhoto getUserProfilePhotoByUsername(User user);
	Boolean checkUserSilinebilirMi(String username);
	Boolean userSil(String username);
	
	
}
