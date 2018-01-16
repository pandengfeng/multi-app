package com.multi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 基础注册服务 
 * 2017-12-15
 * @author pandengfeng
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication implements CommandLineRunner{
	/**
	 *	logback日志
	 */
	private Logger logger = LoggerFactory.getLogger(EurekaServerApplication.class);
	/**
	 *	服务注册名
	 */
	@Value("${spring.application.name}")
	private String applicationName;
	/**
	 *	服务端口号
	 */
	@Value("${server.port}")
	private String serverPort;
	/**
	 *	服务启动
	 *	@param args 程序入口 
	 */
	public static void main(String[] args) {
		//禁止命令行启动设置参数
		//SpringApplication app = new SpringApplication(MultiEurekaServerApplication.class);
		//app.setAddCommandLineProperties(false);
		SpringApplication.run(EurekaServerApplication.class, args);
	}
	/**
	 *  服务启动监控	
	 *  启动成功打印  -----Start "+applicationName+" server port:"+ serverPort +" run success!-----
	 *	@param args 程序入口 
	 */
	public void run(String... args) throws Exception {
		logger.info("-----Start "+applicationName+" server port:"+ serverPort +" run success!-----");
	}
	/**
	 * @return the applicationName
	 */
	public String getApplicationName() {
		return applicationName;
	}

	/**
	 * @return the serverPort
	 */
	public String getServerPort() {
		return serverPort;
	}



	

}
