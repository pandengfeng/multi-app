/**
 * 
 */
package com.mfd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * (操作员服务)
 * @author pandengfeng
 *
 */
@SpringBootApplication
@EnableDiscoveryClient(autoRegister=true)
@MapperScan(basePackages ="com.mfd.demo.dao")
public class OperatorServiceApplication implements CommandLineRunner{
	//private Logger logger = LoggerFactory.getLogger(OperatorServiceApplication.class);
	
	@Value("${server.port}")
	private String port;
	@Value("${spring.application.name}")
	private String applicationName;

	
	public static void main(String[] args) {
		SpringApplication.run(OperatorServiceApplication.class, args);
	}

	/**
	 * 服务启动时 运行 
	 */
	public void run(String... arg0) throws Exception {
		System.out.println(applicationName+" 服务启动完成  端口号:"+port);
	}
	
	
}
