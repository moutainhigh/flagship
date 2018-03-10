package com.hzcf.flagship.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.AppPermissionMapper;
import com.hzcf.flagship.model.AppPermission;
import com.hzcf.flagship.service.AppPermissionService;


/**
 *<dl>
 *<dt>类名：AppPermissionServiceImpl.java</dt>
 *<dd>描述: ~节点业务逻辑实现</dd> 
 *<dd>创建时间： 2017年6月12日 下午3:19:02</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年6月12日 下午3:19:02    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Service
public class AppPermissionServiceImpl implements AppPermissionService {
	@Autowired
	private AppPermissionMapper  appPermissionMapper;

	@Override
	public List<AppPermission> findPermissionByUserId(String userId) {
		return appPermissionMapper.findPermissionByUserId(userId);
	}
	
}
