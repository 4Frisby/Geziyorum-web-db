package com.geziyorum.dao;


import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.geziyorum.entity.PersonalSharing;
import com.geziyorum.entity.ProfilePhoto;
import com.geziyorum.entity.User;

@Repository("profilePage")
public interface ProfilePageDao {


	Boolean updateUserGeneralInfo(User user);
	Boolean updateUserPassword(User user);
	Boolean checkUserHasProfilePicture(User user);
	ProfilePhoto getUserProfilePictureByUser(User user);
	Boolean saveUserProfilePhoto(User user,String photoName);
	Boolean createUserProfilePhoto(User user);	
	Boolean deleteExistingProfilePhoto(User user);
}
