package com.geziyorum.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geziyorum.argumentresolver.CommentPersonalSharingResolver;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.TripDao;
import com.geziyorum.entity.Comment;
import com.geziyorum.entity.CommentedYourTripNotification;
import com.geziyorum.entity.MentionedYouNotification;
import com.geziyorum.entity.PersonalSharing;
import com.geziyorum.entity.Trip;
import com.geziyorum.entity.User;
import com.geziyorum.methods.generals.CommonFuncs;


@Service
public class CommonPageCommentTripService implements GeneralProcess{

	@Autowired
	CommonDao commonDao;
	
	@Autowired
	TripDao tripDao;
	
	
	Trip trip;
	
	PersonalSharing personalSharing; // paylaşılan gezi
	Comment comment = new Comment(); // geziye yapılan yorum
	User userCommentYapan; // yorum yapan user
	User personalSharingOwnerUser; // geziyi paylaşan user

	ArrayList<Integer> bahsedilenler = new ArrayList<Integer>(); // yorumdab bahsedilen users IDS.
	CommentPersonalSharingResolver commentPersonalSharingResolver = new CommentPersonalSharingResolver(); 
	CommentedYourTripNotification commentedYourTripNotification = new CommentedYourTripNotification(); // senin tripine yorum yaptı not.
	MentionedYouNotification mentionedYouNotification; // x tripte y yorumunda senden bahsetti not.
	Timestamp now; // yorum - not. zamanları.. ortak
	
	
	
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		now = new Timestamp(System.currentTimeMillis());
		commonDao.checkSessionExistByToken(commentPersonalSharingResolver.getToken());
		setUserCommentYapan(commonDao.getUserInfoBySessionToken(commentPersonalSharingResolver.getToken()));
		
		commonDao.checkPersonalSharingExistById(commentPersonalSharingResolver.getPersonalSharingId());
		setPersonalSharing(commonDao.getPersonalSharingById(commentPersonalSharingResolver.getPersonalSharingId()));
		setPersonalSharingOwnerUser(commonDao.getUserById(personalSharing.getOwnerId()));
		

		getComment().setContent(commentPersonalSharingResolver.getContent());
		getComment().setPersonalSharingId(commentPersonalSharingResolver.getPersonalSharingId());
		getComment().setUserId(getUserCommentYapan().getId());
		getComment().setSendTime(now);
		
		if(commonDao.checkCommentSpam(getComment()))
			throw new IOException("Spam mesaj algılandı.");		


		
		return true;
	}

	@Override
	public Object processService() throws IOException {
		
		/*if(userCommentYapan.getId() == personalSharingOwnerUser.getId()){
			return false;
		}yorum yapan ile yapılan aynıysa not. yollama ama şimdilik test amaçlı kapalı.  */
		
		commonDao.createComment(getComment()); // yorum yaptı
		setComment(commonDao.getCommentByContentAndId(getComment()));
		
		commentedYourTripNotification.setUserFromId(userCommentYapan.getId());
		commentedYourTripNotification.setUserToId(personalSharingOwnerUser.getId());
		commentedYourTripNotification.setPersonalSharingId(personalSharing.getId());
		commentedYourTripNotification.setCommentTime(comment.getSendTime());
		commentedYourTripNotification.setCommentId(comment.getId());
		
		
		
		commonDao.createCommentedYourTripNotification(commentedYourTripNotification); // x - y'nin tripinie yorum yaptı diye not. oluşturuldu.
		if(bahsedilenler.size() != 0){
			for(int i=0; i<bahsedilenler.size(); i++){
				mentionedYouNotification.setUserFromId(userCommentYapan.getId());
				mentionedYouNotification.setUserToId(bahsedilenler.get(i));
				mentionedYouNotification.setPersonalSharingId(personalSharing.getId());
				mentionedYouNotification.setMentionTime(now);
				mentionedYouNotification.setCommentId(comment.getId());
				commonDao.createMentionedYouNotification(mentionedYouNotification); //-- comment'inde birilerinden bahsetti onlara not. yolla.
			}
		}
		return true;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}


	public User getUserCommentYapan() {
		return userCommentYapan;
	}

	public void setUserCommentYapan(User userCommentYapan) {
		this.userCommentYapan = userCommentYapan;
	}

	
	
	
	public User getPersonalSharingOwnerUser() {
		return personalSharingOwnerUser;
	}

	public void setPersonalSharingOwnerUser(User personalSharingOwnerUser) {
		this.personalSharingOwnerUser = personalSharingOwnerUser;
	}

	public ArrayList<Integer> getBahsedilenler() {
		return bahsedilenler;
	}

	public void setBahsedilenler(ArrayList<Integer> bahsedilenler) {
		this.bahsedilenler = bahsedilenler;
	}

	public CommentPersonalSharingResolver getCommentPersonalSharingResolver() {
		return commentPersonalSharingResolver;
	}

	public void setCommentPersonalSharingResolver(CommentPersonalSharingResolver commentPersonalSharingResolver) {
		this.commentPersonalSharingResolver = commentPersonalSharingResolver;
	}

	public CommentedYourTripNotification getCommentedYourTripNotification() {
		return commentedYourTripNotification;
	}

	public void setCommentedYourTripNotification(CommentedYourTripNotification commentedYourTripNotification) {
		this.commentedYourTripNotification = commentedYourTripNotification;
	}

	public PersonalSharing getPersonalSharing() {
		return personalSharing;
	}

	public void setPersonalSharing(PersonalSharing personalSharing) {
		this.personalSharing = personalSharing;
	}

	

}
