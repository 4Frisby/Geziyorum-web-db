package com.geziyorum.controller.search;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.geziyorum.dao.AramaDao;
import com.geziyorum.entity.AramaSession;

@RestController
public class AramaKaydiGenelController {
	
	@Autowired
	AramaDao aramaDao;
	
    @RequestMapping(method = RequestMethod.POST, value="/aramaKaydiOlustur")
    public Object getUserGeneralInfo(@RequestBody AramaSession aramaSession) throws IOException{
    	aramaDao.aramaKaydiSil(aramaSession.getToken());
    	aramaDao.aramaKaydiOlustur(aramaSession);
    	return true;
    }

    @RequestMapping(method = RequestMethod.POST, value="/aramaKaydiGetir")
    public Object aramaKaydiGetir(@RequestBody String token) throws IOException{
    	if(aramaDao.checkAramaKaydi(token)){
    		AramaSession as = aramaDao.aramaKaydiGetir(token);
    		aramaDao.aramaKaydiSil(token);
    		return as;
    	}
    	else{
    		return false;	
    	}

    }
    
    
}
