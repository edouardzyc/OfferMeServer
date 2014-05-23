package com.offerme.server.database.dao;

import com.offerme.server.database.model.Message;

public interface MessageDao {
		
	public int insertMessage(Message message) throws Exception;
    
    public void updateMessage(Message message) throws Exception;
    
    public void deleteMessage(int messageId) throws Exception;
    
    public Message getMessage(int messageId) throws Exception;

}
