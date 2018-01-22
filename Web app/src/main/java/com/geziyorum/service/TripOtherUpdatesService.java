package com.geziyorum.service;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geziyorum.dao.CommonDao;
import com.geziyorum.dao.TripDao;
import com.geziyorum.entity.MediaMetadata;
import com.geziyorum.entity.Path;
import com.geziyorum.entity.PersonalSharing;
import com.geziyorum.entity.Trip;
import com.geziyorum.entity.User;
import com.geziyorum.methods.generals.CommonFuncs;
import com.geziyorum.methods.generals.Constraints;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

@Service
public class TripOtherUpdatesService implements GeneralProcess{

	@Autowired
	CommonDao commonDao;
	
	@Autowired
	TripDao tripDao;
	
	/*
	 *  gelen parametreler
	 */
	Integer tripId; 
	MultipartFile file;
	String token;
	String fileName;
	
	User user;
	Trip trip;
	
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		commonDao.checkSessionExistByToken(getToken());
		setUser(commonDao.getUserInfoBySessionToken(getToken()));
		setTrip(commonDao.getTripById(getTripId()));
		return true;
	}

	@Override
	public Object processService() throws IOException {
		
		String tripFolderPath = Constraints.FULLPATH + File.separator + 
				Constraints.TRIPFOLDER; //  -- db/trip		
		String tripFolderName = "trip_" + tripId.toString();	
		String tripFolderWithName = tripFolderPath + File.separator + tripFolderName; // 	/db/trip/trip_x
		
		String temporaryFolderPath = Constraints.FULLPATH + File.separator + 
				Constraints.TEMPORARYFOLDER; //  -- db/temporary

		
		if(!file.isEmpty()){
			
			byte[] bytes = file.getBytes();
			
			FileUtils.deleteQuietly(new File(temporaryFolderPath));
			CommonFuncs.checkDirectoryExistIfNotCreate(temporaryFolderPath);

			String temporaryFolderPathWithZipName = temporaryFolderPath + File.separator +  getFileName() + ".zip";
			File zipStream = new File(temporaryFolderPathWithZipName);
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(zipStream));
			stream.write(bytes);
			stream.close();
			
		    try {
		        ZipFile zipFile = new ZipFile(temporaryFolderPathWithZipName);
		        zipFile.extractAll(temporaryFolderPath);
		        FileUtils.deleteQuietly(new File(temporaryFolderPathWithZipName));
		    } catch (ZipException e) {
		        e.printStackTrace();
		    }			
			readMediaMetadataAndWriteMediaNamesToDB(temporaryFolderPath);
			mergeMediaMetadataFiles(temporaryFolderPath,tripFolderWithName,tripFolderWithName);
			copyMediaFiles(  // trip/trip_x/media
					temporaryFolderPath + File.separator + Constraints.TRIPMEDIAFOLDER,
					tripFolderWithName + File.separator + Constraints.TRIPMEDIAFOLDER
					);
			
			PersonalSharing ps = new PersonalSharing();
			ps.setTrip_id(tripId);
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			
			try{
				commonDao.createPersonalSharing(getUser(), ps, now); 
			}catch(Exception ex){
				throw new IOException("Personal sharing oluşturmada hata oluştu");
			}
		    
		}
		return true;
	}
	
	
	public Boolean readMediaMetadataAndWriteMediaNamesToDB(String absoluteFolder){
		ObjectMapper mapper = new ObjectMapper();
		try {
			MediaMetadata[] mediaMetadata = mapper.readValue(new File(absoluteFolder + File.separator 
					+  "media_metadata.JSON"), MediaMetadata[].class);
			for(MediaMetadata e : mediaMetadata){
				e.setPrivacyType(CommonFuncs.privacyTypeCozumle(e.getShare_option()));
				tripDao.createTripMedia(e,getTrip(),getUser().getId(),getUser().getUsername());
			}
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	public Boolean mergeMediaMetadataFiles(String sourceFilePath, String destFilePath, String finalFilePath){
		ObjectMapper mapper = new ObjectMapper();
		List<MediaMetadata> allMedias = new ArrayList<MediaMetadata>();
		try {
			MediaMetadata[] mediaMetadata = mapper.readValue(new File(sourceFilePath + File.separator + "media_metadata.JSON"), MediaMetadata[].class);
			for(MediaMetadata e : mediaMetadata){
				allMedias.add(e);
			}
			
			mediaMetadata = mapper.readValue(new File(destFilePath + File.separator + "media_metadata.JSON"), MediaMetadata[].class);
			for(MediaMetadata e : mediaMetadata){
				allMedias.add(e);
			}			
			
			File realFile = new File(finalFilePath + File.separator + "media_metadata.JSON");
			if (realFile.exists())
			FileUtils.deleteQuietly(realFile);
			
			final ByteArrayOutputStream out = new ByteArrayOutputStream();
			mapper.writeValue(out, allMedias);	
			String stringJSON = new String(out.toByteArray());
			writeFile(finalFilePath,stringJSON);
			
			
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;		
	}
	
	public Boolean copyMediaFiles(String sourceFolder,String destFolder) throws IOException{
			File sourceFolderFiles = new File(sourceFolder + File.separator);
			File destFilePath = new File(destFolder + File.separator);
			File[] listOfFiles = sourceFolderFiles.listFiles();
			for (File file : listOfFiles) {
			    if (file.isFile()) {
			    	String fileName = FilenameUtils.getName(file.getAbsolutePath());
			    	File copiedFile = new File(destFilePath + File.separator + fileName);
			    	CommonFuncs.copyFileAsFileType(file, copiedFile);
			    	//Files.copy(file.toPath(), destFilePath.toPath(), StandardCopyOption.REPLACE_EXISTING);
			    }
			}
		return true;
	}
	
	
	
	public Boolean writeFile(String path,String fileContent){
		FileOutputStream fop = null;
		File file;
		String content = fileContent;

		try {

			file = new File(path + File.separator +  "media_metadata.JSON");
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

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	
	
	
	
	

}
