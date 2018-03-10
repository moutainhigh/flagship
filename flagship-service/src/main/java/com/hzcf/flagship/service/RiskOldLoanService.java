package com.hzcf.flagship.service;

import java.util.List;
import java.util.Map;

import com.hzcf.flagship.util.PageModel;

/**
*<dl>
*<dt>类名：RiskOldLoanService.java</dt>
*<dd>描述: 老系统放款表</dd>
*<dd>创建时间： 2017年10月16日 下午9:47:29</dd>
*<dd>创建人：Tieguowei</dd>
*<dt>版本历史: </dt>
* <pre>
* Date Author Version Description
* ------------------------------------------------------------------
* 2017年10月16日 下午9:47:29 Tieguowei 1.0 1.0 Version
* </pre>
*</dl>
*/
public interface RiskOldLoanService {

	int insertOldLoan(List<List<Object>> list, Integer id);

	PageModel findAllByPage(Map<String, Object> paramsCondition);
	
}
