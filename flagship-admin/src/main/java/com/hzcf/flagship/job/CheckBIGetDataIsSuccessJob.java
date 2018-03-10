package com.hzcf.flagship.job;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hzcf.flagship.dao.EmployeeMapper;
import com.hzcf.flagship.dao.FinanceDailyPerformanceMapper;
import com.hzcf.flagship.dao.MoneymgrDailyPerformanceMapper;
import com.hzcf.flagship.model.MoneymgrDailyPerformance;
import com.hzcf.flagship.model.MoneymgrDailyPerformanceExample;
import com.hzcf.flagship.model.MoneymgrDailyPerformanceExample.Criteria;
import com.hzcf.flagship.service.AppPushMessageService;
import com.hzcf.flagship.service.FinanceJobService;
import com.hzcf.flagship.util.DateTimeUtil;
import com.hzcf.flagship.util.PropertyUtil;
import com.hzcf.flagship.util.push.PushUtil;


/**
 *<dl>
 *<dt>类名：CheckBIGetDataIsSuccessJob.java</dt>
 *<dd>描述: 检查BI抽取数据是否成功</dd> 
 *<dd>创建时间： 2017年6月16日 上午11:07:15</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年6月16日 上午11:07:15    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Component
@Scope("prototype")
public class CheckBIGetDataIsSuccessJob {
	@Autowired
	private FinanceJobService financeJobService;
	@Autowired
	private MoneymgrDailyPerformanceMapper moneymgrDailyPerformanceMapper;
	@Autowired
	private FinanceDailyPerformanceMapper financeDailyPerformanceMapper;
	@Autowired
	private AppPushMessageService appPushMessageService;
	@Autowired
	private EmployeeMapper employeeMapper;
	
	private String androidAppkey = PropertyUtil.getInfo("androidAppkey");
	private String androidAppMasterSecret = PropertyUtil.getInfo("androidAppMasterSecret");
	private String iosAppkey = PropertyUtil.getInfo("iosAppkey");
	private String iosAppMasterSecret = PropertyUtil.getInfo("iosAppMasterSecret");
	private String production_mode = PropertyUtil.getInfo("production_mode");
	//private String md5_key = "fff2f795bc974b5f88ddf519c238ee4e";
	protected final Log logger = LogFactory.getLog(getClass());
	/**
	 * 检查数据是否正常,并向管理员推送消息
	 */
	public void checkAndPushMessageToManager(){
		if (!checkBI()) {
			//获取有管理员权限的所有人员电话
			String mobiles = employeeMapper.getMobilesOfAdmin();
			PushUtil util = new PushUtil(androidAppkey, androidAppMasterSecret, iosAppkey, iosAppMasterSecret, production_mode);
			Integer status = util.customCast("PHONE", mobiles, "notification", null, "系统错误信息", "获取"+DateTimeUtil.getYesterdayDateString()+"BI抽取数据失败!");
			if (status==200) {
				appPushMessageService.insertPushMessageByMobiles(mobiles, "系统错误信息", "获取"+DateTimeUtil.getYesterdayDateString()+"BI抽取数据失败!");
			}
			logger.info(DateTimeUtil.getNowTimeNormalString()+"抽取"+DateTimeUtil.getYesterdayDateString()+"数据失败,给管理员推送消息完毕");
		}
	}
	
	/**
	 * 检查数据是否正常,并向所有人推送消息
	 */
	public void checkAndPushMessageToAll(){
		if (!checkBI()) {
			PushUtil util = new PushUtil(androidAppkey, androidAppMasterSecret, iosAppkey, iosAppMasterSecret, production_mode);
			Integer status = util.broadCast("notification", null, "数据维护通知", DateTimeUtil.getYesterdayDateString()+"的数据正在维护中!");
			if (status==200) {
				appPushMessageService.insertBroadcastPushMessage("数据维护通知", DateTimeUtil.getYesterdayDateString()+"的数据正在维护中!");
			}
			logger.info(DateTimeUtil.getNowTimeNormalString()+"抽取"+DateTimeUtil.getYesterdayDateString()+"数据失败,给所有用户推送消息完毕");
		}
	}
	
	/**
	 * 检查BI抽取数据是否正常(前一天数据)
	 * @return
	 */
	private boolean checkBI(){
		//获得昨天的日期
		String dateString = DateTimeUtil.getYesterdayDateString();
		//理财日业绩
		MoneymgrDailyPerformanceExample moneymgrDailyPerformanceExample = new MoneymgrDailyPerformanceExample();
		Criteria performanceCriteria = moneymgrDailyPerformanceExample.createCriteria();
		performanceCriteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dateString));
		List<MoneymgrDailyPerformance> moneymgrDailyPerformances = moneymgrDailyPerformanceMapper.selectByExample(moneymgrDailyPerformanceExample);
		
		/*//融资日业绩
		FinanceDailyPerformanceExample financeDailyPerformanceExample = new FinanceDailyPerformanceExample();
		com.hzcf.flagship.model.FinanceDailyPerformanceExample.Criteria financeDailyPerformanceCriteria = financeDailyPerformanceExample.createCriteria();
		financeDailyPerformanceCriteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(dateString));
		List<FinanceDailyPerformance> financeDailyPerformances = financeDailyPerformanceMapper.selectByExample(financeDailyPerformanceExample);*/
		
		//如果当日的理财日业绩或者融资日业绩为空则说明BI抽取数据异常
		if (moneymgrDailyPerformances==null || moneymgrDailyPerformances.size()==0 ) {
			return false;
		}
		return true;
	
	}
}
