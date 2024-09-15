package com.shaswat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.shaswat.models.*;
import com.shaswat.request.CreateChatRequest;
import com.shaswat.service.IChatService;
import com.shaswat.service.IUserService;

@RestController
public class ChatController {
	
	@Autowired
	private IChatService chatService;
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/api/chats")
	public Chat createChat(@RequestHeader("Authorization") String jwt, @RequestBody CreateChatRequest req) throws Exception {
		
		User reqUser = userService.FindUserByJwt(jwt);
		User user2 = userService.FindUserById(req.getUserId());
		Chat chat = chatService.CreateChat(reqUser, user2);
		
		return chat;
	}
	
	@GetMapping("/api/chats")
	public List<Chat> FindUsersChat(@RequestHeader("Authorization") String jwt) {
		
		User user = userService.FindUserByJwt(jwt);
		List<Chat> chats = chatService.FindUsersChat(user.getId());
		
		return chats;
	}
}
