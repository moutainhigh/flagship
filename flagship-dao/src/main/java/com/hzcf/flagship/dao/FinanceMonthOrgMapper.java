package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.FinanceMonthOrg;
import com.hzcf.flagship.model.FinanceMonthOrgExample;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface FinanceMonthOrgMapper {
    int countByExample(FinanceMonthOrgExample example);

    int deleteByExample(FinanceMonthOrgExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FinanceMonthOrg record);

    int insertSelective(FinanceMonthOrg record);

    List<FinanceMonthOrg> selectByExample(FinanceMonthOrgExample example);

    FinanceMonthOrg selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FinanceMonthOrg record, @Param("example") FinanceMonthOrgExample example);

    int updateByExample(@Param("record") FinanceMonthOrg record, @Param("example") FinanceMonthOrgExample example);

    int updateByPrimaryKeySelective(FinanceMonthOrg record);

    int updateByPrimaryKey(FinanceMonthOrg record);

	Map<String, Object> financeOrgNameOfMonthList(Map<String, Object> requestMap);

	List<FinanceMonthOrg> findMonthOrgTheLatestOne();

	List<Map<String, Object>> findAllOrgNameByDistrictOfMonth(Map<String, Object> map);

	List<Map<String, Object>> getOrgIndexByAreaAndIndexName(Map<String, Object> map1);

	/**
	 * 根据日期查询记录数(count *)和区域数(count DISTINCT org_name)之间的差值
	 * @param recordDate
	 * @return
	 */
	Map<String, Object> getDiffOfCountAndOrgNumByDate(Date date);
}