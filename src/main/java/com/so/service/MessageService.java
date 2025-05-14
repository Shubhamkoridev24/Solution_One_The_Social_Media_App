package com.so.service;

import java.util.List;

import com.so.models.Chat;
import com.so.models.Message;
import com.so.models.User;

public interface MessageService {

	public Message createMessage(User user,Integer chatId, Message req) throws Exception;
	
	public List<Message> findChatsMessages(Integer chatId) throws Exception;
	
	
}
