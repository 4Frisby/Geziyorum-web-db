package com.geziyorum.argumentresolver;

import java.sql.Timestamp;
import java.util.ArrayList;

public class TripRequestWithUserInfo {
	private Integer id;
	private Integer creatorUserId;
	private Integer katilimciUserId;
	private Integer tripId;
	private String explanation;
	private String name;
	private String surname;
	private String username;
	private Timestamp creationTime;
	private ArrayList<TripKatilimcilar> digerKatilimcilar;
	
	
	public TripRequestWithUserInfo(){
		
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCreatorUserId() {
		return creatorUserId;
	}

	public void setCreatorUserId(Integer creatorUserId) {
		this.creatorUserId = creatorUserId;
	}

	public Integer getKatilimciUserId() {
		return katilimciUserId;
	}

	public void setKatilimciUserId(Integer katilimciUserId) {
		this.katilimciUserId = katilimciUserId;
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public ArrayList<TripKatilimcilar> getDigerKatilimcilar() {
		return digerKatilimcilar;
	}

	public void setDigerKatilimcilar(ArrayList<TripKatilimcilar> digerKatilimcilar) {
		this.digerKatilimcilar = digerKatilimcilar;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	


}
