package com.hzcf.flagship.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hzcf.flagship.dao.AppUserMapper;
import com.hzcf.flagship.dao.AssetDailyPerformanceMapper;
import com.hzcf.flagship.dao.CheckLogMapper;
import com.hzcf.flagship.dao.FinanceDailyDistrictMapper;
import com.hzcf.flagship.dao.FinanceDailyOrgMapper;
import com.hzcf.flagship.dao.FinanceDailyPerformanceMapper;
import com.hzcf.flagship.dao.FinanceMonthDistrictMapper;
import com.hzcf.flagship.dao.FinanceMonthOrgMapper;
import com.hzcf.flagship.dao.FinanceMonthPerformanceMapper;
import com.hzcf.flagship.dao.FinancePersonalInfoMapper;
import com.hzcf.flagship.dao.FinanceRiskMapper;
import com.hzcf.flagship.dao.MoneymgrAccumuPerformanceMapper;
import com.hzcf.flagship.dao.MoneymgrDailyPerformanceMapper;
import com.hzcf.flagship.dao.MoneymgrDistrictAccumuPerformanceMapper;
import com.hzcf.flagship.dao.MoneymgrMonthHistoryMapper;
import com.hzcf.flagship.dao.MoneymgrOrgAccumuPerformanceMapper;
import com.hzcf.flagship.dao.MoneymgrOrgDataMapper;
import com.hzcf.flagship.dao.PropertiesMapper;
import com.hzcf.flagship.dao.RiskLoanCm1Mapper;
import com.hzcf.flagship.dao.RiskLoanM1OverdueMapper;
import com.hzcf.flagship.dao.RiskOrgStructMapper;
import com.hzcf.flagship.dao.RiskOverdueMapper;
import com.hzcf.flagship.dao.RiskPlanMapper;
import com.hzcf.flagship.dao.RiskProductMapper;
import com.hzcf.flagship.dao.RiskSubcenterMapper;
import com.hzcf.flagship.dao.RiskWarningLevelMapper;
import com.hzcf.flagship.enmu.UserConstants;
import com.hzcf.flagship.model.AppUser;
import com.hzcf.flagship.model.CheckLog;
import com.hzcf.flagship.model.FinanceDailyDistrict;
import com.hzcf.flagship.model.FinanceDailyDistrictExample;
import com.hzcf.flagship.model.FinanceDailyOrg;
import com.hzcf.flagship.model.FinanceDailyOrgExample;
import com.hzcf.flagship.model.FinanceDailyPerformance;
import com.hzcf.flagship.model.FinanceDailyPerformanceExample;
import com.hzcf.flagship.model.FinanceDailyPerformanceExample.Criteria;
import com.hzcf.flagship.model.FinanceMonthDistrict;
import com.hzcf.flagship.model.FinanceMonthDistrictExample;
import com.hzcf.flagship.model.FinanceMonthOrg;
import com.hzcf.flagship.model.FinanceMonthOrgExample;
import com.hzcf.flagship.model.FinanceMonthPerformance;
import com.hzcf.flagship.model.FinanceMonthPerformanceExample;
import com.hzcf.flagship.model.FinancePersonalInfo;
import com.hzcf.flagship.model.FinancePersonalInfoExample;
import com.hzcf.flagship.model.FinanceRisk;
import com.hzcf.flagship.model.FinanceRiskExample;
import com.hzcf.flagship.model.MoneymgrAccumuPerformance;
import com.hzcf.flagship.model.MoneymgrAccumuPerformanceExample;
import com.hzcf.flagship.model.MoneymgrDailyPerformance;
import com.hzcf.flagship.model.MoneymgrDailyPerformanceExample;
import com.hzcf.flagship.model.MoneymgrDistrictAccumuPerformance;
import com.hzcf.flagship.model.MoneymgrDistrictAccumuPerformanceExample;
import com.hzcf.flagship.model.MoneymgrMonthHistory;
import com.hzcf.flagship.model.MoneymgrMonthHistoryExample;
import com.hzcf.flagship.model.MoneymgrOrgAccumuPerformance;
import com.hzcf.flagship.model.MoneymgrOrgAccumuPerformanceExample;
import com.hzcf.flagship.model.MoneymgrOrgData;
import com.hzcf.flagship.model.MoneymgrOrgDataExample;
import com.hzcf.flagship.model.Properties;
import com.hzcf.flagship.model.RiskSubcenter;
import com.hzcf.flagship.service.AppPushMessageService;
import com.hzcf.flagship.service.CheckJobService;
import com.hzcf.flagship.util.DateTimeUtil;
import com.hzcf.flagship.util.DateUtil;
import com.hzcf.flagship.util.EmailManager;
import com.hzcf.flagship.util.ExportExcel;
import com.hzcf.flagship.util.HttpUtil;
import com.hzcf.flagship.util.JedisClientUtil;
import com.hzcf.flagship.util.NewSendMessageUtil;
import com.hzcf.flagship.util.PropertyUtil;
import com.hzcf.flagship.util.StringUtil;
import com.hzcf.flagship.util.push.PushUtil;
/**
 *<dl>
 *<dt>类名：CheckJobServiceImpl.java</dt>
 *<dd>描述: 检查数据任务逻辑实现</dd> 
 *<dd>创建时间： 2017年9月13日 上午9:52:43</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年9月13日 上午9:52:43    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Service
public class CheckJobServiceImpl implements CheckJobService {
	static final String BI_LOG_TYPE = "BI抽取";
	static final String UPLOAD_LOG_TYPE = "业务上传";
	static final String API_LOG_TYPE = "接口访问";
	static final String RESULT_NORMAL = "正常";
	static final String RESULT_ABNORMAL = "异常";
	
	static final String CHECKAPI_URL = PropertyUtil.getInfo("checkApiUrl");
	static final String SENDMESSAGEPATH = PropertyUtil.getInfo("sendmessage_path");
	static final String KEY = PropertyUtil.getInfo("key");
	static final String SYSTEMSOURCEID= PropertyUtil.getInfo("systemSourceId");
	/*static final String DAY = DateTimeUtil.getNowDateNormalString();
	static final Date NOW_DATE = DateTimeUtil.convertAsDateString(DAY);
	static final String FIRSTDAYOFMONTH = DateTimeUtil.getFirstDayOfMonth(DAY);*/
	
	static final String TMP_FILES=File.separator+"tmpfiles"; //日志临时文件目录
	static String FILEFULLPATH;
	private String day;
	private Date nowDayDate;
	private String firstDayOfMonth;
	private final Log logger = LogFactory.getLog(getClass());
    @Autowired
	JedisClientUtil jedisClientUtil;
	@Autowired
	private FinanceDailyPerformanceMapper financeDailyPerformanceMapper;
	@Autowired
	private CheckLogMapper checkLogMapper;
	@Autowired
	private FinanceMonthPerformanceMapper financeMonthPerformanceMapper;
	@Autowired
	private FinanceDailyDistrictMapper financeDailyDistrictMapper;
	@Autowired
	private FinanceDailyOrgMapper financeDailyOrgMapper;
	@Autowired
	private FinanceMonthDistrictMapper financeMonthDistrictMapper;
	@Autowired
	private FinanceMonthOrgMapper financeMonthOrgMapper;
	@Autowired
	private FinanceRiskMapper financeRiskMapper;
	@Autowired
	private FinancePersonalInfoMapper financePersonalInfoMapper;
	@Autowired
	private MoneymgrDailyPerformanceMapper moneymgrDailyPerformanceMapper;
	@Autowired
	private MoneymgrAccumuPerformanceMapper moneymgrAccumuPerformanceMapper;
	@Autowired
	private MoneymgrDistrictAccumuPerformanceMapper moneymgrDistrictAccumuPerformanceMapper;
	@Autowired
	private MoneymgrOrgAccumuPerformanceMapper moneymgrOrgAccumuPerformanceMapper;
	@Autowired
	private PropertiesMapper propertiesMapper;
	@Autowired
	private MoneymgrOrgDataMapper moneymgrOrgDataMapper;
	@Autowired
	private MoneymgrMonthHistoryMapper moneymgrMonthHistoryMapper;
	@Autowired
	private AppPushMessageService appPushMessageService;
	@Autowired
	private RiskOrgStructMapper riskOrgStructMapper;
	@Autowired
	private RiskProductMapper riskProductMapper;
	@Autowired
	private RiskLoanCm1Mapper riskLoanCm1Mapper;
	@Autowired
	private RiskLoanM1OverdueMapper riskLoanM1OverdueMapper;
	@Autowired
	private RiskPlanMapper riskPlanMapper;
	@Autowired
	private AssetDailyPerformanceMapper assetDailyPerformanceMapper;
	@Autowired
	private AppUserMapper appUserMapper;
	@Autowired
	private RiskSubcenterMapper riskSubcenterMapper;
	@Autowired
	private RiskOverdueMapper riskOverdueMapper;
	@Autowired
	private RiskWarningLevelMapper riskWarningLevelMapper;
	
	private int sort;
	private String token;
	private String androidAppkey = PropertyUtil.getInfo("androidAppkey");
	private String androidAppMasterSecret = PropertyUtil.getInfo("androidAppMasterSecret");
	private String iosAppkey = PropertyUtil.getInfo("iosAppkey");
	private String iosAppMasterSecret = PropertyUtil.getInfo("iosAppMasterSecret");
	private String production_mode = PropertyUtil.getInfo("production_mode");
	
	public void checkData(){
		sort = getSort();
		day = DateTimeUtil.getNowDateNormalString();
		nowDayDate = DateTimeUtil.convertAsDateString(day);
		firstDayOfMonth = DateTimeUtil.getFirstDayOfMonth(day);
		checkBIData();
		checkUploadDate();
		token = checkLonin();
		if(StringUtil.blank(token)){
			return;
		}
		checkApi();
		
	}
	
	@Override
	public void checkBIData() {
		checkFinanceDailyPerformanceTable();
		checkFinancedailyDistrictTable();
		checkFinancedailyOrgTable();
		checkFinanceMonthPerformanceTable();
		checkFinanceMonthDistrictTable();
		checkFinanceMonthOrgTable();
		checkMoneymgrDailyPerformanceTable();
		checkMoneymgrAccumuPerformanceTable();
		checkMoneymgrDistrictAccumuPerformanceTable();
		checkMoneymgrOrgAccumuPerformanceTable();
		checkRiskOrgStructTable();
		checkRiskProductTable();
		checkRiskLoanCm1Table();
		checkRiskLoanM1OverdueTable();
		checkAssetDailyPerformanceTable();
		checkRiskOverdueTable();
		
	}

	@Override
	public void checkUploadDate() {
		checkFinanceRiskTable();
		checkFinancePersonInfoTable();
		checkMoneymgrOrgDataTable();
		checkMoneymgrMonthHistoryTable();
		checkRiskPlanTable();
		
	}

	@Override
	public void checkApi() {
		checkGetIndexPageInfo();
		checkPerformanceForDays();
		checkPerformanceForMonths();
		checkPerformanceOrgMapForDays();
		checkFinanceForMonthData();
		checkMoneymgrEfficiencyForDays();
		checkMoneymgrEfficiencyForMonths();
		checkFinanceDailyPerformance();
		checkFinanceMonthPerformance();
		checkFinanceRisk();
		checkFinanceDailyPeople();
		checkFinanceMonthPeople();
		checkFinanceDailyIntoQuality();
		checkFinanceMonthIntoQuality();
		checkAllDistrict();
		checkAllOrgNameByDistrictOfDay();
		checkAllOrgNameByDistrictOfMonth();
		checkFinanceDistrictOfDayList();
		checkFinanceOrgNameOfDayList();
		checkFinanceDistrictOfMonthList();
		checkFinanceOrgNameOfMonthList();
		checkFinancePersonnelSum();
		checkRiskZWFirstPage();//风控执委首页
		checkRiskBusinessFirstPage();//风控事业部首页数据
		checkRiskAreaPage();//事业部下钻	
		checkRiskBusinePage();//大区,分中心下钻
		checkRiskAreaFirstPage();//大区首页
		checkRiskRegionToOrgPage();//营业部下钻接口
		checkRiskAfterLoanAll();//贷后整体下钻接口
		checkRiskAfterLoanFirstPage();//风控贷后首页  	
		checkRiskSCFirstPage();//市场管理部首页
		checkRiskBranchCenterFirstPage();//分中心首页
		checkOrgOverdueDetail();//营业部逾期合同明细
		checkWarningOrgs();//事业部预警营业部明细
	}
	
	/**
	 * 获取token
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String  checkLonin(){
		String token = jedisClientUtil.get(UserConstants.CHECK_USER_TOKEN);
		String telephone = PropertyUtil.getInfo("checkUsername");
		try {
			if(StringUtil.isBlank(token)){	//未登录
				String passWord = PropertyUtil.getInfo("checkPassword");
				String loginUrl = CHECKAPI_URL + "user/login";
				Map<String, String> params = new HashMap<String, String>();
				params.put("telephone", telephone);
				params.put("password", passWord);
				String doPost = HttpUtil.doPost(loginUrl, JSON.toJSONString(params));
				Map<String, Object> map = (Map<String, Object>) JSON.parse(doPost);
				Map<String, Object> map2 =  (Map<String, Object>) map.get("data");
				token =  (String) map2.get("token");
				if (StringUtil.notBlank(token)) {
					jedisClientUtil.set(UserConstants.CHECK_USER_TOKEN, token);
					jedisClientUtil.expire(UserConstants.CHECK_USER_TOKEN, UserConstants.REDIS_CHECK_TOKEN_DELAY);
				}
			}else{
				List<AppUser> appUsers = appUserMapper.selectAppUserByTelephone(telephone);
				if(null != appUsers && appUsers.size()>0){
					AppUser appUser = appUsers.get(0);
					Integer id = appUser.getId();
					jedisClientUtil.delByKeyprefixAndValue(UserConstants.USER_TOKEN, id+"");
					jedisClientUtil.set(UserConstants.USER_TOKEN + token, id+"");
					jedisClientUtil.expire(UserConstants.USER_TOKEN + token, UserConstants.REDIS_CHECK_TOKEN_DELAY);
				}
			}
		} catch (Exception e) {
			logger.error("获取token接口出现异常", e);
		}
		return token;
	}
	
	/**
	 * 首页接口
	 * d
	 */
	@SuppressWarnings("unchecked")
	public void checkGetIndexPageInfo(){
		String sufUrl = "licai/getIndexPageInfo";
		try {
			Map<String, Object> map = reqResult(sufUrl);
			String code = (String) map.get("returnCode");
			if("0000".equals(code)){	//接口检测--正常
				StringBuffer strBufer = new StringBuffer();
				Map<String, Object> data = (Map<String, Object>) map.get("data");
				Map<String, Object> financeData =  (Map<String, Object>) data.get("financeData");
				Map<String, Object> moneymgrData =  (Map<String, Object>) data.get("moneymgrData");
				if(financeData.isEmpty()){
					strBufer.append(" 首页融资数据为空");
				}
				if(moneymgrData.isEmpty()){
					strBufer.append(" 首页理财数据为空");
				}
				checkStringBufferIsNull(sufUrl, strBufer);
			}else{	//接口检测--程序异常
				resultError(API_LOG_TYPE, sufUrl, "调用失败,状态码是： " + code);
			}
		} catch (Exception e) {
			logger.error("首页接口检查接口出现异常", e);
		}
	}
	
	
	/**
	 * 检查融资日业绩表数据
	 * 检查过去三个月每天的数据是否重复/是否有数据/数据非空
	 */
	public void checkFinanceDailyPerformanceTable(){
		try {
			//Date nowDate = new Date();
			//获得昨天的日期
			Calendar yesterdayCalendar = Calendar.getInstance();
			yesterdayCalendar.setTime(DateTimeUtil.getYesterdayDate());
			//获得三个月前的日期
			Calendar variableCalendar = Calendar.getInstance();
			variableCalendar.setTime(DateTimeUtil.getYesterdayDate());
			variableCalendar.add(Calendar.MONTH, -3);
			variableCalendar.add(Calendar.DAY_OF_YEAR, 1);
			String result = RESULT_NORMAL;
			StringBuffer stringBuffer = new StringBuffer();
			
			logger.info("开始检查f_finance_daily_performance表数据,检查开始日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
			//依次取当日数据并判断
			for (;variableCalendar.compareTo(yesterdayCalendar)<=0 ; variableCalendar.add(Calendar.DAY_OF_YEAR, 1)) {
				//查询当日融资日业绩表数据
				FinanceDailyPerformanceExample performanceExample = new FinanceDailyPerformanceExample();
				Criteria performanceCriteria = performanceExample.createCriteria();
				performanceCriteria.andRecordDateEqualTo(variableCalendar.getTime());
				List<FinanceDailyPerformance> list = financeDailyPerformanceMapper.selectByExample(performanceExample);
				
				if (list==null || list.size()==0) {//长度为0,当日无数据
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"无数据；");
				}else if (list!=null && list.size()>1) {//长度大于1,数据重复
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"数据重复；");
				}else if (list!=null && list.size()==1 ) {//数据非空校验
					FinanceDailyPerformance performance = list.get(0);
					if (performance.getLoanValue()==null 
							|| performance.getAccumuLoanValue()==null
							|| performance.getLoanQuantity()==null 
							|| performance.getApplyQuantity()==null
							|| performance.getAccumuApprovalRate()==null 
							|| performance.getAccumuApprovalAverage()==null){
						result = RESULT_ABNORMAL;
						stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"有数据为null；");
					}
				}
			}
			
			if (RESULT_NORMAL.equals(result)) {
				resultTrue(BI_LOG_TYPE, "f_finance_daily_performance");
			}else {
				resultError(BI_LOG_TYPE, "f_finance_daily_performance", stringBuffer.toString());
			}
			variableCalendar.add(Calendar.DAY_OF_YEAR, -1);
			logger.info("检查f_finance_daily_performance表数据结束,检查最后日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
		} catch (Exception e) {
			logger.error("检查f_finance_daily_performance表数据出现异常",e);
		}
		
	}
	
	
	/**
	 * 检查融资分区域日业绩表
	 * 检查过去三个月每天的数据是否重复/是否有数据/数据非空
	 */
	public void checkFinancedailyDistrictTable(){
		try {
			//Date nowDate = new Date();
			//获得昨天的日期
			Calendar yesterdayCalendar = Calendar.getInstance();
			yesterdayCalendar.setTime(DateTimeUtil.getYesterdayDate());
			//获得三个月前的日期
			Calendar variableCalendar = Calendar.getInstance();
			variableCalendar.setTime(DateTimeUtil.getYesterdayDate());
			variableCalendar.add(Calendar.MONTH, -3);
			variableCalendar.add(Calendar.DAY_OF_YEAR, 1);
			
			String result = RESULT_NORMAL;
			StringBuffer stringBuffer = new StringBuffer();
			
			logger.info("开始检查f_finance_daily_district表数据,检查开始日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
			//依次取当日数据并检查
			for (;variableCalendar.compareTo(yesterdayCalendar)<=0 ; variableCalendar.add(Calendar.DAY_OF_YEAR, 1)) {
				//查询当日所有数据
				FinanceDailyDistrictExample financeDailyDistrictExample = new FinanceDailyDistrictExample();
				com.hzcf.flagship.model.FinanceDailyDistrictExample.Criteria financeDailyDistrictCriteria = financeDailyDistrictExample.createCriteria();
				financeDailyDistrictCriteria.andRecordDateEqualTo(variableCalendar.getTime());
				List<FinanceDailyDistrict> list = financeDailyDistrictMapper.selectByExample(financeDailyDistrictExample);
				//查询当日记录数与区域数的差值(用于判断数据是否有重复)
				Map<String, Object> diffMap = financeDailyDistrictMapper.getDiffOfCountAndDistrictNumByDate(variableCalendar.getTime());
				if (list==null || list.size()==0 || diffMap==null) {//长度为0,当日无数据
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"无数据；");
					continue;
				}
				Long diff = (Long)diffMap.get("diff");
				if (diff > 0) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"数据重复；");
					continue;
				}
				for (FinanceDailyDistrict financeDailyDistrict : list) {
					if (StringUtil.blank(financeDailyDistrict.getDistrictName())
							|| financeDailyDistrict.getLoanValue()==null
							|| financeDailyDistrict.getAccumuLoanValue()==null
							|| financeDailyDistrict.getApplyQuantity()==null
							|| financeDailyDistrict.getAccumuApprovalRate()==null
							|| financeDailyDistrict.getAccumuApprovalAverage()==null) {
						result = RESULT_ABNORMAL;
						stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"有数据为null；");
						break;
					}
				}
				
			}
			
			if (RESULT_NORMAL.equals(result)) {
				resultTrue(BI_LOG_TYPE, "f_finance_daily_district");
			}else {
				resultError(BI_LOG_TYPE, "f_finance_daily_district", stringBuffer.toString());
			}
			variableCalendar.add(Calendar.DAY_OF_YEAR, -1);
			logger.info("检查f_finance_daily_district表数据结束,检查最后日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
		} catch (Exception e) {
			logger.error("检查f_finance_daily_district表数据出现异常",e);
		}
		
	}
	
	
	
	/**
	 * 检查融资分机构日业绩表
	 * 检查过去三个月每天的数据是否重复/是否有数据/数据非空
	 */
	public void checkFinancedailyOrgTable(){
		try {
			//Date nowDate = new Date();
			//获得昨天的日期
			Calendar yesterdayCalendar = Calendar.getInstance();
			yesterdayCalendar.setTime(DateTimeUtil.getYesterdayDate());
			//获得三个月前的日期
			Calendar variableCalendar = Calendar.getInstance();
			variableCalendar.setTime(DateTimeUtil.getYesterdayDate());
			variableCalendar.add(Calendar.MONTH, -3);
			variableCalendar.add(Calendar.DAY_OF_YEAR, 1);
			
			String result = RESULT_NORMAL;
			StringBuffer stringBuffer = new StringBuffer();
			
			logger.info("开始检查f_finance_daily_org表数据,检查开始日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
			//依次取当日数据并检查
			for (;variableCalendar.compareTo(yesterdayCalendar)<=0 ; variableCalendar.add(Calendar.DAY_OF_YEAR, 1)) {
				//查询当日所有数据
				FinanceDailyOrgExample financeDailyOrgExample = new FinanceDailyOrgExample();
				com.hzcf.flagship.model.FinanceDailyOrgExample.Criteria financeDailyOrgCriteria = financeDailyOrgExample.createCriteria();
				financeDailyOrgCriteria.andRecordDateEqualTo(variableCalendar.getTime());
				List<FinanceDailyOrg> list = financeDailyOrgMapper.selectByExample(financeDailyOrgExample);
				//查询当日记录数与区域数的差值(用于判断数据是否有重复)
				Map<String, Object> diffMap = financeDailyOrgMapper.getDiffOfCountAndOrgNumByDate(variableCalendar.getTime());
				if (list==null || list.size()==0 || diffMap==null) {//长度为0,当日无数据
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"无数据；");
					continue;
				}
				Long diff = (Long)diffMap.get("diff");
				if (diff > 0) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"数据重复；");
					continue;
				}
				for (FinanceDailyOrg financeDailyOrg : list) {
					if (StringUtil.blank(financeDailyOrg.getOrgName())
							|| financeDailyOrg.getLoanValue()==null
							|| financeDailyOrg.getAccumuLoanValue()==null
							|| financeDailyOrg.getApplyQuantity()==null
							|| financeDailyOrg.getAccumuApprovalRate()==null
							|| financeDailyOrg.getAccumuApprovalAverage()==null) {
						result = RESULT_ABNORMAL;
						stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"有数据为null；");
						break;
					}
				}
			}
			
			if (RESULT_NORMAL.equals(result)) {
				resultTrue(BI_LOG_TYPE, "f_finance_daily_org");
			}else {
				resultError(BI_LOG_TYPE, "f_finance_daily_org", stringBuffer.toString());
			}
			variableCalendar.add(Calendar.DAY_OF_YEAR, -1);
			logger.info("检查f_finance_daily_district表数据结束,检查最后日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
		} catch (Exception e) {
			logger.error("检查f_finance_daily_district表数据出现异常",e);
		}
	}
	
	
	/**
	 * 检查融资月业绩表数据
	 * 检查是否有数据,数据是否重复,非空校验
	 * 如果当前日期是27日及之后,检查当年1月至当前所在月数据;
	 * 如果当前日期是27日之前,检查当年1月至当前所在月前月数据
	 * 如果当前日期是1月27日前,检查去年1月至12月数据
	 */
	public void checkFinanceMonthPerformanceTable(){
		try {
			Date nowDate = new Date();
			//获得当前月
			Calendar currentMonth = Calendar.getInstance();
			currentMonth.setTime(nowDate);
			//currentMonth.set(Calendar.DAY_OF_MONTH, 1);
			//获得所在年的1月1日
			Calendar variableCalendar = Calendar.getInstance();
			variableCalendar.setTime(nowDate);
			variableCalendar.set(Calendar.DAY_OF_MONTH, 1);
			variableCalendar.set(Calendar.MONTH,Calendar.JANUARY);
			if (currentMonth.get(Calendar.DAY_OF_MONTH) >= 27) {//如果当前日期是27日及之后,检查当年1月至当前所在月数据;
				currentMonth.set(Calendar.DAY_OF_MONTH, 1);
			}else if (currentMonth.get(Calendar.MONTH)==Calendar.JANUARY && currentMonth.get(Calendar.DAY_OF_MONTH) < 27) {//如果当前日期是1月27日前,检查去年1月至12月数据
				currentMonth.set(Calendar.DAY_OF_MONTH, 1);
				currentMonth.add(Calendar.MONTH, -1);
				variableCalendar.add(Calendar.YEAR, -1);
			}else {//如果当前日期是27日之前,检查当年1月至当前所在月前月数据
				currentMonth.set(Calendar.DAY_OF_MONTH, 1);
				currentMonth.add(Calendar.MONTH, -1);
			}
			
			String result = RESULT_NORMAL;
			StringBuffer stringBuffer = new StringBuffer();
			
			logger.info("开始检查f_finance_month_performance表数据,检查开始日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
			//依次查出每月数据并检查
			for (; variableCalendar.compareTo(currentMonth)<=0; variableCalendar.add(Calendar.MONTH, 1)) {
				FinanceMonthPerformanceExample financeMonthPerformanceExample = new FinanceMonthPerformanceExample();
				com.hzcf.flagship.model.FinanceMonthPerformanceExample.Criteria financeMonthPerformanceCriteria = financeMonthPerformanceExample.createCriteria();
				financeMonthPerformanceCriteria.andRecordDateEqualTo(variableCalendar.getTime());
				List<FinanceMonthPerformance> list = financeMonthPerformanceMapper.selectByExample(financeMonthPerformanceExample);
				if (list==null || list.size()==0) {//长度为0,当日无数据
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"无数据；");
				}else if (list!=null && list.size()>1) {//长度大于1,数据重复
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"数据重复；");
				}else if (list!=null && list.size()==1 ) {//数据非空校验
					FinanceMonthPerformance financeMonthPerformance = list.get(0);
					if (financeMonthPerformance.getMonthPerformance()==null 
							|| financeMonthPerformance.getYearPerformance()==null
							|| financeMonthPerformance.getApplyActualQuantity()==null 
							|| financeMonthPerformance.getLoanActualQuantity()==null
							|| financeMonthPerformance.getApprovalRate()==null
							|| financeMonthPerformance.getApprovalAverage()==null
							|| financeMonthPerformance.getContractMoney()==null
							|| financeMonthPerformance.getServiceChargeRate()==null
							|| financeMonthPerformance.getArbitrationChargeRate()==null
							|| financeMonthPerformance.getInsuranceRate()==null) {
						result = RESULT_ABNORMAL;
						stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"有数据为null；");	
					}
				}
			}
			
			
			if (RESULT_NORMAL.equals(result)) {
				resultTrue(BI_LOG_TYPE, "f_finance_month_performance");
			}else {
				resultError(BI_LOG_TYPE, "f_finance_month_performance", stringBuffer.toString());
			}
			variableCalendar.add(Calendar.MONTH, -1);
			logger.info("检查f_finance_month_performance表数据结束,检查最后日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
		} catch (Exception e) {
			logger.error("检查f_finance_month_performance表数据出现异常",e);
		}
		
	}
	


	/**
	 * 检查融资分区域月业绩表数据
	 * 检查是否有数据,数据是否重复,非空校验
	 * 如果当前日期是27日及之后,检查当年1月至当前所在月数据;
	 * 如果当前日期是27日之前,检查当年1月至当前所在月前月数据
	 * 如果当前日期是1月27日前,检查去年1月至12月数据
	 */
	public void checkFinanceMonthDistrictTable(){
		try {
			Date nowDate = new Date();
			//获得当前月
			Calendar currentMonth = Calendar.getInstance();
			currentMonth.setTime(nowDate);
			//currentMonth.set(Calendar.DAY_OF_MONTH, 1);
			//获得所在年的1月1日
			Calendar variableCalendar = Calendar.getInstance();
			variableCalendar.setTime(nowDate);
			variableCalendar.set(Calendar.DAY_OF_MONTH, 1);
			variableCalendar.set(Calendar.MONTH,Calendar.JANUARY);
			if (currentMonth.get(Calendar.DAY_OF_MONTH) >= 27) {//如果当前日期是27日及之后,检查当年1月至当前所在月数据;
				currentMonth.set(Calendar.DAY_OF_MONTH, 1);
			}else if (currentMonth.get(Calendar.MONTH)==Calendar.JANUARY && currentMonth.get(Calendar.DAY_OF_MONTH) < 27) {//如果当前日期是1月27日前,检查去年1月至12月数据
				currentMonth.set(Calendar.DAY_OF_MONTH, 1);
				currentMonth.add(Calendar.MONTH, -1);
				variableCalendar.add(Calendar.YEAR, -1);
			}else {//如果当前日期是27日之前,检查当年1月至当前所在月前月数据
				currentMonth.set(Calendar.DAY_OF_MONTH, 1);
				currentMonth.add(Calendar.MONTH, -1);
			}
			
			String result = RESULT_NORMAL;
			StringBuffer stringBuffer = new StringBuffer();
			
			logger.info("开始检查f_finance_month_district表数据,检查开始日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
			//依次查出每月数据并检查
			for (; variableCalendar.compareTo(currentMonth)<=0; variableCalendar.add(Calendar.MONTH, 1)) {
				//查询当月所有数据
				FinanceMonthDistrictExample financeMonthDistrictExample = new FinanceMonthDistrictExample();
				com.hzcf.flagship.model.FinanceMonthDistrictExample.Criteria financeMonthDistrictCriteria = financeMonthDistrictExample.createCriteria();
				financeMonthDistrictCriteria.andRecordDateEqualTo(variableCalendar.getTime());
				List<FinanceMonthDistrict> list = financeMonthDistrictMapper.selectByExample(financeMonthDistrictExample);
				//查询记录数与区域数的差值(用于判断数据是否重复)
				Map<String, Object> diffMap = financeMonthDistrictMapper.getDiffOfCountAndDistrictNumByDate(variableCalendar.getTime());
				
				if (list==null || list.size()==0 || diffMap==null) {//长度为0,当日无数据
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"无数据；");
					continue;
				}
				Long diff = (Long)diffMap.get("diff");
				if (diff > 0) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"数据重复；");
					continue;
				}
				for (FinanceMonthDistrict financeMonthDistrict : list) {
					if (StringUtil.blank(financeMonthDistrict.getDistrictName())
							|| financeMonthDistrict.getLoanValue()==null
							|| financeMonthDistrict.getApplyQuantity()==null
							|| financeMonthDistrict.getApprovalRate()==null
							|| financeMonthDistrict.getApprovalAverage()==null) {
						result = RESULT_ABNORMAL;
						stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"有数据为null；");
						break;
					}
				}
			}
			
			if (RESULT_NORMAL.equals(result)) {
				resultTrue(BI_LOG_TYPE, "f_finance_month_district");
			}else {
				resultError(BI_LOG_TYPE, "f_finance_month_district", stringBuffer.toString());
			}
			variableCalendar.add(Calendar.MONTH, -1);
			logger.info("检查f_finance_month_district表数据结束,检查最后日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
		} catch (Exception e) {
			logger.error("检查f_finance_month_district表数据出现异常",e);
		}
		
	}
	
	
	/**
	 * 检查融资分机构月业绩表数据
	 * 检查是否有数据,数据是否重复,非空校验
	 * 如果当前日期是27日及之后,检查当年1月至当前所在月数据;
	 * 如果当前日期是27日之前,检查当年1月至当前所在月前月数据
	 * 如果当前日期是1月27日前,检查去年1月至12月数据
	 */
	public void checkFinanceMonthOrgTable(){
		try {
			Date nowDate = new Date();
			//获得当前月
			Calendar currentMonth = Calendar.getInstance();
			currentMonth.setTime(nowDate);
			//currentMonth.set(Calendar.DAY_OF_MONTH, 1);
			//获得所在年的1月1日
			Calendar variableCalendar = Calendar.getInstance();
			variableCalendar.setTime(nowDate);
			variableCalendar.set(Calendar.DAY_OF_MONTH, 1);
			variableCalendar.set(Calendar.MONTH,Calendar.JANUARY);
			if (currentMonth.get(Calendar.DAY_OF_MONTH) >= 27) {//如果当前日期是27日及之后,检查当年1月至当前所在月数据;
				currentMonth.set(Calendar.DAY_OF_MONTH, 1);
			}else if (currentMonth.get(Calendar.MONTH)==Calendar.JANUARY && currentMonth.get(Calendar.DAY_OF_MONTH) < 27) {//如果当前日期是1月27日前,检查去年1月至12月数据
				currentMonth.set(Calendar.DAY_OF_MONTH, 1);
				currentMonth.add(Calendar.MONTH, -1);
				variableCalendar.add(Calendar.YEAR, -1);
			}else {//如果当前日期是27日之前,检查当年1月至当前所在月前月数据
				currentMonth.set(Calendar.DAY_OF_MONTH, 1);
				currentMonth.add(Calendar.MONTH, -1);
			}
			
			String result = RESULT_NORMAL;
			StringBuffer stringBuffer = new StringBuffer();
			
			logger.info("开始检查f_finance_month_org表数据,检查开始日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
			//依次查出每月数据并检查
			for (; variableCalendar.compareTo(currentMonth)<=0; variableCalendar.add(Calendar.MONTH, 1)) {
				//查询当月所有数据
				FinanceMonthOrgExample financeMonthOrgExample = new FinanceMonthOrgExample();
				com.hzcf.flagship.model.FinanceMonthOrgExample.Criteria financeMonthOrgCriteria = financeMonthOrgExample.createCriteria();
				financeMonthOrgCriteria.andRecordDateEqualTo(variableCalendar.getTime());
				List<FinanceMonthOrg> list = financeMonthOrgMapper.selectByExample(financeMonthOrgExample);
				//查询记录数与区域数的差值(用于判断数据是否重复)
				Map<String, Object> diffMap = financeMonthOrgMapper.getDiffOfCountAndOrgNumByDate(variableCalendar.getTime());
				
				if (list==null || list.size()==0 || diffMap==null) {//长度为0,当月无数据
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"无数据；");
					continue;
				}
				Long diff = (Long)diffMap.get("diff");
				if (diff > 0) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"数据重复；");
					continue;
				}
				for (FinanceMonthOrg financeMonthOrg : list) {
					if (StringUtil.blank(financeMonthOrg.getOrgName())
							|| financeMonthOrg.getLoanValue()==null
							|| financeMonthOrg.getApplyQuantity()==null
							|| financeMonthOrg.getApprovalRate()==null
							|| financeMonthOrg.getApprovalAverage()==null) {
						result = RESULT_ABNORMAL;
						stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"有数据为null；");
						break;
					}
				}
			}
			
			if (RESULT_NORMAL.equals(result)) {
				resultTrue(BI_LOG_TYPE, "f_finance_month_org");
			}else {
				resultError(BI_LOG_TYPE, "f_finance_month_org", stringBuffer.toString());
			}
			variableCalendar.add(Calendar.MONTH, -1);
			logger.info("检查f_finance_month_org表数据结束,检查最后日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
		} catch (Exception e) {
			logger.error("检查f_finance_month_org表数据出现异常",e);
		}
	}
	
	/**
	 * 检查融资风险表
	 * 检查上月所在年的1月至上月每月的数据是否存在/重复/为空
	 */
	public void checkFinanceRiskTable(){
		try {
			Date nowDate = new Date();
			Calendar lastMonth = Calendar.getInstance();
			lastMonth.setTime(nowDate);
			lastMonth.add(Calendar.MONTH, -1);
			lastMonth.set(Calendar.DAY_OF_MONTH, 1);//上月1日
			Calendar variableCalendar = Calendar.getInstance();
			variableCalendar.setTime(lastMonth.getTime());
			variableCalendar.set(Calendar.MONTH, Calendar.JANUARY);//上月所在年的1月1日
			
			String result = RESULT_NORMAL;
			StringBuffer stringBuffer = new StringBuffer();
			
			logger.info("开始检查f_finance_risk表数据,检查开始日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
			//依次查出每月数据并检查
			for (; variableCalendar.compareTo(lastMonth)<=0; variableCalendar.add(Calendar.MONTH, 1)) {
				FinanceRiskExample financeRiskExample = new FinanceRiskExample();
				com.hzcf.flagship.model.FinanceRiskExample.Criteria financeRiskCriteria = financeRiskExample.createCriteria();
				financeRiskCriteria.andManageOfficeEqualTo("整体");
				financeRiskCriteria.andRecordDateEqualTo(variableCalendar.getTime());
				List<FinanceRisk> list = financeRiskMapper.selectByExample(financeRiskExample);
				if (list==null || list.size()==0) {//长度为0,当月无数据
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"无数据；");
					continue;
				}
				if (list.size() > 1) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"数据重复；");
					continue;
				}
				FinanceRisk financeRisk = list.get(0);
				if (financeRisk.getcM1Value()==null || financeRisk.getLossRate()==null) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"有数据为null；");
					continue;
				}
				if (new BigDecimal(0).compareTo(financeRisk.getcM1Value())==0 || new BigDecimal(0).compareTo(financeRisk.getLossRate())==0) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"有数据为0；");
				}
			}
			
			if (RESULT_NORMAL.equals(result)) {
				resultTrue(UPLOAD_LOG_TYPE, "f_finance_risk");
			}else {
				resultError(UPLOAD_LOG_TYPE, "f_finance_risk", stringBuffer.toString());
			}
			variableCalendar.add(Calendar.MONTH, -1);
			logger.info("检查f_finance_risk表数据结束,检查最后日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
			
		} catch (Exception e) {
			logger.error("检查f_finance_risk表数据出现异常",e);
		}
		
	}
	
	
	/**
	 * 检查融资人员信息表
	 * 检查每月数据是否存在/重复/非空/非0
	 */
	public void checkFinancePersonInfoTable(){
		try {
			Date nowDate = new Date();
			Calendar lastMonth = Calendar.getInstance();
			lastMonth.setTime(nowDate);
			/*lastMonth.add(Calendar.MONTH, -1);*/
			lastMonth.set(Calendar.DAY_OF_MONTH, 1);//上月1日
			Calendar variableCalendar = Calendar.getInstance();
			variableCalendar.setTime(lastMonth.getTime());
			variableCalendar.set(Calendar.MONTH, Calendar.JANUARY);//上月所在年的1月1日
			
			String result = RESULT_NORMAL;
			StringBuffer stringBuffer = new StringBuffer();
			
			logger.info("开始检查f_finance_personal_info表数据,检查开始日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
			//依次查出每月数据并检查
			for (; variableCalendar.compareTo(lastMonth)<=0; variableCalendar.add(Calendar.MONTH, 1)) {
				int yearPlanNum=0;
				int entiretyNum=0;
				FinancePersonalInfoExample financePersonalInfoExample = new FinancePersonalInfoExample();
				com.hzcf.flagship.model.FinancePersonalInfoExample.Criteria financePersonalInfoCriteria = financePersonalInfoExample.createCriteria();
				financePersonalInfoCriteria.andRecordDateEqualTo(variableCalendar.getTime());
				List<FinancePersonalInfo> list = financePersonalInfoMapper.selectByExample(financePersonalInfoExample);
				
				Map<String, Object> diffMap = financePersonalInfoMapper.getDiffOfCountAndManageOfficeOrgNumByDate(variableCalendar.getTime());
				if (list==null || list.size()==0 || diffMap==null) {//长度为0,当月无数据
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"无数据；");
					continue;
				}
				
				Long countDiff = (Long)diffMap.get("countDiff");//记录数与管办/机构数的差值
				Long manageOfficeDiff = (Long)diffMap.get("manageOfficeDiff");//管办数与合计数据的差值
				if (countDiff>0) {//差值大于0,说明数据重复
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"有数据重复；");
					continue;
				}
				if (manageOfficeDiff>0) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"有大区无合计数据；");
					continue;
				}
				
				
				for (FinancePersonalInfo financePersonalInfo : list) {
					String manageOffice = financePersonalInfo.getManageOffice();
					if (StringUtil.blank(manageOffice)) {//管办为空
						result = RESULT_ABNORMAL;
						stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"管办数据为null；");
						break;
					}else if (StringUtil.notBlank(manageOffice) && "年计划".equals(manageOffice)) {
						yearPlanNum++;
						if (financePersonalInfo.getMonthPlan()==null || financePersonalInfo.getMonthPlan()==0) {
							result = RESULT_ABNORMAL;
							stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"年计划数据为null或为0；");
						}
					}else if (manageOffice!=null && "整体".equals(manageOffice)) {
						entiretyNum++;
					}else {
						if (StringUtil.blank(financePersonalInfo.getOrgName())
								|| financePersonalInfo.getSalesNum()==null
								|| financePersonalInfo.getTotalEmpNum()==null
								|| financePersonalInfo.getTeamNum()==null
								|| financePersonalInfo.getMonthPlan()==null) {
							result = RESULT_ABNORMAL;
							stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"有数据为null；");
						}
					}
					
				}
				
				if (yearPlanNum==0) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"无年计划数据；");
				}else if (yearPlanNum >1) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"年计划数据重复；");
				}
				if (entiretyNum==0) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"无月整体数据；");
				}else if(entiretyNum>1) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"月整体数据重复；");
				}
			}
			
			if (RESULT_NORMAL.equals(result)) {
				resultTrue(UPLOAD_LOG_TYPE, "f_finance_personal_info");
			}else {
				resultError(UPLOAD_LOG_TYPE, "f_finance_personal_info", stringBuffer.toString());
			}
			variableCalendar.add(Calendar.MONTH, -1);
			logger.info("检查f_finance_personal_info表数据结束,检查最后日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
			
		} catch (Exception e) {
			logger.error("检查f_finance_personal_info表数据出现异常",e);
		}
	}
	
	
	/**
	 * 检查理财日业绩表
	 * 检查昨天所在月的1日至昨日每天的数据是否存在/重复/为空
	 */
	public void checkMoneymgrDailyPerformanceTable(){
		try{
			//Date nowDate = new Date();
			//获得昨天的日期
			Calendar yesterdayCalendar = Calendar.getInstance();
			yesterdayCalendar.setTime(DateTimeUtil.getYesterdayDate());
			//昨日所在月的1日
			Calendar variableCalendar = Calendar.getInstance();
			variableCalendar.setTime(DateTimeUtil.getYesterdayDate());
			variableCalendar.set(Calendar.DAY_OF_MONTH, 1);
			String result = RESULT_NORMAL;
			StringBuffer stringBuffer = new StringBuffer();
			
			logger.info("开始检查f_moneymgr_daily_performance表数据,检查开始日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
			//依次取当日数据并判断
			for (;variableCalendar.compareTo(yesterdayCalendar)<=0 ; variableCalendar.add(Calendar.DAY_OF_YEAR, 1)) {
				//查询当日理财日业绩表数据
				MoneymgrDailyPerformanceExample moneymgrDailyPerformanceExample = new MoneymgrDailyPerformanceExample();
				com.hzcf.flagship.model.MoneymgrDailyPerformanceExample.Criteria moneymgrDailyPerformanceCriteria = moneymgrDailyPerformanceExample.createCriteria();
				moneymgrDailyPerformanceCriteria.andRecordDateEqualTo(variableCalendar.getTime());
				List<MoneymgrDailyPerformance> list = moneymgrDailyPerformanceMapper.selectByExample(moneymgrDailyPerformanceExample);
				
				if (list==null || list.size()==0) {//长度为0,当日无数据
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"无数据；");
				}else if (list!=null && list.size()>1) {//长度大于1,数据重复
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"数据重复；");
				}else if (list!=null && list.size()==1 ) {//数据非空校验
					MoneymgrDailyPerformance moneymgrDailyPerformance = list.get(0);
					if (moneymgrDailyPerformance.getPerformanceValue()==null){
						result = RESULT_ABNORMAL;
						stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"有数据为null；");
					}
				}
			}
			
			if (RESULT_NORMAL.equals(result)) {
				resultTrue(BI_LOG_TYPE, "f_moneymgr_daily_performance");
			}else {
				resultError(BI_LOG_TYPE, "f_moneymgr_daily_performance", stringBuffer.toString());
			}
			variableCalendar.add(Calendar.DAY_OF_YEAR, -1);
			logger.info("检查f_moneymgr_daily_performance表数据结束,检查最后日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
		} catch (Exception e) {
			logger.error("检查f_moneymgr_daily_performance表数据出现异常",e);
		}
	}
	
	
	/**
	 * 检查理财日累计业绩
	 * 检查昨天所在月的1日至昨日每天的数据是否存在/重复/为空
	 */
	public void checkMoneymgrAccumuPerformanceTable(){
		try{
			//Date nowDate = new Date();
			//获得昨天的日期
			Calendar yesterdayCalendar = Calendar.getInstance();
			yesterdayCalendar.setTime(DateTimeUtil.getYesterdayDate());
			//昨日所在月的1日
			Calendar variableCalendar = Calendar.getInstance();
			variableCalendar.setTime(DateTimeUtil.getYesterdayDate());
			variableCalendar.set(Calendar.DAY_OF_MONTH, 1);
			String result = RESULT_NORMAL;
			StringBuffer stringBuffer = new StringBuffer();
			
			logger.info("开始检查f_moneymgr_accumu_performance表数据,检查开始日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
			//依次取当日数据并判断
			for (;variableCalendar.compareTo(yesterdayCalendar)<=0 ; variableCalendar.add(Calendar.DAY_OF_YEAR, 1)) {
				//查询当日理财日累积业绩表数据
				MoneymgrAccumuPerformanceExample moneymgrAccumuPerformanceExample = new MoneymgrAccumuPerformanceExample();
				com.hzcf.flagship.model.MoneymgrAccumuPerformanceExample.Criteria moneymgrAccumuPerformanceCriteria = moneymgrAccumuPerformanceExample.createCriteria();
				moneymgrAccumuPerformanceCriteria.andRecordDateEqualTo(variableCalendar.getTime());
				List<MoneymgrAccumuPerformance> list = moneymgrAccumuPerformanceMapper.selectByExample(moneymgrAccumuPerformanceExample);
				
				if (list==null || list.size()==0) {//长度为0,当日无数据
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"无数据；");
				}else if (list!=null && list.size()>1) {//长度大于1,数据重复
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"数据重复；");
				}else if (list!=null && list.size()==1 ) {//数据非空校验
					MoneymgrAccumuPerformance moneymgrAccumuPerformance = list.get(0);
					if (moneymgrAccumuPerformance.getPerformanceValue()==null
							|| moneymgrAccumuPerformance.getNewClientNum()==null){
						result = RESULT_ABNORMAL;
						stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"有数据为null；");
					}
				}
			}
			
			if (RESULT_NORMAL.equals(result)) {
				resultTrue(BI_LOG_TYPE, "f_moneymgr_accumu_performance");
			}else {
				resultError(BI_LOG_TYPE, "f_moneymgr_accumu_performance", stringBuffer.toString());
			}
			variableCalendar.add(Calendar.DAY_OF_YEAR, -1);
			logger.info("检查f_moneymgr_accumu_performance表数据结束,检查最后日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
		} catch (Exception e) {
			logger.error("检查f_moneymgr_accumu_performance表数据出现异常",e);
		}
	}
	
	
	/**
	 * 检查理财区域日业绩表
	 * 检查昨天所在月的1日至昨日每天的数据是否存在/重复/为空
	 */
	public void checkMoneymgrDistrictAccumuPerformanceTable(){
		try {
			//Date nowDate = new Date();
			//获得昨天的日期
			Calendar yesterdayCalendar = Calendar.getInstance();
			yesterdayCalendar.setTime(DateTimeUtil.getYesterdayDate());
			//昨日所在月的1日
			Calendar variableCalendar = Calendar.getInstance();
			variableCalendar.setTime(DateTimeUtil.getYesterdayDate());
			variableCalendar.set(Calendar.DAY_OF_MONTH, 1);
			String result = RESULT_NORMAL;
			StringBuffer stringBuffer = new StringBuffer();
			
			logger.info("开始检查f_moneymgr_district_accumu_performance表数据,检查开始日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
			//依次取当日数据并判断
			for (;variableCalendar.compareTo(yesterdayCalendar)<=0 ; variableCalendar.add(Calendar.DAY_OF_YEAR, 1)) {
				//查询当日所有数据
				MoneymgrDistrictAccumuPerformanceExample moneymgrDistrictAccumuPerformanceExample = new MoneymgrDistrictAccumuPerformanceExample();
				com.hzcf.flagship.model.MoneymgrDistrictAccumuPerformanceExample.Criteria moneymgrDistrictAccumuPerformanceCriteria = moneymgrDistrictAccumuPerformanceExample.createCriteria();
				moneymgrDistrictAccumuPerformanceCriteria.andRecordDateEqualTo(variableCalendar.getTime());
				List<MoneymgrDistrictAccumuPerformance> list = moneymgrDistrictAccumuPerformanceMapper.selectByExample(moneymgrDistrictAccumuPerformanceExample);
				
				//查询当日记录数与区域数的差值(用于判断数据是否有重复)
				Map<String, Object> diffMap = moneymgrDistrictAccumuPerformanceMapper.getDiffOfCountAndDistrictNumByDate(variableCalendar.getTime());
				if (list==null || list.size()==0 || diffMap==null) {//长度为0,当日无数据
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"无数据；");
					continue;
				}
				Long diff = (Long)diffMap.get("diff");
				if (diff > 0) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"数据重复；");
					continue;
				}
				for (MoneymgrDistrictAccumuPerformance performance : list) {
					if (StringUtil.blank(performance.getDistrictName())
							|| performance.getPerformanceValue()==null
							|| performance.getNewClientNum()==null) {
						result = RESULT_ABNORMAL;
						stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"有数据为null；");
						break;
					}
				}
			}
			
			if (RESULT_NORMAL.equals(result)) {
				resultTrue(BI_LOG_TYPE, "f_moneymgr_district_accumu_performance");
			}else {
				resultError(BI_LOG_TYPE, "f_moneymgr_district_accumu_performance", stringBuffer.toString());
			}
			variableCalendar.add(Calendar.DAY_OF_YEAR, -1);
			logger.info("检查f_moneymgr_district_accumu_performance表数据结束,检查最后日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
		} catch (Exception e) {
			logger.error("检查f_moneymgr_district_accumu_performance表数据出现异常",e);
		}
	}
	
	
	/**
	 * 检查理财机构日业绩表
	 * 检查昨天所在月的1日至昨日每天的数据是否存在/重复/为空
	 */
	public void checkMoneymgrOrgAccumuPerformanceTable(){
		try {
			//Date nowDate = new Date();
			//获得昨天的日期
			Calendar yesterdayCalendar = Calendar.getInstance();
			yesterdayCalendar.setTime(DateTimeUtil.getYesterdayDate());
			//昨日所在月的1日
			Calendar variableCalendar = Calendar.getInstance();
			variableCalendar.setTime(DateTimeUtil.getYesterdayDate());
			variableCalendar.set(Calendar.DAY_OF_MONTH, 1);
			String result = RESULT_NORMAL;
			StringBuffer stringBuffer = new StringBuffer();
			
			logger.info("开始检查f_moneymgr_org_accumu_performance表数据,检查开始日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
			//依次取当日数据并判断
			for (;variableCalendar.compareTo(yesterdayCalendar)<=0 ; variableCalendar.add(Calendar.DAY_OF_YEAR, 1)) {
				//查询当日所有数据
				MoneymgrOrgAccumuPerformanceExample moneymgrOrgAccumuPerformanceExample = new MoneymgrOrgAccumuPerformanceExample();
				com.hzcf.flagship.model.MoneymgrOrgAccumuPerformanceExample.Criteria moneymgrOrgAccumuPerformanceCriteria = moneymgrOrgAccumuPerformanceExample.createCriteria();
				moneymgrOrgAccumuPerformanceCriteria.andRecordDateEqualTo(variableCalendar.getTime());
				List<MoneymgrOrgAccumuPerformance> list = moneymgrOrgAccumuPerformanceMapper.selectByExample(moneymgrOrgAccumuPerformanceExample);
				//查询当日记录数与区域数的差值(用于判断数据是否有重复)
//				Map<String, Object> diffMap = financeDailyOrgMapper.getDiffOfCountAndOrgNumByDate(variableCalendar.getTime());
				Map<String, Object> diffMap = moneymgrOrgAccumuPerformanceMapper.getDiffOfCountAndOrgNumByDate(variableCalendar.getTime());
				
				//System.out.println(variableCalendar.getTime());
				if (list==null || list.size()==0 || diffMap==null) {//长度为0,当日无数据
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"无数据；");
					continue;
				}
				Long diff = (Long)diffMap.get("diff");
				if (diff > 0) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"数据重复；");
					continue;
				}
				for (MoneymgrOrgAccumuPerformance moneymgrOrgAccumuPerformance : list) {
					if (StringUtil.blank(moneymgrOrgAccumuPerformance.getOrgName())
							|| moneymgrOrgAccumuPerformance.getPerformanceValue()==null
							|| moneymgrOrgAccumuPerformance.getPerformanceRanking()==null
							|| moneymgrOrgAccumuPerformance.getNewClientNum()==null) {
						result = RESULT_ABNORMAL;
						stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"有数据为null；");
						break;
					}
				}
			}
			
			if (RESULT_NORMAL.equals(result)) {
				resultTrue(BI_LOG_TYPE, "f_moneymgr_org_accumu_performance");
			}else {
				resultError(BI_LOG_TYPE, "f_moneymgr_org_accumu_performance", stringBuffer.toString());
			}
			variableCalendar.add(Calendar.DAY_OF_YEAR, -1);
			logger.info("检查f_moneymgr_org_accumu_performance表数据结束,检查最后日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
		} catch (Exception e) {
			logger.error("检查f_moneymgr_org_accumu_performance表数据出现异常",e);
		}
	}
	
	
	/**
	 * 检查理财对比表
	 * 检查昨天所在所在月的数据是否存在/重复/为空
	 * @throws ParseException 
	 */
	public void checkMoneymgrOrgDataTable() {
		try {
			//Date nowDate = new Date();
			//获取昨天所在月的第一天
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(DateTimeUtil.getfistDayOfMonthOnYesterday());
			
			MoneymgrOrgDataExample moneymgrOrgDataExample = new MoneymgrOrgDataExample();
			com.hzcf.flagship.model.MoneymgrOrgDataExample.Criteria moneymgrOrgDataCriteria = moneymgrOrgDataExample.createCriteria();
			moneymgrOrgDataCriteria.andRecordDateEqualTo(calendar.getTime());
			List<MoneymgrOrgData> list = moneymgrOrgDataMapper.selectByExample(moneymgrOrgDataExample);
			int totalPlanNum=0;
			String result = RESULT_NORMAL;
			StringBuffer stringBuffer = new StringBuffer();
			Map<String, Object> diffMap = moneymgrOrgDataMapper.getDiffOfCountAndDistinctOrgNumByDate(calendar.getTime());
			
			
			logger.info("开始检查f_moneymgr_org_data表数据,检查开始日期为:"+DateTimeUtil.getDateChinaString(calendar.getTime()));
			check: {
				if (list==null || list.size()==0 || diffMap==null) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(calendar.getTime())+"无数据；");
					break check;
				} 
				Long districtTotalDiff = (Long)diffMap.get("district_total_diff");
				Long orgNumDiff = (Long)diffMap.get("org_num_diff");
				if (orgNumDiff>0) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(calendar.getTime())+"数据重复；");
					break check;
				}
				if (districtTotalDiff>0) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(calendar.getTime())+"有大区缺少合计数据；");
					break check;
				}
				for (MoneymgrOrgData moneymgrOrgData : list) {
					if ("本月总计划".equals(moneymgrOrgData.getOrgName())) {
						totalPlanNum++;
						if (moneymgrOrgData.getMonthPlan()==null || moneymgrOrgData.getCounselorNumLastMonth()==null) {
							result = RESULT_ABNORMAL;
							stringBuffer.append(DateTimeUtil.getDateChinaString(calendar.getTime())+"本月总计划有数据为null；");
							break check;
						}
					}else if (StringUtil.notBlank(moneymgrOrgData.getOrgName()) && !"合计".equals(moneymgrOrgData.getOrgName())) {
						if (StringUtil.notBlank(moneymgrOrgData.getDistrictName())) {
							if (StringUtil.blank(moneymgrOrgData.getDistrictPrincipal())
									|| moneymgrOrgData.getMonthPlan()==null
									|| moneymgrOrgData.getCounselorNumLastMonth()==null) {
								result = RESULT_ABNORMAL;
								stringBuffer.append(DateTimeUtil.getDateChinaString(calendar.getTime())+"有数据为空；");
								break check;
							}
						}else {
							if (moneymgrOrgData.getMonthPlan()==null) {
								result = RESULT_ABNORMAL;
								stringBuffer.append(DateTimeUtil.getDateChinaString(calendar.getTime())+"有数据为空；");
								break check;
							}
						}
					}else if ("合计".equals(moneymgrOrgData.getOrgName())) {
						if (StringUtil.blank(moneymgrOrgData.getDistrictName())
								|| moneymgrOrgData.getMonthPlan()==null) {
							result = RESULT_ABNORMAL;
							stringBuffer.append(DateTimeUtil.getDateChinaString(calendar.getTime())+"有数据为空；");
							break check;
						}
						
					}else {
						result = RESULT_ABNORMAL;
						stringBuffer.append(DateTimeUtil.getDateChinaString(calendar.getTime())+"有数据机构名称为空；");
					}
				}
				
				if (totalPlanNum==0) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(calendar.getTime())+"无月总计划数据；");
				}else if (totalPlanNum>1) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(calendar.getTime())+"月总计划数据重复；");
				}
			}
				
			
			if (RESULT_NORMAL.equals(result)) {
				resultTrue(UPLOAD_LOG_TYPE, "f_moneymgr_org_data");
			}else {
				resultError(UPLOAD_LOG_TYPE, "f_moneymgr_org_data", stringBuffer.toString());
			}
			//variableCalendar.add(Calendar.DAY_OF_YEAR, -1);
			logger.info("检查f_moneymgr_org_data表数据结束,检查最后日期为:"+DateTimeUtil.getDateChinaString(calendar.getTime()));
		} catch (Exception e) {
			logger.error("检查f_moneymgr_org_data表数据出现异常",e);
		}
		
	}
	
	
	/**
	 * 检查理财历史月度累计表
	 * 检查上月所在年的前一年1月至上月每个月的数据是否存在/非空
	 */
	public void checkMoneymgrMonthHistoryTable(){
		try {
			Date nowDate = new Date();
			//获得当前月上月1日
			Calendar currentMonth = Calendar.getInstance();
			currentMonth.setTime(nowDate);
			currentMonth.add(Calendar.MONTH, -1);
			currentMonth.set(Calendar.DAY_OF_MONTH, 1);
			//currentMonth.set(Calendar.DAY_OF_MONTH, 1);
			//获得上月所在年的前一年1月1日
			Calendar variableCalendar = Calendar.getInstance();
			variableCalendar.setTime(currentMonth.getTime());
			variableCalendar.add(Calendar.YEAR, -1);
			variableCalendar.set(Calendar.MONTH,Calendar.JANUARY);
			
			String result = RESULT_NORMAL;
			StringBuffer stringBuffer = new StringBuffer();
			
			logger.info("开始检查f_moneymgr_month_history表数据,检查开始日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
			//依次查出每月数据并检查
			for (; variableCalendar.compareTo(currentMonth)<=0; variableCalendar.add(Calendar.MONTH, 1)) {
				Map<String, Object> diffMap = moneymgrMonthHistoryMapper.getDiffOfCountAndIndexNumByDate(variableCalendar.getTime());
				if (diffMap!=null) {
					Long diff = (Long)diffMap.get("diff");
					if (diff>0) {
						result = RESULT_ABNORMAL;
						stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"数据重复；");
						continue;
					}
				}
				
				//获取检查指标为"业绩"的数据
				MoneymgrMonthHistoryExample moneymgrMonthHistoryExample = new MoneymgrMonthHistoryExample();
				com.hzcf.flagship.model.MoneymgrMonthHistoryExample.Criteria moneymgrMonthHistoryCriteria = moneymgrMonthHistoryExample.createCriteria();
				moneymgrMonthHistoryCriteria.andRecordDateEqualTo(variableCalendar.getTime());
				moneymgrMonthHistoryCriteria.andIndexNameEqualTo("业绩");
				List<MoneymgrMonthHistory> performanceList = moneymgrMonthHistoryMapper.selectByExample(moneymgrMonthHistoryExample);
				
				//获取指标为"新客户数"的数据
				MoneymgrMonthHistoryExample moneymgrMonthHistoryExample2 = new MoneymgrMonthHistoryExample();
				com.hzcf.flagship.model.MoneymgrMonthHistoryExample.Criteria moneymgrMonthHistoryCriteria2 = moneymgrMonthHistoryExample2.createCriteria();
				moneymgrMonthHistoryCriteria2.andRecordDateEqualTo(variableCalendar.getTime());
				moneymgrMonthHistoryCriteria2.andIndexNameEqualTo("新客户数");
				List<MoneymgrMonthHistory> newClientList = moneymgrMonthHistoryMapper.selectByExample(moneymgrMonthHistoryExample2);
				
				//获取指标为"咨询师人数"的数据
				MoneymgrMonthHistoryExample moneymgrMonthHistoryExample3 = new MoneymgrMonthHistoryExample();
				com.hzcf.flagship.model.MoneymgrMonthHistoryExample.Criteria moneymgrMonthHistoryCriteria3 = moneymgrMonthHistoryExample3.createCriteria();
				moneymgrMonthHistoryCriteria3.andRecordDateEqualTo(variableCalendar.getTime());
				moneymgrMonthHistoryCriteria3.andIndexNameEqualTo("咨询师人数");
				List<MoneymgrMonthHistory> counselorList = moneymgrMonthHistoryMapper.selectByExample(moneymgrMonthHistoryExample3);
				
				//获取指标为"机构数目"的数据
				MoneymgrMonthHistoryExample moneymgrMonthHistoryExample4 = new MoneymgrMonthHistoryExample();
				com.hzcf.flagship.model.MoneymgrMonthHistoryExample.Criteria moneymgrMonthHistoryCriteria4 = moneymgrMonthHistoryExample4.createCriteria();
				moneymgrMonthHistoryCriteria4.andRecordDateEqualTo(variableCalendar.getTime());
				moneymgrMonthHistoryCriteria4.andIndexNameEqualTo("机构数目");
				List<MoneymgrMonthHistory> orgNumList = moneymgrMonthHistoryMapper.selectByExample(moneymgrMonthHistoryExample4);
				
				//获取指标为"计划达成率"的数据
				MoneymgrMonthHistoryExample moneymgrMonthHistoryExample5 = new MoneymgrMonthHistoryExample();
				com.hzcf.flagship.model.MoneymgrMonthHistoryExample.Criteria moneymgrMonthHistoryCriteria5 = moneymgrMonthHistoryExample5.createCriteria();
				moneymgrMonthHistoryCriteria5.andRecordDateEqualTo(variableCalendar.getTime());
				moneymgrMonthHistoryCriteria5.andIndexNameEqualTo("计划达成率");
				List<MoneymgrMonthHistory> completeRateList = moneymgrMonthHistoryMapper.selectByExample(moneymgrMonthHistoryExample5);
				
				if (performanceList==null || performanceList.size()==0
						|| newClientList==null || newClientList.size()==0
						|| counselorList==null || counselorList.size()==0
						|| orgNumList==null || orgNumList.size()==0
						|| completeRateList==null || completeRateList.size()==0) {
						
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"无数据；");
					continue;
				}
				
				for (MoneymgrMonthHistory moneymgrMonthHistory : performanceList) {
					if (StringUtil.blank(moneymgrMonthHistory.getRecordName())
							|| moneymgrMonthHistory.getIndexValue()==null) {
						result = RESULT_ABNORMAL;
						stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"有指标为业绩的数据为空；");
						break;
					}
				}
				
				for (MoneymgrMonthHistory moneymgrMonthHistory : newClientList) {
					if (StringUtil.blank(moneymgrMonthHistory.getRecordName())
							|| moneymgrMonthHistory.getIndexValue()==null) {
						result = RESULT_ABNORMAL;
						stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"有指标为新客户数的数据为空；");
						break;
					}
				}
				
				for (MoneymgrMonthHistory moneymgrMonthHistory : counselorList) {
					if (StringUtil.blank(moneymgrMonthHistory.getRecordName())
							|| moneymgrMonthHistory.getIndexValue()==null) {
						result = RESULT_ABNORMAL;
						stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"有指标为咨询师人数的数据为空；");
						break;
					}
				}
				
				if (orgNumList.size()>1) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"指标为机构数目的数据重复；");
					continue;
				}
				
				MoneymgrMonthHistory orgNum = orgNumList.get(0);
				if (StringUtil.blank(orgNum.getRecordName())
						|| orgNum.getIndexValue()==null) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"指标为机构数目有数据为空；");
					continue;
				}
				
				if (completeRateList.size() > 1) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"指标为计划达成率的数据重复；");
					continue;
				}
				
				MoneymgrMonthHistory completeRate = completeRateList.get(0);
				if (StringUtil.blank(completeRate.getRecordName())
						|| completeRate.getIndexValue()==null) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(variableCalendar.getTime())+"指标为机构数目有数据为空；");
					continue;
				}
				
			}
			
			if (RESULT_NORMAL.equals(result)) {
				resultTrue(UPLOAD_LOG_TYPE, "f_moneymgr_month_history");
			}else {
				resultError(UPLOAD_LOG_TYPE, "f_moneymgr_month_history", stringBuffer.toString());
			}
			variableCalendar.add(Calendar.MONTH, -1);
			logger.info("检查f_moneymgr_month_history表数据结束,检查最后日期为:"+DateTimeUtil.getDateChinaString(variableCalendar.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("检查f_moneymgr_month_history表数据出现异常",e);
		}
	}
	
	
	
	/**
	 * 检查风险机构表
	 * 检查是否有f_risk_loan_cm1和f_risk_loan_m1_overdue表中有
	 * 但是机构表中没有的机构
	 */
	public void checkRiskOrgStructTable(){
		try {
			logger.info("开始检查风险机构表");
			//查找f_risk_loan_cm1中有但是机构表中没有的机构
			List<Map<String, Object>> orglist1 = riskOrgStructMapper.findCm1NotexistOrgs();
			boolean flag = false;
			String log = "";
			if (orglist1!=null && orglist1.size()>0) {
				flag = true;
				String orgs = "";
				for (Map<String, Object> map : orglist1) {
					orgs=orgs+(String)map.get("org_no")+",";
				}
				log = log + "f_risk_loan_cm1表中存在机构表中未记录的机构,机构编号为:"+orgs;
				//logger.error("f_risk_loan_cm1表中存在机构表中未记录的机构,机构编号为:"+orgs);
			}
			//查找f_risk_loan_m1_overdue中有但是机构表中没有的机构
			List<Map<String, Object>> orglist2 = riskOrgStructMapper.findM1OverdueNotexistOrgs();
			if (orglist2!=null && orglist2.size()>0) {
				flag = true;
				String orgs = "";
				for (Map<String, Object> map : orglist2) {
					orgs=orgs+(String)map.get("org_no")+",";
				}
				log = log + "f_risk_loan_m1_overdue表中存在机构表中未记录的机构,机构编号为:" +orgs;
				//logger.error("f_risk_loan_m1_overdue表中存在机构表中未记录的机构,机构编号为:"+orgs);
			}
			if (flag) {
				resultError(BI_LOG_TYPE, "f_risk_org_struct", log);
			}else {
				resultTrue(BI_LOG_TYPE, "f_risk_org_struct");
			}
			logger.info("检查风险机构表结束");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("检查f_risk_org_struct表数据出现异常",e);
		}
	}
	
	/**
	 *  检查风险产品表
	 */
	public void checkRiskProductTable(){
		try {
			logger.info("开始检查风险产品表");
			//查找f_risk_loan_cm1中有但是产品表中没有的产品
			//List<Map<String, Object>> orglist1 = riskOrgStructMapper.findCm1NotexistOrgs();
			List<Map<String, Object>> productList1 = riskProductMapper.findCm1NotexistProducts();
			boolean flag = false;
			String log = "";
			if (productList1!=null && productList1.size()>0) {
				flag = true;
				String products = "";
				for (Map<String, Object> map : productList1) {
					products=products+(String)map.get("product_no")+",";
				}
				log = log + "f_risk_loan_cm1表中存在产品表中未记录的产品,产品编号为:"+products;
				//logger.error("f_risk_loan_cm1表中存在机构表中未记录的机构,机构编号为:"+orgs);
			}
			//查找f_risk_loan_m1_overdue中有但是机构表中没有的机构
			//List<Map<String, Object>> orglist2 = riskOrgStructMapper.findM1OverdueNotexistOrgs();
			List<Map<String, Object>> productList2 = riskProductMapper.findM1OverdueNotexistProducts();
			if (productList2!=null && productList2.size()>0) {
				flag = true;
				String products = "";
				for (Map<String, Object> map : productList2) {
					products=products+(String)map.get("product_no")+",";
				}
				log = log + "f_risk_loan_m1_overdue表中存在产品表中未记录的产品,产品编号为:" +products;
				//logger.error("f_risk_loan_m1_overdue表中存在机构表中未记录的机构,机构编号为:"+orgs);
			}
			if (flag) {
				resultError(BI_LOG_TYPE, "f_risk_product", log);
			}else {
				resultTrue(BI_LOG_TYPE, "f_risk_product");
			}
			logger.info("检查风险产品表结束");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("检查f_risk_product表数据出现异常",e);
		}
		
	}
	
	/**
	 * 检查CM-1回款率表
	 * 检查T-1日数据是否存在,是否重复(上线只验证T-1日数据)
	 */
	public void checkRiskLoanCm1Table(){
		try {
			/*Calendar yesterdayCalendar = Calendar.getInstance();
			yesterdayCalendar.setTime(DateTimeUtil.getYesterdayDate());
			//获得三个月前的日期
			Calendar variableCalendar = Calendar.getInstance();
			variableCalendar.setTime(DateTimeUtil.getYesterdayDate());
			variableCalendar.add(Calendar.MONTH, -3);
			variableCalendar.add(Calendar.DAY_OF_YEAR, 1);*/
			
			Calendar yesterdayCalendar = Calendar.getInstance();
			yesterdayCalendar.setTime(DateTimeUtil.getYesterdayDate());
			
			
			String result = RESULT_NORMAL;
			StringBuffer stringBuffer = new StringBuffer();
			
			logger.info("开始检查f_risk_loan_cm1表数据,检查开始日期为:"+DateTimeUtil.getDateChinaString(yesterdayCalendar.getTime()));
			//依次取当日数据并检查
			/*for (;variableCalendar.compareTo(yesterdayCalendar)<=0 ; variableCalendar.add(Calendar.DAY_OF_YEAR, 1)) {*/
				//查询当日各项数据是否存在
				StringBuffer subStringBuffer = new StringBuffer();
				Map<String, Object> counts = riskLoanCm1Mapper.getDetailRecordsByDate(DateTimeUtil.getDateNormalString(yesterdayCalendar.getTime()));
				if (counts==null) {
					logger.error("查找日期为"+DateTimeUtil.getDateChinaString(yesterdayCalendar.getTime())+"的记录个数出错");
					//continue;
				}
				boolean flag=false;
				Object buTotalCount = counts.get("事业部总计");
				Object districtTotalCount = counts.get("大区总计");
				Object orgTotalCount = counts.get("营业部总计");
				Object buAssessCount = counts.get("事业部考核");
				Object districtAssessCount = counts.get("大区考核");
				Object orgAssessCount = counts.get("营业部考核");
				Object buLoanAfterCount = counts.get("事业部贷后");
				Object districtAfterCountCount = counts.get("大区贷后");
				Object orgAfterCountCount = counts.get("营业部贷后");
				if (buTotalCount!=null && (Long)buTotalCount == 0) {
					flag=true;
					result = RESULT_ABNORMAL;
					subStringBuffer.append("无事业部总计数据；");
				}
				if (districtTotalCount!=null && (Long)districtTotalCount == 0) {
					flag=true;
					result = RESULT_ABNORMAL;
					subStringBuffer.append("无大区总计数据；");
				}
				if (orgTotalCount!=null && (Long)orgTotalCount == 0) {
					flag=true;
					result = RESULT_ABNORMAL;
					subStringBuffer.append("无营业部总计数据；");
				}
				if (buAssessCount!=null && (Long)buAssessCount == 0) {
					flag=true;
					result = RESULT_ABNORMAL;
					subStringBuffer.append("无事业部考核数据；");
				}
				if (districtAssessCount!=null && (Long)districtAssessCount == 0) {
					flag=true;
					result = RESULT_ABNORMAL;
					subStringBuffer.append("无大区考核数据；");
				}
				if (orgAssessCount!=null && (Long)orgAssessCount == 0) {
					flag=true;
					result = RESULT_ABNORMAL;
					subStringBuffer.append("无营业部考核数据；");
				}
				if (buLoanAfterCount!=null && (Long)buLoanAfterCount == 0) {
					flag=true;
					result = RESULT_ABNORMAL;
					subStringBuffer.append("无事业部贷后数据；");
					
				}
				if (districtAfterCountCount!=null && (Long)districtAfterCountCount == 0) {
					flag=true;
					result = RESULT_ABNORMAL;
					subStringBuffer.append("大区贷后数据；");
				}
				if (orgAfterCountCount!=null && (Long)orgAfterCountCount == 0) {
					flag=true;
					result = RESULT_ABNORMAL;
					subStringBuffer.append("无营业部贷后数据；");
				}
				if (flag) {
					subStringBuffer.insert(0, DateTimeUtil.getDateChinaString(yesterdayCalendar.getTime()));
					stringBuffer.append(subStringBuffer.toString());
					//continue;
				}
				
				//检查数据是否重复
				Map<String, Object> diffMap = riskLoanCm1Mapper.getRepetitionNumByDate(DateTimeUtil.getDateNormalString(yesterdayCalendar.getTime()));
				if (diffMap==null) {
					logger.error("查找日期为"+DateTimeUtil.getDateChinaString(yesterdayCalendar.getTime())+"的重复记录个数出错");
					//continue;
				}
				Object diff = diffMap.get("diff");
				if (diff != null && (Long)diff > 0) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(yesterdayCalendar.getTime())+"有数据重复；");
				}
			//}
			
			if (RESULT_NORMAL.equals(result)) {
				resultTrue(BI_LOG_TYPE, "f_risk_loan_cm1");
			}else {
				resultError(BI_LOG_TYPE, "f_risk_loan_cm1", stringBuffer.toString());
			}
			//variableCalendar.add(Calendar.DAY_OF_YEAR, -1);
			logger.info("检查f_risk_loan_cm1表数据结束");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("检查f_risk_loan_cm1表数据出现异常",e);
		}
	}
	
	/**
	 * 检查M1逾期率表
	 * 检查T-1日数据是否存在,是否重复
	 */
	public void checkRiskLoanM1OverdueTable(){
		try {
			Calendar yesterdayCalendar = Calendar.getInstance();
			yesterdayCalendar.setTime(DateTimeUtil.getYesterdayDate());
			//获得三个月前的日期
			/*Calendar variableCalendar = Calendar.getInstance();
			variableCalendar.setTime(DateTimeUtil.getYesterdayDate());
			variableCalendar.add(Calendar.MONTH, -3);
			variableCalendar.add(Calendar.DAY_OF_YEAR, 1);*/
			String result = RESULT_NORMAL;
			StringBuffer stringBuffer = new StringBuffer();
			
			logger.info("开始检查f_risk_loan_m1_overdue表数据,检查开始日期为:"+DateTimeUtil.getDateChinaString(yesterdayCalendar.getTime()));
			//依次取当日数据并检查
			//for (;variableCalendar.compareTo(yesterdayCalendar)<=0 ; variableCalendar.add(Calendar.DAY_OF_YEAR, 1)) {
				//查询当日数据是否存在
				Map<String, Object> totalCountAndDiff = riskLoanM1OverdueMapper.getTotalCountAndDiffByDate(DateTimeUtil.getDateNormalString(yesterdayCalendar.getTime()));
				if (totalCountAndDiff==null) {
					logger.error("查找日期为"+DateTimeUtil.getDateChinaString(yesterdayCalendar.getTime())+"的记录个数出错");
					//continue;
				}
				Object total = totalCountAndDiff.get("total");
				Object diff = totalCountAndDiff.get("diff");
				//判断当日是否有数据
				if (total!=null && (Long)total==0) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(yesterdayCalendar.getTime())+"无数据；");
				}
				//判断当日数据是否重复
				if (diff!=null && (Long)diff>0) {
					result = RESULT_ABNORMAL;
					stringBuffer.append(DateTimeUtil.getDateChinaString(yesterdayCalendar.getTime())+"数据重复；");
				}
			//}
			
			if (RESULT_NORMAL.equals(result)) {
				resultTrue(BI_LOG_TYPE, "f_risk_loan_m1_overdue");
			}else {
				resultError(BI_LOG_TYPE, "f_risk_loan_m1_overdue", stringBuffer.toString());
			}
			//variableCalendar.add(Calendar.DAY_OF_YEAR, -1);
			logger.info("检查f_risk_loan_m1_overdue表数据结束");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("检查f_risk_loan_m1_overdue表数据出现异常",e);
		}
	}
	
	/**
	 * 检查T-1日资产端日业绩表是否有数据,是否重复
	 */
	public void checkAssetDailyPerformanceTable(){
		try {
			Calendar yesterdayCalendar = Calendar.getInstance();
			yesterdayCalendar.setTime(DateTimeUtil.getYesterdayDate());
			String result = RESULT_NORMAL;
			StringBuffer stringBuffer = new StringBuffer();
			
			logger.info("开始检查f_asset_daily_performance表数据,检查日期为:"+DateTimeUtil.getDateChinaString(yesterdayCalendar.getTime()));
			//依次取当日数据并检查
			long countByDate = assetDailyPerformanceMapper.countByDate(DateTimeUtil.getDateNormalString(yesterdayCalendar.getTime()));
			if (countByDate==0) {
				result = RESULT_ABNORMAL;
				stringBuffer.append(DateTimeUtil.getDateChinaString(yesterdayCalendar.getTime())+"无数据；");
			}
			
			long countRepetition = assetDailyPerformanceMapper.countRepetition(DateTimeUtil.getDateNormalString(yesterdayCalendar.getTime()));
			if (countRepetition>0) {
				result = RESULT_ABNORMAL;
				stringBuffer.append(DateTimeUtil.getDateChinaString(yesterdayCalendar.getTime())+"数据重复；");
			}
			
			if (RESULT_NORMAL.equals(result)) {
				resultTrue(BI_LOG_TYPE, "f_asset_daily_performance");
			}else {
				resultError(BI_LOG_TYPE, "f_asset_daily_performance", stringBuffer.toString());
			}
			logger.info("检查f_asset_daily_performance表数据结束");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("检查f_asset_daily_performance表数据出现异常",e);
		}
	}
	
	/**
	 * 检查风控计划表
	 * 检查有无当月的计划数据
	 */
	public void checkRiskPlanTable(){
		try {
			logger.info("开始检查f_risk_plan表数据");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			/*RiskPlanExample riskPlanExample = new RiskPlanExample();
			com.hzcf.flagship.model.RiskPlanExample.Criteria riskPlanCriteria = riskPlanExample.createCriteria();
			riskPlanCriteria.andRecordDateEqualTo(calendar.getTime());
			List<RiskPlan> list = riskPlanMapper.selectByExample(riskPlanExample);
			*/
			List<Map<String, Object>> list = riskPlanMapper.findOrgListByDate(DateTimeUtil.getDateNormalString(calendar.getTime()));
			if (list==null || list.size()==0) {
				resultError(UPLOAD_LOG_TYPE, "f_risk_plan", DateTimeUtil.getDateChinaString(calendar.getTime())+"无数据；");
				logger.info("检查f_risk_plan表数据结束");
				return;
			}
			resultTrue(UPLOAD_LOG_TYPE, "f_risk_plan");
			logger.info("检查f_risk_plan表数据结束");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("检查f_risk_plan表数据出现异常",e);
		}
	}
	
	
	/**
	 * 检查风控逾期明细表
	 * 检查T-1日数据是否存在,重复
	 */
	public void checkRiskOverdueTable(){
		try {
			Calendar yesterdayCalendar = Calendar.getInstance();
			yesterdayCalendar.setTime(DateTimeUtil.getYesterdayDate());
			//获得三个月前的日期
			/*Calendar variableCalendar = Calendar.getInstance();
			variableCalendar.setTime(DateTimeUtil.getYesterdayDate());
			variableCalendar.add(Calendar.MONTH, -3);
			variableCalendar.add(Calendar.DAY_OF_YEAR, 1);*/
			String result = RESULT_NORMAL;
			StringBuffer stringBuffer = new StringBuffer();
			
			logger.info("开始检查f_risk_overdue表数据,检查开始日期为:"+DateTimeUtil.getDateChinaString(yesterdayCalendar.getTime()));
			
			Map<String, Object> totalCountAndDiff = riskOverdueMapper.getCountOfTotalAndDiff(DateTimeUtil.getDateNormalString(yesterdayCalendar.getTime()));
			if (totalCountAndDiff==null) {
				logger.error("查找日期为"+DateTimeUtil.getDateChinaString(yesterdayCalendar.getTime())+"的记录个数出错");
				//continue;
			}
			Object total = totalCountAndDiff.get("total");
			Object diff = totalCountAndDiff.get("diff");
			//判断当日是否有数据
			if (total!=null && (Long)total==0) {
				result = RESULT_ABNORMAL;
				stringBuffer.append(DateTimeUtil.getDateChinaString(yesterdayCalendar.getTime())+"无数据；");
			}
			//判断当日数据是否重复
			if (diff!=null && (Long)diff>0) {
				result = RESULT_ABNORMAL;
				stringBuffer.append(DateTimeUtil.getDateChinaString(yesterdayCalendar.getTime())+"数据重复；");
			}	
				
				
			if (RESULT_NORMAL.equals(result)) {
				resultTrue(BI_LOG_TYPE, "f_risk_overdue");
			}else {
				resultError(BI_LOG_TYPE, "f_risk_overdue", stringBuffer.toString());
			}
			//variableCalendar.add(Calendar.DAY_OF_YEAR, -1);
			logger.info("检查f_risk_overdue表数据结束");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("检查f_risk_overdue表数据出现异常",e);
		}
	}
	
	/**
	 * 理财-日业绩接口
	 * d
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public void checkPerformanceForDays(){
		String sufUrl = "licai/performanceForDays";
		try {
			Map<String, Object> map = reqResult(sufUrl);
			String code = (String) map.get("returnCode");
			if("0000".equals(code)){	//接口检测--正常
				StringBuffer strBufer = new StringBuffer();
				Map<String, Object> data = (Map<String, Object>) map.get("data");
				List allDayInMonthList = (List) data.get("allDayInMonthList");
				if(allDayInMonthList.size() == 0){
					strBufer.append(" 理财日业绩当月每日业绩list为空");
				}
				checkStringBufferIsNull(sufUrl, strBufer);
			}else{	//接口检测--程序异常
				resultError(API_LOG_TYPE, sufUrl, "调用失败,状态码是： " + code);
			}
		} catch (Exception e) {
			logger.error("理财-日业绩接口检查接口出现异常", e);
		}
	}
	
	/**
	 * 理财-月业绩接口
	 * d
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public void checkPerformanceForMonths(){
		String sufUrl = "licai/performanceForMonths";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", token);
		params.put("months", firstDayOfMonth);
		try {
			String reqResult = HttpUtil.doPost(CHECKAPI_URL + sufUrl, JSON.toJSONString(params));
			Map<String, Object> map = (Map<String, Object>) JSON.parse(reqResult);
			String code = (String) map.get("returnCode");
			if("0000".equals(code)){
				String header = " 理财月业绩";
				StringBuffer strBufer = new StringBuffer();
				Map<String, Object> data = (Map<String, Object>) map.get("data");
				List currentYearPerformanceList = (List) data.get("currentYearPerformance");
				List lastYearPerformanceList = (List) data.get("lastYearPerformance");
				List currentYearRateList = (List) data.get("currentYearRate");
				List lastYearRateList = (List) data.get("lastYearRate");
				if(currentYearPerformanceList.size() == 0){
					strBufer.append("今年业绩list为空");
				}
				if(lastYearPerformanceList.size() == 0){
					strBufer.append("去年业绩list为空");
				}
				if(currentYearRateList.size() == 0){
					strBufer.append("今年达成率list为空");
				}
				if(lastYearRateList.size() == 0){
					strBufer.append("去年达成率list为空");
				}
				checkStringBufferIsNull(sufUrl, strBufer, header);
			}else{	//接口检测--程序异常
				resultError(API_LOG_TYPE, sufUrl, "调用失败,状态码是： " + code);
			}
		} catch (Exception e) {
			logger.error("理财-日业绩接口检查接口出现异常", e);
		}
	}

	
	/**
	 * 理财-日业绩地图接口
	 * d
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public void checkPerformanceOrgMapForDays(){
		String sufUrl = "licai/performanceOrgMapForDays";
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		params.put("days", day);
		try {
			String reqResult = HttpUtil.doPost(CHECKAPI_URL + sufUrl, params);
			Map<String, Object> map = (Map<String, Object>) JSON.parse(reqResult);
			String code = (String) map.get("returnCode");
			if("0000".equals(code)){
				StringBuffer strBufer = new StringBuffer();
				Map<String, Object> data = (Map<String, Object>) map.get("data");
				List allOrgMonthList = (List) data.get("allOrgMonthList");
				if(allOrgMonthList.size() == 0){
					strBufer.append(" 理财日业绩地图详情list为空");
				}
				checkStringBufferIsNull(sufUrl, strBufer);
			}else{	//接口检测--程序异常
				resultError(API_LOG_TYPE, sufUrl, "调用失败,状态码是： " + code);
			}
		} catch (Exception e) {
			logger.error("理财-日业绩地图接口检查接口出现异常", e);
		}
	}
	
	/**
	 * 理财-区域数据接口
	 * d
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public void checkFinanceForMonthData(){
		String sufUrl = "licai/financeForMonthData";
		try {
			Map<String, Object> map = reqResult(sufUrl);
			String code = (String) map.get("returnCode");
			if("0000".equals(code)){
				String header = " 理财区域,";
				StringBuffer strBufer = new StringBuffer();
				Map<String, Object> data = (Map<String, Object>) map.get("data");
				List monthRegionList = (List) data.get("monthRegionList");
				List areaOrgList = (List) data.get("areaOrgList");
				List orgNameList = (List) data.get("areaNameList");
				if(monthRegionList.size() == 0){
					strBufer.append("区域KPI表list为空");
				}
				if(areaOrgList.size() == 0){
					strBufer.append("机构KPI表list为空");
				}
				if(orgNameList.size() == 0){
					strBufer.append("机构列表list为空");
				}
				checkStringBufferIsNull(sufUrl, strBufer, header);
			}else{	//接口检测--程序异常
				resultError(API_LOG_TYPE, sufUrl, "调用失败,状态码是： " + code);
			}
		} catch (Exception e) {
			logger.error("理财-区域数据接口检查接口出现异常", e);
		}
	}
	
	/**
	 * 理财-日人员人效接口
	 * d
	 */
	@SuppressWarnings({"unchecked"})
	public void checkMoneymgrEfficiencyForDays(){
		String sufUrl = "licai/moneymgrEfficiencyForDays";
		try {
			Map<String, Object> map = reqResult(sufUrl);
			String code = (String) map.get("returnCode");
			if("0000".equals(code)){
				StringBuffer strBufer = new StringBuffer();
				Map<String, Object> data = (Map<String, Object>) map.get("data");
				if(data.isEmpty()){
					strBufer.append(" 理财日人员人效的接口返回数据为空");
				}
				checkStringBufferIsNull(sufUrl, strBufer);
			}else{	//接口检测--程序异常
				resultError(API_LOG_TYPE, sufUrl, "调用失败,状态码是： " + code);
			}
		} catch (Exception e) {
			logger.error("理财-日人员人效接口检查接口出现异常", e);
		}
	}
	
	/**
	 * 理财-月人员人效接口
	 * d
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public void checkMoneymgrEfficiencyForMonths(){
		String sufUrl = "licai/moneymgrEfficiencyForMonths";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", token);
		params.put("months", firstDayOfMonth);
		try {
			String reqResult = HttpUtil.doPost(CHECKAPI_URL + sufUrl, JSON.toJSONString(params));
			Map<String, Object> map = (Map<String, Object>) JSON.parse(reqResult);
			String code = (String) map.get("returnCode");
			if("0000".equals(code)){
				StringBuffer strBufer = new StringBuffer();
				Map<String, Object> data = (Map<String, Object>) map.get("data");
				List currentYearOrgNumList = (List) data.get("currentYearOrgNum");
				List lastYearOrgNumList = (List) data.get("lastYearOrgNum");
				List currentYearNewClientNumList = (List) data.get("currentYearNewClientNum");
				List lastYearNewClientNumList = (List) data.get("lastYearNewClientNum");
				List currentYearPerCapitaNewClientList = (List) data.get("currentYearPerCapitaNewClient");
				List lastYearPerCapitaNewClientList = (List) data.get("lastYearPerCapitaNewClient");
				List currentYearPerCapitaCapacityList = (List) data.get("currentYearPerCapitaCapacity");
				List lastYearPerCapitaCapacityList = (List) data.get("lastYearPerCapitaCapacity");
				String header = " 理财月人员人效";
				if(currentYearOrgNumList.size() == 0){
					strBufer.append("今年机构数list为空");
				}
				if(lastYearOrgNumList.size() == 0){
					strBufer.append("去年机构数list为空");
				}
				if(currentYearNewClientNumList.size() == 0){
					strBufer.append("今年新客户数list为空");
				}
				if(lastYearNewClientNumList.size() == 0){
					strBufer.append("去年新客户数list为空");
				}
				if(currentYearPerCapitaNewClientList.size() == 0){
					strBufer.append("当年人均新客户数list为空");
				}
				if(lastYearPerCapitaNewClientList.size() == 0){
					strBufer.append("去年人均新客户数list为空");
				}
				if(currentYearPerCapitaCapacityList.size() == 0){
					strBufer.append("当年人均产能list为空");
				}
				if(lastYearPerCapitaCapacityList.size() == 0){
					strBufer.append("去年人均产能list为空");
				}
				checkStringBufferIsNull(sufUrl, strBufer, header);
			}else{	//接口检测--程序异常
				resultError(API_LOG_TYPE, sufUrl, "调用失败,状态码是： " + code);
			}
		} catch (Exception e) {
			logger.error("理财-月人员人效接口检查接口出现异常", e);
		}
	}
	
	/**
	 * 融资-日业绩接口
	 * d
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public void checkFinanceDailyPerformance(){
		String sufUrl = "rongzi/financeDailyPerformance";
		try {
			Map<String, Object> map = reqResult(sufUrl);
			String code = (String) map.get("returnCode");
			if("0000".equals(code)){
				StringBuffer strBufer = new StringBuffer();
				Map<String, Object> data = (Map<String, Object>) map.get("data");
				List dailyPerformanceList = (List) data.get("dailyPerformanceList");
				if(dailyPerformanceList.size() == 0){
					strBufer.append(" 融资日业绩，每日放款list为空");
				}
				checkStringBufferIsNull(sufUrl, strBufer);
			}else{	//接口检测--程序异常
				resultError(API_LOG_TYPE, sufUrl, "调用失败,状态码是： " + code);
			}
		} catch (Exception e) {
			logger.error("融资-日业绩接口检查接口出现异常", e);
		}
	}
	
	
	/**
	 * 融资-月业绩接口
	 * d
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public void checkFinanceMonthPerformance(){
		String sufUrl = "rongzi/financeMonthPerformance";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", token);
		try {
			String reqResult = HttpUtil.doPost(CHECKAPI_URL + sufUrl, JSON.toJSONString(params));
			Map<String, Object> map = (Map<String, Object>) JSON.parse(reqResult);
			String code = (String) map.get("returnCode");
			if("0000".equals(code)){
				StringBuffer strBufer = new StringBuffer();
				Map<String, Object> data = (Map<String, Object>) map.get("data");
				List dataList = (List) data.get("dataList");
				if(dataList.size() == 0){
					strBufer.append(" 融资月业绩，计划达成率完成情况list为空");
				}
				checkStringBufferIsNull(sufUrl, strBufer);
			}else{	//接口检测--程序异常
				resultError(API_LOG_TYPE, sufUrl, "调用失败,状态码是： " + code);
			}
		} catch (Exception e) {
			logger.error("融资-月业绩接口检查接口出现异常", e);
		}
	}
	
	/**
	 * 融资-风险接口
	 * d
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public void checkFinanceRisk(){
		String sufUrl = "rongzi/financeRisk";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", token);
		try {
			String reqResult = HttpUtil.doPost(CHECKAPI_URL + sufUrl, JSON.toJSONString(params));
			Map<String, Object> map = (Map<String, Object>) JSON.parse(reqResult);
			String code = (String) map.get("returnCode");
			if("0000".equals(code)){
				StringBuffer strBufer = new StringBuffer();
				Map<String, Object> data = (Map<String, Object>) map.get("data");
				String header = " 融资风险,";
				List CM1List = (List) data.get("CM1List");
				List lossList = (List) data.get("lossList");
				if(CM1List.size() == 0){
					strBufer.append("CM1回款率list为空");
				}
				if(lossList.size() == 0){
					strBufer.append("年化新增损失率list为空");
				}
				checkStringBufferIsNull(sufUrl, strBufer, header);
			}else{	//接口检测--程序异常
				resultError(API_LOG_TYPE, sufUrl, "调用失败,状态码是： " + code);
			}
		} catch (Exception e) {
			logger.error("融资-风险接口检查接口出现异常", e);
		}
	}
	
	/**
	 * 融资-人员人效日接口
	 * d
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public void checkFinanceDailyPeople(){
		String sufUrl = "rongzi/financeDailyPeople";
		try {
			Map<String, Object> map = reqResult(sufUrl);
			String code = (String) map.get("returnCode");
			if("0000".equals(code)){
				StringBuffer strBufer = new StringBuffer();
				Map<String, Object> data = (Map<String, Object>) map.get("data");
				List monthAvgPerEntrylist = (List) data.get("monthAvgPerEntrylist");
				if(monthAvgPerEntrylist.size() == 0){
					strBufer.append(" 融资人员人效，当月日人均进件list为空");
				}
				checkStringBufferIsNull(sufUrl, strBufer);
			}else{	//接口检测--程序异常
				resultError(API_LOG_TYPE, sufUrl, "调用失败,状态码是： " + code);
			}
		} catch (Exception e) {
			logger.error("融资-人员人效日接口检查接口出现异常", e);
		}
	}
	/**
	 * 融资-人员人效月接口
	 * d
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public void checkFinanceMonthPeople(){
		String sufUrl = "rongzi/financeMonthPeople";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", token);
		try {
			String reqResult = HttpUtil.doPost(CHECKAPI_URL + sufUrl, JSON.toJSONString(params));
			Map<String, Object> map = (Map<String, Object>) JSON.parse(reqResult);
			String code = (String) map.get("returnCode");
			if("0000".equals(code)){
				String header = " 融资人员人效，月页面";
				StringBuffer strBufer = new StringBuffer();
				Map<String, Object> data = (Map<String, Object>) map.get("data");
				Map<String, Object> daily = (Map<String, Object>) data.get("daily");
				Map<String, Object> allAvgPerCap = (Map<String, Object>) data.get("allAvgPerCap");
				Map<String, Object> salesAvgPerCap = (Map<String, Object>) data.get("salesAvgPerCap");
				List avgMonthPerList = (List) daily.get("avgMonthPerList");
				List allAvgPerList = (List) allAvgPerCap.get("allAvgPerList");
				List salesAvgPerList = (List) salesAvgPerCap.get("salesAvgPerList");
				if(avgMonthPerList.size() == 0){
					strBufer.append("日人均进件list为空");
				}
				if(allAvgPerList.size() == 0){
					strBufer.append("全员人均产能list为空");
				}
				if(salesAvgPerList.size() == 0){
					strBufer.append("咨询师人均产能list为空");
				}
				checkStringBufferIsNull(sufUrl, strBufer, header);
			}else{	//接口检测--程序异常
				resultError(API_LOG_TYPE, sufUrl, "调用失败,状态码是： " + code);
			}
		} catch (Exception e) {
			logger.error("融资-人员人效月接口检查接口出现异常", e);
		}
	}
	
	/**
	 * 融资-进件质量日接口
	 * d
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public void checkFinanceDailyIntoQuality(){
		String sufUrl = "rongzi/financeDailyIntoQuality";
		try {
			Map<String, Object> map = reqResult(sufUrl);
			String code = (String) map.get("returnCode");
			if("0000".equals(code)){
				StringBuffer strBufer = new StringBuffer();
				Map<String, Object> data = (Map<String, Object>) map.get("data");
				Map<String, Object> accumuRateForMonth = (Map<String, Object>) data.get("accumuRateForMonth");
				Map<String, Object> accApprovalAvgForMonth = (Map<String, Object>) data.get("accApprovalAvgForMonth");
				List accApprovalList = (List) accumuRateForMonth.get("accApprovalList");
				List accApprovalAvgList = (List) accApprovalAvgForMonth.get("accApprovalAvgList");
				String header = " 融资进件质量，日页面";
				if(accApprovalList.size() == 0){
					strBufer.append("当月累计批核率list为空");
				}
				if(accApprovalAvgList.size() == 0){
					strBufer.append("当月累计批核件均list为空");
				}
				checkStringBufferIsNull(sufUrl, strBufer, header);
			}else{	//接口检测--程序异常
				resultError(API_LOG_TYPE, sufUrl, "调用失败,状态码是： " + code);
			}
		} catch (Exception e) {
			logger.error("融资-进件质量日接口检查接口出现异常", e);
		}
	}

	
	/**
	 * 融资-进件质量月接口
	 * d
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public void checkFinanceMonthIntoQuality(){
		String sufUrl = "rongzi/financeMonthIntoQuality";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", token);
		try {
			String reqResult = HttpUtil.doPost(CHECKAPI_URL + sufUrl, JSON.toJSONString(params));
			Map<String, Object> map = (Map<String, Object>) JSON.parse(reqResult);
			String code = (String) map.get("returnCode");
			if("0000".equals(code)){
				String header = " 融资进件质量，月页面";
				StringBuffer strBufer = new StringBuffer();
				Map<String, Object> data = (Map<String, Object>) map.get("data");
				Map<String, Object> approvalRate = (Map<String, Object>) data.get("approvalRate");
				Map<String, Object> approvalAvg = (Map<String, Object>) data.get("approvalAvg");
				List approvalRateList = (List) approvalRate.get("approvalRateList");
				List approvalAvgList = (List) approvalAvg.get("approvalAvgList");
				if(approvalRateList.size() == 0){
					strBufer.append("批核率list为空");
				}
				if(approvalAvgList.size() == 0){
					strBufer.append("批核件均list为空");
				}
				checkStringBufferIsNull(sufUrl, strBufer, header);
			}else{	//接口检测--程序异常
				resultError(API_LOG_TYPE, sufUrl, "调用失败,状态码是： " + code);
			}
		} catch (Exception e) {
			logger.error("融资-进件质量月接口检查接口出现异常", e);
		}
	}
	
	/**
	 * 发送日数据的接口请求
	 * @param sufUrl
	 * @return
	 * d
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> reqResult(String sufUrl) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", token);
		params.put("days", day);
		String reqResult = HttpUtil.doPost(CHECKAPI_URL + sufUrl, JSON.toJSONString(params));
		Map<String, Object> map = (Map<String, Object>) JSON.parse(reqResult);
		return map;
	}
	
	/**
	 * 处理拼接结果优化
	 * @param sufUrl
	 * @param strBufer
	 * @param header
	 * @author guodong
	 */
	private void checkStringBufferIsNull(String sufUrl, StringBuffer strBufer, String header) {
		if(StringUtil.isBlank(strBufer.toString())){	//接口检测--正常
			resultTrue(API_LOG_TYPE,sufUrl);
		}else{	//接口检测--数据异常
			strBufer.insert(0, header);
			resultError(API_LOG_TYPE, sufUrl, "数据异常： " + strBufer);
		}
	}
	
	/**
	 * 处理拼接结果
	 * @param sufUrl
	 * @param strBufer
	 * d
	 */
	private void checkStringBufferIsNull(String sufUrl, StringBuffer strBufer) {
		if(StringUtil.isBlank(strBufer.toString())){	//接口检测--正常
			resultTrue(API_LOG_TYPE,sufUrl);
		}else{	//接口检测--数据异常
			resultError(API_LOG_TYPE, sufUrl, "数据异常： " + strBufer);
		}
	}
	
	/**
	 * 结果正常的入库
	 * @param 
	 * name:表名/接口名
	 * type:类型
	 * @return
	 */
	public void resultTrue(String type, String name){
		try {
			CheckLog checkLog = new CheckLog();
			checkLog.setRecordDate(nowDayDate);
			checkLog.setType(type);
			checkLog.setSort(sort);
			checkLog.setName(name);
			checkLog.setResult(RESULT_NORMAL);
			checkLog.setResultDetail("正常");
			checkLog.setCreateTime(new Date());
			checkLogMapper.insert(checkLog);
		} catch (Exception e) {
			logger.error("结果正常的入库 出现异常",e);
		}
	}
	/**
	 * 结果异常的入库
	 * @param 
	 * type:类型
	 * name:表名/接口名
	 * resultDetail:检查详细结果
	 */
	public void resultError(String type,String name,String resultDetail){
		try {
			CheckLog checkLog = new CheckLog();
			checkLog.setCreateTime(new Date());
			checkLog.setName(name);
			checkLog.setRecordDate(nowDayDate);
			checkLog.setSort(sort);
			checkLog.setResult(RESULT_ABNORMAL);
			checkLog.setType(type);
			checkLog.setResultDetail(resultDetail);
			checkLogMapper.insert(checkLog);
		} catch (Exception e) {
			logger.error("结果正常的入库 出现异常",e);
		}
}
  /**
   * 查询 sort值
   * 
   */
	public  int getSort(){
		try {
			int sort;
			CheckLog checkLog = checkLogMapper.getSort();
			if (null == checkLog || null == checkLog.getSort()) {
				sort = 1;
			} else {
				sort = checkLog.getSort() + 1;
			}
			return sort;
		} catch (Exception e) {
			logger.error("查询 sort值 出现异常", e);
			return 0;
		}
	}
	/**
	 * 18 -- 查询所有大区
	 */
	@SuppressWarnings("unchecked")
	public void checkAllDistrict(){
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("token", token);
			String sufUrl="rongzi/findAllDistrict";
			String loginUrl = CHECKAPI_URL + "rongzi/findAllDistrict";
			String doPost = HttpUtil.doPost(loginUrl, JSON.toJSONString(params));
			Map<String, Object> map = (Map<String, Object>) JSON.parse(doPost);
			String code = (String)map.get("returnCode");
			Map<String,Object> data = (Map<String, Object>) map.get("data");
			List<Map<String,Object>> districtList =null;
			if(null != data){
				districtList = (List<Map<String, Object>>) data.get("districtList");
			}
			if("0000".equals(code)){//接口检测--正常
				if(null == districtList || districtList.size()==0){
					resultError(API_LOG_TYPE,sufUrl,"数据异常： 查询所有大区 无数据");
				}else{
					resultTrue(API_LOG_TYPE,sufUrl);
				}
			}else{	//接口检测--程序异常
				resultError(API_LOG_TYPE, sufUrl, "<<查询所有大区>>接口调用失败,状态码是： " + code);
			}
		} catch (Exception e) {
			logger.error("检查 <<查询所有大区>> 出现异常",e);
		}
	} 
	/**
	 * 19 --融资区域 日 所有营业部
	 */
	@SuppressWarnings("unchecked")
	public void checkAllOrgNameByDistrictOfDay(){
		try {
			Map<String, String> params = new HashMap<String, String>();
			StringBuffer resultDetail = new StringBuffer();
			String sufUrl="rongzi/findAllOrgNameByDistrictOfDay";
			params.put("token", token);
			//得到所有大区
			String areaNameUrl = CHECKAPI_URL + "rongzi/findAllDistrict";
			String allAreaName = HttpUtil.doPost(areaNameUrl, JSON.toJSONString(params));
			Map<String, Object> areaNameMap = (Map<String, Object>) JSON.parse(allAreaName);
			String returnCode = (String) areaNameMap.get("returnCode");
			Map<String,Object> data = (Map<String, Object>) areaNameMap.get("data");
			List<Map<String,Object>> districtList=null;
			if(null != data){
				districtList = (List<Map<String, Object>>) data.get("districtList");
			}
			if("0000".equals(returnCode)){
				if(null == districtList || districtList.size()==0){
					resultError(API_LOG_TYPE, sufUrl,"数据异常：接口 <<融资区域 日 所有营业部>> 查询所有大区 无数据");
					return;
				}else{
					for(Iterator iterator = districtList.iterator(); iterator.hasNext();) {
						Map<String, Object> areaMap = (Map<String, Object>) iterator.next();
						String districtName = (String) areaMap.get("district_name");
						params.put("areaName", districtName);
						params.put("days", day);
						String loginUrl = CHECKAPI_URL + sufUrl;
						String doPost = HttpUtil.doPost(loginUrl, JSON.toJSONString(params));
						Map<String, Object> orgNameMap = (Map<String, Object>) JSON.parse(doPost);
						String code = (String) orgNameMap.get("returnCode");
						Map<String,Object> orgNameData = (Map<String, Object>) orgNameMap.get("data");
						List<Map<String,Object>> orgNameList =null;
						if(null != data){
							orgNameList = (List<Map<String, Object>>) orgNameData.get("orgNameList");
						}
						if("0000".equals(code)){
							if(null == orgNameList || orgNameList.size() ==0 ){
								resultDetail.append(districtName+" ");
							}
						}else{
							resultError(API_LOG_TYPE, sufUrl, "接口调用失败,状态码是： " + returnCode);
							return;
						}
					}
					if(StringUtil.blank(resultDetail.toString())){
						resultTrue(API_LOG_TYPE,sufUrl);
					}else{
						resultError(API_LOG_TYPE,sufUrl,"数据异常： <<融资区域 日 所有营业部>> "+resultDetail+"的营业部 无数据");
					}
				}
			}else{	//接口检测--程序异常
				resultError(API_LOG_TYPE, sufUrl, "<<融资区域 日 所有营业部>>接口 用 查询所有大区 调用失败,状态码是： " + returnCode);
			}
		} catch (Exception e) {
			logger.error("检查 <<融资区域 日 所有营业部>> 出现异常",e);
		}
	}
	
	/**
	 * 20 -- 融资区域 月 所有营业部
	 */
	@SuppressWarnings("unchecked")
	public void checkAllOrgNameByDistrictOfMonth(){
		try {
			Map<String, String> params = new HashMap<String, String>();
			StringBuffer resultDetail = new StringBuffer();
			String sufUrl="rongzi/findAllOrgNameByDistrictOfMonth";
			params.put("token", token);
			//得到所有大区
			String areaNameUrl = CHECKAPI_URL + "rongzi/findAllDistrict";
			String allAreaName = HttpUtil.doPost(areaNameUrl, JSON.toJSONString(params));
			Map<String, Object> areaNameMap = (Map<String, Object>) JSON.parse(allAreaName);
			String returnCode = (String) areaNameMap.get("returnCode");
			Map<String,Object> data = (Map<String, Object>) areaNameMap.get("data");
			List<Map<String,Object>> districtList = null;
			if(null != data){
				districtList = (List<Map<String, Object>>) data.get("districtList");
			}
			if("0000".equals(returnCode)){
				if(null == districtList || districtList.size()==0){
					resultError(API_LOG_TYPE, sufUrl ,"数据异常：<<融资区域 月 所有营业部>> 查询所有大区 无数据");
					return;
				}else{
					for (Iterator iterator = districtList.iterator(); iterator.hasNext();) {
						Map<String, Object> areaMap = (Map<String, Object>) iterator.next();
						String districtName = (String) areaMap.get("district_name");
						params.put("areaName", districtName);
						String loginUrl = CHECKAPI_URL + sufUrl;
						String doPost = HttpUtil.doPost(loginUrl, JSON.toJSONString(params));
						Map<String, Object> orgNameMap = (Map<String, Object>) JSON.parse(doPost);
						String code = (String) orgNameMap.get("returnCode");
						Map<String,Object> orgNameData = (Map<String, Object>) orgNameMap.get("data");
						List<Map<String,Object>> orgNameList= null;
						if(null != orgNameData){
							orgNameList = (List<Map<String, Object>>) orgNameData.get("orgNameList");
						}
						if("0000".equals(code)){
							if( null == orgNameList || orgNameList.size() ==0){
								resultDetail.append(districtName+" ");
							}
						}else{
							resultError(API_LOG_TYPE, sufUrl, "接口调用失败,状态码是： " + code);
							return;
						}
					}
					if(StringUtil.blank(resultDetail.toString())){ 
						resultTrue(API_LOG_TYPE,sufUrl);
					}else{
						resultError(API_LOG_TYPE,sufUrl,"数据异常：<<融资区域 月 所有营业部>>:"+resultDetail+"的营业部 无数据");
					}
				}
			}else{	//接口检测--程序异常
				resultError(API_LOG_TYPE, sufUrl, "<<融资区域 月 所有营业部>> 查询所有大区接口调用失败,状态码是： " + returnCode);
			}
		} catch (Exception e) {
			logger.error("检查 <<融资区域 月 所有营业部>> 出现异常",e);
		}
	}
	
	/**
	 * 21 -- 融资区域 日 区域列表展示
	 */
	@SuppressWarnings("unchecked")
	public void checkFinanceDistrictOfDayList(){
		try {
			
			Map<String, String> params = new HashMap<String, String>();
			StringBuffer resultDetail = new StringBuffer();
			String sufUrl="rongzi/financeDistrictOfDayList";
			params.put("token", token);
			//得到所有大区
			String areaNameUrl = CHECKAPI_URL + "rongzi/findAllDistrict";
			String allAreaName = HttpUtil.doPost(areaNameUrl, JSON.toJSONString(params));
			Map<String, Object> areaNameMap = (Map<String, Object>) JSON.parse(allAreaName);
			String returnCode = (String) areaNameMap.get("returnCode");
			Map<String,Object> data = (Map<String, Object>) areaNameMap.get("data");
			List<Map<String,Object>> districtList=null;
			if(null != data){
				districtList = (List<Map<String, Object>>) data.get("districtList");
			}
			if("0000".equals(returnCode)){
				if(null == districtList || districtList.size()==0){
					resultError(API_LOG_TYPE,sufUrl,"数据异常：<<融资区域 日 区域列表展示>> 查询所有大区 无数据");
					return;
				}
				for (Iterator iterator = districtList.iterator(); iterator.hasNext();) {
					Map<String, Object> areaMap = (Map<String, Object>) iterator.next();
					String districtName = (String) areaMap.get("district_name");
					params.put("areaName", districtName);
					params.put("days", day);
					String loginUrl = CHECKAPI_URL + sufUrl;
					String doPost = HttpUtil.doPost(loginUrl, JSON.toJSONString(params));
					Map<String, Object> orgNameMap = (Map<String, Object>) JSON.parse(doPost);
					String code = (String) orgNameMap.get("returnCode");
					if(!"0000".equals(code)){
						resultDetail.append(districtName+" ");
					}
				}
				if(StringUtil.blank(resultDetail.toString())){
					resultTrue(API_LOG_TYPE,sufUrl);
				}else{
					resultError(API_LOG_TYPE,sufUrl,"数据异常：<<融资区域 日 区域列表展示>> :"+resultDetail+"列表展示 异常");
				}
			}else{
				resultError(API_LOG_TYPE, sufUrl, "<<融资区域 日 区域列表展示>> 查询所有大区接口调用失败,状态码是： " + returnCode);
			}
		} catch (Exception e) {
			logger.error("检查 <<融资区域 日 区域列表展示>> 出现异常",e);
		}
	}
	
	/**
	 * 22 -- 融资区域 日 营业部列表展示
	 */
	@SuppressWarnings("unchecked")
	public void checkFinanceOrgNameOfDayList(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer resultDetail = new StringBuffer();
			String sufUrl="rongzi/financeOrgNameOfDayList";
			StringBuffer orgNameOfDistrict = new StringBuffer();
			params.put("token", token);
			//查询所有大区
			String areaNameUrl = CHECKAPI_URL + "rongzi/findAllDistrict";
			String allAreaName = HttpUtil.doPost(areaNameUrl, JSON.toJSONString(params));
			Map<String, Object> areaNameMap = (Map<String, Object>) JSON.parse(allAreaName);
			String returnCode = (String) areaNameMap.get("returnCode");
			Map<String,Object> data = (Map<String, Object>) areaNameMap.get("data");
			List<Map<String,Object>> districtList = null;
			if(null != data){
				districtList = (List<Map<String, Object>>) data.get("districtList");
			}
			if("0000".equals(returnCode)){
				if(null == districtList || districtList.size()==0){
					resultError(API_LOG_TYPE,sufUrl,"数据异常：<<融资区域 日 营业部列表展示>> 查询所有大区 无数据");
					return;
				}
				//查询所有营业部
				for (Iterator iterator = districtList.iterator(); iterator.hasNext();) {
					Map<String, Object> areaMap = (Map<String, Object>) iterator.next();
					String districtName = (String) areaMap.get("district_name");
					params.put("areaName", districtName);
					params.put("days", day);
					String loginUrl = CHECKAPI_URL + "rongzi/findAllOrgNameByDistrictOfDay";
					String doPost = HttpUtil.doPost(loginUrl, JSON.toJSONString(params));
					Map<String, Object> orgNameMap = (Map<String, Object>) JSON.parse(doPost);
					String code = (String) areaNameMap.get("returnCode");
					Map<String,Object> orgNameData = (Map<String, Object>) orgNameMap.get("data");
					List<Map<String,Object>> orgNameList=null;
					if(null != orgNameData){
						orgNameList= (List<Map<String, Object>>) orgNameData.get("orgNameList");
					}
					if(!"0000".equals(code) || null == orgNameList || orgNameList.size() ==0){
						orgNameOfDistrict.append(districtName+" ");
					}
					//查询融资区域 日 营业部列表
					if(null != orgNameList && orgNameList.size() >0){
						for (Iterator iterator2 = orgNameList.iterator(); iterator2.hasNext();) {
							Map<String, Object> map2 = (Map<String, Object>) iterator2.next();
							params.put("orgName", map2.get("org_name"));
							String url = CHECKAPI_URL + "rongzi/financeOrgNameOfDayList";
							String orgName = HttpUtil.doPost(url, JSON.toJSONString(params));
							Map<String, Object> orgNameMapOfDay = (Map<String, Object>) JSON.parse(orgName);
							String code2 = (String) orgNameMapOfDay.get("returnCode");
							if(!"0000".equals(code2)){
								resultDetail.append(map2.get("org_name")+" ");
							}
						}
					}
					
				}
				//查询 日 营业部 和 查询营业部列表都正常
				if(StringUtil.blank(resultDetail.toString()) && StringUtil.blank(orgNameOfDistrict.toString())){
					resultTrue(API_LOG_TYPE,sufUrl);
				}else{
					//查询所有 日 营业部 和 查询营业部列表 都 异常
					if(!StringUtil.blank(orgNameOfDistrict.toString()) && !StringUtil.blank(resultDetail.toString()) ){
						resultError(API_LOG_TYPE,sufUrl,"数据异常：<<融资区域 日 营业部列表展示>>  "+orgNameOfDistrict+" 的营业部为空;"+resultDetail+"列表展示异常");
					}else{
						//查询所有营业部 异常
						if(!StringUtil.blank(orgNameOfDistrict.toString())){
							resultError(API_LOG_TYPE,sufUrl,"数据异常：<<融资区域 日 营业部列表展示>>  "+orgNameOfDistrict+" 的营业部为空;");
						}
						//查询营业部 列表异常
						if(!StringUtil.blank(resultDetail.toString())){
							resultError(API_LOG_TYPE,sufUrl,"数据异常：<<融资区域 日 营业部列表展示>>  "+resultDetail+"列表展示异常;");
						}
					}
				}
			}else{
				resultError(API_LOG_TYPE, sufUrl, "<<融资区域 日 营业部列表展示>> 查询所有大区接口调用失败,状态码是： " + returnCode);
			}
		} catch (Exception e) {
			logger.error("检查 <<融资区域 日 营业部列表展示>> 出现异常",e);
		}
	}
	
	/**
	 * 25 -- 融资区域 月 区域列表展示
	 */
	@SuppressWarnings("unchecked")
	public void checkFinanceDistrictOfMonthList(){
		try {
			Map<String, String> params = new HashMap<String, String>();
			StringBuffer resultDetail = new StringBuffer();
			String sufUrl="rongzi/financeDistrictOfMonthList";
			params.put("token", token);
			//得到所有大区
			String areaNameUrl = CHECKAPI_URL + "rongzi/findAllDistrict";
			String allAreaName = HttpUtil.doPost(areaNameUrl, JSON.toJSONString(params));
			Map<String, Object> areaNameMap = (Map<String, Object>) JSON.parse(allAreaName);
			String returnCode = (String) areaNameMap.get("returnCode");
			Map<String,Object> data = (Map<String, Object>) areaNameMap.get("data");
			List<Map<String,Object>> districtList=null;
			if(null != data){
				districtList = (List<Map<String, Object>>) data.get("districtList");
			}
			if("0000".equals(returnCode)){
				if(null == districtList || districtList.size()==0){
					resultError(API_LOG_TYPE,sufUrl,"数据异常：<<融资区域 月 区域列表展示>> 查询所有大区 无数据");
					return;
				}
				for (Iterator iterator = districtList.iterator(); iterator.hasNext();) {
					Map<String, Object> areaMap = (Map<String, Object>) iterator.next();
					String districtName = (String) areaMap.get("district_name");
					params.put("areaName", districtName);
					params.put("days", firstDayOfMonth);
					String loginUrl = CHECKAPI_URL + sufUrl;
					String doPost = HttpUtil.doPost(loginUrl, JSON.toJSONString(params));
					Map<String, Object> orgNameMap = (Map<String, Object>) JSON.parse(doPost);
					String code = (String) orgNameMap.get("returnCode");
					if(!"0000".equals(code)){
						resultDetail.append(districtName+" ");
					}
				}
				if(StringUtil.blank(resultDetail.toString())){
					resultTrue(API_LOG_TYPE,sufUrl);
				}else{
					resultError(API_LOG_TYPE,sufUrl,"数据异常：<<融资区域 月 区域列表展示>>  :"+resultDetail+"列表展示 异常");
				}
			}else{
				resultError(API_LOG_TYPE, sufUrl, "<<融资区域 月 区域列表展示>> 查询所有大区接口调用失败,状态码是： " + returnCode);
			}
		} catch (Exception e) {
			logger.error("检查 <<融资区域 月 区域列表展示>> 出现异常",e);
		}
	}
	
	/**
	 * 26 -- 融资区域 月 营业部列表展示
	 */
	@SuppressWarnings("unchecked")
	public void checkFinanceOrgNameOfMonthList(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer resultDetail = new StringBuffer();
			String sufUrl="rongzi/financeOrgNameOfMonthList";
			StringBuffer orgNameOfDistrict = new StringBuffer();
			params.put("token", token);
			//查询所有大区
			String areaNameUrl = CHECKAPI_URL + "rongzi/findAllDistrict";
			String allAreaName = HttpUtil.doPost(areaNameUrl, JSON.toJSONString(params));
			Map<String, Object> areaNameMap = (Map<String, Object>) JSON.parse(allAreaName);
			String returnCode = (String) areaNameMap.get("returnCode");
			Map<String,Object> data = (Map<String, Object>) areaNameMap.get("data");
			List<Map<String,Object>> districtList = null;
			if(null != data){
				districtList = (List<Map<String, Object>>) data.get("districtList");
			}
			if("0000".equals(returnCode)){
				if(null == districtList || districtList.size()==0){
					resultError(API_LOG_TYPE,"rongzi/financeOrgNameOfMonthList","数据异常：<<融资区域 月 营业部列表展示>> 查询所有大区无数据");
					return;
				}
				//查询所有营业部
				for (Iterator iterator = districtList.iterator(); iterator.hasNext();) {
					Map<String, Object> areaMap = (Map<String, Object>) iterator.next();
					String districtName = (String) areaMap.get("district_name");
					params.put("areaName", districtName);
					params.put("days", firstDayOfMonth);
					String loginUrl = CHECKAPI_URL + "rongzi/findAllOrgNameByDistrictOfMonth";
					String doPost = HttpUtil.doPost(loginUrl, JSON.toJSONString(params));
					Map<String, Object> orgNameMap = (Map<String, Object>) JSON.parse(doPost);
					String code = (String) areaNameMap.get("returnCode");
					Map<String,Object> orgNameData = (Map<String, Object>) orgNameMap.get("data");
					List<Map<String,Object>> orgNameList=null;
					if(null != orgNameData){
						orgNameList= (List<Map<String, Object>>) orgNameData.get("orgNameList");
					}
					if(!"0000".equals(code) || null == orgNameList || orgNameList.size() ==0){
						orgNameOfDistrict.append(districtName+" ");
					}
					//查询融资区域 月 营业部列表
					if(null != orgNameList && orgNameList.size() >0){
						for (Iterator iterator2 = orgNameList.iterator(); iterator2.hasNext();) {
							Map<String, Object> map2 = (Map<String, Object>) iterator2.next();
							params.put("orgName", map2.get("org_name"));
							String url = CHECKAPI_URL + "rongzi/financeOrgNameOfMonthList";
							String orgName = HttpUtil.doPost(url, JSON.toJSONString(params));
							Map<String, Object> orgNameMapOfDay = (Map<String, Object>) JSON.parse(orgName);
							String code2 = (String) orgNameMapOfDay.get("returnCode");
							if(!"0000".equals(code2)){
								resultDetail.append(map2.get("org_name")+" ");
							}
						}
					}
					
				}
				//查询所有营业部 和 查询营业部列表都正常
				if(StringUtil.blank(resultDetail.toString()) && StringUtil.blank(orgNameOfDistrict.toString())){
					resultTrue(API_LOG_TYPE,sufUrl);
				}else{
					//查询所有营业部 和 查询营业部列表 都 异常
					if(!StringUtil.blank(resultDetail.toString()) && !StringUtil.blank(orgNameOfDistrict.toString())){
						resultError(API_LOG_TYPE,sufUrl,"数据异常：<<融资区域 月 营业部列表展示>>"+orgNameOfDistrict+" 的营业部为空;"+resultDetail+"列表展示异常");
					}else{
						//查询 月 所有营业部异常
						if(!StringUtil.blank(orgNameOfDistrict.toString()) ){
							resultError(API_LOG_TYPE,sufUrl,"数据异常：<<融资区域 月 营业部列表展示>>  "+orgNameOfDistrict+" 的营业部为空;");
						}
						if(!StringUtil.blank(resultDetail.toString())){
							resultError(API_LOG_TYPE,sufUrl,"数据异常：<<融资区域 月 营业部列表展示>> "+resultDetail+"列表展示异常;");
						}		
					}
				}
			}else{
				resultError(API_LOG_TYPE, sufUrl, "<<融资区域 月 营业部列表展示>>  查询所有大区接口调用失败,状态码是： " + returnCode);
			}
		} catch (Exception e) {
			logger.error("检查 <<融资区域 月 营业部列表展示>> 出现异常",e);
		}
	}
	/**
	 * 35 融资人员汇总
	 */
	@SuppressWarnings("unchecked")
	public void checkFinancePersonnelSum(){
		try {
			Map<String, String> params = new HashMap<String, String>();
			String sufUrl="rongzi/financePersonnelSum";
			params.put("token", token);
			String loginUrl = CHECKAPI_URL + sufUrl;
			String doPost = HttpUtil.doPost(loginUrl, JSON.toJSONString(params));
			Map<String, Object> map = (Map<String, Object>) JSON.parse(doPost);
			String code = (String)map.get("returnCode");
			Map<String,Object> data = (Map<String, Object>) map.get("data");
			if("0000".equals(code)){
				if(null == data){
					resultError(API_LOG_TYPE,sufUrl,"数据异常：<< 融资人员汇总>> 无数据");
				}else{ 
					resultTrue(API_LOG_TYPE,sufUrl);
				}
			}else{
				resultError(API_LOG_TYPE, sufUrl, "<< 融资人员汇总>>接口调用失败,状态码是： " + code);
			}
			
		} catch (Exception e) {
			logger.error("检查 <<融资人员汇总>> 出现异常",e);
		}
	}
	
	public void sendMessage() {
        try {
        	List<Map<String, Object>> list = checkLogMapper.findLogList();
        	 String classPath = this.getClass().getClassLoader().getResource("/").getPath();
        	  String realPath  = "";
        	  //windows下
        	  if("\\".equals(File.separator)){   
        		  realPath  = classPath.substring(1,classPath.indexOf("/WEB-INF/classes"));
        	      realPath = realPath.replace("/", "\\")+TMP_FILES;
        	  }
        	  //linux下
        	  if("/".equals(File.separator)){   
        		  realPath  = classPath.substring(0,classPath.indexOf("/WEB-INF/classes"));
        		  realPath = realPath.replace("\\", "/")+TMP_FILES;
        	  }
        	// 创建Excel
        	createExcel(list, realPath);
        	// 删除昨天的文件
        	deleteFile(realPath);
        	// 发送邮件
        	sendEmail();
        	// 发送短信
        	sendPhones(list);
		} catch (Exception e) {
			logger.error("发送邮件或短信 出现异常",e);
		}
	}

	/**
	 * 创建日志Excel
	 * @param list 
	 * @param realPath 
	 * 
	 */
	private void createExcel(List<Map<String, Object>> list,String realPath) {
		try {
			String[] rowsName = new String[] { "序号", "日期", "类型", "表名/接口名", "结果", "检查详细结果", "创建时间" };
			List<Object[]> dataList = new ArrayList<Object[]>();
			Object[] objs = null;
			Map<String, Object> promotionMap = null;
			for (int i = 0; i < list.size(); i++) {
				promotionMap = list.get(i);
				objs = new Object[rowsName.length];
				objs[0] = i + 1;
				objs[1] = promotionMap.get("record_date");
				objs[2] = promotionMap.get("type");
				objs[3] = promotionMap.get("name");
				objs[4] = promotionMap.get("result");
				objs[5] = promotionMap.get("result_detail");
				objs[6] = promotionMap.get("create_time");
				dataList.add(objs);
			}
			String title = day + "日志报表.xls";
			ExportExcel ex = new ExportExcel(title, rowsName, dataList);
			OutputStream out = null;
			FILEFULLPATH = realPath + File.separator + title;
			try {
				out = new FileOutputStream(new File(FILEFULLPATH));
				// 生成excel文件
				ex.write(out);
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			logger.error("创建日志Excel 出现异常",e);
		}

	}
	/**
	 * 发送短信并推送消息
	* @Description: 
	* @param @param list
	* @throws
	 */
	private void sendPhones(List<Map<String, Object>> list) {
		try {
			boolean status = false;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator.next();
				String result = (String) map.get("result");
				//发送短信通知
				if("异常".equals(result)){
					status = true;
				}
			}
			if(status){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("property_name", "CheckLogMessageSendPhones");
				Properties properties = propertiesMapper.findValueByPropertyName(map);
				String[] phones = properties.getPropertyStringValue().split(",");
				for (String phone : phones) {
					Map<String, String> params = new HashMap<String, String>();
					params.put("telephone", phone);
					params.put("message", "业务监控室数据出现异常，详细信息请查收邮件！！！");
					params.put("systemSourceId", SYSTEMSOURCEID);
					params.put("key", KEY);
					params.put("sendmessage_path", SENDMESSAGEPATH);
					boolean flag = NewSendMessageUtil.sendSmsProduct(params);
					if (!flag) {
						logger.info("-------短信发送异常------");
					}
				}
				//查询消息推送的手机号
				Map<String,Object> pushMap = new HashMap<String,Object>();
				pushMap.put("property_name", "CheckLogMessagePushPhones");
				Properties pushProperties = propertiesMapper.findValueByPropertyName(pushMap);
				String pushMobiles = pushProperties.getPropertyStringValue();
				PushUtil util = new PushUtil(androidAppkey, androidAppMasterSecret, iosAppkey, iosAppMasterSecret, production_mode);
				Integer result = util.customCast("PHONE", pushMobiles, "notification", null, "数据异常提醒", "业务监控室数据出现异常，详细信息请查收邮件！！！");
				if (result==200) {
					appPushMessageService.insertPushMessageByMobiles(pushMobiles, "数据异常提醒", "业务监控室数据出现异常，详细信息请查收邮件！！！");
				}
			}
		} catch (Exception e) {
			logger.error("发送短信 推送消息异常",e);
		}
	}
	
	 /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public  boolean deleteFile(String realPath) {
    	try {
    		String yesterdayBy = DateUtil.getYesterdayBythisDayT(new Date());
    		String yesterDayFileName = yesterdayBy + "日志报表.xls";
    		String yesterDayFileFullPath = realPath + File.separator + yesterDayFileName;
    		File file = new File(yesterDayFileFullPath);
    		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
    		if (file.exists() && file.isFile()) {
    			if (file.delete()) {
    				logger.info("删除单个文件" + yesterDayFileFullPath + "成功！");
    				return true;
    			} else {
    				logger.info("删除单个文件" + yesterDayFileFullPath + "失败！");
    				return false;
    			}
    		} else {
    			logger.info("删除单个文件失败：" + yesterDayFileFullPath + "不存在！");
    			return false;
    		}
		} catch (Exception e) {
			logger.error("删除单个文件",e);
			return false;
		}
	}
    
    /**
     * 发送邮件
     */
	public void sendEmail(){
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("property_name", "checkLogSendEmails");
			Properties properties = propertiesMapper.findValueByPropertyName(map);
			String[] to = null;
			if(null != properties){
				to = properties.getPropertyStringValue().split(",");
			}
			String subject =  day + "日志报表";
			String content = "您好！附件中为" + day + "的日志报表，请查收！";
			String[] fileList = new String[1];
			fileList[0] = FILEFULLPATH;
			boolean sendMail = EmailManager.getInstance().sendMail(to, subject, content, fileList);
			if(!sendMail){
				logger.info("=====邮件发送失败======");
			}
		} catch (Exception e) {
			logger.error("邮件发送异常",e);
		}
	}
	/**
	 * 41风控执委首页
	 */
	@SuppressWarnings({ "unchecked", "null" })
	public void checkRiskZWFirstPage() {
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("token", token);
			params.put("days", day);
			String sufUrl="riskApi/riskZWFirstPage";
			String loginUrl = CHECKAPI_URL + "riskApi/riskZWFirstPage";
			String doPost = HttpUtil.doPost(loginUrl, JSON.toJSONString(params));
			Map<String, Object> map = (Map<String, Object>) JSON.parse(doPost);
			String code = (String)map.get("returnCode");
			if("0000".equals(code)){//接口检测--正常
				StringBuffer strBufer = new StringBuffer();
				//接口返回一个月
				Map<String,Object> data = (Map<String, Object>) map.get("data");
				String plan = String.valueOf(data.get("plan"));
				String cm1Sum = String.valueOf(data.get("cm1Sum"));
				String status = String.valueOf(data.get("isShowNextMonth"));
				List firstKHList = (List) data.get("firstKHList");
				List firstZJList = (List) data.get("firstZJList");
				List firstDHList = (List) data.get("firstDHList");
				if("-1".equals(plan)){
					strBufer.append(" 无本月计划值");
				}
				if("-1".equals(cm1Sum)){
					strBufer.append(" 无本月累计C-M1回款率");
				}
				if(null == firstKHList || firstKHList.size()==0){
					strBufer.append(" 首月6期前列表 无数据");
				}
				if(null == firstZJList || firstZJList.size()==0){
					strBufer.append(" 首月 整体列表 无数据");
				}
				if(null == firstDHList || firstDHList.size()==0){
					strBufer.append(" 首月 6期后列表 无数据");
				}
			    if("1".equals(status)){
			    	//接口返回两个月
					List nextKHList = (List) data.get("nextKHList");
					List nextZJList = (List) data.get("nextZJList");
					List nextDHList = (List) data.get("nextDHList");
					if(null == nextKHList || nextKHList.size()==0){
						strBufer.append(" 下月 6期前列表 无数据");
					}
					if(null == nextZJList || nextZJList.size()==0){
						strBufer.append(" 下月 整体列表 无数据");
					}
					if(null == nextDHList || nextDHList.size()==0){
						strBufer.append(" 下月  6期后列表 无数据");
					}
			     }
			    checkStringBufferIsNull(sufUrl, strBufer);
			}else{	//接口检测--程序异常
				resultError(API_LOG_TYPE, sufUrl, "<<风控执委首页>>接口调用失败,状态码是： " + code);
			}
		} catch (Exception e) {
			logger.error("检查 <<风控执委首页>> 出现异常",e);
		}
	}
	/**
	 * 42  风控事业部首页
	 */
	public void checkRiskBusinessFirstPage() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String sufUrl="riskApi/riskBusinessFirstPage";
			params.put("token", token);
			params.put("days",day);
			String url = CHECKAPI_URL + "riskApi/riskBusinessFirstPage";
			//查询所有事业部
			Map<String, Object> resuestMap = new HashMap<String, Object>();
			resuestMap.put("day", DateTimeUtil.getYesterdayDateString());
			resuestMap.put("rank", 3);
			resuestMap.put("type", 2);
			List<Map<String,Object>> businessList  = riskOrgStructMapper.selectOrgNoByRank(resuestMap);
			if(null != businessList && businessList.size() !=0){
				StringBuffer strBufer = new StringBuffer();
				for (Map<String, Object> business : businessList) {
					//调用事业部首页接口
					String orgName = (String) business.get("orgName");
					params.put("orgNo", business.get("orgNo"));
					String doPost = HttpUtil.doPost(url, JSON.toJSONString(params));
					Map<String, Object> map = (Map<String, Object>) JSON.parse(doPost);
					String code = (String)map.get("returnCode");
					if("0000".equals(code)){//接口检测--正常
						@SuppressWarnings("unchecked")
						Map<String,Object> data = (Map<String, Object>) map.get("data");
						String status = String.valueOf(data.get("isShowNextMonth"));
						//接口返回一个月
						String plan = String.valueOf(data.get("plan"));
						if("-1".equals(plan)){
							strBufer.append(orgName+"  无本月计划值 ");
						}
						String cm1Sum = String.valueOf(data.get("cm1Sum"));
						if("-1.00".equals(cm1Sum)){
							strBufer.append(orgName+"  无本月累计C-M1回款率 ");
						}
						List chartList = (List) data.get("chartList");
						List firstCm1List = (List) data.get("firstCm1List");
						if(null == chartList || chartList.size()==0){
							strBufer.append(orgName+"  近6个月累计C-M1回款率柱状图无数据    ");
						}
						if(null == firstCm1List || firstCm1List.size()==0){
							strBufer.append(orgName+" 首月各大区累计C-M1回款率列表无数据    ");
						}
						//接口返回两个月
						 if("1".equals(status)){
							 List nextCm1List = (List) data.get("nextCm1List");
							 if(null == nextCm1List || nextCm1List.size()==0){
								strBufer.append(orgName+" 下月各大区累计C-M1回款率列表无数据  ");
							}
						 }
					}else{//接口检测--程序异常
						resultError(API_LOG_TYPE, sufUrl, "<<风控事业部首页>> 接口调用失败,状态码是： " + code);
					    return;
					}
				}
				 checkStringBufferIsNull(sufUrl, strBufer);
			}else{
				resultError(API_LOG_TYPE, sufUrl, "<<风控事业部首页>> 查询所有事业部 无数据 ");
			    return;
			}
		} catch (Exception e) {
			logger.error("检查 <<风控事业部首页>> 出现异常",e);
		}
	}
	
	/**
	 * 43 风控贷后首页
	 */
	@SuppressWarnings({ "unchecked", "null" })
	public void checkRiskAfterLoanFirstPage() {
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("token", token);
			params.put("days", day);
			String sufUrl="riskApi/riskAfterLoanFirstPage";
			String loginUrl = CHECKAPI_URL + "riskApi/riskAfterLoanFirstPage";
			String doPost = HttpUtil.doPost(loginUrl, JSON.toJSONString(params));
			Map<String, Object> map = (Map<String, Object>) JSON.parse(doPost);
			String code = (String)map.get("returnCode");
			if("0000".equals(code)){//接口检测--正常
				StringBuffer strBufer = new StringBuffer();
				//接口返回一个月
				Map<String,Object> data = (Map<String, Object>) map.get("data");
				String status = String.valueOf(data.get("isShowNextMonth"));
				String plan = String.valueOf(data.get("plan"));
				if("-1".equals(plan)){
					strBufer.append("  无本月计划值 ");
				}
				String cm1Sum = String.valueOf(data.get("cm1Sum"));
				if("-1".equals(cm1Sum)){
					strBufer.append("  无本月累计C-M1回款率");
				}
				
				List firstCm1List = (List) data.get("firstCm1List");
				if(null == firstCm1List || firstCm1List.size()==0){
					strBufer.append(" 首月累计C-M1回款率列表为空");
				}
			    if("1".equals(status)){
			    	//接口返回两个月
					List nextCm1List = (List) data.get("nextCm1List");
					if(null == nextCm1List || nextCm1List.size()==0){
						strBufer.append(" 下月累计C-M1回款率列表为空");
					}
			     }
			    checkStringBufferIsNull(sufUrl, strBufer);
			}else{	//接口检测--程序异常
				resultError(API_LOG_TYPE, sufUrl, "<<风控贷后首页>>接口调用失败,状态码是： " + code);
			}
		} catch (Exception e) {
			logger.error("检查 <<风控贷后首页>> 出现异常",e);
		}
	}
	
	/**
	 * 44 市场风险管理部首页
	 */
	@SuppressWarnings({ "unchecked", "null" })
	public void checkRiskSCFirstPage() {
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("token", token);
			params.put("days", day);
			String sufUrl="riskApi/riskSCFirstPage";
			String loginUrl = CHECKAPI_URL + "riskApi/riskSCFirstPage";
			String doPost = HttpUtil.doPost(loginUrl, JSON.toJSONString(params));
			Map<String, Object> map = (Map<String, Object>) JSON.parse(doPost);
			String code = (String)map.get("returnCode");
			if("0000".equals(code)){//接口检测--正常
				StringBuffer strBufer = new StringBuffer();
				//接口返回一个月
				Map<String,Object> data = (Map<String, Object>) map.get("data");
				String status = String.valueOf(data.get("isShowNextMonth"));
				String plan = String.valueOf(data.get("planVal"));
				String actualVal = String.valueOf(data.get("actualVal"));
				if("-1.00".equals(plan)){
					strBufer.append("  无本月计划值 ");
				}
				if("-1.00".equals(actualVal)){
					strBufer.append("  无本月累计Cm1实际值 ");
				}
				List subcenterCm1List = (List) data.get("subcenterCm1List");
				if(null == subcenterCm1List || subcenterCm1List.size()==0){
					strBufer.append(" 首月累计C-M1回款率List为空");
				}
			    if("1".equals(status)){
			    	//接口返回两个月
			    	List nextMonthSubcenterCm1List = (List) data.get("nextMonthSubcenterCm1List");
					if(null == nextMonthSubcenterCm1List || nextMonthSubcenterCm1List.size()==0){
						strBufer.append(" 下月累计C-M1回款率List为空");
					}
			     }
			    checkStringBufferIsNull(sufUrl, strBufer);
			}else{	//接口检测--程序异常
				resultError(API_LOG_TYPE, sufUrl, "<<市场管理部首页>>接口调用失败,状态码是： " + code);
			}
		} catch (Exception e) {
			logger.error("检查 <<市场管理部首页>> 出现异常",e);
		}
	}
	
	
	/** 
	 * 事业部下钻
	 * @author guodong
	 */
	public void checkRiskAreaPage() {
		String sufUrl = "riskApi/riskAreaPage";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rank", 3);
		map.put("header", " 事业部下钻接口:");
		map.put("info1", " 产品cm1回款率list为空");
		map.put("info2", " 预警营业部数量list为空");
		map.put("list1", "areaList");
		map.put("list2", "businessWaringList");
		checkRiskAreaAndBusinePage(sufUrl,map);
	}
	
	/** 
	 * 大区下钻
	 * @author guodong
	 */
	public void checkRiskBusinePage() {
		String sufUrl = "riskApi/riskBusinePage";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rank", 4);
		map.put("header", " 大区，分中心下钻接口:");
		map.put("info1", " 产品cm1回款率list为空");
		map.put("info2", " 批次cm1回款率为空");
		map.put("list1", "businessCM1ProductNameList");
		map.put("list2", "businessCM1BatchList");
		checkRiskAreaAndBusinePage(sufUrl,map);
	}
	/** 
	 * 大区首页
	 * @author guodong
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void checkRiskAreaFirstPage() {
		String sufUrl = "riskApi/riskAreaFirstPage";
		try {
			Map<String, Object> qMap = new HashMap<String, Object>();
			qMap.put("day", DateTimeUtil.getYesterdayDateString());
			qMap.put("rank", 4);
			qMap.put("type", 2);
			List<Map<String, Object>> orgNoList = riskOrgStructMapper.selectOrgNoByRank(qMap);
			String header = day + "大区首页接口";
			StringBuffer strBufer = new StringBuffer();
			if (null != orgNoList && orgNoList.size() > 0) {
				for (Map<String, Object> map : orgNoList) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("token", token);
					params.put("days", day);
					params.put("orgNo", map.get("orgNo"));
					String reqResult = HttpUtil.doPost(CHECKAPI_URL + sufUrl, JSON.toJSONString(params));
					Map<String, Object> resultMap = (Map<String, Object>) JSON.parse(reqResult);
					String code = (String) resultMap.get("returnCode");
					String orgName = (String) map.get("orgName");
					if ("0000".equals(code)) {
						StringBuffer strBuferOne = new StringBuffer();
						Map<String, Object> data = (Map<String, Object>) resultMap.get("data");
						List areaCM1ByProduct = (List) data.get("areaCM1ByProduct");
						String actualValStr = data.get("actualVal").toString();
						String planValStr = data.get("planVal").toString();
						Double actualVal = Double.valueOf(actualValStr);
						Double planVal = Double.valueOf(planValStr);
						if (actualVal.equals(-1.0)) {
							strBuferOne.append(" 累计Cm1实际值为空");
						}
						if (planVal.equals(-1.0)) {
							strBuferOne.append(" Cm1目标值为空");
						}
						if (null == areaCM1ByProduct || areaCM1ByProduct.size() == 0) {
							strBuferOne.append(" 各产品累计cm1list为空");
						}
						if(StringUtil.isNotBlank(strBuferOne.toString())){
							strBuferOne.insert(0, orgName);
							strBufer.append(strBuferOne);
							strBuferOne.setLength(0);
						}
					} else { // 接口检测--程序异常
						resultError(API_LOG_TYPE, sufUrl, header + "调用失败,状态码是： " + code);
						return;
					}
				}
				checkStringBufferIsNull(sufUrl, strBufer, header);
			} 
		} catch (Exception e) {
			logger.error("大区首页接口检查接口出现异常", e);
		}
	}
	/** 
	 * 分中心首页
	 * @author tieguowei
	 */
	private void checkRiskBranchCenterFirstPage() {
								 
		String sufUrl = "riskApi/riskBranchCenterFirstPage";
		try {
			//查询所有分中心
		    List<RiskSubcenter> subcenterList = riskSubcenterMapper.selectForDistinct();
			String header = day + "分中心首页接口";
			StringBuffer strBufer = new StringBuffer();
			if (null != subcenterList && subcenterList.size() > 0) {
				for (RiskSubcenter riskSubcenter : subcenterList) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("token", token);
					params.put("days", day);
					params.put("orgNo", riskSubcenter.getSubcenterNo());
					String reqResult = HttpUtil.doPost(CHECKAPI_URL + sufUrl, JSON.toJSONString(params));
					Map<String, Object> resultMap = (Map<String, Object>) JSON.parse(reqResult);
					String code = (String) resultMap.get("returnCode");
					String orgName = riskSubcenter.getName();
					if ("0000".equals(code)) {
						StringBuffer strBuferOrgName = new StringBuffer();
						Map<String, Object> data = (Map<String, Object>) resultMap.get("data");
						List branchCenterCM1ByProduct = (List) data.get("branchCenterCM1ByProduct");
						String actualValStr = data.get("actualVal").toString();
						String planValStr = data.get("planVal").toString();
						Double actualVal = Double.valueOf(actualValStr);
						Double planVal = Double.valueOf(planValStr);
						if (actualVal.equals(-1.0)) {
							strBuferOrgName.append(" 累计Cm1实际值为空");
						}
						if (planVal.equals(-1.0)) {
							strBuferOrgName.append(" 累计Cm1目标值为空");
						}
						if (null == branchCenterCM1ByProduct || branchCenterCM1ByProduct.size() == 0) {
							strBuferOrgName.append(" 各产品累计cm1list为空");
						}
						if(StringUtil.isNotBlank(strBuferOrgName.toString())){
							strBuferOrgName.insert(0, orgName);
							strBufer.append(strBuferOrgName);
							strBuferOrgName.setLength(0);
						}
					}else { // 接口检测--程序异常
						resultError(API_LOG_TYPE, sufUrl, header + "调用失败,状态码是： " + code);
						return;
					}
				}
				checkStringBufferIsNull(sufUrl, strBufer, header);
			}
		} catch (Exception e) {
			logger.error("分中心首页接口检查接口出现异常", e);
		}
	}
	/** 
	 * 贷后整体下钻接口
	 * @author guodong
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void checkRiskAfterLoanAll() {
		String sufUrl = "riskApi/riskAfterLoanAll";
		String month = DateUtil.getMonth(day);
		String[] split = month.split(",");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", token);
		params.put("days", day);
		try {
			StringBuffer strBufer = new StringBuffer();
			String header = "贷后整体下钻接口";
			for (int i = 0; i < split.length; i++) {
				params.put("month", split[i]);
				String reqResult = HttpUtil.doPost(CHECKAPI_URL + sufUrl, JSON.toJSONString(params));
				Map<String, Object> resultMap = (Map<String, Object>) JSON.parse(reqResult);
				String code = (String) resultMap.get("returnCode");
				if ("0000".equals(code)) {
					Map<String, Object> data = (Map<String, Object>) resultMap.get("data");
					List afterLoanAllList = (List) data.get("afterLoanAllList");
					if (null == afterLoanAllList || afterLoanAllList.size() == 0) {
						strBufer.append(split[i] + " 累计Cm1回款率list为空");
					}
					
				} else { // 接口检测--程序异常
					resultError(API_LOG_TYPE, sufUrl, header + split[i] + "调用失败,状态码是： " + code);
					return;
				}
			} 
			checkStringBufferIsNull(sufUrl, strBufer, header);
		} catch (Exception e) {
			logger.error("贷后整体下钻接口检查接口出现异常", e);
		}
			
	}
	/** 
	 * 营业部下钻接口
	 * @author guodong
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void checkRiskRegionToOrgPage() {
		String sufUrl = "riskApi/riskRegionToOrgPage";
		String header = " 营业部下钻接口";
		try {
			Map<String, Object> qMap = new HashMap<String, Object>();
			qMap.put("day", DateTimeUtil.getYesterdayDateString());
			qMap.put("rank", 5);
			qMap.put("limit", 2);
			List<Map<String, Object>> orgNoList = riskOrgStructMapper.selectOrgNoByRank(qMap);
			if(null != orgNoList && orgNoList.size()>0){
				Map<String, Object> orgMap = orgNoList.get(0);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("token", token);
				params.put("days", day);
				params.put("orgNo", orgMap.get("orgNo"));
				String reqResult = HttpUtil.doPost(CHECKAPI_URL + sufUrl, JSON.toJSONString(params));
				Map<String, Object> resultMap = (Map<String, Object>) JSON.parse(reqResult);
				String code = (String) resultMap.get("returnCode");
				if ("0000".equals(code)) {
					StringBuffer strBufer = new StringBuffer();
					Map<String, Object> data = (Map<String, Object>) resultMap.get("data");
					List cm1List = (List) data.get("cm1List");
					if (null == cm1List || cm1List.size() == 0) {
						strBufer.append(" M1+逾期率列表list为空");
					}
					checkStringBufferIsNull(sufUrl, strBufer, header);
				} else { // 接口检测--程序异常
					resultError(API_LOG_TYPE, sufUrl, header + "调用失败,状态码是： " + code);
				} 
			}
		} catch (Exception e) {
			logger.error("营业部下钻接口检查接口出现异常", e);
		}

	}
	/**
	 * 事业部和大区下钻公用
	 * @param sufUrl
	 * @param map
	 * @author guodong
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void checkRiskAreaAndBusinePage(String sufUrl, Map<String, Object> map) {
		StringBuffer strBufer = new StringBuffer();
		String header = (String) map.get("header");
		String month = DateUtil.getMonth(day);
		String[] split = month.split(",");
		try {
			for (int n = 1; n < 3; n++) {//暂时不校验贷后事业部和大区的数据 需校验时可将条件替换为 n<4
				Map<String, Object> qMap = new HashMap<String, Object>();
				qMap.put("day", DateTimeUtil.getYesterdayDateString());
				qMap.put("rank", map.get("rank"));
				qMap.put("type", n);
				List<Map<String, Object>> orgNoList = riskOrgStructMapper.selectOrgNoByRank(qMap);
				if (null != orgNoList && orgNoList.size() > 0) {
					for (Map<String, Object> orgMap : orgNoList) {
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("token", token);
						params.put("days", day);
						params.put("orgNo", orgMap.get("orgNo"));
						params.put("type", n);
						for (int i = 0; i < split.length; i++) {
							params.put("month", split[i]);
							String reqResult = HttpUtil.doPost(CHECKAPI_URL + sufUrl, JSON.toJSONString(params));
							Map<String, Object> resultMap = (Map<String, Object>) JSON.parse(reqResult);
							String code = (String) resultMap.get("returnCode");
							String type = getType(n);
							if ("0000".equals(code)) {
								StringBuffer strBuferOne = new StringBuffer();
								Map<String, Object> data = (Map<String, Object>) resultMap.get("data");
								List businessCM1ProductNameList = (List) data.get(map.get("list1"));
								List businessCM1BatchList = (List) data.get(map.get("list2"));
								if (null == businessCM1ProductNameList || businessCM1ProductNameList.size() == 0) {
									strBuferOne.append(" " + map.get("info1"));
								}
								if (null == businessCM1BatchList || businessCM1BatchList.size() == 0) {
									strBuferOne.append(" " + map.get("info2"));
								}
								if (StringUtil.isNotBlank(strBuferOne.toString())) {
									strBuferOne.insert(0, " " + split[i] + orgMap.get("orgName") + type);
									strBufer.append(strBuferOne);
									strBuferOne.setLength(0);
								}
							} else { // 接口检测--程序异常
								resultError(API_LOG_TYPE, sufUrl, header + "调用失败,状态码是： " + code);
								return;
							}
						}
					}

				}
			} 
		} catch (Exception e) {
			logger.error( map.get("header") + "检查接口出现异常", e);
		}
		checkStringBufferIsNull(sufUrl, strBufer,header);
	}
	
	
	/**
	 * 营业部逾期合同明细
	 * @param sufUrl
	 * @param map
	 * @author xuhao
	 */
	private void checkOrgOverdueDetail() {
		String sufUrl = "riskApi/orgOverdueDetail";
		String header = "营业部逾期合同明细接口";
		try {
			Map<String, Object> qMap = new HashMap<String, Object>();
			//从f_risk_loan_m1_overdue表中取出一个有数据的营业部
//			String orgNo = riskLoanM1OverdueMapper.getOneOrgNo(DateTimeUtil.getYesterdayDateString());
			Map<String, Object> orgAndProduct = riskLoanM1OverdueMapper.getOneOrgAndProduct(DateTimeUtil.getYesterdayDateString());
			if(null != orgAndProduct){
//				Map<String, Object> orgMap = orgNoList.get(0);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("token", token);
				params.put("days", day);
				params.put("orgNo", orgAndProduct.get("org"));
				params.put("productNo", orgAndProduct.get("product"));
				String reqResult = HttpUtil.doPost(CHECKAPI_URL + sufUrl, JSON.toJSONString(params));
				Map<String, Object> resultMap = (Map<String, Object>) JSON.parse(reqResult);
				String code = (String) resultMap.get("returnCode");
				if ("0000".equals(code)) {
					StringBuffer strBufer = new StringBuffer();
					Map<String, Object> data = (Map<String, Object>) resultMap.get("data");
					List contractList = (List) data.get("contractList");
					if (null == contractList || contractList.size() == 0) {
						strBufer.append(" M1+逾期明细列表list为空");
					}
					checkStringBufferIsNull(sufUrl, strBufer, header);
				} else { // 接口检测--程序异常
					resultError(API_LOG_TYPE, sufUrl, header + "调用失败,状态码是： " + code);
				} 
			}
		} catch (Exception e) {
			logger.error("营业部逾期合同明细检查接口出现异常", e);
		}
	}
	
	
	/**
	 * 营业部逾期合同明细
	 * @param sufUrl
	 * @param map
	 * @author xuhao
	 */
	private void checkWarningOrgs() {
		String sufUrl = "riskApi/warningOrgs";
		String header = "事业部预警营业部明细";
		try {
			Map<String, Object> qMap = new HashMap<String, Object>();
			//从f_risk_warning_level表中取出一个有数据的营业部
//			String orgNo = riskLoanM1OverdueMapper.getOneOrgNo(DateTimeUtil.getYesterdayDateString());
//			Map<String, Object> orgAndProduct = riskLoanM1OverdueMapper.getOneOrgAndProduct(DateTimeUtil.getYesterdayDateString());
			Map<String, Object> warningOrg = riskWarningLevelMapper.getOneWarningOrg(DateTimeUtil.getYesterdayDateString());
			if(null != warningOrg){
//				Map<String, Object> orgMap = orgNoList.get(0);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("token", token);
				params.put("days", day);
				params.put("orgNo", warningOrg.get("orgNo"));
				params.put("productNo", warningOrg.get("productNo"));
				params.put("warningLevel", warningOrg.get("warningLevel"));
				String reqResult = HttpUtil.doPost(CHECKAPI_URL + sufUrl, JSON.toJSONString(params));
				Map<String, Object> resultMap = (Map<String, Object>) JSON.parse(reqResult);
				String code = (String) resultMap.get("returnCode");
				if ("0000".equals(code)) {
					StringBuffer strBufer = new StringBuffer();
					Map<String, Object> data = (Map<String, Object>) resultMap.get("data");
					List warningOrgList = (List) data.get("warningOrgList");
					if (null == warningOrgList || warningOrgList.size() == 0) {
						strBufer.append(" 预警明细列表list为空");
					}
					checkStringBufferIsNull(sufUrl, strBufer, header);
				} else { // 接口检测--程序异常
					resultError(API_LOG_TYPE, sufUrl, header + "调用失败,状态码是： " + code);
				} 
			}
		} catch (Exception e) {
			logger.error("事业部预警营业部明细检查接口出现异常", e);
		}
	}
	
	/**
	 * 获取type对应值
	 * @param n
	 * @return
	 * @author guodong
	 */
	private String getType(int n) {
		String type;
		if(1==n){
			type="总计";
		}else if(2==n){
			type="考核";
		}else{
			type="贷后";
		}
		return type;
	}
}
