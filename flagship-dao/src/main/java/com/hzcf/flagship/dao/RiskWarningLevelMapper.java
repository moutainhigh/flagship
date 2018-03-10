package com.hzcf.flagship.dao;

import com.hzcf.flagship.form.PerformanceParm;
import com.hzcf.flagship.model.RiskWarningLevel;
import com.hzcf.flagship.model.RiskWarningLevelExample;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RiskWarningLevelMapper {
    int countByExample(RiskWarningLevelExample example);

    int deleteByExample(RiskWarningLevelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RiskWarningLevel record);

    int insertSelective(RiskWarningLevel record);

    List<RiskWarningLevel> selectByExample(RiskWarningLevelExample example);

    RiskWarningLevel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RiskWarningLevel record, @Param("example") RiskWarningLevelExample example);

    int updateByExample(@Param("record") RiskWarningLevel record, @Param("example") RiskWarningLevelExample example);

    int updateByPrimaryKeySelective(RiskWarningLevel record);

    int updateByPrimaryKey(RiskWarningLevel record);

    /**
     * 预警营业部数量
     * @param orgMap
     * @return
     * @author guodong
     */
	List<LinkedHashMap<String, Object>> selectWarningLeve(Map<String, Object> orgMap);
    
    Map<String, Object> getWarningLevelByOrgAndProduct(Map<String, Object> params);

	List<Map<String, Object>> riskRegionToOrgPage(PerformanceParm performanceParm);
	
	void deleteByDate(String date);
	
	/**
	 * 查询具体事业部下预警营业部明细列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getWarningOrgsDetail(Map<String, Object> params);
	
	/**
	 * 查询一个存在预警的营业部
	 * @param date
	 * @return
	 */
	Map<String, Object> getOneWarningOrg(String date);
}