package com.hzcf.flagship.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.AppLoginLogMapper;
import com.hzcf.flagship.dao.AppPermissionMapper;
import com.hzcf.flagship.dao.AppUserMapper;
import com.hzcf.flagship.dao.AppUserRelationshipsMapper;
import com.hzcf.flagship.dao.AppUserSortMapper;
import com.hzcf.flagship.dao.ImageMapper;
import com.hzcf.flagship.dao.PropertiesMapper;
import com.hzcf.flagship.dao.UserMapper;
import com.hzcf.flagship.enmu.UserConstants;
import com.hzcf.flagship.form.PerformanceParm;
import com.hzcf.flagship.model.AppPermission;
import com.hzcf.flagship.model.AppUser;
import com.hzcf.flagship.model.AppUserExample;
import com.hzcf.flagship.model.AppUserExample.Criteria;
import com.hzcf.flagship.model.AppUserRelationships;
import com.hzcf.flagship.model.AppUserRelationshipsExample;
import com.hzcf.flagship.model.Image;
import com.hzcf.flagship.model.ImageExample;
import com.hzcf.flagship.model.Properties;
import com.hzcf.flagship.model.PropertiesExample;
import com.hzcf.flagship.service.UserService;
import com.hzcf.flagship.util.DateTimeUtil;
import com.hzcf.flagship.util.JedisClientUtil;
import com.hzcf.flagship.util.MathRandom;
import com.hzcf.flagship.util.NewSendMessageUtil;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.PropertyUtil;
import com.hzcf.flagship.util.StringUtil;
import com.hzcf.flagship.util.UUIDUtil;
import com.hzcf.flagship.vo.ReturnMsgData;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JedisClientUtil jedisClient;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AppUserMapper appUserMapper;
	@Autowired
	private AppPermissionMapper appPermissionMapper;
	@Autowired
	private AppLoginLogMapper appLoginLogMapper;
	@Autowired
	private ImageMapper imageMapper;
	@Autowired
	private AppUserSortMapper appUserSortMapper;
	@Autowired
	private AppUserRelationshipsMapper appUserRelationshipsMapper;
	@Autowired
	private PropertiesMapper propertiesMapper;
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public void dataUpload(List resultList) {
		userMapper.dataUpload(resultList);
	}

	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = userMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = userMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}
	/**
	 * 用户登录
	 */
	@Override
	public ReturnMsgData login(String telephone, String password, String osInfo, String allowTimes) {
			if(!telephone.matches(UserConstants.MOBILE_PATTERN)){
				return new ReturnMsgData("1001", "请输入正确的手机号");
			}
			if(!checkLoginErrorTimes(telephone, allowTimes)){
				return new ReturnMsgData("1004", "连续5次登录失败,请30分钟后再试");
			}
			if(noThisTelephone(telephone)){
				return new ReturnMsgData("1005", "手机号不存在，请重新输入");
			}
			AppUser appUser = findUserByTelephoneAndPsw(telephone,password);
			if(appUser == null){	//手机号或密码有误
				addLoginErrorTimes(telephone);	//增加输入错误记录
				return new ReturnMsgData("1003", "手机号或密码错误，请重新输入");
			}else{
				List<String> limitsList = new ArrayList<>();
				Integer id = appUser.getId();
				String status = appUser.getStatus();
				if("4".equals(status)){
					limitsList.add("iosReg");
				}else{
					limitsList = findUserPermissionByUserID(id, true);
				}
				//删除之前redis中的该用户的token记录
				jedisClient.delByKeyprefixAndValue(UserConstants.USER_TOKEN, id+"");
				//生成token
				String token = UUIDUtil.getUUID();
				jedisClient.set(UserConstants.USER_TOKEN + token, id+"");
				jedisClient.expire(UserConstants.USER_TOKEN + token, UserConstants.REDIS_TOKEN_DELAY);
				//查询是否有联系人
				AppUserRelationshipsExample appUserRelationshipsExample = new AppUserRelationshipsExample();
				com.hzcf.flagship.model.AppUserRelationshipsExample.Criteria createCriteria = appUserRelationshipsExample.createCriteria();
				createCriteria.andFromUserIdEqualTo(appUser.getId());
				createCriteria.andStatusEqualTo("1");
				List<AppUserRelationships> userList = appUserRelationshipsMapper.selectByExample(appUserRelationshipsExample);
				String isNotContacts = "N";
				if(null != userList && userList.size()>0){
					isNotContacts = "Y";
				}
				Map<String, Object> data = new HashMap<>();
				data.put("token", token);
				data.put("name", appUser.getName());
				data.put("imPassword", appUser.getImPassword());
				data.put("userId", appUser.getId().toString());
				data.put("limits", limitsList);
				data.put("isNotContacts", isNotContacts);
				data.put("headImgUrl", PropertyUtil.getInfo("imgUrl")+ appUser.getHeadImageName());
				addLoginLog(id,osInfo);
				return new ReturnMsgData("0000", "调用成功", data);
			}
	}
	/**
	 * 添加用户登录记录
	 * @param userId
	 * @param osInfo
	 */
	private void addLoginLog(Integer userId, String osInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("loginWay", osInfo);
		params.put("loginTime", DateTimeUtil.getTimeNormalString(new Date()));
		appLoginLogMapper.insertLoginLog(params);
		
	}

	/**
	 * 根据用户查询所拥有的权限
	 * @param userId
	 * @return
	 */
	private List<String> findUserPermissionByUserID(Integer userId, boolean isAll) {
		List<AppPermission> permissionList;
		if(isAll){
			permissionList = appPermissionMapper.selectAllPermissionById(userId);
		}else{
			permissionList = appPermissionMapper.selectPermissionById(userId);
		}
		List<String> limitsList = new ArrayList<>();
		for (AppPermission appPermission : permissionList) {
			limitsList.add(appPermission.getName());
		}
		return limitsList;
	}

	/**
	 * 根据手机号和密码查询用户
	 * @param telephone
	 * @param password
	 * @return
	 */
	private AppUser findUserByTelephoneAndPsw(String telephone, String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("telephone", telephone);
		params.put("password", password);
		AppUser appUser= appUserMapper.selectByTelephoneAndPsw(params);
		return appUser;
	}

	/**
	 * 检查用户是否连续5次输入密码错误
	 * @param telephone
	 * @return
	 */
	private boolean checkLoginErrorTimes(String telephone, String allowTimes) {
		String loginErrorTimesStr = jedisClient.get(UserConstants.LOGIN_ERROR_TIMES + telephone);
		int allowLoginTimes = Integer.parseInt(allowTimes);
		if(StringUtil.notBlank(loginErrorTimesStr)){	//之前已有错误记录
			int loginErrorTimes = Integer.parseInt(loginErrorTimesStr);
			if(loginErrorTimes >= allowLoginTimes ){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 新增用户连续输入密码错误记录
	 * @param telephone
	 * @return
	 */
	private void addLoginErrorTimes(String telephone) {
		String loginErrorTimesStr = jedisClient.get(UserConstants.LOGIN_ERROR_TIMES + telephone);
		if(StringUtil.notBlank(loginErrorTimesStr)){	//之前已有错误记录
			int loginErrorTimes = Integer.parseInt(loginErrorTimesStr);
			jedisClient.incr(UserConstants.LOGIN_ERROR_TIMES + telephone);
			if(loginErrorTimes == 4 ){
				jedisClient.expire(UserConstants.LOGIN_ERROR_TIMES + telephone, 60*30);
			}
		}else{
			jedisClient.set(UserConstants.LOGIN_ERROR_TIMES + telephone, "1");
			jedisClient.expire(UserConstants.LOGIN_ERROR_TIMES + telephone, 60*30);
		}
	}
	/**
	 * 获取手机验证码
	 */
	@Override
	public ReturnMsgData getPhoneCode(Map<String, String> params) {
		String telephone = params.get("telephone");
		String paramsCode = params.get("paramsCode");
		String allowSendMessageTimes = params.get("allowSendMessageTimes");
		if(!telephone.matches(UserConstants.MOBILE_PATTERN)){
			return new ReturnMsgData("1001", "请输入正确的手机号码");
		}
		if("V1001".equals(paramsCode)){	//ios 注册判断
			List<AppUser> appUsers = appUserMapper.selectAppUserByTelephone(telephone);
			if(null != appUsers && appUsers.size() > 0 ){
				AppUser appUser = appUsers.get(0);
				String status = appUser.getStatus();
				if(!"3".equals(status) && !"6".equals(status)){
					return new ReturnMsgData("1004", "该手机号已注册");
				}
			}
		}
		if( !"V1001".equals(paramsCode) && noThisTelephone(telephone)){
			return new ReturnMsgData("1003", "手机号不存在，请重新输入");
		}
		if(telephoneThenTimes(telephone,allowSendMessageTimes)){
			return new ReturnMsgData("1002", "今日获取验证码已达五次，不能再次获取，请明天再试");
		}
		String message = String.valueOf(MathRandom.getRandomOfSixDigit());
		params.put("message", params.get("beforeMessage") + message + params.get("afterMessage"));
		boolean flag = NewSendMessageUtil.sendSmsProduct(params);
		if(flag){
			addGetCodeTimes(telephone);
			jedisClient.set(UserConstants.USER_VERIFICATIONCODE + telephone, message);
			jedisClient.expire(UserConstants.USER_VERIFICATIONCODE + telephone, UserConstants.REDIS_VERIFICATIONCODE_TIME);
			return new ReturnMsgData("0000", "调用成功");
		}
		return new ReturnMsgData("9999", "调用失败");
		
		
	}
	
	/**
	 * 根据手机号查找用户
	 * 无此用户时返回 true
	 * @param telephone
	 * @return
	 */
	private boolean noThisTelephone(String telephone) {
		AppUserExample appUserExample = new AppUserExample();
		Criteria createCriteria = appUserExample.createCriteria();
		createCriteria.andMobileEqualTo(telephone);
		List  list = new ArrayList<>();
		list.add(1);
		list.add(4);
		createCriteria.andStatusIn(list);
		List<AppUser> userList =  appUserMapper.selectByExample(appUserExample);
		if(userList.size() > 0 ){
			return false;
		}
		return true;
	}

	/**
	 * 判断当前手机号获取验证码是否已超过5次
	 * @param telephone
	 * @param allowSendMessageTimes 
	 * @return
	 */
	private boolean telephoneThenTimes(String telephone, String allowSendMessageTimes) {
		int times = Integer.parseInt(allowSendMessageTimes);
		String phoneTimes = jedisClient.get(UserConstants.GET_CODE_TIMES + telephone);
		if(StringUtil.notBlank(phoneTimes)){	//之前已有获取验证码的记录
			int getCodeTimes = Integer.parseInt(phoneTimes);
			if(getCodeTimes >= times){
				return true;
			}
		}
		return false;
	}
	
	/**
	 *新增手机号获取验证码的次数
	 * @param telephone
	 */
	private void addGetCodeTimes(String telephone) {
		int time = DateTimeUtil.getNowTimeToNewDay();
		String getCodeTimesStr = jedisClient.get(UserConstants.GET_CODE_TIMES + telephone);
		if(StringUtil.notBlank(getCodeTimesStr)){
			jedisClient.incr(UserConstants.GET_CODE_TIMES + telephone);
		}else{
			jedisClient.set(UserConstants.GET_CODE_TIMES + telephone, "1");
			jedisClient.expire(UserConstants.GET_CODE_TIMES + telephone, time);
		}
	}
	
	/**
	 *校验验证码是否正确
	 */
	@Override
	public ReturnMsgData checkPhoneCode(String telephone, String verificationCode) {
		if(!telephone.matches(UserConstants.MOBILE_PATTERN)){
			return new ReturnMsgData("1001", "请输入正确的手机号码");
		}
		if(verifyCode(telephone,verificationCode)){
			return new ReturnMsgData("0000", "调用成功");
		}
		return new ReturnMsgData("1003", "请输入正确的验证码");
	}
	
	/**
	 * 校验验证码
	 * @param telephone
	 * @param verificationCode
	 * @return
	 */
	private boolean verifyCode(String telephone, String verificationCode) {
		String code = jedisClient.get(UserConstants.USER_VERIFICATIONCODE + telephone);
		if(StringUtil.notBlank(code) && verificationCode.equals(code)){
			return true;
		}
		return false;
	}
	/**
	 * 设置新密码（修改密码和忘记密码）
	 * @param paramsCode
	 * @param telephone
	 * @param verificationCode
	 * @param password
	 * @param request
	 * @return
	 */
	@Override
	public ReturnMsgData setNewPassword(String telephone, String verificationCode, String password,
			PerformanceParm performanceParm) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(!verifyCode(telephone, verificationCode)){
			return new ReturnMsgData("1003", "请输入正确的验证码");
		}else{
			params.put("telephone", telephone);
			params.put("password", password);
			appUserMapper.updateByTelephone(params);
			return new ReturnMsgData("0000", "调用成功");
		}
	}

	/**
	 * 设置手势密码前密码校验
	 */
	@Override
	public ReturnMsgData setHandSign(String telephone, String password) {
		AppUser appUser = findUserByTelephoneAndPsw(telephone,password);
		if(appUser == null){	
			return new ReturnMsgData("1003", "手机号或密码有误");
		}
		return  new ReturnMsgData("0000", "调用成功");
	}
	
	/**
	 * ios 用户注册
	 */
	@Override
	public ReturnMsgData addUser(PerformanceParm performanceParm) {
		String telephone = performanceParm.getTelephone();
		String verificationCode = performanceParm.getVerificationCode();
		String password = performanceParm.getPassword();
		Image findDefaultImage = imageMapper.findDefaultImage();
		if(!verifyCode(telephone, verificationCode)){
			return new ReturnMsgData("1002", "请输入正确的验证码");
		}else{
			PropertiesExample propertiesExample = new PropertiesExample();
			com.hzcf.flagship.model.PropertiesExample.Criteria createCriteria = propertiesExample.createCriteria();
			createCriteria.andPropertyNameEqualTo("isShowRegister");
			List<Properties> selectByExample = propertiesMapper.selectByExample(propertiesExample);
			if(null != selectByExample && selectByExample.size()>0){
				Properties properties = selectByExample.get(0);
				if(null != properties.getPropertyStringValue() && "true" .equals(properties.getPropertyStringValue())){
					appUserMapper.deleteDelUserByMobile(telephone);
					AppUser appUser = new AppUser();
					appUser.setName("IOSReg");
					appUser.setUsername("IOSReg");
					appUser.setMobile(telephone);
					appUser.setPassword(password);
					appUser.setHeadImageName(findDefaultImage.getName());
					appUser.setStatus("4");
					appUser.setCreatorId(0);
					appUser.setCreateTime(new Date());
					appUserMapper.insert(appUser);
					return new ReturnMsgData("0000", "注册成功");
				}
			}
		}
		return new ReturnMsgData("1004", "注册失败");
	}
	/**
	 * 获取用户详情
	 */
	@Override
	public ReturnMsgData getUserInfoById(String userId) {
		/**
		 * 1.获取详情
		 * 2.查询权限
		 */
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> userInfo = appUserMapper.getUserInfoById(userId);
		List<String> limitsList = new ArrayList<>();
		if("4".equals(userInfo.get("status"))){	//ios注册
			limitsList.add("iosReg");
		}else{
			limitsList = findUserPermissionByUserID(Integer.parseInt(userId), true);
		}
		String headImgUrl = PropertyUtil.getInfo("imgUrl")+(String) userInfo.get("headImgUrl");
		userInfo.put("headImgUrl", headImgUrl);
		data.put("userInfo", userInfo);
		data.put("limits", limitsList);
		return new ReturnMsgData("0000", "调用成功", data);
	}
	/**
	 * 获取联系人列表
	 */
	@Override
	public ReturnMsgData getUserContactsById(String userId) {
		/**
		 * 1.先查出该用户id关联的联系人有哪些类别
		 * 2.根据类别分类查询联系人
		 */
		Map<String, Object> data = new HashMap<>();
		ArrayList<Object> arrayList = new ArrayList<>();
		List<Map<String, Object>> sortList = appUserSortMapper.selectByUserId(userId);
		if(null != sortList && sortList.size()>0){
			for (Map<String, Object> map : sortList) {
				Map<String, Object> classMap = new HashMap<String, Object>();
				classMap.put("className", map.get("name"));
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sortId", map.get("id"));
				params.put("userId", userId);
				List<Map<String, Object>> usersList = appUserMapper.selectAppUserByIdAndSortId(params);
				for (Map<String, Object> map2 : usersList) {
					String headImgUrl = PropertyUtil.getInfo("imgUrl")+(String) map2.get("headImgUrl");
					map2.put("headImgUrl", headImgUrl);
					map2.put("hxId", map2.get("hxId").toString());
				}
				classMap.put("contactsList", usersList);
				arrayList.add(classMap);
			}
		}
		data.put("userList", arrayList);
		return new ReturnMsgData("0000", "调用成功", data );
	}

	@Override
	public int updateHeadImage(Integer id, Image image) {
		//1.插入头像至image表中
		imageMapper.insert(image);
		//获取用户原头像名称,并
		//Control selectByPrimaryKey = userMapper.selectByPrimaryKey(id);
		AppUser user = appUserMapper.selectByPrimaryKey(id);
		
		
		//Map<String, Object> user = appUserMapper.findByUID(id);
		String oldImageName = null;
		if (user != null) {
			oldImageName = (String)user.getHeadImageName();
		}
		//2.将文件名存入user表中
		AppUser appUser = new AppUser();
		appUser.setId(id);
		appUser.setHeadImageName(image.getName());
		int result = appUserMapper.updateByPrimaryKeySelective(appUser);
		//1表示修改成功 0表示修改
		//如果修改成功,且用户的头像不是默认头像,则将原头像删除
		if (result==1 && oldImageName!=null && ! oldImageName.startsWith("defaultImage")) {
			deleteHeadImageByName(oldImageName);
		}
		return result;
	}

	/**
	 * 根据头像名称,删除图片
	 */
	private void deleteHeadImageByName(String picName){
		ImageExample imageExample = new ImageExample();
		com.hzcf.flagship.model.ImageExample.Criteria imageCriteria = imageExample.createCriteria();
		imageCriteria.andNameEqualTo(picName);
		imageMapper.deleteByExample(imageExample);
		/*if (result==1) {
			logger
		}*/
	}
}
