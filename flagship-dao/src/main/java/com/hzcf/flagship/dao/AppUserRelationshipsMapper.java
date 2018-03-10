package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.AppUserRelationships;
import com.hzcf.flagship.model.AppUserRelationshipsExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AppUserRelationshipsMapper {
	int countByExample(AppUserRelationshipsExample example);

    int deleteByExample(AppUserRelationshipsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AppUserRelationships record);

    int insertSelective(AppUserRelationships record);

    List<AppUserRelationships> selectByExample(AppUserRelationshipsExample example);

    AppUserRelationships selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AppUserRelationships record, @Param("example") AppUserRelationshipsExample example);

    int updateByExample(@Param("record") AppUserRelationships record, @Param("example") AppUserRelationshipsExample example);

    int updateByPrimaryKeySelective(AppUserRelationships record);

	void deleteByFromUID(Integer id);

	void updateToUserIdStatus(Map<String, Object> map);
	
	void deleteRelationsByToUserId(Map<String, Object> map);

	void deleteFromUID(Integer id);

	void updateToUserIdStatusOpen(Map<String, Object> map);
}