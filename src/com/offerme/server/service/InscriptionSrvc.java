package com.offerme.server.service;

import com.offerme.server.database.dao.UserDao;
import com.offerme.server.database.dao.proxy.DaoImplManage;
import com.offerme.server.database.model.User;
import com.offerme.server.util.JSONUtil;

public class InscriptionSrvc {
	
	public String inscriptUser(String request)
	{
		String reply = "";
		UserDao dao = DaoImplManage.getUserDaoInstance();
		User user =  JSONUtil.jsonToBean(request, User.class);
		try {
			dao.insertUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return reply;
	}

}
