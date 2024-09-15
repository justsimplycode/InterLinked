package com.shaswat.service;

import java.util.List;

import com.shaswat.models.Message;
import com.shaswat.models.User;

public interface IMessageService {
	
	public Message CreateMessage(User user, Integer chatId, Message req) throws Exception;
	
	public List<Message> FindChatsMessages(Integer chatId) throws Exception;
}
