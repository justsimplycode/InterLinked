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
import com.shaswat.service.IStoryService;
import com.shaswat.service.IUserService;

@RestController
public class StoryController {
	
	@Autowired
	private IStoryService storyService;
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/api/story")
	public Story CreateStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) {
		
		User reqUser = userService.FindUserByJwt(jwt);
		Story createdStory = storyService.CreateStory(story, reqUser);
		
		return createdStory;
	}
	
	@GetMapping("/api/story/user/{userId}")
	public List<Story> FindUserStory(@PathVariable Integer userId, @RequestHeader("Authorization") String jwt) throws Exception {
		
		User reqUser = userService.FindUserByJwt(jwt);
		List<Story> stories = storyService.FindStoryByUserId(userId);
		
		return stories;
	}
}
