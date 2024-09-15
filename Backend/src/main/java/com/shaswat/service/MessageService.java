package com.shaswat.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaswat.models.Chat;
import com.shaswat.models.Message;
import com.shaswat.models.User;
import com.shaswat.repository.IChatRepository;
import com.shaswat.repository.IMessageRepository;

@Service
public class MessageService implements IMessageService{
	
	@Autowired
	private IMessageRepository messageRepository;
	
	@Autowired
	private IChatService chatService;
	
	@Autowired
	private IChatRepository chatRepository;
	
	@Override
	public Message CreateMessage(User user, Integer chatId, Message req) throws Exception {
		
		Message message = new Message();
		
		Chat chat = chatService.FindChatById(chatId);
		
		message.setChat(chat);
		message.setContent(req.getContent());
		message.setImage(req.getImage());
		message.setUser(user);
		message.setTimeStamp(LocalDateTime.now());
		
		Message savedMessage = messageRepository.save(message);
		
		chat.getMessages().add(savedMessage);
		chatRepository.save(chat);
		
		return savedMessage;
	}

	@Override
	public List<Message> FindChatsMessages(Integer chatId) throws Exception {

		Chat chat = chatService.FindChatById(chatId);
		
		return messageRepository.findByChatId(chatId);
	}

}
