package com.offerme.server;

import org.apache.log4j.Logger;

import com.offerme.server.service.threadpool.ThreadPoolMgn;

/**
 * 存放全局变量
 * @author Edouard.Zhang
 *
 */
public class GlobalData {

	static Logger myLog = Logger.getLogger("com.logi.lpromis.server");
	
	private static ThreadPoolMgn poolMgn = null;

	public static ThreadPoolMgn getPoolMgn() {
		return poolMgn;
	}
	public static void setPoolMgn(ThreadPoolMgn poolMgn) {
		GlobalData.poolMgn = poolMgn;
	}
	
}
