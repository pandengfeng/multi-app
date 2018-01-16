/**
 * 
 */
package com.mfd.config;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pandengfeng
 *
 */
@Configuration
public class DemoConfiguration{
	/**
	 * 自定转换器 并添加到调用列表
	 * @author pandengfeng
	 * @return	HttpMessageConverters
	 */
	@Bean
	public HttpMessageConverters customConverters() {
		/**
		 * 	@author pandengfeng
		 * 	HttpMessageConverter 实现此接口 完成自定义转换器 后添加到转换器调用列表
			HttpMessageConverter converter = new StringHttpMessageConverter();
			HttpMessageConverters http = new HttpMessageConverters(converter);
			return http;
		*/
		return new HttpMessageConverters();
	}
}
