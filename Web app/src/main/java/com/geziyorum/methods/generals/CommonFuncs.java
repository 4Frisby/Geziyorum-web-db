package com.geziyorum.methods.generals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

import java.io.UnsupportedEncodingException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import com.geziyorum.dao.TypeResolverDao;

public class CommonFuncs{

	@Autowired
	TypeResolverDao typeResolverDao;

	

	//https://stackoverflow.com/questions/35485447/how-can-i-decode-in-java-a-string-generated-by-javascript-using-readasdataurl
	public static Object base64ToObjectArray(String base64File) throws ClassNotFoundException, IOException{
	       	byte [] data = Base64.decodeBase64(base64File);
	        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data) );
	        Object o  = ois.readObject();
	        ois.close();
	        return o;

	}

	
	// java 8 ile base64'ü resim dosyasına yazma metodu.
	public static final boolean writePathBase64AsImageFile(String encodedImg,String pathName,String imgName) throws IOException{
		
			byte[] decodedImg = Base64.decodeBase64(encodedImg);
			Path destinationFile = Paths.get(pathName,"name.png");
			Files.write(destinationFile, decodedImg);			


		return true;
	}
	
	// java 8 ile base64'ü resim dosyasına yazma metodu.
	public static final String readFileFromPathAsBase64(String pathName,String photoName) throws IOException{
		Path destinationFile = Paths.get(pathName, photoName);
		Files.readAllBytes(destinationFile);
		
		
		return "";
	}
	
	public static Boolean deleteFile(String filePath,String fileName){
		try{
			closeOpenedFile(filePath,fileName);
			String path = filePath + fileName;
			Path fileToDeletePath = Paths.get(path);
			Files.deleteIfExists(fileToDeletePath);

    	}catch(Exception e){

    		e.printStackTrace();

    	}	
		return true;
	}
	
	public static Boolean moveFile(File source, File destination) throws IOException{
		Files.move(source.toPath(), destination.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
		//source.renameTo(destination);
		return true;
	}
	
	// java'da işlem yapılan dosyanın kapatılması.
	public static Boolean closeOpenedFile(String filePath,String fileName) throws FileNotFoundException{
		String path = filePath + fileName;
		Scanner s = new Scanner(new File(path));
		try {
		    String sha1Txt = s.useDelimiter("\\Z").next();
		}
		finally {
		    s.close();
		    return true;
		}
	}
	
	public static Boolean checkDirectoryExistIfNotCreate(String ppFilePath){
		File realPath = new File(ppFilePath);
		if (!realPath.exists())
			realPath.mkdirs();
		return true;
	}
	
	public static Integer getType(String typeDef){
		if(typeDef.equals("video")){
			return Constraints.VIDEOTPYE;
		}else{
			if(typeDef.equals("photo")){
				return Constraints.PHOTOTYPE;
			}else
				if(typeDef.equals("record")){
					return Constraints.AUDIOTYPE;
				}else{
					return 9999;
			}
		}
	}
	
	public static String mediaPathParse(String path)
	{
		String[] b = path.split("/");
		return b[b.length-1];
	}
	
	public static String kufurFiltresi(String metin){
		
		
		return metin;
	}


	public static String TipCozumle(Integer type) {
		if(type == Constraints.WHO_CAN_SEE_PUBLICTYPE )
			return "Herkes";
		if(type== Constraints.WHO_CAN_SEE_ONLYFRIENDSTYPE)
			return "Sadece Arkadaşlar";
		
		
		return "Belirsiz";
	}
	
	
	public static Integer privacyTypeCozumle(String type){
		if(type.equals("everybody"))
			return Constraints.WHO_CAN_SEE_PUBLICTYPE;
		
		if(type.equals("only_friends"))
			return Constraints.WHO_CAN_SEE_ONLYFRIENDSTYPE;
		
		if(type.equals("only_me"))	
			return Constraints.WHO_CAN_SEE_ONLYME;
		
		return Constraints.WHO_CAN_SEE_PUBLICTYPE;
	}
	
	public static String privacyTypeCozumleTersYon(Integer type){
			if(type == 0)
			return "everybody";
		
			if(type == 1)
			return "only_friends";
			
			if(type == 2)
			return "only_me";
			
			return "everybody";
		
	}
	
	public static String extensionCozumle(Integer type){
		if(type == 0)
		return "video";
	
		if(type == 1)
		return "photo";
		
		if(type == 2)
		return "record";
		
		return "video";
	}
	
	
	
	public static String ppResolver(String ppName){
		String fullPath = Constraints.RELATIVEPATH + File.separator + 
				Constraints.PPFOLDER + File.separator +   ppName;
		return fullPath;
	}
	
	
	public static Boolean copyFile(String from, String to){
		
    	InputStream inStream = null;
    	OutputStream outStream = null;		
    	try{

    	    File afile =new File(from);
    	    File bfile =new File(to);

    	    inStream = new FileInputStream(afile);
    	    outStream = new FileOutputStream(bfile);

    	    byte[] buffer = new byte[10240];

    	    int length;
    	    //copy the file content in bytes
    	    while ((length = inStream.read(buffer)) > 0){

    	    	outStream.write(buffer, 0, length);

    	    }

    	    inStream.close();
    	    outStream.close();

    	    System.out.println("File is copied successful!");

    	}catch(IOException e){
    		e.printStackTrace();
    	}		
		
    	return true;
	}
	
	public static Boolean copyFileAsFileType(File from, File to){
		
    	InputStream inStream = null;
    	OutputStream outStream = null;		
    	try{

    	    File afile = from;
    	    File bfile = to;

    	    inStream = new FileInputStream(afile);
    	    outStream = new FileOutputStream(bfile);

    	    byte[] buffer = new byte[10240];

    	    int length;
    	    //copy the file content in bytes
    	    while ((length = inStream.read(buffer)) > 0){

    	    	outStream.write(buffer, 0, length);

    	    }

    	    inStream.close();
    	    outStream.close();

    	    System.out.println("File is copied successful!");

    	}catch(IOException e){
    		e.printStackTrace();
    	}		
		
    	return true;
	}	
	
	
	
	public String tripTypeResolver(String type){
		
		if(type.equals("walk"))
			return "Yürüyüş";
		if(type.equals("run"))
			return "Tempolu Yürüyüş";
		if(type.equals("ride"))
			return "Bisiklet";
		if(type.equals("car"))
			return "Araba";
		
		return "Araba";
		//return typeResolverDao.getDescription(type);
	}
	
	
	public static void sendMail(String toMail,String mailBaslik, String sendText){
        final String username = "tariknural00@gmail.com";
        final String password = "e35814922E";
        

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tariknural00@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(toMail));
            message.setSubject(mailBaslik);
            message.setText(sendText);

            Transport.send(message);

            System.out.println("Mail yollandı.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
	}
	
	
	
	
}
