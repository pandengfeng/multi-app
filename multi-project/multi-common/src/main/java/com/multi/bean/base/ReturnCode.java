/**
 * 
 */
package com.multi.bean.base;
/**
 * 返回码枚举
 * 2017-12-20
 * @author pandengfeng
 *
 */
public enum ReturnCode {

	CODE0000("0000","成功"),
	CODE0001("0001","系统错误"),
	CODE0002("0002","参数验证错误"),
	CODE0003("0003","未知错误"),
	CODE0004("0004","验证错误"),
	CODE0005("0005","资源不存在");
	private String code;

	private String msg;
	
	ReturnCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
	
}
