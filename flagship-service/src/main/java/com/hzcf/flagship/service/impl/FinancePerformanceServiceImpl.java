

package com.hzcf.flagship.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.ExtractStatusMapper;
import com.hzcf.flagship.dao.FinanceDailyPageMapper;
import com.hzcf.flagship.dao.FinanceDailyPerformanceMapper;
import com.hzcf.flagship.dao.IndexMapper;
import com.hzcf.flagship.form.PerformanceParm;
import com.hzcf.flagship.model.FinanceDailyPage;
import com.hzcf.flagship.model.FinanceDailyPageExample;
import com.hzcf.flagship.model.FinanceDailyPageExample.Criteria;
import com.hzcf.flagship.model.Index;
import com.hzcf.flagship.model.IndexExample;
import com.hzcf.flagship.service.ApiPerformanceService;
import com.hzcf.flagship.service.FinancePerformanceService;
import com.hzcf.flagship.util.DateTimeUtil;
import com.hzcf.flagship.vo.ReturnMsgData;

/**
 *<dl>
 *<dt>类名：FinancePerformanceServiceImpl.java</dt>
 *<dd>描述: 融资业绩</dd> 
 *<dd>创建时间： 2017年6月2日 下午3:14:34</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年6月2日 下午3:14:34    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Service
public class FinancePerformanceServiceImpl implements FinancePerformanceService{
	@Autowired
	private FinanceDailyPerformanceMapper financeDailyPerformanceMapper;
	@Autowired
	private ApiPerformanceService apiPerformanceService;
	@Autowired
	private IndexMapper indexMapper;
	@Autowired
	private ExtractStatusMapper extractStatusMapper;
	@Autowired
	private FinanceDailyPageMapper financeDailyPageMapper;
	private final static Logger logger = Logger.getLogger(FinancePerformanceServiceImpl.class);
	/**
	 * 融资日业绩
	 */
	@Override
	public ReturnMsgData apiGetFinanceDailyPerformance(PerformanceParm performanceParm) {
		Map<String, Object> data = new HashMap<>();
		//计算计划达成率
		//获得累计业绩
		Map<String, Object> financePerformanceData = apiPerformanceService.getFinancePerformanceData(performanceParm.getDays());
		Integer monthPlan = (Integer)financePerformanceData.get("monthPlan");
		data.put("performanceValue", financePerformanceData.get("accumuLoanValue"));
		data.put("monthPlan", monthPlan);
		data.put("completeRate", financePerformanceData.get("monthCumulativeRate"));
		if (monthPlan==-1) {
			data.put("dailyPlan", -1);
		}else {
			data.put("dailyPlan", monthPlan/DateTimeUtil.getLastMonthDays(performanceParm.getDays()));
		}
		Integer dateSchedule = (Integer)financePerformanceData.get("dateSchedule");
		data.put("dateSchedule", dateSchedule);
		double warningIndex = getIndexValue("financeMgr_daily_planYieldRate_warning");
		double wellIndex = getIndexValue("financeMgr_daily_planYieldRate_well");
		data.put("warningIndex", warningIndex);
		data.put("wellIndex", wellIndex);
		data.put("warningLine", (int)(dateSchedule-warningIndex));
		data.put("wellLine", (int)(dateSchedule-wellIndex));
		data.put("dailyPerformanceList",getDailyPerformanceListOfLastFiveDays(performanceParm.getDays()));
		//删掉返回值中为null的字段
		Set<String> keys = data.keySet();
		for (String key : keys) {
			Object object = data.get(key);
			if (object==null) {
				data.remove(key);
			}
		}
		
		return new ReturnMsgData("0000", "调用成功",data);
		/*FinanceDailyPerformanceExample financeDailyPerformanceExample = new FinanceDailyPerformanceExample();
		com.hzcf.flagship.model.FinanceDailyPerformanceExample.Criteria financeDailyPerformanceCriteria = financeDailyPerformanceExample.createCriteria();
		financeDailyPerformanceCriteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(performanceParm.getDays()));
		List<FinanceDailyPerformance> list = financeDailyPerformanceMapper.selectByExample(financeDailyPerformanceExample);
		if (list!=null && list.size()>0) {
			data.put("performanceValue", list.get(0).getAccumuLoanValue());
		}
		return null;*/
	}
	
	/**
	 * 获得过去5天(不跨月)的业绩
	 * @param dateString
	 */
	private List<Map<String, Object>>  getDailyPerformanceListOfLastFiveDays(String dateString){
		Map<String, Date> params = new HashMap<>();
		params.put("begin", DateTimeUtil.getFiveDaysAgoDate(dateString));
		params.put("end", DateTimeUtil.convertAsDateString(dateString));
		List<Map<String, Object>> list = financeDailyPerformanceMapper.getPerformanceBetweenDate(params);
		return list;
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

	/**
	 * 初始化日融资日基础数据(过去三个月index值)
	 */
	@Override
	public void initializeIndexPageData() {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(new Date());
		calendar1.add(Calendar.DAY_OF_MONTH, -1);//昨天
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(new Date());
		calendar2.add(Calendar.MONTH, -3);//3个月前
		//删除过去三个月融资日结果数据
		FinanceDailyPageExample financeDailyPageExample = new FinanceDailyPageExample();
		Criteria financeDailyPageCriteria = financeDailyPageExample.createCriteria();
		financeDailyPageCriteria.andRecordDateBetween(calendar2.getTime(), calendar1.getTime());
		financeDailyPageMapper.deleteByExample(financeDailyPageExample);
		//封装数据
		List<FinanceDailyPage> financeDailys = new ArrayList<>();
		List<FinanceDailyPage> pages = new ArrayList<>();
		logger.info("开始封装indexPage历史数据");
		for (long i = 7L; i <= 18L; i++) {
			FinanceDailyPage financeDailyPage = new FinanceDailyPage();
			Index index = indexMapper.selectByPrimaryKey(i);
			financeDailyPage.setRecordDate(calendar2.getTime());
			financeDailyPage.setDataName(index.getCode());
			financeDailyPage.setDataFigureValue(new BigDecimal(index.getValue()));
			financeDailys.add(financeDailyPage);
		}
		for (;calendar2.compareTo(calendar1)<=0 ; calendar2.add(Calendar.DAY_OF_MONTH, 1)) {
			for (FinanceDailyPage financeDaily : financeDailys) {
				FinanceDailyPage financeDailyPage = new FinanceDailyPage();
				//Index index = indexMapper.selectByPrimaryKey(i);
				financeDailyPage.setRecordDate(calendar2.getTime());
				financeDailyPage.setDataName(financeDaily.getDataName());
				financeDailyPage.setDataFigureValue(financeDaily.getDataFigureValue());
				pages.add(financeDailyPage);
			}
		}
		logger.info("indexPage历史数据封装结束");
		financeDailyPageMapper.insertFinanceDailyPages(pages);
		//financeDailyPage.setFinancemgrDailyPlanyieldrateWarning(new BigDecimal(getIndexValue("financeMgr_daily_planYieldRate_warning")));
		//financeDailyPage.setFinancemgrDailyPlanyieldrateWell(new BigDecimal(getIndexValue("financeMgr_daily_planYieldRate_well")));
		logger.info("初始化index数据完毕");
		
	}

	@Override
	public long checkDataOfTheDayisNormal(String days) {
		return extractStatusMapper.findCountByDate(days);
	}
}
