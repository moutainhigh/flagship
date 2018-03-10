package com.hzcf.flagship.service;

import java.util.List;
import java.util.Map;

import com.hzcf.flagship.util.PageModel;

/**
 *<dl>
 *<dt>类名：OrgMapService.java</dt>
 *<dd>描述:理财机构名称对应表</dd> 
 *<dd>创建时间： 2017年5月25日 上午11:47:14</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月25日 上午11:47:14    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface MoneyOrgMapService {

	/**
	 * 理财机构名称对应表分页查询
	 */
	public PageModel findAllByPage(Map<String, Object> paramsCondition);
	/**
	 * 理财机构名称对应表上传 数据导入
	 * @return 
	 */
	public int insertOrgMap(List<List<Object>> list, Integer id);

}
