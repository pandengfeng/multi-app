/**
 * 
 */
package com.multi.common.util;

/**
 * 服务环境变量存储
 * 2017-12-20
 * @author pandengfeng
 *
 */
public class ApplicationParams {
	
	/**
	 * 服务名称
	 */
	private static String serverName;
	/**
	 * 服务端口号
	 */
	private static String port;
	/**
	 * @return the serverName
	 */
	public static String getServerName() {
		return serverName;
	}

	/**
	 * @param serverName the serverName to set
	 */
	public static void setServerName(String serverName) {
		ApplicationParams.serverName = serverName;
	}

	/**
	 * @return the port
	 */
	public static String getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public static void setPort(String port) {
		ApplicationParams.port = port;
	}
	
	
	
	
}
