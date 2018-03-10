package com.hzcf.flagship.service;

/**
 *<dl>
 *<dt>类名：CheckUserStatusJob.java</dt>
 *<dd>描述: 检查用户状态(是否离职),并更新</dd> 
 *<dd>创建时间： 2017年11月20日 下午2:08:06</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年11月20日 下午2:08:06    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface CheckUserStatusJobService {
	
	/**
	 * 检查前台app用户是否离职并更新app用户表中的状态
	 */
	public void checkAppUser();
	
	/**
	 * 检查后台用户是否离职并更新用户表中的状态
	 */
	public void checkAdminUser();
    
}
