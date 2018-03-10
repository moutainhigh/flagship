package com.hzcf.flagship.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hzcf.flagship.service.RiskJobService;

/**
 *<dl>
 *<dt>类名：RiskJob.java</dt>
 *<dd>描述: 风控定时任务类</dd> 
 *<dd>创建时间： 2017年10月25日 下午3:14:49</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年10月25日 下午3:14:49    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Component
@Scope("prototype")
public class RiskJob {
	@Autowired
	RiskJobService riskJobService;
	
	public void updateRiskDate(){
		//计算本日预警级别
		riskJobService.riskInsertWarningLevel();
		//更新权限表
		riskJobService.riskUpdatePermission();
		
	}
	
}
