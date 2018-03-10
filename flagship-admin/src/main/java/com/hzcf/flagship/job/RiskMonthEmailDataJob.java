package com.hzcf.flagship.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hzcf.flagship.service.RiskJobService;

/**
*<dl>
*<dt>类名：RiskMonthEmailDataJob.java</dt>
*<dd>描述: 风控月邮件CM1明细数据</dd>
*<dd>创建时间： 2018年2月23日 下午2:15:40</dd>
*<dd>创建人：tie</dd>
*<dt>版本历史: </dt>
* <pre>
* Date Author Version Description
* ------------------------------------------------------------------
* 2018年2月23日 下午2:15:40 tie 1.0 1.0 Version
* </pre>
*</dl>
*/
@Component
@Scope("prototype")
public class RiskMonthEmailDataJob {

	@Autowired
	RiskJobService riskJobService;
	
	public void riskMonthEmailDataUpload(){
		//风控月邮件CM1明细数据上传FastDFS
		riskJobService.riskMonthEmailDataUpload();
	}
}
