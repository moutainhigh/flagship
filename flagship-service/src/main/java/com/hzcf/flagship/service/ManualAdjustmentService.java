package com.hzcf.flagship.service;

import java.util.List;
import java.util.Map;

import com.hzcf.flagship.util.PageModel;


/**
*<dl>
*<dt>类名：ManualAdjustmentService.java</dt>
*<dd>描述:手工调整表逻辑实现</dd>
*<dd>创建时间： 2017年10月17日 下午3:49:16</dd>
*<dd>创建人：Tieguowei</dd>
*<dt>版本历史: </dt>
* <pre>
* Date Author Version Description
* ------------------------------------------------------------------
* 2017年10月17日 下午3:49:16 Tieguowei 1.0 1.0 Version
* </pre>
*</dl>
*/
public interface ManualAdjustmentService {

	int insertManualAdjustment(List<List<Object>> list, Integer id);

	PageModel findAllByPage(Map<String, Object> paramsCondition);

}
