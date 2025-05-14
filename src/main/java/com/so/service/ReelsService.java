package com.so.service;

import java.util.List;

import com.so.models.Reels;
import com.so.models.User;

public interface ReelsService {

	
	
	public Reels createReel(Reels reel, User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUsersReel (Integer userId) throws Exception;
	
	
}
