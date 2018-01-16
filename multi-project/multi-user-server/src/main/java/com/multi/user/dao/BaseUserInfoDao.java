/**
 * 
 */
package com.multi.user.dao;


import java.util.Map;

import com.multi.dao.base.BaseDao;
import com.multi.user.entity.BaseUserInfo;

/**
 * BaseUserInfo 对象DB操作层
 * 2017-12-18
 * @author pandengfeng
 */
public interface BaseUserInfoDao extends BaseDao<BaseUserInfo>{
	
	/**
	 * 更新最后登录时间
	 * @param params 条件参数
	 * @return	影响行数
	 */
	public Integer updateLoginTime(Map<String,Object> params);
}
