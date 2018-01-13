package com.geziyorum.entity;

public class TripMetadata {
	
	private Long startdate;
	private Long finishdate;
	private String name;
	private String[] members;
	
	
	
	public TripMetadata(){
		
	}

	public Long getStartdate() {
		return startdate;
	}



	public void setStartdate(Long startdate) {
		this.startdate = startdate;
	}




	public Long getFinishdate() {
		return finishdate;
	}




	public void setFinishdate(Long finishdate) {
		this.finishdate = finishdate;
	}




	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}




	public String[] getMembers() {
		return members;
	}




	public void setMembers(String[] members) {
		this.members = members;
	}
	
	
	
}
