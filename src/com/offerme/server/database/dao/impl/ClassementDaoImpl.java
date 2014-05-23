package com.offerme.server.database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.offerme.server.database.dao.ClassementDao;
import com.offerme.server.database.model.Classement;
import com.offerme.server.util.Log;
import com.offerme.server.util.ResultSetToModel;

public class ClassementDaoImpl implements ClassementDao{
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	

	public ClassementDaoImpl(Connection conn) {
		this.conn = conn;
	}
	
	public int insertClassement(Classement classement) throws Exception
	{
		String sql = "Insert into classement (NAME,LIB)Values(?,?)";
		int idReturn= -1;
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, classement.getName());
			this.pstmt.setString(2, classement.getLib());
			
			ResultSet rs=null;
			this.pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
            	idReturn = rs.getInt(1);
            }
            classement.setClassmentId(idReturn);
			this.pstmt.close();
			this.conn.close();
			return idReturn;
		} catch (SQLException e) {
			
			throw new Exception(Log.getStackInfo(e)); 
		}
	}
    
    public void updateClassement(Classement classement) throws Exception
    {
    	String sql = "update classement " +
    			" set NAME=?,LIB=? " +
    			" where  CLASSMENT_ID = ?";
		int idReturn= -1;
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, classement.getName());
			this.pstmt.setString(2, classement.getLib());
			this.pstmt.setInt(3, classement.getClassmentId());
			
			ResultSet rs=null;
			this.pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
            	idReturn = rs.getInt(1);
            }
            classement.setClassmentId(idReturn);
			this.pstmt.close();
			this.conn.close();
		} catch (SQLException e) {
			
			throw new Exception(Log.getStackInfo(e)); 
		}
    }
    
    public void deleteClassement(int classementId) throws Exception
    {
    	String sql = "delete classement " +
				" where CLASSMENT_ID = ?";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, classementId);
			this.pstmt.executeUpdate();
			this.pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public Classement getClassement(int classementId) throws Exception
    {
    	String sql = "select CLASSMENT_ID as classmentId, NAME, LIB from classement " +
				" where CLASSMENT_ID = ?";
    	Classement classement = null;
    	try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, classementId);
			ResultSet rs = this.pstmt.executeQuery();
			try {
				Object[] obj = ResultSetToModel.parseDataEntityBeans(rs, "com.offerme.server.database.model.Classement");
				classement = (Classement)obj[0];
				
			} catch (Exception e) {
				
				throw new Exception(Log.getStackInfo(e)); 
			}
			this.pstmt.close();
		} catch (SQLException e) {
			
			throw new Exception(Log.getStackInfo(e)); 
		} 	
    	return classement;
    }

}
