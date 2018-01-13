package com.geziyorum.controller.redirector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.geziyorum.dao.RegisterPageDao;
import com.geziyorum.methods.generals.WantToSee;

@Controller
public class GeneralRedirector {

		@Autowired
		RegisterPageDao registerPageDao;
	
	   @RequestMapping(value = "/home", method = RequestMethod.GET)
	    public String home(){  
	        return "homepage";
	    } 
	   	   
	   @RequestMapping(value = "/profile", method = RequestMethod.GET)
	    public String profile(){  
	        return "profilepage";
	    } 
	   
	   @RequestMapping(value="/@{username}", method = RequestMethod.GET)
		   public String user(@PathVariable("username") String username){
			   WantToSee.username = username;
			   return "userpage";
		   
	   }
	   
	   @RequestMapping(value="/verify/{verifyCode}", method = RequestMethod.GET)
	   public String verifyCode(@PathVariable("verifyCode") String verifyCode){
		   if(registerPageDao.checkVerifyCodeExist(verifyCode))
		   {
			   String username = registerPageDao.getUserByVerifyCode(verifyCode);
			   registerPageDao.deleteVerifyCode(verifyCode);
			   registerPageDao.setVerified(username);
			   return "verifypage";
		   }
		   return "/";
	   }
	   
	   @RequestMapping(value="/forgotpassword/{randomKey}", method = RequestMethod.GET)
	   public String forgotPassword(@PathVariable("randomKey") String randomKey){
	   
		   if(registerPageDao.checkForgotPasswordRequestExistByRandomKey(randomKey)){
			   WantToSee.forgotPasswordUsername = registerPageDao.getForgotPasswordUserByRandomKey(randomKey);
			   return "recreatepassword";
		   }
		   return "/";
	   }
	   
	   	   
	   @RequestMapping(value = "/support", method = RequestMethod.GET)
	    public String support(){  
	        return "supportpage";
	    } 
	
	   @RequestMapping(value = "/aboutus", method = RequestMethod.GET)
	    public String aboutus(){  
	        return "aboutuspage";
	    } 
	   
	   @RequestMapping(value = "/privacy", method = RequestMethod.GET)
	    public String privacy(){  
	        return "privacypage";
	    } 
	   
	   @RequestMapping(value = "/notifications", method = RequestMethod.GET)
	    public String notifications(){  
	        return "notificationspage";
	    } 
	   
	   @RequestMapping(value = "/adminpanel", method = RequestMethod.GET)
	    public String adminpanel(){  
	        return "adminpanel";
	    } 	   
	
	   @RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
	    public String adminlogin(){  
	        return "adminlogin";
	    } 	 
	   
	   @RequestMapping(value = "/search", method = RequestMethod.GET)
	    public String search(){  
	        return "search";
	    } 	   
	   
}
