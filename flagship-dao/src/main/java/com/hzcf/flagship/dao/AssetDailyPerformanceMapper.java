package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.AssetDailyPerformance;
import com.hzcf.flagship.model.AssetDailyPerformanceExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AssetDailyPerformanceMapper {
    int countByExample(AssetDailyPerformanceExample example);

    int deleteByExample(AssetDailyPerformanceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AssetDailyPerformance record);

    int insertSelective(AssetDailyPerformance record);

    List<AssetDailyPerformance> selectByExample(AssetDailyPerformanceExample example);

    AssetDailyPerformance selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AssetDailyPerformance record, @Param("example") AssetDailyPerformanceExample example);

    int updateByExample(@Param("record") AssetDailyPerformance record, @Param("example") AssetDailyPerformanceExample example);

    int updateByPrimaryKeySelective(AssetDailyPerformance record);

    int updateByPrimaryKey(AssetDailyPerformance record);

	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	List<Map<String, Object>> findAllByPageCount(Map<String, Object> paramsCondition);

	List<?> getYears();

	List<?> getMonths(String years);

	List<Map<String, Object>> getDays(String months);
	
	/**
	 * 查询某日的业绩条数
	 */
	long countByDate(String date);
	
	/**
	 * 查询某天的重复记录数
	 */
	long countRepetition(String date);
}