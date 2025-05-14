package com.so.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.so.models.Message;
import com.so.models.User;
import com.so.service.MessageService;
import com.so.service.UserService;

@RestController
public class CreateMessage {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/api/messages/chat/{chatId}")
	public Message createMessage(@RequestBody Message req, @RequestHeader ("Authorization")String jwt, @PathVariable Integer chatId) throws Exception {
		
		
		User user = userService.findUserByJwt(jwt);
		Message message = messageService.createMessage(user, chatId, req);
		
		return message;
	}
	
	
	@GetMapping("/api/messages/chat/{chatId}")
	public List<Message> findChatsMessages( @RequestHeader ("Authorization")String jwt, @PathVariable Integer chatId) throws Exception {
		
		
		User user = userService.findUserByJwt(jwt);
		
		List<Message> messages = messageService.findChatsMessages(chatId);
		
		return messages;
	}
	
	
}
