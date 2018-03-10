package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.RiskPlan;
import com.hzcf.flagship.model.RiskPlanExample;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RiskPlanMapper {
    int countByExample(RiskPlanExample example);

    int deleteByExample(RiskPlanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RiskPlan record);

    int insertSelective(RiskPlan record);

    List<RiskPlan> selectByExample(RiskPlanExample example);

    RiskPlan selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RiskPlan record, @Param("example") RiskPlanExample example);

    int updateByExample(@Param("record") RiskPlan record, @Param("example") RiskPlanExample example);

    int updateByPrimaryKeySelective(RiskPlan record);

    int updateByPrimaryKey(RiskPlan record);
    /**
     * 风控目标列表
     * @param paramsCondition
     * @return
     */
	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);
	/**
	 * 风控目标列表分页总数
	 * @param paramsCondition
	 * @return
	 */
	Long findAllByPageCount(Map<String, Object> paramsCondition);
	/**
	 * 批量导入风控目标数据
	 * @param list2
	 */
	void insertRiskPlan(List<?> list2);
	/**
	 * 查找下一个月的目标时间
	 * @return
	 */
	Date selectNextRecord();
	/**
	 * 按月查找风控目标列表
	 * @param date
	 * @return
	 */
	List<Map<String, Object>> findOrgListByDate(String date);
	/**
	 * 按时间删除风控目标表
	 * @param date
	 */
	void deleteByRecordDate(String date);
	/**
	 * 按month orgNo查询目标值
	 */
	RiskPlan findPlanByMonthAndOrgNo(Map<String, Object> map);
}