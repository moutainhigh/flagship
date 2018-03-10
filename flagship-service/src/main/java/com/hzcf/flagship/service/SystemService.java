package com.hzcf.flagship.service;


/**
 *<dl>
 *<dt>类名：SystemService.java</dt>
 *<dd>描述: 系统模块service</dd> 
 *<dd>创建时间： 2017年7月4日 上午10:38:56</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年7月4日 上午10:38:56    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface SystemService {
	/**
	 * 属性配置表中根据属性名称查找String类型的属性值
	 */
	public String getPropertyStringValueByName(String propertyName);
}
