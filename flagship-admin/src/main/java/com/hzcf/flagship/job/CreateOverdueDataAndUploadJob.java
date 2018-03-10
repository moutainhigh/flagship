package com.hzcf.flagship.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hzcf.flagship.service.PublishService;

/**
 * 
*<dl>
*<dt>类名：CreateOverdueDataAndUploadJob.java</dt>
*<dd>描述:创建营业部逾期数据EXCEL上传FastDFS定时任务</dd>
*<dd>创建时间： 2017年12月28日 下午4:38:33</dd>
*<dd>创建人：Tieguowei</dd>
*<dt>版本历史: </dt>
* <pre>
* Date Author Version Description
* ------------------------------------------------------------------
* 2017年12月28日 下午4:38:33 Tieguowei 1.0 1.0 Version
* </pre>
*</dl>
 */
@Component
@Scope("prototype")
public class CreateOverdueDataAndUploadJob {

	@Autowired
	private PublishService publishService;
	
	public void createOverdueExcelAndUpload(){
		publishService.send();
	}
}
