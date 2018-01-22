package com.geziyorum.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.entity.MediaMetadata;
import com.geziyorum.methods.generals.Constraints;

@Service
public class TripDeleteMediaService implements GeneralProcess{
	
	
	@Autowired
	CommonDao commonDao;
	
	ArrayList<String> deleteFileNames = new ArrayList<String>();
	Integer tripId;

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
		String tripMediaPath= Constraints.FULLPATH + File.separator + 
				Constraints.TRIPFOLDER + File.separator + 
				"trip_" + getTripId().toString();; //  -- db/trip/trip_x
		
		for(String mediaName : deleteFileNames){
			commonDao.deleteMediaByFilename(mediaName);
		}
		
		recreateMediaMetadaJSON(tripMediaPath,deleteFileNames);
		return true;
	}
	
	public Boolean recreateMediaMetadaJSON(String absoluteFolder,ArrayList<String> unwantedList){
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

	public ArrayList<String> getDeleteFileNames() {
		return deleteFileNames;
	}

	public void setDeleteFileNames(ArrayList<String> deleteFileNames) {
		this.deleteFileNames = deleteFileNames;
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	
}
