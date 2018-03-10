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
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.IndexMapper;
import com.hzcf.flagship.dao.MoneymgrAccumuPerformanceMapper;
import com.hzcf.flagship.dao.MoneymgrDailyPerformanceMapper;
import com.hzcf.flagship.dao.MoneymgrDistrictAccumuPerformanceMapper;
import com.hzcf.flagship.dao.MoneymgrDistrictAccumuPerformancePageMapper;
import com.hzcf.flagship.dao.MoneymgrEfficiencyPageMapper;
import com.hzcf.flagship.dao.MoneymgrMonthHistoryMapper;
import com.hzcf.flagship.dao.MoneymgrOrgAccumuPerformanceMapper;
import com.hzcf.flagship.dao.MoneymgrOrgAccumuPerfromancePageMapper;
import com.hzcf.flagship.dao.MoneymgrOrgDataMapper;
import com.hzcf.flagship.dao.MoneymgrPerformanceDailyPageMapper;
import com.hzcf.flagship.dao.MoneymgrRosterMapper;
import com.hzcf.flagship.dao.MoneymgrTotalMonthPlanMapper;
import com.hzcf.flagship.model.Index;
import com.hzcf.flagship.model.IndexExample;
import com.hzcf.flagship.model.MoneymgrAccumuPerformance;
import com.hzcf.flagship.model.MoneymgrAccumuPerformanceExample;
import com.hzcf.flagship.model.MoneymgrDailyPerformance;
import com.hzcf.flagship.model.MoneymgrDailyPerformanceExample;
import com.hzcf.flagship.model.MoneymgrDistrictAccumuPerformancePage;
import com.hzcf.flagship.model.MoneymgrDistrictAccumuPerformancePageExample;
import com.hzcf.flagship.model.MoneymgrEfficiencyPage;
import com.hzcf.flagship.model.MoneymgrEfficiencyPageExample;
import com.hzcf.flagship.model.MoneymgrOrgAccumuPerformance;
import com.hzcf.flagship.model.MoneymgrOrgAccumuPerformanceExample;
import com.hzcf.flagship.model.MoneymgrOrgAccumuPerfromancePage;
import com.hzcf.flagship.model.MoneymgrOrgAccumuPerfromancePageExample;
import com.hzcf.flagship.model.MoneymgrOrgData;
import com.hzcf.flagship.model.MoneymgrOrgDataExample;
import com.hzcf.flagship.model.MoneymgrPerformanceDailyPage;
import com.hzcf.flagship.model.MoneymgrPerformanceDailyPageExample;
import com.hzcf.flagship.model.MoneymgrRosterExample;
import com.hzcf.flagship.model.MoneymgrTotalMonthPlan;
import com.hzcf.flagship.model.MoneymgrTotalMonthPlanExample;
import com.hzcf.flagship.model.MoneymgrTotalMonthPlanExample.Criteria;
import com.hzcf.flagship.service.MoneymgrJobService;
import com.hzcf.flagship.util.DateTimeUtil;


/**
 *<dl>
 *<dt>类名：MoneymgrPerformanceServiceImpl.java</dt>
 *<dd>描述: 理财业绩实现类</dd> 
 *<dd>创建时间： 2017年5月13日 下午3:16:07</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月13日 下午3:16:07    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Service
public class MoneymgrJobServiceImpl implements MoneymgrJobService{
	private final static Logger logger = Logger.getLogger(MoneymgrJobServiceImpl.class);
	@Autowired
	private MoneymgrDailyPerformanceMapper moneymgrDailyPerformanceMapper;
	@Autowired
	private MoneymgrTotalMonthPlanMapper moneymgrTotalMonthPlanMapper;
	@Autowired
	private IndexMapper indexMapper;
	@Autowired
	private MoneymgrAccumuPerformanceMapper moneymgrAccumuPerformanceMapper;
	@Autowired
	private MoneymgrOrgDataMapper moneymgrOrgDataMapper;
	@Autowired
	private MoneymgrOrgAccumuPerformanceMapper moneymgrOrgAccumuPerformanceMapper;
	@Autowired
	private MoneymgrRosterMapper moneymgrRosterMapper;
	@Autowired
	private MoneymgrMonthHistoryMapper moneymgrMonthHistoryMapper;
	@Autowired
	private MoneymgrPerformanceDailyPageMapper moneymgrPerformanceDailyPageMapper;
	@Autowired
	private MoneymgrOrgAccumuPerfromancePageMapper moneymgrOrgAccumuPerfromancePageMapper;
	
	@Autowired
	private MoneymgrEfficiencyPageMapper moneymgrEfficiencyPageMapper;
	@Autowired
	private MoneymgrDistrictAccumuPerformanceMapper moneymgrDistrictAccumuPerformanceMapper;
	@Autowired
	private MoneymgrDistrictAccumuPerformancePageMapper moneymgrDistrictAccumuPerformancePageMapper;
	/*@Autowired
	private FinanceDailyPageMapper financeDailyPageMapper;*/
	
	/**
	 * 更新本月所有日业绩页面数据
	 * @throws ParseException
	 */
	public void updateDailyPerformancePageData() throws ParseException{
		//1.删除当前时间前一天所在月的所有结果数据
		MoneymgrPerformanceDailyPageExample moneymgrPerformanceDailyPageExample = new MoneymgrPerformanceDailyPageExample();
		com.hzcf.flagship.model.MoneymgrPerformanceDailyPageExample.Criteria dailyPageCriteria = moneymgrPerformanceDailyPageExample.createCriteria();
		dailyPageCriteria.andRecordDateBetween(DateTimeUtil.getfistDayOfMonthOnYesterday(), DateTimeUtil.getlastDayOfMonthOnYesterday());
		moneymgrPerformanceDailyPageMapper.deleteByExample(moneymgrPerformanceDailyPageExample);
		//封装月数据list(日期从昨天所在月第一天--昨天)
		List<MoneymgrPerformanceDailyPage> pages = new ArrayList<MoneymgrPerformanceDailyPage>();
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
			pages.add(getDailyPerformancePageData(dateString));
			logger.info("已封装好日业绩页面"+dateString+"的数据");
			firstDayOfMonthInYesterday.add(Calendar.DAY_OF_MONTH, 1);
		}
		moneymgrPerformanceDailyPageMapper.insertDailyPerformancePages(pages);
		logger.info("本月所有日业绩页面数据更新完毕");
	}
	
	/**
	 * 更新当日前一天所在月所有地图结果数据
	 * @throws Exception 
	 */
	public void myInsertAllOrgAccumuPerformanceData() throws Exception{
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
			insertOrgDatasByDate(dateString);
			firstDayOfMonthInYesterday.add(Calendar.DAY_OF_MONTH, 1);
		}
		logger.info("所有地图数据更新完毕");
	}
	
	/**
	 * 获得指定日期的日业绩结果表数据
	 * @param dataString
	 * @throws ParseException
	 */
	private MoneymgrPerformanceDailyPage getDailyPerformancePageData(String dataString) throws ParseException{
		//封装数据
		//Map<String, Object> map = new HashMap<>();
		MoneymgrPerformanceDailyPage moneymgrPerformanceDailyPage = new MoneymgrPerformanceDailyPage();
		
		moneymgrPerformanceDailyPage.setRecordDate(DateTimeUtil.convertAsDateString(dataString));
		moneymgrPerformanceDailyPage.setPerformanceValue(getIntegerDailyPerformance(dataString));
		moneymgrPerformanceDailyPage.setCompleteRate(getDailyPerformanceRate(dataString));
		moneymgrPerformanceDailyPage.setDengesMinLine(0);
		moneymgrPerformanceDailyPage.setDengesWarningLine((int)getIndexValue("moneyMgr_daily_perform_warning"));
		moneymgrPerformanceDailyPage.setDengesWellLine((int)getIndexValue("moneyMgr_daily_perform_well"));
		moneymgrPerformanceDailyPage.setDengesMaxLine(150);
		moneymgrPerformanceDailyPage.setMonthPlan(getIntegerTotalMonthPlan(dataString));
		moneymgrPerformanceDailyPage.setDateSchedule(getIntTimeSchedule(dataString));
		moneymgrPerformanceDailyPage.setAccumuPerformance(getIntegerAccumuPerformance(dataString));
		moneymgrPerformanceDailyPage.setAccumuCompleteRate(getAccumuCompleteRate(dataString));
		moneymgrPerformanceDailyPage.setAccumuMinLine(0);
		moneymgrPerformanceDailyPage.setAccumuWarningLine(getAccumuCompleteRateIndex(dataString, "moneyMgr_daily_accumu_warning"));
		moneymgrPerformanceDailyPage.setAccumuWellLine(getAccumuCompleteRateIndex(dataString, "moneyMgr_daily_accumu_well"));
		moneymgrPerformanceDailyPage.setAccumuMaxLine(getAccumuCompleteRateIndex(dataString, 1.5));
		moneymgrPerformanceDailyPage.setAvgAccumuPerformance(getOrgAvgAccumuPerformance(dataString));
		moneymgrPerformanceDailyPage.setDailyPlan(getIntegerDailyPlan(dataString));
		
		/*//如果时间是当前时间的上一天
		MoneymgrPerformanceDailyPageExample moneymgrPerformanceDailyPageExample = new MoneymgrPerformanceDailyPageExample();
		com.hzcf.flagship.model.MoneymgrPerformanceDailyPageExample.Criteria dailyPageCriteria = moneymgrPerformanceDailyPageExample.createCriteria();
		dailyPageCriteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dataString));
		List<MoneymgrPerformanceDailyPage> result = moneymgrPerformanceDailyPageMapper.selectByExample(moneymgrPerformanceDailyPageExample);
		if (result!=null && result.size()>0) {
			moneymgrPerformanceDailyPageMapper.deleteByPrimaryKey(result.get(0).getId());
		}*/
		//插入新数据
		//moneymgrPerformanceDailyPageMapper.insert(moneymgrPerformanceDailyPage);
		return moneymgrPerformanceDailyPage;
	}
	
	
	
	/**
	 * 计算指定日期日业绩完成率
	 * @param dateString 日期格式为:yyyy-MM-dd
	 * @return 返回的是百分比的分子整数
	 * @throws ParseException 
	 */
	private Integer getDailyPerformanceRate(String dateString) throws ParseException{
		//1.计算日总业绩(分机构日业绩表,业绩求和)
		BigDecimal dailyPerformance = getDailyPerformance(dateString);
		//2得到日计划
		BigDecimal dailyPlan = getDailyPlan(dateString);
		//
		if (dailyPerformance==null || dailyPlan==null) {
			return null;
		}
		BigDecimal dailyPerformanceRate = dailyPerformance.divide(dailyPlan,2,BigDecimal.ROUND_DOWN ).multiply(new BigDecimal(100));
		int result = dailyPerformanceRate.intValue();
		if (result == 0) {
			result = 1;
		}
		return result;
	}
	
	/**
	 * 获得指定日期月业绩总计划(从当月月总计划表查询)
	 * @param dateString格式为yyyy-MM-dd
	 * @return
	 */
	private BigDecimal getTotalMonthPlan(String dateString){
		//指定数据库查询条件
		MoneymgrTotalMonthPlanExample monthPlanExample = new MoneymgrTotalMonthPlanExample();
		Criteria criteria = monthPlanExample.createCriteria();
		/*//将dateString设为当月1日
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateTimeUtil.convertAsDateString(dateString));
		calendar.set(Calendar.DAY_OF_MONTH, 1);*/
		criteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(DateTimeUtil.getFirstDayOfMonth(dateString)));
		List<MoneymgrTotalMonthPlan> list = moneymgrTotalMonthPlanMapper.selectByExample(monthPlanExample);
		//如果数据库无月总计划,则返回null
		if (list==null || list.size()==0) {
			logger.error("数据库无"+dateString+"当月月总计划数据");
			return null;
		}
		//list.get(0).getValue();
		return list.get(0).getValue();
	}
	
	/**
	 * 获得Integer类型的月计划
	 * @param dateString
	 * @return
	 */
	private Integer getIntegerTotalMonthPlan(String dateString){
		BigDecimal bigDecimalValue = getTotalMonthPlan(dateString);
		if (bigDecimalValue == null) {
			return null;
		}
		return bigDecimalValue.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
	}
	
	/**
	 * 获得指定日期总业绩
	 * @param dateString日期格式为:yyyy-MM-dd
	 */
	private BigDecimal getDailyPerformance(String dateString){
		//计算日总业绩
		MoneymgrDailyPerformanceExample moneymgrDailyPerformanceExample = new MoneymgrDailyPerformanceExample();
		com.hzcf.flagship.model.MoneymgrDailyPerformanceExample.Criteria dailyCriteria = moneymgrDailyPerformanceExample.createCriteria();
		dailyCriteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dateString));
		List<MoneymgrDailyPerformance> list = moneymgrDailyPerformanceMapper.selectByExample(moneymgrDailyPerformanceExample);
		if (list==null || list.size()==0) {
			logger.info("数据库无"+dateString+"总业绩数据");
			return null;
		}
		return list.get(0).getPerformanceValue();
	}
	
	/**
	 * 获得指定日期业绩,结果是Integer类型,根据bigdecimal类型的数据四舍五入
	 */
	private Integer getIntegerDailyPerformance(String dateString){
		BigDecimal bigDecimalValue = getDailyPerformance(dateString);
		if (bigDecimalValue == null) {
			return null;
		}
		return bigDecimalValue.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
	}
	
	/**
	 * 计算日计划
	 * @param dateString
	 */
	private BigDecimal getDailyPlan(String dateString){
		//得到月计划业绩
		BigDecimal totalMonthPlan = getTotalMonthPlan(dateString);
		//如果月计划为null,则日计划也为null
		if(totalMonthPlan==null){
			logger.error("因为没有月计划数据,计算日计划失败");
			return null;
		}
		//得到指定月天数
		int monthDays = DateTimeUtil.getLastMonthDays(dateString);
		//计算日计划:月计划/月天数
		return totalMonthPlan.divide(new BigDecimal(monthDays),2,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 获得Integer类型的日计划
	 * @param dateString
	 * @return
	 */
	private Integer getIntegerDailyPlan(String dateString){
		BigDecimal bigDecimalValue = getDailyPlan(dateString);
		if (bigDecimalValue == null) {
			return null;
		}
		return bigDecimalValue.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
	}
	
	/**
	 * 计算对应时间月累计达成率
	 * @param dateString
	 * @return
	 */
	private Integer getAccumuCompleteRate(String dateString){
		//获得对应时间月累计业绩
		BigDecimal accumuPerformance = getAccumuPerformance(dateString);
		//获得月计划
		BigDecimal totalMonthPlan = getTotalMonthPlan(dateString);
		
		if (accumuPerformance==null || totalMonthPlan==null) {
			logger.info("因数据库无对应数据,计算月累计达成率失败");
			return null;
		}
		//计算月累计达成率:月累计业绩/月计划
		BigDecimal rate = accumuPerformance.divide(totalMonthPlan,2,BigDecimal.ROUND_DOWN);
		int result = rate.multiply(new BigDecimal(100)).intValue();
		if (result==0) {
			result=1;
		}
		return result;
	}
	
	/**
	 * 计算时间进度,返回int类型
	 * @param dateString 格式为yyyy-MM-dd
	 * @return
	 */
	private int getIntTimeSchedule(String dateString){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateTimeUtil.convertAsDateString(dateString));
		BigDecimal pastDays = new BigDecimal(calendar.get(Calendar.DAY_OF_MONTH));//已过天数
		BigDecimal monthDays = new BigDecimal(DateTimeUtil.getLastMonthDays(dateString));//当月总天数
		BigDecimal rate = pastDays.divide(monthDays,2,BigDecimal.ROUND_HALF_UP);//四舍五入
		return rate.multiply(new BigDecimal(100)).intValue();//返回整数部分
	}
	
	/**
	 * 计算时间进度,返回bigdecimal类型
	 * @param dateString 格式为yyyy-MM-dd
	 * @return
	 */
	private BigDecimal getDecimalTimeSchedule(String dateString){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateTimeUtil.convertAsDateString(dateString));
		BigDecimal pastDays = new BigDecimal(calendar.get(Calendar.DAY_OF_MONTH));//已过天数
		BigDecimal monthDays = new BigDecimal(DateTimeUtil.getLastMonthDays(dateString));//当月总天数
		BigDecimal rate = pastDays.divide(monthDays,4,BigDecimal.ROUND_HALF_UP);//四舍五入
		return rate.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);//返回两位小数
	}
	
	/**
	 * 计算月累计达成率指标
	 * @return 返回分子部分整数
	 */
	private int getAccumuCompleteRateIndex(String dateString,String indexCode){
		int indexValue = (int)getIndexValue(indexCode);
		//当前时间进度
		//int timeSchedule = getIntTimeSchedule(dateString);
		BigDecimal timeSchedule = getDecimalTimeSchedule(dateString);
		//当前时间进度*月累计达成率*100%
		//return timeSchedule*indexValue/100;
		return timeSchedule.multiply(new BigDecimal(indexValue)).divide(new BigDecimal(100),0,BigDecimal.ROUND_HALF_UP).intValue();
		
	}
	
	/**
	 * 计算月累计达成率指标(最大,最小值)
	 * @param dateString
	 * @param percent
	 * @return
	 */
	private int getAccumuCompleteRateIndex(String dateString,double percent){
		//当前时间进度
		//int timeSchedule = getIntTimeSchedule(dateString);
		BigDecimal timeSchedule = getDecimalTimeSchedule(dateString);
		//当前时间进度*月累计达成率*100%
		//return (int)(timeSchedule*percent);
		return timeSchedule.multiply(new BigDecimal(percent)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		
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
		return list.get(0).getValue().intValue();
	}
	
	/**
	 * 查询对应日期月累计业绩
	 * @param dateString
	 * @return
	 */
	private BigDecimal getAccumuPerformance(String dateString){
		MoneymgrAccumuPerformanceExample moneymgrAccumuPerformanceExample = new MoneymgrAccumuPerformanceExample();
		com.hzcf.flagship.model.MoneymgrAccumuPerformanceExample.Criteria criteria = moneymgrAccumuPerformanceExample.createCriteria();
		criteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dateString));
		List<MoneymgrAccumuPerformance> list = moneymgrAccumuPerformanceMapper.selectByExample(moneymgrAccumuPerformanceExample);
		if (list==null || list.size()==0) {
			logger.info("数据库无"+dateString+"的月累计业绩,查询失败");
			return null;
		}
		return list.get(0).getPerformanceValue();
	}
	
	/**
	 * 获得Integer类型的月累计业绩
	 * @param dateString
	 * @return
	 */
	private Integer getIntegerAccumuPerformance(String dateString){
		BigDecimal bigDecimalValue = getAccumuPerformance(dateString);
		if (bigDecimalValue == null) {
			return null;
		}
		return bigDecimalValue.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
	}
	
	/**
	 * 计算全国营业部月累计业绩平均数
	 * @param dateString
	 * @return
	 * @throws ParseException 
	 */
	private Integer getOrgAvgAccumuPerformance(String dateString) throws ParseException{
		//得到月累计业绩
		BigDecimal accumuPerformance = getAccumuPerformance(dateString);
		//查询营业部的总数
		long orgNum = moneymgrOrgDataMapper.getOrgNum(DateTimeUtil.getFirstDayOfMonth(dateString));
		if (accumuPerformance==null || orgNum==0) {
			logger.error("查询月累计业绩失败或者查询营业部数量结果为0,计算全国营业部月累计业绩平均数失败");
			return null;
		}
		//计算全国营业部月累计业绩平均数:月累计业绩/营业部总数
		BigDecimal orgAvgAccumuPerformance = accumuPerformance.divide(new BigDecimal(orgNum),0,BigDecimal.ROUND_HALF_UP);
		return orgAvgAccumuPerformance.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
	}
	
	/**
	 * 得到指定日期(当月)的机构数据(地图数据)
	 * @param orgSimpleName
	 * @param dateString
	 * @return
	 * @throws Exception 
	 */
	private MoneymgrOrgAccumuPerfromancePage getOrgData(String orgSimpleName,String dateString) throws Exception{
		//Map<String, Object> map = new HashMap<>();
		MoneymgrOrgAccumuPerfromancePage page = new MoneymgrOrgAccumuPerfromancePage();
		page.setOrgName(orgSimpleName);
		page.setRecordDate(DateTimeUtil.convertAsDateString(dateString));
		//获得具体机构和指定时间的机构数据
		MoneymgrOrgDataExample moneymgrOrgDataExample = new MoneymgrOrgDataExample();
		com.hzcf.flagship.model.MoneymgrOrgDataExample.Criteria orgCriteria1 = moneymgrOrgDataExample.createCriteria();
		orgCriteria1.andOrgNameEqualTo(orgSimpleName);
		orgCriteria1.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(DateTimeUtil.getFirstDayOfMonth(dateString)));
		List<MoneymgrOrgData> list = moneymgrOrgDataMapper.selectByExample(moneymgrOrgDataExample);
		BigDecimal monthPlan = null;//当月计划
		String principal = null;//区域负责人
		Integer counselorNumOfLastMonth = null;//上月咨询师人数
		if (list!=null && list.size()>0) {
			monthPlan = list.get(0).getMonthPlan();
			principal = list.get(0).getDistrictPrincipal();
			counselorNumOfLastMonth = list.get(0).getCounselorNumLastMonth();
			//return null;
		}else {
			logger.error("对比表无"+orgSimpleName+"数据,查询月计划,区域负责人,上月咨询师人数失败,请上传对比表");
		}
		//放入区域负责人数据
		page.setDistrictPrincipal(principal);
		//放入机构负责人数据
		page.setOrgPrincipal(moneymgrRosterMapper.getOrgPrincipal(orgSimpleName));
		//计算机构月累计达成率
		//1.获得机构月累计业绩,排名
		MoneymgrOrgAccumuPerformanceExample moneymgrOrgAccumuPerformanceExample = new MoneymgrOrgAccumuPerformanceExample();
		com.hzcf.flagship.model.MoneymgrOrgAccumuPerformanceExample.Criteria orgPerformanceCriteria = moneymgrOrgAccumuPerformanceExample.createCriteria();
		orgPerformanceCriteria.andOrgNameEqualTo(orgSimpleName);
		orgPerformanceCriteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dateString));
		List<MoneymgrOrgAccumuPerformance> list2 = moneymgrOrgAccumuPerformanceMapper.selectByExample(moneymgrOrgAccumuPerformanceExample);
		BigDecimal performanceValue = null;//机构月累计业绩
		Integer performanceRanking=null;//业绩排名
		Integer newClientNum = null;
		//如果数据库有数据,则将业绩和排名数据取出
		if (list2!=null && list2.size()>0) {
			performanceValue = list2.get(0).getPerformanceValue();
			performanceRanking = list2.get(0).getPerformanceRanking();
			newClientNum = list2.get(0).getNewClientNum();
		}
		//放入机构月累计业绩
		page.setAccumuPerformance(performanceValue);
		//放入排名
		page.setRanking(performanceRanking);
		
		//2.计算机构月累计达成率
		BigDecimal accumuCompleteRate = null;
		//如果月累计业绩和月计划都不为空则进行月累计达成率计算
		if (performanceValue!=null && monthPlan!=null &&  monthPlan.compareTo(new BigDecimal(0))!=0) {
			BigDecimal rate = performanceValue.divide(monthPlan,4,BigDecimal.ROUND_DOWN);
			accumuCompleteRate = rate.multiply(new BigDecimal(100));
			if (accumuCompleteRate.compareTo(new BigDecimal(0.01))==-1) {
				accumuCompleteRate=new BigDecimal(0.01);
			}
		}
		//放入月累计达成率
		page.setAccumuCompleteRate(accumuCompleteRate);
				
		//获得管理人员人数
		long managerNum = moneymgrRosterMapper.getManagerNum(orgSimpleName);
		//map.put("manager_num", managerNum);
		page.setManagerNum((int)managerNum);
		//获得咨询师人数
		long counselorNum = moneymgrRosterMapper.getCounselorNum(orgSimpleName);
		//map.put("counselor_num", counselorNum);
		page.setCounselorNum((int)counselorNum);
		//获得咨询师/管理人员
		BigDecimal couNumManaNumrate = null;
		try {
			couNumManaNumrate = new BigDecimal(counselorNum).divide(new BigDecimal(managerNum),2,BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) {//分母为0
			logger.error("查询"+orgSimpleName+"的本月管理员人数结果为0");
		}
		//放入咨询师/管理人员
		page.setCounselorManagerRatio(couNumManaNumrate);
		//查询指定机构上月咨询师人数
		//1.计算上月时间(精确到月份)
		
		BigDecimal perCapitaCapacity = null;
		BigDecimal avgCounselorNum = null;
		BigDecimal perCapitaNewClient = null;
		//如果上月咨询师人数不为空,本月咨询师人数不为0,则计算平均人数
		if (counselorNumOfLastMonth!=null && counselorNum!=0) {
			//(上月咨询师人数+本月咨询师人数) /2
			avgCounselorNum = new BigDecimal(counselorNumOfLastMonth+counselorNum).divide(new BigDecimal(2),2,BigDecimal.ROUND_HALF_UP);
			//如果月累计业绩不为null,则计算人均产能
			if (performanceValue!=null) {
				perCapitaCapacity = performanceValue.divide(avgCounselorNum, 2, BigDecimal.ROUND_HALF_UP);
			}
			//如果新客户数不为空,则计算人均新客户数
			if (newClientNum!=null) {
				perCapitaNewClient = new BigDecimal(newClientNum).divide(avgCounselorNum,2,BigDecimal.ROUND_HALF_UP);
			}else {
				logger.error("查询"+orgSimpleName+dateString+"累计新客户数为null");
			}
			
		}else {
			logger.error(orgSimpleName+dateString+"本月咨询师人数为0或无上月咨询师人数");
		}
		//放入人均产能
		page.setPerCapitaCapacity(perCapitaCapacity);
		//放入人均新客户数
		page.setPerCapitaNewClient(perCapitaNewClient);
		
		return page;
	}
	
	/**
	 * 根据跑批的分机构数据,查询到当天所有的机构
	 * @param dateString
	 * @return
	 */
	private List<MoneymgrOrgAccumuPerformance> getAllAccumuPerformanceByDate(String dateString){
		//查询分机构月累计表
		MoneymgrOrgAccumuPerformanceExample orgAccumuPerformanceExample = new MoneymgrOrgAccumuPerformanceExample();
		com.hzcf.flagship.model.MoneymgrOrgAccumuPerformanceExample.Criteria orgAccumuPerformanceCriteria = orgAccumuPerformanceExample.createCriteria();
		orgAccumuPerformanceCriteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dateString));
		List<MoneymgrOrgAccumuPerformance> list = moneymgrOrgAccumuPerformanceMapper.selectByExample(orgAccumuPerformanceExample);
		if (list==null || list.size()==0) {
			logger.info("数据库无"+dateString+"的累计业绩");
			return null;
		}else {
			return list;
		}
		
	}
	
	/**
	 * 插入指定时间的机构结果数据
	 * @param dateString
	 * @throws Exception 
	 */
	private void insertOrgDatasByDate(String dateString) throws Exception{
		//删除当天所有结果数据
		MoneymgrOrgAccumuPerfromancePageExample moneymgrOrgAccumuPerfromancePageExample = new MoneymgrOrgAccumuPerfromancePageExample();
		com.hzcf.flagship.model.MoneymgrOrgAccumuPerfromancePageExample.Criteria accumuPage = moneymgrOrgAccumuPerfromancePageExample.createCriteria();
		accumuPage.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dateString));
		moneymgrOrgAccumuPerfromancePageMapper.deleteByExample(moneymgrOrgAccumuPerfromancePageExample);
		//拼接当天数据
		ArrayList<MoneymgrOrgAccumuPerfromancePage> pages = new ArrayList<>();
		List<MoneymgrOrgAccumuPerformance> accumuPerformances = getAllAccumuPerformanceByDate(dateString);
		if (accumuPerformances!=null && accumuPerformances.size()>0) {
			for (MoneymgrOrgAccumuPerformance moneymgrOrgAccumuPerformance : accumuPerformances) {
				String orgName = moneymgrOrgAccumuPerformance.getOrgName();
				pages.add(getOrgData(orgName, dateString));
			}
			moneymgrOrgAccumuPerfromancePageMapper.insertAccumuPerformancePages(pages);
			logger.info(dateString+"的机构结果数据(地图)更新完毕");
		}else {
			logger.error("无"+dateString+"机构结果数据(地图)");
		}
		
	}
	
	/*private final static Logger logger = Logger.getLogger(MoneymgrJobServiceImpl.class);
	@Autowired
	MoneymgrOrgDataMapper moneymgrOrgDataMapper;
	@Autowired
	MoneymgrRosterMapper moneymgrRosterMapper;
	@Autowired
	MoneymgrAccumuPerformanceMapper moneymgrAccumuPerformanceMapper;
	@Autowired
	MoneymgrEfficiencyPageMapper moneymgrEfficiencyPageMapper;
	@Autowired
	MoneymgrDistrictAccumuPerformanceMapper moneymgrDistrictAccumuPerformanceMapper;
	@Autowired
	MoneymgrDistrictAccumuPerformancePageMapper moneymgrDistrictAccumuPerformancePageMapper;
	@Autowired
	IndexMapper indexMapper;
	@Autowired
	FinanceDailyPageMapper financeDailyPageMapper;*/
	
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
		if (districtDatas==null || districtDatas.size()==0) {
			logger.error("对比表未上传当月数据");
			return;
		}
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
	/*public void insertFinanceDailyPage(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);//昨天
		FinanceDailyPage financeDailyPage = new FinanceDailyPage();
		
		financeDailyPage.setRecordDate(calendar.getTime());
		financeDailyPage.setFinancemgrDailyPlanyieldrateWarning(new BigDecimal(getIndexValue("financeMgr_daily_planYieldRate_warning")));
		financeDailyPage.setFinancemgrDailyPlanyieldrateWell(new BigDecimal(getIndexValue("financeMgr_daily_planYieldRate_well")));
		financeDailyPageMapper.insert(financeDailyPage);
	}*/
	
	
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
		List<Map<String, Object>> list = moneymgrDistrictAccumuPerformanceMapper.getDistrictData(DateTimeUtil.formatTimeStringToDateString(dateString));
		if (list==null || list.size()==0) {
			logger.error("BI抽取"+dateString+"区域数据结果为空");
			//从disttrictDatas中获得数据插入(无BI抽取结果)
			for (Map<String, Object> map : districtDatas) {
				MoneymgrDistrictAccumuPerformancePage moneymgrDistrictAccumuPerformancePage = new MoneymgrDistrictAccumuPerformancePage();
				moneymgrDistrictAccumuPerformancePage.setRecordDate(DateTimeUtil.convertAsDateString(dateString));
				moneymgrDistrictAccumuPerformancePage.setDistrictName((String)map.get("district_name"));//区域名称
				moneymgrDistrictAccumuPerformancePage.setPrincipal((String)map.get("district_principal"));//区域负责人
				moneymgrDistrictAccumuPerformancePage.setOrgNum(((Long)map.get("district_org_num")).intValue());//机构数目
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
				moneymgrDistrictAccumuPerformancePage.setOrgNum(((Long)map.get("district_org_num")).intValue());//机构数目
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
			logger.error(e.getMessage());
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
			BigDecimal avgDistrictCounselor = getAvgDistrictCounselor(map);//平均人数
			BigDecimal districtPerCapitaNewClient = new BigDecimal(newClientNum).divide(avgDistrictCounselor,2,BigDecimal.ROUND_HALF_UP);
			return districtPerCapitaNewClient;
		} catch (Exception e) {
			logger.error("计算区域人均新客户数失败");
			logger.error(e.getMessage());
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
			BigDecimal monthPlan = (BigDecimal)map.get("district_month_plan");
			BigDecimal districtCompleteRate = performance.divide(monthPlan, 4, BigDecimal.ROUND_HALF_UP);
			districtCompleteRate=districtCompleteRate.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
			return districtCompleteRate;
		} catch (Exception e) {
			logger.error("计算区域计划达成率出错");
			logger.error(e.getMessage());
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
			BigDecimal avgDistrictCounselor = getAvgDistrictCounselor(map);//区域平均人数
			BigDecimal districtPerCapitaCapacity = performance.divide(avgDistrictCounselor,2,BigDecimal.ROUND_HALF_UP);
			return districtPerCapitaCapacity;
		} catch (Exception e) {
			logger.error("计算人均产能失败");
			logger.error(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获得区域平均人数
	 */
	private BigDecimal getAvgDistrictCounselor(Map<String, Object> map){
		try {
			String districtName = (String)map.get("district_name");//区域名称
			Integer counselorNumLastMonth = ((BigDecimal)map.get("counselor_num_last_month")).intValue();//区域上月咨询师人数
			Integer districtCouselorNum = getDistrictCouselorNum(districtName);//本月咨询师人数
			//Integer avgDistrictCounselor = (counselorNumLastMonth+districtCouselorNum)/2;
			BigDecimal avgDistrictCounselor = new BigDecimal(counselorNumLastMonth+districtCouselorNum).divide(new BigDecimal(2),2,BigDecimal.ROUND_HALF_UP);
			return avgDistrictCounselor;
		} catch (Exception e) {
			logger.error("计算区域平均人数失败");
			logger.error(e.getMessage());
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
		com.hzcf.flagship.model.MoneymgrRosterExample.Criteria moneymgrRosterCriteria = moneymgrRosterExample.createCriteria();
		moneymgrRosterCriteria.andDistrictNameEqualTo(districtName);
		moneymgrRosterCriteria.andPositionTypeEqualTo("销售序列");
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
		com.hzcf.flagship.model.MoneymgrRosterExample.Criteria moneymgrRosterCriteria = moneymgrRosterExample.createCriteria();
		moneymgrRosterCriteria.andDistrictNameEqualTo(districtName);
		moneymgrRosterCriteria.andPositionTypeEqualTo("销售管理序列");
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
		moneymgrEfficiencyPage.setCounselorManagerRatio(getCounselorManagerRatio(dateString));//咨询师人数/管理人员人数
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
	 * 计算本月全国咨询师人数:花名册人数求和,条件:职类为销售序列,离职日期为空
	 */
	private int getCounselorNum(){
		MoneymgrRosterExample moneymgrRosterExample = new MoneymgrRosterExample();
		com.hzcf.flagship.model.MoneymgrRosterExample.Criteria moneymgrRosterCriteria = moneymgrRosterExample.createCriteria();
		moneymgrRosterCriteria.andPositionTypeEqualTo("销售序列");
		moneymgrRosterCriteria.andDimissionDateIsNull();
		int counselorNum = moneymgrRosterMapper.countByExample(moneymgrRosterExample);
		return counselorNum;
	}
	
	/**
	 * 计算本月全国管理人员人数:花名册人数求和,条件:职类为销售管理序列,离职日期为空
	 * @return
	 */
	private int getManagerNum(){
		MoneymgrRosterExample moneymgrRosterExample = new MoneymgrRosterExample();
		com.hzcf.flagship.model.MoneymgrRosterExample.Criteria moneymgrRosterCriteria = moneymgrRosterExample.createCriteria();
		moneymgrRosterCriteria.andPositionTypeEqualTo("销售管理序列");
		moneymgrRosterCriteria.andDimissionDateIsNull();
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
	private BigDecimal getAvgCounselor(String dateString){
		//获得上月咨询师总人数
		try {
			int counselorNumOfLastMonth = (int)moneymgrOrgDataMapper.getCounselorNumOfLastMonth(DateTimeUtil.getFirstDayOfMonth(dateString));
			//本月咨询师人数
			int counselorNum = getCounselorNum();
			if (counselorNumOfLastMonth==0 || counselorNum==0) {
				logger.error("获取"+DateTimeUtil.formatTimeStringToDateString(dateString)+"上月或本月咨询师人数失败");
				return null;
			}
			//return (counselorNumOfLastMonth+counselorNum)/2;
			return new BigDecimal(counselorNumOfLastMonth+counselorNum).divide(new BigDecimal(2),2,BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) {
			logger.error("计算咨询师平均人数失败");
			logger.error(e.getMessage());
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
		BigDecimal avgCounselor = getAvgCounselor(dateString);
		
		if (list==null || list.size()==0 || avgCounselor==null || avgCounselor.compareTo(new BigDecimal(0))==0) {
			return null;
		}
		
		return list.get(0).getPerformanceValue().divide(avgCounselor,2,BigDecimal.ROUND_HALF_UP);
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
		BigDecimal avgCounselor = getAvgCounselor(dateString);
		if (newClientNum==null || avgCounselor==null || avgCounselor.compareTo(new BigDecimal(0))==0) {
			logger.error("计算"+DateTimeUtil.formatTimeStringToDateString(dateString)+"的人均新客户数失败,新客户数:"+newClientNum+",平均咨询师人数:"+avgCounselor);
			return null;
		}
		return new BigDecimal(newClientNum).divide(avgCounselor,2,BigDecimal.ROUND_HALF_UP);
		
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
	/*private double getIndexValue(String indexCode) {
		IndexExample indexExample = new IndexExample();
		com.hzcf.flagship.model.IndexExample.Criteria indexCriteria = indexExample.createCriteria();
		indexCriteria.andCodeEqualTo(indexCode);
		List<Index> list = indexMapper.selectByExample(indexExample);
		return list.get(0).getValue();
	}*/
	
}
