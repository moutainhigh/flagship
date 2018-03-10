package com.hzcf.flagship.service;

/**
 *<dl>
 *<dt>类名：AppPushMessageService.java</dt>
 *<dd>描述: 推送消息记录服务类</dd> 
 *<dd>创建时间： 2017年6月15日 上午10:39:27</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年6月15日 上午10:39:27    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface AppPushMessageService {
	/**
	 * 根据手机号,插入推送记录(aliescast)
	 * @param mobilestr 格式:mobile1,mobile2...
	 * @param title
	 * @param content
	 */
	public void insertPushMessageByMobiles(String mobilestr,String title,String content);
	
	/**
	 * 插入广播的推送消息
	 * @param title
	 * @param content
	 */
	public void insertBroadcastPushMessage(String title,String content);
	
	/**
	 * 清除该用户所有的消息记录
	 * @param userId
	 */
	public void deleteAllMessagesByUserId(int userId);
	
	/**
	 * 删除指定用户的某条消息记录
	 */
	public void deleteMessageByMessageId(int userId,long messageId);
}
