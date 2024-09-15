package com.shaswat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.shaswat.models.*;
import com.shaswat.service.IReelsService;
import com.shaswat.service.IUserService;

@RestController
public class ReelsController {
	
	@Autowired
	private IReelsService reelsService;
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/api/reels")
	public Reels CreateReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt) {
		
		User reqUser = userService.FindUserByJwt(jwt);
		Reels createdReels = reelsService.CreateReel(reel, reqUser);
		
		return createdReels;
	}
	
	@GetMapping("/api/reels")
	public List<Reels> FindAllReels() {
		
		List<Reels> reels = reelsService.FindAllReels();
		
		return reels;
	}
	
	@GetMapping("/api/reels/user/{userId}")
	public List<Reels> FindUsersReels(@PathVariable Integer userId) throws Exception {
		
		List<Reels> reels = reelsService.FindUsersReel(userId);
		
		return reels;
	}
}
