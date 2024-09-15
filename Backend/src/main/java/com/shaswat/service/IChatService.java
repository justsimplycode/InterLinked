package com.shaswat.service;

import java.util.List;

import com.shaswat.models.*;

public interface IChatService {
	
	public Chat CreateChat(User reqUser, User user);
	
	public Chat FindChatById(Integer chatId) throws Exception;
	
	public List<Chat> FindUsersChat(Integer userId);
}
