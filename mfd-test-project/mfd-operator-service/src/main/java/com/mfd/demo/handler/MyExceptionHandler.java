/**
 * 
 */
package com.mfd.demo.handler;


import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;


/**
 * @author pandengfeng
 *	错误处理
 */
//@ControllerAdvice
public class MyExceptionHandler {

	
	//@ExceptionHandler(value = Exception.class)
	//@ResponseBody
	public String defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		System.out.println("运行了异常处理 接收到异常:"+e);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", e.getMessage());
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
        	jsonObject.put("code","404");
        } else {
        	jsonObject.put("code","500");
        }
        jsonObject.put("status", false);
        jsonObject.put("data", "");
        return jsonObject.toString();
    }
	
		
}
