package com.hzcf.flagship.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.AppPushMessageMapper;
import com.hzcf.flagship.dao.AppUserMapper;
import com.hzcf.flagship.model.AppPushMessage;
import com.hzcf.flagship.model.AppPushMessageExample;
import com.hzcf.flagship.model.AppUser;
import com.hzcf.flagship.model.AppUserExample;
import com.hzcf.flagship.model.AppUserExample.Criteria;
import com.hzcf.flagship.service.AppPushMessageService;
import com.hzcf.flagship.util.StringUtil;


/**
 *<dl>
 *<dt>类名：AppPushMessageServiceImpl.java</dt>
 *<dd>描述: 推送消息记录服务实现类</dd> 
 *<dd>创建时间： 2017年6月15日 上午10:37:01</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年6月15日 上午10:37:01    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Service("appPushMessageService")
public class AppPushMessageServiceImpl implements AppPushMessageService{
	@Autowired
	AppPushMessageMapper appPushMessageMapper;
	@Autowired
	private AppUserMapper appUserMapper;
	
	/**
	 * 根据手机号,插入推送记录(aliescast)
	 * @param mobilestr 格式:mobile1,mobile2...
	 * @param title
	 * @param content
	 */
	public void insertPushMessageByMobiles(String mobilestr,String title,String content){
		if (StringUtil.notBlank(mobilestr)) {
			String[] mobiles = mobilestr.split(",");
			for (String mobile : mobiles) {
				AppUser appUser = appUserMapper.selectByTelephone(mobile);
				if (appUser.getId()!=null) {
					AppPushMessage appPushMessage = new AppPushMessage();
					appPushMessage.setUserId(appUser.getId());
					appPushMessage.setType("aliescast");
					appPushMessage.setTitle(title);
					appPushMessage.setContent(content);
					appPushMessage.setSendTime(new Date());
					//appPushMessageMapper.insertPushMessage(appPushMessage);
					appPushMessageMapper.insertSelective(appPushMessage);
				}
			}
		}
	}
	
	
	/**
	 * 插入广播的推送消息
	 */
	public void insertBroadcastPushMessage(String title,String content){
		//查询所有状态为1(正常非ios注册)的用户
		AppUserExample appUserExample = new AppUserExample();
		Criteria appUserCriteria = appUserExample.createCriteria();
		appUserCriteria.andStatusEqualTo("1");
		List<AppUser> allUsers = appUserMapper.selectByExample(appUserExample);
		ArrayList<AppPushMessage> messages = new ArrayList<AppPushMessage>();
		for (AppUser appUser : allUsers) {
			AppPushMessage appPushMessage = new AppPushMessage();
			//appPushMessage.setId(null);
			appPushMessage.setUserId(appUser.getId());
			appPushMessage.setType("broadcast");
			appPushMessage.setTitle(title);
			appPushMessage.setContent(content);
			appPushMessage.setSendTime(new Date());
			messages.add(appPushMessage);
		}
		
		//appPushMessageMapper.insertPushMessage(appPushMessage);
		//appPushMessageMapper.insertSelective(appPushMessage);
		appPushMessageMapper.insertMessages(messages);
	}
	
	/**
	 * 根据用户id查询用户历史推送记录
	 * @param userId
	 * @return
	 */
	public List< Map<String, String>> getMessagesByUserId(int userId){
		return appPushMessageMapper.getMessagesByUserId(userId);
	}


	/**
	 * 清除用户所有的消息记录
	 */
	@Override
	public void deleteAllMessagesByUserId(int userId) {
		AppPushMessage appPushMessage = new AppPushMessage();
		appPushMessage.setType("del");
		AppPushMessageExample appPushMessageExample = new AppPushMessageExample();
		com.hzcf.flagship.model.AppPushMessageExample.Criteria appPushMessageCriteria = appPushMessageExample.createCriteria();
		appPushMessageCriteria.andUserIdEqualTo(userId);
		appPushMessageMapper.updateByExampleSelective(appPushMessage, appPushMessageExample);
		
	}

	/**
	 * 删除指定用户的某条消息记录
	 */
	@Override
	public void deleteMessageByMessageId(int userId, long messageId) {
		AppPushMessage appPushMessage = new AppPushMessage();
		appPushMessage.setType("del");
		AppPushMessageExample appPushMessageExample = new AppPushMessageExample();
		com.hzcf.flagship.model.AppPushMessageExample.Criteria appPushMessageCriteria = appPushMessageExample.createCriteria();
		appPushMessageCriteria.andUserIdEqualTo(userId);
		appPushMessageCriteria.andIdEqualTo(messageId);
		appPushMessageMapper.updateByExampleSelective(appPushMessage, appPushMessageExample);
	}
}
