package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.MoneymgrTotalMonthPlan;
import com.hzcf.flagship.model.MoneymgrTotalMonthPlanExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MoneymgrTotalMonthPlanMapper {
    int countByExample(MoneymgrTotalMonthPlanExample example);

    int deleteByExample(MoneymgrTotalMonthPlanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MoneymgrTotalMonthPlan record);

    int insertSelective(MoneymgrTotalMonthPlan record);

    List<MoneymgrTotalMonthPlan> selectByExample(MoneymgrTotalMonthPlanExample example);

    MoneymgrTotalMonthPlan selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MoneymgrTotalMonthPlan record, @Param("example") MoneymgrTotalMonthPlanExample example);

    int updateByExample(@Param("record") MoneymgrTotalMonthPlan record, @Param("example") MoneymgrTotalMonthPlanExample example);

    int updateByPrimaryKeySelective(MoneymgrTotalMonthPlan record);

    int updateByPrimaryKey(MoneymgrTotalMonthPlan record);

	void deleteMonthPlanByFirstDayAndLastDay(Map<String, Object> monthPlanMap);
}