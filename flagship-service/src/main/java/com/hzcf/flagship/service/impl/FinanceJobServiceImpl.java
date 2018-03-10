package com.hzcf.flagship.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.FinanceDailyPageMapper;
import com.hzcf.flagship.dao.IndexMapper;
import com.hzcf.flagship.model.FinanceDailyPage;
import com.hzcf.flagship.model.FinanceDailyPageExample;
import com.hzcf.flagship.model.FinanceDailyPageExample.Criteria;
import com.hzcf.flagship.model.Index;
import com.hzcf.flagship.model.IndexExample;
import com.hzcf.flagship.service.FinanceJobService;


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
public class FinanceJobServiceImpl implements FinanceJobService{
	@Autowired
	private FinanceDailyPageMapper financeDailyPageMapper;
	@Autowired
	private IndexMapper indexMapper;
	private final static Logger logger = Logger.getLogger(FinanceJobServiceImpl.class);
	
	/**
	 * 定时插入融资当日结果数据(基本历史数据)
	 */
	@Override
	public void insertFinanceDailyPage() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		//calendar.add(Calendar.DAY_OF_MONTH, -1);//昨天
		//删除当日融资日结果数据
		FinanceDailyPageExample financeDailyPageExample = new FinanceDailyPageExample();
		Criteria financeDailyPageCriteria = financeDailyPageExample.createCriteria();
		financeDailyPageCriteria.andRecordDateEqualTo(calendar.getTime());
		financeDailyPageMapper.deleteByExample(financeDailyPageExample);
		//封装数据
		List<FinanceDailyPage> pages = new ArrayList<>();
		for (long i = 7L; i <= 18L; i++) {
			FinanceDailyPage financeDailyPage = new FinanceDailyPage();
			Index index = indexMapper.selectByPrimaryKey(i);
			financeDailyPage.setRecordDate(calendar.getTime());
			financeDailyPage.setDataName(index.getCode());
			financeDailyPage.setDataFigureValue(new BigDecimal(index.getValue()));
			pages.add(financeDailyPage);
		}
		financeDailyPageMapper.insertFinanceDailyPages(pages);
		//financeDailyPage.setFinancemgrDailyPlanyieldrateWarning(new BigDecimal(getIndexValue("financeMgr_daily_planYieldRate_warning")));
		//financeDailyPage.setFinancemgrDailyPlanyieldrateWell(new BigDecimal(getIndexValue("financeMgr_daily_planYieldRate_well")));
		logger.info("融资日结果数据当日指标值插入完毕");
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
