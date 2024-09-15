package com.shaswat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.shaswat.models.*;
import com.shaswat.service.IMessageService;
import com.shaswat.service.IUserService;

@RestController
public class MessageController {
	
	@Autowired
	private IMessageService messageService;
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/api/messages/chat/{chatId}")
	public Message CreateMessage(@RequestBody Message req, @PathVariable Integer chatId, @RequestHeader("Authorization") String jwt ) throws Exception {
		
		User user = userService.FindUserByJwt(jwt);
		
		Message message = messageService.CreateMessage(user, chatId, req);
		
		return message;
	}
	
	@GetMapping("/api/messages/chat/{chatId}")
	public List<Message> FindChatsMessages(@PathVariable Integer chatId, @RequestHeader("Authorization") String jwt ) throws Exception {
		
		User user = userService.FindUserByJwt(jwt);
		
		List<Message> messages = messageService.FindChatsMessages(chatId);
		
		return messages;
	}
}
