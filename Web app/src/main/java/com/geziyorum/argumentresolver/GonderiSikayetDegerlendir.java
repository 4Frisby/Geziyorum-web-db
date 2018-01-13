package com.geziyorum.argumentresolver;

import java.sql.Timestamp;

public class GonderiSikayetDegerlendir {
	String kararVerenAdmin;
	Timestamp dZaman;
	String aciklama;
	Integer gonderiSikayetId;
	Integer psId;
	String sikayetEdilenUsername;		
	String sikayetNedeni;

	public GonderiSikayetDegerlendir(){
		
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

	public Integer getGonderiSikayetId() {
		return gonderiSikayetId;
	}

	public void setGonderiSikayetId(Integer gonderiSikayetId) {
		this.gonderiSikayetId = gonderiSikayetId;
	}

	public Integer getPsId() {
		return psId;
	}

	public void setPsId(Integer psId) {
		this.psId = psId;
	}

	public String getSikayetEdilenUsername() {
		return sikayetEdilenUsername;
	}

	public void setSikayetEdilenUsername(String sikayetEdilenUsername) {
		this.sikayetEdilenUsername = sikayetEdilenUsername;
	}

	public String getSikayetNedeni() {
		return sikayetNedeni;
	}

	public void setSikayetNedeni(String sikayetNedeni) {
		this.sikayetNedeni = sikayetNedeni;
	}


	

}
