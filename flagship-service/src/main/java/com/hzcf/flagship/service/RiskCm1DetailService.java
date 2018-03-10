package com.hzcf.flagship.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.hzcf.flagship.util.PageModel;

public interface RiskCm1DetailService {

	/**
	 * 
	* @Description: 查询风控基础数据CM1(事业部 大区 营业部 批次)下拉框内容
	* @param @param map
	* @param @return    
	* @return List<?>
	* @throws
	 */
	List<?> getCm1ComboBoxName(Map<String, Object> map);

	/**
	 * 
	* @Description: 根据用户id查询权限
	* @param @param employeeId
	* @param @return    
	* @return Map<String,Object>
	* @throws
	 */
	Map<String, Object> findPermissionByEmpId(Integer employeeId);
	/**
	 * 
	* @Description: 查找最新的贷后分割点
	* @param @param employeeId
	* @param @return    
	* @return Map<String,Object>
	* @throws
	 */
	Map<String, Object> findSeparate(String yesterday);
	/**
	 * @param response 
	 * 
	* @Description:查询日基础数据列表 
	* @param @param paramsCondition
	* @param @return    
	* @return PageModel
	* @throws
	 */
	PageModel getCm1OverDetailList(Map<String, Object> paramsCondition);

	/**
	 * 风控中心-基础数据-月数据页面-初始化月份
	 */
	List<?> getCm1MonthTime();
	/**
	 * 风控中心-基础数据-导出
	 */
	void exportRiskBasicDataExcel(Map<String, Object> paramsCondition, HttpServletResponse response);

}
