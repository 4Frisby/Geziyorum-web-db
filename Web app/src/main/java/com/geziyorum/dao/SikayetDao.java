package com.geziyorum.dao;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.geziyorum.argumentresolver.GonderiSikayetTanim;
import com.geziyorum.argumentresolver.ProfilSikayetTanim;
import com.geziyorum.argumentresolver.SikayetInfo;
import com.geziyorum.argumentresolver.SikayetListRow;
import com.geziyorum.argumentresolver.YorumSikayetTanim;
import com.geziyorum.entity.GonderiSikayet;
import com.geziyorum.entity.ProfilSikayet;
import com.geziyorum.entity.YorumSikayet;

public interface SikayetDao {
	
	ArrayList<SikayetInfo> getSikayetList();

	Boolean createYorumSikayet(YorumSikayet yorumSikayet);
	ArrayList<YorumSikayetTanim> getYorumSikayetler();
	Boolean yorumSikayetDurumKapat(String kararVerenAdmin, Timestamp dZaman, String aciklama,Integer yorumSikayetId);
	Boolean yorumSikayetGizle(Integer yorumId,Integer sikayetId);
	
	
	Boolean createGonderiSikayet(GonderiSikayet gonderiSikayet);
	ArrayList<GonderiSikayetTanim> getGonderiSikayetler();
	Boolean gonderiSikayetDurumKapat(String kararVerenAdmin,Timestamp dZaman,String aciklama,Integer gonderiSikayetId);
	Boolean gonderiSikayetGizle(Integer psId,Integer sikayetId);

	Boolean createProfilSikayet(ProfilSikayet profilSikayet);
	ArrayList<ProfilSikayetTanim> getProfilSikayetler();
	Boolean profilSikayetDurumKapat(String kararVerenAdmin, Timestamp dZaman,String aciklama,Integer profilSikayetId);
	Boolean profilSikayetGizle(String username,Integer profilSikayetId);
	
	ArrayList<SikayetListRow> getSikayetListOfUser(String username);
	
	Boolean userSilinebilirMi(String username);

	Boolean userSil(String username);
	
}
