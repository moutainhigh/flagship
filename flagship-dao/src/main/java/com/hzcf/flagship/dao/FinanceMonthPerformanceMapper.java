package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.FinanceMonthPerformance;
import com.hzcf.flagship.model.FinanceMonthPerformanceExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface FinanceMonthPerformanceMapper {
    int countByExample(FinanceMonthPerformanceExample example);

    int deleteByExample(FinanceMonthPerformanceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FinanceMonthPerformance record);

    int insertSelective(FinanceMonthPerformance record);

    List<FinanceMonthPerformance> selectByExample(FinanceMonthPerformanceExample example);

    FinanceMonthPerformance selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FinanceMonthPerformance record, @Param("example") FinanceMonthPerformanceExample example);

    int updateByExample(@Param("record") FinanceMonthPerformance record, @Param("example") FinanceMonthPerformanceExample example);

    int updateByPrimaryKeySelective(FinanceMonthPerformance record);

    int updateByPrimaryKey(FinanceMonthPerformance record);

	List<FinanceMonthPerformance> getFinceMonthLastOne();

	FinanceMonthPerformance getLastMonthInfo(String firstDayOfLastMonth);
	
	/**
	 * 获得begin,end范围内的业绩数据
	 * @param begin
	 * @param end
	 * @return
	 */
	List<Map<String, Object>> getPerformanceBetweenDate(Map<String, Date> params);
	/**
	 * 查找批核率(月)
	 * @return
	 */
	List<Map<String, Object>> selectApprovalRate();
	/**
	 * 批核件均 (月)
	 * @return
	 */
	List<Map<String, Object>> selectApprovalAvg();
	/**
	 * 获取当年数据
	 * @param requestMap
	 * @return
	 */
	List<FinanceMonthPerformance> getPlanListByYear();
}