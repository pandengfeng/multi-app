package com.multi.jwt.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.multi.redis.config.RedisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * jwt工具类
 * 2017-12-21
 * @author pandengfeng
 *
 */
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    public static final long EXPIRATION_TIME = 3600_000; // 1 hour
    public static final String SECRET = "ThisIsASecret";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final String USER_ID = "userId";
    public static final Long expireTime = new Long(2592000);
    
    /**
     * 通过字符串生成token
     * @param userId 字符串
     * @return String
     */
    public static String generateToken(String userId) {
        HashMap<String, Object> map = new HashMap<>();
        //you can put any data in the map
        try {
            map.put(USER_ID, EncryptUtil.encrypt(userId));
        } catch (Exception e) {
            logger.warn("Encryption failed. "+e.getMessage());
            throw new RuntimeException("Encryption failed");
        }
        String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return jwt;
    }
    /**
     * 验证token 
     * @param request	request请求
     * @param	redisUtil	redis操作对象
     */
    public static void validateToken(HttpServletRequest request,RedisUtil redisUtil) {
    	//获取token
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            try {
                Map<String, Object> body = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody();
                String userId = (String) body.get(USER_ID);
                //解密
                userId=EncryptUtil.decrypt(userId);
                String redisUserId = (String) redisUtil.get(userId);
                if(!redisUserId.equals(userId)) {
                
                	//token验证失败 去掉token
                	 redisUtil.remove(userId);
                	 throw new TokenValidationException("Token valitation mistake");
                }
                //设置超时时间
                redisUtil.set(redisUserId, redisUserId, expireTime);
            } catch (Exception e) {
                logger.info(e.getMessage());
                throw new TokenValidationException(e.getMessage());
            }
        } else {
            logger.info("Missing token");
            throw new TokenValidationException("Missing token");
        }
    }

    public static class CustomHttpServletRequest extends HttpServletRequestWrapper {
        private String userId;

        public CustomHttpServletRequest(HttpServletRequest request, String userId) {
            super(request);
            this.userId = userId;
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            if (name != null && (name.equals(USER_ID))) {
                return Collections.enumeration(Arrays.asList(userId));
            }
            return super.getHeaders(name);
        }
    }

    static class TokenValidationException extends RuntimeException {
        /**
		 * 
		 */
		private static final long serialVersionUID = -6242543502203576084L;

		public TokenValidationException(String msg) {
            super(msg);
        }
    }
}