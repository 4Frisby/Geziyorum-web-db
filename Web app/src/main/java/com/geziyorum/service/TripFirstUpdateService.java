package com.geziyorum.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
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
import com.geziyorum.entity.PathMetadata;
import com.geziyorum.entity.PersonalSharing;
import com.geziyorum.entity.Trip;
import com.geziyorum.entity.TripMetadata;
import com.geziyorum.entity.User;
import com.geziyorum.methods.generals.CommonFuncs;
import com.geziyorum.methods.generals.Constraints;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

@Service
public class TripFirstUpdateService implements GeneralProcess{
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

	User user = new User();
	Trip trip = new Trip();
	String totalAdress = "";
	TripMetadata tripMetadata = new TripMetadata();
	String geziYapilisSekli = "";
	
	@Override
	public Object startService() throws IOException {
		validateService();
		processService();
		return true;
	}

	@Override
	public Object validateService() throws IOException {
		commonDao.checkSessionExistByToken(getToken());
		setUser(commonDao.getUserInfoBySessionToken(getToken()));
		setTrip(tripDao.getTripByTripId(tripId));
		return true;
	}

	@Override
	public Object processService() throws IOException {
		
		String tripFolderPath = Constraints.FULLPATH + File.separator + 
				Constraints.TRIPFOLDER; //  -- db/trip
		String tripFolderName = "trip_" + tripId.toString();	// trip_x -- tip folder
		if (!file.isEmpty()) {
			

			byte[] bytes = file.getBytes();
			String absoluteFolder= tripFolderPath +File.separator+ tripFolderName + File.separator;  // -- trip/trip_x
			
			FileUtils.deleteQuietly(new File(absoluteFolder));
			CommonFuncs.checkDirectoryExistIfNotCreate(absoluteFolder);
			
			String absoluteFolderWithZipName = absoluteFolder + getFileName() + ".zip";  // -- trip/trip_x/bakininzipadi.zip
			File serverFile = new File(absoluteFolderWithZipName); // zip alındığıisimle yazıldı.
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
		    try {
		        ZipFile zipFile = new ZipFile(absoluteFolderWithZipName);
		        zipFile.extractAll(absoluteFolder);
		        File kapakFile = new File(absoluteFolder + "kapak.jpg");
	        		if(!kapakFile.exists())		        
		        CommonFuncs.copyFile(Constraints.FULLPATH + File.separator + "kapak.jpg", absoluteFolder + "kapak.jpg");   
	        	FileUtils.deleteQuietly(new File(absoluteFolderWithZipName));
		    } catch (ZipException e) {
		        e.printStackTrace();
		    }
		    
		    readPathMetadataGetAdress(absoluteFolder);
		    readTripMetadataGetTripName(absoluteFolder);
			try{
				Timestamp startDate = new Timestamp(tripMetadata.getStartdate());
				Timestamp finishDate = new Timestamp(tripMetadata.getFinishdate());
				tripDao.updateExistingTrip(getUser(),tripFolderName,getTotalAdress(),
						tripMetadata.getName(),startDate, finishDate, tripId, geziYapilisSekli);
			}catch(Exception ex){
				throw new IOException("İlk trip update'in yapılmasında hata");
			}

			readMediaMetadataAndWriteMediaNamesToDB(absoluteFolder);
			iteratePathsFolderAndWriteItToDB(absoluteFolder);

			PersonalSharing ps = new PersonalSharing();
			ps.setTrip_id(tripId);
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			
			try{
				commonDao.createPersonalSharing(getUser(), ps, now); 
			}catch(Exception ex){
				throw new IOException("Personal sharing oluşturmada hata oluştu");
			}			
			return "Dosya başarıyla yüklendi =" + absoluteFolder;

		} else {
			return "Yüklemede hata alındı " + file.getName()
					+ " dosya boş.";
		}
	}

	
	public Boolean readMediaMetadataAndWriteMediaNamesToDB(String absoluteFolder){
		ObjectMapper mapper = new ObjectMapper();
		try {
			MediaMetadata[] mediaMetadata = mapper.readValue(new File(absoluteFolder + "media_metadata.JSON"), MediaMetadata[].class);
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
	
	public void readPathMetadataGetAdress(String absoluteFolder){
		CommonFuncs comFun = new CommonFuncs();
		ObjectMapper mapper = new ObjectMapper();
		String totalAdress = "";
		try {
			PathMetadata[] pathMetadata = mapper.readValue(new File(absoluteFolder + "path_metadata.JSON"), PathMetadata[].class);
			for(PathMetadata e : pathMetadata){
				totalAdress += e.getAddress().getCountry() + " " + e.getAddress().getCity() + " " + e.getAddress().getDistrict()+ " " + e.getAddress().getFeature_name()+ " " + e.getAddress().getTown(); 
				
				addToGeziYapilisSekli(comFun.tripTypeResolver(e.getType())); 
			}
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setTotalAdress(totalAdress);		
	}
	
	
	public void readTripMetadataGetTripName(String absoluteFolder){
		ObjectMapper mapper = new ObjectMapper();
		try {
			TripMetadata tripMetadata = mapper.readValue(new File(absoluteFolder + "trip_metadata.JSON"), TripMetadata.class);
			setTripMetadata(tripMetadata);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}	
	
	
	// input -- > trip/userid/trip_x/
	public Boolean iteratePathsFolderAndWriteItToDB(String tripPath) throws IOException{
		String filePath = tripPath + Constraints.TRIPPATHFOLDER + File.separator;
		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();
		ArrayList<Path> pathList = new ArrayList<Path>();
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	
		    	/*CSV okuma*/
		    	String csvFile = folder + File.separator + file.getName(); // folder gezinirken sondaki slash'ı kaldırıyor java.
		        BufferedReader br = null;
		        String line = "";
		        String cvsSplitBy = ",";
		        Integer index = 0; 
	            br = new BufferedReader(new FileReader(csvFile));
		            while ((line = br.readLine()) != null) {
		            	String[] content;
		            	if(index!=0){
			               content = line.split(cvsSplitBy);
			               Path path = new Path();
			               path.setLatitude(Double.parseDouble(content[0]));
			               path.setLongitude((Double.parseDouble(content[1])));
			               path.setAltitude(Double.parseDouble(content[2]));
			               path.setTime(Long.parseLong(content[3]));
			               pathList.add(path);
			               System.out.println("\n");
		            	}
		            	index++;
		            }
		    }
		}
		
		tripDao.createTripPath(pathList, getTrip());
		return true;
	}
	
	public void addToGeziYapilisSekli(String yapilisSekli){
		if(!geziYapilisSekli.contains(yapilisSekli))
			geziYapilisSekli += yapilisSekli;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public String getTotalAdress() {
		return totalAdress;
	}

	public void setTotalAdress(String totalAdress) {
		this.totalAdress = totalAdress;
	}

	public TripMetadata getTripMetadata() {
		return tripMetadata;
	}

	public void setTripMetadata(TripMetadata tripMetadata) {
		this.tripMetadata = tripMetadata;
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	
	
}
