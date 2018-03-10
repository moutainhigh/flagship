package com.hzcf.flagship.service;


/**
 *<dl>
 *<dt>类名：RiskJobService.java</dt>
 *<dd>描述: 风控任务类</dd> 
 *<dd>创建时间： 2017年10月23日 上午9:08:53</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年10月23日 上午9:08:53    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface RiskJobService {
	/**
	 * 计算本日预警级别
	 */
	public void riskInsertWarningLevel();
	
	/**
	 * 更新权限表
	 */
	public void riskUpdatePermission();
	/**
	 * 发送风控数据
	 */
	public void sendRiskDataEmail();
	/**
	 * 检查营业部是否分配分中心
	 */
	public void sendEmailOfSubcenterIsNull();
	/**
	 * 风控月邮件CM1明细数据上传FastDFS
	 */
	public void riskMonthEmailDataUpload();
}
