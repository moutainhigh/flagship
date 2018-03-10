package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.MoneymgrDistrictAccumuPerformancePage;
import com.hzcf.flagship.model.MoneymgrDistrictAccumuPerformancePageExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MoneymgrDistrictAccumuPerformancePageMapper {
    int countByExample(MoneymgrDistrictAccumuPerformancePageExample example);

    int deleteByExample(MoneymgrDistrictAccumuPerformancePageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MoneymgrDistrictAccumuPerformancePage record);

    int insertSelective(MoneymgrDistrictAccumuPerformancePage record);

    List<MoneymgrDistrictAccumuPerformancePage> selectByExample(MoneymgrDistrictAccumuPerformancePageExample example);

    MoneymgrDistrictAccumuPerformancePage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MoneymgrDistrictAccumuPerformancePage record, @Param("example") MoneymgrDistrictAccumuPerformancePageExample example);

    int updateByExample(@Param("record") MoneymgrDistrictAccumuPerformancePage record, @Param("example") MoneymgrDistrictAccumuPerformancePageExample example);

    int updateByPrimaryKeySelective(MoneymgrDistrictAccumuPerformancePage record);

    int updateByPrimaryKey(MoneymgrDistrictAccumuPerformancePage record);
    /**
     * 查询区域列表
     * @param days
     * @return
     */
	List<Map<String, Object>> selectMoneymgrEfficiencyForMonthByDay(String days);
	
	/**
	 * 批量插入分区域结果数据
	 * @param pages
	 */
	void insertDistrictAccumuPerformancePages(List<MoneymgrDistrictAccumuPerformancePage> pages);

}