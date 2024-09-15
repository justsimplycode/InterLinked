package com.shaswat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shaswat.models.Reels;

public interface IReelsRepository extends JpaRepository<Reels, Integer>{
	
	public List<Reels> findByUserId(Integer userId);
}
