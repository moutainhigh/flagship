package com.hzcf.flagship.service;

import java.text.ParseException;

/**
 *<dl>
 *<dt>类名：MoneymgrPerformanceService.java</dt>
 *<dd>描述: 理财业绩类(定时任务)</dd> 
 *<dd>创建时间： 2017年5月17日 上午9:48:38</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月17日 上午9:48:38    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface MoneymgrJobService {
	public void updateDailyPerformancePageData() throws ParseException;
	
	public void myInsertAllOrgAccumuPerformanceData() throws Exception;
	
	public void updateMoneymgrEfficiencyPage() throws ParseException;
	
	public void currentMonthInsertMoneymgrDistrictPage();
}
