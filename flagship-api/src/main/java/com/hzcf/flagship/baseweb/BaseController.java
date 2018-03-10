/* 
 * Copyright (C) 2006-2014 亿谱汇投资管理（北京）有限公司.
 *
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 *
 * ============================================================
 *
 * FileName: BaseController.java 
 *
 * Created: [2014-11-21 下午01:54:57] by ydw 
 *
 * $Id$
 * 
 * $Revision$
 *
 * $Author$
 *
 * $Date$
 *
 * ============================================================ 
 * 
 * ProjectName: sping-mvc 
 * 
 * Description: 
 * 
 * ==========================================================*/

package com.hzcf.flagship.baseweb;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.hzcf.flagship.constant.Constants;
import com.hzcf.flagship.dao.FinanceDailyDistrictMapper;
import com.hzcf.flagship.dao.FinanceDailyOrgMapper;
import com.hzcf.flagship.dao.FinanceDailyPerformanceMapper;
import com.hzcf.flagship.dao.MoneymgrAccumuPerformanceMapper;
import com.hzcf.flagship.dao.MoneymgrDailyPerformanceMapper;
import com.hzcf.flagship.dao.MoneymgrDistrictAccumuPerformanceMapper;
import com.hzcf.flagship.dao.MoneymgrOrgAccumuPerformanceMapper;
import com.hzcf.flagship.enmu.UserConstants;
import com.hzcf.flagship.form.PerformanceParm;
import com.hzcf.flagship.model.AppPermission;
import com.hzcf.flagship.model.Employee;
import com.hzcf.flagship.model.FinanceDailyDistrict;
import com.hzcf.flagship.model.FinanceDailyDistrictExample;
import com.hzcf.flagship.model.FinanceDailyOrg;
import com.hzcf.flagship.model.FinanceDailyOrgExample;
import com.hzcf.flagship.model.FinanceDailyPerformance;
import com.hzcf.flagship.model.FinanceDailyPerformanceExample;
import com.hzcf.flagship.model.MoneymgrAccumuPerformance;
import com.hzcf.flagship.model.MoneymgrAccumuPerformanceExample;
import com.hzcf.flagship.model.MoneymgrDailyPerformance;
import com.hzcf.flagship.model.MoneymgrDailyPerformanceExample;
import com.hzcf.flagship.model.MoneymgrDailyPerformanceExample.Criteria;
import com.hzcf.flagship.model.MoneymgrDistrictAccumuPerformance;
import com.hzcf.flagship.model.MoneymgrDistrictAccumuPerformanceExample;
import com.hzcf.flagship.model.MoneymgrOrgAccumuPerformance;
import com.hzcf.flagship.model.MoneymgrOrgAccumuPerformanceExample;
import com.hzcf.flagship.service.ApiFinanceService;
import com.hzcf.flagship.service.AppPermissionService;
import com.hzcf.flagship.util.DateTimeUtil;
import com.hzcf.flagship.util.JedisClientUtil;
import com.hzcf.flagship.util.PropertyUtil;
import com.hzcf.flagship.util.StringUtil;

/** 
 *
 * Description: Controller的父类，用于封装一些公共的逻辑
 *
 * @author ydw
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-11-21    ydw       1.0        1.0 Version 
 * </pre>
 */

public class BaseController {
	
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private JedisClientUtil jedisClient;
	@Autowired
	private AppPermissionService appPermissionService;
	@Autowired
	private ApiFinanceService apiFinanceService;
	@Autowired
	private MoneymgrDailyPerformanceMapper moneymgrDailyPerformanceMapper;
	@Autowired
	private FinanceDailyPerformanceMapper financeDailyPerformanceMapper;
	@Autowired
	private FinanceDailyDistrictMapper financeDailyDistrictMapper;
	@Autowired
	private FinanceDailyOrgMapper financeDailyOrgMapper;
	@Autowired
	private MoneymgrOrgAccumuPerformanceMapper moneymgrOrgAccumuPerformanceMapper;
	@Autowired
	private MoneymgrAccumuPerformanceMapper moneymgrAccumuPerformanceMapper;
	@Autowired
	private MoneymgrDistrictAccumuPerformanceMapper moneymgrDistrictAccumuPerformanceMapper;
	
	/**
	 * 
	 * Description: 
	 * 	注册一个自定义的处理类
	 * 	使用DateEditor来处理Date类型的日期转换
	 * 	即将Spring默认处理日期的类型换成DateEditor
	 * @param binder
	 * @return void
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-11-21 下午01:56:06
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		// 对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	/**
	 * 
	 * Description: 提交表单、操作处理后显示提示信息
	 *
	 * @Author yubin
	 * @Create Date: 2013-5-7 上午10:22:06
	 */
	protected void showMessageAlert(String refreshTag,String messageCode,Model model){
		//refreshTag代表是不是右键刷新标志,当是右键刷新时，提示信息不能显示
		if(StringUtil.isBlank(refreshTag)){
			if (!StringUtil.isBlank(messageCode)) {
				// 读取提示信息
				model.addAttribute("messageCode", PropertyUtil.getMessageCodeInfo(messageCode));
			}
		}else {
			messageCode = "";//当是右键刷新时，提交标志置空
		}
	}
	
	/**
	 * 
	 * Description: 返回后台管理系统登录用户ENTITY
	 *
	 * @param 
	 * @return Employee
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-11-25 下午02:56:08
	 */
	protected Employee getSystemCurrentUser(HttpSession session){
		Employee employee = (Employee) session.getAttribute(Constants.SYSTEM_USER);
		return employee;
	}
	
	/**
	 * 
	 * Description: 从容器中获得附件的上传路径
	 *
	 * 
 
	 * @return String
	 * @throws 
	 * @Author ydw
	 * Create Date: 2014-11-25 下午03:04:58
	 */
	public String getAttachmentPath (HttpSession session) {
		return (String)session.getServletContext().getAttribute("attachmentPath");
	}
	
	/**
	 * 验证是否登录
	 * 是 :true 否：false
	 * @param:PerformanceParm
	 */
	public boolean  checkIsLogin(PerformanceParm PerformanceParm){
		String token = PerformanceParm.getToken();
		String tokenResult = jedisClient.get(UserConstants.USER_TOKEN+token);
		if (StringUtil.blank(tokenResult)) {
			return false;
		}
		jedisClient.expire(UserConstants.USER_TOKEN + token, UserConstants.REDIS_TOKEN_DELAY);
		return true;
	}
	
	/**
	 * 验证是否有访问权限
	 * 是 :true 否：false
	 * @param:PerformanceParm
	 * requestName:融资 或者 理财
	 */
	public boolean  checkIsPermission(PerformanceParm performanceParm,String requestName){
		String userId = jedisClient.get(UserConstants.USER_TOKEN+performanceParm.getToken());
		if (!StringUtil.blank(userId)) {
			List<AppPermission> list = appPermissionService.findPermissionByUserId(userId);
		   for (AppPermission appPermission : list) {
			   if(appPermission.getName().equals(requestName)){
				   return true;
			   }
		   }
		}
		return false;
	}
	
	/**
	 * 判断BI抽取理财日业绩是否正常
	 * @param dateString
	 * @return 数据抽取正常返回true,异常返回false
	 */
	public boolean checkBIMoneymgrDailyPerformance(String dateString){
		//String dateString = performanceParm.getDays();
		//理财日业绩
		MoneymgrDailyPerformanceExample moneymgrDailyPerformanceExample = new MoneymgrDailyPerformanceExample();
		Criteria performanceCriteria = moneymgrDailyPerformanceExample.createCriteria();
		performanceCriteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dateString));
		List<MoneymgrDailyPerformance> moneymgrDailyPerformances = moneymgrDailyPerformanceMapper.selectByExample(moneymgrDailyPerformanceExample);
		
		//如果当日的理财日业绩或者融资日业绩为空则说明BI抽取数据异常
		if (moneymgrDailyPerformances==null || moneymgrDailyPerformances.size()==0 ) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断BI抽取理财日累计数据是否正常
	 * @param dateString
	 * @return
	 */
	public boolean checkBIMoneymgrDailyAccumuPerformance(String dateString){
		MoneymgrAccumuPerformanceExample moneymgrAccumuPerformanceExample = new MoneymgrAccumuPerformanceExample();
		com.hzcf.flagship.model.MoneymgrAccumuPerformanceExample.Criteria moneymgrAccumuPerformanceCriteria = moneymgrAccumuPerformanceExample.createCriteria();
		moneymgrAccumuPerformanceCriteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dateString));
		List<MoneymgrAccumuPerformance> list = moneymgrAccumuPerformanceMapper.selectByExample(moneymgrAccumuPerformanceExample);
		if (list==null || list.size()==0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断BI抽取理财区域数据是否正常
	 */
	public boolean checkBIDailyDistrict(String dateString){
		MoneymgrDistrictAccumuPerformanceExample moneymgrDistrictAccumuPerformanceExample = new MoneymgrDistrictAccumuPerformanceExample();
		com.hzcf.flagship.model.MoneymgrDistrictAccumuPerformanceExample.Criteria moneymgrDistrictAccumuPerformanceCriteria = moneymgrDistrictAccumuPerformanceExample.createCriteria();
		moneymgrDistrictAccumuPerformanceCriteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dateString));
		List<MoneymgrDistrictAccumuPerformance> list = moneymgrDistrictAccumuPerformanceMapper.selectByExample(moneymgrDistrictAccumuPerformanceExample);
		if (list==null || list.size()==0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断BI抽取理财分机构数据是否成功
	 * @param dateString
	 * @return
	 */
	public boolean checkBIDailyOrg(String dateString){
		MoneymgrOrgAccumuPerformanceExample moneymgrOrgAccumuPerformanceExample = new MoneymgrOrgAccumuPerformanceExample();
		com.hzcf.flagship.model.MoneymgrOrgAccumuPerformanceExample.Criteria moneymgrOrgAccumuPerformanceCriteria = moneymgrOrgAccumuPerformanceExample.createCriteria();
		moneymgrOrgAccumuPerformanceCriteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dateString));
		List<MoneymgrOrgAccumuPerformance> list = moneymgrOrgAccumuPerformanceMapper.selectByExample(moneymgrOrgAccumuPerformanceExample);
		if (list==null || list.size()==0) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * 判断BI抽取融资日数据是否正常
	 * @param dateString
	 * @return 数据抽取正常返回true,异常返回false
	 */
	public boolean checkBIFinanceDailyPerformance(String dateString){
		//融资日业绩
		FinanceDailyPerformanceExample financeDailyPerformanceExample = new FinanceDailyPerformanceExample();
		com.hzcf.flagship.model.FinanceDailyPerformanceExample.Criteria financeDailyPerformanceCriteria = financeDailyPerformanceExample.createCriteria();
		financeDailyPerformanceCriteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dateString));
		List<FinanceDailyPerformance> financeDailyPerformances = financeDailyPerformanceMapper.selectByExample(financeDailyPerformanceExample);
		
		//如果当日的理财日业绩或者融资日业绩为空则说明BI抽取数据异常
		if (financeDailyPerformances==null || financeDailyPerformances.size()==0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断BI抽取融资分区域数据是否成功
	 */
	public boolean checkBIFinanceDailyDistrict(String dateString){
		FinanceDailyDistrictExample financeDailyDistrictExample = new FinanceDailyDistrictExample();
		com.hzcf.flagship.model.FinanceDailyDistrictExample.Criteria financeDailyDistrictCriteria = financeDailyDistrictExample.createCriteria();
		financeDailyDistrictCriteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dateString));
		List<FinanceDailyDistrict> list = financeDailyDistrictMapper.selectByExample(financeDailyDistrictExample);
		if (list==null || list.size()==0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断BI抽取融资分机构数据是否抽取成功
	 */
	public boolean checkBIFinanceDailyOrg(String dateString){
		FinanceDailyOrgExample financeDailyOrgExample = new FinanceDailyOrgExample();
		com.hzcf.flagship.model.FinanceDailyOrgExample.Criteria financeDailyOrgCriteria = financeDailyOrgExample.createCriteria();
		financeDailyOrgCriteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dateString));
		List<FinanceDailyOrg> list = financeDailyOrgMapper.selectByExample(financeDailyOrgExample);
		if (list==null || list.size()==0) {
			return false;
		}
		return true;
	}
	
}
