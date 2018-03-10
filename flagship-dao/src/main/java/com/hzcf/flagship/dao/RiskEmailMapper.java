package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.RiskEmail;
import com.hzcf.flagship.model.RiskEmailExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RiskEmailMapper {
    int countByExample(RiskEmailExample example);

    int deleteByExample(RiskEmailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RiskEmail record);

    int insertSelective(RiskEmail record);

    List<RiskEmail> selectByExample(RiskEmailExample example);

    RiskEmail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RiskEmail record, @Param("example") RiskEmailExample example);

    int updateByExample(@Param("record") RiskEmail record, @Param("example") RiskEmailExample example);

    int updateByPrimaryKeySelective(RiskEmail record);

    int updateByPrimaryKey(RiskEmail record);

	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	Long findAllByPageCount(Map<String, Object> paramsCondition);

	List<Map<String, Object>> getEmailData(Map<String, Object> map);
}