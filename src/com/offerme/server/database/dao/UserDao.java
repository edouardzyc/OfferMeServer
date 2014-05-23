package com.offerme.server.database.dao;

import com.offerme.server.database.model.User;

public interface UserDao {
	
	public void free() throws Exception;
	
	public int insertUser(User user) throws Exception;
	    
    public void updateUser(User user) throws Exception;
    
    public void disableUser(int userId) throws Exception;
    
    public void deleteUser(int userId) throws Exception;
    
    public User getUser(int userId) throws Exception;
    
    public User[] getAllUser() throws Exception;

}
