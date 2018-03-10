package com.hzcf.flagship.service;

import com.hzcf.flagship.model.AppUser;
import com.hzcf.flagship.model.AppUserPermissionRelation;

/**
 * 
 *<dl>
 *<dt>类名：AppUserPermissionRelationService.java</dt>
 *<dd>描述: 客户端用户权限关系Service </dd> 
 *<dd>创建时间： 2017年5月13日 下午5:49:24</dd>
 *<dd>创建人： Caigen</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月13日 下午5:49:24    Caigen       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface AppUserPermissionRelationService {
	/**
	 * 保存
	 * @param record
	 */
	public void save(AppUserPermissionRelation record);
	
	/**
	 * 更新
	 * @param record
	 */
	public void update(AppUserPermissionRelation record);
	
	/**
	 * 删除权限关系
	 * @param id
	 */
	public void deleteByUID(int id);
	
	/**
	 * 修改权限
	 * @param user
	 * @param oauth
	 */
	public void updatePermission(AppUser user, String[] oauth);
	
	/**
	 * 新增权限
	 * @param user
	 * @param oauth
	 * @param sort 
	 */
	public void saveMultiPermission(AppUser user, String[] oauth, String[] letter);
	
}
