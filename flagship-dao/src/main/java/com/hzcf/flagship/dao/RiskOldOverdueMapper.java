package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.RiskOldOverdue;
import com.hzcf.flagship.model.RiskOldOverdueExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RiskOldOverdueMapper {
    int countByExample(RiskOldOverdueExample example);

    int deleteByExample(RiskOldOverdueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RiskOldOverdue record);

    int insertSelective(RiskOldOverdue record);

    List<RiskOldOverdue> selectByExample(RiskOldOverdueExample example);

    RiskOldOverdue selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RiskOldOverdue record, @Param("example") RiskOldOverdueExample example);

    int updateByExample(@Param("record") RiskOldOverdue record, @Param("example") RiskOldOverdueExample example);

    int updateByPrimaryKeySelective(RiskOldOverdue record);

    int updateByPrimaryKey(RiskOldOverdue record);

	void insertOldOverdue(List<?> list2);

	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	Long findAllByPageCount(Map<String, Object> paramsCondition);

	void deleteOldOverdueByCreateTime(String date);

	List<Map<String, Object>> findOldOverdueByCreateTime(String date);
}