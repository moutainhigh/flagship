package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.MoneymgrDistrictAccumuPerformance;
import com.hzcf.flagship.model.MoneymgrDistrictAccumuPerformanceExample;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MoneymgrDistrictAccumuPerformanceMapper {
	int countByExample(MoneymgrDistrictAccumuPerformanceExample example);

    int deleteByExample(MoneymgrDistrictAccumuPerformanceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MoneymgrDistrictAccumuPerformance record);

    int insertSelective(MoneymgrDistrictAccumuPerformance record);

    List<MoneymgrDistrictAccumuPerformance> selectByExample(MoneymgrDistrictAccumuPerformanceExample example);

    MoneymgrDistrictAccumuPerformance selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MoneymgrDistrictAccumuPerformance record, @Param("example") MoneymgrDistrictAccumuPerformanceExample example);

    int updateByExample(@Param("record") MoneymgrDistrictAccumuPerformance record, @Param("example") MoneymgrDistrictAccumuPerformanceExample example);

    int updateByPrimaryKeySelective(MoneymgrDistrictAccumuPerformance record);

    int updateByPrimaryKey(MoneymgrDistrictAccumuPerformance record);
    
    /**
     * 获得指定时间区域具体数据:区域名称,区域负责人,区域累计业绩,区域累计新用户数,月计划,上月咨询师人数
     * @param dateString 格式:yyyy-MM-dd
     * @return
     */
    List<Map<String, Object>> getDistrictData(String dateString);
    
    /**
	 * 根据日期查询记录数(count *)和区域数(count DISTINCT district_name)之间的差值
	 * @param recordDate
	 * @return
	 */
	Map<String, Object> getDiffOfCountAndDistrictNumByDate(Date date);
}