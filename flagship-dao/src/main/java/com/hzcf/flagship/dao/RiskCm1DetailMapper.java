package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.RiskCm1Detail;
import com.hzcf.flagship.model.RiskCm1DetailExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RiskCm1DetailMapper {
    int countByExample(RiskCm1DetailExample example);

    int deleteByExample(RiskCm1DetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RiskCm1Detail record);

    int insertSelective(RiskCm1Detail record);

    List<RiskCm1Detail> selectByExample(RiskCm1DetailExample example);

    RiskCm1Detail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RiskCm1Detail record, @Param("example") RiskCm1DetailExample example);

    int updateByExample(@Param("record") RiskCm1Detail record, @Param("example") RiskCm1DetailExample example);

    int updateByPrimaryKeySelective(RiskCm1Detail record);

    int updateByPrimaryKey(RiskCm1Detail record);
    /**
	 * 
	* @Description: 查询风控基础数据CM1(营业部  批次)下拉框内容
	* @param @param map
	* @param @return    
	* @return List<?>
	* @throws
	 */
	List<?> getOrgNameOrBatch(Map<String, Object> map);

	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	Long findAllByPageCount(Map<String, Object> paramsCondition);
	/**
	 * 
	* @Description: 查询风控基础数据CM1(事业部 大区)下拉框内容
	* @param @param map
	* @param @return    
	* @return List<?>
	* @throws
	 */
	List<?> getbusinessOrDistrictName(Map<String, Object> map);
	/**
	 * 风控中心-基础数据-月数据页面-初始化月份
	 */
	List<?> getCm1MonthTime();
}