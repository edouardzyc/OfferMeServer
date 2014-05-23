package com.offerme.server.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

public class JSONUtil {
	
	/**
	 * 从ResultSet中所有数据转为JSON
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public static String getDataJSON(ResultSet result) throws Exception
	{
		// 获取列数
		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();

		// 遍历ResultSet中的每条数据
		JSONArray array = new JSONArray();
		JSONObject jsonObj = null;
		String columnName = "";
		String value = "";
		while (result.next())
		{
			jsonObj = new JSONObject();
			// 遍历每一列
			for (int j = 1; j <= columnCount; j++)
			{
				columnName = metaData.getColumnLabel(j);
				value = result.getString(columnName);
				jsonObj.put(columnName, value);
			}
			array.add(jsonObj);
		}
		return array.toString();
	}

	
	public static <T> T jsonToBean(String json, Class<T> cla){
		String[] formats={"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"};
		JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats));
		JSONObject jsonObject=JSONObject.fromObject(json);
		return (T) JSONObject.toBean(jsonObject,cla);
	}
	
	public static <T> String beanToJson(T t){
		JsonConfig config=new JsonConfig();
		config.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONObject json=JSONObject.fromObject(t,config);
		return json.toString();
	}
	
	public static <T> JSONObject beanToJsonObj(T t){
		JsonConfig config=new JsonConfig();
		config.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONObject json=JSONObject.fromObject(t,config);
		return json;
	}
}
