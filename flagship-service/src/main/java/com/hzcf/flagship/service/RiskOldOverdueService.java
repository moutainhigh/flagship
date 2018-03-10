package com.hzcf.flagship.service;

import java.util.List;
import java.util.Map;

import com.hzcf.flagship.util.PageModel;


/**
*<dl>
*<dt>类名：RiskOldOverdueService.java</dt>
*<dd>描述: 老系统逾期表</dd>
*<dd>创建时间： 2017年10月16日 下午5:50:51</dd>
*<dd>创建人：Tieguowei</dd>
*<dt>版本历史: </dt>
* <pre>
* Date Author Version Description
* ------------------------------------------------------------------
* 2017年10月16日 下午5:50:51 Tieguowei 1.0 1.0 Version
* </pre>
*</dl>
*/
public interface RiskOldOverdueService {
	
	/**
	 * Description:老系统逾期表数据导入
	 * @return 
	 * 
	 */
	int insertOldOverdue(List<List<Object>> list, Integer id);

	PageModel findAllByPage(Map<String, Object> paramsCondition);
}
