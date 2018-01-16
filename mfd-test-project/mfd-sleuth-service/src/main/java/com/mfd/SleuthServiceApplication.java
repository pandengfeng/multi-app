/**
 * 
 */
package com.mfd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

/**
 * @author pandengfeng
 *	链路追踪服务
 */
@SpringBootApplication
@EnableZipkinServer
public class SleuthServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SleuthServiceApplication.class, args);
	}
}
