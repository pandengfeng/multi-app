/**
 * 
 */
package com.multi.bean.base;

import java.io.Serializable;
import java.util.Date;

/**
 * 统一数据返回模型
 * 2017-12-18
 * @author pandengfeng
 *
 */
public class ResponseBean<T> implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 返回数据
	 */
	private T data;
	/**
	 * 返回码
	 */
	private String returnCode;
	/**
	 * 返回异常
	 */
	private Exception ex;
	/**
	 * 返回时间
	 */
	private Date time = new Date();
	/**
	 * 服务名
	 */
	private String serverName;
	/**
	 * 返回描述
	 */
	private String message;
	
	
	/**
	 * 构造 使用 **ServerAppliction.getApplicationName() 设置 serverName
	 * @param serverName 服务名称
	 */
	public ResponseBean(String serverName) {
		this.serverName = serverName;
	}
	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
	/**
	 * @return the returnCode
	 */
	public String getReturnCode() {
		return returnCode;
	}
	/**
	 * @param returnCode the returnCode to set
	 */
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	/**
	 * @return the ex
	 */
	public Exception getEx() {
		return ex;
	}

	/**
	 * @param ex the ex to set
	 */
	public void setEx(Exception ex) {
		this.ex = ex;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @return the serverName
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * @param serverName the serverName to set
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	
	
}
