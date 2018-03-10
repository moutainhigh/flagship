package com.hzcf.flagship.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzcf.flagship.model.FinanceDailyDistrict;
import com.hzcf.flagship.model.FinanceDailyDistrictExample;

public interface FinanceDailyDistrictMapper {
    int countByExample(FinanceDailyDistrictExample example);

    int deleteByExample(FinanceDailyDistrictExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FinanceDailyDistrict record);

    int insertSelective(FinanceDailyDistrict record);

    List<FinanceDailyDistrict> selectByExample(FinanceDailyDistrictExample example);

    FinanceDailyDistrict selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FinanceDailyDistrict record, @Param("example") FinanceDailyDistrictExample example);

    int updateByExample(@Param("record") FinanceDailyDistrict record, @Param("example") FinanceDailyDistrictExample example);

    int updateByPrimaryKeySelective(FinanceDailyDistrict record);

    int updateByPrimaryKey(FinanceDailyDistrict record);

	List<Map<String, Object>> financeDistrictOfDay(Map<String, Object> map);

	Map<String, Object> financeDistrictOfDayList(Map<String, Object> requestMap);
	
	/**
	 * 根据日期查询记录数(count *)和区域数(count DISTINCT district_name)之间的差值
	 * @param recordDate
	 * @return
	 */
	Map<String, Object> getDiffOfCountAndDistrictNumByDate(Date date);
}