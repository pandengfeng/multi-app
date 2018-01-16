/**
 * 
 */
package com.mfd;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author pandengfeng
 *
 *
 *	通过pc端用post 向的client发送请求/bus/refresh/；
 *	此时8882端口会发送一个消息，由消息总线向其他服务传递，
 *	从而使整个微服务集群都达到更新配置文件。
 *
 *	POST到/env以更新Environment并重新绑定@ConfigurationProperties和日志级别
 *	/refresh重新加载引导带上下文并刷新@RefreshScope bean
 *	/restart关闭ApplicationContext并重新启动（默认情况下禁用）
 *	/pause和/resume调用Lifecycle方法（stop()和start() ApplicationContext）
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker
public class PcAppServiceApplication implements CommandLineRunner{

	@Value("${server.port}")
	private String port;
	@Value("${spring.application.name}")
	private String applicationName;
	
	public static void main(String[] args) {
		SpringApplication.run(PcAppServiceApplication.class, args);
	}
	
	/**
	 * 开启负载均衡
	 * @author pandengfeng
	 * @return RestTemplate
	 */
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	/**
	 * 服务启动时 运行 
	 */
	public void run(String... arg0) throws Exception {
		System.out.println(applicationName+" 服务启动完成  端口号:"+port);
	}
}
