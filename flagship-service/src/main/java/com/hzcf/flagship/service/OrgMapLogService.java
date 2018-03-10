package com.hzcf.flagship.service;

import java.util.Map;

import com.hzcf.flagship.util.PageModel;


/**
 *<dl>
 *<dt>类名：OrgMapLogService.java</dt>
 *<dd>描述:理财</dd> 
 *<dd>创建时间： 2017年7月3日 上午11:04:18</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年7月3日 上午11:04:18    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface OrgMapLogService {

	/**
	 * 理财对比表分页查询
	 * 
	 */
	public PageModel findAllByPage(Map<String, Object> paramsCondition);
	


}
