/**
 * 
 */
package com.mfd;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
/**
 * @author pandengfeng
 *	路由器
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulServiceApplication implements CommandLineRunner{
	
	@Value("${server.port}")
	private String port;
	@Value("${spring.application.name}")
	private String applicationName;
	
	public static void main(String[] args) {
		SpringApplication.run(ZuulServiceApplication.class, args);
	}
	

	/**
	 * 服务启动时 运行 
	 */
	public void run(String... arg0) throws Exception {
		System.out.println(applicationName+" 服务启动完成  端口号:"+port);
	}
}
