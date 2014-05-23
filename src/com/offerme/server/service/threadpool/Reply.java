package com.offerme.server.service.threadpool;

import java.util.HashMap;
import java.util.Map.Entry;

import com.offerme.server.util.JSONUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 返回对象
 * @author Edouard.Zhang
 *
 */
public class Reply {
	
	private HashMap<String,Object> replyList = new HashMap<String,Object>();
	
	public void AddReply(String key,Object value)
	{
		replyList.put(key, value);
	}
	
	/**
	 * 返回json
	 * @return
	 */
	public String getJsonReply()
	{
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		
		for(Entry<String,Object> ent : replyList.entrySet())
		{
			jsonObj = JSONUtil.beanToJsonObj(ent.getValue());
			jsonArray.add(jsonObj);
		}
		
		return jsonArray.toString();
	}

}
