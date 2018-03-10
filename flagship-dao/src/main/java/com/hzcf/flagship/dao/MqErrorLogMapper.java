package com.hzcf.flagship.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzcf.flagship.model.MqErrorLog;
import com.hzcf.flagship.model.MqErrorLogExample;

public interface MqErrorLogMapper {
    int countByExample(MqErrorLogExample example);

    int deleteByExample(MqErrorLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MqErrorLog record);

    int insertSelective(MqErrorLog record);

    List<MqErrorLog> selectByExample(MqErrorLogExample example);

    MqErrorLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MqErrorLog record, @Param("example") MqErrorLogExample example);

    int updateByExample(@Param("record") MqErrorLog record, @Param("example") MqErrorLogExample example);

    int updateByPrimaryKeySelective(MqErrorLog record);

    int updateByPrimaryKey(MqErrorLog record);

	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	Long findAllByPageCount(Map<String, Object> paramsCondition);
}