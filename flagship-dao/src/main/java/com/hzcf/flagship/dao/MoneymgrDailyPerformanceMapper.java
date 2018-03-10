package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.MoneymgrDailyPerformance;
import com.hzcf.flagship.model.MoneymgrDailyPerformanceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MoneymgrDailyPerformanceMapper {
    int countByExample(MoneymgrDailyPerformanceExample example);

    int deleteByExample(MoneymgrDailyPerformanceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MoneymgrDailyPerformance record);

    int insertSelective(MoneymgrDailyPerformance record);

    List<MoneymgrDailyPerformance> selectByExample(MoneymgrDailyPerformanceExample example);

    MoneymgrDailyPerformance selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MoneymgrDailyPerformance record, @Param("example") MoneymgrDailyPerformanceExample example);

    int updateByExample(@Param("record") MoneymgrDailyPerformance record, @Param("example") MoneymgrDailyPerformanceExample example);

    int updateByPrimaryKeySelective(MoneymgrDailyPerformance record);

    int updateByPrimaryKey(MoneymgrDailyPerformance record);

    /**
     * 理财当月每日业绩数据
     * @param parm
     * @return
     */
    List<Map<String, Object>> findDayOfMonthPerformance(Map<String, Object> parm);
    
}