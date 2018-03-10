package com.hzcf.flagship.service;

import com.hzcf.flagship.form.PerformanceParm;
import com.hzcf.flagship.vo.ReturnMsgData;

/**
 *<dl>
 *<dt>类名：FinanceRiskService.java</dt>
 *<dd>描述: 融资业绩</dd> 
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
public interface FinancePerformanceService {
	
	/**
	 * 获取融资日业绩api需要的数据
	 * @param performanceParm
	 * @return
	 */
	ReturnMsgData apiGetFinanceDailyPerformance(PerformanceParm performanceParm);
	
	/**
	 * 初始化日融资日基础数据(index值)
	 */
	void initializeIndexPageData();

	long checkDataOfTheDayisNormal(String days);
}
