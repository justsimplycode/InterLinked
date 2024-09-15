package com.shaswat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaswat.models.Reels;
import com.shaswat.models.User;
import com.shaswat.repository.IReelsRepository;

@Service
public class ReelsService implements IReelsService{
	
	@Autowired
	private IReelsRepository reelsRepository;
	
	@Autowired
	private IUserService userService;
	
	@Override
	public Reels CreateReel(Reels reel, User user) {
		
		Reels createReel = new Reels();
		
		createReel.setTitle(reel.getTitle());
		createReel.setUser(user);
		createReel.setVideo(reel.getVideo());
		
		return reelsRepository.save(createReel);
	}

	@Override
	public List<Reels> FindAllReels() {
		
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> FindUsersReel(Integer userId) throws Exception {
		
		User user = userService.FindUserById(userId);
		
		return reelsRepository.findByUserId(user.getId());
	}

}
