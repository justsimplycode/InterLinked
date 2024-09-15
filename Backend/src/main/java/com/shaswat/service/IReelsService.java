package com.shaswat.service;

import java.util.List;

import com.shaswat.models.*;

public interface IReelsService {
	
	public Reels CreateReel(Reels reel, User user);
	
	public List<Reels> FindAllReels();
	
	public List<Reels> FindUsersReel(Integer userId) throws Exception;
}
