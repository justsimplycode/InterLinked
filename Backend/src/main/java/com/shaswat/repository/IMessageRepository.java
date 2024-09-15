package com.shaswat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shaswat.models.Message;

public interface IMessageRepository extends JpaRepository<Message, Integer>{
	
	public List<Message> findByChatId(Integer chatId);
}
