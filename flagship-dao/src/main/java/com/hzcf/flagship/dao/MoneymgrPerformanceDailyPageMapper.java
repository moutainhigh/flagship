package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.MoneymgrPerformanceDailyPage;
import com.hzcf.flagship.model.MoneymgrPerformanceDailyPageExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MoneymgrPerformanceDailyPageMapper {
    int countByExample(MoneymgrPerformanceDailyPageExample example);

    int deleteByExample(MoneymgrPerformanceDailyPageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MoneymgrPerformanceDailyPage record);

    int insertSelective(MoneymgrPerformanceDailyPage record);

    List<MoneymgrPerformanceDailyPage> selectByExample(MoneymgrPerformanceDailyPageExample example);

    MoneymgrPerformanceDailyPage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MoneymgrPerformanceDailyPage record, @Param("example") MoneymgrPerformanceDailyPageExample example);

    int updateByExample(@Param("record") MoneymgrPerformanceDailyPage record, @Param("example") MoneymgrPerformanceDailyPageExample example);

    int updateByPrimaryKeySelective(MoneymgrPerformanceDailyPage record);

    int updateByPrimaryKey(MoneymgrPerformanceDailyPage record);
    
    /**
     * 批量插入数据到日业绩结果页
     * @param pages
     */
    void insertDailyPerformancePages(List<MoneymgrPerformanceDailyPage> pages);
    /**
     * 查询当月累计业绩和计划达成率
     * @param dateString
     * @return
     */
	Map<String, Object> getAccumuPerformanceAndAccumuCompleteRate(String dateString);
}