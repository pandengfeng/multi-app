/**
 * 
 */
package com.multi.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 读取Redis链接信息 RedisConn 并注入到spring bean池
 * 
 * @author pandengfeng
 */
@Component
public class RedisConn {
	/**
	 * redis 地址
	 */
	@Value("${spring.redis.host}")
	private String host;
	/**
	 * redis 端口
	 */
	@Value("${spring.redis.port}")
	private int port;
	/**
	 * redis 超时时间
	 */
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
