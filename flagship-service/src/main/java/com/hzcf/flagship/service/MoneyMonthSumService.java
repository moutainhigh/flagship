package com.hzcf.flagship.service;

import java.util.List;
import java.util.Map;

import com.hzcf.flagship.util.PageModel;

/**
 *<dl>
 *<dt>类名：MonthSumService.java</dt>
 *<dd>描述: 理财月度累计表</dd> 
 *<dd>创建时间： 2017年5月25日 上午11:47:02</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月25日 上午11:47:02    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface MoneyMonthSumService {

	/**
	 * 理财月度累计表分页查询
	 */
	public PageModel findAllByPage(Map<String, Object> paramsCondition);
	/**
	 * 理财月度累计表上传 数据导入
	 * @return 
	 * @throws Exception 
	 */

	public int insertMoneymgrMonthHistory(List<List<Object>> list, Integer id) throws Exception;

}
