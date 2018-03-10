package com.hzcf.flagship.dao;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzcf.flagship.form.PerformanceParm;
import com.hzcf.flagship.model.RiskLoanCm1;
import com.hzcf.flagship.model.RiskLoanCm1Example;

public interface RiskLoanCm1Mapper {
	int countByExample(RiskLoanCm1Example example);

    int deleteByExample(RiskLoanCm1Example example);

    int deleteByPrimaryKey(Long id);

    int insert(RiskLoanCm1 record);

    int insertSelective(RiskLoanCm1 record);

    List<RiskLoanCm1> selectByExample(RiskLoanCm1Example example);

    RiskLoanCm1 selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RiskLoanCm1 record, @Param("example") RiskLoanCm1Example example);

    int updateByExample(@Param("record") RiskLoanCm1 record, @Param("example") RiskLoanCm1Example example);

    int updateByPrimaryKeySelective(RiskLoanCm1 record);

    int updateByPrimaryKey(RiskLoanCm1 record);

	List<Map<String, Object>> firstPageListWithKH(PerformanceParm performanceParm);

	List<Map<String, Object>> firstPageListWithSum(PerformanceParm performanceParm);

	/**
	 *  市场管理部cm1回款率详细数据
	 * @param map
	 * @return
	 * @author guodong
	 */
	List<LinkedHashMap<String, Object>> selectCM1BackRate(Map<String, Object> map);
	
	/**
	 * 查询CM1回款率表中某日记录数
	 * 各产品C-M1回款率
	 */
	Map<String, Object> getDetailRecordsByDate(String date);
	
	/**
	 * 查询重复记录数
	 * @param date
	 * @return
	 */
	Map<String, Object> getRepetitionNumByDate(String date);

	List<LinkedHashMap<String, Object>> selectAreaCM1ByOrgNos(Map<String, Object> map);

	LinkedHashMap<String, Object> getRoundData(PerformanceParm performanceParm);

	List<Map<String, Object>> findRecordList(PerformanceParm performanceParm);

	Map<String, Object> findMonthData(Map<String, Object> map);
	
	/**
	 * 各批次C-M1回款率
	 * @param map
	 * @return
	 * @author guodong
	 */
	List<LinkedHashMap<String, Object>> selectbusinessCM1BatchByOrgNos(Map<String, Object> map);
	/**
	 * 查询大区的累计C-M1回款率
	 * @param map
	 * @return
	 * @author guodong
	 */
	List<LinkedHashMap<String, Object>> selectAreaCM1BackRate(Map<String, Object> map);
	/**
	 * 查询大区下各产品累计C-M1回款率
	 * @param map
	 * @return
	 * @author guodong
	 */
	List<LinkedHashMap<String, Object>> selectAreaCM1ByProductName(Map<String, Object> map);
	/**
	 * 风控--贷后整体下钻接口
	 * 分产品按批次查询
	 * @param map
	 * @return
	 * @author guodong
	 */
	List<LinkedHashMap<String, Object>> selectAfterLoanAll(Map<String, Object> map);

	Map<String, Object> getRiskAfterRoundData(PerformanceParm performanceParm);

	List<LinkedHashMap<String, Object>> getAfterCm1List(PerformanceParm performanceParm);
	/**
	 * 按日期 month orgNo 查询C-M1回款率
	 * @param map
	 */
	BigDecimal findCM1ByRecordDateAndOrgNo(Map<String, Object> requestMap);
	/**
	 * 
	* @Description:查询营业部排名 
	* @param @param requestMap
	* @throws
	 */
	String findOrgNoRanking(Map<String, Object> requestMap);
	/**
	 * Description: 查找月度C-M1
	 * @param paramsCondition
	 * @return
	 * @author guodong
	 */
	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);
	/**
	 * Description: 查找月度C-M1总数
	 * @param paramsCondition
	 * @return
	 * @author guodong
	 */
	Long findAllByPageCount(Map<String, Object> paramsCondition);

	/**
	 * Description: 获取年份 
	 * @return
	 * @author guodong
	 */
	List<?> getYears();

	/**
	 * Description: 根据年获取月份
	 * @return
	 * @author guodong
	 * @param years 
	 */
	List<?> getMonths(String years);

	/**
	 * Description: 获取事业部名称
	 * @return
	 * @author guodong
	 */
	List<?> getBusinessName();
	/**
	 * 
	 * Description: 单日C-M1查看
	 * @return PageModel
	 */
	List<Map<String, Object>> findEveryDayCm1View(Map<String, Object> paramsCondition);
	/**
	 * 
	 * Description: 单日C-M1查看 总数
	 * @return PageModel
	 */
	List<Map<String, Object>> findEveryDayCm1ViewCount(Map<String, Object> paramsCondition);
	/**
	 * Description: 查找风控分中心首页累计C-M1回款率
	 * @param map
	 * @return
	 * @author guodong
	 */
	List<Map<String, Object>> selectbranchCenterTotalRate(Map<String, Object> map);
	/**
	 * Description: 查找分中心各产品累计C-M1回款率 
	 * @param map
	 * @return
	 * @author guodong
	 */
	List<LinkedHashMap<String, Object>> selectbranchCenterCM1ByProductName(Map<String, Object> map);
	/**
	 * Description: 查找分中心C-M1回款率目标值 
	 * @param map
	 * @return
	 * @author guodong
	 */
	List<Map<String, Object>> selectbranchCenterPlanRate(Map<String, Object> map);

	/**
	 * 将所有精英类(1.69,1.89等)产品聚合成精英类(product_no=991)
	 */
	void updateEliteSum(String date);
	
	/**
	 * 将所有公积金类(2.39)产品聚合成公积金类(product_no=11)
	 */
	void updateProvidentFundSum(String date);
	
	/**
	 * 查询执委首页事业部各批次C-M1
	 * @param map
	 * @return
	 */
	List<LinkedHashMap<String, Object>> getZwBatchCm1(Map<String, Object> map);
	/**
	 * 查询执委首页（月）折线图数据
	 * @param map
	 * @return
	 */
	LinkedHashMap<String, Object> getMonthChartListData(PerformanceParm performanceParm);
	/**
	 * 查询执委首页（月）表格数据
	 * @param map
	 * @return
	 */
	LinkedHashMap<String, Object> getZWMonthTableListData(PerformanceParm performanceParm);
	/**
	 * 查询执委首页（月）查询数据开始时间
	 * @param map
	 * @return
	 */
	String getRiskMonthStartDate();
	/**
	 * 查询风控（月）回款率列表
	 * @param map
	 * @return
	 */
	List<LinkedHashMap<String, Object>> getTableListCm1(Map<String, Object> requestMap);
	/**
	 * 查询风控市场风险管理部（月）首页 分中心回款率列表
	 * @param map
	 * @return
	 */
	List<LinkedHashMap<String, Object>> getSubcenterTableListCm1(Map<String, Object> requestMap);
	
	/**
	 * 查询各组织机构日cm1列表
	 */
	List<LinkedHashMap<String, Object>> getCm1DailyList(Map<String, Object> map);

	/**
	 * 查询各组织机构日cm1记录条数
	 */
	long getCm1Count(Map<String, Object> map);
	
	/**
	 * 查询f_risk_loan_cm1表中所有的月份(即日期为26日)
	 * @return
	 */
	List<Map<String, Object>> getMonthList();
	
	/**
	 * 查询cm1月数据列表
	 * @param map
	 * @return
	 */
	List<LinkedHashMap<String, Object>> getCm1MonthList(Map<String, Object> map);
}