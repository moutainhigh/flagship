package com.hzcf.flagship.service;

import java.util.List;
import java.util.Map;

import com.hzcf.flagship.util.PageModel;

/**
 *<dl>
 *<dt>类名：PerformanceExcludeService.java</dt>
 *<dd>描述: 理财业绩排除表</dd> 
 *<dd>创建时间： 2017年5月25日 上午11:47:28</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月25日 上午11:47:28    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface MoneyPerformanceExcludeService {
	/**
	 * 理财业绩排除表上传 数据导入
	 * @return 
	 */
	int insertPerformanceExclude(List<List<Object>> list, Integer id);
	/**
	 * 理财业绩排除表分页查询
	 */
	PageModel findAllByPage(Map<String, Object> paramsCondition);
	

}
