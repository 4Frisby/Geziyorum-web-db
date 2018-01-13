package com.geziyorum.argumentresolver;

import java.sql.Timestamp;

public class SharedUserInfo {
	
	private String username;
	private String name;
	private String surname;
	private String personalInfo;
	private Timestamp sharedTime;
	
	public SharedUserInfo(){
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(String personalInfo) {
		this.personalInfo = personalInfo;
	}

	public Timestamp getSharedTime() {
		return sharedTime;
	}

	public void setSharedTime(Timestamp sharedTime) {
		this.sharedTime = sharedTime;
	}

	
}
