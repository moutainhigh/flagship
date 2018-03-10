package com.hzcf.flagship.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hzcf.flagship.dao.FinanceDailyPageMapper;
import com.hzcf.flagship.dao.IndexMapper;
import com.hzcf.flagship.dao.MoneymgrAccumuPerformanceMapper;
import com.hzcf.flagship.dao.MoneymgrDistrictAccumuPerformanceMapper;
import com.hzcf.flagship.dao.MoneymgrDistrictAccumuPerformancePageMapper;
import com.hzcf.flagship.dao.MoneymgrEfficiencyPageMapper;
import com.hzcf.flagship.dao.MoneymgrOrgDataMapper;
import com.hzcf.flagship.dao.MoneymgrRosterMapper;
import com.hzcf.flagship.model.FinanceDailyPage;
import com.hzcf.flagship.model.Index;
import com.hzcf.flagship.model.IndexExample;
import com.hzcf.flagship.model.MoneymgrAccumuPerformance;
import com.hzcf.flagship.model.MoneymgrAccumuPerformanceExample;
import com.hzcf.flagship.model.MoneymgrDistrictAccumuPerformancePage;
import com.hzcf.flagship.model.MoneymgrDistrictAccumuPerformancePageExample;
import com.hzcf.flagship.model.MoneymgrEfficiencyPage;
import com.hzcf.flagship.model.MoneymgrEfficiencyPageExample;
import com.hzcf.flagship.model.MoneymgrRosterExample;
import com.hzcf.flagship.model.MoneymgrRosterExample.Criteria;
import com.hzcf.flagship.util.DateTimeUtil;

/**
 *<dl>
 *<dt>类名：SecondMoneymgrPerformanceJobServiceImpl.java</dt>
 *<dd>描述: 二期理财人员人效和区域结果计算</dd> 
 *<dd>创建时间： 2017年5月31日 下午3:02:23</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月31日 下午3:02:23    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public class SecondMoneymgrPerformanceJobServiceImpl {
	private final static Logger logger = Logger.getLogger(SecondMoneymgrPerformanceJobServiceImpl.class);
	@Autowired
	private MoneymgrOrgDataMapper moneymgrOrgDataMapper;
	@Autowired
	private MoneymgrRosterMapper moneymgrRosterMapper;
	@Autowired
	private MoneymgrAccumuPerformanceMapper moneymgrAccumuPerformanceMapper;
	@Autowired
	private MoneymgrEfficiencyPageMapper moneymgrEfficiencyPageMapper;
	@Autowired
	private MoneymgrDistrictAccumuPerformanceMapper moneymgrDistrictAccumuPerformanceMapper;
	@Autowired
	private MoneymgrDistrictAccumuPerformancePageMapper moneymgrDistrictAccumuPerformancePageMapper;
	@Autowired
	private IndexMapper indexMapper;
	@Autowired
	private FinanceDailyPageMapper financeDailyPageMapper;
	
	/**
	 * 更新人员人效结果数据
	 * @throws ParseException
	 */
	public void updateMoneymgrEfficiencyPage() throws ParseException{
		//1.删除当前时间前一天所在月的所有结果数据
		MoneymgrEfficiencyPageExample moneymgrEfficiencyPageExample = new MoneymgrEfficiencyPageExample();
		com.hzcf.flagship.model.MoneymgrEfficiencyPageExample.Criteria moneymgrEfficiencyPageCriteria = moneymgrEfficiencyPageExample.createCriteria();
		moneymgrEfficiencyPageCriteria.andRecordDateBetween(DateTimeUtil.getfistDayOfMonthOnYesterday(), DateTimeUtil.getlastDayOfMonthOnYesterday());
		moneymgrEfficiencyPageMapper.deleteByExample(moneymgrEfficiencyPageExample);
		//封装月数据list(日期从昨天所在月第一天--昨天)
		List<MoneymgrEfficiencyPage> pages = new ArrayList<MoneymgrEfficiencyPage>();
		Calendar yesterdayCalendar = Calendar.getInstance();
		yesterdayCalendar.setTime(new Date());
		yesterdayCalendar.add(Calendar.DAY_OF_MONTH, -1);//昨天
		int yesterday = yesterdayCalendar.get(Calendar.DAY_OF_MONTH);
		Calendar firstDayOfMonthInYesterday = Calendar.getInstance();
		firstDayOfMonthInYesterday.setTime(new Date());
		firstDayOfMonthInYesterday.add(Calendar.DAY_OF_MONTH, -1);
		firstDayOfMonthInYesterday.set(Calendar.DAY_OF_MONTH, 1);//昨天所在月的第一天
		for (int i = 0; i < yesterday; i++) {
			String dateString = DateTimeUtil.getTimeNormalString(firstDayOfMonthInYesterday.getTime());
			pages.add(getMoneymgrEfficiencyPage(dateString));
			logger.info("已封装业绩人效页面"+DateTimeUtil.formatTimeStringToDateString(dateString)+"的数据");
			firstDayOfMonthInYesterday.add(Calendar.DAY_OF_MONTH, 1);
		}
		moneymgrEfficiencyPageMapper.insertEfficiencyForMonth(pages);
		logger.info("本月所有业绩人效页面数据更新完毕");
	}
	
	
	
	/**
	 * 更新当月区域结果数据
	 */
	public void currentMonthInsertMoneymgrDistrictPage(){
		Calendar yesterdayCalendar = Calendar.getInstance();
		yesterdayCalendar.setTime(new Date());
		yesterdayCalendar.add(Calendar.DAY_OF_MONTH, -1);//昨天
		int yesterday = yesterdayCalendar.get(Calendar.DAY_OF_MONTH);
		Calendar firstDayOfMonthInYesterday = Calendar.getInstance();
		firstDayOfMonthInYesterday.setTime(new Date());
		firstDayOfMonthInYesterday.add(Calendar.DAY_OF_MONTH, -1);
		firstDayOfMonthInYesterday.set(Calendar.DAY_OF_MONTH, 1);//昨天所在月的第一天
		//查询到所有的区域(来源:对比表)
		List<Map<String, Object>> districtDatas = moneymgrOrgDataMapper.getAllDistrictData(DateTimeUtil.getFirstDayOfMonth(DateTimeUtil.getDateNormalString(firstDayOfMonthInYesterday.getTime())));
		for (int i = 0; i < yesterday; i++) {
			String dateString = DateTimeUtil.getTimeNormalString(firstDayOfMonthInYesterday.getTime());
			insertMoneymgrDistrictPageByDaily(dateString,districtDatas);
			firstDayOfMonthInYesterday.add(Calendar.DAY_OF_MONTH, 1);
		}
		logger.info("本月所有区域数据更新完毕");
		
	}
	
	/**
	 * 插入当日融资日基本数据(历史数据)
	 */
	public void insertFinanceDailyPage(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);//昨天
		FinanceDailyPage financeDailyPage = new FinanceDailyPage();
		
		financeDailyPage.setRecordDate(calendar.getTime());
		//financeDailyPage.setFinancemgrDailyPlanyieldrateWarning(new BigDecimal(getIndexValue("financeMgr_daily_planYieldRate_warning")));
		//financeDailyPage.setFinancemgrDailyPlanyieldrateWell(new BigDecimal(getIndexValue("financeMgr_daily_planYieldRate_well")));
		financeDailyPageMapper.insert(financeDailyPage);
	}
	
	
	/**
	 * 更新具体日期的区域结果数据
	 * @param dateString 格式:yyyy-MM-dd
	 */
	private void insertMoneymgrDistrictPageByDaily(String dateString,List<Map<String,Object>> districtDatas){
		//删除当日区域业绩数据
		MoneymgrDistrictAccumuPerformancePageExample moneymgrDistrictAccumuPerformancePageExample = new MoneymgrDistrictAccumuPerformancePageExample();
		com.hzcf.flagship.model.MoneymgrDistrictAccumuPerformancePageExample.Criteria moneymgrDistrictAccumuPerformancePageCriteria = moneymgrDistrictAccumuPerformancePageExample.createCriteria();
		moneymgrDistrictAccumuPerformancePageCriteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dateString));
		moneymgrDistrictAccumuPerformancePageMapper.deleteByExample(moneymgrDistrictAccumuPerformancePageExample);
		//封装数据
		List<MoneymgrDistrictAccumuPerformancePage> pages = new ArrayList<>();
		
		//查询具体日期所有区域抽取数据
		List<Map<String, Object>> list = moneymgrDistrictAccumuPerformanceMapper.getDistrictData(dateString);
		if (list==null || list.size()==0) {
			logger.error("BI抽取"+dateString+"区域数据结果为空");
			//从disttrictDatas中获得数据插入(无BI抽取结果)
			for (Map<String, Object> map : districtDatas) {
				MoneymgrDistrictAccumuPerformancePage moneymgrDistrictAccumuPerformancePage = new MoneymgrDistrictAccumuPerformancePage();
				moneymgrDistrictAccumuPerformancePage.setRecordDate(DateTimeUtil.convertAsDateString(dateString));
				moneymgrDistrictAccumuPerformancePage.setDistrictName((String)map.get("district_name"));//区域名称
				moneymgrDistrictAccumuPerformancePage.setPrincipal((String)map.get("district_principal"));//区域负责人
				moneymgrDistrictAccumuPerformancePage.setOrgNum((int)moneymgrOrgDataMapper.getOrgNum(DateTimeUtil.getFirstDayOfMonth(dateString)));//机构数目
				moneymgrDistrictAccumuPerformancePage.setAccumuPerformance(null);//累计业绩
				moneymgrDistrictAccumuPerformancePage.setCompleteRate(null);//计划达成率
				moneymgrDistrictAccumuPerformancePage.setPerCapitaCapacity(null);//人均产能
				moneymgrDistrictAccumuPerformancePage.setPerCapitaNewClient(null);//人均新用户数
				moneymgrDistrictAccumuPerformancePage.setCounselorManagerRatio(getDistrictCounselorManagerRatio(map));//咨询师人数/管理人员人数
				moneymgrDistrictAccumuPerformancePage.setCreateTime(new Date());
				pages.add(moneymgrDistrictAccumuPerformancePage);
				
			}
		}else {
			//如果查询BI抽取结果不为空,则从list中获取数据
			
			for (Map<String, Object> map : list) {
				MoneymgrDistrictAccumuPerformancePage moneymgrDistrictAccumuPerformancePage = new MoneymgrDistrictAccumuPerformancePage();
				moneymgrDistrictAccumuPerformancePage.setRecordDate(DateTimeUtil.convertAsDateString(dateString));
				moneymgrDistrictAccumuPerformancePage.setDistrictName((String)map.get("district_name"));//区域名称
				moneymgrDistrictAccumuPerformancePage.setPrincipal((String)map.get("district_principal"));//区域负责人
				moneymgrDistrictAccumuPerformancePage.setOrgNum((int)moneymgrOrgDataMapper.getOrgNum(DateTimeUtil.getFirstDayOfMonth(dateString)));//机构数目
				moneymgrDistrictAccumuPerformancePage.setAccumuPerformance((BigDecimal)map.get("performance_value"));//累计业绩
				moneymgrDistrictAccumuPerformancePage.setCompleteRate(getDistrictCompleteRate(map));//计划达成率
				moneymgrDistrictAccumuPerformancePage.setPerCapitaCapacity(getDistrictPerCapitaCapacity(map));//人均产能
				moneymgrDistrictAccumuPerformancePage.setPerCapitaNewClient(getDistrictPerCapitaNewClient(map));//人均新用户数
				moneymgrDistrictAccumuPerformancePage.setCounselorManagerRatio(getDistrictCounselorManagerRatio(map));
				moneymgrDistrictAccumuPerformancePage.setCreateTime(new Date());
				pages.add(moneymgrDistrictAccumuPerformancePage);
			}
			
		}
		moneymgrDistrictAccumuPerformancePageMapper.insertDistrictAccumuPerformancePages(pages);
		logger.info(dateString+"的区域业绩结果页插入完毕");
	}
	
	/**
	 * 获得区域咨询师/管理人员
	 * @param map
	 * @return
	 */
	private BigDecimal getDistrictCounselorManagerRatio(Map<String, Object> map){
		try {
			String districtName = (String)map.get("district_name");
			Integer districtCouselorNum = getDistrictCouselorNum(districtName);//咨询师人数
			Integer districtManagerNum = getDistrictManagerNum(districtName);//管理人员人数
			BigDecimal districtCounselorManagerRatio = new BigDecimal(districtCouselorNum).divide(new BigDecimal(districtManagerNum),2,BigDecimal.ROUND_HALF_UP);
			return districtCounselorManagerRatio;
			
		} catch (Exception e) {
			logger.error("计算区域咨询师/管理人员失败");
			return null;
		}
		
	}
	
	/**
	 * 获得区域人均新客户数
	 * @param map
	 * @return
	 */
	private BigDecimal getDistrictPerCapitaNewClient(Map<String, Object> map){
		try {
			Integer newClientNum = (Integer)map.get("new_client_num");//累计新用户数
			Integer avgDistrictCounselor = getAvgDistrictCounselor(map);//平均人数
			BigDecimal districtPerCapitaNewClient = new BigDecimal(newClientNum).divide(new BigDecimal(avgDistrictCounselor),2,BigDecimal.ROUND_HALF_UP);
			return districtPerCapitaNewClient;
		} catch (Exception e) {
			logger.error("计算区域人均新客户数失败");
			return null;
		}
		
	}
	
	/**
	 * 获得区域计划达成率
	 * @param map
	 * @return
	 */
	private BigDecimal getDistrictCompleteRate(Map<String, Object> map){
		try {
			BigDecimal performance = (BigDecimal)map.get("performance_value");
			BigDecimal monthPlan = new BigDecimal((Integer)map.get("district_month_plan"));
			BigDecimal districtCompleteRate = performance.divide(monthPlan, 2, BigDecimal.ROUND_HALF_UP);
			return districtCompleteRate;
		} catch (Exception e) {
			logger.error("计算区域计划达成率出错");
			return null;
		}
	}
	
	/**
	 * 计算区域人均产能
	 * @return
	 */
	private BigDecimal getDistrictPerCapitaCapacity(Map<String, Object> map) {
		try {
			BigDecimal performance = (BigDecimal)map.get("performance_value");//区域累计业绩
			Integer avgDistrictCounselor = getAvgDistrictCounselor(map);//区域平均人数
			BigDecimal districtPerCapitaCapacity = performance.divide(new BigDecimal(avgDistrictCounselor),2,BigDecimal.ROUND_HALF_UP);
			return districtPerCapitaCapacity;
		} catch (Exception e) {
			logger.error("计算人均产能失败");
			return null;
		}
	}
	
	/**
	 * 获得区域平均人数
	 */
	private Integer getAvgDistrictCounselor(Map<String, Object> map){
		try {
			String districtName = (String)map.get("district_name");//区域名称
			Integer counselorNumLastMonth = (Integer)map.get("counselor_num_last_month");//区域上月咨询师人数
			Integer districtCouselorNum = getDistrictCouselorNum(districtName);//本月咨询师人数
			Integer avgDistrictCounselor = counselorNumLastMonth+districtCouselorNum/2;
			return avgDistrictCounselor;
		} catch (Exception e) {
			logger.error("计算区域平均人数失败");
			return null;
		}
		
	}
	
	/**
	 * 获得区域本月咨询师人数(花名册)
	 * @param districtName区域名称
	 * @return
	 */
	private Integer getDistrictCouselorNum(String districtName){
		MoneymgrRosterExample moneymgrRosterExample = new MoneymgrRosterExample();
		Criteria moneymgrRosterCriteria = moneymgrRosterExample.createCriteria();
		moneymgrRosterCriteria.andDistrictNameEqualTo(districtName);
		moneymgrRosterCriteria.andPositionTypeEqualTo("销售");
		moneymgrRosterCriteria.andDimissionDateIsNull();
		int districtCouselorNum = moneymgrRosterMapper.countByExample(moneymgrRosterExample);
		return districtCouselorNum;
	}
	
	/**
	 * 获得区域本月管理人员人数(花名册)
	 * @param districtName
	 * @return
	 */
	private Integer getDistrictManagerNum(String districtName){
		MoneymgrRosterExample moneymgrRosterExample = new MoneymgrRosterExample();
		Criteria moneymgrRosterCriteria = moneymgrRosterExample.createCriteria();
		moneymgrRosterCriteria.andDistrictNameEqualTo(districtName);
		moneymgrRosterCriteria.andPositionTypeEqualTo("销售管理");
		moneymgrRosterCriteria.andDimissionDateIsNull();
		int districtManagerNum = moneymgrRosterMapper.countByExample(moneymgrRosterExample);
		return districtManagerNum;
	}
	
	
	/**
	 * 封装具体日期的人员人效结果数据
	 * @param dateString 格式:yyyy-MM-dd
	 */
	private MoneymgrEfficiencyPage getMoneymgrEfficiencyPage(String dateString){
		MoneymgrEfficiencyPage moneymgrEfficiencyPage = new MoneymgrEfficiencyPage();
		moneymgrEfficiencyPage.setOrgNum((int)moneymgrOrgDataMapper.getOrgNum(DateTimeUtil.getFirstDayOfMonth(dateString)));//机构数目
		moneymgrEfficiencyPage.setCounselorNum(getCounselorNum());//咨询师人数
		moneymgrEfficiencyPage.setManagerNum(getManagerNum());//管理人员人数
		moneymgrEfficiencyPage.setNewClientNum(getNewClientNum(dateString));//新客户数
		moneymgrEfficiencyPage.setPerCapitaCapacity(getPerCapitacCapacity(dateString));//人均产能
		moneymgrEfficiencyPage.setPerCapitaNewClient(getPerCapitacNewClient(dateString));//人均新客户数
		moneymgrEfficiencyPage.setCounselorManagerRatio(getCounselorManagerRatio(dateString));//咨询师人数/挂历人员人数
		moneymgrEfficiencyPage.setRecordDate(DateTimeUtil.convertAsDateString(dateString));
		moneymgrEfficiencyPage.setCreateTime(new Date());
		logger.info(DateTimeUtil.formatTimeStringToDateString(dateString)+"的业绩人效页面封装完毕");
		return moneymgrEfficiencyPage;
	}
	
	/**
	 * 获取当前全国机构数目:对比表机构数求和
	 * @param dateString 格式为yyyy-MM-dd
	 * 			如2017-05-04
	 * @return
	 */
	private long getOrgNum(String dateString){
		//将日期转为yyyy-MM-01
		String firstDayOfMonth = DateTimeUtil.getFirstDayOfMonth(dateString);
		return moneymgrOrgDataMapper.getOrgNum(firstDayOfMonth);
	}
	
	
	/**
	 * 计算本月全国咨询师人数:花名册人数求和,条件:职类为销售,离职日期非空
	 */
	private int getCounselorNum(){
		MoneymgrRosterExample moneymgrRosterExample = new MoneymgrRosterExample();
		Criteria moneymgrRosterCriteria = moneymgrRosterExample.createCriteria();
		moneymgrRosterCriteria.andPositionTypeEqualTo("销售");
		moneymgrRosterCriteria.andDimissionDateIsNotNull();
		int counselorNum = moneymgrRosterMapper.countByExample(moneymgrRosterExample);
		return counselorNum;
	}
	
	/**
	 * 计算本月全国管理人员人数:花名册人数求和,条件:职类为销售管理,离职日期非空
	 * @return
	 */
	private int getManagerNum(){
		MoneymgrRosterExample moneymgrRosterExample = new MoneymgrRosterExample();
		Criteria moneymgrRosterCriteria = moneymgrRosterExample.createCriteria();
		moneymgrRosterCriteria.andPositionTypeEqualTo("销售管理");
		moneymgrRosterCriteria.andDimissionDateIsNotNull();
		int manageNum = moneymgrRosterMapper.countByExample(moneymgrRosterExample);
		if (manageNum==0) {
			logger.error("计算本月管理人员人数结果为0");
		}
		return manageNum;
	}
	
	/**
	 * 获得累计新增客户数:BI抽取,从月累计业绩表获得
	 * @param dateString
	 * @return
	 */
	private Integer getNewClientNum(String dateString){
		MoneymgrAccumuPerformanceExample moneymgrAccumuPerformanceExample = new MoneymgrAccumuPerformanceExample();
		com.hzcf.flagship.model.MoneymgrAccumuPerformanceExample.Criteria moneymgrAccumuPerformanceCriteria = moneymgrAccumuPerformanceExample.createCriteria();
		moneymgrAccumuPerformanceCriteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dateString));
		List<MoneymgrAccumuPerformance> list = moneymgrAccumuPerformanceMapper.selectByExample(moneymgrAccumuPerformanceExample);
		if (list==null || list.size()==0) {
			return null;
		}
		return list.get(0).getNewClientNum();
	}
	
	/**
	 * 计算咨询师平均人数
	 */
	private Integer getAvgCounselor(String dateString){
		//获得上月咨询师总人数
		int counselorNumOfLastMonth = (int)moneymgrOrgDataMapper.getCounselorNumOfLastMonth(DateTimeUtil.getFirstDayOfMonth(dateString));
		//本月咨询师人数
		int counselorNum = getCounselorNum();
		try {
			if (counselorNumOfLastMonth==0 || counselorNum==0) {
				logger.error("获取"+DateTimeUtil.formatTimeStringToDateString(dateString)+"上月或本月咨询师人数失败");
				return null;
			}
			return (counselorNumOfLastMonth+counselorNum)/2;
		} catch (Exception e) {
			logger.error("计算咨询师平均人数失败");
			return null;
		}
	}
	
	/**
	 * 计算指定日期人均月累计产能:月累计业绩/咨询师平均人数
	 * @param dateString
	 * @return
	 */
	private BigDecimal getPerCapitacCapacity(String dateString){
		//获得月累计产能
		MoneymgrAccumuPerformanceExample moneymgrAccumuPerformanceExample = new MoneymgrAccumuPerformanceExample();
		com.hzcf.flagship.model.MoneymgrAccumuPerformanceExample.Criteria moneymgrAccumuPerformanceCriteria = moneymgrAccumuPerformanceExample.createCriteria();
		moneymgrAccumuPerformanceCriteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dateString));
		List<MoneymgrAccumuPerformance> list = moneymgrAccumuPerformanceMapper.selectByExample(moneymgrAccumuPerformanceExample);
		//获得咨询师平均人数
		Integer avgCounselor = getAvgCounselor(dateString);
		
		if (list==null || list.size()==0 || avgCounselor==null || avgCounselor==0) {
			return null;
		}
		
		return list.get(0).getPerformanceValue().divide(new BigDecimal(avgCounselor),2,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 计算人均新客户数
	 * @param dateString
	 * @return
	 */
	private BigDecimal getPerCapitacNewClient(String dateString){
		//获得月累计新客户数
		Integer newClientNum = getNewClientNum(dateString);
		//获得咨询师平均人数
		Integer avgCounselor = getAvgCounselor(dateString);
		if (newClientNum==null || avgCounselor==null || avgCounselor==0) {
			logger.error("计算"+DateTimeUtil.formatTimeStringToDateString(dateString)+"的人均新客户数失败");
			return null;
		}
		return new BigDecimal(newClientNum).divide(new BigDecimal(avgCounselor),2,BigDecimal.ROUND_HALF_UP);
		
	}
	
	/**
	 * 计算咨询师/管理人员
	 * @param dateString
	 * @return
	 */
	private BigDecimal getCounselorManagerRatio(String dateString){
		//获得咨询师人数
		int counselorNum = getCounselorNum();
		//获得管理人员人数
		int managerNum = getManagerNum();
		if (managerNum == 0) {
			logger.error("管理人员人数为0,不能作为分母");
			return null;
		}
		return new BigDecimal(counselorNum).divide(new BigDecimal(managerNum),2,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 查询对应指标值
	 * @param indexCode
	 * @return
	 */
	private double getIndexValue(String indexCode) {
		IndexExample indexExample = new IndexExample();
		com.hzcf.flagship.model.IndexExample.Criteria indexCriteria = indexExample.createCriteria();
		indexCriteria.andCodeEqualTo(indexCode);
		List<Index> list = indexMapper.selectByExample(indexExample);
		return list.get(0).getValue();
	}
	
	
}
