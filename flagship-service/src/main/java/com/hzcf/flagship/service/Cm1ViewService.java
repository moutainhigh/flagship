
package com.hzcf.flagship.service;

import java.util.List;
import java.util.Map;

import com.hzcf.flagship.util.PageModel;

import javax.servlet.http.HttpServletResponse;



/**
*<dl>
*<dt>类名：Cm1ViewService.java</dt>
*<dd>描述: 战略运营部 Cm1查看</dd>
*<dd>创建时间： 2017年11月13日 下午1:46:09</dd>
*<dd>创建人：Tieguowei</dd>
*<dt>版本历史: </dt>
* <pre>
* Date Author Version Description
* ------------------------------------------------------------------
* 2017年11月13日 下午1:46:09 Tieguowei 1.0 1.0 Version
* </pre>
*</dl>
*/
public interface Cm1ViewService {

	/**
	 * 
	 * Description: 单日C-M1查看
	 * @param 
	 * @return PageModel
	 * @throws 
	 */
	public PageModel everyDayCm1View(Map<String, Object> paramsCondition);
	/**
	 * 
	 * Description: 月度C-M1查看
	 * @param paramsCondition
	 * @return
	 * @author guodong
	 */
	public PageModel findAllByPage(Map<String, Object> paramsCondition);
	/**
	 * Description: 获取年份 
	 * @return
	 * @author guodong
	 * @param request 
	 */
	public List<?> getYears(String flag);
	/**
	 * Description: 根据年获取月份
	 * @return
	 * @author guodong
	 * @param flag 
	 * @param string 
	 */
	public List<?> getMonths(String years, String flag);
	/**
	 * Description: 根据月获取日
	 * @return
	 * @author guodong
	 * @param flag 
	 * @param string 
	 */
	public List<Map<String, Object>> getDays(String months);
	/**
	 * Description: 获取事业部名称
	 * @return
	 * @author guodong
	 */
	public List<?> getBusinessName();
	/**
	 * Description: 查找历史业绩
	 * @param paramsCondition
	 * @return
	 * @author guodong
	 */
	public PageModel findHistoryPerformanceList(Map<String, Object> paramsCondition);
	/**
	 * Description: 获取事业部名称(业绩查看)
	 * @return
	 * @author tgw
	 */
	public List<?> getBusinessNameWithResultsView();
	/**
	 * 
	 * Description: 组织机构编码对应列表
	 * 
	 * @Author tgw
	 */
	public PageModel findStructMappingAllByPage(Map<String, Object> paramsCondition);
	/**
	 * 
	 * Description: 业务编码对应列表
	 * 
	 * @Author tgw
	 */
	public PageModel findBusinessMappingAllByPage(Map<String, Object> paramsCondition);

	/**
	 * 导出单日C-M1 Excel
	 * @param paramsCondition
	 * @param response
	 */
    void exportExcelEveryDayView(Map<String, Object> paramsCondition, HttpServletResponse response);

	/**
	 * 导出月度C-M1 Excel
	 * @param paramsCondition
	 * @param response
	 */
	void exportExcelMonthCM1list(Map<String, Object> paramsCondition, HttpServletResponse response);
}
