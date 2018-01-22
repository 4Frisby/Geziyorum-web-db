package com.geziyorum.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
import com.geziyorum.entity.LikeShare;
import com.geziyorum.entity.Likes;
import com.geziyorum.entity.MentionedYouNotification;
import com.geziyorum.entity.PersonalSharing;
import com.geziyorum.entity.SomeoneSharing;
import com.geziyorum.entity.SupportMessage;
import com.geziyorum.entity.Trip;
import com.geziyorum.entity.TripMedia;
import com.geziyorum.entity.TripPath;
import com.geziyorum.entity.User;
import com.geziyorum.mapper.CommentRowMapper;
import com.geziyorum.mapper.FriendRequestRowMapper;
import com.geziyorum.mapper.FriendRowMapper;
import com.geziyorum.mapper.PersonalSharingRowMapper;
import com.geziyorum.mapper.TripMediaRowMapper;
import com.geziyorum.mapper.TripPathRowMapper;
import com.geziyorum.mapper.TripRowMapper;
import com.geziyorum.mapper.UserRowMapper;
import com.geziyorum.methods.generals.CommonFuncs;
import com.geziyorum.methods.generals.Constraints;

@Repository("common")
public class CommonDaoImpl implements CommonDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Boolean checkSessionExistByToken(String token) {
		final String sql = "select count(*) from sessions where sessions.token = ?";
		Integer ifExist = this.jdbcTemplate.queryForObject(sql, Integer.class, token);
		if (ifExist >= 1)
			return true;
		else
			return false;
	}

	@Override
	public Boolean checkSessionTimeOutByToken(String token) {
		// Timeout kullanacak mıyız belli olduğunda burası doldurulacak.
		return null;
	}

	@Override
	public User getUserInfoBySessionToken(String sessionCookie) {
		User user = new User();
		final String sql = "select u.* from users u, sessions s where s.user_id = u.id and u.hide = 0 and s.token = ?";
		user = this.jdbcTemplate.queryForObject(sql, new UserRowMapper(), sessionCookie);
		return user;
	}

	@Override
	public Boolean checkUserExistByUsername(String username) {
		final String sql = "select count(*) from users where hide=0 and username = ?";
		Integer ifExist = this.jdbcTemplate.queryForObject(sql, Integer.class, username);
		if (ifExist >= 1)
			return true;
		else
			return false;
	}

	@Override
	public User getUserByUsername(String username) {
		String sql = "select * from users where hide=0 and username = ?";
		User user = this.jdbcTemplate.queryForObject(sql, new UserRowMapper(), username);
		return user;
	}

	@Override
	public Integer getFriendsCountByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getSharingCountByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean checkUserExistById(Integer userId) {
		final String sql = "select count(*) from users where hide= 0 and id = ?";
		Integer ifExist = this.jdbcTemplate.queryForObject(sql, Integer.class, userId);
		if (ifExist >= 1)
			return true;
		else
			return false;
	}

	@Override
	public User getUserById(Integer userId) {
		final String sql = "select * from users where hide = 0 and id = ?";
		User user = this.jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId);
		return user;
	}
	
	@Override
	public User getUserByIdForAdmin(Integer userId) {
		final String sql = "select * from users where id = ?";
		User user = this.jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId);
		return user;
	}	

	@Override
	public Boolean createComment(Comment comment) throws IOException {
		Object now = comment.getSendTime();
		try {
			final String sql = "INSERT INTO comment(personal_sharing_id,content,send_time,user_id) VALUES(?,?,?,?)";
			jdbcTemplate.update(sql,
					new Object[] { comment.getPersonalSharingId(), comment.getContent(), now, comment.getUserId() });
		} catch (Exception ex) {
			throw new IOException("Yorum yapılırken hata oluştu.");
		}
		return true;
	}

	@Override
	public Boolean createCommentedYourTripNotification(CommentedYourTripNotification commentedYourTripNotification)
			throws IOException {
		Object now = commentedYourTripNotification.getCommentTime();
		try {
			final String sql = "INSERT INTO commented_your_trip_notification(user_id_from,user_id_to,personal_sharing_id,comment_time,comment_id) VALUES(?,?,?,?,?)";
			jdbcTemplate.update(sql,
					new Object[] { commentedYourTripNotification.getUserFromId(),
							commentedYourTripNotification.getUserToId(),
							commentedYourTripNotification.getPersonalSharingId(), now,
							commentedYourTripNotification.getCommentId() });
		} catch (Exception ex) {
			throw new IOException("Senin trip'ine yorum yapıldı not. oluşturulurken hata oluştu.");
		}
		return true;
	}

	@Override
	public Boolean createMentionedYouNotification(MentionedYouNotification mentionedYouNotification)
			throws IOException {
		Object now = mentionedYouNotification.getMentionTime();
		try {
			final String sql = "INSERT INTO mentioned_you_notification(user_id_from,user_id_to,personal_sharing_id,mentioned_time,comment_id) VALUES(?,?,?,?,?)";
			jdbcTemplate.update(sql,
					new Object[] { mentionedYouNotification.getUserFromId(), mentionedYouNotification.getUserToId(),
							mentionedYouNotification.getPersonalSharingId(), now,
							mentionedYouNotification.getCommentId() });
		} catch (Exception ex) {
			throw new IOException("X trip'inde senden bahsetti'yi DB'ye yazarken hata oluştu.");
		}
		return true;
	}

	@Override
	public Boolean checkPersonalSharingExistById(Integer id) throws IOException {
		final String sql = "select count(*) from personal_sharing where hide=0 and id = ?";
		Integer ifExist = this.jdbcTemplate.queryForObject(sql, Integer.class, id);
		if (ifExist >= 1)
			return true;
		else
			return false;
	}

	@Override
	public PersonalSharing getPersonalSharingById(Integer id) throws IOException {
		final String sql = "select * from personal_sharing where hide=0 and id = ?";
		PersonalSharing personalSharing = this.jdbcTemplate.queryForObject(sql, new PersonalSharingRowMapper(), id);
		return personalSharing;
	}
	
	@Override
	public PersonalSharing getPersonalSharingByIdForAdmin(Integer id) throws IOException {
		final String sql = "select * from personal_sharing where id = ?";
		PersonalSharing personalSharing = this.jdbcTemplate.queryForObject(sql, new PersonalSharingRowMapper(), id);
		return personalSharing;
	}	

	@Override
	public Comment getCommentByContentAndId(Comment comment) throws IOException {
		final String sql = "select * from comment where content = ? and hide = 0 and personal_sharing_id = ?";
		Comment commentFromDB = this.jdbcTemplate.queryForObject(sql, new CommentRowMapper(),
				new Object[] { comment.getContent(), comment.getPersonalSharingId() });
		return commentFromDB;
	}

	@Override
	public Boolean checkAreWeFriends(User user1, User user2) throws IOException {
		final String sql = "select count(*) from friend f, users u where f.user_two = u.id and u.hide = 0 and user_one = ? and user_two = ?";
		Integer val = this.jdbcTemplate.queryForObject(sql, Integer.class,
				new Object[] { user1.getId(), user2.getId() });

		Integer val2 = this.jdbcTemplate.queryForObject(sql, Integer.class,
				new Object[] { user2.getId(), user1.getId() });

		if (val == 1 || val2 == 1)
			return true;

		return false;

	}

	@Override
	public Integer getUsersFriendCount(Integer userId) throws IOException {
		final String sql = "select count(*) from friend f, users u where f.user_two = u.id and u.hide = 0 and user_one = ?;"; //
		Integer count = this.jdbcTemplate.queryForObject(sql, Integer.class, userId);
		return count;
	}

	@Override
	public ArrayList<User> getUserListByUser(User user) throws IOException {
		ArrayList<User> friendsList = new ArrayList<User>();
		final String sql = "select f.* from friend f, users u where f.user_two = u.id and u.hide = 0 and user_one = ?"; // user_one
																														// =
																														// user,
																														// user_two
																														// =
																														// friend
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql, user.getId());

		for (Map row : rows) {
			User userDB = new User();
			Integer id1 = (Integer) row.get("user_one");
			Integer id2 = (Integer) row.get("user_two");
			if (id1 == user.getId())
				friendsList.add(getUserById(id2));
			if (id2 == user.getId())
				friendsList.add(getUserById(id1));
		}

		return friendsList;

	}

	@Override
	public ArrayList<PersonalSharingTrip> getGonderiListByUser(User user) throws IOException {
		ArrayList<PersonalSharingTrip> gonderiList = new ArrayList<PersonalSharingTrip>();
		final String sql = "select ps.*,t.folder_name from personal_sharing ps, trip t where ps.trip_id= t.id and ps.owner_id = ? and ps.hide = 0 order by ps.id desc"; // sağda
																																										// veya
																																										// soldaysa
																																										// sayısını
																																										// getir.
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql, user.getId());
		for (Map row : rows) {
			PersonalSharingTrip personalSharingTrip = new PersonalSharingTrip();
			personalSharingTrip.setPersonalSharingId((Integer) row.get("id"));
			personalSharingTrip.setOwnerId((Integer) row.get("owner_id"));
			personalSharingTrip.setTripId((Integer) row.get("trip_id"));
			personalSharingTrip.setSharedTime((Timestamp) row.get("shared_time"));
			personalSharingTrip.setFolderName((String) row.get("folder_name"));
			personalSharingTrip.setWhoCanSee(CommonFuncs.TipCozumle((Integer) row.get("who_can_see")));
			gonderiList.add(personalSharingTrip);
		}

		return gonderiList;
	}

	@Override
	public ArrayList<PersonalSharingTripSharedUser> getPaylasimListByUser(User user) throws IOException {
		ArrayList<PersonalSharingTripSharedUser> gonderiList = new ArrayList<PersonalSharingTripSharedUser>();
		final String sql = "select ps.*,ss.shared_time as paylasilma_zamani,t.folder_name,u.username from someone_sharing ss, personal_sharing ps, users u, trip t where ss.personal_sharing_id = ps.id and ps.trip_id= t.id and ss.shared_user_id = u.id and shared_user_id = ? and ps.hide = 0 order by ss.shared_time desc";
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql, user.getId());
		for (Map row : rows) {
			PersonalSharingTripSharedUser personalSharingTrip = new PersonalSharingTripSharedUser();
			personalSharingTrip.setPersonalSharingId((Integer) row.get("id"));
			personalSharingTrip.setOwnerId((Integer) row.get("owner_id"));
			personalSharingTrip.setTripId((Integer) row.get("trip_id"));
			personalSharingTrip.setSharedTime((Timestamp) row.get("shared_time"));
			personalSharingTrip.setFolderName((String) row.get("folder_name"));
			personalSharingTrip.setWhoCanSee(CommonFuncs.TipCozumle((Integer) row.get("who_can_see")));
			personalSharingTrip.setSharedUserUsername((String) row.get("username"));
			personalSharingTrip.setPaylasilmaZamani((Timestamp) row.get("paylasilma_zamani"));
			gonderiList.add(personalSharingTrip);
		}

		return gonderiList;
	}

	@Override
	public Trip getTripById(Integer tId) {
		final String sql = "select * from trip where id = ?";
		Trip trip = this.jdbcTemplate.queryForObject(sql, new TripRowMapper(), tId);
		return trip;
	}

	@Override
	public ArrayList<TripPath> getTripPathByTripId(Integer tId) {
		ArrayList<TripPath> tripPathList = new ArrayList<TripPath>();
		final String sql = "select * from trip_path where trip_id = ?"; // sağda
																		// veya
																		// soldaysa
																		// sayısını
																		// getir.
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql, tId);
		for (Map row : rows) {
			TripPath tripPath = new TripPath();
			tripPath.setId((Integer) row.get("id"));
			tripPath.setLatitude((Double) row.get("latitude"));
			tripPath.setLongitude((Double) row.get("longitude"));
			tripPath.setAltitude((Double) row.get("altitude"));
			tripPath.setTripId((Integer) row.get("trip_id"));
			tripPathList.add(tripPath);
		}

		return tripPathList;
	}

	@Override
	public ArrayList<TripMedia> getTripMediaByTripId(Integer tId,Boolean arkadasMi,Integer userId,Boolean isOwner) {
		ArrayList<TripMedia> tripMediaList = new ArrayList<TripMedia>();
		String sql = "select * from trip_media where trip_id = ? and user_id = ?";
		if(!isOwner){
			if(arkadasMi){
				sql += " and privacy_type in(4,5)";
			}else{
				sql += " and privacy_type = 4";
			}			
		}
		
		sql += " order by id desc";

		
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql, new Object[] {tId,userId});
		for (Map row : rows) {
			TripMedia tripMedia = new TripMedia();
			tripMedia.setId((Integer) row.get("id"));
			tripMedia.setLatitude((Double) row.get("latitude"));
			tripMedia.setLongitude((Double) row.get("longitude"));
			tripMedia.setAltitude((Double) row.get("altitude"));
			tripMedia.setType((Integer) row.get("type"));
			tripMedia.setTripId((Integer) row.get("trip_id"));
			tripMedia.setFileName((String) row.get("file_name"));
			tripMedia.setNote((String) row.get("note"));
			tripMedia.setCreatorUsername((String) row.get("creator_username"));
			tripMediaList.add(tripMedia);
		}

		return tripMediaList;
	}

	@Override
	public ArrayList<TripMedia> getAllTripMediaForAdmin(Integer tripId){
		
		ArrayList<TripMedia> tripMediaList = new ArrayList<TripMedia>();		
		String sql = "select * from trip_media where trip_id = ?";		
		
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql, tripId);
		for (Map row : rows) {
			TripMedia tripMedia = new TripMedia();
			tripMedia.setId((Integer) row.get("id"));
			tripMedia.setLatitude((Double) row.get("latitude"));
			tripMedia.setLongitude((Double) row.get("longitude"));
			tripMedia.setAltitude((Double) row.get("altitude"));
			tripMedia.setType((Integer) row.get("type"));
			tripMedia.setTripId((Integer) row.get("trip_id"));
			tripMedia.setFileName((String) row.get("file_name"));
			tripMedia.setNote((String) row.get("note"));
			tripMedia.setCreatorUsername((String) row.get("creator_username"));
			tripMediaList.add(tripMedia);
		}

		return tripMediaList;		
	}
	
	
	@Override
	public PersonalSharing getGonderiById(Integer personalSharingId) {
		final String sql = "select * from personal_sharing where hide=0 and id = ?";
		PersonalSharing personalSharing = this.jdbcTemplate.queryForObject(sql, new PersonalSharingRowMapper(),
				personalSharingId);
		return personalSharing;
	}

	@Override
	public Integer getPaylasimCountByUser(User user) {
		final String sql = "select count(*) from someone_sharing ss, personal_sharing ps where ss.personal_sharing_id = ps.id and ps.hide = 0 and shared_user_id = ?";
		Integer count = this.jdbcTemplate.queryForObject(sql, Integer.class, user.getId());
		return count;
	}

	@Override
	public Integer getLikeCountById(Integer personalSharingId) {
		final String sql = "select count(*) from likes where personal_sharing_id = ?";
		Integer count = this.jdbcTemplate.queryForObject(sql, Integer.class, personalSharingId);
		return count;
	}

	@Override
	public Boolean unfriend(User user1, User user2) {
		final String sql = "DELETE FROM friend where user_one = ? and user_two = ? ";
		jdbcTemplate.update(sql, new Object[] { user1.getId(), user2.getId() });

		jdbcTemplate.update(sql, new Object[] { user2.getId(), user1.getId() });

		return true;
	}

	@Override
	public Boolean createPersonalSharing(User user, PersonalSharing ps, Timestamp ts) throws IOException {
		try {
			final String sql = "INSERT INTO personal_sharing(owner_id,trip_id,shared_time) VALUES(?,?,?)";
			jdbcTemplate.update(sql, new Object[] { user.getId(), ps.getTrip_id(), ts });
		} catch (Exception ex) {
			throw new IOException("Yorum yapılırken hata oluştu.");
		}
		return true;
	}

	@Override
	public Boolean checkTripExistById(Integer id) {
		final String sql = "select count(*) from trip where id = ?";
		Integer ifExist = this.jdbcTemplate.queryForObject(sql, Integer.class, id);
		if (ifExist >= 1)
			return true;
		else
			return false;
	}

	@Override
	public Boolean checkCommentSpam(Comment comment) {
		final String sql = "select count(*) from comment where personal_sharing_id = ? and content = ?";
		Integer ifExist = this.jdbcTemplate.queryForObject(sql, Integer.class,
				new Object[] { comment.getPersonalSharingId(), comment.getContent() });
		if (ifExist >= 1)
			return true;
		else
			return false;
	}

	@Override
	public Integer getGonderiCountByUser(User user) {
		final String sql = "select count(*) from personal_sharing where owner_id = ? and hide = 0";
		Integer count = this.jdbcTemplate.queryForObject(sql, Integer.class, user.getId());
		return count;
	}

	@Override
	public ArrayList<CommentUser> getCommentListByPersonalSharingId(Integer personalSharingId) {
		ArrayList<CommentUser> commentList = new ArrayList<CommentUser>();
		final String sql = "select c.*,username from comment c, users u where c.user_id = u.id and personal_sharing_id=? and c.hide = 0 order by c.id desc";
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql, personalSharingId);
		for (Map row : rows) {
			CommentUser comment = new CommentUser();
			comment.setId((Integer) row.get("id"));
			comment.setPersonalSharingId((Integer) row.get("personal_sharing_id"));
			comment.setContent((String) row.get("content"));
			comment.setSendTime((Timestamp) row.get("send_time"));
			comment.setUserId((Integer) row.get("user_id"));
			comment.setUsername((String) row.get("username"));

			commentList.add(comment);
		}

		return commentList;
	}

	@Override
	public Boolean createFriendRequest(User senderUser, User recieverUser, Timestamp time) throws IOException {
		try {

			if(CheckFriendRequestExist(senderUser, recieverUser))
			{
				throw new Exception("Arkadaşlık talebi zaten oluşturulmuştur.");
			}
			else{
				Date date = new Date();
				final String sql = "INSERT INTO friend_request(from_id,to_id,send_time) VALUES(?,?,?)";
				jdbcTemplate.update(sql, new Object[] { senderUser.getId(), recieverUser.getId(), time });				
			}

		} catch (Exception ex) {
			throw new IOException("Arkadaşlık talebi yapılırken hata oluştu.");
		}
		return true;
	}

	@Override
	public Boolean denyFriendRequest(Integer rId) {
		final String sql = "DELETE FROM friend_request where id = ? ";
		jdbcTemplate.update(sql, rId);
		return true;
	}

	@Override
	public Boolean acceptFriendRequest(FriendRequest fr) throws IOException {

		// friend tablosu -- > user_one = user id, user_two = friend_id
		// 10,20 arkadaş olursa 10,20 ve 20,10 olarak kaydedilir.
		try {
			final String sql = "INSERT INTO friend(user_one,user_two) VALUES(?,?)";
			jdbcTemplate.update(sql, new Object[] { fr.getFromId(), fr.getToId() });
			jdbcTemplate.update(sql, new Object[] { fr.getToId(), fr.getFromId() });
		} catch (Exception ex) {
			throw new IOException("Arkadaşlık oluşturulurken bir hata oluştu.");
		}
		return true;
	}

	@Override
	public FriendRequest getFriendRequestById(Integer rId) {
		final String sql = "select * from friend_request where id = ?";
		FriendRequest friendRequest = this.jdbcTemplate.queryForObject(sql, new FriendRequestRowMapper(), rId);
		return friendRequest;
	}

	@Override
	public Boolean CheckFriendRequestExist(User senderUser, User recieverUser) throws IOException {
		try {
			final String sql = "select count(*) from friend_request fr, users u where u.id = fr.from_id and u.hide = 0 and from_id = ? and to_id = ?";
			Integer ifExist = this.jdbcTemplate.queryForObject(sql, Integer.class,
					new Object[] { senderUser.getId(), recieverUser.getId() });

			Integer ifExist2 = this.jdbcTemplate.queryForObject(sql, Integer.class,
					new Object[] { recieverUser.getId(), senderUser.getId() });

			if (ifExist == 1 || ifExist2 == 1)
				return true;
			else
				return false;
		} catch (Exception ex) {
			throw new IOException("Arkadaşlık isteği talebi sorgulanırken hata alındı.");
		}
	}

	@Override
	public ArrayList<FriendRequestsPojo> getFriendRequestsList(User user) {
		ArrayList<FriendRequestsPojo> friendReqList = new ArrayList<FriendRequestsPojo>();
		final String sql = "select fq.id,fq.send_time,name,surname,username,photo_name from friend_request fq, users u, profile_photo p where  p.user_id = u.id and u.id = fq.from_id and u.hide = 0 and fq.to_id = ?";
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql, user.getId());
		for (Map row : rows) {
			FriendRequestsPojo friendRequestsPojo = new FriendRequestsPojo();
			friendRequestsPojo.setFriendRequestId((Integer) row.get("id"));
			friendRequestsPojo.setName((String) row.get("name"));
			friendRequestsPojo.setSurname((String) row.get("surname"));
			friendRequestsPojo.setUsername((String) row.get("username"));
			friendRequestsPojo.setPpName((String) row.get("photo_name"));
			friendRequestsPojo.setSendTime((Timestamp) row.get("send_time"));
			friendRequestsPojo.setPpName(CommonFuncs.ppResolver(friendRequestsPojo.getPpName()));
			friendReqList.add(friendRequestsPojo);
		}

		return friendReqList;
	}

	@Override
	public Object getBegeniPaylasimCount(Integer id) {
		String sql = "select count(*) from someone_sharing where personal_sharing_id = ?";
		Integer sharingCount = this.jdbcTemplate.queryForObject(sql, Integer.class, id);

		sql = "select count(*) from likes where personal_sharing_id = ?";
		Integer likesCount = this.jdbcTemplate.queryForObject(sql, Integer.class, id);

		LikeShare likeShare = new LikeShare();
		likeShare.setLikeCount(likesCount);
		likeShare.setShareCount(sharingCount);
		return likeShare;

	}

	@Override
	public ArrayList<TripPath> getTripPathById(Integer id) {
		ArrayList<TripPath> tripPathList = new ArrayList<TripPath>();
		String sql = "select * from trip_path where trip_id = ?";
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql, id);
		for (Map row : rows) {
			TripPath tripPath = new TripPath();
			tripPath.setAltitude((Double) row.get("altitude"));
			tripPath.setId((Integer) row.get("id"));
			tripPath.setLatitude((Double) row.get("latitude"));
			tripPath.setLongitude((Double) row.get("longitude"));
			tripPath.setTripId((Integer) row.get("trip_id"));
			tripPathList.add(tripPath);
		}
		return tripPathList;
	}

	@Override
	public ArrayList<User> getTripKatilimcilarInfo(Integer id) {
		ArrayList<User> users = new ArrayList<User>();
		String sql = "select u.* from trip_users tu, users u where tu.user_id = u.id and u.hide = 0 and tu.trip_id = ?";
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql, id);
		for (Map row : rows) {
			User user = new User();
			user.setUsername((String) row.get("username"));
			user.setName((String) row.get("name"));
			user.setSurname((String) row.get("surname"));

			users.add(user);
		}
		return users;
	}

	@Override
	public User getTripOlusturucuInfoById(Integer id) {
		final String sql = "select u.* from users u, trip t where t.creator_user_id = u.id and u.hide = 0 and t.id = ?";
		User user = this.jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
		return user;
	}

	@Override
	public Boolean checkIfGroupTrip(Integer id) {
		final String sql = "select count(*) from trip_users tu, users u where u.id = tu.user_id and u.hide = 0 and trip_id = ?";
		Integer count = this.jdbcTemplate.queryForObject(sql, Integer.class, id);
		if (count > 1)
			return true;
		else
			return false;
	}

	@Override
	public Boolean checkLikeTrip(Integer userId, Integer pSharingId) {
		final String sql = "SELECT COUNT(*) from likes where user_id = ? and personal_sharing_id = ?";
		Integer count = jdbcTemplate.queryForObject(sql,Integer.class, new Object[] { userId, pSharingId });
		if(count == 1)
			return true;
		else
			return false;
	}	
	
	
	@Override
	public Boolean likeTrip(Integer userId, Integer pSharingId) throws IOException {
		if (checkLikeTrip(userId, pSharingId) == true)
			throw new IOException("Gönderiyi zaten beğendiniz.");
		final String sql = "INSERT INTO likes(user_id,personal_sharing_id) VALUES(?,?)";
		jdbcTemplate.update(sql, new Object[] { userId, pSharingId });
		return true;
	}

	@Override
	public Boolean shareTrip(Integer ownerId, Integer sharedUserId, Integer pSharingId, Timestamp sharedTime) {
		final String sql = "INSERT INTO someone_sharing(owner_id,shared_user_id,personal_sharing_id,shared_time) VALUES(?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] { ownerId, sharedUserId, pSharingId, sharedTime });
		return true;
	}

	@Override
	public Boolean checkIfILikeTrip(Integer userId, Integer personalSharingId) {
		final String sql = "select count(*) from likes where user_id = ? and personal_sharing_id = ?";
		Integer count = this.jdbcTemplate.queryForObject(sql, Integer.class,
				new Object[] { userId, personalSharingId });
		if (count == 1)
			return true;
		else
			return false;
	}

	@Override
	public Boolean unlikeTrip(Integer userId, Integer pSharingId) throws IOException {
		if (checkLikeTrip(userId, pSharingId) == false)
			throw new IOException("Zaten beğenmekten vazgeçmiştiniz.");		
		final String sql = "DELETE FROM likes where user_id = ? and personal_sharing_id = ?";
		jdbcTemplate.update(sql, new Object[] { userId, pSharingId });
		return true;
	}

	@Override
	public ArrayList<CommentNotification> getUserCommentNotifications(Integer userId) {
		ArrayList<CommentNotification> commentNotificationList = new ArrayList<CommentNotification>();
		String sql = "select cy.id, u.username, pp.photo_name,t.folder_name,comment_time from "
				+ "commented_your_trip_notification cy, personal_sharing ps,trip t,users u, profile_photo pp "
				+ "where ps.id = cy.personal_sharing_id " + "and cy.user_id_from = u.id " + "and	ps.trip_id = t.id "
				+ "and u.id = pp.user_id and u.hide = 0 and cy.user_id_to = ? order by comment_time";
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql, userId);
		for (Map row : rows) {
			CommentNotification commentNotification = new CommentNotification();
			commentNotification.setCommentTime((Timestamp) row.get("comment_time"));
			commentNotification.setId((Integer) row.get("id"));
			String rawPhoto = (String) row.get("photo_name");
			String photoPath = File.separator + Constraints.DBFOLDER + File.separator + Constraints.PPFOLDER
					+ File.separator + rawPhoto;
			commentNotification.setPhotoName(photoPath);
			String tripFolderName = (String) row.get("folder_name");
			String tripPhotoPath = File.separator + Constraints.DBFOLDER + File.separator + Constraints.TRIPFOLDER
					+ File.separator + tripFolderName + File.separator + "kapak.jpg";
			commentNotification.setTripKapakUrl(tripPhotoPath);
			commentNotification.setCommentYapanUsername((String) row.get("username"));
			commentNotificationList.add(commentNotification);

		}
		return commentNotificationList;
	}

	@Override
	public Integer getCommentNotificationCountByUserId(Integer id) {
		final String sql = "select count(*) from commented_your_trip_notification cu, users u where u.id = cu.user_id_from and u.hide = 0 and user_id_to = ?";
		Integer count = this.jdbcTemplate.queryForObject(sql, Integer.class, id);
		return count;
	}

	@Override
	public Integer getFriendRequestsCountByUserId(Integer id) {
		final String sql = "select count(*) from friend_request fr,users u  where fr.from_id = u.id and u.hide = 0 and to_id = ?";
		Integer count = this.jdbcTemplate.queryForObject(sql, Integer.class, id);
		return count;
	}

	@Override
	public Boolean deleteAllCommentBildirimByUserId(Integer id) {
		final String sql = "delete from commented_your_trip_notification where user_id_to = ?";
		jdbcTemplate.update(sql, id);
		return true;
	}

	@Override
	public Boolean deleteCommentBildirimById(Integer id) {
		final String sql = "delete from commented_your_trip_notification where id = ?";
		jdbcTemplate.update(sql, id);
		return true;
	}

	@Override
	public Trip getTripByPsId(Integer psId) {
		final String sql = "select t.* from personal_sharing ps, trip t where ps.trip_id = t.id and ps.hide = 0 and ps.id = ?";
		Trip trip = this.jdbcTemplate.queryForObject(sql, new TripRowMapper(), psId);
		return trip;
	}

	@Override
	public PersonalSharing getPsByPsId(Integer psId) {
		final String sql = "select * from personal_sharing where hide = 0 and id = ?";
		PersonalSharing ps = this.jdbcTemplate.queryForObject(sql, new PersonalSharingRowMapper(), psId);
		return ps;
	}

	@Override
	public Boolean cikisYap(String token) {
		final String sql = "delete from sessions where token = ?";
		jdbcTemplate.update(sql, token);
		return true;
	}

	@Override
	public Integer getTripOwnerIdByTripId(Integer tripId) {
		final String sql = "select creator_user_id from trip where id = ?";
		Integer userId = this.jdbcTemplate.queryForObject(sql,Integer.class, tripId);
		return userId;
	}

	@Override
	public Boolean updatePassword(String password, String username) {
		final String sql = "update users set password=? where username = ?";
		jdbcTemplate.update(sql,new Object[] 
				{
					password,
					username
				});
		return true;
	}

	@Override
	public ArrayList<Likes> getPsLikes(Integer psId) {
		ArrayList<Likes> likes = new ArrayList<Likes>();
		String sql = "select * from likes where personal_sharing_id = ?";
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql, psId);
		for (Map row : rows) {
			Likes like = new Likes();
			like.setId((Integer) row.get("id"));
			like.setPersonalSharingId((Integer) row.get("personal_sharing_id"));
			like.setUserId((Integer) row.get("user_id"));
			likes.add(like);
		}
		return likes;
	}

	@Override
	public ArrayList<SharedUserInfo> getPsSharedUsers(Integer psId) {
		ArrayList<SharedUserInfo> sharedUserInfoList = new ArrayList<SharedUserInfo>();
		String sql = "select u.username,u.name,u.surname,u.personal_info,ss.shared_time from someone_sharing ss,users u where u.id = ss.shared_user_id and personal_sharing_id = ?";
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql, psId);
		for (Map row : rows) {
			SharedUserInfo sharedUserInfo = new SharedUserInfo();
			sharedUserInfo.setName((String) row.get("name"));
			sharedUserInfo.setPersonalInfo((String) row.get("personal_info"));
			sharedUserInfo.setSharedTime((Timestamp) row.get("shared_time"));
			sharedUserInfo.setSurname((String) row.get("surname"));
			sharedUserInfo.setUsername((String) row.get("username"));
			sharedUserInfoList.add(sharedUserInfo);
		}
		return sharedUserInfoList;
	}

	@Override
	public Boolean gonderiGizle(Integer psId) {
		final String sql = "update personal_sharing set hide=1 where id= ?";
		jdbcTemplate.update(sql,psId);
		return true;
	}

	@Override
	public Boolean createSupportMessage(SupportMessage sm) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		final String sql = "INSERT INTO support_message(name,mail,phone,message,date) VALUES(?,?,?,?,?)";
		jdbcTemplate.update(sql,
				new Object[] { sm.getName(), sm.getMail(),sm.getPhone(), sm.getMessage(),now});
		return true;
	}

	@Override
	public ArrayList<SupportMessage> getSupportMessages() {
		ArrayList<SupportMessage> supportMessageList = new ArrayList<SupportMessage>();
		String sql = "select * from support_message order by id desc";
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);
		for (Map row : rows) {
			SupportMessage sm = new SupportMessage();
			sm.setDate((Timestamp) row.get("date"));
			sm.setId((Integer) row.get("id"));
			sm.setMail((String) row.get("mail"));
			sm.setMessage((String) row.get("message"));
			sm.setName((String) row.get("name"));
			sm.setPhone((String) row.get("phone"));
			supportMessageList.add(sm);
		}
		return supportMessageList;
	}

	@Override
	public Boolean adminCikisYap(String token) {
		final String sql = "delete from admin_session where token = ?";
		jdbcTemplate.update(sql, token);
		return true;
	}

	@Override
	public Boolean checkUserActivated(String username) {
		final String sql = "select count(*) from users where username = ? and is_verified = 1";
		Integer count = this.jdbcTemplate.queryForObject(sql, Integer.class, username);
			if(count == 1)
				return true;

			return false;
	}

	@Override
	public ArrayList<User> getTripMediaOwners(Integer tripId) {
		ArrayList<User> ownerUserList = new ArrayList<User>();
		String sql = "select distinct user_id from trip_media where trip_id = ?";
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,tripId);
		for (Map row : rows) {
			User user = new User();
			user.setId((Integer) row.get("user_id"));
			ownerUserList.add(user);
		}
		return ownerUserList;
	}

	@Override
	public Boolean deleteMediaByFilename(String mediaName) {
		final String sql = "DELETE FROM trip_media where file_name = ? ";
		jdbcTemplate.update(sql, mediaName);
		return true;
	}

}
