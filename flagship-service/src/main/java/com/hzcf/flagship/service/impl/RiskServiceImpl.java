package com.hzcf.flagship.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.AppPageModelMapper;
import com.hzcf.flagship.dao.FileMappingMapper;
import com.hzcf.flagship.dao.RiskLoanCm1Mapper;
import com.hzcf.flagship.dao.RiskOrgDataMapper;
import com.hzcf.flagship.dao.RiskOrgStructMapper;
import com.hzcf.flagship.dao.RiskOverdueMapper;
import com.hzcf.flagship.dao.RiskSeparateMapper;
import com.hzcf.flagship.dao.RiskSubcenterMapper;
import com.hzcf.flagship.dao.RiskWarningLevelMapper;
import com.hzcf.flagship.form.PerformanceParm;
import com.hzcf.flagship.model.FileMapping;
import com.hzcf.flagship.service.RiskService;
import com.hzcf.flagship.util.DateTimeUtil;
import com.hzcf.flagship.util.DateUtil;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.PropertyUtil;
import com.hzcf.flagship.util.StringUtil;
import com.hzcf.flagship.vo.ReturnMsgData;


@Service
public class RiskServiceImpl implements RiskService{

	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private RiskLoanCm1Mapper riskLoanCm1Mapper;
	@Autowired
	private RiskOrgStructMapper riskOrgStructMapper;
	@Autowired
	private RiskWarningLevelMapper riskWarningLevelMapper;
	@Autowired
	private RiskSeparateMapper riskSeparateMapper;
	@Autowired
	private RiskOrgDataMapper riskOrgDataMapper;
	@Autowired
	private RiskSubcenterMapper riskSubcenterMapper;
	@Autowired
	private RiskOverdueMapper riskOverdueMapper;
	@Autowired
	private FileMappingMapper fileMappingMapper;
	@Autowired
	private AppPageModelMapper appPageModelMapper;
	static final String helpUrlWithRiskWarning = PropertyUtil.getInfo("helpUrlWithRiskWarning");
	/**
	 * 执委首页
	 */
	@Override
	public ReturnMsgData riskZWFirstPage(PerformanceParm performanceParm) throws ParseException {
		Map<String,Object> data = new HashMap<String,Object>();
		//模块化展示信息
		List<String> displayContents = appPageModelMapper.getRiskZWModel();
		data.put("displayContents", displayContents);
		String date = performanceParm.getDays();
		String month = DateUtil.getMonth(date);
		String[] split = month.split(",");
		//展示一个月
		if(split.length == 1){
			getFirstMonth(performanceParm, data, split);
		}else{
			getFirstMonth(performanceParm, data, split);
			getNextMonth(performanceParm, data, split);
		}
		return new ReturnMsgData("0000", "调用成功", data);
	}
	/**
	 * 执委首页（月）
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ReturnMsgData riskMonthZWFirstPage(PerformanceParm performanceParm) {
		Map<String,Object> data = new HashMap<String,Object>();
		//模块化展示信息
		List<String> displayContents = appPageModelMapper.getRiskZWModel();
		data.put("displayContents", displayContents);
		//查找贷后分割点
		String month = performanceParm.getMonth();
		int maxDateOfMonth = DateUtil.getMaxDateOfMonth(month);
		String param = month+"-"+maxDateOfMonth;
		Map<String, Object> separateMap = riskSeparateMapper.selectSeparate(param);
		data.put("separate", separateMap.get("separate")); //分割点
		//查询首页折线图数据(查询实际值和目标值)
		Map<String, Object> monthAndRecordDateList = getZWChartList(performanceParm, data, month);
		String startMonth = riskLoanCm1Mapper.getRiskMonthStartDate();
		//查询表格CM1数据
		List<LinkedHashMap<String, Object>> tableList;
		tableList =  (List<LinkedHashMap<String, Object>>) monthAndRecordDateList.get("tableList");
		String startDate = (String) tableList.get(tableList.size()-1).get("recordDate");
		String endDate =  (String) tableList.get(0).get("recordDate");
		getZWTableListCm1(data, startDate, endDate);
		String dateResult = getThisYearMonth(startDate,endDate);
	    data.put("thisYearMonth", dateResult);
		data.put("startMonth", startMonth);
		return new ReturnMsgData("0000", "调用成功", data);
	}
	
	private Map<String, Object> getZWChartList(PerformanceParm performanceParm, Map<String, Object> data,
			String month) {
		List<Map<String, Object>> chartList = new ArrayList<Map<String, Object>>();
		List<LinkedHashMap<String, Object>> recordList;
		Map<String, Object> monthAndRecordDateList = getMonthAndRecordDateList(month);
		recordList =  (List<LinkedHashMap<String, Object>>) monthAndRecordDateList.get("list");
	    Collections.reverse(recordList); 
		for (LinkedHashMap<String, Object> map : recordList) {
			performanceParm.setMonth(String.valueOf(map.get("month")));
			performanceParm.setDays(String.valueOf(map.get("recordDate")));
			performanceParm.setType("1");
			performanceParm.setOrgNo("fengkongglb");
			LinkedHashMap<String, Object> chartMap = riskLoanCm1Mapper.getMonthChartListData(performanceParm);
			chartList.add(chartMap);
		}
		data.put("chartList", chartList);
		return monthAndRecordDateList;
	}
	
	private void getZWTableListCm1(Map<String, Object> data, String startDate, String endDate) {
		Map<String,Object> requestMap = new HashMap<String,Object>();
		String  childOrgNos= getChildOrgNos("fengkongzw",3);
		ArrayList<Object> firstZJList  = new ArrayList<>();
		ArrayList<Object> firstKHList  = new ArrayList<>();
		ArrayList<Object> firstDHList  = new ArrayList<>();
		if(null !=  childOrgNos){
			requestMap.put("startDate", startDate);
			requestMap.put("endDate", endDate);
			requestMap.put("orgNos", childOrgNos);
			requestMap.put("dataType", "2");
			//整体
			requestMap.put("type", "1");
			List<LinkedHashMap<String, Object>> firstZJResultList = riskLoanCm1Mapper.getTableListCm1(requestMap);
		    firstZJList = getCustomList(firstZJResultList,"事业部");
			
			//X期前
			requestMap.put("type", "2");
			List<LinkedHashMap<String, Object>> firstKHResultList = riskLoanCm1Mapper.getTableListCm1(requestMap);
			firstKHList = getCustomList(firstKHResultList,"事业部");
		
			//X期后
			requestMap.put("type", "3");
			List<LinkedHashMap<String, Object>> firstDHResultList = riskLoanCm1Mapper.getTableListCm1(requestMap);
		    firstDHList = getCustomList(firstDHResultList,"事业部");
			
		}
		data.put("firstZJList", firstZJList);
		data.put("firstKHList", firstKHList);
		data.put("firstDHList", firstDHList);
		
	}

	 private String getThisYearMonth(String startDate, String endDate) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM");
			Calendar startCal = Calendar.getInstance();
			Date startParse = df.parse(startDate);
			startCal.setTime(startParse);
			
			Calendar endCal = Calendar.getInstance();
			Date endParse = df.parse(endDate);
			endCal.setTime(endParse);
	        int startYear = startCal.get(Calendar.YEAR);
	        int endMonth = endCal.get(Calendar.MONTH )+1;
	        String dateResult;
	        if(endMonth == 1){
				dateResult = startYear+ "年"+"1"+"月";
			}else{
				dateResult = startYear+ "年"+"1"+"-"+endMonth+"月";
			}
			return dateResult;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	 /**
		 * 风控市场管理部首页
		 */
		@SuppressWarnings("unused")
		@Override
		public ReturnMsgData riskSCFirstPage(PerformanceParm performanceParm) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("recordDate", performanceParm.getDays());
			Map<String, Object> resultMap = new HashMap<String, Object>();
			//模块化展示信息
			List<String> displayContents = appPageModelMapper.getRiskManagerModel();
			resultMap.put("displayContents", displayContents);
			String month = DateUtil.getMonth(performanceParm.getDays());
			String[] split = month.split(",");
			map.put("month", split[0]);
			List<LinkedHashMap<String, Object>> list = riskLoanCm1Mapper.selectCM1BackRate(map);
			Map<String, Object> separateMap = riskSeparateMapper.selectSeparate(performanceParm.getDays());
			LinkedHashMap<String, Object> linkedHashMap = list.get(list.size()-1);
			resultMap.put("thisMonth", getMonth(split[0]));	//月份
			resultMap.put("actualVal", linkedHashMap.get("actualVal"));	//实际值
			resultMap.put("planVal", linkedHashMap.get("planVal"));	//目标值
			//事业部维度
		   	resultMap.put("cm1BackRateList", list.subList(0, list.size()-1));	//本月C-M1回款率list
			resultMap.put("cm1BackRateInfoList", list);	//本月C-M1回款率详细数据
			
			//查询分中心cm1List
			List<Map<String,Object>>  subcenterCm1List = riskSubcenterMapper.findSubcenterCm1List(map);
			//查询分中心Cm1List 总计
			Map cm1Sum = riskSubcenterMapper.findSubcenterCm1ListSum(map);
			//查询分中心Cm1List 目标值
			Map plan = riskSubcenterMapper.findSubcenterCm1Plan(map);
			List<Map<String, Object>> packedData = packedData(cm1Sum,plan,subcenterCm1List);
			resultMap.put("subcenterCm1List",  packedData);
			resultMap.put("isShowNextMonth", 0); //是否展示下月 0： 不展示  1：展示
			resultMap.put("separate", separateMap.get("separate")); //分割点
			if(split.length>1){ //两个月的数据
				map.put("month", split[1]);
				resultMap.put("isShowNextMonth", 1);
				resultMap.put("nextMonth", getMonth(split[1]));
				//查询分中心cm1List
				List<Map<String,Object>>  nextSubcenterCm1List = riskSubcenterMapper.findSubcenterCm1List(map);
				//查询分中心Cm1List 总计
				Map<String,Object> nextSumMap = new HashMap<String,Object>();
				Map nextCm1Sum = riskSubcenterMapper.findSubcenterCm1ListSum(map);
				//查询分中心Cm1List 目标值
				Map nextPlan = riskSubcenterMapper.findSubcenterCm1Plan(map);
				resultMap.put("nextMonthSubcenterCm1List",packedData(nextCm1Sum,nextPlan,nextSubcenterCm1List));
				//事业部维度
				resultMap.put("nextMonthCm1BackRateInfoList", riskLoanCm1Mapper.selectCM1BackRate(map));

			}
			return new ReturnMsgData("0000", "调用成功", resultMap);
		}
		/**
		 * 风控市场管理部首页（月）
		 */
		@Override
		public ReturnMsgData riskMonthSCFirstPage(PerformanceParm performanceParm) {
			Map<String,Object> data = new HashMap<String,Object>();
			//模块化展示信息
			List<String> displayContents = appPageModelMapper.getRiskManagerModel();
			data.put("displayContents", displayContents);
			//查找贷后分割点
			String month = performanceParm.getMonth();
			int maxDateOfMonth = DateUtil.getMaxDateOfMonth(month);
			String param = month+"-"+maxDateOfMonth;
			Map<String, Object> separateMap = riskSeparateMapper.selectSeparate(param);
			data.put("separate", separateMap.get("separate")); //分割点
			//查询首页折线图数据(查询实际值和目标值)
			Map<String, Object> monthAndRecordDateList = getScChartList(performanceParm, data, month);
			String startMonth = riskLoanCm1Mapper.getRiskMonthStartDate();
			//查询表格数据
			List<LinkedHashMap<String, Object>> tableList;
			tableList =  (List<LinkedHashMap<String, Object>>) monthAndRecordDateList.get("tableList");
			String startDate = (String) tableList.get(tableList.size()-1).get("recordDate");
			String endDate =  (String) tableList.get(0).get("recordDate");
			getSCTableListCm1(data, startDate, endDate);
			String dateResult = getThisYearMonth(startDate,endDate);
		    data.put("thisYearMonth", dateResult);
			data.put("startMonth", startMonth);
			return new ReturnMsgData("0000", "调用成功", data);
		}
		private void getSCTableListCm1(Map<String, Object> data, String startDate, String endDate) {
			Map<String,Object> requestMap = new HashMap<String,Object>();
			String childOrgNos = getChildOrgNos("fengkongglb",3);
			ArrayList<Object> cm1BackRateInfoList = new ArrayList<>();
			ArrayList<Object> subcenterCm1List = new ArrayList<>();
			if(null != childOrgNos){
				requestMap.put("startDate", startDate);
				requestMap.put("endDate", endDate);
				requestMap.put("orgNos", childOrgNos);
				requestMap.put("type", "2");
				requestMap.put("dataType", "2");
				List<LinkedHashMap<String, Object>> resultList = riskLoanCm1Mapper.getTableListCm1(requestMap);
			    cm1BackRateInfoList = getCustomList(resultList,"事业部");
				
				Map<String,Object> subcenterMap = new HashMap<String,Object>();
				subcenterMap.put("startDate", startDate);
				subcenterMap.put("endDate", endDate);
				requestMap.put("dataType", "2");
				List<LinkedHashMap<String, Object>> subcenterResultList = riskLoanCm1Mapper.getSubcenterTableListCm1(requestMap);
			    subcenterCm1List = getCustomList(subcenterResultList,"分中心");
			}
			data.put("cm1BackRateInfoList", cm1BackRateInfoList);
			data.put("subcenterCm1List", subcenterCm1List);
			
		}
		private Map<String, Object> getScChartList(PerformanceParm performanceParm, Map<String, Object> data,
				String month) {
			List<Map<String, Object>> chartList = new ArrayList<Map<String, Object>>();
			List<LinkedHashMap<String, Object>> recordList;
			
			Map<String, Object> monthAndRecordDateList = getMonthAndRecordDateList(month);
			recordList =  (List<LinkedHashMap<String, Object>>) monthAndRecordDateList.get("list");
		    Collections.reverse(recordList); 
			for (LinkedHashMap<String, Object> map : recordList) {
				performanceParm.setMonth(String.valueOf(map.get("month")));
				performanceParm.setDays(String.valueOf(map.get("recordDate")));
				performanceParm.setType("2");
				performanceParm.setOrgNo("fengkongglb");
				LinkedHashMap<String, Object> chartMap = riskLoanCm1Mapper.getMonthChartListData(performanceParm);
				chartList.add(chartMap);
			}
			data.put("chartList", chartList);
			return monthAndRecordDateList;
		}
	
	
	private ArrayList<Object> getCustomList(List<LinkedHashMap<String, Object>> subcenterResultList,String customColumn) {
		ArrayList<Object> resultList = new ArrayList<>(); 
		if(null != subcenterResultList && subcenterResultList.size()>0){
			 LinkedHashMap<String, Object> linkedHashMap = subcenterResultList.get(0);
			Set<String> keySet = linkedHashMap.keySet();
			LinkedHashMap<String, Object> keyMap = new LinkedHashMap<String, Object>();
			for (String key : keySet) {
				if("机构".equals(key)){
					keyMap.put(key, customColumn);	
				}else{
					keyMap.put(key, key);
				}
			}
			subcenterResultList.add(0, keyMap);
		}
		
		for (LinkedHashMap<String, Object> linkedHashMap2 : subcenterResultList) {
				ArrayList<Object> list = new ArrayList<>(); 
				for (Map.Entry<String, Object> entry : linkedHashMap2.entrySet()) {
					  list.add(entry.getValue());
				 }
				resultList.add(list);
			}
		return resultList;
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

	/**
	 *  风控-市场管理部点击事业部下钻
	 */
	@Override
	public ReturnMsgData riskAreaPage(PerformanceParm performanceParm) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<LinkedHashMap<String, Object>> businessWaringList = new ArrayList<LinkedHashMap<String, Object>>();
		ArrayList<Object> childData = new ArrayList<Object>();
		//组装事业部下钻图标数据
		List<Map<String,Object>> chartList = new ArrayList<Map<String,Object>>();
		String childOrgNos = getChildOrgNos(performanceParm.getOrgNo(),4);
		if(StringUtil.isNotBlank(childOrgNos)){	//下级不为空
			childData = getChildData(performanceParm.getDays(), performanceParm.getMonth(),
					childOrgNos,performanceParm.getType(),"大区");
			if(null !=childData && childData.size()>0){
				Map<String, Object> orgMap = new HashMap<String, Object>();
				orgMap.put("rank", 5);
				orgMap.put("recordDate", performanceParm.getDays());
				orgMap.put("orgNo", performanceParm.getOrgNo());
				businessWaringList = riskWarningLevelMapper.selectWarningLeve(orgMap);
			}
			for (int i = 1; i < childData.size(); i++) {
				Map<String,Object> map = new HashMap<String,Object>();
				List list = (List) childData.get(i);
				map.put("orgName", String.valueOf(list.get(1)));
				map.put("sumValue", Double.valueOf(String.valueOf(list.get(2)).split("%")[0]));
				chartList.add(map);	
			}
		}
		resultMap.put("url", helpUrlWithRiskWarning);
		resultMap.put("chartList", chartList);
		resultMap.put("areaList", childData);
		resultMap.put("businessWaringList", businessWaringList);
		return new ReturnMsgData("0000", "调用成功", resultMap);
	}
	
	/**
	 * 风控-执委或市场管理部点击事业部下钻(月)
	 * @param performanceParm
	 * @return
	 * @author tie
	 */
	@Override
	public ReturnMsgData riskMonthAreaPage(PerformanceParm performanceParm) {
		Map<String,Object> data = new HashMap<String,Object>();
		String month = performanceParm.getMonth();
		String orgNo = performanceParm.getOrgNo();
		String type = performanceParm.getType();
		Map<String, Object> monthAndRecordDateList = getMonthAndRecordDateList(month);
		//查询表格数据
		List<LinkedHashMap<String, Object>> tableList;
		tableList =  (List<LinkedHashMap<String, Object>>) monthAndRecordDateList.get("tableList");
		String startDate = (String) tableList.get(tableList.size()-1).get("recordDate");
		String endDate =  (String) tableList.get(0).get("recordDate");
		Map<String,Object> requestMap = new HashMap<String,Object>();
		String childOrgNos = getChildOrgNos(orgNo,4);
		ArrayList<Object> areaList = new ArrayList<>();
		if(null != childOrgNos){
			requestMap.put("startDate", startDate);
			requestMap.put("endDate", endDate);
			requestMap.put("orgNos", childOrgNos);
			requestMap.put("dataType", "1");
			requestMap.put("type", type);
			List<LinkedHashMap<String, Object>> businessResultList = riskLoanCm1Mapper.getTableListCm1(requestMap);
			areaList = getCustomList(businessResultList,"大区");
		}
		data.put("areaList", areaList);
		//查询时间段
		String dateResult = getThisYearMonth(startDate,endDate);
	    data.put("thisYearMonth", dateResult);
		return new ReturnMsgData("0000", "调用成功", data);
	}
	/**
	 * 获取父级下的子级
	 * @param orgNo 机构编号
	 * @param rank 3 事业部 4：大区  5：营业部
	 * @return
	 * @author guodong
	 */
	public String getChildOrgNos(String orgNo, int rank) {
		Map<String, Object> orgMap = new HashMap<String, Object>();
		orgMap.put("orgNo", orgNo);
		orgMap.put("rank", rank);
		String orgs = null;
		List<String> orgNoList = riskOrgStructMapper.selectNewOrgNos(orgMap);
		if(null != orgNoList && orgNoList.size()>0){
			StringBuffer orgNoBuffer = new StringBuffer();
			for (String org : orgNoList) {
				orgNoBuffer.append("\'");
				orgNoBuffer.append(org);
				orgNoBuffer.append("\'");
				orgNoBuffer.append(",");
			}
			orgs = orgNoBuffer.substring(0,orgNoBuffer.length()-1);
			logger.info("父级编号为：" + orgNo + "下的所有子级编号为： " + orgs);
		}
		return orgs;
	}
	
	/**
	 * 获取 childOrgNos 的数据
	 * @param recordDate 日期
	 * @param month 月份
	 * @param childOrgNos 机构编号字符串
	 * @param type 1:总计 2:考核 3贷后
	 * @param headerKey 表头替换值为：大区或者营业部
	 * @return
	 * @author guodong
	 */
	public ArrayList<Object> getChildData(String recordDate, String month,String childOrgNos, String type, String headerKey) {
		ArrayList<Object> resultList = new ArrayList<>(); 
		List<LinkedHashMap<String, Object>> areaList = new ArrayList<LinkedHashMap<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", type);
			map.put("orgNos", childOrgNos);
			map.put("recordDate", recordDate);
			map.put("month", month);
			
			areaList = riskLoanCm1Mapper.selectAreaCM1ByOrgNos(map);
			
			if(null != areaList && areaList.size()>0){
				LinkedHashMap<String, Object> linkedHashMap = areaList.get(0);
				LinkedHashMap<String, Object> map2 = new LinkedHashMap<String, Object>();
				Set<String> keySet = linkedHashMap.keySet();
				for (String key : keySet) {
					if("大区".equals(key)){
						map2.put(key, headerKey);
					}else if(key.contains("αα")){ 
						String replaceAll = key.replaceAll("αα", ".");
						map2.put(replaceAll,replaceAll);
					}else{
						map2.put(key, key);
					}
				}
				areaList.add(0, map2);
				resultList = getResultList(areaList);
			}
		return resultList;
	}
	
	/**
	 * 重新组装返回数据
	 * @param areaList
	 * @return
	 * @author guodong
	 */
	public ArrayList<Object> getResultList(List<LinkedHashMap<String, Object>> areaList) {
		ArrayList<Object> resultList = new ArrayList<>(); 
		if(null != areaList && areaList.size() > 0){
			for (LinkedHashMap<String, Object> linkedHashMap2 : areaList) {
				ArrayList<Object> list = new ArrayList<>(); 
				for (Map.Entry<String, Object> entry : linkedHashMap2.entrySet()) {
					  list.add(entry.getValue());
				 }
				resultList.add(list);
			}
		}
		return resultList;
	}

	/**
	 * 风控-市场管理部点击大区下钻
	 */
	@Override
	public ReturnMsgData riskBusinePage(PerformanceParm performanceParm) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<Object> businessCM1ProductNameList = new ArrayList<Object>();
		ArrayList<Object> businessCM1BatchList = new ArrayList<Object>();
		String subcenter = performanceParm.getSubcenter();
		String childOrgNos;
		if(null != subcenter){
			childOrgNos = getBranchCenterChildOrgNos(performanceParm.getOrgNo(),performanceParm.getDays());			
		}else{
		    childOrgNos = getChildOrgNos(performanceParm.getOrgNo(),5);
		}
		if(StringUtil.isNotBlank(childOrgNos)){
			businessCM1ProductNameList = getChildData(performanceParm.getDays(),
					performanceParm.getMonth(),childOrgNos, performanceParm.getType(), "营业部");
			
			businessCM1BatchList = getChildBatchData(performanceParm.getDays(),
					performanceParm.getMonth(),childOrgNos, performanceParm.getType(), "营业部");
		}
		resultMap.put("businessCM1ProductNameList", businessCM1ProductNameList);
		resultMap.put("businessCM1BatchList", businessCM1BatchList);
		return new ReturnMsgData("0000", "调用成功", resultMap);
	}
	/**
	 * 大区/分中心下钻（月）
	 * @param performanceParm
	 * @return
	 * @author tie
	 */
	@Override
	public ReturnMsgData riskMonthBusinePage(PerformanceParm performanceParm) {
		Map<String, Object> data = new HashMap<String, Object>();
		String subcenter = performanceParm.getSubcenter();
		String month = performanceParm.getMonth();
		String orgNo = performanceParm.getOrgNo();
		String type = performanceParm.getType();
		List<LinkedHashMap<String, Object>> recordList;
		Map<String, Object> monthAndRecordDateList = getMonthAndRecordDateList(month);
		recordList =  (List<LinkedHashMap<String, Object>>) monthAndRecordDateList.get("tableList");
		String startDate = (String) recordList.get(recordList.size()-1).get("recordDate");
		String endDate =  (String) recordList.get(0).get("recordDate");
		ArrayList<Object> businessCM1ProductNameList = new ArrayList<Object>();
		String childOrgNos;
		if(null != subcenter){
			String recordDate = getSubcenterRecordDate(month);
			childOrgNos = getBranchCenterChildOrgNos(orgNo,recordDate);			
		}else{
		    childOrgNos = getChildOrgNos(orgNo,5);
		}
		if(StringUtil.isNotBlank(childOrgNos)){
			Map<String,Object> requestMap = new HashMap<String,Object>();
			requestMap.put("startDate", startDate);
			requestMap.put("endDate", endDate);
			requestMap.put("orgNos", childOrgNos);
			requestMap.put("dataType", "1");
			requestMap.put("type", type);
			List<LinkedHashMap<String, Object>> businessResultList = riskLoanCm1Mapper.getTableListCm1(requestMap);
			businessCM1ProductNameList = getCustomList(businessResultList,"营业部");
		}
		//查询时间段
		String dateResult = getThisYearMonth(startDate,endDate);
		data.put("thisYearMonth", dateResult);
		data.put("businessCM1ProductNameList", businessCM1ProductNameList);
		return new ReturnMsgData("0000", "调用成功", data);
	}
	
	/**
	 * 获取营业部下的 CM1回款率 （批次）
	 * @param recordDate
	 * @param month
	 * @param childOrgNos
	 * @param type
	 * @param headerKey
	 * @return
	 * @author guodong
	 */
	public ArrayList<Object> getChildBatchData(String recordDate, String month, String childOrgNos, String type,
			String headerKey) {
		ArrayList<Object> resultList = new ArrayList<>(); 
		List<LinkedHashMap<String, Object>> areaList = new ArrayList<LinkedHashMap<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("orgNos", childOrgNos);
		map.put("recordDate", recordDate);
		map.put("month", month);
		areaList = riskLoanCm1Mapper.selectbusinessCM1BatchByOrgNos(map);
		if(null != areaList && areaList.size()>0){
			LinkedHashMap<String, Object> linkedHashMap = areaList.get(0);
			LinkedHashMap<String, Object> map2 = new LinkedHashMap<String, Object>();
			Set<String> keySet = linkedHashMap.keySet();
			for (String key : keySet) {
				if("大区".equals(key)){
					map2.put(key, headerKey);
				}else{
					map2.put(key, key);
				}
			}
			areaList.add(0, map2);
			resultList = getResultList(areaList);
		}
		return resultList;
	}

	private void getFirstMonth(PerformanceParm performanceParm, Map<String, Object> data, String[] split) throws ParseException {
		data.put("isShowNextMonth", 0);
		data.put("thisMonth",getMonth(split[0]));
		performanceParm.setMonth(split[0]);
		//查找最新的贷后分割点
		Map<String, Object> separateMap = riskSeparateMapper.selectSeparate(performanceParm.getDays());
		data.put("separate", separateMap.get("separate")); //分割点
		performanceParm.setRiskLastMonthDay(DateTimeUtil.getRiskLastMonthDay(performanceParm.getDays()));
		//考核 （x期前）列表 type=2
		performanceParm.setType("2");
		List<Map<String,Object>> firstPageListWithKH =  riskLoanCm1Mapper.firstPageListWithKH(performanceParm);
		//整体(不含贷后)列表  type=1
		performanceParm.setType("1");
		List<Map<String,Object>> firstPageListWithSum =  riskLoanCm1Mapper.firstPageListWithSum(performanceParm);
		//贷后（x期后）列表 type=3
		performanceParm.setType("3");
		List<Map<String,Object>> firstPageListWithDH =  riskLoanCm1Mapper.firstPageListWithSum(performanceParm);
		//新增个贷分批次列表
		Map<String, Object> map = new HashMap<>();
		map.put("days", performanceParm.getDays());
		map.put("month", performanceParm.getMonth());
		List<LinkedHashMap<String, Object>> batchList = riskLoanCm1Mapper.getZwBatchCm1(map);
		ArrayList<Object> firstBatchList = new ArrayList<>();
		LinkedHashMap<String, Object> heards = batchList.get(0);
		Set<String> keySet = heards.keySet();
		ArrayList<Object> heardList = new ArrayList<>();
		for (String string : keySet) {
			if ("orgName".equals(string)) {
				heardList.add("");
			}else {
				heardList.add(string);
			}
		}
		firstBatchList.add(heardList);
		for (LinkedHashMap<String, Object> hashMap : batchList) {
			ArrayList<Object> itemList = new ArrayList<>();
			Set<String> keySet2 = hashMap.keySet();
			for (String key : keySet2) {
				itemList.add(hashMap.get(key));
			}
			firstBatchList.add(itemList);
		}
		//图标实际值
		String str = (String) firstPageListWithSum.get(firstPageListWithSum.size()-1).get("actualValue");
		if("--".equals(str)){
			data.put("cm1Sum", -1);
		}else{
			data.put("cm1Sum",Double.valueOf(str.split("%")[0]));
		}
		//图标计划值
		String plan = (String) firstPageListWithKH.get(firstPageListWithKH.size()-1).get("planValue");
		if("--".equals(plan)){
			data.put("plan", -1);
		}else{
			data.put("plan",Double.valueOf(plan.split("%")[0]));
		}
		data.put("firstKHList", firstPageListWithKH);
		data.put("firstZJList", firstPageListWithSum);
		data.put("firstDHList", firstPageListWithDH);
		data.put("firstBatchList",firstBatchList);
	}
	private void getNextMonth(PerformanceParm performanceParm, Map<String, Object> data, String[] split) {
		
		data.put("nextMonth",getMonth(split[1]));
		data.put("isShowNextMonth", 1);
		performanceParm.setMonth(split[1]);
		//考核 列表 type=2
		performanceParm.setType("2");
		List<Map<String,Object>> nextPageListWithKH =  riskLoanCm1Mapper.firstPageListWithKH(performanceParm);
		//总计列表  type=1
		performanceParm.setType("1");
		List<Map<String,Object>> nextPageListWithSum =  riskLoanCm1Mapper.firstPageListWithSum(performanceParm);
		//贷后列表 type=3
		performanceParm.setType("3");
		List<Map<String,Object>> nextPageListWithDH =  riskLoanCm1Mapper.firstPageListWithSum(performanceParm);
		Map<String, Object> map = new HashMap<>();
		map.put("days", performanceParm.getDays());
		map.put("month", performanceParm.getMonth());
		List<LinkedHashMap<String, Object>> batchList = riskLoanCm1Mapper.getZwBatchCm1(map);
		ArrayList<Object> nextBatchList = new ArrayList<>();
		LinkedHashMap<String, Object> heards = batchList.get(0);
		Set<String> keySet = heards.keySet();
		ArrayList<Object> heardList = new ArrayList<>();
		for (String string : keySet) {
			if ("orgName".equals(string)) {
				heardList.add("");
			}else {
				heardList.add(string);
			}
		}
		nextBatchList.add(heardList);
		for (LinkedHashMap<String, Object> hashMap : batchList) {
			ArrayList<Object> itemList = new ArrayList<>();
			Set<String> keySet2 = hashMap.keySet();
			for (String key : keySet2) {
				itemList.add(hashMap.get(key));
			}
			nextBatchList.add(itemList);
		}
		data.put("nextKHList", nextPageListWithKH);
		data.put("nextZJList", nextPageListWithSum);
		data.put("nextDHList", nextPageListWithDH);
		data.put("nextBatchList", nextBatchList);
	}

	/**
	 * 风险事业部首页
	* @Description: 
	* @param @param performanceParm
	* @param @return    
	* @return ReturnMsgData
	* @throws
	 */
	@Override
	public ReturnMsgData riskBusinessFirstPage(PerformanceParm performanceParm) {
		Map<String, Object> data = new HashMap<String, Object>();
		String date = performanceParm.getDays();
		String month = DateUtil.getMonth(date);
		String[] split = month.split(",");
		String orgNo = performanceParm.getOrgNo();
		// 查询页面总的计划值和实际值
		performanceParm.setOrgNo(orgNo);
		performanceParm.setMonth(split[0]);
		Map<String, Object> roundMap = riskLoanCm1Mapper.getRoundData(performanceParm);
		if(null != roundMap){
			data.put("cm1Sum",  roundMap.get("cm1Sum"));
			data.put("plan", roundMap.get("planValue"));
		}else{
			data.put("cm1Sum", -1);
			data.put("plan", -1);
		}
		//贷后分割点
		Map<String, Object> separateMap = riskSeparateMapper.selectSeparate(performanceParm.getDays());
		data.put("separate", separateMap.get("separate")); //分割点
		List<Map<String, Object>> chartList = new ArrayList<Map<String, Object>>();
		List<LinkedHashMap<String, Object>> recordList = getRecordList(date);
		Collections.reverse(recordList); 
		for (LinkedHashMap<String, Object> map : recordList) {
			performanceParm.setMonth(String.valueOf(map.get("month")));
			performanceParm.setDays(String.valueOf(map.get("recordDate")));
			LinkedHashMap<String, Object> chartMap = riskLoanCm1Mapper.getRoundData(performanceParm);
			chartList.add(chartMap);
		}
		data.put("chartList", chartList);
		data.put("isShowNextMonth", 0);
		data.put("thisMonth", getMonth(split[0]));
		String childOrgNos = getChildOrgNos(orgNo, 4);
		ArrayList<Object> cm1List = new ArrayList<Object>();
		if (StringUtil.isNotBlank(childOrgNos)) { // 下级不为空
			cm1List = getChildData(date, split[0], childOrgNos, 2+"", "大区");
		}
		data.put("firstCm1List", cm1List);
		// 表格展示两个月
		if (split.length > 1) {
			cm1List = getChildData(date, split[1], childOrgNos, 2+"", "大区");
			data.put("isShowNextMonth", 1);
			data.put("nextMonth", getMonth(split[1]));
			data.put("nextCm1List", cm1List);
		}
		return new ReturnMsgData("0000", "调用成功", data);
	}
	/**
	 * 
	* @Description:风控事业部（月）首页 
	* @param @param performanceParm
	* @param @return    
	* @return ReturnMsgData
	* @throws
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ReturnMsgData riskMonthBusinessFirstPage(PerformanceParm performanceParm) {
		Map<String,Object> data = new HashMap<String,Object>();
		String month = performanceParm.getMonth();
		String orgNo = performanceParm.getOrgNo();
		
		//查询首页折线图数据(查询实际值和目标值)
		Map<String, Object> monthAndRecordDateList = getBusinessChartList(performanceParm, data, month, orgNo);
		//查询起始时间
		String startMonth = riskLoanCm1Mapper.getRiskMonthStartDate();
		//查询表格数据
		List<LinkedHashMap<String, Object>> tableList;
		tableList =  (List<LinkedHashMap<String, Object>>) monthAndRecordDateList.get("tableList");
		String startDate = (String) tableList.get(tableList.size()-1).get("recordDate");
		String endDate =  (String) tableList.get(0).get("recordDate");
	
		Map<String,Object> requestMap = new HashMap<String,Object>();
		String childOrgNos = getChildOrgNos(orgNo,4);
		ArrayList<Object> businessCM1BatchList = new ArrayList<>();
		if(null != childOrgNos){
			requestMap.put("startDate", startDate);
			requestMap.put("endDate", endDate);
			requestMap.put("orgNos", childOrgNos);
			requestMap.put("dataType", "1");
			requestMap.put("type", "2");
			List<LinkedHashMap<String, Object>> businessResultList = riskLoanCm1Mapper.getTableListCm1(requestMap);
			businessCM1BatchList = getCustomList(businessResultList,"大区");
		}
		data.put("businessCM1BatchList", businessCM1BatchList);
		
		
		//查询时间段
		String dateResult = getThisYearMonth(startDate,endDate);
	    data.put("thisYearMonth", dateResult);
		data.put("startMonth", startMonth);
		return new ReturnMsgData("0000", "调用成功", data);
	}
	
	private Map<String, Object> getBusinessChartList(PerformanceParm performanceParm, Map<String, Object> data,
			String month, String orgNo) {
		List<Map<String, Object>> chartList = new ArrayList<Map<String, Object>>();
		List<LinkedHashMap<String, Object>> recordList;
		Map<String, Object> monthAndRecordDateList = getMonthAndRecordDateList(month);
		recordList =  (List<LinkedHashMap<String, Object>>) monthAndRecordDateList.get("list");
	    Collections.reverse(recordList); 
		for (LinkedHashMap<String, Object> map : recordList) {
			performanceParm.setMonth(String.valueOf(map.get("month")));
			performanceParm.setDays(String.valueOf(map.get("recordDate")));
			performanceParm.setOrgNo(orgNo);
			LinkedHashMap<String, Object> chartMap = riskLoanCm1Mapper.getRoundData(performanceParm);			chartList.add(chartMap);
		}
		data.put("chartList", chartList);
		return monthAndRecordDateList;
	}
	/**
	 * 风控-大区首页
	 */
	@Override
	public ReturnMsgData riskAreaFirstPage(PerformanceParm performanceParm) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<Object> businessCM1RateList = new ArrayList<Object>();
		ArrayList<Object> businessCM1BatchList = new ArrayList<Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("recordDate", performanceParm.getDays());
		map.put("orgNo", performanceParm.getOrgNo());
		String month = DateUtil.getMonth(performanceParm.getDays());
		String[] split = month.split(",");
		map.put("month", split[0]);
		BigDecimal actualVal = new BigDecimal(-1.00);
		BigDecimal planVal = new BigDecimal(-1);
		//查询大区的累计C-M1回款率
		List<LinkedHashMap<String, Object>> areaCM1 = riskLoanCm1Mapper.selectAreaCM1BackRate(map);
		if(null != areaCM1 && areaCM1.size()>0){
			LinkedHashMap<String, Object> linkedHashMap = areaCM1.get(0);
			actualVal = (BigDecimal) linkedHashMap.get("cm1Val");
			planVal = (BigDecimal) linkedHashMap.get("planVal");
		}
		//查询大区下各产品累计C-M1回款率
		List<LinkedHashMap<String, Object>> areaCM1ByProduct = riskLoanCm1Mapper.selectAreaCM1ByProductName(map);
		String childOrgNos = getChildOrgNos(performanceParm.getOrgNo(), 5);
		if(StringUtil.isNotBlank(childOrgNos)){	//下级不为空
			businessCM1RateList = getChildData(performanceParm.getDays(), 
					split[0], childOrgNos,2+"","营业部");
			businessCM1BatchList = getChildBatchData(performanceParm.getDays(),
					split[0],childOrgNos, 2+"", "营业部");
			resultMap.put("isShowNextMonth", 0); //是否展示下月 0： 不展示  1：展示
			if(split.length>1){ //两个月的数据
				resultMap.put("isShowNextMonth", 1);
				resultMap.put("nextMonth", getMonth(split[1]));
				resultMap.put("businessNextMonthCM1RateList",getChildData(performanceParm.getDays(), 
						split[1], childOrgNos,2+"","营业部"));
				resultMap.put("businessNextMonthCM1BatchList",getChildBatchData(performanceParm.getDays(),
						split[1],childOrgNos, 2+"", "营业部"));
			}
		}
		resultMap.put("thisMonth", getMonth(split[0]));	//月份
		resultMap.put("actualVal", actualVal);	
		resultMap.put("planVal", planVal);	
		resultMap.put("areaCM1ByProduct", areaCM1ByProduct);	
		resultMap.put("businessCM1RateList", businessCM1RateList);	
		resultMap.put("businessCM1BatchList", businessCM1BatchList);	
		//贷后分割点
		Map<String, Object> separateMap = riskSeparateMapper.selectSeparate(performanceParm.getDays());
		resultMap.put("separate", separateMap.get("separate")); //分割点
		return  new ReturnMsgData("0000", "调用成功", resultMap);
	}
	/**
	 * 风控-大区（月）首页
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public ReturnMsgData riskMonthAreaFirstPage(PerformanceParm performanceParm) {
		Map<String,Object> data = new HashMap<String,Object>();
		String month = performanceParm.getMonth();
		String orgNo = performanceParm.getOrgNo();
		
		//查询首页折线图数据(查询实际值和目标值)
		List<Map<String, Object>> chartList = new ArrayList<Map<String, Object>>();
		List<LinkedHashMap<String, Object>> recordList;
		Map<String, Object> monthAndRecordDateList = getMonthAndRecordDateList(month);
		recordList =  (List<LinkedHashMap<String, Object>>) monthAndRecordDateList.get("list");
	    Collections.reverse(recordList); 
		for (LinkedHashMap<String, Object> map : recordList) {
			performanceParm.setMonth(String.valueOf(map.get("month")));
			performanceParm.setDays(String.valueOf(map.get("recordDate")));
			performanceParm.setOrgNo(orgNo);
			LinkedHashMap<String, Object> chartMap = riskLoanCm1Mapper.getRoundData(performanceParm);			chartList.add(chartMap);
		}
		String startMonth = riskLoanCm1Mapper.getRiskMonthStartDate();
		//查询表格数据
		List<LinkedHashMap<String, Object>> tableList;
		tableList =  (List<LinkedHashMap<String, Object>>) monthAndRecordDateList.get("tableList");
		String startDate = (String) tableList.get(tableList.size()-1).get("recordDate");
		String endDate =  (String) tableList.get(0).get("recordDate");
		
		Map<String,Object> requestMap = new HashMap<String,Object>();
		String childOrgNos = getChildOrgNos(orgNo,5);
		requestMap.put("startDate", startDate);
		requestMap.put("endDate", endDate);
		requestMap.put("orgNos", childOrgNos);
		requestMap.put("dataType", "1");
		requestMap.put("type", "2");
		ArrayList<Object> businessCM1BatchList = new ArrayList<>();
		if(null != childOrgNos){
			List<LinkedHashMap<String, Object>> businessResultList = riskLoanCm1Mapper.getTableListCm1(requestMap);
			 businessCM1BatchList = getCustomList(businessResultList,"营业部");
		}
		data.put("businessCM1BatchList", businessCM1BatchList);
		
		//查询时间段
		String dateResult = getThisYearMonth(startDate,endDate);
	     data.put("thisYearMonth", dateResult);
		data.put("chartList", chartList);
		data.put("startMonth", startMonth);
		return new ReturnMsgData("0000", "调用成功", data);
	}
	

	@Override
	public ReturnMsgData riskRegionToOrgPage(PerformanceParm performanceParm) {
		Map<String, Object> data = new HashMap<String, Object>();
		List<Map<String,Object>> m1List = riskWarningLevelMapper.riskRegionToOrgPage(performanceParm);
		List<Map<String, Object>> otherCm1List = riskOverdueMapper.getOrgCM1OverdueList(performanceParm);
		data.put("cm1List", m1List);
		data.put("otherCm1List", otherCm1List);
		return new ReturnMsgData("0000", "调用成功", data);
	}
	/**
	 * 风控--贷后整体下钻接口
	 * @param performanceParm
	 * @return
	 * @author guodong
	 */
	@Override
	public ReturnMsgData riskAfterLoanAll(PerformanceParm performanceParm) {
		Map<String, Object> map = new HashMap<String, Object>();
		 map.put("recordDate", performanceParm.getDays());
		 map.put("type", 3);
		 map.put("month", performanceParm.getMonth());
		List<LinkedHashMap<String,Object>> afterLoanAllList = riskLoanCm1Mapper.selectAfterLoanAll(map);
		if(null != afterLoanAllList && afterLoanAllList.size()>0){
			LinkedHashMap<String, Object> linkedHashMap = afterLoanAllList.get(0);
			Set<String> keySet = linkedHashMap.keySet();
			LinkedHashMap<String, Object> map2 = new LinkedHashMap<String, Object>();
			for (String key : keySet) {
				map2.put(key, key);
			}
			afterLoanAllList.add(0, map2);
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<Object> resultList = getResultList(afterLoanAllList);
		resultMap.put("afterLoanAllList", resultList);
		return new ReturnMsgData("0000", "调用成功", resultMap);
	}
	/**
	 * Description: 风控贷后首页
	 * @param performanceParm
	 * @author tie
	 */
	@Override
	public ReturnMsgData riskAfterLoanFirstPage(PerformanceParm performanceParm) {
		Map<String, Object> data = new HashMap<String, Object>();
		String date = performanceParm.getDays();
		String month = DateUtil.getMonth(date);
		String[] split = month.split(",");
		performanceParm.setMonth(split[0]);
		Map<String, Object> roundMap = riskLoanCm1Mapper.getRiskAfterRoundData(performanceParm);
		if(null != roundMap){
			String acValue = (String) roundMap.get("actualValue");
			if("--".equals(acValue)){
				data.put("cm1Sum", -1);
			}else{
				data.put("cm1Sum", Double.valueOf(acValue.split("%")[0]));
			}
			String plan = (String) roundMap.get("planValue");
			if("-1".equals(plan)){
				data.put("plan", plan);
			}else{
				data.put("plan", Double.valueOf(plan.split("%")[0]));
			}
		}else{
			data.put("cm1Sum", -1);
			data.put("plan", -1);
		}
		//查询累计C-M1回款率
		LinkedHashMap<String,Object> columnName = new LinkedHashMap<String,Object>();
		List<LinkedHashMap<String, Object>> cm1List; 
		cm1List = riskLoanCm1Mapper.getAfterCm1List(performanceParm);
		for (Map<String, Object> map : cm1List) {
			Set<String> keySet = map.keySet();
			for (String key : keySet) {
				if("整体".equals(key)){
					columnName.put(key, "");
				}else{
					columnName.put(key, key);
				}
			}
		}
		cm1List.add(0, columnName);
		ArrayList<Object> firstCm1List = getResultList(cm1List);
		data.put("isShowNextMonth", 0);
		data.put("firstCm1List", firstCm1List);
		data.put("thisMonth",getMonth(split[0]));
		if(split.length>1){ //两个月的数据
			performanceParm.setMonth(split[1]);
			LinkedHashMap<String,Object> column = new LinkedHashMap<String,Object>();
		    cm1List = riskLoanCm1Mapper.getAfterCm1List(performanceParm);
			for (Map<String, Object> map : cm1List) {
				Set<String> keySet = map.keySet();
				for (String key : keySet) {
					if("整体".equals(key)){
						column.put(key, "");
					}else{
						column.put(key, key);
					}
				}
			}
			cm1List.add(0, column);
			ArrayList<Object> nextCm1List = getResultList(cm1List);
			data.put("nextCm1List", nextCm1List);
			data.put("isShowNextMonth", 1);
			data.put("nextMonth",getMonth(split[1]));
		}
		
		//贷后分割点
		Map<String, Object> separateMap = riskSeparateMapper.selectSeparate(performanceParm.getDays());
		data.put("separate", separateMap.get("separate")); //分割点
		return new ReturnMsgData("0000", "调用成功", data);
	}
	/**
	 * Description: 风控贷后首页（月）
	 * @param performanceParm
	 * @author tie
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ReturnMsgData riskMonthAfterLoanFirstPage(PerformanceParm performanceParm) {
		Map<String,Object> data = new HashMap<String,Object>();
		String month = performanceParm.getMonth();
		//查询首页折线图数据(查询实际值和目标值)
		List<Map<String, Object>> chartList = new ArrayList<Map<String, Object>>();
		List<LinkedHashMap<String, Object>> recordList;
		
		Map<String, Object> monthAndRecordDateList = getMonthAndRecordDateList(month);
		recordList =  (List<LinkedHashMap<String, Object>>) monthAndRecordDateList.get("list");
	    Collections.reverse(recordList); 
		for (LinkedHashMap<String, Object> map : recordList) {
			performanceParm.setMonth(String.valueOf(map.get("month")));
			performanceParm.setDays(String.valueOf(map.get("recordDate")));
			performanceParm.setType("3");
			performanceParm.setOrgNo("fengkongdh");
			LinkedHashMap<String, Object> chartMap = riskLoanCm1Mapper.getMonthChartListData(performanceParm);
			chartList.add(chartMap);
		}
		data.put("chartList", chartList);
		//查询起始时间
		String startMonth = riskLoanCm1Mapper.getRiskMonthStartDate();
		//查询表格数据
		List<Map<String, Object>> cm1BackRateInfoList = new ArrayList<Map<String, Object>>();
		List<LinkedHashMap<String, Object>> tableList;
		tableList =  (List<LinkedHashMap<String, Object>>) monthAndRecordDateList.get("tableList");
		String startDate = (String) tableList.get(tableList.size()-1).get("recordDate");
		String endDate =  (String) tableList.get(0).get("recordDate");
		for (LinkedHashMap<String, Object> map : tableList) {
			performanceParm.setMonth(String.valueOf(map.get("month")));
			performanceParm.setDays(String.valueOf(map.get("recordDate")));
			performanceParm.setType("3");
			performanceParm.setOrgNo("fengkongdh");
			performanceParm.setParamsCode("tableList");//标识是折线图还是表格
			LinkedHashMap<String, Object> chartMap = riskLoanCm1Mapper.getMonthChartListData(performanceParm);
			cm1BackRateInfoList.add(chartMap);
		}
		data.put("cm1BackRateInfoList", cm1BackRateInfoList);
		//查询时间段
		String dateResult = getThisYearMonth(startDate,endDate);
	    data.put("thisYearMonth", dateResult);
		data.put("startMonth", startMonth);
		return  new ReturnMsgData("0000", "调用成功", data);
	}
	/**
	 * Description: 风控分中心首页
	 * @param performanceParm
	 * @return
	 * @author guodong
	 */
	@Override
	public ReturnMsgData riskBranchCenterFirstPage(PerformanceParm performanceParm) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<Object> businessCM1RateList = new ArrayList<Object>();
		ArrayList<Object> businessCM1BatchList = new ArrayList<Object>();
		String month = DateUtil.getMonth(performanceParm.getDays());
		String[] split = month.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgNo", performanceParm.getOrgNo());
		map.put("recordDate", performanceParm.getDays());
		map.put("month", split[0]);
		//分中心首页累计C-M1回款率
		List<Map<String, Object>> totalRate = 
				riskLoanCm1Mapper.selectbranchCenterTotalRate(map);
		BigDecimal actualVal = new BigDecimal(-1.00);
		BigDecimal planVal = new BigDecimal(-1);
		if(null != totalRate && totalRate.size()>0){
			Map<String, Object> totalAndPlanRateMap = totalRate.get(0);
			actualVal = (BigDecimal) totalAndPlanRateMap.get("cm1Val");
		}
		//分中心首页累计C-M1回款率
		List<Map<String, Object>> planRate = 
				riskLoanCm1Mapper.selectbranchCenterPlanRate(map);
		if(null != planRate && planRate.size()>0){
			Map<String, Object> totalAndPlanRateMap = planRate.get(0);
			planVal = (BigDecimal) totalAndPlanRateMap.get("planVal");
		}
		//分中心各产品累计C-M1回款率 
		List<LinkedHashMap<String, Object>> branchCenterCM1ByProduct = 
				riskLoanCm1Mapper.selectbranchCenterCM1ByProductName(map);
		String childOrgNos = getBranchCenterChildOrgNos(performanceParm.getOrgNo(),performanceParm.getDays());
		if(StringUtil.isNotBlank(childOrgNos)){	//下级不为空
			businessCM1RateList = getChildData(performanceParm.getDays(), 
					split[0], childOrgNos,2+"","营业部");
			businessCM1BatchList = getChildBatchData(performanceParm.getDays(),
					split[0],childOrgNos, 2+"", "营业部");
			resultMap.put("isShowNextMonth", 0); //是否展示下月 0： 不展示  1：展示
			if(split.length>1){ //两个月的数据
				resultMap.put("isShowNextMonth", 1);
				resultMap.put("nextMonth", getMonth(split[1]));
				resultMap.put("businessNextMonthCM1RateList",getChildData(performanceParm.getDays(), 
						split[1], childOrgNos,2+"","营业部"));
				resultMap.put("businessNextMonthCM1BatchList",getChildBatchData(performanceParm.getDays(),
						split[1],childOrgNos, 2+"", "营业部"));
			}
		}
		resultMap.put("thisMonth", getMonth(split[0]));	//月份
		resultMap.put("actualVal", actualVal);	
		resultMap.put("planVal", planVal);	
		resultMap.put("branchCenterCM1ByProduct", branchCenterCM1ByProduct);	
		resultMap.put("businessCM1RateList", businessCM1RateList);	
		resultMap.put("businessCM1BatchList", businessCM1BatchList);	
		return  new ReturnMsgData("0000", "调用成功", resultMap);
	}
	/**
	 * Description: 风控分中心（月）首页
	 * @param performanceParm
	 * @return
	 * @author guodong
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ReturnMsgData riskMonthBranchCenterFirstPage(PerformanceParm performanceParm) {
		Map<String,Object> data = new HashMap<String,Object>();
		String month = performanceParm.getDays();
		String orgNo = performanceParm.getOrgNo();
		//查询首页折线图数据(查询实际值和目标值)
		Map<String, Object> monthAndRecordDateList = getSubcenterChartList(performanceParm, data, month, orgNo);
		String startMonth = riskLoanCm1Mapper.getRiskMonthStartDate();
		//查询表格数据
		List<LinkedHashMap<String, Object>> tableList;
		tableList =  (List<LinkedHashMap<String, Object>>) monthAndRecordDateList.get("tableList");
		String startDate = (String) tableList.get(tableList.size()-1).get("recordDate");
		String endDate =  (String) tableList.get(0).get("recordDate");
		getSubcenterTableList(data, month, orgNo, startDate, endDate);
		//查询时间段
		String dateResult = getThisYearMonth(startDate,endDate);
	     data.put("thisYearMonth", dateResult);
		data.put("startMonth", startMonth);
		return new ReturnMsgData("0000", "调用成功", data);
	}
	
	private void getSubcenterTableList(Map<String, Object> data, String month, String orgNo, String startDate,
			String endDate) {
		//判断入参是否是本年本月
		String recordDate = getSubcenterRecordDate(month);
		Map<String,Object> requestMap = new HashMap<String,Object>();
		String childOrgNos = getBranchCenterChildOrgNos(orgNo,recordDate);
		requestMap.put("startDate", startDate);
		requestMap.put("endDate", endDate);
		requestMap.put("orgNos", childOrgNos);
		requestMap.put("dataType", "1");
		requestMap.put("type", "2");
		ArrayList<Object> businessCM1BatchList = new ArrayList<>();
		if(null != childOrgNos){
			List<LinkedHashMap<String, Object>> businessResultList = riskLoanCm1Mapper.getTableListCm1(requestMap);
			 businessCM1BatchList = getCustomList(businessResultList,"营业部");
		}
		data.put("businessCM1BatchList", businessCM1BatchList);
	}
	private String getSubcenterRecordDate(String month) {
		Calendar cal = Calendar.getInstance();
		int y = Integer.valueOf(month.split("-")[0]);
		int m = Integer.valueOf(month.split("-")[1]);
		int nowMonth = cal.get(Calendar.MONTH )+1;
		int nowYearyear = cal.get(Calendar.YEAR);
		String recordDate;
		if(nowMonth ==m && nowYearyear==y ){
			recordDate = DateTimeUtil.getYesterdayDateString();
		}else{
			recordDate = month+"-26";
		}
		return recordDate;
	}
	private Map<String, Object> getSubcenterChartList(PerformanceParm performanceParm, Map<String, Object> data,
			String month, String orgNo) {
		List<Map<String, Object>> chartList = new ArrayList<Map<String, Object>>();
		List<LinkedHashMap<String, Object>> recordList;
		
		Map<String, Object> monthAndRecordDateList = getMonthAndRecordDateList(month);
		recordList =  (List<LinkedHashMap<String, Object>>) monthAndRecordDateList.get("list");
	    Collections.reverse(recordList); 
		for (LinkedHashMap<String, Object> map : recordList) {
			LinkedHashMap<String, Object> chartMap = riskLoanCm1Mapper.getMonthChartListData(performanceParm);
			//分中心实际值
			map.put("orgNo", orgNo);
			List<Map<String, Object>> totalRate = 
					riskLoanCm1Mapper.selectbranchCenterTotalRate(map);
			BigDecimal actualVal = new BigDecimal(-1.00);
			BigDecimal planVal = new BigDecimal(-1);
			String m = null;
			if(null != totalRate && totalRate.size()>0){
				Map<String, Object> totalAndPlanRateMap = totalRate.get(0);
				actualVal = (BigDecimal) totalAndPlanRateMap.get("cm1Val");
			    m = (String) totalAndPlanRateMap.get("month");
			}
			//分中心目标值
			List<Map<String, Object>> planRate = 
					riskLoanCm1Mapper.selectbranchCenterPlanRate(map);
			if(null != planRate && planRate.size()>0){
				Map<String, Object> totalAndPlanRateMap = planRate.get(0);
				planVal = (BigDecimal) totalAndPlanRateMap.get("planVal");
			}
			chartMap.put("month", m);
			chartMap.put("realValue", actualVal);
			chartMap.put("planValue", planVal);
			chartList.add(chartMap);
		}
		data.put("chartList", chartList);
		return monthAndRecordDateList;
	}
	

	/**
	 * Description: 获取分中心所有的营业部
	 * @param orgNo
	 * @return
	 * @author guodong
	 */
	private String getBranchCenterChildOrgNos(String orgNo, String recordDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgNo", orgNo);
		map.put("recordDate", recordDate);
		return riskOrgDataMapper.getBranchCenterChildOrgNos(map);
	}
	
	/**
	 * 风控分中心 市场风险管理部 组装数据
	* @Description: 
	* @param @param cm1Sum
	* @param @param plan
	* @param @param subcenterCm1List
	* @param @return    
	* @return List<Map<String,Object>>
	* @throws
	 */
	public List<Map<String,Object>> packedData(Map cm1Sum,Map plan,List<Map<String,Object>> subcenterCm1List ){
		 if(null != subcenterCm1List && subcenterCm1List.size()>0){
			 Map<String,Object> sumMap = new HashMap<String,Object>();
				BigDecimal planV;
				double actualValue = 0;
				double planValue = 0 ;
				double deviationRate = 0;
				double noValue = -1;
				if(null != cm1Sum && null != plan){
					BigDecimal	cm1sum = (BigDecimal) cm1Sum.get("actualValueSum");
				    planV  = (BigDecimal) plan.get("plan");
					actualValue =  cm1sum.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					planValue =  planV.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					//偏差率 = (目标-实际)/（1-目标）
					//如果目标值 为1,捕获异常，返回偏差率为0
					try {
						deviationRate = ((planV.subtract(cm1sum)).divide((new BigDecimal(1).subtract(planV)),6,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					} catch (ArithmeticException e) {
						deviationRate=0;
					}
					sumMap.put("actualVal", actualValue);
					sumMap.put("planVal", planValue);
					sumMap.put("deviationRate", deviationRate);
				}else{
					if(null != cm1Sum){
						BigDecimal	cm1sum = (BigDecimal) cm1Sum.get("actualValueSum");
						actualValue =  cm1sum.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						sumMap.put("actualVal", actualValue);
					}else{
						sumMap.put("actualVal", noValue);
						sumMap.put("deviationRate", noValue);
					}
					
					if(null !=plan){
						sumMap.put("planVal", planValue);
					}else{
						sumMap.put("planVal", noValue);
						sumMap.put("deviationRate", noValue);
					}
				}
				sumMap.put("name", "总计");
				sumMap.put("subcenter_no", "");
				subcenterCm1List.add(sumMap);
				return subcenterCm1List; 
		 }
		 return subcenterCm1List;
	  }

	@Override
	public ReturnMsgData orgOverdueDetail(PerformanceParm param) {
		HashMap<String, Object> map = new HashMap<>();
		/*map.put("date", day);
		map.put("orgNo", orgNo);
		map.put("productNo", productNo);*/
		map.put("date", param.getDays());
		map.put("orgNo", param.getOrgNo());
		map.put("productNo", param.getProductNo());
		List<Map<String, Object>> orgOverdueDetail = riskOverdueMapper.findOrgOverdueDetail(map);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("contractList", orgOverdueDetail);
//		resultMap.put("data", value)
		return  new ReturnMsgData("0000", "调用成功", resultMap);
	}

	/**
	 * 获取预事业部下警营业部明细
	 * @param days
	 * @param orgNo
	 * @param productNo
	 * @param warningLevel
	 * @return
	 */
	@Override
//	public ReturnMsgData warningOrgs(String days, String orgNo, String productNo, String warningLevel) {
	public ReturnMsgData warningOrgs(PerformanceParm param){
		HashMap<String, Object> params = new HashMap<>();
		/*params.put("date", days);
		params.put("orgNo", orgNo);
		params.put("productNo", productNo);
		params.put("warningLevel", warningLevel);*/
		params.put("date", param.getDays());
		params.put("orgNo", param.getOrgNo());
		params.put("productNo", param.getProductNo());
		params.put("warningLevel", param.getWarningLevel());
		List<Map<String, Object>> warningOrgsDetail = riskWarningLevelMapper.getWarningOrgsDetail(params);
		
		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("warningOrgList", warningOrgsDetail);
		return  new ReturnMsgData("0000", "调用成功", resultMap);
	}

	@Override
	public FileMapping findInfoAndAddressByCode(String code) {
		FileMapping fileMapping =  fileMappingMapper.findInfoAndAddressByCode(code);
		return fileMapping;
	}

	@Override
	public ReturnMsgData orgCM1OverdueDetail(PerformanceParm performanceParm) {
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> data = new HashMap<String,Object>();
		ArrayList<Object> contractList = new ArrayList<>(); 
		map.put("record_date", performanceParm.getDays());
		map.put("org_no", performanceParm.getOrgNo());
		map.put("productNo", performanceParm.getProductNo());

		List <LinkedHashMap<String, Object>> resultList  = riskOverdueMapper.orgCM1OverdueDetail(map);
		if(null != resultList && resultList.size()>0){
			 LinkedHashMap<String, Object> linkedHashMap = resultList.get(0);
			Set<String> keySet = linkedHashMap.keySet();
			LinkedHashMap<String, Object> keyMap = new LinkedHashMap<String, Object>();
			for (String key : keySet) {
				keyMap.put(key, key);
			}
			resultList.add(0, keyMap);
		}
		contractList = getResultList(resultList);
		data.put("contractList", contractList);
		return new ReturnMsgData("0000", "调用成功", data);
	}
	
	/**
	 * 
	* @Description: 
	* @param @param "2017-05"
	* @param @return  dateResult:2017年1-5月   
	* list:[{recordDate=2017-05-26, month=201705}, {recordDate=2017-04-26, month=201704}, {recordDate=2017-03-26, month=201703}, {recordDate=2017-02-26, month=201702}, {recordDate=2017-01-26, month=201701}, {recordDate=2016-12-26, month=201612}]
	* @return Map<String,Object>
	* @throws
	 */
	public   Map<String, Object> getMonthAndRecordDateList(String date){
		try {
			
			Map<String,Object> map = new HashMap<String,Object>();
			List<LinkedHashMap<String,Object>> list = new ArrayList<LinkedHashMap<String,Object>>();
			List<LinkedHashMap<String,Object>> tableList = new ArrayList<LinkedHashMap<String,Object>>();

			DateFormat df = new SimpleDateFormat("yyyy-MM");
			DateFormat dfs = new SimpleDateFormat("yyyyMM");
			 
			Calendar cal = Calendar.getInstance();
			int y = Integer.valueOf(date.split("-")[0]);
			int m = Integer.valueOf(date.split("-")[1]);
			int month = cal.get(Calendar.MONTH )+1;
			int year = cal.get(Calendar.YEAR);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			//传入日期是本年本月
			if(year == y && month == m){
				if(day > 26){
					//获取t月近半年
					for (int i = 0; i <6; i++) {
						 LinkedHashMap<String,Object> chartMap = new LinkedHashMap<String,Object>();
						 String recordDate = df.format(cal.getTime())+"-26";
						 chartMap.put("recordDate", recordDate);
						 chartMap.put("month", dfs.format( cal.getTime()));
						 cal.add(Calendar.MONTH,-1);
						 list.add(chartMap);	
					}
					//获取t所在年 t月 到1月
					Calendar tableCal = Calendar.getInstance();
					int tableMonth = tableCal.get(Calendar.MONTH )+1;
					for (int i = 0; i < tableMonth; i++) {
						 LinkedHashMap<String,Object> tableMap = new LinkedHashMap<String,Object>();
						 String recordDate = df.format(tableCal.getTime())+"-26";
						 tableMap.put("recordDate", recordDate);
						 tableMap.put("month", dfs.format( tableCal.getTime()));
						 tableCal.add(Calendar.MONTH,-1);
						 tableList.add(tableMap);	
					}
					map.put("list", list);
					map.put("tableList", tableList);
				}else{
					//获取（t-1）月近半年
					Calendar lastCal = Calendar.getInstance();
					for (int i = 0; i <6; i++) {
						 LinkedHashMap<String,Object> chartMap = new LinkedHashMap<String,Object>();
						 lastCal.add(Calendar.MONTH,-1);
						 String recordDate = df.format(lastCal.getTime())+"-26";
						 chartMap.put("recordDate", recordDate);
						 chartMap.put("month", dfs.format(lastCal.getTime()));
						 list.add(chartMap);	
					}
					
					//获取（t-1）月到1月
					Calendar lastTableCal = Calendar.getInstance();
					lastTableCal.add(Calendar.MONTH,-1);
					int lastTableMonth = lastTableCal.get(Calendar.MONTH )+1;
					for (int i = 0; i < lastTableMonth; i++) {
						 LinkedHashMap<String,Object> tableMap = new LinkedHashMap<String,Object>();
						 String recordDate = df.format(lastTableCal.getTime())+"-26";
						 tableMap.put("recordDate", recordDate);
						 tableMap.put("month", dfs.format( lastTableCal.getTime()));
						 lastTableCal.add(Calendar.MONTH,-1);
						 tableList.add(tableMap);
					}
					map.put("list", list);
					map.put("tableList", tableList);
				}
				//传入日期非本月
			}else{
				Calendar now = Calendar.getInstance();
				Date parse = df.parse(date);
		        now.setTime(parse);
				//获取t月近半年
				for (int i = 0; i < 6; i++) {
					 LinkedHashMap<String,Object> chartMap = new LinkedHashMap<String,Object>();
					 String recordDate = df.format(now.getTime())+"-26";
					 chartMap.put("recordDate", recordDate);
					 chartMap.put("month", dfs.format( now.getTime()));
					 now.add(Calendar.MONTH,-1);
					 list.add(chartMap);	
				}
				
				
				//获取t所在年 t月 到1月
				Calendar now2 = Calendar.getInstance();
				Date parse2 = df.parse(date);
		        now2.setTime(parse2);
				int tableMonth2 = now2.get(Calendar.MONTH )+1;
				for (int i = 0; i < tableMonth2; i++) {
					 LinkedHashMap<String,Object> tableMap = new LinkedHashMap<String,Object>();
					 String recordDate = df.format(now2.getTime())+"-26";
					 tableMap.put("recordDate", recordDate);
					 tableMap.put("month", dfs.format(now2.getTime()));
					 now2.add(Calendar.MONTH,-1);
					 tableList.add(tableMap);	
				}
				map.put("list", list);
				map.put("tableList", tableList);
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static List<LinkedHashMap<String,Object>> getRecordList(String date){
		try {
			List<LinkedHashMap<String,Object>> list = new ArrayList<LinkedHashMap<String,Object>>();
			 DateFormat df = new SimpleDateFormat("yyyy-MM");
			 DateFormat dfs = new SimpleDateFormat("yyyyMM");
			 Date d1 = df.parse(date); 
			 int day = Integer.valueOf(date.split("-")[2]);
			 Calendar  g = Calendar.getInstance();
			 g.setTime(d1);
			 String[] split = date.split("-");
			if(day>26){
				 LinkedHashMap<String,Object> thisMonth = new LinkedHashMap<String,Object>();
				 thisMonth.put("recordDate",split[0]+"-"+split[1]+"-26");
				 thisMonth.put("month", dfs.format(d1));
				 LinkedHashMap<String,Object> nextMonth = new LinkedHashMap<String,Object>();
				 nextMonth.put("recordDate", date);
				 Date d2 = df.parse(date); 
				 Calendar  g2= Calendar.getInstance();
				 g2.setTime(d2);
				 g2.add(Calendar.MONTH,1);
				 nextMonth.put("month", dfs.format( g2.getTime()));
				 list.add(nextMonth);
				 list.add(thisMonth);
				 for (int i = 0; i <4; i++) {
					 LinkedHashMap<String,Object> map = new LinkedHashMap<String,Object>();
					 g.add(Calendar.MONTH,-1);
					 String recordDate = df.format(g.getTime())+"-26";
					 map.put("recordDate", recordDate);
					 map.put("month", dfs.format( g.getTime()));
					 list.add(map);
				}
			}else{
				LinkedHashMap<String,Object> thisMonth = new LinkedHashMap<String,Object>();
				 thisMonth.put("recordDate",date);
				 thisMonth.put("month", dfs.format(d1));
				 list.add(thisMonth);
				 for (int i = 1; i <6; i++) {
					 LinkedHashMap<String,Object> map = new LinkedHashMap<String,Object>();
					 g.add(Calendar.MONTH,-1);
					 String recordDate = df.format(g.getTime())+"-26";
					 map.put("recordDate", recordDate);
					 map.put("month", dfs.format( g.getTime()));
					 list.add(map);	
				}
				}
				return list;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}

	/**
	 * 根据不同参数和权限返回不同的C-M1日列表数据
	 * @param permission
	 * @return
	 */
	@Override
	public PageModel cm1DailyList(Map<String, Object> params) {
		Map<String, Object> mapperParam = new HashMap<>();
		String permissinNo = (String)params.get("permissinNo");
		Integer permissinRank = (Integer)params.get("permissinRank");
		String recordDate = (String)params.get("recordDate");
		String rankStr = (String)params.get("rank");
		Integer rank ;
		String type = (String)params.get("type");
		String orgName = (String)params.get("orgName");
		int pageNo = 1;
		int pageSize = 10;
		String month = null;
		PageModel pageModel = new PageModel();
		
		if (params.get("pageNo")!=null) {
			pageNo = Integer.parseInt((String)params.get("pageNo"));
		}
		
		if (params.get("pageSize")!=null) {
			pageSize = Integer.parseInt((String)params.get("pageSize"));
		}
		
		List<String> orgList = new ArrayList<>();//查询的机构列表
		
//		if ("fengkongzw".equals(permissinNo)) {
		//确定查询的组织级别
		if (rankStr == null || "".equals(rankStr)) {
			if (permissinRank == 3) {//如果是事业部权限,则查询的默认级别为大区
				rank = 4;
			}else if (permissinRank ==4) {//如果是大区权限或者是分中心权限,则查询的默认级别为营业部
				rank = 5;
			}else {
				rank = 3;
			}
		}else {
			rank=Integer.parseInt(rankStr);
		}
		
		//确定日期
		if (recordDate==null || "".equals(recordDate)) {
			recordDate = DateTimeUtil.getYesterdayDateString();
		}
		
		//获得所有的机构列表
		if(orgName==null || "".equals(orgName)){
			if (permissinRank ==6) {//如果是分中心权限,则机构为该分中心下所有的营业部
				orgList = riskOrgDataMapper.getOrgListBySubcenter(permissinNo);
			}else {
				Map<String, Object> map1 = new HashMap<>();
				map1.put("orgNo", permissinNo);
				map1.put("rank", rank);
				orgList = riskOrgStructMapper.selectNewOrgNos(map1);
			}
		}else {
			//如果前端传入了orgName,查询对应的组织机构编号,
			HashMap<String, Object> params2 = new HashMap<>();
			params2.put("orgName", orgName);
			params2.put("recordDate", recordDate);
			String orgNo = riskOrgStructMapper.getOrgNoByName(params2);
			//判断是否有查询该机构的权限
			boolean permitGetOrgData = isPermitGetOrgData(orgNo, permissinRank, permissinNo);
			if (!permitGetOrgData) {
				pageModel.setList(null);
				pageModel.setTotalRecords(0L);
				return pageModel;
			}
			orgList.add(orgNo);
		}
		
		//确定统计周期
		if (type == null || "".equals(type)) {
			if ("fengkongzw".equals(permissinNo)) {//如果是执委权限则默认为整体
				type = "1";
			}else if ("fengkongdh".equals(permissinNo)) {//如果是贷后权限则默认为6期后
				type = "3";
			}else {//其他情况下默认为6期前
				type = "2";
			}
		}
//		}
		
		
		//确定月份(当月)
		month = DateTimeUtil.getMonthString(DateTimeUtil.convertAsDateString(recordDate));
		mapperParam.put("recordDate", recordDate);
		mapperParam.put("orgs", orgList);
		mapperParam.put("type", type);
		mapperParam.put("month", month);
		mapperParam.put("startIndex", (pageNo-1)*pageSize);
		mapperParam.put("pageSize", pageSize);
		List<LinkedHashMap<String, Object>> cm1DailyList = riskLoanCm1Mapper.getCm1DailyList(mapperParam);
		
		pageModel.setList(cm1DailyList);
		pageModel.setTotalRecords(riskLoanCm1Mapper.getCm1Count(mapperParam));
//		pageModel.set
		return pageModel;
	}

	@Override
	public List<Map<String, Object>> getMonthList() {
		List<Map<String, Object>> monthList = riskLoanCm1Mapper.getMonthList();
		return monthList;
	}
	
	/**
	 * 根据不同参数和权限返回不同的C-M1月列表数据
	 * @param permission
	 * @return
	 */
	@Override
	public PageModel cm1MonthList(Map<String, Object> params) {
		Map<String, Object> mapperParam = new HashMap<>();
		String permissinNo = (String)params.get("permissinNo");
		Integer permissinRank = (Integer)params.get("permissinRank");
		String recordDate = (String)params.get("recordDate");
		String rankStr = (String)params.get("rank");
		Integer rank ;
		String type = (String)params.get("type");
		String orgName = (String)params.get("orgName");
		int pageNo = 1;
		int pageSize = 10;
		String month = null;
		PageModel pageModel = new PageModel();
		
		if (params.get("pageNo")!=null) {
			pageNo = Integer.parseInt((String)params.get("pageNo"));
		}
		
		if (params.get("pageSize")!=null) {
			pageSize = Integer.parseInt((String)params.get("pageSize"));
		}
		
		List<String> orgList = new ArrayList<>();//查询的机构列表
		
//		if ("fengkongzw".equals(permissinNo)) {
		//确定查询的组织级别
		if (rankStr == null || "".equals(rankStr)) {
			if (permissinRank == 3) {//如果是事业部权限,则查询的默认级别为大区
				rank = 4;
			}else if (permissinRank ==4) {//如果是大区权限或者是分中心权限,则查询的默认级别为营业部
				rank = 5;
			}else {
				rank = 3;
			}
		}else {
			rank=Integer.parseInt(rankStr);
		}
		
		//确定日期
		if (recordDate==null || "".equals(recordDate)) {
//					recordDate = DateTimeUtil.getYesterdayDateString()
			List<Map<String, Object>> monthList = getMonthList();
			if (monthList!=null && monthList.size()>0) {
				recordDate = (String)monthList.get(monthList.size()-1).get("recordDate") ;
			}
		}
		//获得所有的机构列表
		if(orgName==null || "".equals(orgName)){
			if (permissinRank ==6) {//如果是分中心权限,则机构为该分中心下所有的营业部
				orgList = riskOrgDataMapper.getOrgListBySubcenter(permissinNo);
			}else {
				Map<String, Object> map1 = new HashMap<>();
				map1.put("orgNo", permissinNo);
				map1.put("rank", rank);
				orgList = riskOrgStructMapper.selectNewOrgNos(map1);
			}
		}else {
			//如果前端传入了orgName,查询对应的组织机构编号,
			HashMap<String, Object> params2 = new HashMap<>();
			params2.put("orgName", orgName);
			params2.put("recordDate", recordDate);
			String orgNo = riskOrgStructMapper.getOrgNoByName(params2);
			//判断是否有查询该机构的权限
			boolean permitGetOrgData = isPermitGetOrgData(orgNo, permissinRank, permissinNo);
			if (!permitGetOrgData) {
				pageModel.setList(null);
				pageModel.setTotalRecords(0L);
				return pageModel;
			}
			orgList.add(orgNo);
		}
		
		//确定统计周期
		if (type == null || "".equals(type)) {
			if ("fengkongzw".equals(permissinNo)) {//如果是执委权限则默认为整体
				type = "1";
			}else if ("fengkongdh".equals(permissinNo)) {//如果是贷后权限则默认为6期后
				type = "3";
			}else {//其他情况下默认为6期前
				type = "2";
			}
		}
//		}
		
		//确定月份(当月)
		month = DateTimeUtil.getMonthString(DateTimeUtil.convertAsDateString(recordDate));
		mapperParam.put("recordDate", recordDate);
		mapperParam.put("orgs", orgList);
		mapperParam.put("type", type);
		mapperParam.put("month", month);
		mapperParam.put("startIndex", (pageNo-1)*pageSize);
		mapperParam.put("pageSize", pageSize);
		List<LinkedHashMap<String, Object>> cm1DailyList = riskLoanCm1Mapper.getCm1MonthList(mapperParam);
		
		pageModel.setList(cm1DailyList);
		pageModel.setTotalRecords(riskLoanCm1Mapper.getCm1Count(mapperParam));
//		pageModel.set
		return pageModel;
	}
	
	private boolean isPermitGetOrgData(String orgNo,int permissinRank,String permissinNo){
		//如果是执委/风控管理部/贷后管理部则可以查询所有机构
		if (permissinRank == 2) {
			return true;
		}
		//如果是分中心权限,则查询该机构是否是该分中心下的营业部
		if (permissinRank == 6) {
			String subcenter = riskOrgDataMapper.getSubcenterByOrg(orgNo);
			if (subcenter!=null && subcenter.equals(permissinNo)) {
				return true;
			}
			return false;
		}
		//如果是事业部权限,则查询该机构是否是该事业部下的大区或营业部
		if (permissinRank == 3) {
			Map<String, Object> org = riskOrgStructMapper.getOrgDataByOrgNo(orgNo);
			if (org!=null) {
				if (permissinNo.equals((String)org.get("parent_no"))) {
					return true;
				}
				Map<String, Object> org2 = riskOrgStructMapper.getOrgDataByOrgNo((String)org.get("parent_no"));
				if (org2!=null && permissinNo.equals((String)org2.get("parent_no"))) {
					return true;
				}
				return false;
			}
			
		}
		
		//如果是大区权限,则查询该机构是否是该大区下的营业部
		if (permissinRank == 4) {
			Map<String, Object> org = riskOrgStructMapper.getOrgDataByOrgNo(orgNo);
			if (org!=null && permissinNo.equals((String)org.get("parent_no"))) {
				return true;
			}
			return false;
		}
		return false;
	}

}
