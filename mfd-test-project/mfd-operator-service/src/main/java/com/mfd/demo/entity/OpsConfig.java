package com.mfd.demo.entity;

import java.util.Date;

/**
 * 新增配置中心运维配置表
 * config_ops
 */
public class OpsConfig{

	private Long id;
	private String key;
	private String value;
	
	private Long optimistic;
	private Date createTime;
	private Date updateTime;
	private Long optimisticLastsync; 
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOptimistic() {
		return optimistic;
	}
	public void setOptimistic(Long optimistic) {
		this.optimistic = optimistic;
	}

	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getOptimisticLastsync() {
		return optimisticLastsync;
	}
	public void setOptimisticLastsync(Long optimisticLastsync) {
		this.optimisticLastsync = optimisticLastsync;
	}

	
	
	

}
