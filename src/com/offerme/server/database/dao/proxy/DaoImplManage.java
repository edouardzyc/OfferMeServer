package com.offerme.server.database.dao.proxy;

import java.lang.reflect.Proxy;
import java.sql.Connection;

import com.offerme.server.database.dao.ClassementDao;
import com.offerme.server.database.dao.ClassementDetailDao;
import com.offerme.server.database.dao.MessageDao;
import com.offerme.server.database.dao.OfferDao;
import com.offerme.server.database.dao.UserDao;
import com.offerme.server.database.dao.impl.ClassementDaoImpl;
import com.offerme.server.database.dao.impl.ClassementDetailDaoImpl;
import com.offerme.server.database.dao.impl.MessageDaoImpl;
import com.offerme.server.database.dao.impl.OfferDaoImpl;
import com.offerme.server.database.dao.impl.UserDaoImpl;
import com.offerme.server.database.dbpool.DataPoolManager;

/**
 * 动态代理获取Dao实现的管理类
 * @author Edouard.Zhang
 *
 */
public class DaoImplManage {
	

	/**
	 * 获取UserDao实现
	 * @return
	 */
	public static UserDao getUserDaoInstance() {
		
		Connection conn = null;
		try {
			conn = DataPoolManager.getConnection("OfferMe");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		UserDao userDao = (UserDao) Proxy.newProxyInstance(UserDao.class
				.getClassLoader(), UserDaoImpl.class.getInterfaces(),
				new InvocationHandlerImp(new UserDaoImpl(conn)));

		return userDao;
	}
	
	/**
	 * 获取UserDao实现
	 * @param conn 连接
	 * @return
	 */
	public static UserDao getUserDaoInstance(Connection conn) {
		
		UserDao userDao = (UserDao) Proxy.newProxyInstance(UserDao.class
				.getClassLoader(), UserDaoImpl.class.getInterfaces(),
				new InvocationHandlerImp(new UserDaoImpl(conn)));

		return userDao;
	}
	
	/**
	 * 获取OfferDao实现
	 * @return
	 */
	public static OfferDao getOfferDaoInstance() {
		
		Connection conn = null;
		try {
			conn = DataPoolManager.getConnection("OfferMe");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		OfferDao offerDao = (OfferDao) Proxy.newProxyInstance(OfferDao.class
				.getClassLoader(), OfferDaoImpl.class.getInterfaces(),
				new InvocationHandlerImp(new OfferDaoImpl(conn)));

		return offerDao;
	}
	
	/**
	 * 获取MessageDao实现
	 * @return
	 */
	public static MessageDao getMessageDaoInstance() {
		
		Connection conn = null;
		try {
			conn = DataPoolManager.getConnection("OfferMe");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		MessageDao messageDao = (MessageDao) Proxy.newProxyInstance(MessageDao.class
				.getClassLoader(), MessageDaoImpl.class.getInterfaces(),
				new InvocationHandlerImp(new MessageDaoImpl(conn)));

		return messageDao;
	}
	
	/**
	 * 获取ClassementDao实现
	 * @return
	 */
	public static ClassementDao getClassementDaoInstance() {
		
		Connection conn = null;
		try {
			conn = DataPoolManager.getConnection("OfferMe");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		ClassementDao classementDao = (ClassementDao) Proxy.newProxyInstance(ClassementDao.class
				.getClassLoader(), ClassementDaoImpl.class.getInterfaces(),
				new InvocationHandlerImp(new ClassementDaoImpl(conn)));

		return classementDao;
	}
	
	/**
	 * 获取ClassementDao实现
	 * @param conn
	 * @return
	 */
	public static ClassementDao getClassementDaoInstance(Connection conn) {
		
		ClassementDao classementDao = (ClassementDao) Proxy.newProxyInstance(ClassementDao.class
				.getClassLoader(), ClassementDaoImpl.class.getInterfaces(),
				new InvocationHandlerImp(new ClassementDaoImpl(conn)));

		return classementDao;
	}
	
	/**
	 * 获取ClassementDetailDao实现
	 * @return
	 */
	public static ClassementDetailDao getClassementDetailDaoInstance() {
		
		Connection conn = null;
		try {
			conn = DataPoolManager.getConnection("OfferMe");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		ClassementDetailDao classementDetailDao = (ClassementDetailDao) Proxy.newProxyInstance(ClassementDetailDao.class
				.getClassLoader(), ClassementDetailDaoImpl.class.getInterfaces(),
				new InvocationHandlerImp(new ClassementDetailDaoImpl(conn)));

		return classementDetailDao;
	}
	
	/**
	 * 获取ClassementDetailDao实现
	 * @param conn
	 * @return
	 */
	public static ClassementDetailDao getClassementDetailDaoInstance(Connection conn) {
		
		ClassementDetailDao classementDetailDao = (ClassementDetailDao) Proxy.newProxyInstance(ClassementDetailDao.class
				.getClassLoader(), ClassementDetailDaoImpl.class.getInterfaces(),
				new InvocationHandlerImp(new ClassementDetailDaoImpl(conn)));

		return classementDetailDao;
	}
	

}
