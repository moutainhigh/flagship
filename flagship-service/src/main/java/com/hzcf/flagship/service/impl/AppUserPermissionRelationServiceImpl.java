package com.hzcf.flagship.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.AppUserPermissionRelationMapper;
import com.hzcf.flagship.dao.AppUserRelationshipsMapper;
import com.hzcf.flagship.model.AppUser;
import com.hzcf.flagship.model.AppUserPermissionRelation;
import com.hzcf.flagship.model.AppUserRelationships;
import com.hzcf.flagship.service.AppUserPermissionRelationService;

/**
 * 
 *<dl>
 *<dt>类名：AppUserPermissionRelationServiceImpl.java</dt>
 *<dd>描述: 客户端用户权限关系Service实现</dd> 
 *<dd>创建时间： 2017年5月13日 下午5:51:17</dd>
 *<dd>创建人： Caigen</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月13日 下午5:51:17    Caigen       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Service("appUserPermissionRelationService")
public class AppUserPermissionRelationServiceImpl implements AppUserPermissionRelationService {
	@Autowired
	private AppUserPermissionRelationMapper  appUserPermissionRelationMapper;
	@Autowired
	private AppUserRelationshipsMapper appUserRelationshipsMapper;
	@Override
	public void save(AppUserPermissionRelation record) {
		 appUserPermissionRelationMapper.insertSelective(record);
	}

	@Override
	public void update(AppUserPermissionRelation record) {
		appUserPermissionRelationMapper.updateByUserID(record); 
	}

	@Override
	public void deleteByUID(int id) {
		appUserPermissionRelationMapper.deleteByUID(id); 
	}

	@Override
	public void updatePermission(AppUser user, String[] oauth) {
		// delete all
		appUserPermissionRelationMapper.deleteByUID(user.getId()); 
		// insert
		AppUserPermissionRelation record = new AppUserPermissionRelation();
		record.setUserId(user.getId());
		record.setCreateTime(user.getCreateTime());
		record.setCreatorId(user.getCreatorId()); 
		if(null != oauth  || oauth.length != 0){
			for (int i = 0; i < oauth.length; i++) {
				String str = oauth[i]; 
				record.setPermissionId(Integer.valueOf( str ) );
				appUserPermissionRelationMapper.insertSelective(record);
			}
		}
		
	}

	@Override
	public void saveMultiPermission(AppUser user, String[] oauth, String[] letter) {
		
		//添加 用户权限关系表
		AppUserPermissionRelation record = new AppUserPermissionRelation();
		record.setUserId(user.getId());
		record.setCreateTime(user.getCreateTime());
		record.setCreatorId(user.getCreatorId());
		
		 
		for (int i = 0; i < oauth.length; i++) {
			String str = oauth[i];
			record.setPermissionId(Integer.valueOf( str ) );
			appUserPermissionRelationMapper.insertSelective(record);
		}
		//添加 用户  私信联系人 关系表
		AppUserRelationships appUserRelationships = new AppUserRelationships();
		appUserRelationships.setFromUserId(user.getId());
		appUserRelationships.setCreateTime(user.getCreateTime());
		appUserRelationships.setCreator(user.getCreatorId());
		if(letter != null){
			for (String string : letter) {
				appUserRelationships.setToUserId(Integer.valueOf(string));
				appUserRelationshipsMapper.insertSelective(appUserRelationships);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("toUserId", string);
				map.put("status", "1");
				appUserRelationshipsMapper.updateToUserIdStatus(map);
			}
		}
		
	}

}
