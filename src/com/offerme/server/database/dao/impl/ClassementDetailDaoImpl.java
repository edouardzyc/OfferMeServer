package com.offerme.server.database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.offerme.server.database.dao.ClassementDao;
import com.offerme.server.database.dao.ClassementDetailDao;
import com.offerme.server.database.dao.proxy.DaoImplManage;
import com.offerme.server.database.model.Classement;
import com.offerme.server.database.model.ClassementDetail;
import com.offerme.server.util.Log;
import com.offerme.server.util.ResultSetToModel;

public class ClassementDetailDaoImpl implements ClassementDetailDao{
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	private ClassementDao classementDao;

	public ClassementDetailDaoImpl(Connection conn) {
		this.conn = conn;
		this.classementDao = DaoImplManage.getClassementDaoInstance(conn);
	}
	
	public int insertClassementDetail(ClassementDetail classementDetail) throws Exception
	{
		String sql = "Insert into classementdetail (CLASSEMENT_ID ,P_CLASSEMENT_DETAIL_ID,NAME,LIB)Values(?,?,?,?)";
		int idReturn= -1;
		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, classementDetail.getClassementId());
			this.pstmt.setInt(2, classementDetail.getpClassementDetailId());
			this.pstmt.setString(3, classementDetail.getName());
			this.pstmt.setString(4, classementDetail.getLib());
			
			ResultSet rs=null;
			this.pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
            	idReturn = rs.getInt(1);
            }
            classementDetail.setClassementDetailId(idReturn);
			this.pstmt.close();
			return idReturn;
		} catch (SQLException e) {
			
			throw new Exception(Log.getStackInfo(e)); 
		}
	}
    
    public void updateClassementDetail(ClassementDetail classementDetail) throws Exception
    {
    	String sql = "Update classementdetail " +
    			" set CLASSEMENT_ID=? ,P_CLASSEMENT_DETAIL_ID=?,NAME=?,LIB=?" +
    			" where CLASSMENT_DETAIL_ID=?";

		try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, classementDetail.getClassementId());
			this.pstmt.setInt(2, classementDetail.getpClassementDetailId());
			this.pstmt.setString(3, classementDetail.getName());
			this.pstmt.setString(4, classementDetail.getLib());
			this.pstmt.setInt(5, classementDetail.getClassementDetailId());
			
			this.pstmt.executeUpdate();
			
			this.pstmt.close();
		} catch (SQLException e) {
			
			throw new Exception(Log.getStackInfo(e)); 
		}
    }
    
    public void deleteClassementDetail(int classementDetailId) throws Exception
    {
    	String sql = "delete classementdetail " +
    			" where CLASSMENT_DETAIL_ID=?";
    	try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, classementDetailId);
			
			this.pstmt.executeUpdate();
			
			this.pstmt.close();
		} catch (SQLException e) {
			
			throw new Exception(Log.getStackInfo(e)); 
		}
    }
    
    public ClassementDetail getClassementDetail(int classementDetailId) throws Exception
    {
    	String sql = "select CLASSMENT_DETAIL_ID as classementDetailId, CLASSEMENT_ID as classementId, P_CLASSEMENT_DETAIL_ID as pClassementDetailId, NAME, LIB from classementdetail " +
				" where CLASSMENT_ID = ?";
    	ClassementDetail classementDetail = null;
    	ClassementDetail upClassementDetail = null;
    	try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, classementDetailId);
			ResultSet rs = this.pstmt.executeQuery();
			try {
				Object[] obj = ResultSetToModel.parseDataEntityBeans(rs, "com.offerme.server.database.model.ClassementDetail");
				classementDetail = (ClassementDetail)obj[0];
				Classement classement = classementDao.getClassement(classementDetail.getClassementId());
				classementDetail.setClassement(classement);
				//获取细分父对象
				int upClassementDetailId = classementDetail.getpClassementDetailId();
				if(upClassementDetailId!= 0)
				{
					upClassementDetail = getClassementDetail(classementDetail.getpClassementDetailId());
					classementDetail.setUpClassementDetail(upClassementDetail);
				}
				//获取细分子对象集合
				classementDetail.setSubClassementDetailMap(getSubClassementDetail(classementDetail.getClassementDetailId()));
			} catch (Exception e) {
				
				throw new Exception(Log.getStackInfo(e)); 
			}
			this.pstmt.close();
		} catch (SQLException e) {
			
			throw new Exception(Log.getStackInfo(e)); 
		} 	
    	return classementDetail;
    }
    
    public HashMap<String, ClassementDetail> getClassementDetailByName(String classementDetailName) throws Exception
    {
    	String sql = "select CLASSMENT_DETAIL_ID as classementDetailId, CLASSEMENT_ID as classementId, P_CLASSEMENT_DETAIL_ID as pClassementDetailId, NAME, LIB from classementdetail " +
				" where NAME = ?";
    	ClassementDetail classementDetail = null;
    	ClassementDetail upClassementDetail = null;
    	HashMap<String, ClassementDetail> classementDetailMap = new HashMap<String, ClassementDetail>();
    	try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1, classementDetailName);
			ResultSet rs = this.pstmt.executeQuery();
			try {
				Object[] objArray = ResultSetToModel.parseDataEntityBeans(rs, "com.offerme.server.database.model.ClassementDetail");
				for (Object object : objArray) {
					
					classementDetail = (ClassementDetail)object;
					Classement classement = classementDao.getClassement(classementDetail.getClassementId());
					classementDetail.setClassement(classement);
					//获取细分父对象
					int upClassementDetailId = classementDetail.getpClassementDetailId();
					if(upClassementDetailId!= 0)
					{
						upClassementDetail = getClassementDetail(classementDetail.getpClassementDetailId());
						classementDetail.setUpClassementDetail(upClassementDetail);
					}
					//获取细分子对象集合
					classementDetail.setSubClassementDetailMap(getSubClassementDetail(classementDetail.getClassementDetailId()));
				}
				
			} catch (Exception e) {
				
				throw new Exception(Log.getStackInfo(e)); 
			}
			this.pstmt.close();
		} catch (SQLException e) {
			
			throw new Exception(Log.getStackInfo(e)); 
		} 	
    	return classementDetailMap;
    }
    
    public HashMap<String, ClassementDetail> getSubClassementDetail(int classementDetailId) throws Exception
    {
    	String sql = "select CLASSMENT_DETAIL_ID as classementDetailId, CLASSEMENT_ID as classementId, P_CLASSEMENT_DETAIL_ID as pClassementDetailId, NAME, LIB from classementdetail " +
				" where P_CLASSEMENT_DETAIL_ID = ?";
    	ClassementDetail classementDetail = null;
    	HashMap<String, ClassementDetail> subClassementDetailMap= new HashMap<String, ClassementDetail>();
    	try {
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, classementDetailId);
			ResultSet rs = this.pstmt.executeQuery();
			try {
				Object[] objArray = ResultSetToModel.parseDataEntityBeans(rs, "com.offerme.server.database.model.ClassementDetail");
				
				for (Object object : objArray) {
					
					classementDetail = (ClassementDetail)object;
					subClassementDetailMap.put(classementDetail.getName(), classementDetail);
					
				}
			} catch (Exception e) {
				
				throw new Exception(Log.getStackInfo(e)); 
			}
			this.pstmt.close();
		} catch (SQLException e) {
			
			throw new Exception(Log.getStackInfo(e)); 
		} 	
    	return subClassementDetailMap;
    }
    
    /**
     * 判断国家城市是否新增,且建立上下级关系
     * @param country
     * @param city
     * @throws Exception
     */
    public void checkforInsertCountryCity(String country, String city) throws Exception
    {
    	String sql = "select CLASSMENT_DETAIL_ID as classementDetailId,CLASSEMENT_ID as classementId, P_CLASSEMENT_DETAIL_ID as pClassementDetailId, NAME, LIB from classementdetail " +
				" where CLASSEMENT_ID = 1 and NAME = ?";
    	try {
    		this.pstmt = this.conn.prepareStatement(sql);
    		this.pstmt.setString(1, country);
    		ResultSet rs = this.pstmt.executeQuery();
    		
    		Object[] objArray = ResultSetToModel.parseDataEntityBeans(rs, "com.offerme.server.database.model.ClassementDetail");
    		if(objArray.length > 0)
    		{
    			ClassementDetail classementDetail = (ClassementDetail)objArray[0];
    			HashMap<String, ClassementDetail> subClassementDetailMap = getSubClassementDetail(classementDetail.getClassementDetailId());
    			// 国家存在,城市不存在
    			if(!subClassementDetailMap.containsKey(city))
    			{
    				ClassementDetail subClassementDetail = new ClassementDetail();
    				subClassementDetail.setpClassementDetailId(classementDetail.getClassementDetailId());
    				subClassementDetail.setClassementId(2);
    				subClassementDetail.setName(city);
    				subClassementDetail.setLib(city);
    				insertClassementDetail(subClassementDetail);
    			}
    		}
    		else
    		{
    			ClassementDetail classementDetail = new ClassementDetail();
    			classementDetail.setClassementId(1);
    			classementDetail.setName(country);
    			classementDetail.setLib(country);
				int classementDetailId  = insertClassementDetail(classementDetail);
				ClassementDetail subClassementDetail = new ClassementDetail();
				subClassementDetail.setpClassementDetailId(classementDetail.getClassementDetailId());
				subClassementDetail.setClassementId(2);
				subClassementDetail.setName(city);
				subClassementDetail.setLib(city);
				insertClassementDetail(subClassementDetail);
    		}
			
    		
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }

}
