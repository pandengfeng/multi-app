/**
 * 
 */
package com.mfd.demo.fegin;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mfd.demo.hystrix.DemoFeignClientHystrix;

/**
 * @author pandengfeng
 * 消费者
 */
@FeignClient(name = "operator-service",fallback = DemoFeignClientHystrix.class)
public interface DemoFeignClient {
	
	@ResponseBody
	@RequestMapping(value="/demo",method = RequestMethod.POST,consumes="application/json")
	public String demoTest(@RequestBody Map<String,String> params);
	
	
}
