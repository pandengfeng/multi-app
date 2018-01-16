/**
 * 
 */
package com.mfd.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfd.demo.dao.DemoDao;
import com.mfd.demo.entity.OpsConfig;

/**
 * @author pandengfeng
 *
 */
@Service
public class DemoService {

	@Autowired
	private DemoDao demoDao;
	
	public OpsConfig getByKey(String key) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("key", key);
		return demoDao.getByKey(params);
	}
}
