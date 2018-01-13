package com.geziyorum.argumentresolver;

public class YorumSikayetDegerlendir {
	private String kararVerenAdmin;
	private Integer yorumId;
	private Integer sikayetId;
	private String aciklama;
	private String sikayetEdilenUsername;
	private String yorumIcerik;
	private String sikayetNedeni;
	
	public YorumSikayetDegerlendir(){
		
	}

	public String getKararVerenAdmin() {
		return kararVerenAdmin;
	}

	public void setKararVerenAdmin(String kararVerenAdmin) {
		this.kararVerenAdmin = kararVerenAdmin;
	}

	public Integer getYorumId() {
		return yorumId;
	}

	public void setYorumId(Integer yorumId) {
		this.yorumId = yorumId;
	}

	public Integer getSikayetId() {
		return sikayetId;
	}

	public void setSikayetId(Integer sikayetId) {
		this.sikayetId = sikayetId;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public String getSikayetEdilenUsername() {
		return sikayetEdilenUsername;
	}

	public void setSikayetEdilenUsername(String sikayetEdilenUsername) {
		this.sikayetEdilenUsername = sikayetEdilenUsername;
	}

	public String getYorumIcerik() {
		return yorumIcerik;
	}

	public void setYorumIcerik(String yorumIcerik) {
		this.yorumIcerik = yorumIcerik;
	}

	public String getSikayetNedeni() {
		return sikayetNedeni;
	}

	public void setSikayetNedeni(String sikayetNedeni) {
		this.sikayetNedeni = sikayetNedeni;
	}
	

}
