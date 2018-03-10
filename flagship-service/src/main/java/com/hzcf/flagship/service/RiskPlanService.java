package com.hzcf.flagship.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzcf.flagship.util.PageModel;
/**
 * 
 *<dl>
 *<dt>类名：RiskPlanService.java</dt>
 *<dd>描述: ~风控目标service</dd> 
 *<dd>创建时间： 2017年10月17日 上午11:11:23</dd>
 *<dd>创建人： GuoDong</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年10月17日 上午11:11:23    GuoDong       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface RiskPlanService {

	PageModel findAllByPage(Map<String, Object> paramsCondition);

	PageModel getRiskPlanListByRecordDate(String monthDate);
	/**
	 *  模板下载
	 * @param request 
	 * @param response
	 */
	void riskPlanDownLoad(HttpServletRequest request, HttpServletResponse response);
	/**
	 * 风控目标数据导入
	 * @param list
	 * @param id
	 * @return
	 */
	int insertRiskPlan(List<List<Object>> list, Integer id);
	/**
	 * 按月上传风控目标
	 * @param list
	 * @param id
	 * @param date
	 * @return
	 */
	int insertRiskPlanByRecordDate(List<List<Object>> list, Integer id, String date);

}
