package com.multi.jwt.config;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.multi.redis.config.RedisUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt 过滤器 Filter
 * 2017-12-21
 * @author pandengfeng
 *
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	/**
	 * 不需要过滤的 url
	 */
    private final String[] protectUrlPattern;
    /**
     * redis操作
     */
    private RedisUtil redisUtil;
    /**
     * URL 匹配
     */
    private static final PathMatcher pathMatcher = new AntPathMatcher();
    
    public JwtAuthenticationFilter(String[] protectUrlPattern,RedisUtil redisUtil) {
        this.protectUrlPattern = protectUrlPattern;
        this.redisUtil = redisUtil;
    }
    
    /**
     * 自定过滤
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    	try {
    		boolean valit = true;
    		for(String url : protectUrlPattern) {
    			if(pathMatcher.match(url, request.getServletPath())) {
    				valit = false;
    			}
    		}
        	//匹配url 
            if(valit) {
            	//验证token
                JwtUtil.validateToken(request,redisUtil);
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }
        filterChain.doFilter(request, response);
    }

}