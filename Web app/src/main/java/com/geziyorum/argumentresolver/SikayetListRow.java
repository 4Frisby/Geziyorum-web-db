package com.geziyorum.argumentresolver;

import java.sql.Timestamp;

public class SikayetListRow {
	
	private Integer sikayetId;
	private String sikayetEdilenUser;
	private String sikayetMetini;
	private String durum;
	private Timestamp sikayetZamani;
	private Integer sonucDurum;
	private String tip;
	private String sonucAciklama;
	private String kararVerenAdmin;
	private Timestamp degerlendirilmeZamani;
	
	
	public SikayetListRow(){
		
	}


	public Integer getSikayetId() {
		return sikayetId;
	}


	public void setSikayetId(Integer sikayetId) {
		this.sikayetId = sikayetId;
	}


	public String getSikayetEdilenUser() {
		return sikayetEdilenUser;
	}


	public void setSikayetEdilenUser(String sikayetEdilenUser) {
		this.sikayetEdilenUser = sikayetEdilenUser;
	}


	public String getSikayetMetini() {
		return sikayetMetini;
	}


	public void setSikayetMetini(String sikayetMetini) {
		this.sikayetMetini = sikayetMetini;
	}


	public String getDurum() {
		return durum;
	}


	public void setDurum(String durum) {
		this.durum = durum;
	}


	public Timestamp getSikayetZamani() {
		return sikayetZamani;
	}


	public void setSikayetZamani(Timestamp sikayetZamani) {
		this.sikayetZamani = sikayetZamani;
	}




	public Integer getSonucDurum() {
		return sonucDurum;
	}


	public void setSonucDurum(Integer sonucDurum) {
		this.sonucDurum = sonucDurum;
	}


	public String getTip() {
		return tip;
	}


	public void setTip(String tip) {
		this.tip = tip;
	}


	public String getSonucAciklama() {
		return sonucAciklama;
	}


	public void setSonucAciklama(String sonucAciklama) {
		this.sonucAciklama = sonucAciklama;
	}


	public String getKararVerenAdmin() {
		return kararVerenAdmin;
	}


	public void setKararVerenAdmin(String kararVerenAdmin) {
		this.kararVerenAdmin = kararVerenAdmin;
	}


	public Timestamp getDegerlendirilmeZamani() {
		return degerlendirilmeZamani;
	}


	public void setDegerlendirilmeZamani(Timestamp degerlendirilmeZamani) {
		this.degerlendirilmeZamani = degerlendirilmeZamani;
	}
	
	
	
	

}
