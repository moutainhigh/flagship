package com.hzcf.flagship.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzcf.flagship.model.FinancePersonalInfo;
import com.hzcf.flagship.model.FinancePersonalInfoExample;

public interface FinancePersonalInfoMapper {
	int countByExample(FinancePersonalInfoExample example);

    int deleteByExample(FinancePersonalInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FinancePersonalInfo record);

    int insertSelective(FinancePersonalInfo record);

    List<FinancePersonalInfo> selectByExample(FinancePersonalInfoExample example);

    FinancePersonalInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FinancePersonalInfo record, @Param("example") FinancePersonalInfoExample example);

    int updateByExample(@Param("record") FinancePersonalInfo record, @Param("example") FinancePersonalInfoExample example);

    int updateByPrimaryKeySelective(FinancePersonalInfo record);

    int updateByPrimaryKey(FinancePersonalInfo record);
    /**
   	 * Description:融资人员信息表数据导入
   	 *
   	*/
	void insertFinancePeopleInfo(List<?> list2);
	/**
	 * 融资人员信息相同时间的数据整体替换
	 * @param financeRiskMap
	 */
	void deleteFinancePeopleInfoByFirstDayAndLastDay(Map<String, Object> financeRiskMap);

	Long findAllByPageCount(Map<String, Object> paramsCondition);
	/**
	 * 
	 * Description: 融资人员信息表分页查询列表
	 */
	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	/**
	 * 查询月计划放款金额
	 * @param monthFirstDayAndLastDay
	 * @return
	 */
	int selectTotalMonthPlanByDate(Map<String, Object> monthFirstDayAndLastDay);

	/**
	 * 查询月计划汇总
	 * @param requestMap
	 * @return
	 */
	FinancePersonalInfo getMonthPlan(Map<String, Object> map);


	Map<String, Object> findCountByDays(Map<String, Object> requestMap);

	/**
	 * 月人均进件
	 * @return
	 */
	List<Map<String, Object>> SelectAvgMonthPerEntry();
	/**
	 * 全员人均产能
	 * @return
	 */
	List<Map<String, Object>> selectAllAvgPer();
	/**
	 * 咨询师人均产能
	 * @return
	 */
	List<Map<String, Object>> selectSalesAvgPer();

	List<Map<String, Object>> financePersonnelSum(Map<String, Object> requestMap);

	List<FinancePersonalInfo> findTheLatestOne();
	/**
	 * 查询年计划汇总
	 * @param requestMap
	 * @return
	 */
	FinancePersonalInfo getYearPlan(Map<String, Object> requestMap);
	
	/**
	 * 根据日期查询记录数count(*)与大区/营业部count(DISTINCT manage_office,org_name)
	 * 记录的差值(用于判断数据是否重复),以及标记为整体的数据和大区个数的差值(用于判断是否有漏掉大区整体的情况)
	 */
	Map<String, Object> getDiffOfCountAndManageOfficeOrgNumByDate(Date date);
	
}