package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.RiskProductWarning;
import com.hzcf.flagship.model.RiskProductWarningExample;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RiskProductWarningMapper {
    int countByExample(RiskProductWarningExample example);

    int deleteByExample(RiskProductWarningExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RiskProductWarning record);

    int insertSelective(RiskProductWarning record);

    List<RiskProductWarning> selectByExample(RiskProductWarningExample example);

    RiskProductWarning selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RiskProductWarning record, @Param("example") RiskProductWarningExample example);

    int updateByExample(@Param("record") RiskProductWarning record, @Param("example") RiskProductWarningExample example);

    int updateByPrimaryKeySelective(RiskProductWarning record);

    int updateByPrimaryKey(RiskProductWarning record);

	List<Map<String, Object>> selectPorductWarList();
	
	/**
	 * 获得某日所有产品的预警指标值
	 * @param date
	 * @return
	 */
	List<Map<String, Object>> getWarningIndexByDate(Date date);
	
	/**
	 * 根据日期和产品获得预警指标值
	 * @return
	 */
	Map<String, Object> getWarningIndexByProductAndDate(Map<String, Object> params);
	
}