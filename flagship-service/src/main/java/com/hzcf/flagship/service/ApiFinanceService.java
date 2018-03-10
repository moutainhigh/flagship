package com.hzcf.flagship.service;

import java.util.Map;

import com.hzcf.flagship.form.PerformanceParm;
import com.hzcf.flagship.vo.ReturnMsgData;

/**
 *
 * 类名：ApiFinanceService.java</dt>
 * 功能描述：APP接口 融资 interface
 * 创建时间： 2017年5月22日 下午10:51:02</dd>
 * 创建人：zhangmx</dd>
 */
public interface ApiFinanceService {

	ReturnMsgData financeMonthPerformance();

	Map<String, String> getMonthRange(Map<String,Object> map);

	Map<String, String> getDayRange(Map<String,Object> map);

	ReturnMsgData findAllOrgNameByDistrictOfDay(PerformanceParm performanceParm);

	ReturnMsgData findAllOrgNameByDistrictOfMonth(PerformanceParm performanceParm);

	ReturnMsgData financeDailyPeople(String days);

	ReturnMsgData financeMonthPeople();
	
	ReturnMsgData financeDistrictOfDayList(PerformanceParm performanceParm);

	ReturnMsgData financeOrgNameOfDayList(PerformanceParm performanceParm);

	ReturnMsgData financeDistrictOfMonthList(PerformanceParm performanceParm);

	ReturnMsgData financeOrgNameOfMonthList(PerformanceParm performanceParm);

	ReturnMsgData financeDailyIntoQuality(String days);

	ReturnMsgData financeMonthIntoQuality();

	ReturnMsgData findAllDistrict();

	ReturnMsgData financePersonnelSum(PerformanceParm performanceParm);

	ReturnMsgData getOrgIndexByAreaAndIndexName(PerformanceParm performanceParm);
}
