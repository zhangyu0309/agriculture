package common;

import org.apache.log4j.Logger;

public class Constant {
	public static Logger logger = Logger.getLogger(Constant.class);
	/**
	 * 服务启动时 是否允许socket监听
	 */
	public static final boolean IS_RUN_SOCKET = true;
	/**
	 * 监听端口uuuuuuu
	 */
	public static final int LISTEN_PORT = 50005;
	
	/**
	 * socket超时关闭的时间，毫秒
	 */
	public static int SOCKET_WAIT_TIME = 0;
	
	/**
	 * 开始标记
	 */
	public static final byte[] START = "START".getBytes();
	
	/**
	 * 结束标记
	 */
	public static final byte[] CLOSE = "CLOSE".getBytes();
	
	/**
	 * 查询所有传感器数据
	 */
	public static final byte[] GET_DATA = {0x3a,0x00,(byte) 0xff,0x01,(byte) 0xc4,0x23};
	
	/**
	 * 开设备
	 */
	public static final byte[] OPEN_DEVICE = {0x3a,0x00,0x01,0x0a,0x00,0x31,0x23};
	
	/**
	 * 关设备
	 */
	public static final byte[] CLOSE_DEVICE = {0x3a,0x00,0x01,0x0a,0x01,0x30,0x23};
}
