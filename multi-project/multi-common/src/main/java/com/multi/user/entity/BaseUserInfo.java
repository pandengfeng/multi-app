/**
 * 
 */
package com.multi.user.entity;

import java.io.Serializable;
import java.util.Date;

import com.multi.entity.base.BaseEntity;

/**
 * 基础用户表 base_user_info
 * 2017-12-15
 * @author pandengfeng
 */
public class BaseUserInfo extends BaseEntity implements Serializable{

	/**
	 * io序列
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 帐号
	 */
	private String account;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the lastLoginTime
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * @param lastLoginTime the lastLoginTime to set
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	

}
