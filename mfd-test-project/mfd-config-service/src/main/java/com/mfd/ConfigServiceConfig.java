package com.mfd;
/**
 * 
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author pandengfeng
 *	POST 请求 ${ip}:${server.port}/bus/refresh/ 使用消息总线 更新配置文件
 *			${ip}:${server.port}/refresh   单独更新
 *			data:String none
 */
@SpringBootApplication
@EnableDiscoveryClient  
@EnableConfigServer
public class ConfigServiceConfig implements CommandLineRunner{
	
	@Value("${server.port}")
	private String port;
	@Value("${spring.application.name}")
	private String applicationName;
	
	public static void main(String[] args) {
		SpringApplication.run(ConfigServiceConfig.class, args);
	}
	 
	 /**
	  * 服务启动时 运行 
	  */
	public void run(String... arg0) throws Exception {
		System.out.println(applicationName+" 服务启动完成  端口号:"+port);
	}
}
