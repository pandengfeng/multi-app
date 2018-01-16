/**
 * 
 */
package com.multi.dao.base;

import java.util.List;
import java.util.Map;

/**
 * 基础DB操作层
 * 2017-12-18
 * @author pandengfeng
 *	
 */
public interface BaseDao<T> {
	/**
	 * 基础插入操作 操作结束 将主键赋值到操作对象中
	 * @param params 操作对象
	 * @return Integer 影响行数
	 */
	Integer insert(Map<String,Object> params);
	/**
	 * 基础更新方法
	 * @param params	操作对象
	 * @return	返回操作影响行数
	 */
	Integer update(Map<String,Object> params);
	
	/**
	 * 逻辑删除 将禁用字段 disable 置 1 
	 * @param params 对象
	 * @return 返回操作影响行数
	 */
	Integer delete(Map<String,Object> params);
	/**
	 * 通过参数来获取对象
	 * @param params 通过 map.put()
	 * @return	通过主键查到的对象集合
	 */
	List<T> getByParams(Map<String,Object> params);
}
