package com.geziyorum.dao;

import java.util.ArrayList;

import com.geziyorum.argumentresolver.SearchResultUser;
import com.geziyorum.argumentresolver.TripSearch;
import com.geziyorum.entity.AramaSession;

public interface AramaDao {
	
	Boolean aramaKaydiOlustur(AramaSession aramaSession);

	Boolean checkAramaKaydi(String token);

	AramaSession aramaKaydiGetir(String token);

	Boolean aramaKaydiSil(String token);

	ArrayList<SearchResultUser> findUserBySearchText(String aramaMetini);
	
	Boolean checkSearchedUserExist(String aramaMetini);

	ArrayList<TripSearch> searchTrip(String aramaMetini, Integer id, String kimlerArasinda, String geziTipi);

}
