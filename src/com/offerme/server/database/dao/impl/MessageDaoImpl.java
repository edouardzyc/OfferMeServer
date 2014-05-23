package com.offerme.server.database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.offerme.server.database.dao.MessageDao;
import com.offerme.server.database.dao.UserDao;
import com.offerme.server.database.dao.proxy.DaoImplManage;
import com.offerme.server.database.model.Message;
import com.offerme.server.database.model.User;
import com.offerme.server.util.Log;
import com.offerme.server.util.ResultSetToModel;

public class MessageDaoImpl implements MessageDao{
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	private UserDao userDao;

	public MessageDaoImpl(Connection conn) {
		this.conn = conn;
		this.userDao = DaoImplManage.getUserDaoInstance(conn);
	}
	
	public int insertMessage(Message message) throws Exception
	{
		String sql = "Insert into message (SUSER_ID,RUSER_ID,TITLE,CONTENT,DATE)Values(?,?,?,?,?)";
		int idReturn= -1;
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, message.getsUserId());
			this.pstmt.setInt(2, message.getrUserId());
			this.pstmt.setString(3, message.getTitle());
			this.pstmt.setString(4, message.getContent());
			this.pstmt.setTimestamp(5, message.getDate());
			
			ResultSet rs=null;
			this.pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
            	idReturn = rs.getInt(1);
            }
            message.setMessageId(idReturn);
			this.pstmt.close();
			return idReturn;
		} catch (SQLException e) {
			
			throw new Exception(Log.getStackInfo(e)); 
		}
	}
    
    public void updateMessage(Message message) throws Exception
    {
    	String sql = "update message " +
    			" set SUSER_ID=?,RUSER_ID=?,TITLE=?,CONTENT=?,DATE=?" +
    			" where MESSAGE_ID = ?";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, message.getsUserId());
			this.pstmt.setInt(2, message.getrUserId());
			this.pstmt.setString(3, message.getTitle());
			this.pstmt.setString(4, message.getContent());
			this.pstmt.setTimestamp(5, message.getDate());
			this.pstmt.setInt(6, message.getMessageId());
			
			this.pstmt.executeUpdate();
			
			this.pstmt.close();
		} catch (SQLException e) {
			
			throw new Exception(Log.getStackInfo(e)); 
		}
    }
    
    public void deleteMessage(int messageId) throws Exception
    {
    	String sql = "delete message " +
				" where MESSAGE_ID = ?";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, messageId);
			this.pstmt.executeUpdate();
			this.pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public Message getMessage(int messageId) throws Exception
    {
    	String sql = "select MESSAGE_ID as messageId, SUSER_ID as sUserId, RUSER_ID as rUserId,TITLE,CONTENT,DATE from message " +
				" where MESSAGE_ID = ?";
    	Message message = null;
    	try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, messageId);
			ResultSet rs = this.pstmt.executeQuery();
			try {
				Object[] obj = ResultSetToModel.parseDataEntityBeans(rs, "com.offerme.server.database.model.Message");
				message = (Message)obj[0];
				User user = userDao.getUser(message.getsUserId());
				message.setSUser(user);
				user = userDao.getUser(message.getrUserId());
				message.setRUser(user);
//				data = JSONUtil.getDataJSON(rs);
			} catch (Exception e) {
				
				throw new Exception(Log.getStackInfo(e)); 
			}
			this.pstmt.close();
		} catch (SQLException e) {
			
			throw new Exception(Log.getStackInfo(e)); 
		} 	
    	return message;
    	
    }

}
