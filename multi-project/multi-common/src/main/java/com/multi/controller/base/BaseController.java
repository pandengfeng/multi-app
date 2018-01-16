/**
 * 
 */
package com.multi.controller.base;

import java.util.Map;

import org.springframework.util.StringUtils;

import com.multi.bean.base.ResponseBean;
import com.multi.bean.base.ReturnCode;


/**
 * 基础controller 
 * 2017-12-21
 * @author pandengfeng
 *
 */
public class BaseController {
	
	/**
	 * Controller参数验证
	 * @param keys 需要不为空的参数
	 * @param paramsMap	controller接收的参数
	 * @param resBean 	ResponseBean ? 对象 统一
	 * @return	true or false
	 */
	protected boolean valitationParams(String[] keys,Map<String, Object> paramsMap,ResponseBean<?> resBean) {
		for(String key : keys) {
			if(StringUtils.isEmpty(paramsMap.get(key))) {
				resBean.setReturnCode(ReturnCode.CODE0002.getCode());
				resBean.setMessage(key+" can not null");
				return false;
			}
		}
		return true;
	}
}
