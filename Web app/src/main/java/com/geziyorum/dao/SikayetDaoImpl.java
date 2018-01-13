package com.geziyorum.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.geziyorum.argumentresolver.GonderiSikayetTanim;
import com.geziyorum.argumentresolver.PersonalSharingTrip;
import com.geziyorum.argumentresolver.ProfilSikayetTanim;
import com.geziyorum.argumentresolver.SikayetInfo;
import com.geziyorum.argumentresolver.SikayetListRow;
import com.geziyorum.argumentresolver.YorumSikayetTanim;
import com.geziyorum.entity.GonderiSikayet;
import com.geziyorum.entity.ProfilSikayet;
import com.geziyorum.entity.YorumSikayet;
import com.geziyorum.methods.generals.CommonFuncs;



@Repository("sikayetDao")
public class SikayetDaoImpl implements SikayetDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public ArrayList<SikayetInfo> getSikayetList() {
		ArrayList<SikayetInfo> sikayetInfoList = new ArrayList<SikayetInfo>();
		final String sql = "select * from type where type_code in (51,52,53)";
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);
		for(Map row : rows){
			SikayetInfo sikayetInfo = new SikayetInfo();
			sikayetInfo.setSikayetTypeCode((Integer) row.get("type_code"));
			sikayetInfo.setTanim((String) row.get("description"));
			sikayetInfoList.add(sikayetInfo);
		}

		return sikayetInfoList;
	}


	@Override
	public Boolean createYorumSikayet(YorumSikayet yorumSikayet) {
		final String sql = "INSERT INTO yorum_sikayet(sikayet_edilen_yorum_id,sikayetci_username,"
				+ "sikayet_edilen_username,yorum_icerik,sikayet_type,sikayet_metini,degerlendirildi,gonderi_sikayet_zamani) VALUES(?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql,new Object[] 
				{yorumSikayet.getSikayetEdilenYorumId()
				,yorumSikayet.getSikayetciUsername()
				,yorumSikayet.getSikayetEdilenUsername()
				,yorumSikayet.getYorumIcerik()
				,yorumSikayet.getSikayetType()
				,yorumSikayet.getSikayetMetini()
				,yorumSikayet.getDegerlendirildi()
				,yorumSikayet.getSikayetZamani()
				});
		return true;
	}


	@Override
	public Boolean createGonderiSikayet(GonderiSikayet gonderiSikayet) {
		final String sql = "INSERT INTO gonderi_sikayet(sikayet_edilen_gonderi_id,sikayetci_username,"
				+ "sikayet_edilen_username,sikayet_type,sikayet_metini,degerlendirildi,gonderi_sikayet_zamani) VALUES(?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql,new Object[] 
				{gonderiSikayet.getSikayetEdilenGonderiId()
				,gonderiSikayet.getSikayetciUsername()
				,gonderiSikayet.getSikayetEdilenUsername()
				,gonderiSikayet.getSikayetType()
				,gonderiSikayet.getSikayetMetini()
				,gonderiSikayet.getDegerlendirildi()
				,gonderiSikayet.getSikayetZamani()
				});
		return true;
	}


	@Override
	public Boolean createProfilSikayet(ProfilSikayet profilSikayet) {
		final String sql = "INSERT INTO profil_sikayet(sikayetci_username,"
				+ "sikayet_edilen_username,sikayet_type,sikayet_metini,degerlendirildi,profil_sikayet_zamani) VALUES(?,?,?,?,?,?)";
		jdbcTemplate.update(sql,new Object[] 
				{
				 profilSikayet.getSikayetciUsername()
				,profilSikayet.getSikayetEdilenUsername()
				,profilSikayet.getSikayetType()
				,profilSikayet.getSikayetMetini()
				,profilSikayet.getDegerlendirildi()
				,profilSikayet.getSikayetZamani()
				});
		return true;
	}





	@Override
	public ArrayList<GonderiSikayetTanim> getGonderiSikayetler() {
		ArrayList<GonderiSikayetTanim> gonderiSikayetList = new ArrayList<GonderiSikayetTanim>();
		final String sql = "select gs.*,t.description from gonderi_sikayet gs, type t where t.type_code = gs.sikayet_type order by sikayet_id desc"; // sağda veya soldaysa sayısını getir.
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);
		for(Map row : rows){
			GonderiSikayet gonderiSikayet = new GonderiSikayet();
			GonderiSikayetTanim gonderiSikayetTanim = new GonderiSikayetTanim();
			gonderiSikayet.setDegerlendirildi((Integer) row.get("degerlendirildi"));
			gonderiSikayet.setSikayetciUsername((String) row.get("sikayetci_username"));
			gonderiSikayet.setSikayetEdilenUsername((String) row.get("sikayet_edilen_username"));
			gonderiSikayet.setSikayetMetini((String) row.get("sikayet_metini"));
			gonderiSikayet.setSikayetType((Integer) row.get("sikayet_type"));
			gonderiSikayet.setSikayetZamani((Timestamp) row.get("gonderi_sikayet_zamani"));
			gonderiSikayet.setId((Integer) row.get("sikayet_id"));
			gonderiSikayet.setSikayetEdilenGonderiId((Integer) row.get("sikayet_edilen_gonderi_id"));
			gonderiSikayet.setDegerlendirilmeZamani((Timestamp) row.get("degerlendirilme_zamani"));
			gonderiSikayet.setKararVerenAdmin((String) row.get("karar_veren_admin"));
			gonderiSikayet.setSonucAciklama((String) row.get("sonuc_aciklama"));
			
			gonderiSikayetTanim.setTanim((String) row.get("description"));
			gonderiSikayetTanim.setGonderiSikayet(gonderiSikayet);
			gonderiSikayetList.add(gonderiSikayetTanim);
		}

		return gonderiSikayetList;
	}


	@Override
	public ArrayList<ProfilSikayetTanim> getProfilSikayetler() {
		ArrayList<ProfilSikayetTanim> profilSikayetTanimList = new ArrayList<ProfilSikayetTanim>();
		final String sql = "select ps.*,t.description from profil_sikayet ps, type t where t.type_code = ps.sikayet_type order by sikayet_id desc"; // sağda veya soldaysa sayısını getir.
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);
		for(Map row : rows){
			ProfilSikayet profilSikayet = new ProfilSikayet();
			ProfilSikayetTanim profilSikayetTanim = new ProfilSikayetTanim();
			profilSikayet.setDegerlendirildi((Integer) row.get("degerlendirildi"));
			profilSikayet.setSikayetciUsername((String) row.get("sikayetci_username"));
			profilSikayet.setSikayetEdilenUsername((String) row.get("sikayet_edilen_username"));
			profilSikayet.setSikayetMetini((String) row.get("sikayet_metini"));
			profilSikayet.setSikayetType((Integer) row.get("sikayet_type"));
			profilSikayet.setSikayetZamani((Timestamp) row.get("profil_sikayet_zamani"));
			profilSikayet.setSikayetId((Integer) row.get("sikayet_id"));
			profilSikayet.setDegerlendirilmeZamani((Timestamp) row.get("degerlendirilme_zamani"));
			profilSikayet.setKararVerenAdmin((String) row.get("karar_veren_admin"));
			profilSikayet.setSonucAciklama((String) row.get("sonuc_aciklama"));
			
			
			profilSikayetTanim.setTanim((String) row.get("description"));
			profilSikayetTanim.setProfilSikayet(profilSikayet);
			profilSikayetTanimList.add(profilSikayetTanim);
		}

		return profilSikayetTanimList;
	}


	@Override
	public ArrayList<YorumSikayetTanim> getYorumSikayetler() {
		ArrayList<YorumSikayetTanim> yorumSikayetList = new ArrayList<YorumSikayetTanim>();
		final String sql = "select ys.*,t.description from yorum_sikayet ys, type t where ys.sikayet_type = t.type_code order by sikayet_id desc"; // sağda veya soldaysa sayısını getir.
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);
		for(Map row : rows){
			YorumSikayet yorumSikayet = new YorumSikayet();
			YorumSikayetTanim yst = new YorumSikayetTanim();
			yorumSikayet.setDegerlendirildi((Integer) row.get("degerlendirildi"));
			yorumSikayet.setSikayetciUsername((String) row.get("sikayetci_username"));
			yorumSikayet.setSikayetEdilenUsername((String) row.get("sikayet_edilen_username"));
			yorumSikayet.setSikayetEdilenYorumId((Integer)row.get("sikayet_edilen_yorum_id"));
			yorumSikayet.setSikayetId((Integer) row.get("sikayet_id"));
			yorumSikayet.setSikayetMetini((String) row.get("sikayet_metini"));
			yorumSikayet.setSikayetType((Integer) row.get("sikayet_type"));
			yorumSikayet.setSikayetZamani((Timestamp) row.get("gonderi_sikayet_zamani"));
			yorumSikayet.setYorumIcerik((String) row.get("yorum_icerik"));
			yorumSikayet.setDegerlendirilmeZamani((Timestamp) row.get("degerlendirilme_zamani"));
			yorumSikayet.setKararVerenAdmin((String) row.get("karar_veren_admin"));
			yorumSikayet.setSonucAciklama((String) row.get("sonuc_aciklama"));			
			
			yst.setYorumSikayet(yorumSikayet);
			yst.setTanim(((String) row.get("description")));
			yorumSikayetList.add(yst);
		}

		return yorumSikayetList;
	}


	@Override
	public Boolean yorumSikayetDurumKapat(String kararVerenAdmin, Timestamp dZaman, String aciklama,Integer yorumSikayetId) {
		final String sql = "UPDATE yorum_sikayet SET degerlendirildi=1, sonuc_durum = 0, "
				+ "karar_veren_admin = ?, degerlendirilme_zamani = ?, sonuc_aciklama = ? where sikayet_id = ?";
		jdbcTemplate.update(sql,new Object[]{
				kararVerenAdmin,
				dZaman,
				aciklama,
				yorumSikayetId
		});
		return true;
	}


	@Override
	public Boolean yorumSikayetGizle(Integer yorumId,Integer sikayetId) {
		String sql = "UPDATE comment SET hide=1 where id = ?";
		jdbcTemplate.update(sql,yorumId);
		sql = "UPDATE yorum_sikayet SET sonuc_durum=1 where sikayet_id = ?";		
		jdbcTemplate.update(sql,sikayetId);
		return true;
	}


	@Override
	public Boolean gonderiSikayetDurumKapat(String kararVerenAdmin,Timestamp dZaman,String aciklama,Integer gonderiSikayetId) {
		final String sql = "UPDATE gonderi_sikayet SET degerlendirildi=1, sonuc_durum = 0, "
				+ "karar_veren_admin = ?, degerlendirilme_zamani = ?,sonuc_aciklama = ? where sikayet_id = ?";
		jdbcTemplate.update(sql,new Object[] {
				kararVerenAdmin,
				dZaman,
				aciklama,
				gonderiSikayetId});
		return true;
	}


	@Override
	public Boolean gonderiSikayetGizle(Integer psId,Integer sikayetId) {
		String sql = "UPDATE personal_sharing SET hide=1 where id = ?";
		jdbcTemplate.update(sql,psId);
		sql = "UPDATE gonderi_sikayet SET sonuc_durum=1 where sikayet_id = ?";		
		jdbcTemplate.update(sql,sikayetId);		
		return true;
	}


	@Override
	public Boolean profilSikayetDurumKapat(String kararVerenAdmin, Timestamp dZaman,String aciklama,Integer profilSikayetId) {
		final String sql = "UPDATE profil_sikayet SET degerlendirildi=1, sonuc_durum = 0, "
				+ "karar_veren_admin = ?,degerlendirilme_zamani = ?, sonuc_aciklama = ? where sikayet_id = ?";
		jdbcTemplate.update(sql,new Object[]{
				kararVerenAdmin,
				dZaman,
				aciklama,
				profilSikayetId
		});
		return true;
	}


	@Override
	public Boolean profilSikayetGizle(String username,Integer profilSikayetId) {
		String sql = "UPDATE users SET hide=1 where username = ?";
		jdbcTemplate.update(sql,username);
		sql = "UPDATE profil_sikayet SET sonuc_durum=1 where sikayet_id = ?";		
		jdbcTemplate.update(sql,profilSikayetId);			
		return true;
	}


	@Override
	public ArrayList<SikayetListRow> getSikayetListOfUser(String username) {
		ArrayList<SikayetListRow> sikayetList = new ArrayList<SikayetListRow>();
		final String sql = "select sikayet_id,sikayet_edilen_username,sikayet_metini,IF (degerlendirildi = 0, \"Değerlendiriliyor\",\"Kapatıldı\") as durum,profil_sikayet_zamani as sikayet_zamani,sonuc_durum, \"profil şikayet\" as tip,sonuc_aciklama,karar_veren_admin,degerlendirilme_zamani  from profil_sikayet where sikayetci_username = ? \n" + 
				"union\n" + 
				"select sikayet_id,sikayet_edilen_username,sikayet_metini,IF (degerlendirildi = 0, \"Değerlendiriliyor\",\"Kapatıldı\") as durum,gonderi_sikayet_zamani as sikayet_zamani,sonuc_durum, \"gönderi şikayet\" as tip,sonuc_aciklama,karar_veren_admin,degerlendirilme_zamani from gonderi_sikayet where sikayetci_username = ? \n" + 
				"union\n" + 
				"select sikayet_id,sikayet_edilen_username,sikayet_metini,IF (degerlendirildi = 0, \"Değerlendiriliyor\",\"Kapatıldı\") as durum,gonderi_sikayet_zamani as sikayet_zamani,sonuc_durum, \"yorum şikayet\" as tip,sonuc_aciklama,karar_veren_admin,degerlendirilme_zamani from yorum_sikayet where sikayetci_username = ? order by sikayet_zamani desc; \n";
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,new Object[]{username,username,username});
		for(Map row : rows){
			SikayetListRow sikayetListRow = new SikayetListRow();
			sikayetListRow.setDurum((String) row.get("durum"));
			sikayetListRow.setSikayetEdilenUser((String) row.get("sikayet_edilen_username"));
			sikayetListRow.setSikayetId((Integer) row.get("sikayet_id"));
			sikayetListRow.setSikayetMetini((String) row.get("sikayet_metini"));
			sikayetListRow.setSikayetZamani((Timestamp) row.get("sikayet_zamani"));
			sikayetListRow.setSonucDurum((Integer) row.get("sonuc_durum"));
			sikayetListRow.setTip((String) row.get("tip"));
			sikayetListRow.setDegerlendirilmeZamani((Timestamp) row.get("degerlendirilme_zamani"));
			sikayetListRow.setKararVerenAdmin((String) row.get("karar_veren_admin"));
			sikayetListRow.setSonucAciklama((String) row.get("sonuc_aciklama"));
			sikayetList.add(sikayetListRow);
		}

		return sikayetList;
	}


	@Override
	public Boolean userSilinebilirMi(String username) {
		String sql = "select count(*) from profil_sikayet where sonuc_durum = 1 and degerlendirildi = 1 and sikayet_edilen_username = ?";
		Integer count1 = this.jdbcTemplate.queryForObject(sql, Integer.class, username);
		
		sql = "select count(*) from gonderi_sikayet where sonuc_durum = 1 and degerlendirildi = 1 and sikayet_edilen_username = ?";
		Integer count2 = this.jdbcTemplate.queryForObject(sql, Integer.class, username);
		
		sql = "select count(*) from yorum_sikayet where sonuc_durum = 1 and degerlendirildi = 1 and sikayet_edilen_username = ? ";
		Integer count3 = this.jdbcTemplate.queryForObject(sql, Integer.class, username);
				
		if(count1 >=3 || count2 >=3 || count3 >=3)
			return true;
		else
			return false;
	}


	@Override
	public Boolean userSil(String username) {
		final String sql = "DELETE FROM users where username = ?";
		jdbcTemplate.update(sql, username);
		return true;
	}

	
	
}
