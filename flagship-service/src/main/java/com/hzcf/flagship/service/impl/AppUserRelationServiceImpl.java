package com.hzcf.flagship.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.AppUserMapper;
import com.hzcf.flagship.dao.AppUserRelationshipsMapper;
import com.hzcf.flagship.model.AppUser;
import com.hzcf.flagship.model.AppUserRelationships;
import com.hzcf.flagship.service.AppUserRelationService;

/**
 * <dl>
 * <dt>类名：AppUserRelationServiceImpl.java</dt>
 * <dd>描述: 客户端用户 联系人关系</dd>
 * <dd>创建时间： 2017年7月25日 上午11:46:14</dd>
 * <dd>创建人： TieGuowei</dd>
 * <dt>版本历史:</dt>
 * 
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年7月25日 上午11:46:14    TieGuowei       1.0        1.0 Version
 * </pre>
 * </dl>
 */
@Service()
public class AppUserRelationServiceImpl implements AppUserRelationService {
	@Autowired
	private AppUserRelationshipsMapper appUserRelationshipsMapper;
	@Autowired
	private AppUserMapper appUserMapper;
	@Override
	public void updateUserRelation(AppUser user, String[] letter) {
		// delete fromId
		appUserRelationshipsMapper.deleteFromUID(user.getId());
		// insert
		AppUserRelationships record = new AppUserRelationships();
		record.setFromUserId(user.getId());
		record.setCreateTime(user.getCreateTime());
		record.setCreator(user.getCreatorId());
		record.setStatus("1");
		for (int i = 0; i < letter.length; i++) {
			String str = letter[i];
			record.setToUserId(Integer.valueOf(str));
			appUserRelationshipsMapper.insertSelective(record);
		}
		
	}

}
