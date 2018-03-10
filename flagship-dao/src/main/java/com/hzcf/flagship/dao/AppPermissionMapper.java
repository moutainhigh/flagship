package com.hzcf.flagship.dao;

import java.util.List;
import java.util.Map;

import com.hzcf.flagship.model.AppPermission;

public interface AppPermissionMapper extends BaseMapper<AppPermission>{

	List<AppPermission> selectAllPermissionById(Integer userId);

	List<AppPermission> findPermissionByUserId(String userId);

	List<Map<String, Object>> findPermissionList();

	List<AppPermission> selectPermissionById(Integer userId);
     
	/**
     * 查询每个rank的最新排序
     */
    Integer getSortingByRank(int rank);
}