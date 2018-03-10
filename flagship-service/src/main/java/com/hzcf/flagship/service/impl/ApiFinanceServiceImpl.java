package com.hzcf.flagship.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.FinanceDailyDistrictMapper;
import com.hzcf.flagship.dao.FinanceDailyOrgMapper;
import com.hzcf.flagship.dao.FinanceDailyPageMapper;
import com.hzcf.flagship.dao.FinanceDailyPerformanceMapper;
import com.hzcf.flagship.dao.FinanceMonthDistrictMapper;
import com.hzcf.flagship.dao.FinanceMonthOrgMapper;
import com.hzcf.flagship.dao.FinanceMonthPerformanceMapper;
import com.hzcf.flagship.dao.FinanceOrgMapMapper;
import com.hzcf.flagship.dao.FinancePersonalInfoMapper;
import com.hzcf.flagship.dao.IndexMapper;
import com.hzcf.flagship.enmu.DescriptionConstants;
import com.hzcf.flagship.enmu.TemplateConstants;
import com.hzcf.flagship.form.PerformanceParm;
import com.hzcf.flagship.model.FinanceDailyPage;
import com.hzcf.flagship.model.FinanceMonthDistrict;
import com.hzcf.flagship.model.FinanceMonthOrg;
import com.hzcf.flagship.model.FinanceMonthPerformance;
import com.hzcf.flagship.model.FinancePersonalInfo;
import com.hzcf.flagship.model.Index;
import com.hzcf.flagship.model.SortBean;
import com.hzcf.flagship.service.ApiFinanceService;
import com.hzcf.flagship.util.DateTimeUtil;
import com.hzcf.flagship.util.DateUtil;
import com.hzcf.flagship.util.StringUtil;
import com.hzcf.flagship.vo.ReturnMsgData;

/**
 *
 * 类名：ApiFinanceService.java</dt> 功能描述：APP接口 融资 interface 实现类 创建时间： 2017年5月22日
 * 下午10:51:02</dd> 创建人：zhangmx</dd>
 */
@Service
public class ApiFinanceServiceImpl implements ApiFinanceService {

	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private FinanceMonthPerformanceMapper financeMonthPerformanceMapper;
	@Autowired
	private FinancePersonalInfoMapper financePersonalInfoMapper;
	@Autowired
	private IndexMapper indexMapper;
	@Autowired
	private FinanceDailyPageMapper financeDailyPageMapper;
	@Autowired
	private FinanceDailyDistrictMapper financeDailyDistrictMapper;
	@Autowired
	private FinanceDailyOrgMapper financeDailyOrgMapper;
	@Autowired
	private FinanceMonthDistrictMapper financeMonthDistrictMapper;
	@Autowired
	private FinanceMonthOrgMapper financeMonthOrgMapper;
	@Autowired
	private FinanceDailyPerformanceMapper financeDailyPerformanceMapper;
	@Autowired
	private FinanceOrgMapMapper financeOrgMapMapper;

	private Map<String, Object> dataMap = new HashMap<String, Object>();
	DecimalFormat df = new DecimalFormat("###################.###########");
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-dd");


	/**
	 * 查找融资月业绩
	 */
	@Override
	public ReturnMsgData financeMonthPerformance() {
		BigDecimal monthPerFormanceSum;
		BigDecimal yearPerFormanceSum;
		BigDecimal yearPerformance;// 年业绩
		BigDecimal monthPerformance;// 月业绩 和 放款金额实际值
		FinanceMonthPerformance financeMP = null;
		Integer applyActualQuantity = null;// 进件件数实际值
		Integer loanActualQuantity = null;// 放款件数实际值

		// 查询month表 得到最新一条记录
		financeMP = getFinceMonthLastOne();
		// 月业绩 和 放款金额实际值
		monthPerformance = financeMP.getMonthPerformance();
		dataMap.put("monthPerFormance", monthPerformance);
		// 年业绩
		yearPerformance = financeMP.getYearPerformance();
		dataMap.put("yearPerFormance", yearPerformance);
		// 进件件数实际值
		applyActualQuantity = financeMP.getApplyActualQuantity();
		dataMap.put("applyActualQuantity", applyActualQuantity);
		// 放款件数实际值
		loanActualQuantity = financeMP.getLoanActualQuantity();
		dataMap.put("loanActualQuantity", loanActualQuantity);
		// 合同金额
		dataMap.put("contractMoney", financeMP.getContractMoney());
		// 查询上月批核率 上月放款件均
		String firstDate = format.format(financeMP.getRecordDate());
		Date convertAsDateString = DateTimeUtil.convertAsDateString(firstDate);
		String recordDate = simpleDateFormat.format(convertAsDateString);

		
		String[] recordTime = recordDate.split("-");
		// 得到年 -
		dataMap.put("year", recordTime[0]);
		// 得到月 -
		dataMap.put("month", recordTime[1]);
		
		String createTime = format.format(financeMP.getCreateTime());
		// 得到创建日期前一天
		String yesterday = DateUtil.getYesterdayBythisDay(financeMP.getCreateTime());
		int days = Integer.valueOf(createTime.split("-")[2]);
		if(days <=10){
			//上月 的最后一天
			Map<String, Object> monthFirstDayAndLastDay = DateUtil.getMonthFirstDayAndLastDay(recordDate);
			String day = (String) monthFirstDayAndLastDay.get("lastDay");
			String[] lastDay = day.split("-");
			dataMap.put("day", lastDay[2]);
		}else{
			//本月的创建日期减一
			String[] lastCreateTime = yesterday.split("-");
			dataMap.put("day", lastCreateTime[2]);
		}
		
		String firstDayOfLastMonth = DateUtil.getFirstDayOfLastMonth(recordDate);
		FinanceMonthPerformance lastMonthInfo = financeMonthPerformanceMapper.getLastMonthInfo(firstDayOfLastMonth);
		// 查询融资人员信息表的月计划汇总和年计划
		Map<String, Object> reaultMap = getMonthAndYearPlanSum(recordDate);
		monthPerFormanceSum = (BigDecimal) reaultMap.get("monthPerFormanceSum");
		yearPerFormanceSum = (BigDecimal) reaultMap.get("yearPerFormanceSum");
		if (monthPerFormanceSum!=null && yearPerFormanceSum!=null) {
			dataMap.put("monthPerFormanceSum",
					monthPerFormanceSum.divide(new BigDecimal(10000)).setScale(2, BigDecimal.ROUND_HALF_UP));
			dataMap.put("yearPerFormanceSum",
					yearPerFormanceSum.divide(new BigDecimal(10000)).setScale(2, BigDecimal.ROUND_HALF_UP));
			if (!new BigDecimal(-1).equals(monthPerFormanceSum)) {
				// 计算 月计划达成率比例 = 月业绩/月计划汇总
				int monthPlanRate = financeMP.getMonthPerformance().multiply(new BigDecimal(10000))
						.divide(monthPerFormanceSum, 2, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100))
						.setScale(0, BigDecimal.ROUND_DOWN).intValue();
				dataMap.put("monthPlanRate", monthPlanRate);
				
				// 放款件数计划值 = 本月目标/上月放款件均
				BigDecimal loanPlanQuantity = null;
				// 判断上月放款件均是否为空
				if (null != lastMonthInfo.getApprovalAverage()) {
					loanPlanQuantity = monthPerFormanceSum.divide(lastMonthInfo.getApprovalAverage(), 0,
							BigDecimal.ROUND_HALF_UP);
					dataMap.put("loanPlanQuantity", loanPlanQuantity);
					// 放款件数差值 = 放款件数实际值 - 放款件数计划值
					BigDecimal loanDIFQuantity = new BigDecimal(loanActualQuantity).subtract(loanPlanQuantity);
					dataMap.put("loanDIFQuantity", loanDIFQuantity);
				} else {
					dataMap.put("loanPlanQuantity", -1);
					dataMap.put("loanDIFQuantity", -1);
				}
				// 判断上月放款件均是否为0
				if (0 != lastMonthInfo.getApprovalAverage().compareTo(new BigDecimal(0))) {
					loanPlanQuantity = monthPerFormanceSum.divide(lastMonthInfo.getApprovalAverage(), 0,
							BigDecimal.ROUND_HALF_UP);
					dataMap.put("loanPlanQuantity", loanPlanQuantity);
					// 放款件数差值 = 放款件数实际值 - 放款件数计划值
					BigDecimal loanDIFQuantity = new BigDecimal(loanActualQuantity).subtract(loanPlanQuantity);
					dataMap.put("loanDIFQuantity", loanDIFQuantity);
				} else {
					dataMap.put("loanPlanQuantity", -1);
					dataMap.put("loanDIFQuantity", loanActualQuantity);
				}
				
				// 进件件数计划值 放款件数计划值/上月批核率
				BigDecimal applyPlanQuantity = null;
				// 判断上月批核率是否为空
				if (null != lastMonthInfo.getApprovalRate()) {
					applyPlanQuantity = loanPlanQuantity.divide(lastMonthInfo.getApprovalRate(), 0,
							BigDecimal.ROUND_HALF_UP);
					dataMap.put("applyPlanQuantity", applyPlanQuantity);
					// 放款金额差值 = 放款金额实际值 - 放款金额计划值
					BigDecimal loanDIFMoney = monthPerformance
							.subtract(
									monthPerFormanceSum.divide(new BigDecimal(10000)).setScale(2, BigDecimal.ROUND_HALF_UP))
							.setScale(2, BigDecimal.ROUND_HALF_UP);
					dataMap.put("loanDIFMoney", loanDIFMoney);
					
					// 进件件数差值 = 进件件数实际值 - 进件件数计划值
					BigDecimal applyDIFQuantity = new BigDecimal(applyActualQuantity).subtract(applyPlanQuantity);
					dataMap.put("applyDIFQuantity", applyDIFQuantity);
				} else {
					dataMap.put("applyPlanQuantity", -1);
					dataMap.put("loanDIFMoney", -1);
					dataMap.put("applyDIFQuantity", -1);
				}
				// 判断上月批核率是否为0
				if (0 != lastMonthInfo.getApprovalRate().compareTo(new BigDecimal(0))) {
					applyPlanQuantity = loanPlanQuantity.divide(lastMonthInfo.getApprovalRate(), 0,
							BigDecimal.ROUND_HALF_UP);
					dataMap.put("applyPlanQuantity", applyPlanQuantity);
					// 放款金额差值 = 放款金额实际值 - 放款金额计划值
					BigDecimal loanDIFMoney = monthPerformance
							.subtract(
									monthPerFormanceSum.divide(new BigDecimal(10000)).setScale(2, BigDecimal.ROUND_HALF_UP))
							.setScale(2, BigDecimal.ROUND_HALF_UP);
					dataMap.put("loanDIFMoney", loanDIFMoney);
					
					// 进件件数差值 = 进件件数实际值 - 进件件数计划值
					BigDecimal applyDIFQuantity = new BigDecimal(applyActualQuantity).subtract(applyPlanQuantity);
					dataMap.put("applyDIFQuantity", applyDIFQuantity);
				} else {
					dataMap.put("applyPlanQuantity", -1);
					dataMap.put("loanDIFMoney", monthPerformance);
					dataMap.put("applyDIFQuantity", -1);
				}
				
			} else {
				dataMap.put("applyDIFQuantity", -1);// 进件件数差值
				dataMap.put("loanDIFQuantity", -1);// 放款件数差值
				dataMap.put("loanDIFMoney", -1);// 放款金额差值
			}
			if (!new BigDecimal(-1).equals(yearPerFormanceSum)) {
				int yearPlanRate = financeMP.getYearPerformance().multiply(new BigDecimal(10000))
						.divide(yearPerFormanceSum, 2, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100))
						.setScale(0, BigDecimal.ROUND_DOWN).intValue();
				dataMap.put("yearPlanRate", yearPlanRate);// 年计划达成率比例 = 年业绩/年计划汇总
			}
		}
		// 业绩收入占比 排序
		sort(financeMP.getServiceChargeRate(), financeMP.getArbitrationChargeRate(), financeMP.getInsuranceRate());
		//融资月业绩 计划达成率列表
		List<FinanceMonthPerformance> list =financeMonthPerformanceMapper.getPlanListByYear();
		getDataList(list,Integer.valueOf(recordTime[0]));
		return new ReturnMsgData("0000", "调用成功", dataMap);
	}

	public void getDataList(List<FinanceMonthPerformance> list, Integer year){
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		for (FinanceMonthPerformance monthformance : list) {
			Date recorDate = monthformance.getRecordDate();
			String recordDate = format.format(recorDate);
			Map<String,Object> responseMap = new HashMap<String,Object>();
			//月业绩实际金额
			BigDecimal monthPerformance = monthformance.getMonthPerformance();
			Map<String, Object> map = DateUtil.getMonthFirstDayAndLastDay(recordDate);
			FinancePersonalInfo monthPlanInfo = financePersonalInfoMapper.getMonthPlan(map);
			//月业绩计划金额
			BigDecimal monthPerformanceSum = new BigDecimal(monthPlanInfo.getMonthPlan());
			//达成率
			int monthPlanRate = monthPerformance.multiply(new BigDecimal(10000))
					.divide(monthPerformanceSum, 2, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100))
					.setScale(0, BigDecimal.ROUND_DOWN).intValue();
			responseMap.put("monthPerformance", monthPerformance);
			responseMap.put("monthPerformanceSum", monthPerformanceSum.divide(new BigDecimal(10000)).setScale(2, BigDecimal.ROUND_HALF_UP));
			responseMap.put("monthPlanRate", monthPlanRate);
			responseMap.put("month", simpleDateFormat.format(recorDate).split("-")[1]);
			dataList.add(responseMap);
		}
		dataMap.put("dataList", dataList);
		if(list.size() == 1){
			dataMap.put("recordMonth", year+"年"+simpleDateFormat.format(list.get(0).getRecordDate()).split("-")[1]+"月");
		}else{
			dataMap.put("recordMonth", year+"年"+simpleDateFormat.format(list.get(list.size()-1).getRecordDate()).split("-")[1]+"~"+
					simpleDateFormat.format(list.get(0).getRecordDate()).split("-")[1]+"月");
		}
		
	}
	/**
	 * 查询融资人员信息表的月计划汇总和年计划
	 * 
	 * @param str
	 * 
	 */
	public Map<String, Object> getMonthAndYearPlanSum(String str) {

		// 查询融资人员信息表的月计划汇总和年计划
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> requestMap = DateUtil.getMonthFirstDayAndLastDay(str);
		FinancePersonalInfo monthPlanInfo = financePersonalInfoMapper.getMonthPlan(requestMap);
		FinancePersonalInfo yearPlanInfo = financePersonalInfoMapper.getYearPlan(requestMap);
		BigDecimal resultB = new BigDecimal(-1);
		if(null == monthPlanInfo){
			logger.error("=====融资人员信息表暂无数据上传=============");
			dataMap.put("monthPerFormanceSum", resultB);// 月计划汇总 | 放款金额计划值
			dataMap.put("monthPlanRate", resultB);// 月计划达成率比例
			dataMap.put("loanPlanQuantity", resultB);// 放款件数计划值
			dataMap.put("applyPlanQuantity", resultB);// 进件件数计划值
		}else{
			Integer monthPlan = monthPlanInfo.getMonthPlan();
			if (null == monthPlan) {
				logger.error("=====融资人员信息表暂无数据上传=============");
				dataMap.put("monthPerFormanceSum", resultB);// 月计划汇总 | 放款金额计划值
				dataMap.put("monthPlanRate", resultB);// 月计划达成率比例
				dataMap.put("loanPlanQuantity", resultB);// 放款件数计划值
				dataMap.put("applyPlanQuantity", resultB);// 进件件数计划值
			} else {
				resultMap.put("monthPerFormanceSum", new BigDecimal(monthPlan));
			}
		}
		if(null == yearPlanInfo){
			logger.error("=====融资人员信息表暂无数据上传=============");
			dataMap.put("yearPerFormanceSum", resultB);// 年计划汇总
			dataMap.put("yearPlanRate", resultB);// 年计划达成率比例
		}else{
			Integer yearPlan = yearPlanInfo.getMonthPlan();
			if (null == yearPlan) {
				logger.error("=====融资人员信息表暂无数据上传=============");
				dataMap.put("yearPerFormanceSum", resultB);// 年计划汇总
				dataMap.put("yearPlanRate", resultB);// 年计划达成率比例
			} else {
				resultMap.put("yearPerFormanceSum",  new BigDecimal(yearPlan));// 年计划汇总
			}

		}
		return resultMap;
	}

	/**
	 * 查询month表 得到最新一条记录
	 * @param map 
	 * 
	 * @return
	 */
	public FinanceMonthPerformance getFinceMonthLastOne() {

		List<FinanceMonthPerformance> financeMPList = financeMonthPerformanceMapper.getFinceMonthLastOne();
		FinanceMonthPerformance financeMP = null;
		if (null != financeMPList && 0 != financeMPList.size()) {
			financeMP = financeMPList.get(0);
		}
		return financeMP;

	}

	/**
	 * 
	 * 融资区域日：根据区域名称查找所有营业部
	 */
	@Override
	public ReturnMsgData findAllOrgNameByDistrictOfDay(PerformanceParm performanceParm) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		String days = performanceParm.getDays();
		String districtName = performanceParm.getAreaName();
		map.put("recordDate", days);
		map.put("districtName", districtName);
		List<Map<String, Object>> orgNameList = financeDailyOrgMapper.findAllOrgNameByDistrictOfDay(map);
		dataMap.put("orgNameList", orgNameList);
		return new ReturnMsgData("0000", "调用成功", dataMap);
	}

	/**
	 * 
	 * 融资区域月：查找所有区域
	 */
	@Override
	public ReturnMsgData findAllDistrict() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Map<String, Object>> districtList = financeOrgMapMapper.findAllDistrict();
		dataMap.put("districtList", districtList);
		return new ReturnMsgData("0000", "调用成功", dataMap);
	}

	/**
	 * 
	 * 融资区域月：根据区域名称查找所有营业部
	 */
	@Override
	public ReturnMsgData findAllOrgNameByDistrictOfMonth(PerformanceParm performanceParm) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		String districtName = performanceParm.getAreaName();
		map.put("districtName", districtName);
		String recordDate;
		// 根据区域名称    查询出_month_district表最新的时间点
		List<FinanceMonthDistrict> list = financeMonthDistrictMapper.findLastDateByDistrict(map);
		recordDate = format.format(list.get(0).getRecordDate());
		map.put("recordDate", recordDate);
		List<Map<String, Object>> orgNameList = financeMonthOrgMapper.findAllOrgNameByDistrictOfMonth(map);
		dataMap.put("orgNameList", orgNameList);
		return new ReturnMsgData("0000", "调用成功", dataMap);
	}

	/**
	 * 
	 * 融资区域--> 日 区域列表展示
	 * 
	 * @param performanceParm
	 */
	@Override
	public ReturnMsgData financeDistrictOfDayList(PerformanceParm performanceParm) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		String recordDate = performanceParm.getDays();
		String districtName = performanceParm.getAreaName();
		requestMap.put("recordDate", recordDate);
		requestMap.put("districtName", districtName);
		Map<String, Object> map = financeDailyDistrictMapper.financeDistrictOfDayList(requestMap);
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> resultMap2 = new HashMap<String, Object>();
		Map<String, Object> resultMap3 = new HashMap<String, Object>();
		Map<String, Object> resultMap4 = new HashMap<String, Object>();
		Map<String, Object> resultMap5 = new HashMap<String, Object>();
		if (null != map) {
			// 组装数据
			// 计划达成率 区间和其他值不同
			resultMap.put("name", DescriptionConstants.D1);
			if (null != map.get("planYieldRate")) {
				double planYieldRate = ((java.math.BigDecimal) map.get("planYieldRate")).doubleValue();
				Map<String, Object> rangeMap = getRangeMap(DescriptionConstants.DPLANYIELDRATE, planYieldRate,
						recordDate, TemplateConstants.F1, TemplateConstants.F2, "", "");
				long planYieldRateRanking = (long) map.get("planYieldRateRanking");
				planYieldRate(recordDate, planYieldRateRanking, resultMap, planYieldRate, rangeMap);
			} else {
				valueIsNullMethod(resultMap);
			}

			// 当日放款金额
			resultMap2.put("name", DescriptionConstants.D2);
			if (null != map.get("loanValue")) {
				resultMap2.put("value", df.format(map.get("loanValue")) + "万");
				resultMap2.put("range", "-");
				resultMap2.put("color", "normal");
				resultMap2.put("ranking", map.get("loanValueRanking"));
			} else {
				resultMap2.put("value", "--");
				resultMap2.put("range", "--");
				resultMap2.put("color", "normal");
				resultMap2.put("ranking", -1);
			}

			// 当日人均进件
			resultMap3.put("name", DescriptionConstants.D3);
			if (null != map.get("applyQuantityAvg")) {
				double applyQuantityAvg = ((java.math.BigDecimal) map.get("applyQuantityAvg")).doubleValue();
				Map<String, Object> rangeMap3 = getRangeMap(DescriptionConstants.DPEOPLEAVG, applyQuantityAvg,
						recordDate, TemplateConstants.F3, TemplateConstants.F4, "", "");
				long applyQuantityRanking = (long) map.get("applyQuantityRanking");
				getPerAvgApply(recordDate, applyQuantityRanking, resultMap3, applyQuantityAvg, rangeMap3);
			} else {
				valueIsNullMethod(resultMap3);
			}
			// 批核率
			resultMap4.put("name", DescriptionConstants.D4);
			if (null != map.get("accumuApprovalRate")) {
				double accumuApprovalRate = ((java.math.BigDecimal) map.get("accumuApprovalRate")).doubleValue();
				Map<String, Object> rangeMap4 = getRangeMap(DescriptionConstants.DAPPROVALRATE, accumuApprovalRate,
						recordDate, TemplateConstants.F5, TemplateConstants.F6, TemplateConstants.F7,
						TemplateConstants.F8);
				Map<String, String> dayRange4 = getDayRange(rangeMap4);

				resultMap4.put("value", dayRange4.get("value"));
				resultMap4.put("ranking", map.get("accumuApprovalRateRanking"));
				resultMap4 = checkIsNull(dayRange4, resultMap4);
			} else {
				valueIsNullMethod(resultMap4);
			}

			// 批核件均
			resultMap5.put("name", DescriptionConstants.D5);
			if (null != map.get("accumuApprovalAverage")) {
				double accumuApprovalAverage = ((java.math.BigDecimal) map.get("accumuApprovalAverage")).doubleValue();
				Map<String, Object> rangeMap5 = getRangeMap(DescriptionConstants.DAPPROVALAVG, accumuApprovalAverage,
						recordDate, TemplateConstants.F9, TemplateConstants.F10, TemplateConstants.F11,
						TemplateConstants.F12);
				Map<String, String> dayRange5 = getDayRange(rangeMap5);

				resultMap5.put("value", dayRange5.get("value"));
				resultMap5.put("ranking", map.get("accumuApprovalAverageRanking"));
				resultMap5 = checkIsNull(dayRange5, resultMap5);
			} else {
				valueIsNullMethod(resultMap5);
			}
			dataList.add(resultMap);
			dataList.add(resultMap2);
			dataList.add(resultMap3);
			dataList.add(resultMap4);
			dataList.add(resultMap5);
			data.put("dataList", dataList);
			data.put("districtName", map.get("districtName"));
			return new ReturnMsgData("0000", "调用成功", data);
		} else {
			// 所有数据全部显示 "--"
			data = noDataOfDay(dataList, data, resultMap, resultMap2, resultMap3, resultMap4, resultMap5);
			return new ReturnMsgData("0000", "调用成功", data);
		}
	}

	/**
	 * 
	 * 融资区域--> 日 营业部列表展示
	 * 
	 * @param performanceParm
	 */
	@Override
	public ReturnMsgData financeOrgNameOfDayList(PerformanceParm performanceParm) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		String recordDate = performanceParm.getDays();
		String orgName = performanceParm.getOrgName();
		requestMap.put("recordDate", recordDate);
		requestMap.put("orgName", orgName);
		Map<String, Object> map = financeDailyOrgMapper.financeOrgNameOfDayList(requestMap);
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> resultMap2 = new HashMap<String, Object>();
		Map<String, Object> resultMap3 = new HashMap<String, Object>();
		Map<String, Object> resultMap4 = new HashMap<String, Object>();
		Map<String, Object> resultMap5 = new HashMap<String, Object>();
		if (null != map) {
			// 组装数据
			// 计划达成率
			resultMap.put("name", DescriptionConstants.D1);
			if (null != map.get("planYieldRate")) {
				double planYieldRate = ((java.math.BigDecimal) map.get("planYieldRate")).doubleValue();
				Map<String, Object> rangeMap = getRangeMap(DescriptionConstants.DPLANYIELDRATE, planYieldRate,
						recordDate, TemplateConstants.F1, TemplateConstants.F2, "", "");
				long planYieldRateRanking = (long) map.get("planYieldRateRanking");
				planYieldRate(recordDate, planYieldRateRanking, resultMap, planYieldRate, rangeMap);
			} else {
				valueIsNullMethod(resultMap);
			}

			// 当日放款金额
			resultMap2.put("name", DescriptionConstants.D2);
			if (null != map.get("loanValue")) {
				resultMap2.put("value", df.format(map.get("loanValue")) + "万");
				resultMap2.put("range", "-");
				resultMap2.put("color", "normal");
				resultMap2.put("ranking", map.get("loanValueRanking"));
			} else {
				resultMap2.put("value", "--");
				resultMap2.put("range", "--");
				resultMap2.put("color", "normal");
				resultMap2.put("ranking", -1);
			}

			// 当日人均进件
			resultMap3.put("name", DescriptionConstants.D3);
			if (null != map.get("applyQuantityAvg")) {
				double applyQuantityAvg = ((java.math.BigDecimal) map.get("applyQuantityAvg")).doubleValue();
				Map<String, Object> rangeMap3 = getRangeMap(DescriptionConstants.DPEOPLEAVG, applyQuantityAvg,
						recordDate, TemplateConstants.F3, TemplateConstants.F4, "", "");
				long applyQuantityRanking = (long) map.get("applyQuantityRanking");
				getPerAvgApply(recordDate, applyQuantityRanking, resultMap3, applyQuantityAvg, rangeMap3);

			} else {
				valueIsNullMethod(resultMap3);
			}
			// 批核率
			resultMap4.put("name", DescriptionConstants.D4);
			if (null != map.get("accumuApprovalRate")) {
				double accumuApprovalRate = ((java.math.BigDecimal) map.get("accumuApprovalRate")).doubleValue();
				Map<String, Object> rangeMap4 = getRangeMap(DescriptionConstants.DAPPROVALRATE, accumuApprovalRate,
						recordDate, TemplateConstants.F5, TemplateConstants.F6, TemplateConstants.F7,
						TemplateConstants.F8);
				Map<String, String> dayRange4 = getDayRange(rangeMap4);

				resultMap4.put("value", dayRange4.get("value"));
				resultMap4.put("ranking", map.get("accumuApprovalRateRanking"));
				resultMap4 = checkIsNull(dayRange4, resultMap4);
			} else {
				valueIsNullMethod(resultMap4);
			}

			// 批核件均
			resultMap5.put("name", DescriptionConstants.D5);
			if (null != map.get("accumuApprovalAverage")) {
				double accumuApprovalAverage = ((java.math.BigDecimal) map.get("accumuApprovalAverage")).doubleValue();
				Map<String, Object> rangeMap5 = getRangeMap(DescriptionConstants.DAPPROVALAVG, accumuApprovalAverage,
						recordDate, TemplateConstants.F9, TemplateConstants.F10, TemplateConstants.F11,
						TemplateConstants.F12);
				Map<String, String> dayRange5 = getDayRange(rangeMap5);
				resultMap5.put("value", dayRange5.get("value"));
				resultMap5.put("ranking", map.get("accumuApprovalAverageRanking"));
				resultMap5 = checkIsNull(dayRange5, resultMap5);
			} else {
				valueIsNullMethod(resultMap5);
			}

			dataList.add(resultMap);
			dataList.add(resultMap2);
			dataList.add(resultMap3);
			dataList.add(resultMap4);
			dataList.add(resultMap5);
			data.put("dataList", dataList);
			data.put("orgName", map.get("orgName"));
			return new ReturnMsgData("0000", "调用成功", data);
		} else {
			// 所有数据全部显示 "--"
			data = noDataOfDay(dataList, data, resultMap, resultMap2, resultMap3, resultMap4, resultMap5);
			return new ReturnMsgData("0000", "调用成功", data);
		}
	}

	/**
	 * 融资区域 月 区域列表展示
	 */
	@Override
	public ReturnMsgData financeDistrictOfMonthList(PerformanceParm performanceParm) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		String districtName = performanceParm.getAreaName();
		requestMap.put("districtName", districtName);
		String days = performanceParm.getDays();
		
		String firstDayOfLastMonth;
		String recordDate = null;
		if(!StringUtil.blank(days)){
			//得到上月第一天
		    firstDayOfLastMonth = DateUtil.getFirstDayOfLastMonth(days);
			Date convertAsDateString = DateTimeUtil.convertAsDateString(days);
			recordDate = simpleDateFormat.format(convertAsDateString);
		}else{
			// 查询month_district表中最新一条数据的时间
			List<FinanceMonthDistrict> list = financeMonthDistrictMapper.findMonthDistrictTheLatestOne();
			String firstDate = format.format(list.get(0).getRecordDate());
		    firstDayOfLastMonth = DateUtil.getFirstDayOfLastMonth(firstDate);
			Date convertAsDateString = DateTimeUtil.convertAsDateString(firstDate);
			recordDate = simpleDateFormat.format(convertAsDateString);
		}
		
		requestMap.put("recordDate", recordDate);
		Map<String, Object> map = financeMonthDistrictMapper.financeDistrictOfMonthList(requestMap);
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> resultMap2 = new HashMap<String, Object>();
		Map<String, Object> resultMap3 = new HashMap<String, Object>();
		Map<String, Object> resultMap4 = new HashMap<String, Object>();
		Map<String, Object> resultMap5 = new HashMap<String, Object>();
		Map<String, Object> resultMap6 = new HashMap<String, Object>();
		Map<String, Object> resultMap7 = new HashMap<String, Object>();
		
		//查询上月各项排名
		Map<String,Object> LastMonthMap = new HashMap<String,Object>();
		LastMonthMap.put("recordDate", firstDayOfLastMonth);
		LastMonthMap.put("districtName", districtName);
		Map<String, Object> requltMap = financeMonthDistrictMapper.findRankingOfLastMonth(LastMonthMap);
		long oldPlanYield = 0;//月计划达成率
		long oldLoanValue =0;//月放款金额
		long oldSalesNumAvg =0;//日人均进件
		long oldApprovalRate =0;//批核率
		long oldApprovalAverage =0;//批核件均
		long oldTotalEmpNumAvg =0;//全员人均产能
		long oldCounselorAvg =0;//咨询师人均产能
		if(null != requltMap){
			oldPlanYield = Long.valueOf(String.valueOf(requltMap.get("planYieldRateRanking")));
			oldLoanValue = Long.valueOf(String.valueOf(requltMap.get("loanValueRanking")));
			oldSalesNumAvg = Long.valueOf(String.valueOf(requltMap.get("salesNumAvgRanking")));
			oldApprovalRate = Long.valueOf(String.valueOf(requltMap.get("approvalRateRanking")));
		    oldApprovalAverage = Long.valueOf(String.valueOf(requltMap.get("approvalAverageRanking")));
			oldTotalEmpNumAvg = Long.valueOf(String.valueOf(requltMap.get("totalEmpNumAvgRanking")));
			oldCounselorAvg = Long.valueOf(String.valueOf(requltMap.get("counselorAvgRanking")));
		}
		if (null != map) {
			// 组装数据
			// 月计划达成率
			resultMap.put("name", DescriptionConstants.M1);
			if (null != map.get("planYieldRate")) {
				double planYieldRate = ((java.math.BigDecimal) map.get("planYieldRate")).doubleValue();
				Map<String, Object> rangeMap = getRangeMap(DescriptionConstants.MPLANYIELDRATE, planYieldRate,
						recordDate, TemplateConstants.F13, TemplateConstants.F14, "", "");
				Map<String, String> dayRange = getMonthRange(rangeMap);
				resultMap.put("value", dayRange.get("value"));
				 Object planYieldRateRanking = map.get("planYieldRateRanking");
				//判断箭头朝向
				 long newPlanYield = Long.valueOf(String.valueOf(planYieldRateRanking));
				long result = newPlanYield - oldPlanYield;
				if(null == requltMap){
					resultMap.put("rankingMarks","normal");	
				}else{
					if(result>0){
						resultMap.put("rankingMarks", "down");	
					}else if(result<0){
						resultMap.put("rankingMarks", "up");
					}else{
						resultMap.put("rankingMarks", "normal");
					}
				}
				resultMap.put("ranking", planYieldRateRanking);
				resultMap = checkIsNull(dayRange, resultMap);
			} else {
				valueIsNullMethod(resultMap);
			}

			// 月放款金额
			resultMap2.put("name", DescriptionConstants.M2);
			if (null != map.get("loanValue")) {
				resultMap2.put("value", df.format(map.get("loanValue")) + "万");
				resultMap2.put("range", "--");
				resultMap2.put("color", "normal");
				Object loanValueRanking = map.get("loanValueRanking");
				long newLoanValue = Long.valueOf(String.valueOf(loanValueRanking));
				long result = newLoanValue - oldLoanValue;
				
				if(null == requltMap){
					resultMap2.put("rankingMarks","normal");	
				}else{
					if(result>0){
						resultMap2.put("rankingMarks", "down");	
					}else if(result<0){
						resultMap2.put("rankingMarks", "up");
					}else{
						resultMap2.put("rankingMarks", "normal");
					}
				}
				resultMap2.put("ranking", loanValueRanking);
			} else {
				resultMap2.put("value", "--");
				resultMap2.put("range", "--");
				resultMap2.put("ranking", -1);
				resultMap2.put("color", "normal");
			}

			// 日人均进件
			resultMap3.put("name", DescriptionConstants.M3);
			if (null != map.get("salesNumAvg")) {
				double salesNumAvg = ((java.math.BigDecimal) map.get("salesNumAvg")).doubleValue();
				Map<String, Object> rangeMap3 = getRangeMap(DescriptionConstants.DPEOPLEAVG, salesNumAvg, recordDate,
						TemplateConstants.F3, TemplateConstants.F4, "", "");
				long salesNumAvgRanking = (long) map.get("salesNumAvgRanking");
				long result = salesNumAvgRanking - oldSalesNumAvg;
				if(null == requltMap){
					resultMap3.put("rankingMarks","normal");	
				}else{
					if(result>0){
						resultMap3.put("rankingMarks", "down");	
					}else if(result<0){
						resultMap3.put("rankingMarks", "up");
					}else{
						resultMap3.put("rankingMarks", "normal");
					}
				}
				
				getPerAvgApply(recordDate, salesNumAvgRanking, resultMap3, salesNumAvg, rangeMap3);
			} else {
				valueIsNullMethod(resultMap3);
			}

			// 批核率
			resultMap4.put("name", DescriptionConstants.M4);
			if (null != map.get("approvalRate")) {
				double approvalRate = ((java.math.BigDecimal) map.get("approvalRate")).doubleValue();
				Map<String, Object> rangeMap4 = getRangeMap(DescriptionConstants.MAPPROVALRATE, approvalRate, "",
						TemplateConstants.F23, TemplateConstants.F24, TemplateConstants.F25, TemplateConstants.F26);
				Map<String, String> dayRange4 = getMonthRange(rangeMap4);
				resultMap4.put("value", dayRange4.get("value"));
				Object approvalRateRanking = map.get("approvalRateRanking");
				long newApprovalRate = Long.valueOf(String.valueOf(approvalRateRanking));
				long result = newApprovalRate - oldApprovalRate;
				
				if(null == requltMap){
					resultMap4.put("rankingMarks","normal");	
				}else{
					if(result>0){
						resultMap4.put("rankingMarks", "down");	
					}else if(result<0){
						resultMap4.put("rankingMarks", "up");
					}else{
						resultMap4.put("rankingMarks", "normal");
					}
				}
				resultMap4.put("ranking",approvalRateRanking);
				resultMap4 = checkIsNull(dayRange4, resultMap4);
			} else {
				valueIsNullMethod(resultMap4);
			}

			// 批核件均
			resultMap5.put("name", DescriptionConstants.M5);
			if (null != map.get("approvalAverage")) {
				double approvalAverage = ((java.math.BigDecimal) map.get("approvalAverage")).doubleValue();
				Map<String, Object> rangeMap5 = getRangeMap(DescriptionConstants.MAPPROVALAVG, approvalAverage, "",
						TemplateConstants.F27, TemplateConstants.F28, TemplateConstants.F29, TemplateConstants.F30);
				Map<String, String> dayRange5 = getMonthRange(rangeMap5);
				resultMap5.put("value", dayRange5.get("value"));
				Object approvalAverageRanking = map.get("approvalAverageRanking");
				long newApprovalAverage = Long.valueOf(String.valueOf(approvalAverageRanking));
				long result = newApprovalAverage - oldApprovalAverage;
				

				if(null == requltMap){
					resultMap5.put("rankingMarks","normal");	
				}else{
					if(result>0){
						resultMap5.put("rankingMarks", "down");	
					}else if(result<0){
						resultMap5.put("rankingMarks", "up");
					}else{
						resultMap5.put("rankingMarks", "normal");
					}
				}
				
				resultMap5.put("ranking", approvalAverageRanking);
				resultMap5 = checkIsNull(dayRange5, resultMap5);
			} else {
				valueIsNullMethod(resultMap5);
			}

			// 全员人均产能
			resultMap6.put("name", DescriptionConstants.M6);
			if (null != map.get("totalEmpNumAvg")) {
				double totalEmpNumAvg = ((java.math.BigDecimal) map.get("totalEmpNumAvg")).doubleValue();
				Map<String, Object> rangeMap6 = getRangeMap(DescriptionConstants.DAPPROVALAVG, totalEmpNumAvg, "",
						TemplateConstants.F19, TemplateConstants.F20, "", "");
				Map<String, String> dayRange6 = getMonthRange(rangeMap6);
				resultMap6.put("value", dayRange6.get("value"));
				Object totalEmpNumAvgRanking = map.get("totalEmpNumAvgRanking");
				long newTotalEmpNumAvg = Long.valueOf(String.valueOf(totalEmpNumAvgRanking));
				long result = newTotalEmpNumAvg - oldTotalEmpNumAvg;
				
				if(null == requltMap){
					resultMap6.put("rankingMarks","normal");	
				}else{
					if(result>0){
						resultMap6.put("rankingMarks", "down");	
					}else if(result<0){
						resultMap6.put("rankingMarks", "up");
					}else{
						resultMap6.put("rankingMarks", "normal");
					}
				}
				
				resultMap6.put("ranking",totalEmpNumAvgRanking);
				resultMap6 = checkIsNull(dayRange6, resultMap6);
			} else {
				valueIsNullMethod(resultMap6);
			}

			// 咨询师人均产能
			resultMap7.put("name", DescriptionConstants.M7);
			if (null != map.get("counselorAvg")) {
				double counselorAvg = ((java.math.BigDecimal) map.get("counselorAvg")).doubleValue();
				Map<String, Object> rangeMap7 = getRangeMap(DescriptionConstants.DAPPROVALAVG, counselorAvg, "",
						TemplateConstants.F21, TemplateConstants.F22, TemplateConstants.F29, TemplateConstants.F30);
				Map<String, String> dayRange7 = getMonthRange(rangeMap7);
				resultMap7.put("value", dayRange7.get("value"));
				Object totalEmpNumAvgRanking = map.get("totalEmpNumAvgRanking");
				long newTotalEmpNumAvg = Long.valueOf(String.valueOf(totalEmpNumAvgRanking));
				long result = newTotalEmpNumAvg - oldCounselorAvg;
				if(null == requltMap){
					resultMap7.put("rankingMarks","normal");	
				}else{
					if(result>0){
						resultMap7.put("rankingMarks", "down");	
					}else if(result<0){
						resultMap7.put("rankingMarks", "up");
					}else{
						resultMap7.put("rankingMarks", "normal");
					}
				}
				
				resultMap7.put("ranking", totalEmpNumAvgRanking);
				resultMap7 = checkIsNull(dayRange7, resultMap7);
			} else {
				valueIsNullMethod(resultMap7);
			}

			dataList.add(resultMap);
			dataList.add(resultMap2);
			dataList.add(resultMap3);
			dataList.add(resultMap4);
			dataList.add(resultMap5);
			dataList.add(resultMap6);
			dataList.add(resultMap7);
			String[] split = recordDate.split("-");
			data.put("year", split[0]);
			data.put("month", split[1]);
			data.put("dataList", dataList);
			data.put("districtName", map.get("districtName"));
			return new ReturnMsgData("0000", "调用成功", data);
		}
		
		//有的区域 没有数据
		String[] split = recordDate.split("-");
		data.put("year", split[0]);
		data.put("month", split[1]);
		resultMap.put("rankingMarks", "");
		resultMap2.put("rankingMarks", "");
		resultMap3.put("rankingMarks", "");
		resultMap4.put("rankingMarks", "");
		resultMap5.put("rankingMarks", "");
		resultMap6.put("rankingMarks", "");
		resultMap7.put("rankingMarks", "");
		data = noDataOfMonth(dataList, data, resultMap, resultMap2, resultMap3, resultMap4, resultMap5,resultMap6,resultMap7);
		return new ReturnMsgData("0000", "调用成功", data);
	}


	/**
	 * 融资区域 月 营业部列表展示
	 */
	@Override
	public ReturnMsgData financeOrgNameOfMonthList(PerformanceParm performanceParm) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		String recordDate;
		String orgName = performanceParm.getOrgName();
		requestMap.put("orgName", orgName);
		String days = performanceParm.getDays();
         if(!StringUtil.blank(days)){
        	 Date convertAsDateString = DateTimeUtil.convertAsDateString(days);
        	 recordDate = simpleDateFormat.format(convertAsDateString);
         }else{
        	// 查询month_org表中最新一条数据的时间
     		List<FinanceMonthOrg> list = financeMonthOrgMapper.findMonthOrgTheLatestOne();
     		String firstDate = format.format(list.get(0).getRecordDate());
     		Date convertAsDateString = DateTimeUtil.convertAsDateString(firstDate);
     		recordDate = simpleDateFormat.format(convertAsDateString);
         }
		
		requestMap.put("recordDate", recordDate);
		Map<String, Object> map = financeMonthOrgMapper.financeOrgNameOfMonthList(requestMap);
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> resultMap2 = new HashMap<String, Object>();
		Map<String, Object> resultMap3 = new HashMap<String, Object>();
		Map<String, Object> resultMap4 = new HashMap<String, Object>();
		Map<String, Object> resultMap5 = new HashMap<String, Object>();
		Map<String, Object> resultMap6 = new HashMap<String, Object>();
		Map<String, Object> resultMap7 = new HashMap<String, Object>();
		if (null != map) {
			// 组装数据
			// 月计划达成率
			resultMap.put("name", DescriptionConstants.M1);
			if (null != map.get("planYieldRate")) {
				double planYieldRate = ((java.math.BigDecimal) map.get("planYieldRate")).doubleValue();
				Map<String, Object> rangeMap = getRangeMap(DescriptionConstants.MPLANYIELDRATE, planYieldRate,
						recordDate, TemplateConstants.F13, TemplateConstants.F14, "", "");
				Map<String, String> dayRange = getMonthRange(rangeMap);
				resultMap.put("value", dayRange.get("value"));
				resultMap.put("ranking", map.get("planYieldRateRanking"));
				resultMap = checkIsNull(dayRange, resultMap);
			} else {
				valueIsNullMethod(resultMap);
			}

			// 月放款金额
			resultMap2.put("name", DescriptionConstants.M2);
			if (null != map.get("loanValue")) {
				resultMap2.put("value", df.format(map.get("loanValue")) + "万");
				resultMap2.put("range", "-");
				resultMap2.put("color", "normal");
				resultMap2.put("ranking", map.get("loanValueRanking"));
			} else {
				resultMap2.put("value", "--");
				resultMap2.put("range", "--");
				resultMap2.put("ranking", -1);
				resultMap2.put("color", "normal");
			}

			// 日人均进件
			resultMap3.put("name", DescriptionConstants.M3);
			if (null != map.get("salesNumAvg")) {
				double salesNumAvg = ((java.math.BigDecimal) map.get("salesNumAvg")).doubleValue();
				Map<String, Object> rangeMap3 = getRangeMap(DescriptionConstants.DPEOPLEAVG, salesNumAvg, recordDate,
						TemplateConstants.F3, TemplateConstants.F4, "", "");
				long salesNumAvgRanking = (long) map.get("salesNumAvgRanking");
				getPerAvgApply(recordDate, salesNumAvgRanking, resultMap3, salesNumAvg, rangeMap3);
			} else {
				valueIsNullMethod(resultMap3);
			}

			// 批核率
			resultMap4.put("name", DescriptionConstants.M4);
			if (null != map.get("approvalRate")) {
				double approvalRate = ((java.math.BigDecimal) map.get("approvalRate")).doubleValue();
				Map<String, Object> rangeMap4 = getRangeMap(DescriptionConstants.MAPPROVALRATE, approvalRate, "",
						TemplateConstants.F23, TemplateConstants.F24, TemplateConstants.F25, TemplateConstants.F26);
				Map<String, String> dayRange4 = getMonthRange(rangeMap4);
				resultMap4.put("value", dayRange4.get("value"));
				resultMap4.put("ranking", map.get("approvalRateRanking"));
				resultMap4 = checkIsNull(dayRange4, resultMap4);
			} else {
				valueIsNullMethod(resultMap4);
			}

			// 批核件均
			resultMap5.put("name", DescriptionConstants.M5);
			if (null != map.get("approvalAverage")) {
				double approvalAverage = ((java.math.BigDecimal) map.get("approvalAverage")).doubleValue();
				Map<String, Object> rangeMap5 = getRangeMap(DescriptionConstants.MAPPROVALAVG, approvalAverage, "",
						TemplateConstants.F27, TemplateConstants.F28, TemplateConstants.F29, TemplateConstants.F30);
				Map<String, String> dayRange5 = getMonthRange(rangeMap5);
				resultMap5.put("value", dayRange5.get("value"));
				resultMap5.put("ranking", map.get("approvalAverageRanking"));
				resultMap5 = checkIsNull(dayRange5, resultMap5);
			} else {
				valueIsNullMethod(resultMap5);
			}

			// 全员人均产能
			resultMap6.put("name", DescriptionConstants.M6);
			if (null != map.get("totalEmpNumAvg")) {
				double totalEmpNumAvg = ((java.math.BigDecimal) map.get("totalEmpNumAvg")).doubleValue();
				Map<String, Object> rangeMap6 = getRangeMap(DescriptionConstants.DAPPROVALAVG, totalEmpNumAvg, "",
						TemplateConstants.F19, TemplateConstants.F20, "", "");
				Map<String, String> dayRange6 = getMonthRange(rangeMap6);
				resultMap6.put("value", dayRange6.get("value"));
				resultMap6.put("ranking", map.get("totalEmpNumAvgRanking"));
				resultMap6 = checkIsNull(dayRange6, resultMap6);
			} else {
				valueIsNullMethod(resultMap6);
			}

			// 咨询师人均产能
			resultMap7.put("name", DescriptionConstants.M7);
			if (null != map.get("counselorAvg")) {
				double counselorAvg = ((java.math.BigDecimal) map.get("counselorAvg")).doubleValue();
				Map<String, Object> rangeMap7 = getRangeMap(DescriptionConstants.DAPPROVALAVG, counselorAvg, "",
						TemplateConstants.F21, TemplateConstants.F22, TemplateConstants.F29, TemplateConstants.F30);
				Map<String, String> dayRange7 = getMonthRange(rangeMap7);
				resultMap7.put("value", dayRange7.get("value"));
				resultMap7.put("ranking", map.get("totalEmpNumAvgRanking"));
				resultMap7 = checkIsNull(dayRange7, resultMap7);
			} else {
				valueIsNullMethod(resultMap7);
			}

			dataList.add(resultMap);
			dataList.add(resultMap2);
			dataList.add(resultMap3);
			dataList.add(resultMap4);
			dataList.add(resultMap5);
			dataList.add(resultMap6);
			dataList.add(resultMap7);
			String[] split = recordDate.split("-");
			data.put("year", split[0]);
			data.put("month", split[1]);
			data.put("dataList", dataList);
			data.put("orgName", map.get("orgName"));
			return new ReturnMsgData("0000", "调用成功", data);
		}
		//有的营业部没有数据，返回空列表
		String[] split = recordDate.split("-");
		data.put("year", split[0]);
		data.put("month", split[1]);
		data = noDataOfMonth(dataList, data, resultMap, resultMap2, resultMap3, resultMap4, resultMap5, resultMap6,
				resultMap7);
		return new ReturnMsgData("0000", "调用成功", data);
			
	}

	/**
	 * 融资人员 汇总
	 */
	@Override
	public ReturnMsgData financePersonnelSum(PerformanceParm performanceParm) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		String recordDate;
		// 查询f_finance_personal_info表中最新一条数据的时间
		List<FinancePersonalInfo> list = financePersonalInfoMapper.findTheLatestOne();
		recordDate = simpleDateFormat.format(list.get(0).getRecordDate());
		requestMap.put("recordDate", recordDate);
		// 查询汇总信息
		data = financePersonalInfoMapper.findCountByDays(requestMap);
		List<Map<String, Object>> sumList = financePersonalInfoMapper.financePersonnelSum(requestMap);
		// 元数据
		Map<String, Object> map = sumList.get(0);
		// 环比数据
		Map<String, Object> map2 = sumList.get(1);
		// 同比数据
		Map<String, Object> map3 = sumList.get(2);

		// 大区个数环比
		long districtCountValue = Long.parseLong(String.valueOf(map2.get("districtCount")));
		if (0 != districtCountValue) {
			BigDecimal mapDistrictCount = new BigDecimal(String.valueOf((map.get("districtCount"))));
			BigDecimal map2DistrictCount = new BigDecimal(String.valueOf((map2.get("districtCount"))));

			BigDecimal districtCount = mapDistrictCount.subtract(map2DistrictCount);
			
			if (districtCount.compareTo(new BigDecimal(0)) == 0) {
				data.put("districtLinkRelativeRatio", "持平");
				data.put("districtLinkRelativeRatioColor", "normal");
			} else {
				BigDecimal districtCountYearOnYear = districtCount.divide(map2DistrictCount, 2,BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
				//判断返回什么颜色
				if(districtCountYearOnYear.compareTo(new BigDecimal(0)) == 1){
					data.put("districtLinkRelativeRatioColor", "green");
				}else if(districtCountYearOnYear.compareTo(new BigDecimal(0)) == -1){
					data.put("districtLinkRelativeRatioColor", "red");
				}else{
					data.put("districtLinkRelativeRatioColor", "normal");
				}
				data.put("districtLinkRelativeRatio", districtCountYearOnYear + "%");
			}
		} else {
			data.put("districtLinkRelativeRatioColor", "normal");
			data.put("districtLinkRelativeRatio", "--");
		}

		// 营业部个数环比
		long orgNameCountValue = Long.parseLong(String.valueOf(map2.get("orgNameCount")));
		if (0 != orgNameCountValue) {
			BigDecimal mapOrgNameCount = new BigDecimal(String.valueOf((map.get("orgNameCount"))));
			BigDecimal map2OrgNameCount = new BigDecimal(String.valueOf((map2.get("orgNameCount"))));
			BigDecimal orgNameCount =mapOrgNameCount.subtract(map2OrgNameCount);
			
			if (orgNameCount.compareTo(new BigDecimal(0)) == 0) {
				data.put("orgNameLinkRelativeRatio", "持平");
				data.put("orgNameLinkRelativeRatioColor", "normal");
			} else {
				BigDecimal orgNameCountYearOnYear = orgNameCount.divide(map2OrgNameCount, 2, BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
				
				//判断返回什么颜色
				if(orgNameCountYearOnYear.compareTo(new BigDecimal(0)) == 1){
					data.put("orgNameLinkRelativeRatioColor", "green");
					
				}else if(orgNameCountYearOnYear.compareTo(new BigDecimal(0)) == -1){
					
					data.put("orgNameLinkRelativeRatioColor", "red");
					
				}else{
					data.put("orgNameLinkRelativeRatioColor", "normal");
				}
				data.put("orgNameLinkRelativeRatio", orgNameCountYearOnYear + "%");
			}
		} else {
			data.put("orgNameLinkRelativeRatio", "--");
			data.put("orgNameLinkRelativeRatioColor", "normal");
		}

		// 团队个数环比
		Object object = map2.get("teamCount");
		if (null != object) {
			BigDecimal mapTeamCount = (BigDecimal) map.get("teamCount");
			BigDecimal map2TeamCount = (BigDecimal)map2.get("teamCount");
			
			BigDecimal teamCount = mapTeamCount.subtract(map2TeamCount);
			if (teamCount.compareTo(new BigDecimal(0)) == 0) {
				data.put("teamLinkRelativeRatio", "持平");
				data.put("teamLinkRelativeRatioColor", "normal");
			} else {
				BigDecimal teamCountYearOnYear = teamCount.divide(map2TeamCount, 2, BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
				
				//判断返回什么颜色
				if(teamCountYearOnYear.compareTo(new BigDecimal(0)) == 1){
					data.put("teamLinkRelativeRatioColor", "green");
				}else if(teamCountYearOnYear.compareTo(new BigDecimal(0)) == -1){
					data.put("teamLinkRelativeRatioColor", "red");
				}else{
					data.put("teamLinkRelativeRatioColor", "normal");
				}
				
				data.put("teamLinkRelativeRatio", teamCountYearOnYear + "%");
			}
		} else {
			data.put("teamLinkRelativeRatio", "--");
			data.put("teamLinkRelativeRatioColor", "normal");
		}

		// 咨询师人数环比
		Object mapSalesCountValue = map.get("salesCount");
		Object map2SalesCountValue= map2.get("salesCount");
		if (null != mapSalesCountValue && null != map2SalesCountValue) {
			BigDecimal mapSalesCount = (BigDecimal) map.get("salesCount");
			BigDecimal map2SalesCount = (BigDecimal)map2.get("salesCount");
			BigDecimal salesCount = mapSalesCount.subtract(map2SalesCount);
			if (salesCount.compareTo(new BigDecimal(0)) == 0) {
				data.put("salesLinkRelativeRatio", "持平");
				data.put("salesLinkRelativeRatioColor", "normal");
			} else {
				BigDecimal salesLinkRelativeRatio = salesCount.divide(map2SalesCount, 2, BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
				//判断返回什么颜色
				if(salesLinkRelativeRatio.compareTo(new BigDecimal(0)) == 1){
					data.put("salesLinkRelativeRatioColor", "green");
				}else if(salesLinkRelativeRatio.compareTo(new BigDecimal(0)) == -1){
					data.put("salesLinkRelativeRatioColor", "red");
				}else{
					data.put("salesLinkRelativeRatioColor", "normal");
				}
				
				data.put("salesLinkRelativeRatio", salesLinkRelativeRatio + "%");
			}
		} else {
			data.put("salesLinkRelativeRatio", "--");
			data.put("salesLinkRelativeRatioColor", "normal");
		}

		// 大区个数同比
		long districtCountValueY = Long.parseLong(String.valueOf(map3.get("districtCount")));
		if (0 != districtCountValueY) {
			BigDecimal mapDistrictCount = new BigDecimal(String.valueOf((map.get("districtCount"))));
			BigDecimal map3DistrictCount = new BigDecimal(String.valueOf((map3.get("districtCount"))));
			BigDecimal districtCount = mapDistrictCount.subtract(map3DistrictCount);
					
			if (districtCount.compareTo(new BigDecimal(0)) == 0) {
				data.put("districtYearOnYear", "持平");
				data.put("districtYearOnYearColor", "normal");
			} else {
				BigDecimal districtCountYearOnYear = districtCount.divide(map3DistrictCount, 2,BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
				//判断返回什么颜色
				if(districtCountYearOnYear.compareTo(new BigDecimal(0)) == 1){
					data.put("districtYearOnYearColor", "green");
				}else if(districtCountYearOnYear.compareTo(new BigDecimal(0)) == -1){
					data.put("districtYearOnYearColor", "red");
				}else{
					data.put("districtYearOnYearColor", "normal");
				}
				data.put("districtYearOnYear", districtCountYearOnYear + "%");
			}
		} else {
			data.put("districtYearOnYear", "--");
			data.put("districtYearOnYearColor", "normal");
		}

		// 营业部个数同比
		long orgNameCountValueY = Long.parseLong(String.valueOf(map3.get("orgNameCount")));
		if (0 != orgNameCountValueY) {
			BigDecimal mapOrgNameCount = new BigDecimal(String.valueOf((map.get("orgNameCount"))));
			BigDecimal map3OrgNameCount = new BigDecimal(String.valueOf((map3.get("orgNameCount"))));
			
			BigDecimal orgNameCount = mapOrgNameCount.subtract(map3OrgNameCount);
			if (orgNameCount.compareTo(new BigDecimal(0)) == 0) {
				data.put("orgNameYearOnYear", "持平");
				data.put("orgNameYearOnYearColor", "normal");
			} else {
				BigDecimal orgNameCountYearOnYear = orgNameCount.divide(map3OrgNameCount, 2, BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
				//判断返回什么颜色
				if(orgNameCountYearOnYear.compareTo(new BigDecimal(0)) == 1){
					data.put("orgNameYearOnYearColor", "green");
				}else if(orgNameCountYearOnYear.compareTo(new BigDecimal(0)) == -1){
					data.put("orgNameYearOnYearColor", "red");
				}else{
					data.put("orgNameYearOnYearColor", "normal");
				}
				
				data.put("orgNameYearOnYear", orgNameCountYearOnYear + "%");
			}
		} else {
			data.put("orgNameYearOnYear", "--");
			data.put("orgNameYearOnYearColor", "normal");
		}

		// 团队个数同比
		Object map3TeamCountY = map3.get("teamCount");
		Object mapTeamCountValueY = map.get("teamCount");
		if (null != mapTeamCountValueY && null != map3TeamCountY ) {
			BigDecimal mapTeamCount = (BigDecimal) map.get("teamCount");
			BigDecimal map3TeamCount = (BigDecimal)map3.get("teamCount");
			
			BigDecimal teamCount = mapTeamCount.subtract(map3TeamCount);
			if (teamCount.compareTo(new BigDecimal(0)) == 0) {
				data.put("teamYearOnYear", "持平");
				data.put("teamYearOnYearColor", "normal");
			} else {
				BigDecimal teamCountYearOnYear = teamCount.divide(map3TeamCount, 2, BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
				//判断返回什么颜色
				if(teamCountYearOnYear.compareTo(new BigDecimal(0)) == 1){
					data.put("teamYearOnYearColor", "green");
				}else if(teamCountYearOnYear.compareTo(new BigDecimal(0)) == -1){
					data.put("teamYearOnYearColor", "red");
				}else{
					data.put("teamYearOnYearColor", "normal");
				}
				
				data.put("teamYearOnYear", teamCountYearOnYear + "%");
			}
		} else {
			data.put("teamYearOnYear", "--");
			data.put("teamYearOnYearColor", "normal");
			
		}

		// 咨询师人数同比
		Object mapSalesCountValueY = map.get("salesCount");
		Object map3SalesCountValueY = map3.get("salesCount");
		if (null != mapSalesCountValueY && null !=map3SalesCountValueY ) {
			BigDecimal mapSalesCount = (BigDecimal) map.get("salesCount");
			BigDecimal map3SalesCount = (BigDecimal)map3.get("salesCount");
			
			BigDecimal salesCount =mapSalesCount.subtract(map3SalesCount);
			if (salesCount.compareTo(new BigDecimal(0)) == 0) {
				data.put("salesYearOnYear", "持平");
				data.put("salesYearOnYearColor", "normal");
			} else {
				BigDecimal salesCountYearOnYear = salesCount.divide(map3SalesCount, 2, BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP);
				//判断返回什么颜色
				if(salesCountYearOnYear.compareTo(new BigDecimal(0)) == 1){
					data.put("salesYearOnYearColor", "green");
				}else if(salesCountYearOnYear.compareTo(new BigDecimal(0)) == -1){
					data.put("salesYearOnYearColor", "red");
				}else{
					data.put("salesYearOnYearColor", "normal");
				}
				
				data.put("salesYearOnYear", salesCountYearOnYear + "%");
			}
		} else {
			data.put("salesYearOnYear", "--");
			data.put("salesYearOnYearColor", "normal");
		}

		String[] split = recordDate.split("-");
		data.put("year", split[0]);
		data.put("month", split[1]);
		return new ReturnMsgData("0000", "调用成功", data);

	}

	/**
	 * 得到月区间
	 */
	public Map<String, String> getMonthRange(Map<String, Object> map) {
		Map<String, String> responseMap = new HashMap<String, String>();
		String description = (String) map.get("description");
		double value = (double) map.get("value");
		String str = "";
		if (description.equals(DescriptionConstants.MPLANYIELDRATE)
				|| description.equals(DescriptionConstants.MAPPROVALRATE)) {
			str = "%";
		} else if (description.equals(DescriptionConstants.MTOTALPERAVGCAPITA)
				|| description.equals(DescriptionConstants.MCTASAVGCAPITA)
				|| description.equals(DescriptionConstants.MAPPROVALAVG)) {
			str = "万";
		}

		List<Index> list = indexMapper.getValueByCode(map);
		if (list.size() == 4) {
			responseMap = getMonthRangeByFourValue(description, list, value, str);
		}
		if (list.size() == 2) {
			responseMap = getMonthRangeByTwoValue(description, list, value, str);
		}
		responseMap.put("value", df.format(value) + str);
		return responseMap;
	}

	/**
	 * 得到日区间
	 */
	@Override
	public Map<String, String> getDayRange(Map<String, Object> map) {
		String description = (String) map.get("description");
		double value = (double) map.get("value");
		Map<String, String> responseMap = new HashMap<String, String>();
		List<FinanceDailyPage> list = financeDailyPageMapper.getValueByCodeAndDate(map);
		String str = "";
		if (description.equals(DescriptionConstants.DAPPROVALRATE)
				|| description.equals(DescriptionConstants.DPLANYIELDRATE)) {
			str = "%";
		} else if (description.equals(DescriptionConstants.DAPPROVALAVG)) {
			str = "万";
		}
		if (list.size() == 4) {
			responseMap = getDayRangeByFourValue(description, list, value, str);
		}
		if (list.size() == 2) {
			responseMap = getDayRangeByTwoValue(description, list, value, str);
		}
		responseMap.put("value", df.format(value) + str);
		return responseMap;
	}

	private Map<String, String> getDayRangeByTwoValue(String description, List<FinanceDailyPage> list, double value,
			String str) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String resultStr;
		String color;
		if (value < list.get(0).getDataFigureValue().doubleValue()) {
			resultStr = "<" + df.format(list.get(0).getDataFigureValue().doubleValue()) + str;
			color = "red";
		} else if (value >= list.get(0).getDataFigureValue().doubleValue()
				&& value <= list.get(1).getDataFigureValue().doubleValue()) {

			resultStr = df.format(list.get(0).getDataFigureValue().doubleValue()) + str + "~"
					+ df.format(list.get(1).getDataFigureValue().doubleValue()) + str;
			color = "yellow";
		} else if (value > list.get(1).getDataFigureValue().doubleValue()) {

			resultStr = ">" + df.format(list.get(1).getDataFigureValue().doubleValue()) + str;
			color = "green";

		} else {

			return null;
		}
		resultMap.put("range", resultStr);
		resultMap.put("color", color);
		return resultMap;
	}

	private Map<String, String> getDayRangeByFourValue(String description, List<FinanceDailyPage> list, double value,
			String str) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String resultStr = "";
		String color;
		if (value < list.get(0).getDataFigureValue().doubleValue()) {
			resultStr = "<" + df.format(list.get(0).getDataFigureValue().doubleValue()) + str;
			color = "red";
		} else if (value >= list.get(0).getDataFigureValue().doubleValue()
				&& value <= list.get(1).getDataFigureValue().doubleValue()) {
			resultStr = df.format(list.get(0).getDataFigureValue().doubleValue()) + str + "~"
					+ df.format(list.get(1).getDataFigureValue().doubleValue()) + str;
			color = "yellow";
		} else if (value > list.get(1).getDataFigureValue().doubleValue()
				&& value <= list.get(2).getDataFigureValue().doubleValue()) {
			resultStr = df.format(list.get(1).getDataFigureValue().doubleValue()) + str + "~"
					+ df.format(list.get(2).getDataFigureValue().doubleValue()) + str;
			color = "green";
		} else if (value > list.get(2).getDataFigureValue().doubleValue()
				&& value <= list.get(3).getDataFigureValue().doubleValue()) {
			resultStr = df.format(list.get(2).getDataFigureValue().doubleValue()) + str + "~"
					+ df.format(list.get(3).getDataFigureValue().doubleValue()) + str;
			color = "yellow";
		} else if (value > list.get(3).getDataFigureValue().doubleValue()) {
			resultStr = ">" + df.format(list.get(3).getDataFigureValue().doubleValue()) + str;
			color = "red";
		} else {
			return null;
		}
		resultMap.put("range", resultStr);
		resultMap.put("color", color);
		return resultMap;
	}

	private Map<String, String> getMonthRangeByTwoValue(String description, List<Index> list, double value,
			String str) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String resultStr;
		String color;
		if (value < list.get(0).getValue()) {
			resultStr = "<" + df.format(list.get(0).getValue()) + str;
			color = "red";
		} else if (value >= list.get(0).getValue() && value <= list.get(1).getValue()) {

			resultStr = df.format(list.get(0).getValue()) + str + "~" + df.format(list.get(1).getValue()) + str;

			color = "yellow";
		} else if (value > list.get(1).getValue()) {

			resultStr = ">" + df.format(list.get(1).getValue()) + str;
			color = "green";
		} else {
			return null;
		}
		resultMap.put("range", resultStr);
		resultMap.put("color", color);
		return resultMap;
	}

	private Map<String, String> getMonthRangeByFourValue(String description, List<Index> list, double value,
			String str) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String resultStr;
		String color;
		if (value < list.get(0).getValue()) {
			resultStr = "<" + df.format(list.get(0).getValue()) + str;
			color = "red";
		} else if (value >= list.get(0).getValue() && value <= list.get(1).getValue()) {

			resultStr = df.format(list.get(0).getValue()) + str + "~" + df.format(list.get(1).getValue()) + str;
			color = "yellow";
		} else if (value > list.get(1).getValue() && value <= list.get(2).getValue()) {

			resultStr = df.format(list.get(1).getValue()) + str + "~" + df.format(list.get(2).getValue()) + str;
			color = "green";
		} else if (value > list.get(2).getValue() && value <= list.get(3).getValue()) {

			resultStr = df.format(list.get(2).getValue()) + str + "~" + df.format(list.get(3).getValue()) + str;
			color = "yellow";
		} else if (value > list.get(3).getValue()) {
			resultStr = ">" + df.format(list.get(3).getValue()) + str;
			color = "red";
		} else {
			return null;
		}
		resultMap.put("range", resultStr);
		resultMap.put("color", color);
		return resultMap;
	}

	/**
	 * 融资日人员人效
	 * 
	 * @param days
	 * @return
	 * @author 东东
	 */
	@Override
	public ReturnMsgData financeDailyPeople(String days) {
		/**
		 * 1.当日人均进件
		 */
		Map<String, Object> map = financeDailyPerformanceMapper.selectAvgDailyPerEntry(days);
		BigDecimal notNull = new BigDecimal(-1);
		BigDecimal avgPerEntry = notNull;
		if (null != map) {
			avgPerEntry = (BigDecimal) map.get("avgPerEntry");
		}
		/**
		 * 2.日人均进件目标 financeMonthPerformanceMapper
		 */
		Map<String, Object> avgPerEntryPlanMap = getDayAvgPerPlan(days);
		BigDecimal avgPerEntryPlan = notNull;
		if (null != avgPerEntryPlanMap) {
			avgPerEntryPlan = (BigDecimal) avgPerEntryPlanMap.get("avgPerEntryPlan");
		}
		/**
		 * 3.当月日人均进件
		 */
		List<Map<String, Object>> monthAvgPerEntrylist = financeDailyPerformanceMapper.selectMonthAvgPerEntry(days);
		/**
		 * 4.查询指标
		 */
		Map<String, Object> wellIndexMap = new HashMap<String, Object>();
		wellIndexMap.put("dataName", "financeMgr_daily_perCapitaEntry_well");
		wellIndexMap.put("days", days);
		BigDecimal wellIndex = financeDailyPageMapper.selectByDataName(wellIndexMap);
		if (wellIndex == null) {
			wellIndex = new BigDecimal(-1);
		}
		Map<String, Object> waringIndexMap = new HashMap<String, Object>();
		waringIndexMap.put("dataName", "financeMgr_daily_perCapitaEntry_warning");
		waringIndexMap.put("days", days);
		BigDecimal waringIndex = financeDailyPageMapper.selectByDataName(waringIndexMap);
		if (waringIndex == null) {
			waringIndex = new BigDecimal(-1);
		}
		/**
		 * 5.计算警戒线
		 */
		BigDecimal wellLine = notNull;
		BigDecimal waringLine = notNull;
		BigDecimal flag = notNull;
		if(avgPerEntryPlan != flag){
			Map<String, Object> wellMap = new HashMap<>();
			wellMap.put("days", days);
			wellMap.put("code", "financeMgr_daily_perCapitaEntry_well");
			wellMap.put("plan", avgPerEntryPlan);
			wellLine = financeDailyPageMapper.selectIndex(wellMap);
			if (wellLine == null) {
				wellLine = new BigDecimal(-1);
			}
			Map<String, Object> waringMap = new HashMap<>();
			waringMap.put("days", days);
			waringMap.put("code", "financeMgr_daily_perCapitaEntry_warning");
			waringMap.put("plan", avgPerEntryPlan);
			waringLine = financeDailyPageMapper.selectIndex(waringMap);
			if (waringLine == null) {
				waringLine = new BigDecimal(-1);
			}
		}
		/**
		 * 6.组装返回参数
		 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("avgPerEntry", avgPerEntry);
		data.put("wellIndex", wellIndex);
		data.put("waringIndex", waringIndex);
		data.put("wellLine", wellLine);
		data.put("waringLine", waringLine);
		data.put("monthAvgPerEntrylist", monthAvgPerEntrylist);
		return new ReturnMsgData("0000", "调用成功", data);
	}

	/**
	 * 得到 日人均进件目标
	 * 
	 * @param days
	 * @return
	 */
	public Map<String, Object> getDayAvgPerPlan(String days) {
		int thisMonthDays = DateTimeUtil.getThisMonthDays();
		String firstDayOfLastMonth = DateTimeUtil.getFirstDayOfMonthChina(days);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("thisMonthDays", thisMonthDays);
		params.put("firstDayOfLastMonth", firstDayOfLastMonth);
		params.put("days", days);
		Map<String, Object> avgPerEntryPlanMap = financeDailyPerformanceMapper.selectAvgDailyPerEntryPlan(params);
		return avgPerEntryPlanMap;
	}

	/**
	 * 融资月人员人效
	 * 
	 * @author 东东
	 */
	@Override
	public ReturnMsgData financeMonthPeople() {
		/**
		 * 1.日人均进件
		 */
		List<Map<String, Object>> avgMonthPerList = financePersonalInfoMapper.SelectAvgMonthPerEntry();
		String beginDate = "--年--月";
		String endDate = "--年--月";
		if (avgMonthPerList == null || avgMonthPerList.size() == 0) {
			logger.info("日人均进件数据为空");
		} else {
			Map<String, Object> mapStart = avgMonthPerList.get(avgMonthPerList.size() - 1);
			Map<String, Object> mapEnd = avgMonthPerList.get(0);
			endDate = (String) mapStart.get("yearDate");
			beginDate = (String) mapEnd.get("yearDate");

		}
		Map<String, Object> daily = new HashMap<>();
		daily.put("avgMonthPerList", avgMonthPerList);
		daily.put("beginDate", beginDate);
		daily.put("endDate", endDate);
		/**
		 * 2.全员人均产能
		 */
		List<Map<String, Object>> allAvgPerList = financePersonalInfoMapper.selectAllAvgPer();
		String allEndDate =  "--年--月";
		String allStartDate =  "--年--月";
		if (allAvgPerList == null || allAvgPerList.size() == 0) {
			logger.info("全员人均产能为空");
		} else {
			Map<String, Object> allEnd = allAvgPerList.get(allAvgPerList.size() - 1);
			Map<String, Object> allStart = allAvgPerList.get(0);
			allEndDate = (String) allEnd.get("yearDate");
			allStartDate = (String) allStart.get("yearDate");
		}
		// 查询警戒线
		double allAvgWarningIndex = getValByCode("financeMgr_month_totalAvg_warning");
		double allAvgWellIndex = getValByCode("financeMgr_month_totalAvg_well");
		Map<String, Object> allAvgPerCap = new HashMap<String, Object>();
		allAvgPerCap.put("beginDate", allStartDate);
		allAvgPerCap.put("endDate", allEndDate);
		allAvgPerCap.put("allAvgWarningIndex", allAvgWarningIndex);
		allAvgPerCap.put("allAvgWellIndex", allAvgWellIndex);
		allAvgPerCap.put("allAvgPerList", allAvgPerList);
		/**
		 * 3.咨询师人均产能
		 */
		List<Map<String, Object>> salesAvgPerList = financePersonalInfoMapper.selectSalesAvgPer();
		String salesEndDate = "--年--月";
		String salesBeginDate =  "--年--月";
		if (salesAvgPerList == null || salesAvgPerList.size() == 0) {
			logger.info("咨询师人均产能数据为空");
		} else {
			Map<String, Object> salesEnd = salesAvgPerList.get(salesAvgPerList.size() - 1);
			Map<String, Object> salesStart = salesAvgPerList.get(0);
			salesEndDate = (String) salesEnd.get("yearDate");
			salesBeginDate = (String) salesStart.get("yearDate");
		}
		double salesWarningIndex = getValByCode("financeMgr_month_consultantsAvg_warning");
		double salesWellIndex = getValByCode("financeMgr_month_consultantsAvg_well");
		Map<String, Object> salesAvgPerCap = new HashMap<String, Object>();
		salesAvgPerCap.put("beginDate", salesBeginDate);
		salesAvgPerCap.put("endDate", salesEndDate);
		salesAvgPerCap.put("salesWarningIndex", salesWarningIndex);
		salesAvgPerCap.put("salesWellIndex", salesWellIndex);
		salesAvgPerCap.put("salesAvgPerList", salesAvgPerList);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("daily", daily);
		data.put("allAvgPerCap", allAvgPerCap);
		data.put("salesAvgPerCap", salesAvgPerCap);
		return new ReturnMsgData("0000", "调用成功", data);
	}

	/**
	 * 进件质量 日
	 * 
	 * @author 东东
	 */
	@Override
	public ReturnMsgData financeDailyIntoQuality(String days) {
		/**
		 * 1.当月累计批核率
		 */
		Date fiveDaysAgoDate = DateTimeUtil.getFiveDaysAgoDate(days);
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("beginDay", fiveDaysAgoDate);
		para.put("endDay", days);
		List<Map<String, Object>> accApprovalList = financeDailyPerformanceMapper.selectAccumuRateForMonth(para);
		if (accApprovalList == null || accApprovalList.size() == 0) {
			logger.info("日期为 ： " + days + "进件质量批核率为空");
		}
		double intoDailyWaringIndex = getValByCode("financeMgr_daily_approvalRate_one");
		double intoDailyWellIndex = getValByCode("financeMgr_daily_approvalRate_four");
		Map<String, Object> accumuRateForMonth = new HashMap<String, Object>();
		accumuRateForMonth.put("intoDailyWaringIndex", intoDailyWaringIndex);
		accumuRateForMonth.put("intoDailyWellIndex", intoDailyWellIndex);
		accumuRateForMonth.put("accApprovalList", accApprovalList);
		/**
		 * 2.当月累计批核件均
		 */
		List<Map<String, Object>> accApprovalAvgList = financeDailyPerformanceMapper.selectAccApprovalAvgForMonth(para);
		if (accApprovalAvgList == null || accApprovalAvgList.size() == 0) {
			logger.info("日期为 ： " + days + "当月累计批核件均为空");
		}
		double intoDailyAvgWaringIndex = getValByCode("financeMgr_daily_batchParts_one");
		double intoDailyAvgWellIndex = getValByCode("financeMgr_daily_batchParts_four");
		Map<String, Object> accApprovalAvgForMonth = new HashMap<String, Object>();
		accApprovalAvgForMonth.put("intoDailyAvgWaringIndex", intoDailyAvgWaringIndex);
		accApprovalAvgForMonth.put("intoDailyAvgWellIndex", intoDailyAvgWellIndex);
		accApprovalAvgForMonth.put("accApprovalAvgList", accApprovalAvgList);
		/**
		 * 3.组装返回数据
		 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("accumuRateForMonth", accumuRateForMonth);
		data.put("accApprovalAvgForMonth", accApprovalAvgForMonth);
		return new ReturnMsgData("0000", "调用成功", data);
	}

	/**
	 * 进件质量 月
	 * 
	 * @author 东东
	 */
	@Override
	public ReturnMsgData financeMonthIntoQuality() {
		/**
		 * 1. 查找批核率
		 */
		List<Map<String, Object>> approvalRateList = financeMonthPerformanceMapper.selectApprovalRate();
		String endTime =  "--年--月";
		String beginTime = "--年--月";
		if (approvalRateList == null || approvalRateList.size() == 0) {
			logger.info("批核率无数据");
		} else {
			Map<String, Object> endMap = approvalRateList.get(approvalRateList.size() - 1);
			Map<String, Object> beginMap = approvalRateList.get(0);
			endTime = (String) endMap.get("yearDate");
			beginTime = (String) beginMap.get("yearDate");
		}
		double approvalRateWaringIndex = getValByCode("financeMgr_monthapprovalRate_one");
		double approvalRateWellIndex = getValByCode("financeMgr_month_approvalRate_four");
		Map<String, Object> approvalRate = new HashMap<>();
		approvalRate.put("approvalRateList", approvalRateList);
		approvalRate.put("endTime", endTime);
		approvalRate.put("beginTime", beginTime);
		approvalRate.put("approvalRateWaringIndex", approvalRateWaringIndex);
		approvalRate.put("approvalRateWellIndex", approvalRateWellIndex);

		/**
		 * 2.批核件均
		 */
		List<Map<String, Object>> approvalAvgList = financeMonthPerformanceMapper.selectApprovalAvg();
		String endAvgTime =  "--年--月";
		String beginAvgTime =  "--年--月";
		if (approvalAvgList == null || approvalAvgList.size() == 0) {
			logger.info("批核件均");
		} else {
			Map<String, Object> endAvgMap = approvalAvgList.get(approvalRateList.size() - 1);
			Map<String, Object> beginAvgMap = approvalAvgList.get(0);
			endAvgTime = (String) endAvgMap.get("yearDate");
			beginAvgTime = (String) beginAvgMap.get("yearDate");
		}
		double approvalAvgWaringIndex = getValByCode("financeMgr_month_batchParts_one");
		double approvalAvgWellIndex = getValByCode("financeMgr_month_batchParts_four");
		Map<String, Object> approvalAvg = new HashMap<>();
		approvalAvg.put("approvalAvgList", approvalAvgList);
		approvalAvg.put("endTime", endAvgTime);
		approvalAvg.put("beginTime", beginAvgTime);
		approvalAvg.put("approvalAvgWaringIndex", approvalAvgWaringIndex);
		approvalAvg.put("approvalAvgWellIndex", approvalAvgWellIndex);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("approvalRate", approvalRate);
		data.put("approvalAvg", approvalAvg);
		return new ReturnMsgData("0000", "调用成功", data);
	}

	public Map<String, Object> getRangeMap(String description, double value, String recordDate, String codeOne,
			String codeTwo, String codeThree, String codeFour) {
		Map<String, Object> rangeMap = new HashMap<String, Object>();
		rangeMap.put("description", description);
		rangeMap.put("value", value);
		rangeMap.put("recordDate", recordDate);
		rangeMap.put("codeOne", codeOne);
		rangeMap.put("codeTwo", codeTwo);
		rangeMap.put("codeThree", codeThree);
		rangeMap.put("codeFour", codeFour);
		return rangeMap;
	}

	private Map<String, Object> checkIsNull(Map<String, String> dayRange, Map<String, Object> resultMap) {
		if (null != dayRange.get("color")) {
			resultMap.put("color", dayRange.get("color"));
		} else {
			resultMap.put("color", "normal");
		}
		if (null != dayRange.get("range")) {
			resultMap.put("range", dayRange.get("range"));
		} else {
			resultMap.put("range", "--");
		}
		return resultMap;
	}

	private void valueIsNullMethod(Map<String, Object> resultMap) {
		resultMap.put("value", "--");
		resultMap.put("range", "--");
		resultMap.put("color", "normal");
		resultMap.put("ranking", -1);
	}

	/**
	 * 对 业绩收入占比 进行排序
	 * 
	 * @param a
	 *            服务费比例
	 * @param b
	 *            仲裁费比例
	 * @param c
	 *            意外险比例
	 * @return
	 */
	public void sort(BigDecimal a, BigDecimal b, BigDecimal c) {
		SortBean SortBean = new SortBean();
		SortBean.setCode("serviceChargeRate");// 服务费比例
		SortBean.setValue(a);
		SortBean SortBean2 = new SortBean();
		SortBean2.setCode("arbitrationChargeRate");// 仲裁费比例
		SortBean2.setValue(b);
		SortBean SortBean3 = new SortBean();
		SortBean3.setCode("insuranceRate");// 意外险比例
		SortBean3.setValue(c);
		List<SortBean> list = new ArrayList<SortBean>();
		list.add(SortBean);
		list.add(SortBean2);
		list.add(SortBean3);
		Collections.sort(list, new Comparator<SortBean>() {
			public int compare(SortBean arg0, SortBean arg1) {
				return arg0.getValue().compareTo(arg1.getValue());
			}
		});

		int first = list.get(1).getValue().setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))
				.intValue();
		int second = list.get(2).getValue().setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))
				.intValue();
		int third = 100 - first - second;
		dataMap.put(list.get(1).getCode(), first);
		dataMap.put(list.get(2).getCode(), second);
		dataMap.put(list.get(0).getCode(), third);
	}

	/**
	 * 对计划达成率 和 人均进件 进行排序
	 */
	public Map<String, Double> planRateSort(BigDecimal a, BigDecimal b) {
		Map<String, Double> resultMap = new HashMap<String, Double>();
		SortBean SortBean = new SortBean();
		SortBean.setValue(a);
		SortBean SortBean2 = new SortBean();
		SortBean2.setValue(b);
		List<SortBean> list = new ArrayList<SortBean>();
		list.add(SortBean);
		list.add(SortBean2);
		Collections.sort(list, new Comparator<SortBean>() {
			public int compare(SortBean arg0, SortBean arg1) {
				return arg0.getValue().compareTo(arg1.getValue());
			}
		});
		resultMap.put("first", list.get(0).getValue().doubleValue());
		resultMap.put("second", list.get(1).getValue().doubleValue());
		return resultMap;
	}

	/**
	 * 根据code获取对应的value index表
	 * 
	 * @param code
	 * @return
	 */
	private double getValByCode(String code) {
		Index index = indexMapper.getLineByCode(code);
		Double value = index.getValue() == null ? -1 : index.getValue();
		return value;
	}

	/**
	 * 得到计划达成率的区间和颜色
	 * 
	 * @param value
	 * @param first
	 * @param second
	 * @return
	 */
	private Map<String, String> getPlanRateRange(double value, double first, double second, String str) {
		Map<String, String> map = new HashMap<String, String>();
		String resultStr;
		String color;
		if (value < first) {
			resultStr = "<" + df.format(first) + str;
			color = "red";
		} else if (value >= first && value <= second) {
			resultStr = df.format(first) + str + "~" + df.format(second) + str;
			color = "yellow";
		} else if (value > second) {
			resultStr = ">" + df.format(second) + str;
			color = "green";
		} else {
			return null;
		}
		map.put("range", resultStr);
		map.put("color", color);
		return map;
	}

	/*
	 * 融资 日 区域，营业部 没有数据，返回列表内容
	 */
	public static Map<String, Object> noDataOfDay(List<Map<String, Object>> dataList, Map<String, Object> data,
			Map<String, Object> resultMap, Map<String, Object> resultMap2, Map<String, Object> resultMap3,
			Map<String, Object> resultMap4, Map<String, Object> resultMap5) {
		resultMap.put("name", DescriptionConstants.D1);
		resultMap.put("value", "--");
		resultMap.put("range", "--");
		resultMap.put("ranking", -1);
		resultMap.put("color", "normal");
		resultMap2.put("name", DescriptionConstants.D2);
		resultMap2.put("value", "--");
		resultMap2.put("range", "--");
		resultMap2.put("ranking", -1);
		resultMap2.put("color", "normal");
		resultMap3.put("name", DescriptionConstants.D3);
		resultMap3.put("value", "--");
		resultMap3.put("range", "--");
		resultMap3.put("ranking", -1);
		resultMap3.put("color", "normal");
		resultMap4.put("name", DescriptionConstants.D4);
		resultMap4.put("value", "--");
		resultMap4.put("range", "--");
		resultMap4.put("ranking", -1);
		resultMap4.put("color", "normal");
		resultMap5.put("name", DescriptionConstants.D5);
		resultMap5.put("value", "--");
		resultMap5.put("range", "--");
		resultMap5.put("ranking", -1);
		resultMap5.put("color", "normal");
		dataList.add(resultMap);
		dataList.add(resultMap2);
		dataList.add(resultMap3);
		dataList.add(resultMap4);
		dataList.add(resultMap5);
		data.put("dataList", dataList);
		return data;
	}
	
	/*
	 * 融资 月 区域，区域和 营业部 没有数据，返回列表内容
	 */
	private Map<String, Object> noDataOfMonth(List<Map<String, Object>> dataList, Map<String, Object> data,
			Map<String, Object> resultMap, Map<String, Object> resultMap2, Map<String, Object> resultMap3,
			Map<String, Object> resultMap4, Map<String, Object> resultMap5, Map<String, Object> resultMap6,
			Map<String, Object> resultMap7) {
		resultMap.put("name", DescriptionConstants.M1);
		resultMap.put("value", "--");
		resultMap.put("range", "--");
		resultMap.put("ranking", -1);
		resultMap.put("color", "normal");
		resultMap2.put("name", DescriptionConstants.M2);
		resultMap2.put("value", "--");
		resultMap2.put("range", "--");
		resultMap2.put("ranking", -1);
		resultMap2.put("color", "normal");
		resultMap3.put("name", DescriptionConstants.M3);
		resultMap3.put("value", "--");
		resultMap3.put("range", "--");
		resultMap3.put("ranking", -1);
		resultMap3.put("color", "normal");
		resultMap4.put("name", DescriptionConstants.M4);
		resultMap4.put("value", "--");
		resultMap4.put("range", "--");
		resultMap4.put("ranking", -1);
		resultMap4.put("color", "normal");
		resultMap5.put("name", DescriptionConstants.M5);
		resultMap5.put("value", "--");
		resultMap5.put("range", "--");
		resultMap5.put("ranking", -1);
		resultMap5.put("color", "normal");
		resultMap6.put("name", DescriptionConstants.M6);
		resultMap6.put("value", "--");
		resultMap6.put("range", "--");
		resultMap6.put("ranking", -1);
		resultMap6.put("color", "normal");
		resultMap7.put("name", DescriptionConstants.M7);
		resultMap7.put("value", "--");
		resultMap7.put("range", "--");
		resultMap7.put("ranking", -1);
		resultMap7.put("color", "normal");
		dataList.add(resultMap);
		dataList.add(resultMap2);
		dataList.add(resultMap3);
		dataList.add(resultMap4);
		dataList.add(resultMap5);
		dataList.add(resultMap6);
		dataList.add(resultMap7);
		data.put("dataList", dataList);
		return data;
	}

	/**
	 * 当日人均进件
	 * 
	 * @param recordDate
	 *            请求日期
	 * @param ranking
	 *            排名
	 * @param resultMap
	 *            返回的map
	 * @param value
	 *            当日人均进件值
	 * @param rangeMap3
	 *            请求map（封装code）
	 */
	public void getPerAvgApply(String recordDate, long ranking, Map<String, Object> resultMap, double value,
			Map<String, Object> rangeMap3) {
		// 得到page表中的阈值
		List<FinanceDailyPage> list = financeDailyPageMapper.getValueByCodeAndDate(rangeMap3);
		BigDecimal value1 = null ;
		BigDecimal value2 = null ;
		if(null != list && list.size() ==2){
			value1 = list.get(0).getDataFigureValue();
			value2 = list.get(1).getDataFigureValue();
		}else{
			resultMap.put("range", "--");
			resultMap.put("color", "--");
			resultMap.put("value", df.format(value));
			resultMap.put("ranking", -1);
		}
		if (null != value1 && null != value2) {
			// 得到日人均进件目标
			Map<String, Object> dayAvgPerPlan = getDayAvgPerPlan(recordDate);
			if(null != dayAvgPerPlan){
				BigDecimal avgPerEntryPlan = (BigDecimal) dayAvgPerPlan.get("avgPerEntryPlan");
				// 计算日人均进件 区间 值
				BigDecimal rangeValueOne = avgPerEntryPlan.multiply(value1).setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal rangeValueTwo = avgPerEntryPlan.multiply(value2).setScale(2, BigDecimal.ROUND_HALF_UP);

				// 将得到的日人均进件排序
				Map<String, Double> planRateSort = planRateSort(rangeValueOne, rangeValueTwo);
				double first = planRateSort.get("first");
				double second = planRateSort.get("second");

				// 得到区间和颜色
				String str = "";
				Map<String, String> planRateRange = getPlanRateRange(value, first, second, str);

				if (null != planRateRange.get("range")) {
					resultMap.put("range", planRateRange.get("range"));
				} else {
					resultMap.put("range", "--");
				}

				if (null != planRateRange.get("color")) {
					resultMap.put("color", planRateRange.get("color"));
				} else {
					resultMap.put("color", "normal");
				}
				resultMap.put("value", df.format(value));
				resultMap.put("ranking", ranking);
			}else{
				resultMap.put("range", "--");
				resultMap.put("color", "--");
				resultMap.put("value", df.format(value));
				resultMap.put("ranking", ranking);
			}
		} else {
			resultMap.put("range", "--");
			resultMap.put("color", "--");
			resultMap.put("value", df.format(value));
			resultMap.put("ranking", -1);
		}
	}

	/**
	 * 计划达成率
	 * 
	 * @param recordDate
	 *            请求日期
	 * @param ranking
	 *            排名
	 * @param resultMap
	 *            返回的map
	 * @param value
	 *            当日人均进件值
	 * @param rangeMap3
	 *            请求map（封装code）
	 */
	public void planYieldRate(String recordDate, long ranking, Map<String, Object> resultMap, double value,
			Map<String, Object> rangeMap) {
		List<FinanceDailyPage> list = financeDailyPageMapper.getValueByCodeAndDate(rangeMap);
		BigDecimal value1 = list.get(0).getDataFigureValue();
		BigDecimal value2 = list.get(1).getDataFigureValue();
		if (null != value1 && null != value2) {
			// 得到时间进度
			BigDecimal timeSchedule = getTimeSchedule(recordDate);
			// 计算计划达成率
			BigDecimal planRateOne = timeSchedule.subtract(value1);
			BigDecimal planRateTwo = timeSchedule.subtract(value2);
			;
			// 将得到的计划达成率排序
			Map<String, Double> planRateSort = planRateSort(planRateOne, planRateTwo);

			double first = planRateSort.get("first");
			double second = planRateSort.get("second");

			// 得到区间和颜色
			String str = "%";
			Map<String, String> planRateRange = getPlanRateRange(value, first, second, str);

			if (null != planRateRange.get("range")) {
				resultMap.put("range", planRateRange.get("range"));
			} else {
				resultMap.put("range", "--");
			}

			if (null != planRateRange.get("color")) {
				resultMap.put("color", planRateRange.get("color"));
			} else {
				resultMap.put("color", "normal");
			}

			resultMap.put("value", df.format(value) + "%");
			resultMap.put("ranking", ranking);
		} else {
			resultMap.put("range", "--");
			resultMap.put("color", "--");
			resultMap.put("value", df.format(value) + "%");
			resultMap.put("ranking", ranking);
		}
	}

	/**
	 * 计算时间进度
	 * 
	 * @param dateString
	 *            格式为yyyy-MM-dd
	 * @return
	 */
	private BigDecimal getTimeSchedule(String dateString) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateTimeUtil.convertAsDateString(dateString));
		BigDecimal pastDays = new BigDecimal(calendar.get(Calendar.DAY_OF_MONTH));// 已过天数
		BigDecimal monthDays = new BigDecimal(DateTimeUtil.getLastMonthDays(dateString));// 当月总天数
		//0.1290
		BigDecimal rate = pastDays.divide(monthDays, 4, BigDecimal.ROUND_HALF_UP);
		return rate.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);// 返回整数部分
	}
	
	/**
	 * 根据指标名称和区域查找对应区域下所有营业部的指标
	 */
	@Override
	public ReturnMsgData getOrgIndexByAreaAndIndexName(PerformanceParm performanceParm) {
		/**
		 * 1.查询大区下所有的营业部list
		 * 2.根据前台传的指标名称，遍历营业部list根据营业部查询指标并正序排序
		 */
		Map<String, Object> data = new HashMap<String, Object>();
		List<Map<String, Object>> indexList = new ArrayList<Map<String, Object>>();
		Map<String, Object> dismap = new HashMap<String, Object>();
		String recordDate = performanceParm.getMonths();
		dismap.put("recordDate", recordDate);
		dismap.put("districtName", performanceParm.getAreaName());
		//营业部list
		List<Map<String, Object>> orgNameList = financeMonthOrgMapper.findAllOrgNameByDistrictOfMonth(dismap);
		if(null != orgNameList && orgNameList.size()>0){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgNameList", orgNameList);
			map.put("months", performanceParm.getMonths());
			map.put("indexName", performanceParm.getIndexName());
			//月计划达成率
			if("M001".equals(performanceParm.getIndexName())){
				getMonthPlanRate(indexList, recordDate, map);
			}
			//月放款金额
			if("M002".equals(performanceParm.getIndexName())){
				getMonthLendAmount(indexList, map);
			}
			//日人均进件
			if("M003".equals(performanceParm.getIndexName())){
				getDailyPerEntryAvg(indexList, recordDate, map);
			}
			//批核率
			if("M004".equals(performanceParm.getIndexName())){
				getBatchRate(indexList, map);
			}
			//批核件均
			if("M005".equals(performanceParm.getIndexName())){
				getBatchParts(indexList, map);
			}
			//全员人均产能
			if("M006".equals(performanceParm.getIndexName())){
				getTotalEmpNumAvg(indexList, map);
			}
			//咨询师人均产能
			if("M007".equals(performanceParm.getIndexName())){
				getCounselorAvg(indexList, map);
			}
		}
		data.put("salesDeptList", indexList);
		return new ReturnMsgData("0000", "调用成功", data);
	}
	/**
	 * 获得指定大区下所有营业部的咨询师均产能并正序排序
	 * @param indexList
	 * @param map1
	 */
	private void getCounselorAvg(List<Map<String, Object>> indexList, Map<String, Object> map1) {
		map1.put("orderField", "counselorAvg");
		List<Map<String, Object>> list = financeMonthOrgMapper.getOrgIndexByAreaAndIndexName(map1);
		for (Map<String, Object> map : list) {
			Map<String, Object> resultMap7 = new HashMap<String, Object>();
			if (null != map.get("counselorAvg")) {
				double counselorAvg = ((java.math.BigDecimal) map.get("counselorAvg")).doubleValue();
				Map<String, Object> rangeMap7 = getRangeMap(DescriptionConstants.DAPPROVALAVG, counselorAvg, "",
						TemplateConstants.F21, TemplateConstants.F22, TemplateConstants.F29, TemplateConstants.F30);
				Map<String, String> dayRange7 = getMonthRange(rangeMap7);
				resultMap7.put("value", dayRange7.get("value"));
				resultMap7.put("ranking", map.get("totalEmpNumAvgRanking"));
				resultMap7 = checkIsNull(dayRange7, resultMap7);
			} else {
				valueIsNullMethod(resultMap7);
			}
			removeUnuse(map, resultMap7);
			indexList.add(resultMap7);
		}
	}
	/**
	 * 获得指定大区下所有营业部的全员人均产能并正序排序
	 * @param indexList
	 * @param map1
	 */
	private void getTotalEmpNumAvg(List<Map<String, Object>> indexList, Map<String, Object> map1) {
		map1.put("orderField", "totalEmpNumAvg");
		List<Map<String, Object>> list = financeMonthOrgMapper.getOrgIndexByAreaAndIndexName(map1);
		for (Map<String, Object> map : list) {
			Map<String, Object> resultMap6 = new HashMap<String, Object>();
			if (null != map.get("totalEmpNumAvg")) {
				double totalEmpNumAvg = ((java.math.BigDecimal) map.get("totalEmpNumAvg")).doubleValue();
				Map<String, Object> rangeMap6 = getRangeMap(DescriptionConstants.DAPPROVALAVG, totalEmpNumAvg, "",
						TemplateConstants.F19, TemplateConstants.F20, "", "");
				Map<String, String> dayRange6 = getMonthRange(rangeMap6);
				resultMap6.put("value", dayRange6.get("value"));
				resultMap6.put("ranking", map.get("totalEmpNumAvgRanking"));
				resultMap6 = checkIsNull(dayRange6, resultMap6);
			} else {
				valueIsNullMethod(resultMap6);
			}
			removeUnuse(map, resultMap6);
			indexList.add(resultMap6);
		}
	}
	/**
	 * 获得指定大区下所有营业部的批核件均并正序排序
	 * @param indexList
	 * @param map1
	 */
	private void getBatchParts(List<Map<String, Object>> indexList, Map<String, Object> map1) {
		map1.put("orderField", "approvalAverage");
		List<Map<String, Object>> list = financeMonthOrgMapper.getOrgIndexByAreaAndIndexName(map1);
		for (Map<String, Object> map : list) {
			Map<String, Object> resultMap5 = new HashMap<String, Object>();
			if (null != map.get("approvalAverage")) {
				double approvalAverage = ((java.math.BigDecimal) map.get("approvalAverage")).doubleValue();
				Map<String, Object> rangeMap5 = getRangeMap(DescriptionConstants.MAPPROVALAVG, approvalAverage, "",
						TemplateConstants.F27, TemplateConstants.F28, TemplateConstants.F29, TemplateConstants.F30);
				Map<String, String> dayRange5 = getMonthRange(rangeMap5);
				resultMap5.put("value", dayRange5.get("value"));
				resultMap5.put("ranking", map.get("approvalAverageRanking"));
				resultMap5 = checkIsNull(dayRange5, resultMap5);
			} else {
				valueIsNullMethod(resultMap5);
			}
			removeUnuse(map, resultMap5);
			indexList.add(resultMap5);
		}
	}
	/**
	 * 获得指定大区下所有营业部的批核率并正序排序
	 * @param indexList
	 * @param map1
	 */
	private void getBatchRate(List<Map<String, Object>> indexList, Map<String, Object> map1) {
		map1.put("orderField", "approvalRate");
		List<Map<String, Object>> list = financeMonthOrgMapper.getOrgIndexByAreaAndIndexName(map1);
		for (Map<String, Object> map : list) {
			Map<String, Object> resultMap4 = new HashMap<String, Object>();
			resultMap4.put("name", DescriptionConstants.M4);
			if (null != map.get("approvalRate")) {
				double approvalRate = ((java.math.BigDecimal) map.get("approvalRate")).doubleValue();
				Map<String, Object> rangeMap4 = getRangeMap(DescriptionConstants.MAPPROVALRATE, approvalRate, "",
						TemplateConstants.F23, TemplateConstants.F24, TemplateConstants.F25, TemplateConstants.F26);
				Map<String, String> dayRange4 = getMonthRange(rangeMap4);
				resultMap4.put("value", dayRange4.get("value"));
				resultMap4.put("ranking", map.get("approvalRateRanking"));
				resultMap4 = checkIsNull(dayRange4, resultMap4);
			} else {
				valueIsNullMethod(resultMap4);
			}
			removeUnuse(map, resultMap4);
			indexList.add(resultMap4);
		}
	}
	/**
	 * 获得指定大区下所有营业部的日人均进件并正序排序
	 * @param indexList
	 * @param recordDate
	 * @param map1
	 */
	private void getDailyPerEntryAvg(List<Map<String, Object>> indexList, String recordDate, Map<String, Object> map1) {
		map1.put("orderField", "salesNumAvg");
		List<Map<String, Object>> list = financeMonthOrgMapper.getOrgIndexByAreaAndIndexName(map1);
		for (Map<String, Object> map2 : list) {
			Map<String, Object> resultMap3 = new HashMap<String, Object>();
			resultMap3.put("name", DescriptionConstants.M3);
			if (null != map2.get("salesNumAvg")) {
				double salesNumAvg = ((java.math.BigDecimal) map2.get("salesNumAvg")).doubleValue();
				Map<String, Object> rangeMap3 = getRangeMap(DescriptionConstants.DPEOPLEAVG, salesNumAvg, recordDate,
						TemplateConstants.F3, TemplateConstants.F4, "", "");
				long salesNumAvgRanking = (long) map2.get("salesNumAvgRanking");
				getPerAvgApply(recordDate, salesNumAvgRanking, resultMap3, salesNumAvg, rangeMap3);
			} else {
				valueIsNullMethod(resultMap3);
			}
			removeUnuse(map2, resultMap3);
			indexList.add(resultMap3);
		}
	}
	/**
	 * 获得指定大区下所有营业部的月放款金额并正序排序
	 * @param indexList
	 * @param map1
	 */
	private void getMonthLendAmount(List<Map<String, Object>> indexList, Map<String, Object> map1) {
		map1.put("orderField", "loanValue");
		List<Map<String, Object>> list = financeMonthOrgMapper.getOrgIndexByAreaAndIndexName(map1);
		for (Map<String, Object> map2 : list) {
			map2.put("color", "normal");
			if (null != map2.get("loanValue")) {
				map2.put("loanValue", df.format(map2.get("loanValue")) + "万");
			}else{
				map2.put("loanValue", "--");
			}
			map2.put("salesDepartment", map2.get("orgName"));
			map2.remove("orgName");
			indexList.add(map2);
		}
	}
	/**
	 * 获得指定大区下所有营业部的月计划达成率并正序排序
	 * @param indexList
	 * @param recordDate
	 * @param map1
	 */
	private void getMonthPlanRate(List<Map<String, Object>> indexList, String recordDate, Map<String, Object> map1) {
		map1.put("orderField", "planYieldRate");
		List<Map<String, Object>> list = financeMonthOrgMapper.getOrgIndexByAreaAndIndexName(map1);
		for (Map<String, Object> map2 : list) {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("name", DescriptionConstants.M1);
			if (null != map2.get("planYieldRate")) {
				double planYieldRate = ((java.math.BigDecimal) map2.get("planYieldRate")).doubleValue();
				Map<String, Object> rangeMap = getRangeMap(DescriptionConstants.MPLANYIELDRATE, planYieldRate,
						recordDate, TemplateConstants.F13, TemplateConstants.F14, "", "");
				Map<String, String> dayRange = getMonthRange(rangeMap);
				String[] split = dayRange.get("value").split("%");
				resultMap.put("value", split[0]);
				resultMap = checkIsNull(dayRange, resultMap);
			}else{
				valueIsNullMethod(resultMap);
			}
			removeUnuse(map2, resultMap);
			indexList.add(resultMap);
		}
	}
	
	/**
	 * 去除多余的字段并加上机构名称
	 * @param map2
	 * @param resultMap
	 */
	private void removeUnuse(Map<String, Object> map2, Map<String, Object> resultMap) {
		resultMap.remove("name");
		resultMap.remove("range");
		resultMap.remove("ranking");
		resultMap.put("salesDepartment", map2.get("orgName"));
	}


}
