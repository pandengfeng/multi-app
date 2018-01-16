/**
 * 
 */
package com.multi.user.util;

import com.multi.jwt.config.JwtUtil;
import com.multi.redis.config.RedisUtil;
import com.multi.user.entity.BaseUserInfo;

/**
 * user服务 工具栏
 * 2017-12-21
 * @author pandengfeng
 *
 */
public class UserServerUtil {
	
	/**
	 * 缓存用户token
	 * @param redisUtil	redis操作对象
	 * @param baseUserInfo	登录的用户
	 * @return String
	 */
	public static String setRedisToken(RedisUtil redisUtil,BaseUserInfo baseUserInfo) {
		String userId = baseUserInfo.getUserId() + System.currentTimeMillis()+"";
		redisUtil.set(userId, userId, JwtUtil.expireTime);
		return JwtUtil.generateToken(userId);
	}
}
