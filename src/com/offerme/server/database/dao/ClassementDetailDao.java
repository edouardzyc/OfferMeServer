package com.offerme.server.database.dao;

import java.util.HashMap;

import com.offerme.server.database.model.ClassementDetail;


public interface ClassementDetailDao {
	
	public int insertClassementDetail(ClassementDetail classementDetail) throws Exception;
    
    public void updateClassementDetail(ClassementDetail classementDetail) throws Exception;
    
    public void deleteClassementDetail(int classementDetailId) throws Exception;
    
    public ClassementDetail getClassementDetail(int classementDetailId) throws Exception;
    
    public HashMap<String, ClassementDetail> getSubClassementDetail(int classementDetailId) throws Exception;
    /**
     * 判断国家城市是否新增,且建立上下级关系
     * @param country
     * @param city
     * @throws Exception
     */
    public void checkforInsertCountryCity(String country, String city) throws Exception;
    

    //TODO 新增USER 或者 offer的之后需要通过判断维护 ClassementDetail

}
