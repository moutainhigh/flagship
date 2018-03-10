package com.hzcf.flagship.service;

import java.util.List;
import java.util.Map;

import com.hzcf.flagship.util.PageModel;

/**
 *<dl>
 *<dt>类名：FinancePeopleInfoService.java</dt>
 *<dd>描述: 融资人员信息表</dd> 
 *<dd>创建时间： 2017年5月25日 上午11:45:32</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月25日 上午11:45:32    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface FinancePeopleInfoService {
	/**
	 * Description:融资人员信息表分页查询列表
	 * 
	 */
	PageModel findAllByPage(Map<String, Object> paramsCondition);
	/**
	 * Description:融资人员信息表数据导入
	 * 
	 */
	int insertFinancePeopleInfo(List<List<Object>> list, Integer id);

}
