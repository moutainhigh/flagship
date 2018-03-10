package com.hzcf.flagship.dao;

import com.hzcf.flagship.form.PerformanceParm;
import com.hzcf.flagship.model.RiskOverdue;
import com.hzcf.flagship.model.RiskOverdueExample;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RiskOverdueMapper {
	int countByExample(RiskOverdueExample example);

    int deleteByExample(RiskOverdueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RiskOverdue record);

    int insertSelective(RiskOverdue record);

    List<RiskOverdue> selectByExample(RiskOverdueExample example);

    RiskOverdue selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RiskOverdue record, @Param("example") RiskOverdueExample example);

    int updateByExample(@Param("record") RiskOverdue record, @Param("example") RiskOverdueExample example);

    int updateByPrimaryKeySelective(RiskOverdue record);

    int updateByPrimaryKey(RiskOverdue record);
	/**
	 * 
	* @Description: 查找风控经理明细数据
	* @param @param map
	* @param @return    
	* @return List<Map<String,Object>>
	* @throws
	 */
	List<Map<String, Object>> findDetailDataByOrgNoAndDate(Map<String, Object> map);
	
	/**
	 * 查询营业部某款产品的逾期明细
	 * @param map
	 * @return List<Map<String, Object
	 */
	List<Map<String, Object>> findOrgOverdueDetail(Map<String, Object> map);
	
	/**
	 * 查询具体日期下的记录条数和重复条数
	 * @param date
	 * @return
	 */
	Map<String, Object> getCountOfTotalAndDiff(String date);
	
	/**
	 * 将所有精英类(1.69,1.89等)产品聚合成精英类(product_no=991)
	 * @param date
	 */
	void updateEliteSum(String date);
	
	/**
	 * 将所有公积金类(2.39)产品聚合成公积金类(product_no=11)
	 */
	void updateProvidentFundSum(String date);
	/**
	 * 查询所有营业部编号
	* @Description: 
	* @param @return    
	* @return List<Map<String,Object>>
	* @throws
	 */
	List<Map<String,Object>> findOverdueOrgNOByRecordDate(Map<String, Object> map);
	
	/**
	 * 获得某营业部产品C-M1逾期列表
	 * @param performanceParm
	 * @return
	 */
	List<Map<String, Object>> getOrgCM1OverdueList(PerformanceParm performanceParm);
	/**
	 * 营业部C-M1逾期合同明细
	* @Description: 
	* @param @return    
	* @return List<Map<String,Object>>
	* @throws
	 */
	List<LinkedHashMap<String, Object>> orgCM1OverdueDetail(Map<String, Object> map);
}