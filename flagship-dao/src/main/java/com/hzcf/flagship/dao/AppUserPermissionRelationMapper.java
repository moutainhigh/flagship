package com.hzcf.flagship.dao;

import java.util.List;

import com.hzcf.flagship.model.AppUserPermissionRelation;

public interface AppUserPermissionRelationMapper extends BaseMapper<AppUserPermissionRelation>{
	public void updateByUserID(AppUserPermissionRelation record);
	
	public void deleteByUID(int userId);

	public List findPermissionByUserId(String userId);
}