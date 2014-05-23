package com.offerme.server.service.threadpool;

import java.util.ArrayList;

import net.sf.json.JSONArray;

import com.offerme.server.util.ServiceRequestType;

/**
 * 请求对象
 * @author Edouard.Zhang
 *
 */
public class Request {
	
	/**
	 * 服务编号
	 */
	private int serviceRequestType = ServiceRequestType.None;
	/**
	 * 参数列表
	 */
	private ArrayList<String> paramList = new ArrayList<String>();
	
	public int getServiceRequestType() {
		return serviceRequestType;
	}

	public ArrayList<String> getParamList() {
		return paramList;
	}

	public Request(String json)
	{
		JSONArray jsonArray = JSONArray.fromObject(json);
		Object [] objects = jsonArray.toArray();
		for (int i = 0; i < objects.length; i++) {
			
			if(i==0)
			{
				serviceRequestType = Integer.parseInt(String.valueOf(objects[0]));
			}
			else
			{
				paramList.add(String.valueOf(objects[i]));
			}
		}
		
	}

}
