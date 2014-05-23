package com.offerme.server.database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.offerme.server.database.dao.ClassementDetailDao;
import com.offerme.server.database.dao.UserDao;
import com.offerme.server.database.dao.proxy.DaoImplManage;
import com.offerme.server.database.model.User;
import com.offerme.server.util.Log;
import com.offerme.server.util.ResultSetToModel;

public class UserDaoImpl implements UserDao{
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	private ClassementDetailDao classementDetailDao;

	public UserDaoImpl(Connection conn) {
		this.conn = conn;
		this.classementDetailDao = DaoImplManage.getClassementDetailDaoInstance(conn);
	}
	
	  /**
     * 释放连接
     */
    public void free() throws Exception
    {
    	if(this.conn != null)
    	{
    		this.conn.close();
    	}
    }

	public int insertUser(User user) throws Exception
	{		
		String sql = "Insert into user (NAME,NAME_FAMILY,CITY,COUNTRY,COMPANY,NICKNAME,EMAIL,PHONE,PSW,PORTRAIT,SIGNUPDATE,SIGNINTIME,SIGNLASTDATE,ISENABLE)Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int idReturn= -1;
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, user.getName());
			this.pstmt.setString(2, user.getNameFamily());
			this.pstmt.setString(3, user.getCity());
			this.pstmt.setString(4, user.getCountry());
			this.pstmt.setString(5, user.getCompany());
			this.pstmt.setString(6, user.getNickName());
			this.pstmt.setString(7, user.getEmail());
			this.pstmt.setString(8, user.getPhone());
			this.pstmt.setString(9, user.getPsw());
			this.pstmt.setBytes(10, user.getPortrait());
			this.pstmt.setTimestamp(11, user.getSignUpDate());
			this.pstmt.setInt(12, user.getSignInTime());
			this.pstmt.setTimestamp(13, user.getSignLastDate());
			this.pstmt.setInt(14, user.getIsEnable());
			
			ResultSet rs=null;
			this.pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
            	idReturn = rs.getInt(1);
            }
            
			this.pstmt.close();
			return idReturn;
		} catch (SQLException e) {
			
			throw new Exception(Log.getStackInfo(e)); 
		}
	}
    
    public void updateUser(User user) throws Exception
    {
    	String sql = "update user " +
    			"set NAME=?,NAME_FAMILY=?,CITY=?,COUNTRY=?,COMPANY=?,NICKNAME=?,EMAIL=?,PHONE=?,PSW=?,PORTRAIT=?,SIGNUPDATE=?,SIGNINTIME=?,SIGNLASTDATE=?,ISENABLE=? " +
    			"where user_id=?";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, user.getName());
			this.pstmt.setString(2, user.getNameFamily());
			this.pstmt.setString(3, user.getCity());
			this.pstmt.setString(4, user.getCountry());
			this.pstmt.setString(5, user.getCompany());
			this.pstmt.setString(6, user.getNickName());
			this.pstmt.setString(7, user.getEmail());
			this.pstmt.setString(8, user.getPhone());
			this.pstmt.setString(9, user.getPsw());
			this.pstmt.setBytes(10, user.getPortrait());
			this.pstmt.setTimestamp(11, user.getSignUpDate());
			this.pstmt.setInt(12, user.getSignInTime());
			this.pstmt.setTimestamp(13, user.getSignLastDate());
			this.pstmt.setInt(14, user.getIsEnable());
			this.pstmt.setInt(15, user.getUserId());
			this.pstmt.executeUpdate();
		} catch (SQLException e) {
			
			throw new Exception(Log.getStackInfo(e)); 
		}
	}
    
    public void disableUser(int userId) throws Exception
    {
    	String sql = "update user " +
    			" set isenable = 0"+
				" where user_id = ?";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, userId);
			this.pstmt.executeUpdate();
			this.pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void deleteUser(int userId) throws Exception
    {
    	String sql = "delete user " +
				" where user_id = ?";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, userId);
			this.pstmt.executeUpdate();
			this.pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public User getUser(int userId) throws Exception
    {
    	String sql = "select USER_ID as userId, NAME,NAME_FAMILY as nameFamily,CITY,COUNTRY,COMPANY,NICKNAME,EMAIL,PHONE,PSW,PORTRAIT,SIGNUPDATE,SIGNINTIME,SIGNLASTDATE,ISENABLE from user " +
				" where user_id = ?";
    	User user = null;
    	try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, userId);
			ResultSet rs = this.pstmt.executeQuery();
			try {
				Object[] obj = ResultSetToModel.parseDataEntityBeans(rs, "com.offerme.server.database.model.User");
				user = (User)obj[0];
//				data = JSONUtil.getDataJSON(rs);
			} catch (Exception e) {
				
				throw new Exception(Log.getStackInfo(e)); 
			}
			this.pstmt.close();
		} catch (SQLException e) {
			
			throw new Exception(Log.getStackInfo(e)); 
		} 	
    	return user;
    }
    
    public User[] getAllUser() throws Exception
    {
    	String sql = "select USER_ID as userId, NAME,NAME_FAMILY as nameFamily,CITY,COUNTRY,COMPANY,NICKNAME,EMAIL,PHONE,PSW,PORTRAIT,SIGNUPDATE,SIGNINTIME,SIGNLASTDATE,ISENABLE from user ";
    	User[] userArray = null;
    	try {
			this.pstmt = this.conn.prepareStatement(sql);
			ResultSet rs = this.pstmt.executeQuery();
			try {
//				data = JSONUtil.getDataJSON(rs);
				Object[] obj = ResultSetToModel.parseDataEntityBeans(rs, "com.offerme.server.database.model.User");
				userArray = (User[])obj;
			} catch (Exception e) {
				throw new Exception(Log.getStackInfo(e)); 
			}
			this.pstmt.close();
		} catch (SQLException e) {
			throw new Exception(Log.getStackInfo(e)); 
		} 	
    	return userArray;
    }
    
}
