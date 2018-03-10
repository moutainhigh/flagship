package com.hzcf.flagship.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hzcf.flagship.model.OrgLocation;
import com.hzcf.flagship.model.OrgLocationExample;

public interface OrgLocationMapper {
    int countByExample(OrgLocationExample example);

    int deleteByExample(OrgLocationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrgLocation record);

    int insertSelective(OrgLocation record);

    List<OrgLocation> selectByExample(OrgLocationExample example);

    OrgLocation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrgLocation record, @Param("example") OrgLocationExample example);

    int updateByExample(@Param("record") OrgLocation record, @Param("example") OrgLocationExample example);

    int updateByPrimaryKeySelective(OrgLocation record);

    int updateByPrimaryKey(OrgLocation record);

	void insertOrgLocationMapper(List<OrgLocation> orgLocationList);
}