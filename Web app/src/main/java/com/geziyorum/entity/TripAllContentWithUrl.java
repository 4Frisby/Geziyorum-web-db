package com.geziyorum.entity;

import java.io.File;
import java.util.ArrayList;

import com.geziyorum.methods.generals.Constraints;

public class TripAllContentWithUrl {
	ArrayList<TripAllContent> tripListesi = new ArrayList<TripAllContent>();
	String url;
	String mediaFolderName;
	String fileSeperator;
	
	public TripAllContentWithUrl(){
		this.fileSeperator = File.separator; // "/"
		this.mediaFolderName = Constraints.TRIPMEDIAFOLDER;	// "Media"
		this.url = Constraints.RELATIVEPATH + File.separator + Constraints.TRIPFOLDER + File.separator;  //"DB/trip/"
		
	}

	public ArrayList<TripAllContent> getTripListesi() {
		return tripListesi;
	}

	public void setTripListesi(ArrayList<TripAllContent> tripListesi) {
		this.tripListesi = tripListesi;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	

	
}
