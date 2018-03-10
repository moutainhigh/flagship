package com.hzcf.flagship.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hzcf.flagship.baseweb.BaseController;
import com.hzcf.flagship.form.PerformanceParm;
import com.hzcf.flagship.service.ApiFinanceService;
import com.hzcf.flagship.service.FinancePerformanceService;
import com.hzcf.flagship.service.FinanceRiskService;
import com.hzcf.flagship.service.impl.ApiFinanceServiceImpl;
import com.hzcf.flagship.util.DateTimeUtil;
import com.hzcf.flagship.util.StringUtil;
import com.hzcf.flagship.vo.ReturnMsgData;

/**
 * 
 *<dl>
 *<dt>类名：FinanceController.java</dt>
 *<dd>描述: 融资业务逻辑实现</dd> 
 *<dd>创建时间： 2017年8月14日 下午2:48:10</dd>
 *<dd>创建人： GuoDong</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年8月14日 下午2:48:10    GuoDong       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Controller
@RequestMapping("/rongzi")
public class FinanceController extends BaseController {
	
    private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private ApiFinanceService apiFinanceService;
	@Autowired
	private FinanceRiskService financeRiskService;
	@Autowired
	private FinancePerformanceService financePerformanceService;
	
	/**
	 * 
	 * 融资月业绩
	 * @param  performanceParm
	 * @author  Tieguowei
	 */ 
	@ResponseBody
	@RequestMapping(value="/financeMonthPerformance",method= RequestMethod.POST)
	public ReturnMsgData financeMonthPerformance(@RequestBody PerformanceParm performanceParm){
		
		try {
		logger.info("进入融资月业绩接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//校验该用户是否有此权限
			if(!checkIsPermission(performanceParm, "融资")){
				return new ReturnMsgData("2002", "该用户无此权限");
			}
			 //查找融资月业绩
			return apiFinanceService.financeMonthPerformance();
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	/**
	 * 融资风险接口
	 * @param performanceParm
	 * @author hao
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/financeRisk" ,method= RequestMethod.POST)
	public ReturnMsgData getFinanceRisk(@RequestBody PerformanceParm performanceParm) {
		try {
			
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//校验该用户是否有此权限
			if(!checkIsPermission(performanceParm, "融资")){
				return new ReturnMsgData("2002", "该用户无此权限");
			}
	    	Map<String, Object> data = financeRiskService.apiGetFinanceRisk();
			return new ReturnMsgData("0000", "调用成功",data);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	/**
	 * 融资日业绩接口
	 * @param performanceParm
	 * @author hao	
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/financeDailyPerformance" ,method= RequestMethod.POST)
	public ReturnMsgData getFinancePerformance(@RequestBody PerformanceParm performanceParm) {
		try {
			
			/*if (!StringUtil.blank(performanceParm.getDays())) {
				//判断是否是当天日期
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), performanceParm.getDays()) == 0){
					performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(performanceParm.getDays(), -1));
				}
				//return apiPerformanceService.findPerformanceByDate(performanceParm);
			}*/
			//判断登陆
			if (!checkIsLogin(performanceParm)) {
				return new ReturnMsgData("2001", "用户未登录", null);
			}
			//判断是否有访问权限
			if (!checkIsPermission(performanceParm, "融资")) {
				return new ReturnMsgData("2002", "该用户无此权限", null);
			}
			/*//判断BI抽取数据是否异常
			if (!checkBIFinanceDailyPerformance(performanceParm.getDays())) {
				return new ReturnMsgData("2003", "当日数据异常", financePerformanceService.apiGetFinanceDailyPerformance(performanceParm).getData());
			}*/
			
			boolean result =  checkDataOfTheDayisNormal(performanceParm);
			if(result){
				return financePerformanceService.apiGetFinanceDailyPerformance(performanceParm);
			}else{
				ReturnMsgData returnMsgData = financePerformanceService.apiGetFinanceDailyPerformance(performanceParm);
				returnMsgData.setReturnCode("2004");
				return returnMsgData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
		
	}
	
	/**
	 * 融资日人员人效  
	 * @param performanceParm
	 * @author 东东
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/financeDailyPeople" ,method= RequestMethod.POST)
	public ReturnMsgData financeDailyPeople(@RequestBody PerformanceParm performanceParm) {
		try {
			logger.info("进入融资人员人效日页面接口，调用参数是： " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//校验该用户是否有此权限
			if(!checkIsPermission(performanceParm, "融资")){
				return new ReturnMsgData("2002", "该用户无此权限");
			}
			String days = performanceParm.getDays();
			if(!StringUtil.blank(days)){
				//判断是否为当天时间
				/*if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), days) == 0){
					days = DateTimeUtil.getIntervalDaysLater(days, -1);
				}*/
				//判断BI抽取数据是否异常
				/*if (!checkBIFinanceDailyPerformance(days)) {
					return new ReturnMsgData("2003", "当日数据异常", apiFinanceService.financeDailyPeople(days).getData());
				}*/
				
				boolean result =  checkDataOfTheDayisNormal(performanceParm);
				if(result){
					return apiFinanceService.financeDailyPeople(performanceParm.getDays());
				}else{
					ReturnMsgData returnMsgData = apiFinanceService.financeDailyPeople(performanceParm.getDays());
					returnMsgData.setReturnCode("2004");
					return returnMsgData;
				}
				
			}
			return new ReturnMsgData("9999", "日期参数不能为 Null");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
		
	}
	
	/**
	 * 融资月人员人效  
	 * @param performanceParm
	 * @author 东东
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/financeMonthPeople" ,method= RequestMethod.POST)
	public ReturnMsgData financeMonthPeople(@RequestBody PerformanceParm performanceParm) {
		try {
			logger.info("进入融资人员人效月页面接口 " );
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//校验该用户是否有此权限
			if(!checkIsPermission(performanceParm, "融资")){
				return new ReturnMsgData("2002", "该用户无此权限");
			}
			return apiFinanceService.financeMonthPeople();
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
		
	}
	
	
	/**
	 * 
	 *  查询所有大区
	 * @param performanceParm
	 * 开发者：    TieGuowei
	 */
	@ResponseBody
	@RequestMapping(value="/findAllDistrict",method= RequestMethod.POST)
	public ReturnMsgData findAllDistrictOfDay(@RequestBody PerformanceParm performanceParm){
		try {
			logger.info("=======进入查询所有大区方法。参数为====="+JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//校验该用户是否有此权限
			if(!checkIsPermission(performanceParm, "融资")){
				return new ReturnMsgData("2002", "该用户无此权限");
			}
			return apiFinanceService.findAllDistrict();
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	/**
	 * 
	 * 融资区域日： 根据区域名称 查找所有营业部
	 * @param performanceParm
	 * @author   TieGuowei
	 */
	@ResponseBody
	@RequestMapping(value="/findAllOrgNameByDistrictOfDay",method= RequestMethod.POST)
	public ReturnMsgData findAllOrgNameByDistrictOfDay(@RequestBody PerformanceParm performanceParm){
		logger.info("=======进入融资区域日： 根据区域名称 查找所有营业部方法。参数为====="+JSON.toJSONString(performanceParm));
		try {
			
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//校验该用户是否有此权限
			if(!checkIsPermission(performanceParm, "融资")){
				return new ReturnMsgData("2002", "该用户无此权限");
			}
			 //根据区域名称 查找所有营业部
			/*if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), performanceParm.getDays()) == 0){
				performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(performanceParm.getDays(), -1));
			}*/
			boolean result =  checkDataOfTheDayisNormal(performanceParm);
			if(result){
				return apiFinanceService.findAllOrgNameByDistrictOfDay(performanceParm);
			}else{
				ReturnMsgData returnMsgData = apiFinanceService.findAllOrgNameByDistrictOfDay(performanceParm);
				returnMsgData.setReturnCode("2004");
				return returnMsgData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	

	/**
	 * 
	 * 融资区域月： 根据区域名称 查找所有营业部
	 * @param performanceParm
	 * @author   TieGuowei
	 */
	@ResponseBody
	@RequestMapping(value="/findAllOrgNameByDistrictOfMonth",method= RequestMethod.POST)
	public ReturnMsgData findAllOrgNameByDistrictOfMonth(@RequestBody PerformanceParm performanceParm){
		logger.info("=======进入融资区域月： 根据区域名称 查找所有营业部 方法。参数为====="+JSON.toJSONString(performanceParm));

		try {
			
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//校验该用户是否有此权限
			if(!checkIsPermission(performanceParm, "融资")){
				return new ReturnMsgData("2002", "该用户无此权限");
			}
			return apiFinanceService.findAllOrgNameByDistrictOfMonth(performanceParm);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	/**
	 * 
	 * 融资区域 日 区域列表展示
	 * @param  performanceParm
	 * @author  TieGuowei
	 */
	@ResponseBody
	@RequestMapping(value="/financeDistrictOfDayList",method= RequestMethod.POST)
	public ReturnMsgData financeDistrictOfDay(@RequestBody PerformanceParm performanceParm){
		logger.info("=======进入融资区域 日 区域列表展示 方法。参数为====="+JSON.toJSONString(performanceParm));
		try {
			
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//校验该用户是否有此权限
			if(!checkIsPermission(performanceParm, "融资")){
				return new ReturnMsgData("2002", "该用户无此权限");
			}
			 //查找融资区域日
			/*if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), performanceParm.getDays()) == 0){
				performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(performanceParm.getDays(), -1));
			}*/
			//判断BI抽取数据是否异常
			/*if (!checkBIFinanceDailyDistrict(performanceParm.getDays())) {
				Map<String, Object> data = new HashMap<String, Object>();
				List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
				Map<String, Object> resultMap = new HashMap<String, Object>();
				Map<String, Object> resultMap2 = new HashMap<String, Object>();
				Map<String, Object> resultMap3 = new HashMap<String, Object>();
				Map<String, Object> resultMap4 = new HashMap<String, Object>();
				Map<String, Object> resultMap5 = new HashMap<String, Object>();
				data = ApiFinanceServiceImpl.noDataOfDay(dataList,data,resultMap,resultMap2,resultMap3,resultMap4,resultMap5);
				return new ReturnMsgData("2003", "当日数据异常", data);
			}*/
			
			boolean result =  checkDataOfTheDayisNormal(performanceParm);
			if(result){
				return apiFinanceService.financeDistrictOfDayList(performanceParm);
			}else{
				ReturnMsgData returnMsgData = apiFinanceService.financeDistrictOfDayList(performanceParm);
				returnMsgData.setReturnCode("2004");
				return returnMsgData;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	/**
	 * 
	 * 融资区域 日 营业部列表展示
	 * @param  performanceParm
	 * @author  TieGuowei
	 */
	@ResponseBody
	@RequestMapping(value="/financeOrgNameOfDayList",method= RequestMethod.POST)
	public ReturnMsgData financeOrgNameOfDayList(@RequestBody PerformanceParm performanceParm){
		logger.info("=======进入融资区域 日 营业部列表展示 方法。参数为====="+JSON.toJSONString(performanceParm));
		try {
			
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//校验该用户是否有此权限
			if(!checkIsPermission(performanceParm, "融资")){
				return new ReturnMsgData("2002", "该用户无此权限");
			}
			 //查找融资区域日
			/*if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), performanceParm.getDays()) == 0){
				performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(performanceParm.getDays(), -1));
			}*/
			//判断BI抽取数据是否异常
			/*if (!checkBIFinanceDailyOrg(performanceParm.getDays())) {
				Map<String, Object> data = new HashMap<String, Object>();
				List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
				Map<String, Object> resultMap = new HashMap<String, Object>();
				Map<String, Object> resultMap2 = new HashMap<String, Object>();
				Map<String, Object> resultMap3 = new HashMap<String, Object>();
				Map<String, Object> resultMap4 = new HashMap<String, Object>();
				Map<String, Object> resultMap5 = new HashMap<String, Object>();
				data = ApiFinanceServiceImpl.noDataOfDay(dataList,data,resultMap,resultMap2,resultMap3,resultMap4,resultMap5);
				return new ReturnMsgData("2003", "当日数据异常", data);
			}*/
			
			boolean result =  checkDataOfTheDayisNormal(performanceParm);
			if(result){
				return apiFinanceService.financeOrgNameOfDayList(performanceParm);
			}else{
				ReturnMsgData returnMsgData = apiFinanceService.findAllOrgNameByDistrictOfDay(performanceParm);
				returnMsgData.setReturnCode("2004");
				return returnMsgData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	/**
	 * 
	 * 融资区域 月 区域列表展示
	 * @param  performanceParm
	 * @author  TieGuowei
	 */
	@ResponseBody
	@RequestMapping(value="/financeDistrictOfMonthList",method= RequestMethod.POST)
	public ReturnMsgData financeDistrictOfMonthList(@RequestBody PerformanceParm performanceParm){
		logger.info("=======进入融资区域 月 区域列表展示 方法。参数为====="+JSON.toJSONString(performanceParm));
		try {
			
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//校验该用户是否有此权限
			if(!checkIsPermission(performanceParm, "融资")){
				return new ReturnMsgData("2002", "该用户无此权限");
			}
			return apiFinanceService.financeDistrictOfMonthList(performanceParm);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	/**
	 * 
	 * 融资区域 月 营业部列表展示
	 * @param  performanceParm
	 * @author  TieGuowei
	 */
	@ResponseBody
	@RequestMapping(value="/financeOrgNameOfMonthList",method= RequestMethod.POST)
	public ReturnMsgData financeOrgNameOfMonthList(@RequestBody PerformanceParm performanceParm){
		logger.info("=======进入融资区域 月 营业部列表展示 方法。参数为====="+JSON.toJSONString(performanceParm));
		try {
			
			 //验证是否登录
			if (!checkIsLogin(performanceParm)) {
				return new ReturnMsgData("2001", "用户未登录", null);
			}
			//验证是否有访问权限
			if(!checkIsPermission(performanceParm, "融资")){
				return new ReturnMsgData("2002", "该用户无此权限", null);
			}
			return apiFinanceService.financeOrgNameOfMonthList(performanceParm);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	/**
	 * 
	 * 融资人员 汇总
	 * @param  performanceParm
	 * @author  TieGuowei
	 */
	@ResponseBody
	@RequestMapping(value="/financePersonnelSum",method= RequestMethod.POST)
	public ReturnMsgData financePersonnelSum(@RequestBody PerformanceParm performanceParm){
		logger.info("=======进入融资人员 汇总方法。参数为====="+JSON.toJSONString(performanceParm));
		try {
			
			 //验证是否登录
			if (!checkIsLogin(performanceParm)) {
				return new ReturnMsgData("2001", "用户未登录", null);
			}
			//验证是否有访问权限
			if(!checkIsPermission(performanceParm, "融资")){
				return new ReturnMsgData("2002", "该用户无此权限", null);
			}
			return apiFinanceService.financePersonnelSum(performanceParm);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	
	/**
	 * 进件质量  日
	 * @param performanceParm
	 * @author 东东
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/financeDailyIntoQuality" ,method= RequestMethod.POST)
	public ReturnMsgData financeDailyIntoQuality(@RequestBody PerformanceParm performanceParm) {
		try {
			logger.info("进入进件质量 日页面接口 " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//校验该用户是否有此权限
			if(!checkIsPermission(performanceParm, "融资")){
				return new ReturnMsgData("2002", "该用户无此权限");
			}
			String days = performanceParm.getDays();
			if(!StringUtil.blank(days)){
				//判断是否为当天时间
				/*if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), days) == 0){
					days = DateTimeUtil.getIntervalDaysLater(days, -1);
				}*/
				//判断BI抽取数据是否异常
				/*if (!checkBIFinanceDailyPerformance(days)) {
					return new ReturnMsgData("2003", "当日数据异常", apiFinanceService.financeDailyIntoQuality(days).getData());
				}*/
				
				boolean result =  checkDataOfTheDayisNormal(performanceParm);
				if(result){
					return apiFinanceService.financeDailyIntoQuality(performanceParm.getDays());
				}else{
					ReturnMsgData returnMsgData = apiFinanceService.financeDailyIntoQuality(performanceParm.getDays());
					returnMsgData.setReturnCode("2004");
					return returnMsgData;
				}
			}
			return new ReturnMsgData("9999", "日期参数不能为 Null");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
		
	}
	
	/**
	 * 进件质量  月
	 * @param performanceParm
	 * @author 东东
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/financeMonthIntoQuality" ,method= RequestMethod.POST)
	public ReturnMsgData financeMonthIntoQuality(@RequestBody PerformanceParm performanceParm) {
		try {
			logger.info("进入进件质量 月页面接口" + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//校验该用户是否有此权限
			if(!checkIsPermission(performanceParm, "融资")){
				return new ReturnMsgData("2002", "该用户无此权限");
			}
				return apiFinanceService.financeMonthIntoQuality();
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
		
	}
	/**
	 * 根据指标名称和区域查找对应区域下所有营业部的指标
	 * @param performanceParm
	 * @author 东东
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getOrgIndexByAreaAndIndexName" ,method= RequestMethod.POST)
	public ReturnMsgData getOrgIndexByAreaAndIndexName(@RequestBody PerformanceParm performanceParm) {
		try {
			logger.info("进入根据指标名称和区域查找对应区域下所有营业部的指标接口 " + JSON.toJSONString(performanceParm));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//校验该用户是否有此权限
			if(!checkIsPermission(performanceParm, "融资")){
				return new ReturnMsgData("2002", "该用户无此权限");
			}
			//校验月份是否为空
			if(StringUtil.isBlank(performanceParm.getMonths())){
				return new ReturnMsgData("9999", "日期参数不能为 Null");
			}
			//校验区域是否为空
			if(StringUtil.isBlank(performanceParm.getAreaName())){
				return new ReturnMsgData("9999", "区域参数不能为 Null");
			}
			//校验指标参数
			if(StringUtil.isBlank(performanceParm.getIndexName())){
				return new ReturnMsgData("9999", "指标参数不能为 Null");
			}
			return apiFinanceService.getOrgIndexByAreaAndIndexName(performanceParm);
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "请求失败", null);
		}
	}
	/**
	 * 
	* @Description: 检查BI当日快多好数据跑批是否完成
	* @param @param performanceParm
	* @param @return    
	* @return String
	* @throws
	 */
	private boolean checkDataOfTheDayisNormal(PerformanceParm performanceParm) {
		try {
			/*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d2 = df.parse("2018-01-16 20:32:59");
			Calendar  g2= Calendar.getInstance();
			g2.setTime(d2);
			int hour = g2.get(Calendar.HOUR_OF_DAY);
			int minute = g2.get(Calendar.MINUTE);*/
			
			Calendar calendar = Calendar.getInstance();
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			//判断日期是否是当天
			if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), performanceParm.getDays()) == 0){
				//八点半以后（含八点半）
				if((hour >= 20 && minute >=30) || hour >= 21 ){
					//验证bi数据跑批是否完成
					long count = financePerformanceService.checkDataOfTheDayisNormal(performanceParm.getDays());
					if(count < 3){
						return false;
					}else{
						return true;
					}
				}else{
					//八点半以前 
					String intervalDaysLater = DateTimeUtil.getIntervalDaysLater(performanceParm.getDays(), -1);
					performanceParm.setDays(intervalDaysLater);
					return true;
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
