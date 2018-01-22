package com.geziyorum.dao;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.geziyorum.argumentresolver.CommentNotification;
import com.geziyorum.argumentresolver.CommentUser;
import com.geziyorum.argumentresolver.FriendRequestsPojo;
import com.geziyorum.argumentresolver.PersonalSharingTrip;
import com.geziyorum.argumentresolver.PersonalSharingTripSharedUser;
import com.geziyorum.argumentresolver.SharedUserInfo;
import com.geziyorum.argumentresolver.UserLikeTime;
import com.geziyorum.argumentresolver.UserShareTime;
import com.geziyorum.entity.Comment;
import com.geziyorum.entity.CommentedYourTripNotification;
import com.geziyorum.entity.FriendRequest;
import com.geziyorum.entity.Likes;
import com.geziyorum.entity.MentionedYouNotification;
import com.geziyorum.entity.PersonalSharing;
import com.geziyorum.entity.SomeoneSharing;
import com.geziyorum.entity.SupportMessage;
import com.geziyorum.entity.Trip;
import com.geziyorum.entity.TripMedia;
import com.geziyorum.entity.TripPath;
import com.geziyorum.entity.User;

public interface CommonDao {

	Boolean checkSessionExistByToken(String token);
	Boolean checkSessionTimeOutByToken(String token);
	Boolean checkUserExistByUsername(String username);
	Boolean checkUserExistById(Integer userId);	
	User getUserInfoBySessionToken(String sessionCookie);
	User getUserByUsername(String username);
	User getUserById(Integer userId);
	Integer getFriendsCountByUsername(String username);
	Integer getSharingCountByUsername(String username);
	
	Boolean createComment(Comment comment) throws IOException;
	Boolean createCommentedYourTripNotification(CommentedYourTripNotification commentedYourTripNotification) throws IOException;
	Boolean createMentionedYouNotification(MentionedYouNotification mentionedYouNotification) throws IOException;
	
	
	Boolean checkPersonalSharingExistById(Integer id) throws IOException;
	PersonalSharing getPersonalSharingById(Integer id) throws IOException;
	Boolean createPersonalSharing(User user,PersonalSharing ps,Timestamp ts) throws IOException;
	Comment getCommentByContentAndId(Comment comment) throws IOException;
	
	
	Boolean checkAreWeFriends(User user1, User user2) throws IOException;
	Integer getUsersFriendCount(Integer userId) throws IOException;
	ArrayList<User> getUserListByUser(User user) throws IOException;
	
	
	PersonalSharing getGonderiById(Integer personalSharingId);
	ArrayList<PersonalSharingTrip> getGonderiListByUser(User user) throws IOException;
	Integer getLikeCountById(Integer personalSharingId);

	
	

	Trip getTripById(Integer tId);
	Boolean checkTripExistById(Integer id);
	ArrayList<TripPath> getTripPathByTripId(Integer tId);
	ArrayList<TripMedia> getTripMediaByTripId(Integer tId, Boolean arkadasMi, Integer userId,Boolean isOwner);
	
	
	ArrayList<PersonalSharingTripSharedUser> getPaylasimListByUser(User user) throws IOException;
	Integer getPaylasimCountByUser(User user);
	Boolean unfriend(User user1, User user2);
	Boolean checkCommentSpam(Comment comment);
	Integer getGonderiCountByUser(User user);
	ArrayList<CommentUser> getCommentListByPersonalSharingId(Integer personalSharingId);
	
	
	FriendRequest getFriendRequestById(Integer rId);
	Boolean createFriendRequest(User senderUser, User recieverUser, Timestamp time) throws IOException ;
	Boolean denyFriendRequest(Integer rId);
	Boolean acceptFriendRequest(FriendRequest fr) throws IOException;
	Boolean CheckFriendRequestExist(User senderUser, User recieverUser) throws IOException ;
	ArrayList<FriendRequestsPojo> getFriendRequestsList(User user);
	Object getBegeniPaylasimCount(Integer id);
	ArrayList<TripPath> getTripPathById(Integer id);
	
	ArrayList<User> getTripKatilimcilarInfo(Integer id);
	User getTripOlusturucuInfoById(Integer id);
	Boolean checkIfGroupTrip(Integer id);
	
	Boolean checkLikeTrip(Integer userId,Integer psId);
	Boolean likeTrip(Integer userId,Integer pSharingId) throws IOException;
	Boolean unlikeTrip(Integer userId,Integer pSharingId) throws IOException;	
	Boolean shareTrip(Integer ownerId,Integer sharedUserId, Integer pSharingId, Timestamp sharedTime);
		
	
	Boolean checkIfILikeTrip(Integer userId, Integer personalSharingId);
	ArrayList<CommentNotification> getUserCommentNotifications(Integer userId);
	Integer getCommentNotificationCountByUserId(Integer id);
	Integer getFriendRequestsCountByUserId(Integer id);
	
	Boolean deleteAllCommentBildirimByUserId(Integer id);
	Boolean deleteCommentBildirimById(Integer id);
	Trip getTripByPsId(Integer psId);
	PersonalSharing getPsByPsId(Integer psId);
	
	
	Boolean cikisYap(String token);
	
	Integer getTripOwnerIdByTripId(Integer tripId);
	Boolean updatePassword(String password, String username);

	ArrayList<Likes> getPsLikes(Integer psId);
	ArrayList<SharedUserInfo> getPsSharedUsers(Integer psId);
	
	Boolean gonderiGizle(Integer psId);
	
	Boolean createSupportMessage(SupportMessage sm);
	ArrayList<SupportMessage> getSupportMessages();
	
	Boolean adminCikisYap(String token);
	
	Boolean checkUserActivated(String username);
	
	ArrayList<User> getTripMediaOwners(Integer tripId);
	User getUserByIdForAdmin(Integer userId);
	PersonalSharing getPersonalSharingByIdForAdmin(Integer id) throws IOException;
	ArrayList<TripMedia> getAllTripMediaForAdmin(Integer tripId);
	Boolean deleteMediaByFilename(String mediaName);
	

}
