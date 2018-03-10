package com.hzcf.flagship.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.hzcf.flagship.form.PerformanceParm;
import com.hzcf.flagship.model.FileMapping;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.vo.ReturnMsgData;


/**
*<dl>
*<dt>类名：RiskService.java</dt>
*<dd>描述: 风险逻辑实现</dd>
*<dd>创建时间： 2017年10月23日 下午2:34:01</dd>
*<dd>创建人：Tieguowei</dd>
*<dt>版本历史: </dt>
* <pre>
* Date Author Version Description
* ------------------------------------------------------------------
* 2017年10月23日 下午2:34:01 Tieguowei 1.0 1.0 Version
* </pre>
*</dl>
*/
public interface RiskService {

	/**
	 * 风险执委首页
	* @Description: 
	* @param @param performanceParm
	* @param @return    
	* @return ReturnMsgData
	*@author Tieguowei
	 * @throws ParseException 
	 */
	ReturnMsgData riskZWFirstPage(PerformanceParm performanceParm) throws ParseException;

	/**
	 * 风控市场管理部首页
	 * @param performanceParm
	 * @return
	 * @author guodong
	 */
	ReturnMsgData riskSCFirstPage(PerformanceParm performanceParm);

	/**
	 *  风控-市场管理部点击事业部下钻
	 * @param performanceParm
	 * @return
	 * @author guodong
	 */
	ReturnMsgData riskAreaPage(PerformanceParm performanceParm);

	/**
	 * 风控-市场管理部点击大区下钻
	 * @param performanceParm
	 * @return
	 * @author guodong
	 */
	ReturnMsgData riskBusinePage(PerformanceParm performanceParm);
	/**
	 * 风险事业部首页
	* @Description: 
	* @param @param performanceParm
	* @param @return    
	* @return ReturnMsgData
	*@author Tieguowei
	 */
	ReturnMsgData riskBusinessFirstPage(PerformanceParm performanceParm);
	/**
	 * 风控-大区首页
	 * @param performanceParm
	 * @return
	 * @author guodong
	 */
	ReturnMsgData riskAreaFirstPage(PerformanceParm performanceParm);
	/**
	 * 风险- 大区 点击营业部下钻
	* @Description: 
	* @param @param performanceParm
	* @param @return    
	* @return ReturnMsgData
	*@author Tieguowei
	 */
	ReturnMsgData riskRegionToOrgPage(PerformanceParm performanceParm);
	/**
	 * 风控--贷后整体下钻接口
	 * @param performanceParm
	 * @return
	 * @author guodong
	 */
	ReturnMsgData riskAfterLoanAll(PerformanceParm performanceParm);
	/**
	 * 风险贷后首页
	* @Description: 
	* @param @param performanceParm
	* @param @return    
	* @return ReturnMsgData
	*@author Tieguowei
	 */
	ReturnMsgData riskAfterLoanFirstPage(PerformanceParm performanceParm);
	/**
	 * Description: 风控分中心首页
	 * @param performanceParm
	 * @return
	 * @author guodong
	 */
	ReturnMsgData riskBranchCenterFirstPage(PerformanceParm performanceParm);

	/**
	 * 营业部逾期明细
	 * @param performanceParm
	 * @return
	 */
	ReturnMsgData orgOverdueDetail(PerformanceParm param);
	
	/**
	 * 获取事业部预警营业部明细
	 * @param days
	 * @param orgNo
	 * @param productNo
	 * @param warningLevel
	 * @return
	 */
	ReturnMsgData warningOrgs(PerformanceParm param);

	/**
	* @Description: 根据code查询info和地址
	* @param @param code
	* @param @return    
	* @return String
	* @throws
	 */
	FileMapping findInfoAndAddressByCode(String code);
	/**
	* @Description: 营业部C-M1逾期合同明细
	* @param @param code
	* @param @return    
	* @return String
	* @throws
	 */
	ReturnMsgData orgCM1OverdueDetail(PerformanceParm performanceParm);
	/**
	 * 风险执委首页（月）
	* @Description: 
	* @param @param performanceParm
	* @param @return    
	* @return ReturnMsgData
	 *@author tie
	 * @throws ParseException 
	 */
	ReturnMsgData riskMonthZWFirstPage(PerformanceParm performanceParm);
	/**
	 * 风险风险管理部首页（月）
	* @Description: 
	* @param @param performanceParm
	* @param @return    
	* @return ReturnMsgData
	 *@author tie
	 * @throws ParseException 
	 */
	ReturnMsgData riskMonthSCFirstPage(PerformanceParm performanceParm);
	/**
	 * 风控贷后首页（月）
	* @Description: 
	* @param @param performanceParm
	* @param @return    
	* @return ReturnMsgData
	 *@author tie
	 * @throws ParseException 
	 */
	ReturnMsgData riskMonthAfterLoanFirstPage(PerformanceParm performanceParm);
	/**
	 * 风控事业部(月)首页
	* @Description: 
	* @param @param performanceParm
	* @return ReturnMsgData
	*@author tie
	 */
	ReturnMsgData riskMonthBusinessFirstPage(PerformanceParm performanceParm);
	/**
	 * 风控大区(月)首页
	* @Description: 
	* @param @param performanceParm
	* @return ReturnMsgData
	*@author tie
	 */
	ReturnMsgData riskMonthAreaFirstPage(PerformanceParm performanceParm);
	/**
	 * 风控分中心(月)首页
	* @Description: 
	* @param @param performanceParm
	* @return ReturnMsgData
	*@author tie
	 */
	ReturnMsgData riskMonthBranchCenterFirstPage(PerformanceParm performanceParm);
	/**
	 * 风控-执委或市场管理部点击事业部下钻(月)
	 * @param performanceParm
	 * @return
	 * @author tie
	 */
	ReturnMsgData riskMonthAreaPage(PerformanceParm performanceParm);
	/**
	 * 大区分中心下钻(月)
	 * @param performanceParm
	 * @return
	 * @author tie
	 */
	ReturnMsgData riskMonthBusinePage(PerformanceParm performanceParm);
	
	/**
	 * 根据不同参数和权限返回不同的C-M1日列表数据
	 * @param permission
	 * @return
	 */
	PageModel cm1DailyList(Map<String, Object> params);

	/**
	 * 查询f_risk_loan_cm1表中所有的月份(即日期为26日)
	 */
	List<Map<String, Object>> getMonthList();
	
	/**
	 * 根据不同参数和权限返回不同的C-M1月列表数据
	 * @param permission
	 * @return
	 */
	PageModel cm1MonthList(Map<String, Object> params);
}
