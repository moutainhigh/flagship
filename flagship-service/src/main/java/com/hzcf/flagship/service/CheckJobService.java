package com.hzcf.flagship.service;

/**
 *<dl>
 *<dt>类名：CheckJobService.java</dt>
 *<dd>描述: 检查数据任务service</dd> 
 *<dd>创建时间： 2017年9月13日 上午9:51:18</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年9月13日 上午9:51:18    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface CheckJobService {
	/**
	 * 检查数据
	 */
	public void checkData();
	
	/**
	 * 检查BI数据
	 */
	public void checkBIData();
	
	/**
	 * 检查上传数据
	 */
	public void checkUploadDate();
	
	/**
	 * 检查API接口
	 */
	public void checkApi();
	/**
	 * 发送邮件或短信
	 */
    public void sendMessage();
    
}
