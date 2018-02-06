package com.multi.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.multi.bean.base.ResponseBean;
import com.multi.bean.base.ReturnCode;
import com.multi.common.util.ApplicationParams;
import com.multi.common.util.MapUtils;
import com.multi.controller.base.BaseController;
import com.multi.redis.config.RedisUtil;
import com.multi.user.entity.BaseUserInfo;
import com.multi.user.service.BaseUserInfoService;
import com.multi.user.util.UserServerUtil;

/**
 * 基本用户信息控制层
 * 2017-12-19
 * @author pandengfeng
 */
@RequestMapping(value="/user")
@RestController
@RefreshScope
public class BaseUserInfoController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(BaseUserInfoController.class);
	/**
	 * 注入BaseUserInfoService
	 */
	@Autowired
	private BaseUserInfoService baseUserInfoService;
	/**
	 * 注入RedisUtil
	 */
	@Autowired
	private RedisUtil redisUtil;
	/**
	 * 登录
	 * @param params BaseUserInfo对象参数 拦截其他属性
	 * @return ResponseBean String
	 */
	@RequestMapping(value="/login",method = RequestMethod.POST,consumes="application/json")
	public ResponseBean<String> login(@RequestBody  BaseUserInfo params) {
		logger.info("登录开始 login");
		ResponseBean<String> resBean = new ResponseBean<String>(ApplicationParams.getServerName());
		Map<String, Object> paramsMap = new HashMap<String, Object>();	
		paramsMap = MapUtils.java2Map(params);
		String[] keys = {"account","password"};
		if(!this.valitationParams(keys, paramsMap, resBean)) {
			logger.info("必须参数验证错误");
			return resBean;
		}
		logger.info("帐号:"+paramsMap.get("account")+" 参数验证通过,开始登录");
		try {		
			BaseUserInfo baseUserInfo = baseUserInfoService.getBaseUserInfoByParamsUnqiue(paramsMap);
			if(ObjectUtils.isEmpty(baseUserInfo)) {
				resBean.setReturnCode(ReturnCode.CODE0004.getCode());
				resBean.setMessage("帐号或密码错误");
				logger.info("帐号或密码错误");
				return resBean;
			}
			Boolean ok = baseUserInfoService.updateLastLoginTime(baseUserInfo);
			if(ok) {
				resBean.setReturnCode(ReturnCode.CODE0000.getCode());
				resBean.setMessage("登录成功");
				logger.info("登录成功");
				//缓存用户token
				String token = UserServerUtil.setRedisToken(redisUtil, baseUserInfo);
				resBean.setData(token);
				logger.info("帐号:"+paramsMap.get("account")+" 登录成功");
			}else {
				resBean.setReturnCode(ReturnCode.CODE0003.getCode());
				resBean.setMessage("未知错误,请查询日志.");
				logger.info("帐号:"+paramsMap.get("account")+" 更新最后登录时间失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			resBean.setReturnCode(ReturnCode.CODE0001.getCode());
			resBean.setEx(e);
			resBean.setMessage("出现异常");
		}
		logger.info("登录结束");
		return resBean;
	}
	
	/**
	 * 注册
	 * @param params 注册参数
	 * @return	返回	ResponseBean Boolean
	 */
	@RequestMapping(value="register",method = RequestMethod.POST,consumes="application/json")
	public ResponseBean<Boolean> register(@RequestBody  BaseUserInfo params){
		logger.info("注册开始 register");
		ResponseBean<Boolean> resBean = new ResponseBean<Boolean>(ApplicationParams.getServerName());
		Map<String, Object> paramsMap = new HashMap<String, Object>();	
		paramsMap = MapUtils.java2Map(params);
		//参数校验
		String[] keys = {"account","password","userName"};
		if(!this.valitationParams(keys, paramsMap, resBean)) {
			logger.info("必须参数验证错误");
			return resBean;
		}
		try {
			//查询帐号是否已经注册
			Map<String, Object> paramsMapOld = new HashMap<String, Object>();	
			paramsMapOld.put("account", paramsMap.get("account"));
			BaseUserInfo baseUserInfo = baseUserInfoService.getBaseUserInfoByParamsUnqiue(paramsMapOld);
			if(!ObjectUtils.isEmpty(baseUserInfo)) {
				
				resBean.setReturnCode(ReturnCode.CODE0006.getCode());
				resBean.setMessage(paramsMap.get("account")+":帐号已经被注册");
				resBean.setData(false);
				return resBean;
			}
			
			Boolean ok = baseUserInfoService.insertBaseUserInfo(paramsMap);
			//注册成功
			if(ok) {
				resBean.setReturnCode(ReturnCode.CODE0000.getCode());
				resBean.setMessage("注册成功");
				resBean.setData(true);
				logger.info("注册成功");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			resBean.setReturnCode(ReturnCode.CODE0001.getCode());
			resBean.setEx(e);
			resBean.setMessage("出现异常");
		}
		logger.info("注册结束");
		return resBean;
	}
	
	
	/**
	 * 禁用用户
	 * @param params	参数
	 * @return	ResponseBean Boolean
	 */
	@RequestMapping(method = RequestMethod.DELETE,consumes="application/json")
	public ResponseBean<Boolean> delete(@RequestBody  BaseUserInfo params) {
		logger.info("禁用用户开始");
		ResponseBean<Boolean> resBean = new ResponseBean<Boolean>(ApplicationParams.getServerName());
		Map<String, Object> paramsMap = new HashMap<String, Object>();	
		paramsMap = MapUtils.java2Map(params);
		String[] keys = {"account"};
		if(!this.valitationParams(keys, paramsMap, resBean)) {
			logger.info("参数验证错误");
			return resBean;
		}
		try {
			BaseUserInfo baseUserInfo = baseUserInfoService.getBaseUserInfoByParamsUnqiue(paramsMap);
			if(ObjectUtils.isEmpty(baseUserInfo)) {
				resBean.setReturnCode(ReturnCode.CODE0005.getCode());
				resBean.setMessage("该用户不存在");
				logger.info("该用户不存在:"+paramsMap.get("account"));
				return resBean;
			}
			Map<String, Object> bean = new HashMap<String,Object>();
			bean = MapUtils.java2Map(baseUserInfo);
			Boolean ok = baseUserInfoService.deleteBaseUserInfo(bean);
			if(ok) {
				resBean.setReturnCode(ReturnCode.CODE0000.getCode());
				resBean.setMessage("禁用成功");
				logger.info("禁用成功");
				resBean.setData(true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			resBean.setReturnCode(ReturnCode.CODE0001.getCode());
			resBean.setEx(e);
			resBean.setMessage("出现异常");
		}
		logger.info("禁用操作结束");
		return resBean;
	}
	
	
}
