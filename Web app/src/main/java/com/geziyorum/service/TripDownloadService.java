package com.geziyorum.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geziyorum.entity.MediaMetadata;
import com.geziyorum.methods.generals.CommonFuncs;
import com.geziyorum.methods.generals.Constraints;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

@Service
public class TripDownloadService implements GeneralProcess{

	Integer tripId;
	ArrayList<String> downloadFileNames;
	
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object processService() throws IOException {
		return generateTripZip();
	}

	private String generateTripZip() throws IOException {
		String tripFolderPath = Constraints.FULLPATH + File.separator + 
				Constraints.TRIPFOLDER; //  -- db/trip
		
		String tripFolderName = "trip_" + getTripId().toString();
		
		String tripFolderNameCopy = tripFolderName + "_copy";
		
		String tripPathWithName = tripFolderPath + File.separator + tripFolderName; // /opt/tomcat/webapps/Geziyorum/DB/trip/trip_44
		
		String tripPathWitNameCopy = tripPathWithName + "_copy";
		
		if(new File(tripPathWitNameCopy).exists())
			FileUtils.deleteQuietly(new File(tripPathWitNameCopy));
		
		FileUtils.copyDirectory(new File(tripPathWithName), new File(tripPathWitNameCopy));
		
		ArrayList<String> unwantedFileNames = readMediaMetadataAndFindUnwantedList(tripPathWitNameCopy);
		readMediaMetadataAndRemoveUnwantedMedia(tripPathWitNameCopy,unwantedFileNames );
		
		File existingZipFile = new File(tripPathWitNameCopy + ".zip");
		if(existingZipFile.exists())
			FileUtils.deleteQuietly(existingZipFile);
		
		if(!existingZipFile.exists()){
			 try {
				  // Initiate ZipFile object with the path/name of the zip file.
				  ZipFile zipFile = new ZipFile(tripPathWitNameCopy+".zip");

				  // Folder to add
				  String folderToAdd = tripPathWitNameCopy + File.separator;

				  // Initiate Zip Parameters which define various properties such
				  // as compression method, etc.
				  ZipParameters parameters = new ZipParameters();

				  // set compression method to store compression
				  parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
				  parameters.setIncludeRootFolder(false);
				  // Set the compression level
				  parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

				  // Add folder to the zip file
				  zipFile.addFolder(folderToAdd, parameters);

				 } catch (Exception e) {
				  e.printStackTrace();
				 }			
		}

		String tripFolderRelativePath = Constraints.RELATIVEPATH + File.separator + 
				Constraints.TRIPFOLDER;
		
		 return tripFolderRelativePath + File.separator + tripFolderNameCopy + ".zip";
	
	}
	
	public ArrayList<String> readMediaMetadataAndFindUnwantedList(String absoluteFolder){
		ArrayList<String> unwanteds = new ArrayList<String>();
		Boolean bulundu = false;
		Integer i=0;		
		ObjectMapper mapper = new ObjectMapper();
		try {
			MediaMetadata[] mediaMetadata = mapper.readValue(new File(absoluteFolder + File.separator 
					+  "media_metadata.JSON"), MediaMetadata[].class);


			for(int j=0; j<mediaMetadata.length; j++){
				String fileName = mediaMetadata[j].getPath();
				bulundu = false;
				i=0;
				while(i<downloadFileNames.size() && bulundu == false){
					if(downloadFileNames.get(i).equals(fileName))
						bulundu = true;
					i++;
					}
				if(bulundu == false)
					unwanteds.add(fileName);
				}

		}catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return unwanteds;
		
	}
	
	
	public Boolean readMediaMetadataAndRemoveUnwantedMedia(String absoluteFolder,ArrayList<String> unwantedList){
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			MediaMetadata[] mediaMetadata = mapper.readValue(new File(absoluteFolder + File.separator 
					+  "media_metadata.JSON"), MediaMetadata[].class);
			ArrayList<MediaMetadata> CleanedMediaMetadata = removeElements(unwantedList, mediaMetadata,absoluteFolder);
			createNewMediaMetadaJSON(CleanedMediaMetadata,absoluteFolder);
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public static ArrayList<MediaMetadata> removeElements(ArrayList<String> deleteMe, MediaMetadata[] mediaList, String tripFolder) {
		List<MediaMetadata> list = new ArrayList<MediaMetadata>(Arrays.asList(mediaList));

	    for(String item : deleteMe){
	    	for(int i=0; i<list.size(); i++){
		        if(list.get(i).getPath().equals(item)){
		        	String filePathWithName = tripFolder + File.separator + "Media" + File.separator + list.get(i).getPath();
		        	list.remove(i);
		        	FileUtils.deleteQuietly(new File(filePathWithName));
		        }
		            
	    	}
	    }
	    return (ArrayList<MediaMetadata>) list;
	}
	
	public static void createNewMediaMetadaJSON(List<MediaMetadata> cleanedMedias, String folderPath) throws JsonGenerationException, JsonMappingException, IOException{
		String finalFilePath = folderPath + File.separator 
		+  "media_metadata.JSON";
		
		if(new File(finalFilePath).exists())
			FileUtils.deleteQuietly(new File(finalFilePath));
		
		ObjectMapper mapper = new ObjectMapper();
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		mapper.writeValue(out, cleanedMedias);	
		String stringJSON = new String(out.toByteArray());
		writeFile(finalFilePath,stringJSON);
	}
	
	
	public static Boolean writeFile(String path,String fileContent){
		FileOutputStream fop = null;
		File file;
		String content = fileContent;

		try {

			file = new File(path);
			fop = new FileOutputStream(file);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();


		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	public ArrayList<String> getDownloadFileNames() {
		return downloadFileNames;
	}

	public void setDownloadFileNames(ArrayList<String> downloadFileNames) {
		this.downloadFileNames = downloadFileNames;
	}

	

}
