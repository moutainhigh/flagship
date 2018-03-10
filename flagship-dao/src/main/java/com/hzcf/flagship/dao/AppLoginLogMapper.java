package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.AppLoginLog;
import com.hzcf.flagship.model.AppLoginLogExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AppLoginLogMapper {
    int countByExample(AppLoginLogExample example);

    int deleteByExample(AppLoginLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppLoginLog record);

    int insertSelective(AppLoginLog record);

    List<AppLoginLog> selectByExample(AppLoginLogExample example);

    AppLoginLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppLoginLog record, @Param("example") AppLoginLogExample example);

    int updateByExample(@Param("record") AppLoginLog record, @Param("example") AppLoginLogExample example);

    int updateByPrimaryKeySelective(AppLoginLog record);

    int updateByPrimaryKey(AppLoginLog record);

	void insertLoginLog(Map<String, Object> params);
}