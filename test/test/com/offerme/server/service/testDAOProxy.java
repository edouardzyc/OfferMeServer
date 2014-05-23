package test.com.offerme.server.service;

import com.offerme.server.database.dao.MessageDao;
import com.offerme.server.database.dao.OfferDao;
import com.offerme.server.database.dao.UserDao;
import com.offerme.server.database.dao.proxy.DaoImplManage;
import com.offerme.server.database.model.Message;
import com.offerme.server.database.model.Offer;
import com.offerme.server.database.model.User;
import com.offerme.server.service.InscriptionSrvc;
import com.offerme.server.util.JSONUtil;

public class testDAOProxy {
	
	public static void main(String[] args) throws Exception {
		
		UserDao dao = DaoImplManage.getUserDaoInstance();
		OfferDao offerDao = DaoImplManage.getOfferDaoInstance();
		MessageDao messageDao = DaoImplManage.getMessageDaoInstance();
//	    User user = new User();
//	    user.setUserId(4);
//	    user.setName("test2");
//	    user.setCity("上海");
//	    dao.insertUser(user);
//	    dao.updateUser(user);
//	    dao.deleteUser(4);
//       	dao.getAllUser();
       	
		User user  = dao.getUser(1);
		String data = JSONUtil.beanToJson(user);
       	InscriptionSrvc srvc = new InscriptionSrvc();
       	srvc.inscriptUser(data);
       	
//       	Offer offer = offerDao.getOffer(1);
//       	String data = JSONUtil.beanToJson(offer);
//       	offer = JSONUtil.jsonToBean(data, Offer.class);
//		Message message = messageDao.getMessage(1);
//		String data = JSONUtil.beanToJson(message);
//		message = JSONUtil.jsonToBean(data, Message.class);
		
        
	}

}
