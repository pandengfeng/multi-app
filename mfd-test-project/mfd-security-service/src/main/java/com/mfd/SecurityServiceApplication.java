/**
 * 
 */
package com.mfd;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author pandengfeng
 *	
 *	授权服务
 */
@SpringBootApplication
@EnableAutoConfiguration
public class SecurityServiceApplication implements CommandLineRunner{
	
	@Value("${server.port}")
	private String port;
	@Value("${spring.application.name}")
	private String applicationName;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SecurityServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(applicationName+" 服务启动完成  端口号:"+port);
	}
	
}
