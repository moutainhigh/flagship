package com.hzcf.flagship.service;

import java.util.List;
import java.util.Map;

import com.hzcf.flagship.form.PerformanceParm;
import com.hzcf.flagship.model.Image;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.vo.ReturnMsgData;

public interface UserService {

	public void dataUpload(@SuppressWarnings("rawtypes") List resultList);

	public PageModel findAllByPage(Map<String, Object> paramsCondition);
	/**
	 * 用户登录
	 * @param telephone
	 * @param password
	 * @param osInfo
	 * @param allowTimes
	 * @return
	 */
	public ReturnMsgData login(String telephone, String password, String osInfo, String allowTimes);
	/**
	 * 获取短信验证码
	 * @param params
	 * @return
	 */
	public ReturnMsgData getPhoneCode(Map<String, String> params);
	/**
	 * 校验验证码
	 * @param telephone
	 * @param verificationCode
	 * @return
	 */
	public ReturnMsgData checkPhoneCode(String telephone, String verificationCode);
	/**
	 * 设置新密码
	 * @param paramsCode
	 * @param telephone
	 * @param verificationCode
	 * @param password
	 * @param performanceParm
	 * @return
	 */
	public ReturnMsgData setNewPassword(String telephone, String verificationCode, String password,
			PerformanceParm performanceParm);
	/**
	 * 设置手势密码前密码校验
	 * @param telephone
	 * @param password
	 * @return
	 */
	public ReturnMsgData setHandSign(String telephone, String password);
	/**
	 * ios 用户注册
	 * @param performanceParm
	 * @return
	 */
	public ReturnMsgData addUser(PerformanceParm performanceParm);
	
	/**
	 * 修改用户头像
	 * @param id
	 * @param image
	 */
	public int updateHeadImage(Integer id,Image image);
	/**
	 * 获取个人详情
	 * @param telephone
	 * @return
	 */
	public ReturnMsgData getUserInfoById(String userId);
	/**
	 * 获取用户联系人
	 * @param telephone
	 * @return
	 */
	public ReturnMsgData getUserContactsById(String userId);

}
