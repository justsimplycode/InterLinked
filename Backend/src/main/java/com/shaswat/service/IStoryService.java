package com.shaswat.service;

import java.util.List;

import com.shaswat.models.*;

public interface IStoryService {
	
	public Story CreateStory(Story story, User user);
	
	public List<Story> FindStoryByUserId(Integer userId) throws Exception;
}
