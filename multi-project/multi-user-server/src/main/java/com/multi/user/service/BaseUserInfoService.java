/**
 * 
 */
package com.multi.user.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.common.util.MapUtils;
import com.multi.entity.base.BaseEntity;
import com.multi.user.dao.BaseUserInfoDao;
import com.multi.user.entity.BaseUserInfo;

/**
 * 基础用户 业务层	
 * 2017-12-19
 * @author pandengfeng
 */
@Service
public class BaseUserInfoService {
	
	/**
	 * 自动注入BaseUserInfoDao
	 */
	@Autowired
	private BaseUserInfoDao baseUserInfoDao;
	
	/**
	 * 通过参数查询唯一 BaseUserInfo
	 * @param params	查询参数
	 * @return	返回查询结果,查询失败返回null
	 */
	public BaseUserInfo getBaseUserInfoByParamsUnqiue(Map<String,Object> params) {
		List<BaseUserInfo> baseUserInfoList = baseUserInfoDao.getByParams(params);
		if(baseUserInfoList.size() == 1) {
			return baseUserInfoList.get(0);
		}
		return null;
	}
	/**
	 * 添加用户
	 * @param params	参数
	 * @return	添加成功或失败
	 */
	public Boolean insertBaseUserInfo(Map<String,Object> params) {
		Date date = new Date();
		params.put("createTime", date);
		params.put("updateTime", date);
		params.put("disable", BaseEntity.DISABLECODE0);
		params.put("operand", 1);
		Integer sum=baseUserInfoDao.insert(params);
		if(sum == 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 更新最后登录时间
	 * @param baseUserInfo 登录对象
	 * @return	成功或失败
	 */
	public Boolean updateLastLoginTime(BaseUserInfo baseUserInfo) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		Date date = new Date();
		map = MapUtils.java2Map(baseUserInfo);
		map.put("lastLoginTime", date);
		map.put("operand",	baseUserInfo.getOperand()+1);
		Integer sum=baseUserInfoDao.updateLoginTime(map);
		if(sum == 1) {
			return true;
		}
		return false;
	}
	/**
	 * 禁用用户
	 * @param params	参数
	 * @return 成功或失败
	 */
	public Boolean deleteBaseUserInfo(Map<String,Object> params) {
		params.put("operand", 
				(Integer)params.get("operand") + 1);
		Integer sum = baseUserInfoDao.delete(params);
		if(sum == 1) {
			return true;
		}
		return false;
	}
	 
}
