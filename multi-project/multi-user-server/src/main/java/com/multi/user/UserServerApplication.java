/**
 * 
 */
package com.multi.user;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.multi.common.util.ApplicationParams;


/**
 * 用户服务 
 * 2017-12-15
 * @author pandengfeng
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.multi.aop.config"
											,"com.multi.redis.config"
											,"com.multi.jwt.config"
											,"com.multi.user.*"})
@MapperScan(basePackages = {"com.multi.user.dao"})
public class UserServerApplication implements CommandLineRunner {
	/**
	 * logback日志
	 */
	private Logger logger = LoggerFactory.getLogger(UserServerApplication.class);
	/**
	 * 服务注册名
	 */
	@Value("${spring.application.name}")
	private String applicationName;
	/**
	 * 服务端口号
	 */
	@Value("${server.port}")
	private	String serverPort;

	/**
	 * 服务启动
	 * 
	 * @param args
	 *            程序入口
	 */
	public static void main(String[] args) {
		// 禁止命令行启动设置参数
		// SpringApplication app = new
		// SpringApplication(MultiEurekaServerApplication.class);
		// app.setAddCommandLineProperties(false);
		SpringApplication.run(UserServerApplication.class, args);
	}

	/**
	 * 服务启动监控 启动成功打印 -----Start "+applicationName+" server port:"+ serverPort +" run
	 * success!-----
	 * 
	 * @param args
	 *            程序入口
	 */
	public void run(String... args) throws Exception {
		ApplicationParams.setServerName(applicationName);
		ApplicationParams.setPort(serverPort);
		logger.info("-----Start " + applicationName + " server port:" + serverPort + " run success!-----");
	}

}
