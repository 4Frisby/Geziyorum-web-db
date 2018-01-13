package com.geziyorum.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import com.geziyorum.entity.Trip;
import com.geziyorum.entity.User;
import com.geziyorum.methods.generals.CommonFuncs;
import com.geziyorum.methods.generals.Constraints;

@Service
public class UploadTripMediaService implements GeneralProcess{

	@Autowired
	CommonDao commonDao;
	
	@Autowired
	TripDao tripDao;
	
	private String uzanti;
	private MultipartFile file;
	private String token;
	private String not;
	private Integer kimGorebilir;
	private Double longitude;
	private Double latitude;
	private Double altitude;
	private Integer tripId;
	private Integer dosyaTipi;
	
	
	private String fileNameWithExtension;
	private User user;
	private Trip trip;
	
	@Override
	public Object startService() throws IOException {
		validateService();
		return processService();
	}

	@Override
	public Object validateService() throws IOException {
		commonDao.checkSessionExistByToken(token);
		user = commonDao.getUserInfoBySessionToken(token);
		trip = commonDao.getTripById(tripId);
		return true;
	}

	@Override
	public Object processService() throws IOException {
		dosyaUzantiKontrol(uzanti);
		this.fileNameWithExtension = UUID.randomUUID().toString().replace("-","") + "." + uzanti;
		String tripFolderPath = Constraints.FULLPATH + File.separator + 
				Constraints.TRIPFOLDER;
		String tripFolderName = "trip_" + tripId.toString();
		String tripFolderWithName = tripFolderPath + File.separator + tripFolderName;
		String tripMediaFolderWithFileName = tripFolderWithName + File.separator +
				Constraints.TRIPMEDIAFOLDER + File.separator +fileNameWithExtension;
		MediaMetadata mm = generateMediaMetadata();
		mergeMediaMetadatas(tripFolderWithName,mm);
		tripDao.createTripMedia(mm,trip,getUser().getId(),getUser().getUsername());
		convert(file,tripMediaFolderWithFileName);
		
		return true;
	}

	public File convert(MultipartFile file,String fileFolderWithName) throws IOException
	{    
	    File convFile = new File(fileFolderWithName);
	    convFile.createNewFile(); 
	    FileOutputStream fos = new FileOutputStream(convFile); 
	    fos.write(file.getBytes());
	    fos.close(); 
	    return convFile;
	}
	
	public Boolean dosyaUzantiKontrol(String uzanti) throws IOException{
		if(uzanti.equals("jpg") || uzanti.equals("jpeg") ||
				uzanti.equals("png") || uzanti.equals("mp4") || uzanti.equals("mp3")
				|| uzanti.equals("m4v") || uzanti.equals("avi") || uzanti.equals("mpg"))
			return true;
		else
			throw new IOException("Sadece c uzantılı dosyaları yükleyebilirsiniz.");
	}
	
	public MediaMetadata generateMediaMetadata(){
		
		MediaMetadata mm = new MediaMetadata();
		Integer privacy = 4;
		if(kimGorebilir == 0)
			privacy = 4;
		if(kimGorebilir == 1)
			privacy = 5;
		if(kimGorebilir == 2)
			privacy = 6;
		mm.setPrivacyType(privacy);
		mm.setAltitude(altitude);
		mm.setLatitude(latitude);
		mm.setLongitude(longitude);
		mm.setPath(fileNameWithExtension);
		mm.setShare_option(CommonFuncs.privacyTypeCozumleTersYon(kimGorebilir));
		mm.setType(CommonFuncs.extensionCozumle(dosyaTipi));
		mm.setNote(not);
		return mm;
		
	}
	
	public void mergeMediaMetadatas(String filePath,MediaMetadata mm){
		ObjectMapper mapper = new ObjectMapper();
		String metadataFile = filePath + File.separator + "media_metadata.JSON";
		List<MediaMetadata> allMedias = new ArrayList<MediaMetadata>();
		try {
			MediaMetadata[] mediaMetadata = mapper.readValue(new File(metadataFile), MediaMetadata[].class);
			for(MediaMetadata e : mediaMetadata){
				allMedias.add(e);
			}
			allMedias.add(mm);
			File realFile = new File(metadataFile);
			if (realFile.exists())
			FileUtils.deleteQuietly(realFile);
			
			final ByteArrayOutputStream out = new ByteArrayOutputStream();
			mapper.writeValue(out, allMedias);	
			String stringJSON = new String(out.toByteArray());
			writeFile(metadataFile,stringJSON);
			
		}catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean writeFile(String path,String fileContent){
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
	
	
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUzanti() {
		return uzanti;
	}

	public void setUzanti(String uzanti) {
		this.uzanti = uzanti;
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

	public String getNot() {
		return not;
	}

	public void setNot(String not) {
		this.not = not;
	}

	public Integer getKimGorebilir() {
		return kimGorebilir;
	}

	public void setKimGorebilir(Integer kimGorebilir) {
		this.kimGorebilir = kimGorebilir;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	public Integer getDosyaTipi() {
		return dosyaTipi;
	}

	public void setDosyaTipi(Integer dosyaTipi) {
		this.dosyaTipi = dosyaTipi;
	}

	public String getFileNameWithExtension() {
		return fileNameWithExtension;
	}

	public void setFileNameWithExtension(String fileNameWithExtension) {
		this.fileNameWithExtension = fileNameWithExtension;
	}
	
	

}
