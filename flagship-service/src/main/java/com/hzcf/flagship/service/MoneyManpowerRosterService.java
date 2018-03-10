package com.hzcf.flagship.service;

import java.util.List;
import java.util.Map;

import com.hzcf.flagship.util.PageModel;

/**
 *<dl>
 *<dt>类名：ManpowerRosterService.java</dt>
 *<dd>描述: 理财人力花名册</dd> 
 *<dd>创建时间： 2017年5月25日 上午11:46:21</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月25日 上午11:46:21    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface MoneyManpowerRosterService {
	
	/**
	 * 理财人力花名册 数据导入
	 * @return 
	 * @throws Exception 
	 */
	int insertMoneymgrRoster(List<List<Object>> list, Integer id) throws Exception;
	/**
	 * 
	 * Description: 理财花名册分页查询列表
	 */
	PageModel findAllByPage(Map<String, Object> paramsCondition);
	

}
