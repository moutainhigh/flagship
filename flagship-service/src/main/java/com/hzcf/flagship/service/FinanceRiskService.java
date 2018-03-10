package com.hzcf.flagship.service;

import java.util.List;
import java.util.Map;

import com.hzcf.flagship.util.PageModel;

/**
 *<dl>
 *<dt>类名：FinanceRiskService.java</dt>
 *<dd>描述: 融资风险表</dd> 
 *<dd>创建时间： 2017年5月25日 上午11:45:45</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月25日 上午11:45:45    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface FinanceRiskService {
	
	/**
	 * Description:融资风险表数据导入
	 * 
	 */
	int insertFinanceRisk(List<List<Object>> list, Integer id);
	/**
	 * 
	 * Description: 融资风险表分页查询列表
	 */
	PageModel findAllByPage(Map<String, Object> paramsCondition);

	Map<String, Object> apiGetFinanceRisk();
}
