package com.hzcf.flagship.service;

import java.util.List;

import com.hzcf.flagship.model.AppPermission;


/**
 *<dl>
 *<dt>类名：AppPermissionService.java</dt>
 *<dd>描述: 查询权限</dd> 
 *<dd>创建时间： 2017年6月12日 下午3:17:41</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年6月12日 下午3:17:41    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface AppPermissionService {
	/*
	 * 查询权限
	 * @param user
	 * @param oauth
	 */
	public List<AppPermission> findPermissionByUserId(String userId);
	
}
