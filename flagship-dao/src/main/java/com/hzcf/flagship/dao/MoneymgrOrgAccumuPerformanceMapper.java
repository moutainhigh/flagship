package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.MoneymgrOrgAccumuPerformance;
import com.hzcf.flagship.model.MoneymgrOrgAccumuPerformanceExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MoneymgrOrgAccumuPerformanceMapper {
    int countByExample(MoneymgrOrgAccumuPerformanceExample example);

    int deleteByExample(MoneymgrOrgAccumuPerformanceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MoneymgrOrgAccumuPerformance record);

    int insertSelective(MoneymgrOrgAccumuPerformance record);

    List<MoneymgrOrgAccumuPerformance> selectByExample(MoneymgrOrgAccumuPerformanceExample example);

    MoneymgrOrgAccumuPerformance selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MoneymgrOrgAccumuPerformance record, @Param("example") MoneymgrOrgAccumuPerformanceExample example);

    int updateByExample(@Param("record") MoneymgrOrgAccumuPerformance record, @Param("example") MoneymgrOrgAccumuPerformanceExample example);

    int updateByPrimaryKeySelective(MoneymgrOrgAccumuPerformance record);

    int updateByPrimaryKey(MoneymgrOrgAccumuPerformance record);

    int countOrgNum(String dateString);
    
    /**
	 * 根据日期查询记录数(count *)和机构数(count DISTINCT org_name)之间的差值
	 * @param recordDate
	 * @return
	 */
	Map<String, Object> getDiffOfCountAndOrgNumByDate(Date date);

}