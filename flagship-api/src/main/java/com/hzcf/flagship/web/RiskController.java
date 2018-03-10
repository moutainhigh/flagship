package com.hzcf.flagship.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hzcf.flagship.baseweb.BaseController;
import com.hzcf.flagship.form.PerformanceParm;
import com.hzcf.flagship.model.FileMapping;
import com.hzcf.flagship.service.RiskService;
import com.hzcf.flagship.util.DateTimeUtil;
import com.hzcf.flagship.util.FileUtil;
import com.hzcf.flagship.util.PropertyUtil;
import com.hzcf.flagship.util.StringUtil;
import com.hzcf.flagship.vo.ReturnMsgData;


/**
*<dl>
*<dt>类名：RiskController.java</dt>
*<dd>描述: 风险业务逻辑实现</dd>
*<dd>创建时间： 2017年10月23日 下午2:24:33</dd>
*<dd>创建人：Tieguowei</dd>
*<dt>版本历史: </dt>
* <pre>
* Date Author Version Description
* ------------------------------------------------------------------
* 2017年10月23日 下午2:24:33 Tieguowei 1.0 1.0 Version
* </pre>
*</dl>
*/
@Controller
@RequestMapping("/riskApi")
public class RiskController extends BaseController {
	
    private final Log logger = LogFactory.getLog(getClass());
  //#下载文件 全路径
  	private String downloadFullPath = PropertyUtil.getInfo("downloadFullPath");
	@Autowired
	private RiskService riskService;
	/**
	 * 
	 * 风险执委首页
	 * @param  performanceParm
	 * @author  Tieguowei
	 */ 
	@ResponseBody
	@RequestMapping(value="/riskZWFirstPage",method= RequestMethod.POST)
	public ReturnMsgData riskZWFirstPage(@RequestBody PerformanceParm performanceParm){
		try {
			logger.info("进入风险执委首页接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			String days =performanceParm.getDays();
			if(!StringUtil.blank(days)){
				//判断是否为当天时间
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), days) == 0){
					performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(days, -1));
				}
				//风险执委首页
				return riskService.riskZWFirstPage(performanceParm);
			}
			return new ReturnMsgData("9999", "请求失败", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	
	/**
	 * 
	 * 风险执委首页(月)
	 * @param  performanceParm
	 * @author  tie
	 */ 
	@ResponseBody
	@RequestMapping(value="/riskMonthZWFirstPage",method= RequestMethod.POST)
	public ReturnMsgData riskMonthZWFirstPage(@RequestBody PerformanceParm performanceParm){
		try {
			logger.info("进入风险执委首页(月)接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//风险执委(月)首页
			return riskService.riskMonthZWFirstPage(performanceParm);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	/**
	 * 风控市场管理部首页
	 * @param performanceParm
	 * @return
	 * @author guodong
	 */
	@ResponseBody
	@RequestMapping(value="/riskSCFirstPage",method= RequestMethod.POST)
	public ReturnMsgData riskSCFirstPage(@RequestBody PerformanceParm performanceParm){
		
		try {
			logger.info("进入风控市场管理部首页接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			String days = performanceParm.getDays();
			if(!StringUtil.blank(days)){
				//判断是否为当天时间
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), days) == 0){
					performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(days, -1));
				}
				//风控市场管理部首页
				return riskService.riskSCFirstPage(performanceParm);
			}
			return new ReturnMsgData("9999", "请求失败", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}

	
	/**
	 * 风控市场管理部首页（月）
	 * @param performanceParm
	 * @return
	 * @author tie
	 */
	@ResponseBody
	@RequestMapping(value="/riskMonthSCFirstPage",method= RequestMethod.POST)
	public ReturnMsgData riskMonthSCFirstPage(@RequestBody PerformanceParm performanceParm){
		
		try {
			logger.info("进入风控市场管理部首页(月)接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
				//风控市场管理部首页（月）
			return riskService.riskMonthSCFirstPage(performanceParm);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	
	/**
	 * 风控-市场管理部点击事业部下钻
	 * @param performanceParm
	 * @return
	 * @author guodong
	 */
	@ResponseBody
	@RequestMapping(value="/riskAreaPage",method= RequestMethod.POST)
	public ReturnMsgData riskAreaPage(@RequestBody PerformanceParm performanceParm){
		try {
			logger.info("进入风控市场管理部点击事业部下钻接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			String month = performanceParm.getMonth();
			String orgNo = performanceParm.getOrgNo();
			String day = performanceParm.getDays();
			String type = performanceParm.getType();
			if(!StringUtil.blank(month) && !StringUtil.blank(orgNo) && !StringUtil.blank(day) && !StringUtil.blank(type)){
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), day) == 0){
					performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(day, -1));
				}
				return riskService.riskAreaPage(performanceParm);
			}
			return new ReturnMsgData("9999", "请求失败", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	/**
	 * 风控-执委或市场管理部点击事业部下钻(月)
	 * @param performanceParm
	 * @return
	 * @author tie
	 */
	@ResponseBody
	@RequestMapping(value="/riskMonthAreaPage",method= RequestMethod.POST)
	public ReturnMsgData riskMonthAreaPage(@RequestBody PerformanceParm performanceParm){
		try {
			logger.info("进入 风控-执委或市场管理部点击事业部下钻(月) 接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			String month = performanceParm.getMonth();
			String orgNo = performanceParm.getOrgNo();
			String type = performanceParm.getType();
			if(!StringUtil.blank(month) && !StringUtil.blank(orgNo)  && !StringUtil.blank(type)){
				return riskService.riskMonthAreaPage(performanceParm);
			}
			return new ReturnMsgData("9999", "请求失败", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	/**
	 * 风控-市场管理部点击大区下钻
	 * @param performanceParm
	 * @return
	 * @author guodong
	 */
	@ResponseBody
	@RequestMapping(value="/riskBusinePage",method= RequestMethod.POST)
	public ReturnMsgData riskBusinePage(@RequestBody PerformanceParm performanceParm){
		
		try {
			logger.info("进入风控市场管理部点击大区下钻接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			String month = performanceParm.getMonth();
			String orgNo = performanceParm.getOrgNo();
			String day = performanceParm.getDays();
			String type = performanceParm.getType();
			if(!StringUtil.blank(month) && !StringUtil.blank(orgNo) && !StringUtil.blank(day) && !StringUtil.blank(type)){
				//判断是否为当天时间
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), day) == 0){
					performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(day, -1));
				}
				return riskService.riskBusinePage(performanceParm);
			}
			return new ReturnMsgData("9999", "请求失败", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	/**
	 * 大区/分中心下钻（月）
	 * @param performanceParm
	 * @return
	 * @author guodong
	 */
	@ResponseBody
	@RequestMapping(value="/riskMonthBusinePage",method= RequestMethod.POST)
	public ReturnMsgData riskMonthBusinePage(@RequestBody PerformanceParm performanceParm){
		
		try {
			logger.info("进入大区/分中心下钻（月） 接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			String month = performanceParm.getMonth();
			String orgNo = performanceParm.getOrgNo();
			String type = performanceParm.getType();
			if(!StringUtil.blank(month) && !StringUtil.blank(orgNo) && !StringUtil.blank(type)){
				return riskService.riskMonthBusinePage(performanceParm);
			}
			return new ReturnMsgData("9999", "请求失败", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	/**
	 * 风控-大区首页
	 * @param performanceParm
	 * @return
	 * @author guodong
	 */
	@ResponseBody
	@RequestMapping(value="/riskAreaFirstPage",method= RequestMethod.POST)
	public ReturnMsgData riskAreaFirstPage(@RequestBody PerformanceParm performanceParm){
		try {
			logger.info("进入风控大区首页接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			String orgNo = performanceParm.getOrgNo();
			String day = performanceParm.getDays();
			if(!StringUtil.blank(orgNo) && !StringUtil.blank(day)){
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), day) == 0){
					performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(day, -1));
				}
				return riskService.riskAreaFirstPage(performanceParm);
			}
			return new ReturnMsgData("9999", "请求失败", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	
	/**
	 * 
	 * 风控-大区首页(月)
	 * @param  performanceParm
	 * @author  tie
	 */ 
	@ResponseBody
	@RequestMapping(value="/riskMonthAreaFirstPage",method= RequestMethod.POST)
	public ReturnMsgData riskMonthAreaFirstPage(@RequestBody PerformanceParm performanceParm){
		try {
			logger.info("风控-大区(月) 首页接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			String orgNo = performanceParm.getOrgNo();
			if(!StringUtil.blank(orgNo)){
				// 风控-大区 首页(月) 首页
				return riskService.riskMonthAreaFirstPage(performanceParm);
			}
			return new ReturnMsgData("9999", "请求失败", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	/**
	 * 
	 * 风控 事业部 首页
	 * @param  performanceParm
	 * @author  tie
	 */ 
	@ResponseBody
	@RequestMapping(value="/riskBusinessFirstPage",method= RequestMethod.POST)
	public ReturnMsgData riskBusinessFirstPage(@RequestBody PerformanceParm performanceParm){
		try {
			logger.info("风控 事业部 首页接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			String days =performanceParm.getDays();
			String orgNo = performanceParm.getOrgNo();
			if(!StringUtil.blank(days) && !StringUtil.blank(orgNo)){
				//判断是否为当天时间
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), days) == 0){
					performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(days, -1));
				}
				// 风控 事业部 首页
				return riskService.riskBusinessFirstPage(performanceParm);
			}
			return new ReturnMsgData("9999", "请求失败", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	/**
	 * 
	 * 风控 事业部 首页(月)
	 * @param  performanceParm
	 * @author  tie
	 */ 
	@ResponseBody
	@RequestMapping(value="/riskMonthBusinessFirstPage",method= RequestMethod.POST)
	public ReturnMsgData riskMonthBusinessFirstPage(@RequestBody PerformanceParm performanceParm){
		try {
			logger.info("风控 事业部 首页(月) 首页接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			String orgNo = performanceParm.getOrgNo();
			if(!StringUtil.blank(orgNo)){
				// 风控 事业部 首页(月) 首页
				return riskService.riskMonthBusinessFirstPage(performanceParm);
			}
			return new ReturnMsgData("9999", "请求失败", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	/**
	 * 风控-大区点击营业部下钻
	 * @param performanceParm
	 * @return
	 * @author Tieguowei
	 */
	@ResponseBody
	@RequestMapping(value="/riskRegionToOrgPage",method= RequestMethod.POST)
	public ReturnMsgData riskRegionToOrgPage(@RequestBody PerformanceParm performanceParm){
		
		try {
			logger.info("进入风控-大区点击营业部下钻接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			String days =performanceParm.getDays();
			String orgNo = performanceParm.getOrgNo();
			if(!StringUtil.blank(days) && !StringUtil.blank(orgNo)){
				//判断是否为当天时间
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), days) == 0){
					performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(days, -1));
				}
				return riskService.riskRegionToOrgPage(performanceParm);
			}
			return new ReturnMsgData("9999", "请求失败", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	/**
	 * 风控--贷后整体下钻接口
	 * @param performanceParm
	 * @return
	 * @author guodong
	 */
	@ResponseBody
	@RequestMapping(value="/riskAfterLoanAll",method= RequestMethod.POST)
	public ReturnMsgData riskAfterLoanAll(@RequestBody PerformanceParm performanceParm){
		try {
			logger.info("进入风控贷后整体下钻接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			String days =performanceParm.getDays();
			if(!StringUtil.blank(days) && !StringUtil.blank(performanceParm.getMonth())){
				//判断是否为当天时间
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), days) == 0){
					performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(days, -1));
				}
			}
		 return riskService.riskAfterLoanAll(performanceParm);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
			}
		}
	/**
	 * 
	 * 风控 贷后 首页
	 * @param  performanceParm
	 * @author  Tieguowei
	 */ 
	@ResponseBody
	@RequestMapping(value="/riskAfterLoanFirstPage",method= RequestMethod.POST)
	public ReturnMsgData riskAfterLoanFirstPage(@RequestBody PerformanceParm performanceParm){
		try {
			logger.info("进入 风控 贷后 首页接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			String days =performanceParm.getDays();
			if(!StringUtil.blank(days)){
				//判断是否为当天时间
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), days) == 0){
					performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(days, -1));
				}
				// 风控 贷后 首页
				return riskService.riskAfterLoanFirstPage(performanceParm);
			}
			return new ReturnMsgData("9999", "请求失败", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	
	/**
	 * 风控 贷后 首页（月）
	 * @param performanceParm
	 * @return
	 * @author tie
	 */
	@ResponseBody
	@RequestMapping(value="/riskMonthAfterLoanFirstPage",method= RequestMethod.POST)
	public ReturnMsgData riskMonthAfterLoanFirstPage(@RequestBody PerformanceParm performanceParm){
		
		try {
			logger.info("进入风控 贷后 首页（月）接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//风控 贷后 首页（月）
			return riskService.riskMonthAfterLoanFirstPage(performanceParm);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	/**
	 * Description: 风控分中心首页
	 * @param performanceParm
	 * @return
	 * @author guodong
	 */
	@ResponseBody
	@RequestMapping(value="/riskBranchCenterFirstPage",method= RequestMethod.POST)
	public ReturnMsgData riskBranchCenterFirstPage(@RequestBody PerformanceParm performanceParm){
		try {
			logger.info("进入风控分中心首页接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			String orgNo = performanceParm.getOrgNo();
			String day = performanceParm.getDays();
			if(!StringUtil.blank(orgNo) && !StringUtil.blank(day)){
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), day) == 0){
					performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(day, -1));
				}
				return riskService.riskBranchCenterFirstPage(performanceParm);
			}
			return new ReturnMsgData("9999", "请求失败", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	/**
	 * Description: 风控分中心（月）首页
	 * @param performanceParm
	 * @return
	 * @author tie
	 */
	@ResponseBody
	@RequestMapping(value="/riskMonthBranchCenterFirstPage",method= RequestMethod.POST)
	public ReturnMsgData riskMonthBranchCenterFirstPage(@RequestBody PerformanceParm performanceParm){
		try {
			logger.info("进入风控分中心（月）首页接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			String orgNo = performanceParm.getOrgNo();
			if(!StringUtil.blank(orgNo) ){
				return riskService.riskMonthBranchCenterFirstPage(performanceParm);
			}
			return new ReturnMsgData("9999", "请求失败", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	/**
	 * Description: 营业部逾期合同明细
	 * @param performanceParm
	 * @return
	 * @author xuhao
	 */
	@ResponseBody
	@RequestMapping(value="/orgOverdueDetail",method= RequestMethod.POST)
	public ReturnMsgData orgOverdueDetail(@RequestBody PerformanceParm performanceParm){
		try {
			logger.info("进入营业部逾期合同明细接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			String orgNo = performanceParm.getOrgNo();
			String day = performanceParm.getDays();
			String productNo = performanceParm.getProductNo();
			if(!StringUtil.blank(orgNo) && !StringUtil.blank(day) && !StringUtil.blank(productNo)){
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), day) == 0){
					performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(day, -1));
				}
//				return riskService.orgOverdueDetail(day, orgNo, productNo);
				return riskService.orgOverdueDetail(performanceParm);
			}
			return new ReturnMsgData("9999", "请求失败", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	/**
	 * Description: 事业部预警营业部明细
	 * @param performanceParm
	 * @return
	 * @author xuhao
	 */
	@ResponseBody
	@RequestMapping(value="/warningOrgs",method= RequestMethod.POST)
	public ReturnMsgData warningOrgs(@RequestBody PerformanceParm performanceParm){
		try {
			logger.info("进事业部预警营业部明细接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			String orgNo = performanceParm.getOrgNo();
			String day = performanceParm.getDays();
			String productNo = performanceParm.getProductNo();
			String warningLevel = performanceParm.getWarningLevel();
			if(!StringUtil.blank(orgNo) && !StringUtil.blank(day) && !StringUtil.blank(productNo) && !StringUtil.blank(warningLevel)){
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), day) == 0){
					performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(day, -1));
				}
//				return riskService.warningOrgs(day, orgNo, productNo, warningLevel);
				return riskService.warningOrgs(performanceParm);
			}
			return new ReturnMsgData("9999", "请求失败", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	
	/**
	 * 营业部C-M1逾期合同明细
	 * @param performanceParm
	 * @return
	 * @author tgw
	 */
	@ResponseBody
	@RequestMapping(value="/orgCM1OverdueDetail",method= RequestMethod.POST)
	public ReturnMsgData orgCM1OverdueDetail(@RequestBody PerformanceParm performanceParm){
		
		try {
			logger.info("进入 营业部C-M1逾期合同明细 接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			String days = performanceParm.getDays();
			if(!StringUtil.blank(days)){
				//判断是否为当天时间
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), days) == 0){
					performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(days, -1));
				}
				//营业部C-M1逾期合同明细
				return riskService.orgCM1OverdueDetail(performanceParm);
			}
			return new ReturnMsgData("9999", "请求失败", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	
	@RequestMapping("/checkUrlCode/{code}")
	public String checkCode(@PathVariable String code,HttpServletResponse response){
		try {
			//通过code 去查询 path 和 日期
			 FileMapping fileMapping = null;
			if(null != code){
				  fileMapping = riskService.findInfoAndAddressByCode(code);
				  if(null != fileMapping){
					String info = fileMapping.getInfo();
					//营业部预期数据为空，不做校验，直接下载
					if(null == info){
						//拼接全路径
						String dfsAddress = fileMapping.getDfsAddress();
						FileUtil.downLoadFile(downloadFullPath+dfsAddress,response,code);
					}else{
						String[] split = info.split("\\+");
						//判断日期是否是 五天之内
						SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
						String recordDate = split[0];
						String orgNo = split[1];
						Date date = sd.parse(recordDate + " 00:00:00");
						boolean result = DateTimeUtil.checkDateIsPast(date,new Date());
						if(result){
							//拼接全路径
							String dfsAddress = fileMapping.getDfsAddress();
							FileUtil.downLoadFile(downloadFullPath+dfsAddress,response,orgNo);
						}else{
							//跳转到 链接已失效页面
							return "disabled";
						}
					}
					
				  }else{
					  return "disabled";
					}
			}else{
				return "disabled";	
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "disabled";
		}
		return null;
	}
	
	
	
	
}
