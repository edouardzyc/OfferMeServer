package com.offerme.server.service.threadpool;

import java.net.Socket;

import org.apache.log4j.Logger;

import com.offerme.server.MainServer;
import com.offerme.server.socket.SocketReadWrite;
import com.offerme.server.util.Log;

public class ThreadTask implements Runnable  {

	static Logger myLog = Logger.getLogger("com.offerme.database.threadpool");
	private MainServer server =null;
	Socket clientSocket ;
	private String clientIp = null;
	
	public ThreadTask(MainServer server, Socket clientSocket) {
		this.server = server;
		this.clientSocket = clientSocket;
		this.clientIp = null;
	}
	
	@Override
	public void run() {
		
		if(clientSocket == null)
		{
			return;
		}
		try {
			
			if(clientSocket != null)
			{
				clientIp = clientSocket.getInetAddress().getHostAddress();
				String strRequest = SocketReadWrite.SocketRead(clientSocket);
				Request request = new Request(strRequest);
				Reply reply = new Reply();
				try {
					
					DistributeManage.DistributeRequest(request, reply);
					
				} catch (Exception e) {
					
				}
				
				SocketReadWrite.SocketWrite(clientSocket, reply.getJsonReply());
			}
			
		} catch (Exception e) {
			myLog.error(Log.getStackInfo(e));
			myLog.error("Logis Server failed in Listener:" + e.getLocalizedMessage());
		}
		
	
	}
}
