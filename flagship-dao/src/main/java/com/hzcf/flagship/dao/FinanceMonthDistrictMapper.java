package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.FinanceMonthDistrict;
import com.hzcf.flagship.model.FinanceMonthDistrictExample;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface FinanceMonthDistrictMapper {
    int countByExample(FinanceMonthDistrictExample example);

    int deleteByExample(FinanceMonthDistrictExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FinanceMonthDistrict record);

    int insertSelective(FinanceMonthDistrict record);

    List<FinanceMonthDistrict> selectByExample(FinanceMonthDistrictExample example);

    FinanceMonthDistrict selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FinanceMonthDistrict record, @Param("example") FinanceMonthDistrictExample example);

    int updateByExample(@Param("record") FinanceMonthDistrict record, @Param("example") FinanceMonthDistrictExample example);

    int updateByPrimaryKeySelective(FinanceMonthDistrict record);

    int updateByPrimaryKey(FinanceMonthDistrict record);

	List<Map<String, Object>> findAllDistrictOfMonth(Map<String, Object> map);

	Map<String, Object> financeDistrictOfMonthList(Map<String, Object> requestMap);

	List<FinanceMonthDistrict> findMonthDistrictTheLatestOne();

	Map<String, Object> findRankingOfLastMonth(Map<String, Object> lastMonthMap);

	List<FinanceMonthDistrict> findLastDateByDistrict(Map<String, Object> map);
	
	/**
	 * 根据日期查询记录数(count *)和区域数(count DISTINCT district_name)之间的差值
	 * @param recordDate
	 * @return
	 */
	Map<String, Object> getDiffOfCountAndDistrictNumByDate(Date date);

}