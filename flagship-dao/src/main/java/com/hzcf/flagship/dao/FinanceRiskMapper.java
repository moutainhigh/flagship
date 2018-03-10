package com.hzcf.flagship.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzcf.flagship.model.FinanceRisk;
import com.hzcf.flagship.model.FinanceRiskExample;

public interface FinanceRiskMapper {
    int countByExample(FinanceRiskExample example);

    int deleteByExample(FinanceRiskExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FinanceRisk record);

    int insertSelective(FinanceRisk record);

    List<FinanceRisk> selectByExample(FinanceRiskExample example);

    FinanceRisk selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FinanceRisk record, @Param("example") FinanceRiskExample example);

    int updateByExample(@Param("record") FinanceRisk record, @Param("example") FinanceRiskExample example);

    int updateByPrimaryKeySelective(FinanceRisk record);

    int updateByPrimaryKey(FinanceRisk record);
	/**
	 * 
	 * Description: 融资风险表分页查询列表
	 */
	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	Long findAllByPageCount(Map<String, Object> paramsCondition);
	 /**
	 * Description:融资风险表数据导入
	 *
	*/
	void insertFinanceRisk(List<?> list2);
	
	/**
	 * 查询今年CM1回款率列表
	 * @return
	 */
	List<Map<String, Object>> findCM1ListOfThisYear();
	
	/**
	 * 查询今年新增损失率列表
	 * @return
	 */
	List<Map<String, Object>> findLossListOfThisYear();

	/**
	 * 根据时间删除融资风险表
	 * @param financeRiskMap
	 */
	void deleteFinanceRiskByFirstDayAndLastDay(Map<String, Object> financeRiskMap);
}