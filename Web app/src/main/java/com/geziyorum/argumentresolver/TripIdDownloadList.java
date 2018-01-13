package com.geziyorum.argumentresolver;

import java.util.ArrayList;

public class TripIdDownloadList {
	Integer tripId;
	ArrayList<String> downloadList;
	
	public TripIdDownloadList(){
		
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	public ArrayList<String> getDownloadList() {
		return downloadList;
	}

	public void setDownloadList(ArrayList<String> downloadList) {
		this.downloadList = downloadList;
	}


}
