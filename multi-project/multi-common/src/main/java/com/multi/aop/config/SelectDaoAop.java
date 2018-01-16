/**
 * 
 */
package com.multi.aop.config;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * Dao查询方法aop
 * 2017-12-18
 * @author pandengfeng
 */
@Aspect
@Component
public class SelectDaoAop {
	/**
	 * Dao查询 切入点
	 * @param params 参数	java.util.Map
	 */
	 @Pointcut("execution(* com.multi.user.dao.*.get*(java.util.Map)) &&" + "args(params)")
	 public void excudeDao(Map<String,Object> params){}
	/**
	 * 对所有的dao层 get* args为Map String,Object params 的方法做前置处理
	 * @param params 传递的参数	Map String,Object params 
	 * @param	joinPoint JoinPoint 切点对象
	 */
	@Before(value="excudeDao(params)",argNames="params")
	public void dealParamsBeforeSelete(JoinPoint joinPoint,Map<String,Object> params) {
		if(ObjectUtils.isEmpty(params)) {
			params = new HashMap<String,Object>();
		}
		params.put("isEmpty", params.isEmpty());
	}
	
}
