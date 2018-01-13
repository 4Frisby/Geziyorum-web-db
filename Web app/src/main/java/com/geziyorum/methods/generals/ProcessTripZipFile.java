package com.geziyorum.methods.generals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
 
 multipart file yolladı
trip/userid/trip_<x> yeni ismini verdiğim bir folder daha ekledim
aldım trip zip'i trip/userid/trip_<x>/ folder'a attım.
zip'i açtım. trip_x diye bi ismi var bunu extract here gibi yani folder'ı önüne koymadan açmam lazım. zip'i saklamam lazım bunu da bakiye dönmem lazım.



mediaMetadata class'ında bilgilerim duruyor.
bunları trip_media'ya yazdım.

okurken trip'e folder name'ini sordum.
trip/userid/trip_<x> cevabını verdi oraya gittim.
trip/userid/trip_<x>/media trip_media'ya sordum. bana file'ın adını ve tipini verdi.
ön'e döndürdüm.



 */

public class ProcessTripZipFile {
	
	public static void unzipFile(String fileNameWithPath,String outputFolder){
	     byte[] buffer = new byte[10240];

	     try{

	    	//create output directory is not exists
	    	File folder = new File(outputFolder);
	    	if(!folder.exists()){
	    		folder.mkdir();
	    	}
	    	

	    	
	    	//get the zip file content
	    	ZipInputStream zis =
	    		new ZipInputStream(new FileInputStream(fileNameWithPath));
	    	//get the zipped file list entry
	    	ZipEntry ze = zis.getNextEntry();

	    	while(ze!=null){

	    	   String fileName = ze.getName();
	    	   
		    	String childFolder = new File(outputFolder + File.separator + fileName).getParent();
	    	   //new File(outputFolder + File.separator + fileName).getParent().mkdirs();

		    	File newFile = new File(outputFolder + fileName);
	           
		    	if(fileName.contains("/"))
		    	if(!newFile.exists()){
		    		newFile.mkdir();
		    	}
		    	
	           System.out.println("file unzip : "+ newFile.getAbsoluteFile());

	            //create all non exists folders
	            //else you will hit FileNotFoundException for compressed folder
	            new File(newFile.getParent()).mkdirs();

	            FileOutputStream fos = new FileOutputStream(newFile);

	            int len;
	            while ((len = zis.read(buffer)) > 0) {
	       		fos.write(buffer, 0, len);
	            }

	            fos.close();
	            ze = zis.getNextEntry();
	    	}

	        zis.closeEntry();
	    	zis.close();

	    	System.out.println("Done");

	    }catch(IOException ex){
	       ex.printStackTrace();
	    }
		
	}
	
	

}
