package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.RiskOrgData;
import com.hzcf.flagship.model.RiskOrgDataExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RiskOrgDataMapper {
	

	int countByExample(RiskOrgDataExample example);

    int deleteByExample(RiskOrgDataExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RiskOrgData record);

    int insertSelective(RiskOrgData record);

    List<RiskOrgData> selectByExample(RiskOrgDataExample example);

    RiskOrgData selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RiskOrgData record, @Param("example") RiskOrgDataExample example);

    int updateByExample(@Param("record") RiskOrgData record, @Param("example") RiskOrgDataExample example);

    int updateByPrimaryKeySelective(RiskOrgData record);

    int updateByPrimaryKey(RiskOrgData record);

	List<RiskOrgData> getOrgData();

	Map<String, Object> selectByOrgShortNameAndPrincipalNo(RiskOrgData orgData);

	Map<String, Object> selectShortNameByOrgNo(String orgNo);

	void updateCreateTime(Map<String, Object> map);
	/**
	 * Description: 查找分中心下的所有营业部编号
	 * @param orgNo
	 * @return
	 * @author guodong
	 */
	String getBranchCenterChildOrgNos(Map<String, Object> map);
	/**
	 * Description: 查找分中心下的所有营业部
	 * @param orgNo
	 * @return
	 * @author guodong
	 */
	List<RiskOrgData> selectOrgDataBySubcenterNo(String subcenterNo);
	/**
	 * Description:判断营业部是否分配 分中心
	 * @author tieguowei
	 * @param recordDate 
	 */
	List<Map<String, Object>> checkSubcenterIsNull(String recordDate);
	
	/**
	 * 根据分中心编号,查询分中心下所有营业部的编号列表
	 * @param subcenterNo
	 * @return
	 */
	List<String> getOrgListBySubcenter(String subcenterNo);
	
	/**
	 * <!-- 查询机构属于哪个分中心 -->
	 * @param orgNo
	 * @return
	 */
	String getSubcenterByOrg(String orgNo);
}