package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.RiskOrgStruct;
import com.hzcf.flagship.model.RiskOrgStructExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RiskOrgStructMapper {
    int countByExample(RiskOrgStructExample example);

    int deleteByExample(RiskOrgStructExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RiskOrgStruct record);

    int insertSelective(RiskOrgStruct record);

    List<RiskOrgStruct> selectByExample(RiskOrgStructExample example);

    RiskOrgStruct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RiskOrgStruct record, @Param("example") RiskOrgStructExample example);

    int updateByExample(@Param("record") RiskOrgStruct record, @Param("example") RiskOrgStructExample example);

    int updateByPrimaryKeySelective(RiskOrgStruct record);

    int updateByPrimaryKey(RiskOrgStruct record);

	List<Map<String, Object>> findOrgList();
	
	List<Map<String, Object>> findOrgStructTree();

	List<Map<String, Object>> findChildListByOrgNo(Map<String, Object> paramsCondition);

	List<Map<String, Object>> findDetailByOrgNo(Map<String, Object> paramsCondition);
	
	/**
	 * 查询所有营业部(rank为5的)
	 * @return
	 */
	List<Map<String, Object>> findAllBusinessOrg(Map<String, Object> map);
	
	/**
	 * 查询组织机构表中的新机构
	 */
	List<Map<String, Object>> findNewOrgs();
	
	/**
	 * 查询组织机构表中有改动的记录
	 * @return
	 */
	List<Map<String, Object>> findUpdateOrgs();
	
	/**
	 * 查询CM1表中有但机构表中没有的机构
	 */
	List<Map<String, Object>> findCm1NotexistOrgs();
	
	/**
	 * 查询M1逾期率表中有但机构表中没有的机构
	 */
	List<Map<String, Object>> findM1OverdueNotexistOrgs();

	/**
	 * 获取父级下的子级
	 * @param map
	 * @return
	 * @author guodong
	 */
	List<String> selectNewOrgNos(Map<String, Object> orgMap);
	/**
	 * 根据机构等级查询机构编号
	 * @param qMap
	 * @return
	 * @author guodong
	 */
	List<Map<String, Object>> selectOrgNoByRank(Map<String, Object> qMap);
	/**
	 * 查询事业部
	 * @param i
	 * @return
	 * @author guodong
	 */

	List<Map<String, Object>> selectOrgNoByRankAndDay(Map<String, Object> params);
	/**
	 * 查找最新的一个机构根据等级
	 * @param i
	 * @return
	 * @author guodong
	 */
	Map<String, Object> selectNewOrgByRank(int rank);
	
	/**
	 * 根据机构名称查找机构编号
	 * @param orgName
	 * @return
	 */
	String getOrgNoByName(Map<String, Object> params);
	
	/**
	 * 根据机构编号查询机构信息
	 */
	Map<String, Object> getOrgDataByOrgNo(String orgNo);
}