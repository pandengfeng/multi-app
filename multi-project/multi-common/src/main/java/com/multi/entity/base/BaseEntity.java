/**
 * 
 */
package com.multi.entity.base;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类bean
 * 2017-12-18 
 * @author pandengfeng
 *
 */
public class BaseEntity implements Serializable{
	/**
	 * io序列
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 创建时间
	 */
	protected Date createTime;
	/**
	 * 更新时间
	 */
	protected Date updateTime;
	/**
	 * 禁用字段 1禁用  0可用
	 */
	protected String disable;
	/**
	 * 操作次数 对记录进行操作时 +1 
	 */
	protected Integer operand = 1;
	
	/**
	 * 禁用字段状态 DISABLECODE1 = 1  
	 */
	public final static String DISABLECODE1 = "1";
	/**
	 * 禁用字段状态 DISABLECODE0 = 0
	 */
	public final static String DISABLECODE0 = "0";
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @return the disable
	 */
	public String getDisable() {
		return disable;
	}
	/**
	 * @param disable the disable to set
	 */
	public void setDisable(String disable) {
		this.disable = disable;
	}
	/**
	 * @return the operand
	 */
	public Integer getOperand() {
		return operand;
	}
	/**
	 * @param operand the operand to set
	 */
	public void setOperand(Integer operand) {
		this.operand = operand;
	}
	
	
	
}
