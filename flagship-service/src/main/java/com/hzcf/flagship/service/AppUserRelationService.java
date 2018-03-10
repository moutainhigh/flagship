package com.hzcf.flagship.service;

import com.hzcf.flagship.model.AppUser;

/**
 * 
 * <dl>
 * <dt>类名：AppUserRelationService.java</dt>
 * <dd>描述: 客户端用户 联系人关系Service</dd>
 * <dd>创建时间： 2017年7月25日</dd>
 * <dd>创建人： Tieguowei</dd>
 * <dt>版本历史:</dt>
 * 
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 *  2017年7月25日    Tieguowei       1.0        1.0 Version
 * </pre>
 * </dl>
 */
public interface AppUserRelationService {

	/**
	 * 修改联系人
	 * 
	 * @param user
	 * @param oauth
	 */
	public void updateUserRelation(AppUser user, String[] letter);
	

}
