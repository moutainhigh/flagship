package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.FinanceDailyOrg;
import com.hzcf.flagship.model.FinanceDailyOrgExample;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface FinanceDailyOrgMapper {
    int countByExample(FinanceDailyOrgExample example);

    int deleteByExample(FinanceDailyOrgExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FinanceDailyOrg record);

    int insertSelective(FinanceDailyOrg record);

    List<FinanceDailyOrg> selectByExample(FinanceDailyOrgExample example);

    FinanceDailyOrg selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FinanceDailyOrg record, @Param("example") FinanceDailyOrgExample example);

    int updateByExample(@Param("record") FinanceDailyOrg record, @Param("example") FinanceDailyOrgExample example);

    int updateByPrimaryKeySelective(FinanceDailyOrg record);

    int updateByPrimaryKey(FinanceDailyOrg record);

	List<Map<String, Object>> findAllOrgNameByDistrictOfDay(Map<String, Object> map);

	Map<String, Object> financeOrgNameOfDayList(Map<String, Object> requestMap);
	
	/**
	 * 根据日期查询记录数(count *)和机构数(count DISTINCT org_name)之间的差值
	 * @param recordDate
	 * @return
	 */
	Map<String, Object> getDiffOfCountAndOrgNumByDate(Date date);
}