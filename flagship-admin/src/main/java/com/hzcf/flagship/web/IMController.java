package com.hzcf.flagship.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.flagship.baseweb.BaseController;
import com.hzcf.flagship.im.api.IMUserAPI;
import com.hzcf.flagship.model.AppUser;
import com.hzcf.flagship.service.AppUserService;
import com.hzcf.flagship.service.CheckJobService;
import com.hzcf.flagship.service.CheckUserStatusJobService;
import com.hzcf.flagship.service.IMService;
import com.hzcf.flagship.service.RiskJobService;
import com.hzcf.flagship.util.RandomNumUtil;

import io.swagger.client.model.Nickname;
import io.swagger.client.model.RegisterUsers;
import io.swagger.client.model.User;


/**
 *<dl>
 *<dt>类名：IMController.java</dt>
 *<dd>描述: 即时通讯controller类</dd> 
 *<dd>创建时间： 2017年7月25日 下午3:48:52</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年7月25日 下午3:48:52    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Controller
@RequestMapping(value="/IM")
public class IMController extends BaseController{ 
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private IMUserAPI IMUserAPI;
	@Autowired
	private IMService iMService;
	@Autowired
	private CheckJobService checkJobService;
	@Autowired
	private RiskJobService riskJobService;
	@Autowired
	CheckUserStatusJobService checkUserStatusJobService;
	
	/**
	 * 为所有已存在的用户注册环信账号
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/initIMUsers")
	public String  initIMUsers(HttpServletRequest request) {
		try {
			List<AppUser> users = appUserService.findAllNormalAndForbiddenUsers();
			for (AppUser appUser : users) {
				//2.依次注册用户并修改昵称(如果IM列表中已有账号则跳过)
				Object imUser = IMUserAPI.getIMUserByUserName(appUser.getId()+"");
				if (imUser==null) {
					RegisterUsers registerUsers = new RegisterUsers();
					User user = new User();
					user.setUsername(appUser.getId()+"");
					String imPassword = RandomNumUtil.getRandomNum("10");
					user.setPassword(imPassword);
					registerUsers.add(user);
					Object result = IMUserAPI.createNewIMUserSingle(registerUsers);
					//如果注册成功,修改昵称,并修改user表中的im密码
					if (result!=null) {
						//修改用户表
						appUserService.updateImPassword(appUser.getId(), imPassword);
						//修改环信昵称
						Nickname nickname = new Nickname();
						nickname.setNickname(appUser.getName());
						IMUserAPI.modifyIMUserNickNameWithAdminToken(appUser.getId()+"", nickname);
					}
				}
			}
			logger.info("初始化注册IM成功");
			return "初始化注册IM成功";
		} catch (Exception e) {
			logger.error("初始化注册IM成功");
			return "初始化注册IM失败";
		}
		
	}
	
	/**
	 * 初始化用户好友列表
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/initUserRelation")
	public String  initUserRelation(HttpServletRequest request) {
		try {
			iMService.insertInitUserRelation();
			logger.info("初始化用户关系列表成功");
			return "初始化用户关系列表成功";
		} catch (Exception e) {
			logger.info("初始化用户关系列表失败");
			return "初始化用户关系列表失败";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/testJob")
	public String  testJob(HttpServletRequest request) {
		/*try {
			iMService.insertInitUserRelation();
			logger.info("初始化用户关系列表成功");
			return "初始化用户关系列表成功";
		} catch (Exception e) {
			logger.info("初始化用户关系列表失败");
			return "初始化用户关系列表失败";
		}*/
		/*checkJobService.checkBIDate();
		checkJobService.checkUploadDate();*/
		checkJobService.checkData();
		checkJobService.sendMessage();
		riskJobService.riskInsertWarningLevel();
		riskJobService.riskUpdatePermission();
		checkUserStatusJobService.checkAppUser();
		checkUserStatusJobService.checkAdminUser();
		return "success";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/runRiskJob")
	public String  runRiskJob(HttpServletRequest request) {
		riskJobService.riskInsertWarningLevel();
		return "success";
	}
}
