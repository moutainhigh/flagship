package com.hzcf.flagship.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.hzcf.flagship.baseweb.BaseController;
import com.hzcf.flagship.cache.CacheManager;
import com.hzcf.flagship.enmu.UserConstants;
import com.hzcf.flagship.form.PerformanceParm;
import com.hzcf.flagship.model.Image;
import com.hzcf.flagship.service.UserService;
import com.hzcf.flagship.util.GetSystemUtil;
import com.hzcf.flagship.util.JedisClientUtil;
import com.hzcf.flagship.util.PropertyUtil;
import com.hzcf.flagship.util.RandomNumUtil;
import com.hzcf.flagship.util.StringUtil;
import com.hzcf.flagship.vo.ReturnMsgData;

/**
 * 
 *<dl>
 *<dt>类名：UserController.java</dt>
 *<dd>描述: ~用户模块</dd> 
 *<dd>创建时间： 2017年5月22日 下午9:18:45</dd>
 *<dd>创建人： GuoDong</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月22日 下午9:18:45    GuoDong       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private UserService userServiec;
	@Autowired
	private JedisClientUtil jedisClient;
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMsgData login(@RequestBody PerformanceParm performanceParm, HttpServletRequest request){
		logger.info("------------------进入用户登录接口--------------------");
		String telephone = performanceParm.getTelephone();
		String password = performanceParm.getPassword();
		String osInfo = GetSystemUtil.getOsAndBrowserInfo(request);
		try {
			if(StringUtil.isBlank(telephone)){	
				return new ReturnMsgData("1000", "请输入手机号码");
			}
			if(StringUtil.isBlank(password)){
				return new ReturnMsgData("1002", "请输入密码");
			}
			String allowTimes = CacheManager.getGeneralText("allowTimes");
			ReturnMsgData returnMsgData = userServiec.login(telephone,password,osInfo,allowTimes);
			return returnMsgData;
		} catch (Exception e) {
			logger.info("登录接口调用发生异常,请求参数为：" + JSON.toJSONString(performanceParm));
			logger.error(e.getMessage());
			return new ReturnMsgData("9999", "调用失败");
		}
	}
	/**
	 * 获取手机验证码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getPhoneCode",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMsgData getPhoneCode(@RequestBody PerformanceParm performanceParm){
		logger.info("------------------进入获取手机验证码接口--------------------");
		String telephone = performanceParm.getTelephone();
		String paramsCode = performanceParm.getParamsCode();
		try {
			if(StringUtil.isBlank(telephone)){	
				return new ReturnMsgData("1000", "请输入手机号码");
			}
			Map<String, String> params = new HashMap<>();
			sendMessageParams(params);
			params.put("telephone", telephone);
			params.put("paramsCode", paramsCode);
			ReturnMsgData returnMsgData = userServiec.getPhoneCode(params);
			return returnMsgData;
		} catch (Exception e) {
			logger.info("短信验证码接口调用发生异常,请求参数为：" + JSON.toJSONString(performanceParm));
			logger.error(e.getMessage());
			return new ReturnMsgData("9999", "调用失败");
		}
	}
	/**
	 * 加载短信配置
	 * @param params
	 */
	private void sendMessageParams(Map<String, String> params) {
		params.put("systemSourceId", CacheManager.getGeneralText("systemSourceId"));
		params.put("key", CacheManager.getGeneralText("key"));
		params.put("sendmessage_path", CacheManager.getGeneralText("sendmessage_path"));
		params.put("allowSendMessageTimes", CacheManager.getGeneralText("allowTimes"));
		params.put("beforeMessage", CacheManager.getGeneralText("beforeMessage"));
		params.put("afterMessage", CacheManager.getGeneralText("afterMessage"));
	}
	
	/**
	 * 校验验证码是否正确
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/checkPhoneCode",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMsgData checkPhoneCode(@RequestBody PerformanceParm performanceParm){
		logger.info("------------------进入校验验证码是否正确接口--------------------");
		String telephone = performanceParm.getTelephone();
		String verificationCode = performanceParm.getVerificationCode();
		try {
			if(StringUtil.isBlank(telephone)){	
				return new ReturnMsgData("1000", "请输入手机号码");
			}
			if(StringUtil.isBlank(verificationCode)){
				return new ReturnMsgData("1002", "请输入验证码");
			}
			ReturnMsgData returnMsgData = userServiec.checkPhoneCode(telephone,verificationCode);
			return returnMsgData;
		} catch (Exception e) {
			logger.info("校验验证码接口调用发生异常,请求参数为：" + JSON.toJSONString(performanceParm));
			logger.error(e.getMessage());
			return new ReturnMsgData("9999", "调用失败");
		}
	}
	
	/**
	 * 设置新密码（修改密码和忘记密码）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/setNewPassword",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMsgData setNewPassword(@RequestBody PerformanceParm performanceParm){
		logger.info("------------------进入设置新密码接口--------------------");
		String paramsCode = performanceParm.getParamsCode();
		String telephone = performanceParm.getTelephone();
		String verificationCode = performanceParm.getVerificationCode();
		String password = performanceParm.getPassword();
		try {
			if(StringUtil.isBlank(paramsCode)){
				return new ReturnMsgData("1004", "请求码为空");
			}
			if("A1002".equals(paramsCode)){	//修改密码
				if(!checkIsLogin(performanceParm)){
					return new ReturnMsgData("2001", "用户未登录");
				}
			}
			if(StringUtil.isBlank(telephone)){
				return new ReturnMsgData("1000", "请输入手机号码");
			}
			if(StringUtil.isBlank(verificationCode)){
				return new ReturnMsgData("1001", "请输入验证码");
			}
			if(StringUtil.isBlank(password)){
				return new ReturnMsgData("1003", "请输入密码");
			}
			ReturnMsgData returnMsgData = userServiec.setNewPassword(telephone,verificationCode,password,performanceParm);
			return returnMsgData;
		} catch (Exception e) {
			logger.info("设置密码接口调用发生异常,请求参数为：" + JSON.toJSONString(performanceParm));
			logger.error(e.getMessage());
			return new ReturnMsgData("9999", "调用失败");
		}
	}
	
	
	/**
	 * 设置手势密码前密码校验
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="/setHandSign",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMsgData setHandSign(@RequestBody PerformanceParm performanceParm){
		logger.info("------------------进入设置手势密码前密码校验接口--------------------");
		String telephone =performanceParm.getTelephone();
		String password = performanceParm.getPassword();
		String token = performanceParm.getToken();
		try {
			if(StringUtil.isBlank(telephone)){
				return new ReturnMsgData("1000", "请输入手机号码");
			}
			if(StringUtil.isBlank(password)){
				return new ReturnMsgData("1001", "请输入密码");
			}
			String redisToken = jedisClient.get(UserConstants.USER_TOKEN + token);
			if(StringUtil.isBlank(redisToken)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			ReturnMsgData returnMsgData = userServiec.setHandSign(telephone,password);
			return returnMsgData;
		} catch (Exception e) {
			logger.info("设置手势密码前密码校验接口调用发生异常,请求参数为：" + JSON.toJSONString(performanceParm));
			logger.error(e.getMessage());
			return new ReturnMsgData("9999", "调用失败");
		}
	}
	
	
	/**
	 * 用户注册
	 * @param performanceParm
	 * @return
	 */
	
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMsgData addUser(@RequestBody PerformanceParm performanceParm){
		logger.info("------------------进入用户注册接口--------------------");
		try {
			if(StringUtil.isBlank(performanceParm.getTelephone())){
				return new ReturnMsgData("1000", "请输入手机号码");
			}
			if(StringUtil.isBlank(performanceParm.getVerificationCode())){
				return new ReturnMsgData("1001", "请输入验证码");
			}
			if(StringUtil.isBlank(performanceParm.getPassword())){
				return new ReturnMsgData("1003", "请输入密码");
			}
			return userServiec.addUser(performanceParm);
			
		} catch (Exception e) {
			logger.info("用户注册接口调用发生异常,请求参数为：" + JSON.toJSONString(performanceParm));
			logger.error(e.getMessage());
			return new ReturnMsgData("9999", "调用失败");
		}
	}
	
	/**
	 * 修改用户头像
	 * @param performanceParm
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/uploadHeadImage",method=RequestMethod.POST)  
    @ResponseBody
    public ReturnMsgData uploadHeadImage(@RequestParam(value = "headImage") CommonsMultipartFile headImage,@RequestParam(value = "token") String token) throws IOException{  
		logger.info("-------------上传图片----------------");
		try {
			//验证是否登录
			PerformanceParm performanceParm = new PerformanceParm();
			performanceParm.setToken(token);
			if (!checkIsLogin(performanceParm)) {
				return new ReturnMsgData("2001", "用户未登录", null);
			}
			//原始文件名称
			String pictureFileName =  headImage.getOriginalFilename();
			//新文件名称
			String newFileName = System.currentTimeMillis()+""+RandomNumUtil.getRandomNum("6");
			if (pictureFileName.lastIndexOf(".") != -1){
				newFileName = newFileName+pictureFileName.substring(pictureFileName.lastIndexOf("."));
			}
			//上传图片
			Image image = new Image();
			image.setName(newFileName);
			image.setImage(headImage.getBytes());
			image.setCreateTime(new Date());
			//通过token获得用户id
			String userId = jedisClient.get(UserConstants.USER_TOKEN+performanceParm.getToken());
			int result = userServiec.updateHeadImage(Integer.parseInt(userId), image);
			if (result==1) {
				logger.info("用户id为"+userId+"的用户修改头像成功");
				Map<String, Object> data = new HashMap<>();
				data.put("headImgUrl", PropertyUtil.getInfo("imgUrl")+newFileName);
				return new ReturnMsgData("0000", "调用成功", data);
			}else {
				logger.info("用户id为"+userId+"的用户修改头像失败");
				return new ReturnMsgData("0000", "调用成功", null);
			}
		} catch (Exception e) {
			return new ReturnMsgData("9999", "调用失败", null);
		}
    	
    } 
	/**
	 * 获取个人详情
	 * @param performanceParm
	 * @return
	 */
	@RequestMapping(value="/getUserInfo",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMsgData getUserInfo(@RequestBody PerformanceParm performanceParm){
		logger.info("------------------进入获取用户详细信息接口--------------------");
		try {
			String userId = jedisClient.get(UserConstants.USER_TOKEN + performanceParm.getToken());
			if(StringUtil.isBlank(userId)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			return userServiec.getUserInfoById(userId);
		} catch (Exception e) {
			logger.info("获取用户详细信息接口调用发生异常,请求参数为：" + JSON.toJSONString(performanceParm));
			logger.error(e.getMessage());
			return new ReturnMsgData("9999", "调用失败");
		}
	}
	
	/**
	 * 获取联系人列表
	 * @param performanceParm
	 * @return
	 */
	@RequestMapping(value="/getUserContacts",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMsgData getUserContacts(@RequestBody PerformanceParm performanceParm){
		logger.info("------------------进入获取联系人接口--------------------");
		try {
			String userId = jedisClient.get(UserConstants.USER_TOKEN + performanceParm.getToken());
			if(StringUtil.isBlank(userId)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			ReturnMsgData returnMsgData = userServiec.getUserContactsById(userId);
			return returnMsgData;
		} catch (Exception e) {
			logger.info("获取联系人接口调用发生异常,请求参数为：" + JSON.toJSONString(performanceParm));
			logger.error(e.getMessage());
			return new ReturnMsgData("9999", "调用失败");
		}
	}
	
}
