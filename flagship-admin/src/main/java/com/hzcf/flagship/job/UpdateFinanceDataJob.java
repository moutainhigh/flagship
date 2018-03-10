package com.hzcf.flagship.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hzcf.flagship.service.FinanceJobService;

/**
 *<dl>
 *<dt>类名：UpdateFinanceDataJob.java</dt>
 *<dd>描述: 更新融资数据定时任务类</dd> 
 *<dd>创建时间： 2017年6月7日 上午9:14:05</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年6月7日 上午9:14:05    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Component
@Scope("prototype")
public class UpdateFinanceDataJob {
	@Autowired
	private FinanceJobService financeJobService;
	protected final Log logger = LogFactory.getLog(getClass());
	/**
	 * 更新融资数据定时任务方法
	 */
	public void updateFinanceData(){
		updateDailyPage();
		logger.info("融资定时任务执行完成");
	}
	
	/**
	 * 更新融资日结果数据
	 */
	public void updateDailyPage(){
		try {
			financeJobService.insertFinanceDailyPage();
		} catch (Exception e) {
			logger.error("插入融资日结果数据出错");
			//logger.error(e.getMessage()); 
			e.printStackTrace();
		}
	}
}
