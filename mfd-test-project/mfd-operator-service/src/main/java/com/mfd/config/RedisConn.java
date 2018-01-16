/**
 * 
 */
package com.mfd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author pandengfeng
 *读取Redis链接信息 RedisConn
 */
@Component
public class RedisConn {
	
	@Value("${spring.redis.host}")
	private String host;
	 //prefix+参数名  对应于配置文件config.properties中的spring.redis.*信息
	@Value("${spring.redis.port}")
	private int port;
	@Value("${spring.redis.timeout}") 
	private int timeout;
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}
	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	/**
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}
	
	
	
}
