package com.shaswat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shaswat.models.Story;

public interface IStoryRepository extends JpaRepository<Story, Integer>{
	
	public List<Story> findByUserId(Integer userId);
}
