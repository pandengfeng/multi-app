package com.mfd;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * 
 * @author pandengfeng
 *	
 *	(服务注册)
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceApplication implements CommandLineRunner{
	
	@Value("${server.port}")
	private String port;
	@Value("${spring.application.name}")
	private String applicationName;
	
	public static void main(String[] args) {
		//禁止命令行启动设置参数
		//SpringApplication app = new SpringApplication(EurekaServiceApplication.class);
		//app.setAddCommandLineProperties(false);
		
		SpringApplication.run(EurekaServiceApplication.class, args);
	}
	
	 /**
	  * 服务启动时 运行 
	  */
	public void run(String... arg0) throws Exception {
		System.out.println(applicationName+" 服务启动完成  端口号:"+port);
	}
}
