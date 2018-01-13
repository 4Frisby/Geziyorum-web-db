package com.geziyorum.controller.sikayet;

import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.argumentresolver.GonderiSikayetDegerlendir;
import com.geziyorum.argumentresolver.ProfilSikayetDegerlendir;
import com.geziyorum.argumentresolver.YorumSikayetDegerlendir;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.SikayetDao;
import com.geziyorum.entity.PersonalSharing;
import com.geziyorum.entity.Trip;
import com.geziyorum.entity.User;
import com.geziyorum.methods.generals.CommonFuncs;

@RestController
public class SikayetDegerlendirController {

	@Autowired
	SikayetDao sikayetDao;
	
	@Autowired
	CommonDao commonDao;
	
	@RequestMapping(method = RequestMethod.POST , value="/yorumSikayetGizle")
	public Object yorumSikayetGizle(@RequestBody YorumSikayetDegerlendir ysd) throws IOException{
		User gizlenenUser = commonDao.getUserByUsername(ysd.getSikayetEdilenUsername());
		String aciklama = "'" + ysd.getYorumIcerik() +"'" + " içerikli yorumunuz, '" + ysd.getSikayetNedeni() + "' sebebiyle erişime kapatılmıştır. Yetkili tarafından yapılan açıklama : '" + ysd.getAciklama()+"'"; 
		Timestamp now = new Timestamp(System.currentTimeMillis());
		sikayetDao.yorumSikayetDurumKapat(ysd.getKararVerenAdmin(),now,ysd.getAciklama(),ysd.getSikayetId());
		sikayetDao.yorumSikayetGizle(ysd.getYorumId(),ysd.getSikayetId());
		CommonFuncs.sendMail(gizlenenUser.getEmail(), "Yorumunuz erişime kapatılmıştır", aciklama);
		return true;
	}
	
	@RequestMapping(method = RequestMethod.POST , value="/yorumSikayetGizleme")
	public Object yorumSikayetGizleme(@RequestBody YorumSikayetDegerlendir ysd) throws IOException{
		Timestamp now = new Timestamp(System.currentTimeMillis());
		sikayetDao.yorumSikayetDurumKapat(ysd.getKararVerenAdmin(),now,ysd.getAciklama(),ysd.getSikayetId());
		return true;
	}
	
	@RequestMapping(method = RequestMethod.POST , value="/profilSikayetGizle")
	public Object profilSikayetGizle(@RequestBody ProfilSikayetDegerlendir psd) throws IOException{
		User gizlenenUser = commonDao.getUserByUsername(psd.getUsername());
		Timestamp now = new Timestamp(System.currentTimeMillis());
		sikayetDao.profilSikayetDurumKapat(psd.getKararVerenAdmin(),now,psd.getAciklama(),psd.getProfilSikayetId());
		sikayetDao.profilSikayetGizle(psd.getUsername(),psd.getProfilSikayetId());
		String aciklama = "Hesabınız '" + psd.getSikayetNedeni() + "' suçundan erişime kapatılmıştır. Yetkili tarafından yapılan açıklama : '" + psd.getAciklama()+ "'";
		CommonFuncs.sendMail(gizlenenUser.getEmail(), "Hesabınız erişiminiz kapatılmıştır", aciklama);
		return true;
	}
	
	@RequestMapping(method = RequestMethod.POST , value="/profilSikayetGizleme")
	public Object profilSikayetGizleme(@RequestBody ProfilSikayetDegerlendir psd) throws IOException{
		Timestamp now = new Timestamp(System.currentTimeMillis());
		sikayetDao.profilSikayetDurumKapat(psd.getKararVerenAdmin(),now,psd.getAciklama(),psd.getProfilSikayetId());
		return true;
	}
	
	@RequestMapping(method = RequestMethod.POST , value="/gonderiSikayetGizle")
	public Object gonderiSikayetGizle(@RequestBody GonderiSikayetDegerlendir gsd) throws IOException{
		User gizlenenUser = commonDao.getUserByUsername(gsd.getSikayetEdilenUsername());
		PersonalSharing ps = commonDao.getPersonalSharingById(gsd.getPsId());
		Trip trip = commonDao.getTripById(ps.getTrip_id());
		Timestamp now = new Timestamp(System.currentTimeMillis());
		sikayetDao.gonderiSikayetDurumKapat(gsd.getKararVerenAdmin(),now,gsd.getAciklama(),gsd.getGonderiSikayetId());
		sikayetDao.gonderiSikayetGizle(gsd.getPsId(),gsd.getGonderiSikayetId());
		String aciklama = "'" + trip.getExplanation() + "' başlıklı gönderiniz '" + gsd.getSikayetNedeni() + "' suçundan erişime kapatılmıştır. Yetkili tarafından yapılan açıklama : '" + gsd.getAciklama() + "'";
		CommonFuncs.sendMail(gizlenenUser.getEmail(), "Gönderiniz erişime kapatılmıştır", aciklama);
		return true;
	}
	
	@RequestMapping(method = RequestMethod.POST , value="/gonderiSikayetGizleme")
	public Object gonderiSikayetGizleme(@RequestBody GonderiSikayetDegerlendir gsd) throws IOException{
		Timestamp now = new Timestamp(System.currentTimeMillis());
		sikayetDao.gonderiSikayetDurumKapat(gsd.getKararVerenAdmin(),now,gsd.getAciklama(),gsd.getGonderiSikayetId());
		return true;
	}
	
}
