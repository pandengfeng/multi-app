/**
 * 
 */
package com.mfd.demo.controller;

import java.util.ArrayList;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mfd.demo.entity.OpsConfig;
import com.mfd.demo.service.DemoService;
import com.mfd.util.RedisUtil;

/**
 * @author pandengfeng
 *
 */
@RestController
@RefreshScope
public class DemoController {
	
	@Autowired
	private DemoService demoService;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Value("${my.test}")
	private String myTest;
	
	@ResponseBody
	@RequestMapping(value="/demo",method = RequestMethod.POST,consumes="application/json")
	public String demoTest(@RequestBody Map<String,String> params) {
		String key = "";
		key = params.get("key");
		OpsConfig config = demoService.getByKey(key);
		JSONObject jsonObject = new JSONObject(config);
		return jsonObject.toString()+" my.test:"+myTest;
		
	}
	
	/**
	 * 这是个redis测试
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/test",method = RequestMethod.POST,produces="application/json;charset=UTF-8",consumes="application/json;charset=UTF-8")
	public String demoRedisUtil(@RequestBody Map<String,String> params) {
		redisUtil.set("test", "这是个测试");
		System.err.println((String) redisUtil.get("test"));
		return (String) redisUtil.get("test");
	}
	
}
