package com.geziyorum.entity;

public class PathMetadata {
	
	private Long start_date;
	private Long finish_date;
	private String file;
	private String type;
	private Adress address;
	
	public PathMetadata(){
		
	}

	

	public Long getStart_date() {
		return start_date;
	}

	public void setStart_date(Long start_date) {
		this.start_date = start_date;
	}



	public Long getFinish_date() {
		return finish_date;
	}



	public void setFinish_date(Long finish_date) {
		this.finish_date = finish_date;
	}



	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}



	public Adress getAddress() {
		return address;
	}



	public void setAddress(Adress address) {
		this.address = address;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	



	
	
	

}
