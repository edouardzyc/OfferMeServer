package com.offerme.server.database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.offerme.server.database.dao.ClassementDetailDao;
import com.offerme.server.database.dao.OfferDao;
import com.offerme.server.database.dao.UserDao;
import com.offerme.server.database.dao.proxy.DaoImplManage;
import com.offerme.server.database.model.Offer;
import com.offerme.server.database.model.User;
import com.offerme.server.util.Log;
import com.offerme.server.util.ResultSetToModel;

public class OfferDaoImpl implements OfferDao {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	private UserDao userDao;
	
	private ClassementDetailDao classementDetailDao;

	public OfferDaoImpl(Connection conn) {
		this.conn = conn;
		this.userDao = DaoImplManage.getUserDaoInstance(conn);
		this.classementDetailDao = DaoImplManage.getClassementDetailDaoInstance(conn);
	}
	
	
	public int insertOffer(Offer offer) throws Exception
	{
		String sql = "Insert into offer (USER_ID,TITLE,CONTENT,COMPANY,CITY,COUNTRY,ADDRESS,DATE,STATUS)Values(?,?,?,?,?,?,?,?,?)";
		int idReturn= -1;
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, offer.getUserId());
			this.pstmt.setString(2, offer.getTitle());
			this.pstmt.setString(3, offer.getContent());
			this.pstmt.setString(4, offer.getCompany());
			this.pstmt.setString(5, offer.getCity());
			this.pstmt.setString(6, offer.getCountry());
			this.pstmt.setString(7, offer.getAddress());
			this.pstmt.setTimestamp(8, offer.getDate());
			
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
    
    public void updateOffer(Offer offer) throws Exception
    {
    	String sql = "update offer" +
    			" set USER_ID=?,TITLE=?,CONTENT=?,COMPANY=?,CITY=?,COUNTRY=?,ADDRESS=?,DATE=?,STATUS=?)" +
    			" where OFFER_ID=?";

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, offer.getUserId());
			this.pstmt.setString(2, offer.getTitle());
			this.pstmt.setString(3, offer.getContent());
			this.pstmt.setString(4, offer.getCompany());
			this.pstmt.setString(5, offer.getCity());
			this.pstmt.setString(6, offer.getCountry());
			this.pstmt.setString(7, offer.getAddress());
			this.pstmt.setTimestamp(8, offer.getDate());
			this.pstmt.setInt(9, offer.getOfferId());
			
			this.pstmt.executeUpdate();
			
			this.pstmt.close();
		} catch (SQLException e) {
			
			throw new Exception(Log.getStackInfo(e)); 
		}
    }
    
    public void deleteOffer(int offerId) throws Exception
    {
    	String sql = "delete offer " +
				" where offer_id = ?";
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, offerId);
			this.pstmt.executeUpdate();
			this.pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public Offer getOffer(int offerId) throws Exception
    {
    	String sql = "select OFFER_ID, USER_ID,TITLE,CONTENT,COMPANY,CITY,COUNTRY,ADDRESS,DATE,STATUS from offer " +
				" where OFFER_ID = ?";
    	Offer offer = null;
    	try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, offerId);
			ResultSet rs = this.pstmt.executeQuery();
			try {
				Object[] obj = ResultSetToModel.parseDataEntityBeans(rs, "com.offerme.server.database.model.Offer");
				offer = (Offer)obj[0];
				User user = userDao.getUser(offer.getUserId());
				offer.setOfferUser(user);
//				data = JSONUtil.getDataJSON(rs);
			} catch (Exception e) {
				
				throw new Exception(Log.getStackInfo(e)); 
			}
			this.pstmt.close();
		} catch (SQLException e) {
			
			throw new Exception(Log.getStackInfo(e)); 
		} 	
    	return offer;
    	
    }

}
