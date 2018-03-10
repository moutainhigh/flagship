package com.hzcf.flagship.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.flagship.baseweb.BaseController;
import com.hzcf.flagship.form.PerformanceParm;
import com.hzcf.flagship.service.ApiPerformanceService;
import com.hzcf.flagship.util.DateTimeUtil;
import com.hzcf.flagship.util.StringUtil;
import com.hzcf.flagship.vo.ReturnMsgData;

/**
 *
 * 类名：PerformanceController.java</dt>
 * 功能描述：APP接口 业绩 Controller
 * 创建时间： 2017年5月15日 下午3:12:02</dd>
 * 创建人：zhangmx</dd>
 */
@Controller
@RequestMapping("/licai")
public class PerformanceController extends BaseController{

	@Autowired
	private ApiPerformanceService apiPerformanceService;

	/**
	 * 日业绩
	 * 按日统计业绩、月累计达成率、当月每日业绩、各机构当月累计业绩
	 * @param performanceParm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/performanceForDays" ,method= RequestMethod.POST)
	public ReturnMsgData performanceForDays(@RequestBody PerformanceParm performanceParm) {
		try {
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//校验该用户是否有此权限
			if(!checkIsPermission(performanceParm, "理财")){
				return new ReturnMsgData("2002", "该用户无此权限");
			}
			if (!StringUtil.blank(performanceParm.getDays())) {
				//判断是否是当天日期
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), performanceParm.getDays()) == 0){
					performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(performanceParm.getDays(), -1));
				}
				//判断BI抽取数据是否异常
				if (!checkBIMoneymgrDailyPerformance(performanceParm.getDays())) {
					return new ReturnMsgData("2003", "当日数据异常", apiPerformanceService.findPerformanceByDate(performanceParm).getData());
				}
				return apiPerformanceService.findPerformanceByDate(performanceParm);
			}
			return new ReturnMsgData("9999", "日期参数为不能为 Null");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "调用失败");
		}
		
	}

	/**
	 * 月业绩
	 * 按月统计业绩对比、月达成率对比
	 * @param performanceParm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/performanceForMonths" ,method= RequestMethod.POST)
	public ReturnMsgData performanceForMonths(@RequestBody PerformanceParm performanceParm){
		try {
			
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//校验该用户是否有此权限
			if(!checkIsPermission(performanceParm, "理财")){
				return new ReturnMsgData("2002", "该用户无此权限");
			}
			if(!StringUtil.blank(performanceParm.getMonths())){
				//判断是否是1月份，是：取去年的数据
				if("01".equals(DateTimeUtil.getDate(performanceParm.getMonths(),"MM"))){
					performanceParm.setMonths(DateTimeUtil.getIntervalMonthLater(performanceParm.getMonths(), -1));
				}
				return apiPerformanceService.findPerformanceByMonths(performanceParm);
			}
			return new ReturnMsgData("9999", "日期参数为不能为 Null");
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "调用失败");
		}
	}

	/**
	 * 日业绩  各机构当月累计业绩 H5地图
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/performanceOrgMapForDays", method = RequestMethod.POST)
	public ReturnMsgData performanceOrgMapForDays(HttpServletRequest request, HttpServletResponse response){
		try {
			PerformanceParm performanceParm = new PerformanceParm();
			performanceParm.setToken(request.getParameter("token"));
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//校验该用户是否有此权限
			if(!checkIsPermission(performanceParm, "理财")){
				return new ReturnMsgData("2002", "该用户无此权限");
			}
			
			if(!StringUtil.blank(request.getParameter("days"))) {
				//判断BI抽取数据是否异常
				String date = request.getParameter("days");
				Map<String, Object> parm = new HashMap<>();
				String currYear = DateTimeUtil.getDate(request.getParameter("days"),"yyyy");
				String currMonth = DateTimeUtil.getDate(request.getParameter("days"),"M");
				//判断是否是当天日期
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), request.getParameter("days")) == 0){
					date = DateTimeUtil.getIntervalDaysLater(request.getParameter("days"), -1);
				}
				parm.put("dateString", date);
				parm.put("yearValue", currYear);
				parm.put("monthValue", currMonth);
				if (!checkBIDailyOrg(date)) {
					return new ReturnMsgData("2003", "当日数据异常", apiPerformanceService.findPerformanceOrgMapByDays(parm).getData());
				}
				return apiPerformanceService.findPerformanceOrgMapByDays(parm);
			}
			return new ReturnMsgData("9999", "日期参数为不能为 Null");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "调用失败");
		}
	}

	/**
	 * 理财 人员人效 日
	 * @param performanceParm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/moneymgrEfficiencyForDays", method = RequestMethod.POST)
	public ReturnMsgData moneymgrEfficiencyForDays(@RequestBody PerformanceParm performanceParm){
		try {
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//校验该用户是否有此权限
			if(!checkIsPermission(performanceParm, "理财")){
				return new ReturnMsgData("2002", "该用户无此权限");
			}
			if(!StringUtil.blank(performanceParm.getDays())){
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), performanceParm.getDays()) == 0){
					performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(performanceParm.getDays(), -1));
				}
				//判断BI抽取数据是否异常
				if (!checkBIMoneymgrDailyAccumuPerformance(performanceParm.getDays())) {
					return new ReturnMsgData("2003", "当日数据异常", apiPerformanceService.findEfficiencyForDays(performanceParm).getData());
				}
				return apiPerformanceService.findEfficiencyForDays(performanceParm);
			}
			return new ReturnMsgData("9999", "日期参数不能为 Null");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "调用失败");
		}
	}
	
	/**
	 * 查询理财区域页数据
	 * @param performanceParm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/financeForMonthData", method = RequestMethod.POST)
	public ReturnMsgData financeForMonthData(@RequestBody PerformanceParm performanceParm){
		try {
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//校验该用户是否有此权限
			if(!checkIsPermission(performanceParm, "理财")){
				return new ReturnMsgData("2002", "该用户无此权限");
			}
			//BI数据异常
			String days = performanceParm.getDays();
			String token = performanceParm.getToken();
			if(!StringUtil.blank(days)){
				//判断是否为当天时间
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), days) == 0){
					days = DateTimeUtil.getIntervalDaysLater(days, -1);
				}
				//判断BI抽取数据是否异常
				if (!checkBIDailyDistrict(days)) {
					return new ReturnMsgData("2003", "当日数据异常", apiPerformanceService.financeForMonthData(days,token).getData());
				}
				
				return apiPerformanceService.financeForMonthData(days,token);
			}
			return new ReturnMsgData("9999", "日期参数不能为 Null");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "调用失败");
		}
	}
	
	/**
	 * 查询业务监控室首页数据
	 * @param performanceParm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getIndexPageInfo", method = RequestMethod.POST)
	public ReturnMsgData getIndexPageInfo(@RequestBody PerformanceParm performanceParm){
		try {
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			String days = performanceParm.getDays();
			if(!StringUtil.blank(days)){
				//判断是否为当天时间
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), days) == 0){
					days = DateTimeUtil.getIntervalDaysLater(days, -1);
				}
				//判断BI抽取数据是否异常
				if (!checkBIMoneymgrDailyPerformance(days) || !checkBIFinanceDailyPerformance(days)) {
					return new ReturnMsgData("2003", "当日数据异常", apiPerformanceService.findIndexPageInfo(days).getData());
				}
				return apiPerformanceService.findIndexPageInfo(days);
			}
			return new ReturnMsgData("9999", "日期参数不能为 Null");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "调用失败");
		}
	}

	/**
	 * 理财 人员人效 月
	 * @param performanceParm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/moneymgrEfficiencyForMonths", method = RequestMethod.POST)
	public ReturnMsgData moneymgrEfficiencyForMonths(@RequestBody PerformanceParm performanceParm){
		try {
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			//校验该用户是否有此权限
			if(!checkIsPermission(performanceParm, "理财")){
				return new ReturnMsgData("2002", "该用户无此权限");
			}
			if(!StringUtil.blank(performanceParm.getMonths())){
				return apiPerformanceService.findEfficiencyForMonths(performanceParm);
			}
			return new ReturnMsgData("9999", "日期参数不能为 Null");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "调用失败");
		}
	}

	/**
	 * 机构模糊查询 地图查询条件
	 * @param performanceParm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findLikeOrgName", method = RequestMethod.POST)
	public ReturnMsgData findLikeOrgName(@RequestBody PerformanceParm performanceParm){
		Map<String, Object> parm = new HashedMap();
		if(!StringUtil.blank(performanceParm.getOrgName())){
			parm.put("orgName", performanceParm.getOrgName());
			return apiPerformanceService.findLikeOrgName(parm);
		}
		return new ReturnMsgData("9999","参数不能为 Null");
	}

	/**
	 * 理财区域 查询所有区名称  地图查询条件
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/allAreaName", method = RequestMethod.POST)
	public ReturnMsgData allAreaName(@RequestBody PerformanceParm performanceParm){		//token
		try {
			return apiPerformanceService.findAllAreaName(performanceParm);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "调用失败");
		}
		
	}

	/**
	 * 理财区域 查询所有区域下的机构 地图查询条件
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findOrgNameByArea", method = RequestMethod.POST)
	public ReturnMsgData findOrgNameByArea(@RequestBody PerformanceParm performanceParm){
		try {
			Map<String, Object> parmMap = new HashedMap();
			String areaString = performanceParm.getAreaName();
			String dateString = performanceParm.getDays();
			if(!StringUtil.blank(areaString) && !StringUtil.blank(dateString)){		//
				parmMap.put("areaString", areaString);
				parmMap.put("dateString", dateString);
				return apiPerformanceService.findOrgNameByArea(parmMap);
			}
			return new ReturnMsgData("9999", "参数不能为 Null");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "调用失败");
		}
	}

	/**
	 * 理财区域 查询所有区域和机构名称  地图查询条件
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findAllAreaOrgName", method = RequestMethod.POST)
	public ReturnMsgData findAllAreaOrgName(@RequestBody PerformanceParm performanceParm){		//token
		try {
			String days = performanceParm.getDays();
			if(!StringUtil.blank(days)) {
				//判断是否为当天时间
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), days) == 0){
					performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(days, -1));
				}
				return apiPerformanceService.findAllAreaOrgName(performanceParm);
			}
			return new ReturnMsgData("9999", "参数不能为 Null");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "调用失败");
		}
	}

	/**
	 * 理财区域 查询区域机构业绩达成率  APP接口
	 * @param performanceParm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/areaCompleteRateByDay", method = RequestMethod.POST)
	public ReturnMsgData areaCompleteRateByDay(@RequestBody PerformanceParm performanceParm){
		try {
			String days = performanceParm.getDays();
			if(!StringUtil.blank(days)){
				//判断是否为当天时间
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), days) == 0){
					performanceParm.setDays(DateTimeUtil.getIntervalDaysLater(days, -1));
				}
				return apiPerformanceService.findAreaPerformanceByOrgName(performanceParm);
			}
			return new ReturnMsgData("9999", "参数不能为 Null");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "调用失败");
		}
	}

	/**
	 * 理财区域 查询区域机构业绩达成率  H5地图
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/areaCompleteRateForMap", method = RequestMethod.POST)
	public ReturnMsgData areaCompleteRateForMap(HttpServletRequest request, HttpServletResponse response){
		try {
			String orgNameString = request.getParameter("orgNames");
			String days = request.getParameter("days");
			if(!StringUtil.blank(days)) {
				//判断是否为当天时间
				if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), days) == 0){
					days = DateTimeUtil.getIntervalDaysLater(days, -1);
				}
				Map<String, Object> parm = new HashMap<>();
				parm.put("dateString", days);
				if(!StringUtil.blank(orgNameString)){
					String[] orgNameList = orgNameString.split(",");
					parm.put("orgNameList", orgNameList);
				}
				return apiPerformanceService.findAreaPerformanceByOrgName(parm);
			}
			return new ReturnMsgData("9999", "参数不能为 Null");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "调用失败");
		}
	}
}
