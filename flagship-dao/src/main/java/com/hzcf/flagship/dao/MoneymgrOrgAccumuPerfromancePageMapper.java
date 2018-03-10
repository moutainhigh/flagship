package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.MoneymgrOrgAccumuPerfromancePage;
import com.hzcf.flagship.model.MoneymgrOrgAccumuPerfromancePageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MoneymgrOrgAccumuPerfromancePageMapper {
	int countByExample(MoneymgrOrgAccumuPerfromancePageExample example);

    int deleteByExample(MoneymgrOrgAccumuPerfromancePageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MoneymgrOrgAccumuPerfromancePage record);

    int insertSelective(MoneymgrOrgAccumuPerfromancePage record);

    List<MoneymgrOrgAccumuPerfromancePage> selectByExample(MoneymgrOrgAccumuPerfromancePageExample example);

    MoneymgrOrgAccumuPerfromancePage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MoneymgrOrgAccumuPerfromancePage record, @Param("example") MoneymgrOrgAccumuPerfromancePageExample example);

    int updateByExample(@Param("record") MoneymgrOrgAccumuPerfromancePage record, @Param("example") MoneymgrOrgAccumuPerfromancePageExample example);

    int updateByPrimaryKeySelective(MoneymgrOrgAccumuPerfromancePage record);

    int updateByPrimaryKey(MoneymgrOrgAccumuPerfromancePage record);
    
    /**
     * 批量插入指定日期的所有机构结果数据(地图)
     * @param pages
     */
    void insertAccumuPerformancePages(List<MoneymgrOrgAccumuPerfromancePage> pages);
    /**
     * 理财H5接口 各机构当月累计业绩(地图)
     * @param parm
     */
    List<MoneymgrOrgAccumuPerfromancePage> findPerformanceOrgMapByDays(Map<String, Object> parm);

    /**
     * 理财H5接口 各机构当月累计达成率(地图)
     * @param parmMap
     */
    List<Map<String, Object>> findAreaPerformanceByOrgName(Map<String, Object> parmMap);

    /**
     * 理财H5接口 模糊查询机构名称(地图)
     * @param parmMap
     * @return
     */
	List findLikeOrgName(Map<String, Object> parmMap);

}