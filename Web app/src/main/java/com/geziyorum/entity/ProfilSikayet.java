package com.geziyorum.entity;

import java.sql.Timestamp;

public class ProfilSikayet {
	
	private Integer sikayetId;
	private String sikayetciUsername;
	private String sikayetEdilenUsername;
	private Integer sikayetType;
	private String sikayetMetini;
	private Integer degerlendirildi;
	private Timestamp sikayetZamani;
	private String sonucAciklama;
	private Integer sonucDurum;
    private String kararVerenAdmin;
    private Timestamp degerlendirilmeZamani;	
	
	public ProfilSikayet(){
		
	}

	public Integer getSikayetId() {
		return sikayetId;
	}

	public void setSikayetId(Integer sikayetId) {
		this.sikayetId = sikayetId;
	}

	public String getSikayetciUsername() {
		return sikayetciUsername;
	}

	public void setSikayetciUsername(String sikayetciUsername) {
		this.sikayetciUsername = sikayetciUsername;
	}

	public String getSikayetEdilenUsername() {
		return sikayetEdilenUsername;
	}

	public void setSikayetEdilenUsername(String sikayetEdilenUsername) {
		this.sikayetEdilenUsername = sikayetEdilenUsername;
	}

	public Integer getSikayetType() {
		return sikayetType;
	}

	public void setSikayetType(Integer sikayetType) {
		this.sikayetType = sikayetType;
	}

	public String getSikayetMetini() {
		return sikayetMetini;
	}

	public void setSikayetMetini(String sikayetMetini) {
		this.sikayetMetini = sikayetMetini;
	}

	public Timestamp getSikayetZamani() {
		return sikayetZamani;
	}

	public void setSikayetZamani(Timestamp sikayetZamani) {
		this.sikayetZamani = sikayetZamani;
	}

	public Integer getDegerlendirildi() {
		return degerlendirildi;
	}

	public void setDegerlendirildi(Integer degerlendirildi) {
		this.degerlendirildi = degerlendirildi;
	}

	public String getSonucAciklama() {
		return sonucAciklama;
	}

	public void setSonucAciklama(String sonucAciklama) {
		this.sonucAciklama = sonucAciklama;
	}

	public Integer getSonucDurum() {
		return sonucDurum;
	}

	public void setSonucDurum(Integer sonucDurum) {
		this.sonucDurum = sonucDurum;
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
