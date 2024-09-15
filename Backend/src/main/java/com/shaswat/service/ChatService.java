package com.shaswat.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaswat.models.Chat;
import com.shaswat.models.User;
import com.shaswat.repository.IChatRepository;

@Service
public class ChatService implements IChatService{
	
	@Autowired
	private IChatRepository chatRepository;
	
	@Override
	public Chat CreateChat(User reqUser, User user) {
		
		Chat doesExist = chatRepository.findChatByUsersId(user, reqUser);
		
		if(doesExist != null) return doesExist;
		
		Chat chat = new Chat();
		chat.getUsers().add(user);
		chat.getUsers().add(reqUser);
		chat.setTimeStamp(LocalDateTime.now());
		
		chatRepository.save(chat);
		
		return chat;
	}

	@Override
	public Chat FindChatById(Integer chatId) throws Exception {
		
		Optional<Chat> chat = chatRepository.findById(chatId);
		
		if(chat.isEmpty()) throw new Exception("chat not found with id - " + chatId);
		
		return chat.get();
	}

	@Override
	public List<Chat> FindUsersChat(Integer userId) {

		return chatRepository.findByUsersId(userId);
	}
	
}
