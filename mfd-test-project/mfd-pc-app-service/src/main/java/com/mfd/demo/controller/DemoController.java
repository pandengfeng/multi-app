/**
 * 
 */
package com.mfd.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mfd.demo.fegin.DemoFeignClient;

/**
 * @author pandengfeng
 *
 */
@RestController
@RefreshScope
public class DemoController {
	
	@Autowired
	private DemoFeignClient demoFeignClient;
	
	/**
	 * 消费 operator_service 服务
	 * demoTest
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/demo",method = RequestMethod.POST,consumes="application/json")
	public String demoTest(@RequestBody Map<String,String> params) {
		return demoFeignClient.demoTest(params);
	}
	
}
