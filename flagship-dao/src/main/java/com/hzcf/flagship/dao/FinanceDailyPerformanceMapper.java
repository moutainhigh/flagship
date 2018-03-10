package com.hzcf.flagship.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzcf.flagship.model.FinanceDailyPerformance;
import com.hzcf.flagship.model.FinanceDailyPerformanceExample;

public interface FinanceDailyPerformanceMapper {
	int countByExample(FinanceDailyPerformanceExample example);

    int deleteByExample(FinanceDailyPerformanceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FinanceDailyPerformance record);

    int insertSelective(FinanceDailyPerformance record);

    List<FinanceDailyPerformance> selectByExample(FinanceDailyPerformanceExample example);

    FinanceDailyPerformance selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FinanceDailyPerformance record, @Param("example") FinanceDailyPerformanceExample example);

    int updateByExample(@Param("record") FinanceDailyPerformance record, @Param("example") FinanceDailyPerformanceExample example);

    int updateByPrimaryKeySelective(FinanceDailyPerformance record);

    int updateByPrimaryKey(FinanceDailyPerformance record);

	List<FinanceDailyPerformance> selectByDate(String days);
	
	/**
	 * 获得begin,end范围内的业绩数据
	 * @param begin
	 * @param end
	 * @return
	 */
	List<Map<String, Object>> getPerformanceBetweenDate(Map<String, Date> params);
	/**
	 * 当日人均进件
	 * @param days
	 * @return
	 */
	Map<String, Object> selectAvgDailyPerEntry(String days);
	/**
	 * 日人均进件目标
	 * @param params
	 * @return
	 */
	Map<String, Object> selectAvgDailyPerEntryPlan(Map<String, Object> params);
	/**
	 * 当月日人均进件
	 * @param days
	 * @return
	 */
	List<Map<String, Object>> selectMonthAvgPerEntry(String days);
	/**
	 * 当月累计批核率
	 * @param para
	 * @return
	 */
	List<Map<String, Object>> selectAccumuRateForMonth(Map<String, Object> para);
	/**
	 * 当月累计批核件均	
	 * @param para
	 * @return
	 */
	List<Map<String, Object>> selectAccApprovalAvgForMonth(Map<String, Object> para);
}