package com.so.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.so.models.Chat;
import com.so.models.User;
import com.so.request.CreateChatRequest;
import com.so.service.ChatService;
import com.so.service.UserService;

@RestController
public class ChatController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ChatService chatService;
	
	
	@PostMapping("/api/chats")
	public Chat createChat(@RequestHeader ("Authorization")String jwt,@RequestBody CreateChatRequest req) throws Exception {
		User reqUser = userService.findUserByJwt(jwt);
		User user2 = userService.findUserById(req.getUserId());
		Chat chat = chatService.createChat(reqUser, user2);
		
		return chat;
	};
	
	@GetMapping("/api/chats")
	public List<Chat> findUsersChat(@RequestHeader ("Authorization")String jwt) {
		
		User user = userService.findUserByJwt(jwt);
		
		List<Chat> chats = chatService.findUsersChat(user.getId());
		
		return chats;
	};
	
	
}
