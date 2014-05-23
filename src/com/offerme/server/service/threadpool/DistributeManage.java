package com.offerme.server.service.threadpool;


import com.offerme.server.service.InscriptionSrvc;

/**
 * 分发请求管理类
 * @author Edouard.Zhang
 *
 */
public class DistributeManage {
	
	/**
	 * @param request
	 * @param reply
	 * @return
	 */
	public static String DistributeRequest(Request request,Reply reply)
	{
		String strError = "";
		try
		{
			switch(request.getServiceRequestType())
			{
			case DistributeType.userInsript:
			{
				InscriptionSrvc srvc = new InscriptionSrvc();
				srvc.inscriptUser(request.getParamList().get(0));
				break;
			}
			default:
			{
				break;
			}
			}
			
		}
		catch (Exception e)
		{
			
		}
		return strError;
	}
	
	/**
	 * 约定好所有请求号
	 * @author Edouard.Zhang	 *
	 */
	public class DistributeType
	{
		/**
		 * 用户注册
		 */
		public static final int userInsript = 1000;
	}

}
