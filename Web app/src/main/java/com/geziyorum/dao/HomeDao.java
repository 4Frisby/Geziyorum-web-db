package com.geziyorum.dao;

import java.util.ArrayList;

import com.geziyorum.argumentresolver.HomeGonderi;

public interface HomeDao {
	
	ArrayList<HomeGonderi> getGonderiListOfFriends(Integer userId); 
	
	
}
