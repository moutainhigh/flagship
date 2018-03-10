package com.hzcf.flagship.service;

import java.util.List;
import java.util.Map;

import com.hzcf.flagship.model.MoneymgrOrgData;
import com.hzcf.flagship.util.PageModel;

/**
 *<dl>
 *<dt>类名：MonthCompareService.java</dt>
 *<dd>描述: 理财对比表</dd> 
 *<dd>创建时间： 2017年5月25日 上午11:46:48</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月25日 上午11:46:48    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface MoneyCompareService {

	/**
	 * 理财对比表分页查询
	 * 
	 */
	public PageModel findAllByPage(Map<String, Object> paramsCondition);
	
	/**
	 * 理财对比表数据批量插入
	 * 
	 */
	public void insertMoneyCompare(List<MoneymgrOrgData> resultList);


}
