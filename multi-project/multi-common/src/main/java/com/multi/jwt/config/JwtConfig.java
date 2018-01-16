/**
 * 
 */
package com.multi.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.multi.redis.config.RedisUtil;


/**
 * jsw配置类
 * 2017-12-21
 * @author pandengfeng
 *	
 */
@Configuration
@Component
public class JwtConfig {
	/**
	 * reids操作对象
	 */
	@Autowired
	private RedisUtil redisUtil;
	
	/**
	 * 自定义过滤对象Bean
	 * @return FilterRegistrationBean
	 */
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		String[] url = {"/user/login","/user/register"};
		JwtAuthenticationFilter filter = new JwtAuthenticationFilter(url,redisUtil);
		registrationBean.setFilter(filter);
		return registrationBean;
	}
}
