package com.hzcf.flagship.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.hzcf.flagship.util.PageModel;

/**
 * 
 *<dl>
 *<dt>类名：CheckDataService.java</dt>
 *<dd>描述: ~数据校验Service类</dd> 
 *<dd>创建时间： 2017年9月15日 上午10:21:19</dd>
 *<dd>创建人： GuoDong</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年9月15日 上午10:21:19    GuoDong       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface CheckDataService {
	/**
	 * 分页查询
	 * @param condition
	 * @return
	 */
	public PageModel findAllByPage(Map<String, Object> condition);
	
	/**
	 * 获取访问类型
	 * @return
	 */
	public List<?> getTypes();
	
	/**
	 * 获取检查结果类型
	 * @return
	 */
	public List<?> getResult();
	
	/**
	 * 获取检查结果次数
	 * @return
	 */
	public List<?> getSort(Map<String, Object> paramsCondition);
	
	/**
	 * 导出Excel
	 * @param paramsCondition
	 * @param outputStream 
	 */
	public void exportExcel(Map<String, Object> paramsCondition, HttpServletResponse response);

}
