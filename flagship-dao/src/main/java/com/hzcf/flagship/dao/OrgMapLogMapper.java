package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.OrgMapLog;
import com.hzcf.flagship.model.OrgMapLogExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrgMapLogMapper {
    int countByExample(OrgMapLogExample example);

    int deleteByExample(OrgMapLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrgMapLog record);

    int insertSelective(OrgMapLog record);

    List<OrgMapLog> selectByExample(OrgMapLogExample example);

    OrgMapLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrgMapLog record, @Param("example") OrgMapLogExample example);

    int updateByExample(@Param("record") OrgMapLog record, @Param("example") OrgMapLogExample example);

    int updateByPrimaryKeySelective(OrgMapLog record);

    int updateByPrimaryKey(OrgMapLog record);

	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	Long findAllByPageCount(Map<String, Object> paramsCondition);
}