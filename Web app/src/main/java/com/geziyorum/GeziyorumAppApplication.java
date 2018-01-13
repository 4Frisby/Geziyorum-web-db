package com.geziyorum;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.geziyorum.methods.generals.CommonFuncs;
import com.geziyorum.methods.generals.Constraints;


@SpringBootApplication
public class GeziyorumAppApplication {

	public static void main(String[] args) {
		//CommonFuncs.setConfigurationRegardingPlatform(2); // 1 - windows , 2 - linux
		CommonFuncs.checkDirectoryExistIfNotCreate(Constraints.FULLPATH);
		CommonFuncs.checkDirectoryExistIfNotCreate(Constraints.FULLPATH + File.separator + Constraints.PPFOLDER);
		CommonFuncs.checkDirectoryExistIfNotCreate(Constraints.FULLPATH + File.separator + Constraints.TRIPFOLDER);
		
		SpringApplication.run(GeziyorumAppApplication.class, args);
	}
	
	
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
	    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
	    characterEncodingFilter.setForceEncoding(true);
	    characterEncodingFilter.setEncoding("UTF-8");
	    registrationBean.setFilter(characterEncodingFilter);
	    return registrationBean;
	}
	
}
