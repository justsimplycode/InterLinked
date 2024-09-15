package com.shaswat.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaswat.models.*;
import com.shaswat.repository.IStoryRepository;

@Service
public class StoryService implements IStoryService{
	
	@Autowired
	private IStoryRepository storyRepository;
	
	@Autowired
	private IUserService userService;
	
	@Override
	public Story CreateStory(Story story, User user) {
		
		Story newStory = new Story();
		
		newStory.setCaptions(story.getCaptions());
		newStory.setImage(story.getImage());
		newStory.setUser(user);
		newStory.setTimeStamp(LocalDateTime.now());
		
		return storyRepository.save(newStory);
	}

	@Override
	public List<Story> FindStoryByUserId(Integer userId) throws Exception {
		
		User user = userService.FindUserById(userId);
		
		return storyRepository.findByUserId(user.getId());
	}

}
