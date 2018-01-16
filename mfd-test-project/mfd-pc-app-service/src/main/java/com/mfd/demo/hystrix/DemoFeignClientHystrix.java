/**
 * 
 */
package com.mfd.demo.hystrix;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.mfd.demo.fegin.DemoFeignClient;

/**
 * @author pandengfeng
 * 熔断异常情况处理
 */
@Component
public class DemoFeignClientHystrix implements DemoFeignClient{


	@Override
	public String demoTest(Map<String, String> params) {
		return "error";
	}

}
