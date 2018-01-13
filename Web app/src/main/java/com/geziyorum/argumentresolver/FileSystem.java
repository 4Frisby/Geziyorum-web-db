package com.geziyorum.argumentresolver;

import java.io.File;

import com.geziyorum.methods.generals.Constraints;

public class FileSystem {
	
	private String RELATIVEPATH = Constraints.RELATIVEPATH;
	private String PPFOLDER =  Constraints.PPFOLDER;
	private String TRIPFOLDER = Constraints.TRIPFOLDER;
	private String TRIPMEDIAFOLDER=Constraints.TRIPMEDIAFOLDER;
	private String TRIPPATHFOLDER=Constraints.TRIPPATHFOLDER;
	private String MEDIAPATH = Constraints.RELATIVEPATH + File.separator + Constraints.TRIPFOLDER + File.separator;

	public FileSystem(){
		
	}

	public String getRELATIVEPATH() {
		return RELATIVEPATH;
	}

	public void setRELATIVEPATH(String rELATIVEPATH) {
		RELATIVEPATH = rELATIVEPATH;
	}

	public String getPPFOLDER() {
		return PPFOLDER;
	}

	public void setPPFOLDER(String pPFOLDER) {
		PPFOLDER = pPFOLDER;
	}

	public String getTRIPFOLDER() {
		return TRIPFOLDER;
	}

	public void setTRIPFOLDER(String tRIPFOLDER) {
		TRIPFOLDER = tRIPFOLDER;
	}

	public String getTRIPMEDIAFOLDER() {
		return TRIPMEDIAFOLDER;
	}

	public void setTRIPMEDIAFOLDER(String tRIPMEDIAFOLDER) {
		TRIPMEDIAFOLDER = tRIPMEDIAFOLDER;
	}

	public String getTRIPPATHFOLDER() {
		return TRIPPATHFOLDER;
	}

	public void setTRIPPATHFOLDER(String tRIPPATHFOLDER) {
		TRIPPATHFOLDER = tRIPPATHFOLDER;
	}

	public String getMEDIAPATH() {
		return MEDIAPATH;
	}

	public void setMEDIAPATH(String mEDIAPATH) {
		MEDIAPATH = mEDIAPATH;
	}
	
	
	
	
	
}
