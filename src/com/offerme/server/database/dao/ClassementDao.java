package com.offerme.server.database.dao;

import com.offerme.server.database.model.Classement;

public interface ClassementDao {
	
	public int insertClassement(Classement classement) throws Exception;
    
    public void updateClassement(Classement classement) throws Exception;
    
    public void deleteClassement(int classementId) throws Exception;
    
    public Classement getClassement(int classementId) throws Exception;

}
