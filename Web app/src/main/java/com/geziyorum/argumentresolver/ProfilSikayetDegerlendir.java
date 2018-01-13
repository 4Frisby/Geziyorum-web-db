package com.geziyorum.argumentresolver;

import java.sql.Timestamp;

public class ProfilSikayetDegerlendir {
	private String kararVerenAdmin;
	private Timestamp dZaman;
	private String aciklama;
	private Integer profilSikayetId;
	private String username;
	private String sikayetNedeni;
	
	public ProfilSikayetDegerlendir(){
		
	}

	public String getKararVerenAdmin() {
		return kararVerenAdmin;
	}

	public void setKararVerenAdmin(String kararVerenAdmin) {
		this.kararVerenAdmin = kararVerenAdmin;
	}

	public Timestamp getdZaman() {
		return dZaman;
	}

	public void setdZaman(Timestamp dZaman) {
		this.dZaman = dZaman;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public Integer getProfilSikayetId() {
		return profilSikayetId;
	}

	public void setProfilSikayetId(Integer profilSikayetId) {
		this.profilSikayetId = profilSikayetId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSikayetNedeni() {
		return sikayetNedeni;
	}

	public void setSikayetNedeni(String sikayetNedeni) {
		this.sikayetNedeni = sikayetNedeni;
	}
	

}
