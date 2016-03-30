package com.agri.platform.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Timer;

import org.apache.log4j.Logger;

import com.agri.core.util.ByteUtil;

import common.Constant;

public class Server implements Runnable {
	private static Logger logger = Logger.getLogger(Server.class.getName());
	private final Socket _s;
	Timer timer = new Timer();
	byte[] reqDate = null;
	long lastReceiveTime;// 上次收到数据包的时间
	InputStream in = null;
	OutputStream os = null;
	public Server(Socket s) {
		_s = s;
		lastReceiveTime = System.currentTimeMillis();
	}

	public void run() {
		try {
			in = _s.getInputStream();
			os = _s.getOutputStream();
			while (true) {
				if (Constant.SOCKET_WAIT_TIME > 0 && 
						(System.currentTimeMillis() - lastReceiveTime > Constant.SOCKET_WAIT_TIME)) {
					//time out
					break;
				}
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				if (in.available() > 0) {
					lastReceiveTime = System.currentTimeMillis();
					// 处理客户端发来的数据
					byte[] bytes = new byte[in.available()];
					in.read(bytes);
					logger.info("receice:" + ByteUtil.ListBytes(bytes));
//					for (byte b : bytes) {
//						System.out.print(ByteUtil.toHex(b));
//						System.out.print("|");
//					}
					byte[] response = MessageHandle(bytes);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				if (!_s.isClosed()) {
					_s.close();
				}
				if (timer != null){
					timer.cancel();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 对socket收到的报文进行处理
	 * 
	 * @param msg
	 *            收到报文
	 * @return 响应报文
	 */
	private byte[] MessageHandle(byte[] msg) {
		logger.info("HANDLE MESSAGE:" + new String(msg));
		if (new String(msg).contains("Hello from wifi board")){
			sendMessage(Constant.START);
		}else if (new String(msg).startsWith("Ok Fd:")){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sendMessage(Constant.GET_DATA);
		}else if (new String(msg).startsWith("local_client_fd is not connect to")){
			sendMessage(Constant.START);
		}
		return null;
	}
	
	/**
	 * 回复信息
	 * @param response
	 */
	private void sendMessage(byte[] response){
		if (os != null && response != null) {
			int trytime = 0;
			do {// 为防止发送失败，最多重试6次
				try {
					trytime++;
					os.write(response);
					logger.info("response:" + ByteUtil.ListBytes(response));//new String(response));
					break;
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			} while (trytime < 6);
		}
	}

}
