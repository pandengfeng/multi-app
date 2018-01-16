/**
 * 
 */
package com.mfd;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * (整体集群监控)
 * 通过@EnableTurbine接口，激活对Turbine的支持。
 * @author pandengfeng
 */
@SpringBootApplication
@EnableTurbine
public class TurbineApplication implements CommandLineRunner{
	
	@Value("${server.port}")
	private String port;
	@Value("${spring.application.name}")
	private String applicationName;
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(TurbineApplication.class).web(true).run(args);
	}
	
	 /**
	  * 服务启动时 运行 
	  */
	public void run(String... arg0) throws Exception {
		System.out.println(applicationName+" 服务启动完成  端口号:"+port);
	}
}
