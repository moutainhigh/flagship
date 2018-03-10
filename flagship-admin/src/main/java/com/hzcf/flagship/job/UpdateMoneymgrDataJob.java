package com.hzcf.flagship.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hzcf.flagship.service.MoneymgrJobService;


/**
 *<dl>
 *<dt>类名：AddNearestDataJob.java</dt>
 *<dd>描述: 更新业绩模块数据任务类</dd> 
 *<dd>创建时间： 2017年5月17日 上午11:33:34</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月17日 上午11:33:34    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Component
@Scope("prototype")
public class UpdateMoneymgrDataJob {
	@Autowired
	MoneymgrJobService moneymgrJobService;
	protected final Log logger = LogFactory.getLog(getClass());
	
	public void testJobMethod(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//System.out.println(dateFormat.format(new Date()));
		logger.info(dateFormat.format(new Date())+"定时任务方法执行了");
	}
	
	/**
	 * 更新理财数据定时任务方法
	 */
	public void updateMoneymgrData(){
		updateMoneymgrDailyPerformancePages();
		updateMoneymgrOrgAccumuPerformancePages();
		updateDistrictPages();
		updateEfficiencyPage();
		logger.info("理财定时任务执行完成");
	}
	
	/**
	 * 更新日业绩结果页所有数据
	 */
	public void updateMoneymgrDailyPerformancePages(){
		try {
			moneymgrJobService.updateDailyPerformancePageData();
		} catch (Exception e) {
			logger.error("更新日业绩结果页数据出错");
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新所有地图数据结果页数据
	 */
	public void updateMoneymgrOrgAccumuPerformancePages(){
		try {
			moneymgrJobService.myInsertAllOrgAccumuPerformanceData();
		} catch (Exception e) {
			logger.error("更新机构月累计数据出错");
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新区域结果数据
	 */
	public void updateDistrictPages(){
		try {
			moneymgrJobService.currentMonthInsertMoneymgrDistrictPage();
		} catch (Exception e) {
			logger.error(" 更新当月区域结果数据出错");
			//logger.error(e.getMessage()); 
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新人员人效结果数据
	 */
	public void updateEfficiencyPage(){
		try {
			moneymgrJobService.updateMoneymgrEfficiencyPage();
		} catch (Exception e) {
			logger.error(" 更新理财人员人效结果数据出错");
//			//logger.error(e.getMessage()); 
			e.printStackTrace();
		}
	}
}
