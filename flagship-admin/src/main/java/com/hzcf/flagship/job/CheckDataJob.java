package com.hzcf.flagship.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hzcf.flagship.service.CheckJobService;
import com.hzcf.flagship.service.CheckUserStatusJobService;


/**
 *<dl>
 *<dt>类名：CheckDateJob.java</dt>
 *<dd>描述: 检查数据任务类</dd> 
 *<dd>创建时间： 2017年9月18日 上午10:03:12</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年9月18日 上午10:03:12    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Component
@Scope("prototype")
public class CheckDataJob {
	@Autowired
	CheckJobService checkJobService;
	@Autowired
	CheckUserStatusJobService checkUserStatusJobService;
	
	public void checkData(){
		//检查数据和接口
		checkJobService.checkData();
		//发送信息
		checkJobService.sendMessage();
	}
	
	public void checkUserStatus(){
		//检查app用户
		checkUserStatusJobService.checkAppUser();
		//检查后台用户
		checkUserStatusJobService.checkAdminUser();
	}
	
	
}
