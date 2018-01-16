/**
 * 
 */
package com.mfd.demo.dao;

import java.util.Map;

import com.mfd.demo.entity.OpsConfig;

/**
 * @author pandengfeng
 *
 */
public interface DemoDao {
	
	public OpsConfig getByKey(Map<String,Object> params);
}
