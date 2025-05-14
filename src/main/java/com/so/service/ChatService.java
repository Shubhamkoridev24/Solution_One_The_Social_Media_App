package com.so.service;

import java.util.List;

import com.so.models.Chat;
import com.so.models.User;

public interface ChatService {

	
	public Chat createChat(User reqUser,User user2);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat(Integer userId);
	
	
	
}

