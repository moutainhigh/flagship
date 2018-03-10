package com.hzcf.flagship.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.FinanceDailyPageMapper;
import com.hzcf.flagship.dao.FinanceDailyPerformanceMapper;
import com.hzcf.flagship.dao.FinancePersonalInfoMapper;
import com.hzcf.flagship.dao.IndexMapper;
import com.hzcf.flagship.dao.MoneymgrAccumuPerformanceMapper;
import com.hzcf.flagship.dao.MoneymgrDailyPerformanceMapper;
import com.hzcf.flagship.dao.MoneymgrDistrictAccumuPerformancePageMapper;
import com.hzcf.flagship.dao.MoneymgrEfficiencyPageMapper;
import com.hzcf.flagship.dao.MoneymgrMonthHistoryMapper;
import com.hzcf.flagship.dao.MoneymgrOrgAccumuPerformanceMapper;
import com.hzcf.flagship.dao.MoneymgrOrgAccumuPerfromancePageMapper;
import com.hzcf.flagship.dao.MoneymgrOrgDataMapper;
import com.hzcf.flagship.dao.MoneymgrPerformanceDailyPageMapper;
import com.hzcf.flagship.dao.RiskLoanCm1Mapper;
import com.hzcf.flagship.dao.RiskPlanMapper;
import com.hzcf.flagship.form.PerformanceParm;
import com.hzcf.flagship.model.FinanceDailyPerformance;
import com.hzcf.flagship.model.FinancePersonalInfo;
import com.hzcf.flagship.model.FinancePersonalInfoExample;
import com.hzcf.flagship.model.Index;
import com.hzcf.flagship.model.IndexExample;
import com.hzcf.flagship.model.MoneymgrAccumuPerformance;
import com.hzcf.flagship.model.MoneymgrAccumuPerformanceExample;
import com.hzcf.flagship.model.MoneymgrAccumuPerformanceExample.Criteria;
import com.hzcf.flagship.model.MoneymgrEfficiencyPage;
import com.hzcf.flagship.model.MoneymgrOrgAccumuPerfromancePage;
import com.hzcf.flagship.model.MoneymgrPerformanceDailyPage;
import com.hzcf.flagship.model.MoneymgrPerformanceDailyPageExample;
import com.hzcf.flagship.model.RiskPlan;
import com.hzcf.flagship.service.ApiPerformanceService;
import com.hzcf.flagship.util.DateTimeUtil;
import com.hzcf.flagship.util.DateUtil;
import com.hzcf.flagship.util.FastJSONUtil;
import com.hzcf.flagship.util.PropertyUtil;
import com.hzcf.flagship.util.log.LogDefault;
import com.hzcf.flagship.vo.ReturnMsgData;


/**
 *
 * 类名：ApiPerformanceServiceImpl.java</dt>
 * 功能描述：APP接口 理财业绩 interface 实现类
 * 创建时间： 2017年5月16日 下午3:32:03</dd>
 * 创建人：zhangmx</dd>
 */
@Service
public class ApiPerformanceServiceImpl implements ApiPerformanceService {
	private final static Logger logger = LogDefault.getLogger(ApiPerformanceServiceImpl.class);
	@Autowired
	private MoneymgrPerformanceDailyPageMapper moneymgrPerformanceDailyPageMapper;
	@Autowired
	private MoneymgrDailyPerformanceMapper moneymgrDailyPerformanceMapper;
	@Autowired
	private MoneymgrMonthHistoryMapper moneymgrMonthHistoryMapper;
	@Autowired
	private IndexMapper indexMapper;
	@Autowired
	private MoneymgrOrgAccumuPerfromancePageMapper moneymgrOrgAccumuPerfromancePageMapper;
	@Autowired
	private MoneymgrEfficiencyPageMapper moneymgrEfficiencyPageMapper;
	@Autowired
	private MoneymgrDistrictAccumuPerformancePageMapper	moneymgrDistrictAccumuPerformancePageMapper;
	@Autowired
	private MoneymgrAccumuPerformanceMapper moneymgrAccumuPerformanceMapper;
	@Autowired
	private FinancePersonalInfoMapper financePersonalInfoMapper;
	@Autowired
    private	FinanceDailyPerformanceMapper financeDailyPerformanceMapper;
	@Autowired
	private MoneymgrOrgDataMapper moneymgrOrgDataMapper;
	@Autowired	
	private FinanceDailyPageMapper financeDailyPageMapper;
	@Autowired
	private MoneymgrOrgAccumuPerformanceMapper moneymgrOrgAccumuPerformanceMapper;
	@Autowired
	private RiskPlanMapper riskPlanMapper;
	@Autowired
	private RiskLoanCm1Mapper riskLoanCm1Mapper;
	/**
	 * 理财 日业绩页面结果数据  日页面
	 * @param performanceParm
	 * @return
	 */
	public ReturnMsgData findPerformanceByDate(PerformanceParm performanceParm) {
		String dateString = performanceParm.getDays();
		//日业绩页面结果数据
		MoneymgrPerformanceDailyPageExample moneymgrPerformanceDailyPageExample = new MoneymgrPerformanceDailyPageExample();
		MoneymgrPerformanceDailyPageExample.Criteria criteriaPage = moneymgrPerformanceDailyPageExample.createCriteria();
		criteriaPage.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dateString));
		List<MoneymgrPerformanceDailyPage> dayList = moneymgrPerformanceDailyPageMapper.selectByExample(moneymgrPerformanceDailyPageExample);
		MoneymgrPerformanceDailyPage moneymgrPerformanceDailyPage = new MoneymgrPerformanceDailyPage();
		if (dayList==null || dayList.size()==0) {
			logger.info("日期为："+dateString+"的 《日业绩页面结果数据》 is Null");
		}else{
			moneymgrPerformanceDailyPage = dayList.get(0);
		}

		//当月每日业绩
		Map<String, Object> parm = new HashedMap();
		parm.put("startDate", DateTimeUtil.convertAsDateString(DateTimeUtil.getFirstDayOfMonth(dateString)));
		parm.put("endDate", DateTimeUtil.convertAsDateString(dateString));
		List<Map<String, Object>> allDayList = moneymgrDailyPerformanceMapper.findDayOfMonthPerformance(parm);
		if (allDayList==null || allDayList.size()==0) {
			logger.info("日期为："+dateString+"的 《当月每日业绩》 is Null");
		}

		//返回数据信息
		moneymgrPerformanceDailyPage.setWarningIndex(this.getIndexValue("moneyMgr_daily_accumu_warning"));
		moneymgrPerformanceDailyPage.setWellIndex(this.getIndexValue("moneyMgr_daily_accumu_well"));
		Map<String, Object> data = new HashMap<>();
		data.put("dayValue", performanceParm.getDays());
		data.put("performanceCurrentDay", moneymgrPerformanceDailyPage);
		data.put("allDayInMonthList", allDayList);
		data.put("mapUrl", PropertyUtil.getInfo("mapUrl")+"?days="+performanceParm.getDays()+"&token="+performanceParm.getToken());
		logger.info("【返回数据】理财 日业绩页面结果数据  日页面 ::::" + FastJSONUtil.toJSONString(data));
		return new ReturnMsgData("0000", "调用成功", data);

	}

	/**
	 * 理财 月业绩同比/达成率同比 月页面
	 * @param performanceParm
	 * @return
	 */
	public ReturnMsgData findPerformanceByMonths(PerformanceParm performanceParm) {
		String dateString = performanceParm.getMonths();
        String currYear = DateTimeUtil.getDate(dateString,"yyyy");
        Map<String, Object> parmMap = new  HashMap<>();
		parmMap.put("yearValue", currYear);
		//今年业绩/达成率
        List<Map<String, Object>>  currentYearList = moneymgrMonthHistoryMapper.findPerformanceByYear(parmMap);
        if (currentYearList==null || currentYearList.size()==0) {
            logger.info("日期为："+dateString+"的 《月业绩今年业绩》 is Null");
            //return new ReturnMsgData("9999", "月业绩今年业绩数据 is Null");
        }
        List<Map<String, Object>>  currentYearRateList = moneymgrMonthHistoryMapper.findPerformanceRateByYear(parmMap);
        if (currentYearRateList==null || currentYearRateList.size()==0) {
            logger.info("日期为："+dateString+"的 《月业绩今年达成率》 is Null");
            //return new ReturnMsgData("9999", "月业绩今年达成率数据 is Null");
        }

        String lastYear = DateTimeUtil.getDate(DateTimeUtil.getIntervalMonthLater(dateString, -12), "yyyy");	//DateTimeUtil.getLastYear();
		parmMap.put("yearValue", lastYear);
        //去年业绩/达成率
        List<Map<String, Object>>  lastYearList = moneymgrMonthHistoryMapper.findPerformanceByYear(parmMap);
        if (lastYearList==null || lastYearList.size()==0) {
            logger.info("日期为："+dateString+"的 《月业绩去年业绩》 is Null");
            //return new ReturnMsgData("9999", "月业绩去年业绩数据 is Null");
        }
        List<Map<String, Object>>  lastYearRateList = moneymgrMonthHistoryMapper.findPerformanceRateByYear(parmMap);
        if (lastYearRateList==null || lastYearRateList.size()==0) {
            logger.info("日期为："+dateString+"的 《月业绩去年达成率》 is Null");
            //return new ReturnMsgData("9999", "月业绩去年达成率数据 is Null");
        }

		//返回数据信息
		Map<String, Object> data = new HashMap<>();
		data.put("currentYear", currYear.substring(2,4));
		data.put("lastYear", lastYear.substring(2,4));
		data.put("warningLine", this.getIndexValue("moneyMgr_month_perform_warning"));
		data.put("wellLine", this.getIndexValue("moneyMgr_month_perform_well"));
		data.put("currentYearPerformance", currentYearList);
		data.put("lastYearPerformance", lastYearList);
		data.put("currentYearRate", currentYearRateList);
		data.put("lastYearRate", lastYearRateList);
		logger.info("【返回数据】理财 月业绩同比/达成率同比 月页面  ::::" + FastJSONUtil.toJSONString(data));
		return new ReturnMsgData("0000", "调用成功", data);
	}

	/**
	 * 理财H5接口 各机构当月累计业绩  日页面
	 * @param parmMap
	 * @return
	 */
	public ReturnMsgData findPerformanceOrgMapByDays(Map<String, Object> parmMap) {
		String dateString = parmMap.get("dateString").toString();
		List<MoneymgrOrgAccumuPerfromancePage> allOrgMonthList = moneymgrOrgAccumuPerfromancePageMapper.findPerformanceOrgMapByDays(parmMap);

		//日业绩页面结果数据
		MoneymgrPerformanceDailyPageExample moneymgrPerformanceDailyPageExample = new MoneymgrPerformanceDailyPageExample();
		MoneymgrPerformanceDailyPageExample.Criteria criteriaPage = moneymgrPerformanceDailyPageExample.createCriteria();
		criteriaPage.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dateString));
		List<MoneymgrPerformanceDailyPage> dayList = moneymgrPerformanceDailyPageMapper.selectByExample(moneymgrPerformanceDailyPageExample);
		MoneymgrPerformanceDailyPage moneymgrPerformanceDailyPage = new MoneymgrPerformanceDailyPage();
		Map<String, Object> data = new HashedMap();
		if (dayList==null || dayList.size()==0) {
			data.put("performanceOrgCurrentDay", moneymgrPerformanceDailyPage);
			logger.info("日期为："+dateString+"的 《日业绩页面结果数据》 is Null");
			//return new ReturnMsgData("9999", "日业绩页面结果数据 is Null");
		}else{
			moneymgrPerformanceDailyPage = dayList.get(0);
			Map<String, Object> inData = new HashedMap();
			inData.put("performanceCurrentDay", moneymgrPerformanceDailyPage.getAvgAccumuPerformance());
			inData.put("accumuWarningLine", moneymgrPerformanceDailyPage.getAccumuWarningLine());
			inData.put("accumuWellLine", moneymgrPerformanceDailyPage.getAccumuWellLine());
			inData.put("warningIndex", this.getIndexValue("moneyMgr_month_perform_warning"));
			inData.put("wellIndex", this.getIndexValue("moneyMgr_month_perform_well"));
			data.put("performanceOrgCurrentDay", inData);
		}
		data.put("allOrgMonthList", allOrgMonthList);
		logger.info("【返回数据】理财H5地图接口 各机构当月累计业绩  日页面 ::::" + FastJSONUtil.toJSONString(data));
		return new ReturnMsgData("0000", "调用成功", data);
	}

	/**
	 * 理财 日人员人效
	 * @param performanceParm
	 * @return
	 */
	public ReturnMsgData findEfficiencyForDays(PerformanceParm performanceParm){
		Map<String, Object> parmMap = new  HashMap<>();
		parmMap.put("dateString", performanceParm.getDays());
		Map<String, Object> data = this.moneymgrEfficiencyPageMapper.findEfficiencyForDays(parmMap);
		if(data == null){
			data = new HashedMap();
			logger.info("日期为："+performanceParm.getDays()+"的 《理财 日人员人效》 is Null");
		}
		logger.info("【返回数据】理财 日人员人效 ::: "+FastJSONUtil.toJSONString(data));
		return new ReturnMsgData("0000", "调用成功", data);
	}

	/**
	 * 理财 月人员人效
	 * @param performanceParm
	 * @return
	 */
	public ReturnMsgData findEfficiencyForMonths(PerformanceParm performanceParm){
		String dateString = performanceParm.getMonths();
		String currYear = DateTimeUtil.getDate(dateString,"yyyy");
		Map<String, Object> parmMap = new  HashMap<>();
		parmMap.put("yearValue", currYear);
		//机构数同比
		List<Map<String, Object>> currYearOrgNumList = moneymgrMonthHistoryMapper.findOrgNumByYear(parmMap);
		if(currYearOrgNumList == null || currYearOrgNumList.size() ==0){
			logger.info("日期为："+currYear+"的 《理财 月人员人效 机构数》 is Null");
		}
		//人均新客户数
		List<Map<String, Object>> currYearCapitaNewClientList = moneymgrMonthHistoryMapper.findPerCapitaNewClientByYear(parmMap);
		if(currYearCapitaNewClientList == null || currYearCapitaNewClientList.size() ==0){
			logger.info("日期为："+currYear+"的 《理财 月人员人效 人均新客户数》 is Null");
		}
		//人均产能
		List<Map<String, Object>> currYearCapacityList = moneymgrMonthHistoryMapper.findPerCapitaCapacityByYear(parmMap);
		if(currYearCapacityList == null || currYearCapacityList.size() ==0){
			logger.info("日期为："+currYear+"的 《理财 月人员人效 人均产能》 is Null");
		}
		//新客户数
		List<Map<String, Object>> currYearNewClientList = moneymgrMonthHistoryMapper.findNewClientNumByYear(parmMap);
		if(currYearNewClientList == null || currYearNewClientList.size() ==0){
			logger.info("日期为："+currYear+"的 《理财 月人员人效 人均产能》 is Null");
		}
		String lastYear = DateTimeUtil.getDate(DateTimeUtil.getIntervalMonthLater(dateString, -12), "yyyy");	//DateTimeUtil.getLastYear();
		parmMap.put("yearValue", lastYear);
		//机构数同比
		List<Map<String, Object>> lastYearOrgNumList = moneymgrMonthHistoryMapper.findOrgNumByYear(parmMap);
		if(lastYearOrgNumList == null || lastYearOrgNumList.size() ==0){
			logger.info("日期为："+lastYear+"的 《理财 月人员人效 机构数》 is Null");
		}
		//人均新客户数
		List<Map<String, Object>> lastYearCapitaNewClientList = moneymgrMonthHistoryMapper.findPerCapitaNewClientByYear(parmMap);
		if(lastYearCapitaNewClientList == null || lastYearCapitaNewClientList.size() ==0){
			logger.info("日期为："+lastYear+"的 《理财 月人员人效 人均新客户数》 is Null");
		}
		//人均产能
		List<Map<String, Object>> lastYearCapacityList = moneymgrMonthHistoryMapper.findPerCapitaCapacityByYear(parmMap);
		if(lastYearCapacityList == null || lastYearCapacityList.size() ==0){
			logger.info("日期为："+lastYear+"的 《理财 月人员人效 人均产能》 is Null");
		}
		//新客户数
		List<Map<String, Object>> lastYearNewClientList = moneymgrMonthHistoryMapper.findNewClientNumByYear(parmMap);
		if(lastYearNewClientList == null || lastYearNewClientList.size() ==0){
			logger.info("日期为："+lastYear+"的 《理财 月人员人效 人均产能》 is Null");
		}
		Map<String , Object> data = new HashedMap();
		data.put("currentYearOrgNum",currYearOrgNumList);
		data.put("lastYearOrgNum", lastYearOrgNumList);
		data.put("currentYearNewClientNum", currYearNewClientList);
		data.put("lastYearNewClientNum", lastYearNewClientList);
		data.put("currentYearPerCapitaNewClient", currYearCapitaNewClientList);
		data.put("lastYearPerCapitaNewClient", lastYearCapitaNewClientList);
		data.put("currentYearPerCapitaCapacity", currYearCapacityList);
		data.put("lastYearPerCapitaCapacity", lastYearCapacityList);
		data.put("currentYear", currYear.substring(2,4));
		data.put("lastYear", lastYear.substring(2,4));
		data.put("warningLine", this.getIndexValue("moneyMgr_month_perform_warning"));
		data.put("wellLine", this.getIndexValue("moneyMgr_month_perform_well"));
		logger.info("【返回数据】理财 月人员人效 ::: "+FastJSONUtil.toJSONString(data));
		return new ReturnMsgData("0000", "调用成功", data);
	}

	/**
	 * 理财H5接口 查询所有区
	 * @return
	 */
	public ReturnMsgData findAllAreaName(PerformanceParm performanceParm){
		Map<String ,Object> parm = new HashedMap();
		parm.put("dateString", performanceParm.getDays());
		List<Map<String, Object>> orgList = moneymgrOrgDataMapper.findAllAreaName(parm);
		if(orgList == null || orgList.size() == 0){
			orgList = new ArrayList<>();
		}
		Map<String, Object> data = new HashedMap();
		data.put("areaNameList", this.sortAreaName(orgList));
		return new ReturnMsgData("0000", "调用成功", data);
	}

	/**
	 * 理财H5接口 查询区域下的机构
	 * @return
	 */
	public ReturnMsgData findOrgNameByArea(Map<String, Object> parmMap){
		List<Map<String, Object>> areaOrgList = moneymgrOrgDataMapper.findOrgNameByArea(parmMap);
		if(areaOrgList == null || areaOrgList.size() == 0){
			areaOrgList = new ArrayList<>();
		}
		Map<String, Object> data = new HashedMap();
		data.put("orgNameList", areaOrgList);
		return new ReturnMsgData("0000", "调用成功", data);
	}


	/**
	 * 理财H5接口 查询全部区域和机构
	 * @return
	 */
	public ReturnMsgData findAllAreaOrgName(PerformanceParm performanceParm){
		List<Map<String, Object>> resultList = getAllAreaOrgName(performanceParm.getDays());
		Map<String, Object> data = new HashedMap();
		data.put("areaNameList", resultList);
		return new ReturnMsgData("0000", "调用成功", data);
	}

	/**
	 * 区域名称排序
	 * @param data
	 * @return
	 */
	private List<Map<String, Object>> sortAreaName(List<Map<String, Object>> data){
		if(data != null && data.size() >0) {
			List<String> temp = Arrays.asList("一区", "二区", "三区", "四区", "五区", "六区", "七区", "八区", "九区", "十区");

			List<Map<String, Object>> resultList = new ArrayList<>();
			String districtName = "";
			for (int i = 0; i < temp.size(); i++) {
				for (int j = 0; j < data.size(); j++) {
					districtName = data.get(j).get("districtName").toString();
					if (temp.get(i).toString().equals(districtName)) {
						resultList.add(data.get(j));
						break;
					}
				}
			}
			return resultList;
		}else{
			return data;
		}
	}
	
	/**
	 * 理财H5接口 查询全部区域和机构
	 * @return
	 */
	private List<Map<String, Object>> getAllAreaOrgName(String dateString) {
		Map<String, Object> parm = new HashedMap();
		parm.put("dateString", dateString);
		List<Map<String, Object>> orgList = moneymgrOrgDataMapper.findAllAreaName(parm);
		if(null == orgList || orgList.size() == 0){
			orgList = new ArrayList<>();
			logger.info("《理财H5接口 查询全部区域和机构》 is Null");
		}
		Map<String, Object> parmMap = null;
		List<Map<String, Object>> resultList = new ArrayList<>();
		for(int i=0; i<orgList.size(); i++){
			parmMap = new HashedMap();
			Map<String, Object> temp = orgList.get(i);
			parmMap.put("areaString", temp.get("districtName"));
			parmMap.put("dateString", dateString);
			temp.put("orgNameList", moneymgrOrgDataMapper.findOrgNameByArea(parmMap));
			resultList.add(temp);
		}
		return resultList;
	}


	/**
	 * 模糊查询机构名称
	 * @param parmMap
	 * @return
	 */
	public ReturnMsgData findLikeOrgName(Map<String ,Object> parmMap){
		List orgNameList = moneymgrOrgAccumuPerfromancePageMapper.findLikeOrgName(parmMap);
		if(orgNameList != null && orgNameList.size() == 0){
			orgNameList = new ArrayList();
		}
		Map<String, Object> data = new HashedMap();
		data.put("orgNameList", orgNameList);
		return new ReturnMsgData("0000", "调用成功", data);
	}

	/**
	 * 理财 各机构当月累计达成率
	 * @param performanceParm
	 * @return
	 */
	public ReturnMsgData findAreaPerformanceByOrgName(PerformanceParm performanceParm){
		Map<String, Object> data = new HashedMap();
		String[] orgArray = performanceParm.getOrgNameArray();
		Map<String, Object> parm = new HashMap<>();
		String orgNameString = "";
		if(orgArray != null && orgArray.length > 0){
			for(int i=0; i<orgArray.length; i++){
				orgNameString += orgArray[i] + ",";
			}
			parm.put("orgNameList", orgArray);
		}else{
			List<Map<String, Object>> resultList = getAllAreaOrgName(performanceParm.getDays());
			if(resultList != null && resultList.size()>0){
				StringBuffer stringBuffer = new StringBuffer();
				for (Map<String, Object> map : resultList) {
					List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
					list2= (List<Map<String, Object>>) map.get("orgNameList");
					for (Map<String, Object> map2 : list2) {
						stringBuffer.append(map2.get("orgName")+",");
					}
				}
				stringBuffer = stringBuffer.deleteCharAt(stringBuffer.length() - 1);
				String str = stringBuffer.toString();
				String[] orgNameList = str.split(",");
				parm.put("orgNameList", orgNameList);
			}
		}
		data.put("mapUrl", PropertyUtil.getInfo("mapUrl")+"?days="+performanceParm.getDays()+"&token="+performanceParm.getToken()
				+"&orgNames="+orgNameString);

		parm.put("dateString", performanceParm.getDays());
		List<Map<String, Object>> areaOrgList = moneymgrOrgAccumuPerfromancePageMapper.findAreaPerformanceByOrgName(parm);
		if(areaOrgList == null || areaOrgList.size() == 0){
			areaOrgList = new ArrayList<>();
			logger.info("日期为："+ performanceParm.getDays() +"的 《理财 各机构当月累计达成率》 is Null");
		}
		
		
		/**
		 * 查询区域机构KPI表	
		 * 
		 * 查询区域机构列表全国平均值
		 */
		BigDecimal averageCompleteRate = new BigDecimal(-1);
		List<Map<String, Object>> orgKPIList = new ArrayList<>();
		String days = performanceParm.getDays();
		List<Map<String, Object>> resultList = getAllAreaOrgName(days);
		Map<String, Object> avgAreaPerformance = new HashMap<>();
		if(null != resultList && resultList.size()>0){
			StringBuffer stringBuffer = new StringBuffer();
			for (Map<String, Object> map : resultList) {
				List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
				list2= (List<Map<String, Object>>) map.get("orgNameList");
				for (Map<String, Object> map2 : list2) {
					stringBuffer.append(map2.get("orgName")+",");
				}
			}
			stringBuffer = stringBuffer.deleteCharAt(stringBuffer.length() - 1);
			String str = stringBuffer.toString();
			Map<String, Object> parm1 = new HashMap<>();
			parm1.put("dateString", days);
			String[] orgNameList = str.split(",");
			parm1.put("orgNameList", orgNameList);
			orgKPIList = moneymgrOrgAccumuPerfromancePageMapper.findAreaPerformanceByOrgName(parm1);
			if(null == orgKPIList || orgKPIList.size() == 0){
				orgKPIList = new ArrayList<>();
			}
			avgAreaPerformance = getAvgAreaPerformance(days);
		}
		areaOrgList.add(avgAreaPerformance);
		data.put("areaCompleteRateList", areaOrgList);
		return new ReturnMsgData("0000", "调用成功", data);
	}

	/**
	 * 获得当日机构总数
	 * @param dateString
	 * @return
	 */
	private int getCountOrgNum(String dateString){
		return moneymgrOrgAccumuPerformanceMapper.countOrgNum(dateString);
	}

	/**
	 * 理财H5接口 各机构当月累计达成率
	 * @return
	 */
	public ReturnMsgData findAreaPerformanceByOrgName(Map<String, Object> parmMap){
		List<Map<String, Object>> areaOrgList = moneymgrOrgAccumuPerfromancePageMapper.findAreaPerformanceByOrgName(parmMap);
		if(areaOrgList == null || areaOrgList.size() == 0){
			areaOrgList = new ArrayList<>();
		}
		String dateString = parmMap.get("dateString").toString();
		Map<String, Object> avgAreaPerformance = getAvgAreaPerformance(dateString);
		areaOrgList.add(avgAreaPerformance);
		

		//日业绩页面结果数据
		MoneymgrPerformanceDailyPageExample moneymgrPerformanceDailyPageExample = new MoneymgrPerformanceDailyPageExample();
		MoneymgrPerformanceDailyPageExample.Criteria criteriaPage = moneymgrPerformanceDailyPageExample.createCriteria();
		criteriaPage.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dateString));
		List<MoneymgrPerformanceDailyPage> dayList = moneymgrPerformanceDailyPageMapper.selectByExample(moneymgrPerformanceDailyPageExample);
		MoneymgrPerformanceDailyPage moneymgrPerformanceDailyPage = new MoneymgrPerformanceDailyPage();
		Map<String, Object> data = new HashedMap();
		if (dayList==null || dayList.size()==0) {
			data.put("performanceOrgCurrentDay", moneymgrPerformanceDailyPage);
			logger.info("日期为："+dateString+"的 《日业绩页面结果数据》 is Null");
			//return new ReturnMsgData("9999", "日业绩页面结果数据 is Null");
		}else{
			moneymgrPerformanceDailyPage = dayList.get(0);
			Map<String, Object> inData = new HashedMap();
			inData.put("performanceCurrentDay", moneymgrPerformanceDailyPage.getAvgAccumuPerformance());
			inData.put("accumuWarningLine", moneymgrPerformanceDailyPage.getAccumuWarningLine());
			inData.put("accumuWellLine", moneymgrPerformanceDailyPage.getAccumuWellLine());
			inData.put("warningIndex", this.getIndexValue("moneyMgr_month_perform_warning"));
			inData.put("wellIndex", this.getIndexValue("moneyMgr_month_perform_well"));
			data.put("performanceOrgCurrentDay", inData);
		}		
		
		data.put("allOrgMonthList", areaOrgList);
		return new ReturnMsgData("0000", "调用成功", data);
	}

	/**
	 * 计算 全国平均值 （理财H5接口 各机构当月累计达成率）
	 * @param data
	 * @return
	 */
	private Map<String, Object> getAvgAreaPerformance(String dateString){
		Map<String, Object> avgData = new HashMap<String, Object>();
		avgData.put("orgName", "全国平均值");
		BigDecimal notNull = new BigDecimal(-1);
		Map<String, Object> map =  moneymgrPerformanceDailyPageMapper.getAccumuPerformanceAndAccumuCompleteRate(dateString);
		BigDecimal accumuPerformance = notNull;	//月累计业绩
		BigDecimal accumuCompleteRate = notNull;//计划达成率
		BigDecimal perNewClient = notNull;
		BigDecimal counselorNumDecimal = notNull;
		BigDecimal managerNumDecimal = notNull;
		BigDecimal counselorManagerRatio = notNull;
		BigDecimal perCapitaCapacity = notNull;
		if(null != map){
			accumuPerformance  = (BigDecimal) (map.get("accumuPerformance")== null? notNull:map.get("accumuPerformance"));	//当月累计业绩
			accumuCompleteRate =  (BigDecimal) (map.get("accumuCompleteRate")==null? notNull:map.get("accumuCompleteRate"));	//计划达成率
		}
		Map<String, Object>  infoMap = moneymgrEfficiencyPageMapper.selectInfoByDate(dateString);
		if(null != infoMap){
			perNewClient = (BigDecimal) (infoMap.get("perNewClient")==null? notNull:infoMap.get("perNewClient"));	//人均新客户数
			counselorNumDecimal =  (BigDecimal) (infoMap.get("counselorNum")==null? notNull:infoMap.get("counselorNum"));	//咨询师人数
			managerNumDecimal = (BigDecimal) (infoMap.get("managerNum")==null? notNull:infoMap.get("managerNum"));	//管理员人数
			counselorManagerRatio = (BigDecimal) (infoMap.get("counselorManagerRatio")==null? notNull:infoMap.get("counselorManagerRatio"));	//咨询师人数/管理员人数
			perCapitaCapacity = (BigDecimal) (infoMap.get("perCapitaCapacity")==null? notNull:infoMap.get("perCapitaCapacity"));	//人均产能
		}
		int counselorNum = counselorNumDecimal.intValue();
		int managerNum = managerNumDecimal.intValue();
		//月累计业绩
		avgData.put("accumuPerformance", accumuPerformance);
		//计划达成率
		avgData.put("accumuCompleteRate", accumuCompleteRate);
		//人均新客户数
		avgData.put("perCapitaNewClient", perNewClient);
		//咨询师人数
		avgData.put("counselorNum", counselorNum);
		//管理员人数
		avgData.put("managerNum", managerNum);
		//咨询师/管理人员
		avgData.put("counselorManagerRatio", counselorManagerRatio);
		//本月人均产能
		avgData.put("perCapitaCapacity", perCapitaCapacity);
		return avgData;
	}


	/**
	 * 查询对应指标值
	 * @param indexCode
	 * @return
	 */
	private int getIndexValue(String indexCode) {
		IndexExample indexExample = new IndexExample();
		com.hzcf.flagship.model.IndexExample.Criteria indexCriteria = indexExample.createCriteria();
		indexCriteria.andCodeEqualTo(indexCode);
		List<Index> list = indexMapper.selectByExample(indexExample);
		return list.get(0).getValue().intValue();
	}
	
	/**
	 *查询理财区域页数据
	 * @param days
	 * @return
	 */
	@Override
	public ReturnMsgData financeForMonthData(String days, String token) {
		Map<String,Object> data = new HashMap<>();	
		/**
		 * 查询区域KPI表	f_moneymgr_district_accumu_performance_page
		 * 
		 */
		List<Map<String, Object>> monthRegionList;
		monthRegionList =  moneymgrDistrictAccumuPerformancePageMapper.selectMoneymgrEfficiencyForMonthByDay(days);
		if(monthRegionList.size() == 0 || null == monthRegionList){
			logger.info("日期为："+days+"的 《理财区域列表数据》 is Null");
		}
		//查询全国机构数	f_moneymgr_efficiency_page
		Integer allOrgNum;
		MoneymgrEfficiencyPage moneymgrEfficiencyPage = moneymgrEfficiencyPageMapper.selectByDate(days);	
		if(null == moneymgrEfficiencyPage){
			logger.info("日期为："+days+"的 全国机构数 is Null");
			allOrgNum = -1;
		}else{
			allOrgNum = moneymgrEfficiencyPage.getOrgNum();
		}
		//查询当月累计业绩	f_moneymgr_accumu_performance
		MoneymgrAccumuPerformanceExample moneymgrAccumuPerformanceExample = new MoneymgrAccumuPerformanceExample();
		Criteria createCriteria = moneymgrAccumuPerformanceExample.createCriteria();
		createCriteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(days));
		List<MoneymgrAccumuPerformance> list = moneymgrAccumuPerformanceMapper.selectByExample(moneymgrAccumuPerformanceExample);
		BigDecimal allPerformanceValue =  new BigDecimal(-1) ;
		if(list.size()!=0 && null != list ){
			MoneymgrAccumuPerformance moneymgrAccumuPerformance = list.get(0);
			allPerformanceValue = moneymgrAccumuPerformance.getPerformanceValue();
		}
		//计算机构KPI表的全国平均值
		Map<String, Object> avgAreaPerformance = getAvgAreaPerformance(days);
		
		Map<String, Object> monDisPage = new HashMap<>();
		monDisPage.put("districtName", "全国总计");
		monDisPage.put("orgNum", allOrgNum);
		monDisPage.put("accumuPerformance", allPerformanceValue);
		monDisPage.put("completeRate", avgAreaPerformance.get("accumuCompleteRate"));
		monDisPage.put("perCapitaNewClient", avgAreaPerformance.get("perCapitaNewClient"));
		monDisPage.put("perCapitaCapacity", avgAreaPerformance.get("perCapitaCapacity"));
		monDisPage.put("counselorManagerRatio", avgAreaPerformance.get("counselorManagerRatio"));
		
		monthRegionList.add(monDisPage);
		//查询区域指标（警戒线和良好线）	f_moneymgr_performance_daily_page
		MoneymgrPerformanceDailyPageExample moneymgrPerformanceDailyPageExample = new MoneymgrPerformanceDailyPageExample();
		com.hzcf.flagship.model.MoneymgrPerformanceDailyPageExample.Criteria createCriteria2 = moneymgrPerformanceDailyPageExample.createCriteria();
		createCriteria2.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(days));
		List<MoneymgrPerformanceDailyPage> moneymgrPerformanceDailyPageList = moneymgrPerformanceDailyPageMapper.selectByExample(moneymgrPerformanceDailyPageExample);
		Integer accumuWarningLine = -1;
		Integer accumuWellLine = -1;
		if(moneymgrPerformanceDailyPageList.size()!=0 && null !=moneymgrPerformanceDailyPageList){
			MoneymgrPerformanceDailyPage moneymgrPerformanceDailyPage = moneymgrPerformanceDailyPageList.get(0);
			accumuWarningLine = moneymgrPerformanceDailyPage.getAccumuWarningLine();
			accumuWellLine = moneymgrPerformanceDailyPage.getAccumuWellLine();
		}
		/**
		 * 查询区域机构KPI表
		 */
		BigDecimal avgCompleteRate = (BigDecimal) avgAreaPerformance.get("accumuCompleteRate");
		BigDecimal averageCompleteRate = avgCompleteRate.setScale(0, BigDecimal.ROUND_HALF_UP);
		List<Map<String, Object>> areaOrgList = new ArrayList<>();
		//获取所有的区域和机构名称
		List<Map<String, Object>> resultList = getAllAreaOrgName(days);
		//将机构名称拼接为字符串
		if(resultList != null && resultList.size()>0){
			StringBuffer stringBuffer = new StringBuffer();
			for (Map<String, Object> map : resultList) {
				List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
				list2= (List<Map<String, Object>>) map.get("orgNameList");
				for (Map<String, Object> map2 : list2) {
					stringBuffer.append(map2.get("orgName")+",");
				}
			}
			stringBuffer = stringBuffer.deleteCharAt(stringBuffer.length() - 1);
			String str = stringBuffer.toString();
			//根据机构名称查询查询机构KPI表
			String[] orgNameList = str.split(",");
			Map<String, Object> parm = new HashMap<>();
			parm.put("dateString", days);
			parm.put("orgNameList", orgNameList);
			areaOrgList = moneymgrOrgAccumuPerfromancePageMapper.findAreaPerformanceByOrgName(parm);
			if(areaOrgList == null || areaOrgList.size() == 0){
				areaOrgList = new ArrayList<>();
			}
			
			areaOrgList.add(avgAreaPerformance);
		}
		
		/**
		 * 查询所有区域和机构名称（供区域选择机构时使用）
		 */
		List<Map<String, Object>> allAreaOrgNameList = getAllAreaOrgName(days);
		/**
		 * 机构指标值
		 */
		int orgWarningLine = getIndexValue("moneyMgr_daily_accumu_warning");
		int orgWellLine = getIndexValue("moneyMgr_daily_accumu_well");
		/**
		 * 时间进度
		 */
		Integer dateSchedule = getTimeSchedule(days);
		/**
		 * 组装返回数据	averageCompleteRate
		 */
		data.put("monthRegionList", monthRegionList);
		data.put("areaWarningLine", accumuWarningLine);
		data.put("areaWellLine", accumuWellLine);
		data.put("orgWarningLine", orgWarningLine);
		data.put("orgWellLine", orgWellLine);
		data.put("dateSchedule", dateSchedule);
		data.put("averageCompleteRate", averageCompleteRate);
		data.put("areaOrgList", areaOrgList);
		data.put("mapUrl", PropertyUtil.getInfo("mapUrl")+"?days="+days+"&token="+token);
		data.put("areaNameList", allAreaOrgNameList);
		return new ReturnMsgData("0000", "调用成功", data);
	}
	
	/**
	 * 业务监控室首页数据	
	 * @param days	f_finance_personal_info
	 * @return
	 */
	@Override
	public ReturnMsgData findIndexPageInfo(String days) {
		
		Map<String,Object> data = new HashMap<>();	
		/**
		 * 查询融资数据
		 */
		Map<String, Object> financeData = getFinancePerformanceData(days);
		
		Map<String, Object> moneymgrData = new HashMap<>();
		/**
		 * 理财数据	f_moneymgr_performance_daily_page
		 */
		MoneymgrPerformanceDailyPageExample moneymgrPerformanceDailyPageExample1 = new MoneymgrPerformanceDailyPageExample();
		MoneymgrPerformanceDailyPageExample.Criteria criteriaPage = moneymgrPerformanceDailyPageExample1.createCriteria();
		criteriaPage.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(days));
		List<MoneymgrPerformanceDailyPage> dayList = moneymgrPerformanceDailyPageMapper.selectByExample(moneymgrPerformanceDailyPageExample1);
		if (null==dayList || dayList.size()==0) {
			logger.info("日期为："+days+"的 《理财结果数据》 is Null");
		}else{
			MoneymgrPerformanceDailyPage moneymgrPerformanceDailyPage = dayList.get(0);
			moneymgrData.put("monthPlan", moneymgrPerformanceDailyPage.getMonthPlan() == null? -1:moneymgrPerformanceDailyPage.getMonthPlan());
			moneymgrData.put("accumuPerformance", moneymgrPerformanceDailyPage.getAccumuPerformance() == null? -1:moneymgrPerformanceDailyPage.getAccumuPerformance());
			moneymgrData.put("performanceValue", moneymgrPerformanceDailyPage.getPerformanceValue() == null? -1:moneymgrPerformanceDailyPage.getPerformanceValue());
			moneymgrData.put("dateSchedule", getTimeSchedule(days));
			Integer accumuCompleteRate = moneymgrPerformanceDailyPage.getAccumuCompleteRate() == null? -1:moneymgrPerformanceDailyPage.getAccumuCompleteRate();
			moneymgrData.put("accumuCompleteRate", accumuCompleteRate);
			moneymgrData.put("accumuWarningLine", moneymgrPerformanceDailyPage.getAccumuWarningLine() == null? -1:moneymgrPerformanceDailyPage.getAccumuWarningLine());
			moneymgrData.put("accumuWellLine", moneymgrPerformanceDailyPage.getAccumuWellLine() == null? -1:moneymgrPerformanceDailyPage.getAccumuWellLine());
		}
		/**
		 * 查询风控数据
		 */
		Map<String,Object> riskData = new HashMap<String, Object>();
		//判断是否为当天时间
		if(DateTimeUtil.compare_date(DateTimeUtil.getNowDateNormalString(), days) == 0){
			days = DateTimeUtil.getIntervalDaysLater(days, -1);
		}
		packageRiskData(days, riskData);
		
		/**
		 * 首页返回数据
		 */
		data.put("riskData", riskData);
		data.put("financeData", financeData);
		data.put("moneymgrData", moneymgrData);
		return new ReturnMsgData("0000", "调用成功", data);
	}

	private void packageRiskData(String days, Map<String, Object> riskData) {
		String month = DateUtil.getMonth(days);
		String[] split = month.split(",");
		String thisMonth = getMonth(split[0]);
		riskData.put("thisMonth", thisMonth);
		//查询目标值
		Map<String,Object> map  = new HashMap<String,Object>();
		map.put("orgNo", "company");
		map.put("month", split[0]);
		RiskPlan riskPlan = riskPlanMapper.findPlanByMonthAndOrgNo(map);
		if(null != riskPlan){
			BigDecimal cm1Plan= riskPlan.getCm1Rate();
			 double cm1plan = cm1Plan.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			riskData.put("plan", cm1plan);
		}else{
			riskData.put("plan", -1);
		}
		//查询实际值
		PerformanceParm performanceParm = new PerformanceParm();
		performanceParm.setType("1");
		performanceParm.setMonth(split[0]);
		performanceParm.setDays(days);
		List<Map<String,Object>> firstPageListWithSum =  riskLoanCm1Mapper.firstPageListWithSum(performanceParm);
		String str = (String) firstPageListWithSum.get(firstPageListWithSum.size()-1).get("actualValue");
		if("--".equals(str)){
			riskData.put("cm1Sum", -1);
		}else{
			riskData.put("cm1Sum",Double.valueOf(str.split("%")[0]));
		}
	}
	
	/**
	 * 查询融资数据
	 */
	public Map<String, Object> getFinancePerformanceData(String days) {
		/**
		 * 融资 -- 月计划放款金额	f_finance_personal_info
		 */
		Map<String,Object> financeData = new HashMap<String, Object>();
		int monthPlan ;
		try {
			FinancePersonalInfoExample financePersonalInfoExample = new FinancePersonalInfoExample();
			com.hzcf.flagship.model.FinancePersonalInfoExample.Criteria financePersonalInfoCriteria = financePersonalInfoExample.createCriteria();
			financePersonalInfoCriteria.andManageOfficeEqualTo("整体");
			financePersonalInfoCriteria.andOrgNameEqualTo("合计");
			financePersonalInfoCriteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(DateTimeUtil.getFirstDayOfMonth(days)));
			List<FinancePersonalInfo> list = financePersonalInfoMapper.selectByExample(financePersonalInfoExample);
			if (null== list|| list.size()==0) {
				monthPlan=-1;
			}else {
				monthPlan=list.get(0).getMonthPlan();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.info("日期为："+days+" 融资 -- 月计划放款金额 is Null");
			monthPlan = -1;
		}
		/**
		 * 融资 -- 查询融资月累计放款金额	 当日放款金额    f_finance_daily_performance
		 */
		BigDecimal accumuLoanValue = new BigDecimal(-1);
		BigDecimal loanValue = new BigDecimal(-1);
		List<FinanceDailyPerformance> financeDailyPerformanceList =  financeDailyPerformanceMapper.selectByDate(days);
		if(null != financeDailyPerformanceList && financeDailyPerformanceList.size() != 0){
			if(null != financeDailyPerformanceList.get(0).getAccumuLoanValue()){
				accumuLoanValue = financeDailyPerformanceList.get(0).getAccumuLoanValue().setScale(0, BigDecimal.ROUND_HALF_UP);
			}
			if(null != financeDailyPerformanceList.get(0).getLoanValue()){
				loanValue = financeDailyPerformanceList.get(0).getLoanValue().setScale(0, BigDecimal.ROUND_HALF_UP);
			}
		}
		/**
		 * 融资 -- 当月累计达成率
		 */
		int monthCumulativeRate = 0;
		int compareTo = accumuLoanValue.compareTo(BigDecimal.ZERO);
		if(monthPlan > 0 && compareTo == 1){
			BigDecimal monthPlanDec = new BigDecimal(monthPlan);
			BigDecimal bigDecimal = new BigDecimal(100);
			BigDecimal divide = accumuLoanValue.divide(monthPlanDec,2, BigDecimal.ROUND_DOWN);//舍弃小数点后面的值
			monthCumulativeRate = divide.multiply(bigDecimal).intValue();
			//如果结果为0,则将结果统一成1
			if (monthCumulativeRate==0) {
				monthCumulativeRate=1;
			}
		}
		/**
		 * 融资 -- 获取当前时间进度	f_moneymgr_performance_daily_page
		 */
		Integer dateSchedule = getTimeSchedule(days);
		/**
		 * 融资---指标值
		 */
		BigDecimal financemgrDailyPlanyieldrateWarning = getDailyPageVal(days,"financeMgr_daily_planYieldRate_warning");
		BigDecimal financemgrDailyPlanyieldrateWell = getDailyPageVal(days,"financeMgr_daily_planYieldRate_well");
		int wellIndex  = getFinanceLine(days,financemgrDailyPlanyieldrateWarning);
		int warningIndex  = getFinanceLine(days,financemgrDailyPlanyieldrateWell);
		/**
		 * 组装融资返回数据
		 */
		financeData.put("monthPlan", monthPlan);
		financeData.put("accumuLoanValue", accumuLoanValue);
		financeData.put("loanValue", loanValue);
		financeData.put("monthCumulativeRate", monthCumulativeRate);
		financeData.put("dateSchedule", dateSchedule);
		financeData.put("warningIndex", warningIndex);
		financeData.put("wellIndex", wellIndex);
		return financeData;
	}
	/**
	 * 根据名称和日期获取val
	 * @param days
	 * @param dataName
	 * @return
	 */
	private BigDecimal getDailyPageVal(String days, String dataName) {
		Map<String, Object> params = new HashMap<>();
		params.put("dataName", dataName);
		params.put("days", days);
		BigDecimal dataVal = financeDailyPageMapper.selectByDataName(params);
		if(null == dataVal){
			dataVal = new BigDecimal(-1);
		}
		return dataVal;
	}
	/**
	 * 计算时间进度
	 * @param dateString 格式为yyyy-MM-dd
	 * @return
	 */
	private int getTimeSchedule(String dateString){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateTimeUtil.convertAsDateString(dateString));
		BigDecimal pastDays = new BigDecimal(calendar.get(Calendar.DAY_OF_MONTH));//已过天数
		BigDecimal monthDays = new BigDecimal(DateTimeUtil.getLastMonthDays(dateString));//当月总天数
		BigDecimal rate = pastDays.divide(monthDays,2,BigDecimal.ROUND_HALF_UP);//舍弃小数点后的数
		return rate.multiply(new BigDecimal(100)).intValue();//返回整数部分
	}

	/**
	 * 计算指标（理财首页）
	 * @param dateString 格式为yyyy-MM-dd
	 * @return
	 */
	private int getFinanceLine(String dateString, BigDecimal line){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateTimeUtil.convertAsDateString(dateString));
		BigDecimal pastDays = new BigDecimal(calendar.get(Calendar.DAY_OF_MONTH));//已过天数
		BigDecimal monthDays = new BigDecimal(DateTimeUtil.getLastMonthDays(dateString));//当月总天数
		BigDecimal rate = pastDays.divide(monthDays,4,BigDecimal.ROUND_HALF_UP);
		BigDecimal subtract = rate.multiply(new BigDecimal(100)).subtract(line);
		BigDecimal setScale = subtract.setScale(0, BigDecimal.ROUND_HALF_UP);
		 return setScale.intValue();
	}
	
	/**
	 * 转换日期
	 * 201709 转为  2017年9月
	 * @param date
	 * @return
	 * @author guodong
	 */
	public String getMonth(String date){
		String month ;
		String year = date.substring(0, 4) + "年";
		String str = date.substring(4, 5);
		if("0".equals(str)){
			month = date.substring(5,6) + "月";
		}else{
			month = date.substring(4,6) + "月";
		}
		return year+month;
	}
}
