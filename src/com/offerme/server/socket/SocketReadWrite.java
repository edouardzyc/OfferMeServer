package com.offerme.server.socket;

import java.net.Socket;

public class SocketReadWrite {
	
	/**
	 * 从socket通道中读取数据
	 * @param clientSocket
	 * @return
	 * @throws Exception
	 */
	public static String SocketRead(Socket clientSocket) throws Exception
	{
		String data = "";
		int count = GetClientRequestCount(clientSocket);
		byte[] buffer = null;
		if (count == 0)
		{
			throw new Exception("等不到客户数据！");
		}
		else if (count == -1)
		{
			return null;
		}
		else
		{
			if (count < SocketUtil.MAX_SIZE_SEND * 1024 * 1024)
			{
				buffer = new byte[count];
			}
			else
			{
				throw new Exception("数据大于" + SocketUtil.MAX_SIZE_SEND + "M，不处理 ！");
			}
			int received = 0;
			int intOffset = 0;
			while (count > 0)
			{
				if (count <= SocketUtil.MAX_SIZE_PER_SEND * 1024 * 1024)
				{
					received = clientSocket.getInputStream().read(buffer, intOffset, count);
				}
				else
				{
					int intReceiveCount = SocketUtil.MAX_SIZE_PER_SEND * 1024 * 1024;
					if (ReceiveData(clientSocket, buffer, intOffset, intReceiveCount) == true)
					{
						byte[] cc = { (byte) 128 };
						clientSocket.getOutputStream().write(cc);
						Thread.sleep(10);
					}
					received = intReceiveCount;
				}
				if (received <= 0)
				{
					throw new Exception("Read failed");
				}
				intOffset += received;
				count -= received;
			}
			data = new String(buffer, 0, buffer.length, "UnicodeLittleUnmarked");
		}
		return data;
	}
	/**
	 * 从socket通道中写数据
	 * @param clientSocket
	 * @param strReply
	 * @return
	 * @throws Exception
	 */
	public static void SocketWrite(Socket clientSocket, String strReply) throws Exception
	{
		byte[] buffer = null;
		if(strReply == null)
		{
			buffer = "NULL".getBytes("UnicodeLittleUnmarked");
		}
		else
		{
			buffer = strReply.getBytes("UnicodeLittleUnmarked");
		}
		
		// 分批发送处理，分批接受
		byte[] buffLength = SetRequestCount(buffer.length);
		clientSocket.getOutputStream().write(buffLength, 0, buffLength.length);
		int sendCount = buffer.length;
		int intOffset = 0;
		while (sendCount > 0)
		{
			if (sendCount <= SocketUtil.MAX_SIZE_PER_SEND * 1024 * 1024)
			{
				if (sendCount > SocketUtil.SLIPT_SIZE_PER_SEND * 1024 * 1024)
				{
					clientSocket.getOutputStream().write(buffer, intOffset, SocketUtil.SLIPT_SIZE_PER_SEND * 1024 * 1024);
					intOffset += (SocketUtil.SLIPT_SIZE_PER_SEND * 1024 * 1024);
					sendCount -= (SocketUtil.SLIPT_SIZE_PER_SEND * 1024 * 1024);
					Thread.sleep(1);
				}
				else
				{
					clientSocket.getOutputStream().write(buffer, intOffset, sendCount);
					sendCount = 0;
				}
			}
			else
			{
				int intSendCount = SocketUtil.MAX_SIZE_PER_SEND * 1024 * 1024;
				if (SendData(clientSocket, buffer, intOffset, intSendCount) == true)
				{
					byte[] cc = new byte[2];
					clientSocket.getInputStream().read(cc, 0, 1);
					intOffset += intSendCount;
					sendCount -= intSendCount;
				}
				else
				{
					throw new Exception("Error when writing data to socket!");
				}
			}
		}
	}
	
	/**
	 * 发送数据
	 * @param clientSocket
	 * @param buffer
	 * @param intOffset偏移量
	 * @param maxSize
	 * @return
	 * @throws Exception
	 */
	private static boolean SendData(Socket clientSocket, byte[] buffer, int intOffset, int maxSize) throws Exception
	{
		int sendCount = maxSize;
		while (sendCount > 0)
		{
			if (sendCount > SocketUtil.SLIPT_SIZE_PER_SEND * 1024 * 1024)
			{
				clientSocket.getOutputStream().write(buffer, intOffset, SocketUtil.SLIPT_SIZE_PER_SEND * 1024 * 1024);
				intOffset += (SocketUtil.SLIPT_SIZE_PER_SEND * 1024 * 1024);
				sendCount -= (SocketUtil.SLIPT_SIZE_PER_SEND * 1024 * 1024);
				Thread.sleep(10);
			}
			else
			{
				clientSocket.getOutputStream().write(buffer, intOffset, sendCount);
				sendCount = 0;
			}
		}
		return true;
	}
	
	/**
	 * 接受数据
	 * @param clientSocket
	 * @param buffer
	 * @param intOffset
	 * @param maxRec
	 * @return
	 * @throws Exception
	 */
	private static boolean ReceiveData(Socket clientSocket, byte[] buffer, int intOffset, int maxRec) throws Exception
	{
		int count = maxRec;
		while (count > 0)
		{
			maxRec = clientSocket.getInputStream().read(buffer, intOffset, count);
			if (maxRec <= 0)
			{
				return false;
			}
			intOffset += maxRec;
			count -= maxRec;
		}
		return true;
	}
	
	/**
	 * 从socket通道中先获取数据长度
	 * @param clientSocket
	 * @return
	 * @throws Exception
	 */
	private static int GetClientRequestCount(Socket clientSocket) throws Exception
	{
		int count = 0;
		byte[] buffer = new byte[4];
		int intLength = clientSocket.getInputStream().read(buffer, 0, 4);
		if (intLength == 4)
		{
			count = GetRequestCount(buffer);
		}
		else if (intLength == -1)
		{
			count = -1;
		}
		return count;
	}
	
	/**
	 * 获取长度
	 * @param buff
	 * @return
	 */
	private static int GetRequestCount(byte[] buff) {
        int count = ((buff[0] & 0xFF) << 24) + ((buff[1] & 0xFF) << 16) + ((buff[2] & 0xFF) << 8) + (buff[3] & 0xFF);
        return count;
	}
	
	/**
	 * 设定数据长度
	 * @param count
	 * @return
	 */
	private static byte[] SetRequestCount(int count) {
		byte[] buff = new byte[4];
		buff[3] = (byte) (count & 0xFF);
		count >>= 8;
		buff[2] = (byte) (count & 0xFF);
		count >>= 8;
		buff[1] = (byte) (count & 0xFF);
		count >>= 8;
		buff[0] = (byte) count;
		return buff;
	}


}
