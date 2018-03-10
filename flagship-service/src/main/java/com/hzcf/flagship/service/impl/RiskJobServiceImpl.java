package com.hzcf.flagship.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hzcf.flagship.dao.AppPermissionMapper;
import com.hzcf.flagship.dao.PropertiesMapper;
import com.hzcf.flagship.dao.RiskLoanCm1Mapper;
import com.hzcf.flagship.dao.RiskLoanM1OverdueMapper;
import com.hzcf.flagship.dao.RiskOrgDataMapper;
import com.hzcf.flagship.dao.RiskOrgStructMapper;
import com.hzcf.flagship.dao.RiskOverdueMapper;
import com.hzcf.flagship.dao.RiskProductWarningMapper;
import com.hzcf.flagship.dao.RiskSubcenterMapper;
import com.hzcf.flagship.dao.RiskWarningLevelMapper;
import com.hzcf.flagship.model.AppPermission;
import com.hzcf.flagship.model.Properties;
import com.hzcf.flagship.model.RiskEmail;
import com.hzcf.flagship.model.RiskOrgData;
import com.hzcf.flagship.model.RiskWarningLevel;
import com.hzcf.flagship.service.RiskEmailService;
import com.hzcf.flagship.service.RiskJobService;
import com.hzcf.flagship.util.DateTimeUtil;
import com.hzcf.flagship.util.EmailManager;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.PropertyUtil;
import com.hzcf.flagship.util.StringUtil;

/**
 *<dl>
 *<dt>类名：RiskJobServiceImpl.java</dt>
 *<dd>描述: 风控任务类逻辑实现</dd> 
 *<dd>创建时间： 2017年10月23日 上午9:07:09</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年10月23日 上午9:07:09    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Service
public class RiskJobServiceImpl implements RiskJobService{
	private final Log logger = LogFactory.getLog(getClass());
	private List<Map<String, Object>> productWarningIndexList;
	private Date recordDate;
	private Date lastMonth;
	private Date twoMonthAgo;
	@Autowired
	private RiskOrgStructMapper riskOrgStructMapper;
	@Autowired
	private RiskProductWarningMapper riskProductWarningMapper;
	@Autowired
	private RiskWarningLevelMapper riskWarningLevelMapper;
	@Autowired
	private RiskLoanM1OverdueMapper riskLoanM1OverdueMapper;
	@Autowired
	private AppPermissionMapper appPermissionMapper;
	@Autowired
	private RiskEmailService riskEmailService;
	@Autowired
	private RiskOrgDataMapper riskOrgDataMapper;
	@Autowired
	private RiskSubcenterMapper riskSubcenterMapper;
	@Autowired
	private RiskLoanCm1Mapper riskLoanCm1Mapper;
	@Autowired
	private PropertiesMapper propertiesMapper;
	@Autowired
	private RiskOverdueMapper riskOverdueMapper;
	
	//营业部负责人，风控经理发送邮件 队列名
	private String rabbitmq_riskSendEmail_queueName = PropertyUtil.getInfo("rabbitmq.riskSendEmail.queueName");
	//交换机
    private String rabbitmq_risk_exchange = PropertyUtil.getInfo("rabbitmq.risk.exchange");
	@Autowired  
	private AmqpTemplate amqpTemplate; 
	 //营业部cm1月邮件上传到Fast队列名
	 private String rabbitmq_risk_queueName = PropertyUtil.getInfo("rabbitmq.risk.queueName");
	/**
	 * 更新预警级别
	 */
	public void riskInsertWarningLevel(){
		logger.info("开始更新预警级别");
		//如果是1-26日,则统计month为当月的数据,如果是27日至月底则统计下月的数据
		//获得当前日期-1
		String month;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		int day= calendar.get(Calendar.DAY_OF_MONTH);
		recordDate = calendar.getTime();
		//将所有精英类(1.69,1.89等)产品聚合成精英类(product_no=991),将所有公积金类(2.39)产品聚合成公积金类(product_no=11)
		riskLoanCm1Mapper.updateEliteSum(DateTimeUtil.getDateNormalString(recordDate));
		riskLoanCm1Mapper.updateProvidentFundSum(DateTimeUtil.getDateNormalString(recordDate));
		riskLoanM1OverdueMapper.updateEliteSum(DateTimeUtil.getDateNormalString(recordDate));
		riskLoanM1OverdueMapper.updateProvidentFundSum(DateTimeUtil.getDateNormalString(recordDate));
		riskOverdueMapper.updateEliteSum(DateTimeUtil.getDateNormalString(recordDate));
		riskOverdueMapper.updateProvidentFundSum(DateTimeUtil.getDateNormalString(recordDate));
		//删除T-1日的记录(防止多次执行导致数据重复)
		riskWarningLevelMapper.deleteByDate(DateTimeUtil.getDateNormalString(recordDate));
		//获取当天所有产品的预警指标值
		productWarningIndexList = riskProductWarningMapper.getWarningIndexByDate(new Date());
		
		if (day>=27) {//如果日期大于27,则获取下月数据
			calendar.add(Calendar.MONTH, 1);
		}
		month = DateTimeUtil.getMonthString(recordDate);
		calendar.set(Calendar.DAY_OF_MONTH, 26);
		calendar.add(Calendar.MONTH, -1);//上个月26日
		lastMonth = calendar.getTime();
		calendar.add(Calendar.MONTH, -1);//上上个月26日
		twoMonthAgo = calendar.getTime();
		//获取所有的营业部和产品
		HashMap<String, Object> paramsCondition = new HashMap<>();
		paramsCondition.put("date", DateTimeUtil.getDateNormalString(recordDate));
		paramsCondition.put("month", month);
		
		List<Map<String, Object>> list = riskOrgStructMapper.findAllBusinessOrg(paramsCondition);
		for (Map<String, Object> map : list) {
			insertWarningLevleByOrgProduct(map);
		}
		logger.info("更新预警级别结束");
		
	}
	
	/**
	 * 根据机构和产品插入预警级别
	 */
	public void insertWarningLevleByOrgProduct(Map<String, Object> map){
		try {
			//1.取出机构编号,产品编号,应回款笔数
			if (map == null || map.get("org_no")==null || map.get("product_no")==null || map.get("repayment_num")==null) {
				logger.error("插入预警级别时,营业部或产品或者应回款笔数为空");
				return;
			}
			String orgNo = (String)map.get("org_no");
			String productNo = (String)map.get("product_no");
			int repaymentNum = ((BigDecimal)map.get("repayment_num")).intValue();
			//int intValue = repaymentNum.intValue();
			BigDecimal cm1Value = (BigDecimal)map.get("cm1_value");
			//获得M1逾期率
			HashMap<String, Object> params = new HashMap<>();
			params.put("date", DateTimeUtil.getDateNormalString(recordDate));
			params.put("orgNo", orgNo);
			params.put("productNo", productNo);
			Map<String, Object> m1ValueMap = riskLoanM1OverdueMapper.getM1ValueByOrgAndProduct(params);
			BigDecimal m1Value=null;
			Integer loanNum=null;
			if (m1ValueMap!=null) {
				m1Value = (BigDecimal)m1ValueMap.get("m1_value");
				loanNum = ((BigDecimal)m1ValueMap.get("loan_num")).intValue();
				
			}
			//2计算预警级别
			//2.1根据条件判断风险指标的方式
			if (repaymentNum>=20) {//如果当月某一款产品应回款笔数>=20笔,以cm-1回款率作为风险指标
				calculateWarningLevelByCM1(orgNo, productNo, cm1Value,m1Value);
				//如果当月某一款产品应回款笔数<20笔,且连续6个月放款笔数>=10笔,以m1逾期率作为风险指标
			}else if (repaymentNum<20 && loanNum!=null && loanNum>=10) {
				calculateWarningLevelByM1(orgNo, productNo, cm1Value,m1Value);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("机构编号为"+(String)map.get("org_no")+"的机构下的编号为"+(String)map.get("product_no")+"的产品插入失败");
		}
		
	}
	
	/**
	 * 计算类型为CM-1的预警级别
	 * @param orgNo
	 * @param productNo
	 * @param cm1Value
	 */
	public void calculateWarningLevelByCM1(String orgNo,String productNo,BigDecimal cm1Value,BigDecimal m1Value){
		//获得产品的预警指标
		BigDecimal warningIndex = getWarningIndexByProduct(productNo);
		//获得上月份的预警状态
		Map<String, Object> warningLevelLastMonth = getWarningLevelLastMonth(orgNo, productNo);
		//获得上上个月预警状态
		Map<String, Object> warningLevelTwoMonthAgo = getWarningLevelTwoMonthAgo(orgNo, productNo);
		if (warningIndex == null) {
			logger.error("获取产品编号为"+productNo+"的产品的预警值为空");
			return;
		}
		if (cm1Value.compareTo(warningIndex)==-1) {//判断是否大于预警值(等于0或1表示未预警,-1表示预警)
			//如果上月的预警方式不是CM-1(即状态为1)回款率或者上月无预警,则重新计算
			if ((warningLevelLastMonth != null && "2".equals(warningLevelLastMonth.get("type")))||warningLevelLastMonth ==null) {
				logger.info("机构编号为"+orgNo+"的机构下的编号为"+productNo+"的产品上月预警方式为逾期率,本月预警方式为回款率或上月无预警");
				//return;
				//上月为逾期率预警,本月为回款率预警,则不论上个月是几级预警,本月都重新计算,即为一级预警
				insertWarningLevelRecord(orgNo, productNo, "1", "一级预警", cm1Value, m1Value);
			}else if ("一级预警".equals(warningLevelLastMonth.get("warning_level"))) {//如果上月是一级预警,判断本月是否为二级预警
				//求两个月的平均CM1值
				BigDecimal cm1LastMonth = (BigDecimal)warningLevelLastMonth.get("cm1_value");
				BigDecimal average = cm1Value.add(cm1LastMonth).divide(new BigDecimal(2), 4 ,BigDecimal.ROUND_DOWN);
				if (average.compareTo(warningIndex) == -1) {//如果两月平均数小于预警值则为二级预警
					insertWarningLevelRecord(orgNo, productNo, "1", "二级预警", cm1Value, m1Value);
				}else {
					insertWarningLevelRecord(orgNo, productNo, "1", "一级预警", cm1Value, m1Value);
				}
			}else if ("二级预警".equals(warningLevelLastMonth.get("warning_level"))) {//如果上月是二级预警,判断本月是否为三级预警
				//计算连续三个月的平均值
				BigDecimal cm1LastMonth = (BigDecimal)warningLevelLastMonth.get("cm1_value");
				BigDecimal cm1TwoMonthAgo = (BigDecimal)warningLevelTwoMonthAgo.get("cm1_value");
				BigDecimal average = cm1Value.add(cm1LastMonth).add(cm1TwoMonthAgo).divide(new BigDecimal(3), 4 ,BigDecimal.ROUND_DOWN);
				if (average.compareTo(warningIndex) == -1) {//如果三个月平均数小于预警值则为三级预警
					insertWarningLevelRecord(orgNo, productNo, "1", "三级预警", cm1Value, m1Value);
				}else {
					insertWarningLevelRecord(orgNo, productNo, "1", "一级预警", cm1Value, m1Value);
				}
			}else {//如果上月是三级预警,则判断本月是否为
				insertWarningLevelRecord(orgNo, productNo, "1", "三级预警", cm1Value, m1Value);
			}
		}else {//表示本月大于预警值
			if (warningLevelLastMonth==null) {//上月无预警,则本月也无预警
				return;
			}
			//如果上月为一级预警,则判断本月是否为二级预警
			if ("一级预警".equals(warningLevelLastMonth.get("warning_level"))) {
				//求两个月的平均CM1值
				BigDecimal cm1LastMonth = (BigDecimal)warningLevelLastMonth.get("cm1_value");
				BigDecimal average = cm1Value.add(cm1LastMonth).divide(new BigDecimal(2), 4 ,BigDecimal.ROUND_DOWN);
				if (average.compareTo(warningIndex) == -1) {//如果两月平均数小于预警值则为二级预警
					insertWarningLevelRecord(orgNo, productNo, "1", "二级预警", cm1Value, m1Value);
				}
			}else if ("二级预警".equals(warningLevelLastMonth.get("warning_level"))) {//如果上月为二级预警,则判断本月是否为三级预警
				//计算连续三个月的平均值
				BigDecimal cm1LastMonth = (BigDecimal)warningLevelLastMonth.get("cm1_value");
				BigDecimal cm1TwoMonthAgo = (BigDecimal)warningLevelTwoMonthAgo.get("cm1_value");
				BigDecimal average = cm1Value.add(cm1LastMonth).add(cm1TwoMonthAgo).divide(new BigDecimal(3), 4 ,BigDecimal.ROUND_DOWN);
				if (average.compareTo(warningIndex) == -1) {//如果三个月平均数小于预警值则为三级预警
					insertWarningLevelRecord(orgNo, productNo, "1", "三级预警", cm1Value, m1Value);
				}
			}else if ("三级预警".equals(warningLevelLastMonth.get("warning_level"))) {//如果上月是三级预警,则判断本月是否为三级预警
				//如果连续两个月的CM1回款率小于预警值则不再预警,否则仍为三级预警
				BigDecimal cm1LastMonth = (BigDecimal)warningLevelLastMonth.get("cm1_value");
				if (cm1LastMonth.compareTo(warningIndex) == -1) {
					insertWarningLevelRecord(orgNo, productNo, "1", "三级预警", cm1Value, m1Value);
				}
			}
		}
		
	}
	
	/**
	 * 计算类型为M1逾期率的预警级别
	 * @param orgNo
	 * @param productNo
	 * @param m1Value
	 */
	private void calculateWarningLevelByM1(String orgNo,String productNo,BigDecimal cm1Value,BigDecimal m1Value){
		//m1预警指标值
		BigDecimal warningIndex = new BigDecimal(0.08).setScale(4, BigDecimal.ROUND_DOWN);
		//获得上月份的预警状态
		Map<String, Object> warningLevelLastMonth = getWarningLevelLastMonth(orgNo, productNo);
		//获得上上个月预警状态
		Map<String, Object> warningLevelTwoMonthAgo = getWarningLevelTwoMonthAgo(orgNo, productNo);
		if (m1Value.compareTo(warningIndex)== 1) {//大于8%的预警值,说明不达标
			//如果上月计算预警状态的方式为CM-1,本月为M1,或者上月无预警则重新计算
			if ((warningLevelLastMonth != null && "1".equals(warningLevelLastMonth.get("type"))) || warningLevelLastMonth ==null) {
				logger.info("机构编号为"+orgNo+"的机构下的编号为"+productNo+"的产品上月预警方式为逾期率,本月预警方式为回款率或者上月无预警");
				insertWarningLevelRecord(orgNo, productNo, "2", "一级预警", cm1Value, m1Value);
			}else if("一级预警".equals(warningLevelLastMonth.get("warning_level"))){//如果上月是一级预警,判断本月是否为二级预警
				//如果两个月平均值大于预警值,则为二级预警,否则为一级预警
				BigDecimal m1ValueLastMonth = (BigDecimal)warningLevelLastMonth.get("m1_value");
				BigDecimal average = m1Value.add(m1ValueLastMonth).divide(new BigDecimal(2), 4,BigDecimal.ROUND_DOWN);
				if (average.compareTo(warningIndex) == 1) {//
					insertWarningLevelRecord(orgNo, productNo, "2", "二级预警", cm1Value, m1Value);
				}else {
					insertWarningLevelRecord(orgNo, productNo, "2", "一级预警", cm1Value, m1Value);
				}
				//如果上月是二级预警,则判断本月是否为三级预警
			}else if ("二级预警".equals(warningLevelLastMonth.get("warning_level"))) {
				if (warningLevelTwoMonthAgo == null) {
					insertWarningLevelRecord(orgNo, productNo, "2", "一级预警", cm1Value, m1Value);
				}else {
					BigDecimal m1ValueLastMonth = (BigDecimal)warningLevelLastMonth.get("m1_value");
					BigDecimal m1ValueTwoMonthAgo = (BigDecimal)warningLevelTwoMonthAgo.get("m1_value");
					BigDecimal average = m1Value.add(m1ValueLastMonth).add(m1ValueTwoMonthAgo).divide(new BigDecimal(3), 4,BigDecimal.ROUND_DOWN);
					if (average.compareTo(warningIndex) == 1) {//三个月m1回款率平局值仍大于指标值,则为三级预警
						insertWarningLevelRecord(orgNo, productNo, "2", "三级预警", cm1Value, m1Value);
					}else {
						insertWarningLevelRecord(orgNo, productNo, "2", "一级预警", cm1Value, m1Value);
					}
				}
			}else {//上月为三级预警
				insertWarningLevelRecord(orgNo, productNo, "2", "三级预警", cm1Value, m1Value);
			}
		}else {//本月达标
			if (warningLevelLastMonth==null) {//上月无预警,则本月也无预警
				return;
			}
			//如果上月为一级预警,则判断本月是否为一级预警
			if ("一级预警".equals(warningLevelLastMonth.get("warning_level"))) {
				//求两个月的平均CM1值
				BigDecimal m1LastMonth = (BigDecimal)warningLevelLastMonth.get("m1_value");
				BigDecimal average = m1Value.add(m1LastMonth).divide(new BigDecimal(2), 4 ,BigDecimal.ROUND_DOWN);
				if (average.compareTo(warningIndex) == 1) {//如果两月平均数小于预警值则为二级预警
					insertWarningLevelRecord(orgNo, productNo, "2", "二级预警", cm1Value, m1Value);
				}
			}else if ("二级预警".equals(warningLevelLastMonth.get("warning_level"))) {//如果上月为二级预警,则判断本月是否为三级预警
				//计算连续三个月的平均值
				BigDecimal m1LastMonth = (BigDecimal)warningLevelLastMonth.get("m1_value");
				BigDecimal m1TwoMonthAgo = (BigDecimal)warningLevelTwoMonthAgo.get("m1_value");
				BigDecimal average = m1Value.add(m1LastMonth).add(m1TwoMonthAgo).divide(new BigDecimal(3), 4 ,BigDecimal.ROUND_DOWN);
				if (average.compareTo(warningIndex) == 1) {//如果三个月平均数小于预警值则为三级预警
					insertWarningLevelRecord(orgNo, productNo, "2", "三级预警", cm1Value, m1Value);
				}
			}else if ("三级预警".equals(warningLevelLastMonth.get("warning_level"))) {//如果上月是三级预警,则判断本月是否为三级预警
				//如果连续两个月的CM1回款率小于预警值则不再预警,否则仍为三级预警
				BigDecimal m1LastMonth = (BigDecimal)warningLevelLastMonth.get("m1_value");
				if (m1LastMonth.compareTo(warningIndex) == 1) {
					insertWarningLevelRecord(orgNo, productNo, "2", "三级预警", cm1Value, m1Value);
				}
			}
		}
		
	}
	
	/**
	 * 获得某个产品的预警指标值
	 * @param productNo
	 * @return
	 */
	private BigDecimal getWarningIndexByProduct(String productNo){
		if (productWarningIndexList!=null && productWarningIndexList.size()>0) {
			for (Map<String, Object> map : productWarningIndexList) {
				if (productNo.equals(map.get("product_no"))) {
					return (BigDecimal)map.get("warning_value");
				}
			}
		}
		return null;
	}
	
	/**
	 * 获得上个月预警等级
	 * @param orgNo
	 * @param productNo
	 * @return
	 */
	private Map<String, Object> getWarningLevelLastMonth(String orgNo,String productNo){
		HashMap<String, Object> params = new HashMap<>();
		//获得上个月26日的日期
		params.put("date", DateTimeUtil.getDateNormalString(lastMonth));
		params.put("orgNo",orgNo);
		params.put("productNo", productNo);
		Map<String, Object> warningLevelOfLastMonth = riskWarningLevelMapper.getWarningLevelByOrgAndProduct(params);
		return warningLevelOfLastMonth;
	}
	
	/**
	 * 获得上上个月预警等级
	 * @param orgNo
	 * @param productNo
	 * @return
	 */
	private Map<String, Object> getWarningLevelTwoMonthAgo(String orgNo,String productNo){
		HashMap<String, Object> params = new HashMap<>();
		//获得上个月26日的日期
		params.put("date", DateTimeUtil.getDateNormalString(twoMonthAgo));
		params.put("orgNo",orgNo);
		params.put("productNo", productNo);
		Map<String, Object> warningLevelOfLastMonth = riskWarningLevelMapper.getWarningLevelByOrgAndProduct(params);
		return warningLevelOfLastMonth;
	}
	
	
	/**
	 * 插入预警级别记录
	 */
	private void insertWarningLevelRecord(String orgNo,String productNo,String type,
					String warningLevel,BigDecimal cm1Value,BigDecimal m1Value){
		
			RiskWarningLevel riskWarningLevel = new RiskWarningLevel();
			riskWarningLevel.setRecordDate(recordDate);
			riskWarningLevel.setOrgNo(orgNo);
			riskWarningLevel.setProductNo(productNo);
			riskWarningLevel.setType(type);
			riskWarningLevel.setWarningLevel(warningLevel);
			riskWarningLevel.setCm1Value(cm1Value);
			riskWarningLevel.setM1Value(m1Value);
			riskWarningLevel.setCreateTime(new Date());
			riskWarningLevelMapper.insert(riskWarningLevel);
	}

	/**
	 * 更新权限表
	 */
	@Override
	public void riskUpdatePermission() {
		
		//插入新机构(组织机构表)
		insertNewPerssion();
		//更新有改动的机构
		updatePermission();
		//插入新分中心权限(分中心表)
		insertNewSubcente();
		//更新有改动的分中心权限
		updateSubcente();
	}
	
	/**
	 * 根据机构表中的新机构在权限表中插入新权限
	 */
	private void insertNewPerssion(){
		try {
			logger.info("开始更新权限表新机构");
			//查找机构表中的新机构
			List<Map<String, Object>> newOrgs = riskOrgStructMapper.findNewOrgs();
			if (newOrgs!=null && newOrgs.size()>0) {
				
				for (Map<String, Object> map : newOrgs) {
					int rank = (Integer)map.get("rank");
					Integer sorting = appPermissionMapper.getSortingByRank(rank);
					if (sorting==null) {
						sorting=rank*1000+10;
					}else {
						sorting+=10;
					}
					AppPermission appPermission = new AppPermission();
					appPermission.setNo((String)map.get("org_no"));
					appPermission.setName((String)map.get("org_name"));
					appPermission.setOperation((String)map.get("org_name"));
					appPermission.setParentNo((String)map.get("parent_no"));
					appPermission.setRank((Integer)map.get("rank"));
					appPermission.setSorting(sorting);
					appPermission.setStatus("1");
					appPermission.setCreatorId(1);
					appPermission.setCreateTime(new Date());
					appPermissionMapper.insert(appPermission);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新权限表新机构出错", e);
		}

	}
	
	/**
	 * 根据机构表中的修改记录更新权限表中的记录
	 */
	private void updatePermission(){
		try {
			logger.info("开始更新机构权限修改记录");
			//查找机构表中有改动的数据
			List<Map<String, Object>> updateOrgs = riskOrgStructMapper.findUpdateOrgs();
			if (updateOrgs!=null && updateOrgs.size()>0) {
				for (Map<String, Object> map : updateOrgs) {
					AppPermission appPermission = new AppPermission();
					//appPermission.setNo((String)map.get("org_no"));
					appPermission.setId((Integer)map.get("id"));
					appPermission.setName((String)map.get("org_name"));
					appPermission.setOperation((String)map.get("org_name"));
					appPermission.setParentNo((String)map.get("parent_no"));
					appPermission.setRank((Integer)map.get("rank"));
					appPermissionMapper.updateByPrimaryKeySelective(appPermission);
				}
			}
			logger.info("更新机构权限修改记录结束");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新机构权限修改记录出错", e);
		}
	}
	
	/**
	 * 插入新分中心权限
	 */
	private void insertNewSubcente(){
		try {
			logger.info("开始更新分中心新记录");
			//1.查找分中心表中的新记录
			List<Map<String, String>> newSubcenters = riskSubcenterMapper.findNewSubcente();
			if (newSubcenters!=null && newSubcenters.size()>0) {
				//查询最新排序
				int sorting;
				Integer sortingByRank = appPermissionMapper.getSortingByRank(6);
				if (sortingByRank==null) {
					sorting=6010;
				}else {
					sorting=sortingByRank+10;
				}
				for (Map<String, String> map : newSubcenters) {
					AppPermission appPermission = new AppPermission();
					appPermission.setNo(map.get("subcenter_no"));
					appPermission.setName(map.get("name"));
					appPermission.setOperation(map.get("name"));
					appPermission.setParentNo("fengkongglb");
					appPermission.setRank(6);
					appPermission.setSorting(sorting);
					appPermission.setStatus("1");
					appPermission.setCreatorId(1);
					appPermission.setCreateTime(new Date());
					appPermissionMapper.insert(appPermission);
					sorting+=10;
				}
			}
			logger.info("更新分中心新记录结束");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新分中心新记录出错", e);
		}
	}
	
	/**
	 * 根据分中心表中的修改记录更新权限表中的记录
	 */
	private void updateSubcente(){
		try {
			logger.info("开始更新分中心修改记录");
			//查找机构表中有改动的数据
			List<Map<String, Object >> updateSubcenters = riskSubcenterMapper.findUpdateSubcente();
			if (updateSubcenters!=null && updateSubcenters.size()>0) {
				for (Map<String, Object> map : updateSubcenters) {
					/*if ("0".equals((String)map.get("status"))) {//如果分中心被删除,则删除对应权限
						appPermissionMapper.deleteByPrimaryKey((int)map.get("id"));
					}*/
					AppPermission appPermission = new AppPermission();
					appPermission.setId((int)map.get("id"));
					appPermission.setName((String)map.get("name"));
					appPermission.setOperation((String)map.get("name"));
					//appPermission.setParentNo("fengkongglb");
					//appPermission.setRank(6);
					appPermissionMapper.updateByPrimaryKeySelective(appPermission);
				}
			}
			logger.info("更新分中心修改记录结束");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新分中心修改记录出错", e);
		}
		
	}
	

	@Override
	public void sendRiskDataEmail() {
		
		//查询f_risk_org_data表中负责人
		List <RiskOrgData> list = riskOrgDataMapper.getOrgData();
		//通过负责人编号查询负责人邮箱信息
		PageModel address = riskEmailService.findEmailAddressByList(list);
		List<Map <String,Object>> emailList = address.getList();
		if (null != emailList && emailList.size() > 0) {
			for (int i = 0; i <emailList.size(); i++) {
				RiskEmail riskEmail = new RiskEmail();
				Map<String, Object> map = emailList.get(i);
				String status = (String) map.get("status");
				String email = (String) map.get("email");
				//判断是否离职("0":已离职 "   1":在职) 或者邮箱为空
				if("1".equals(status) && StringUtil.isNotBlank(email)){
					//调用发送邮件接口
					String isPrincipalDetail = (String) map.get("isPrincipalDetail");
					//是否是负责人
					String principal = (String) map.get("principal");
					String isCreditManagerDetail = (String) map.get("isCreditManagerDetail");
					String orgNo = (String) map.get("orgNo");

					//负责人员工编号
					String receiverNo = (String) map.get("principalNo");
					//负责人员工姓名
					String receiverName = (String) map.get("name");
					
					riskEmail.setReceiverAddress(email);
					
					if(StringUtil.isNotBlank(receiverNo)){
						riskEmail.setReceiverNo(receiverNo);
					}
					if(StringUtil.isNotBlank(receiverName)){
						riskEmail.setReceiverName(receiverName);
					}
					if(StringUtil.isNotBlank(principal)){
						riskEmail.setPrincipal(principal);
					}
					if(StringUtil.isNotBlank(isPrincipalDetail)){
						riskEmail.setIsPrincipalDetail(isPrincipalDetail);
					}
					if(StringUtil.isNotBlank(isCreditManagerDetail)){
						riskEmail.setIsCreditManagerDetail(isCreditManagerDetail);
					}
					if(StringUtil.isNotBlank(orgNo)){
						riskEmail.setOrgNo(orgNo);;
					}
					
					//进入队列  发送邮件
					String jsonString = JSON.toJSONString(riskEmail);
					amqpTemplate.convertAndSend(rabbitmq_risk_exchange, rabbitmq_riskSendEmail_queueName, jsonString);
				}else{
					insertResult(riskEmail, map, "0", "");
				}
			}
		}
	}

	private void insertResult(RiskEmail riskEmail, Map<String, Object> map, String status, String email) {
		riskEmail.setCreateTime(new Date());
		riskEmail.setResult(status);
		riskEmail.setOrgNo((String)map.get("orgNo"));
		riskEmail.setReceiverName((String)map.get("name"));
    	riskEmail.setReceiverNo((String)map.get("principalNo"));
    	riskEmail.setReceiverAddress(email);
		riskEmailService.insertRecord(riskEmail);
	}

	@Override
	public void sendEmailOfSubcenterIsNull() {
		try {
			String recordDate = DateTimeUtil.getYesterdayDateString();
			List<Map<String,Object>> list =  riskOrgDataMapper.checkSubcenterIsNull(recordDate);
			if(null != list && list.size() > 0){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm分");
				SimpleDateFormat sdf2 = new SimpleDateFormat("MM月dd日");
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("property_name", "checkSubcenterIsNullSendEmails");
				Properties properties = propertiesMapper.findValueByPropertyName(map);
				String[] to = null;
				if(null != properties){
					to = properties.getPropertyStringValue().split(",");
				}
				String subject = sdf2.format(new Date())+"营业部未分配分中心详情";
				StringBuffer buffer = new StringBuffer();
				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					Map<String, Object> map2 = (Map<String, Object>) iterator.next();
					buffer.append(map2.get("orgName")+"("+map2.get("pName")+")"+", ");
				};
				 String str;
				if(1 == list.size()){
					str = "名称为：";
				}else{
					str = "分别是:";
				}
				String content = "您好:<br>"+
				"&nbsp;&nbsp;&nbsp;&nbsp; 截止"+sdf.format(new Date())+"，共有"+list.size()+"个营业部无所属分中心，"+str
						+buffer+
				"请您及时管理营业部所属分中心。";
				//不设置附件
				String[] fileList = new String[1];
				boolean sendMail = EmailManager.getInstance().sendMail(to, subject, content, fileList);
				if(!sendMail){
					logger.info("=====邮件发送失败======");
				}
			}else{
				logger.info("=====营业部分配分中心已完成======");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void riskMonthEmailDataUpload() {
		//查询f_risk_org_data表中负责人
		List <RiskOrgData> list = riskOrgDataMapper.getOrgData();
		String yesterdayDateString = DateTimeUtil.getYesterdayDateString();
		
		//通过当前日期判断 批次
		Calendar cal=Calendar.getInstance();      
		int d=cal.get(Calendar.DATE);
		String batch = getBatchByDay(d);
		if(null != batch){
			String firstBatch = batch.split(":")[0];
			String secondBatch = batch.split(":")[1];
			for (RiskOrgData riskOrgData : list) {
				Map<String, Object> map = new HashMap<String,Object>();
				String orgNo = riskOrgData.getOrgNo();
				map.put("org_no",orgNo);
				map.put("firstBatch", firstBatch);
				map.put("secondBatch", secondBatch);
				map.put("recordDate", yesterdayDateString);
				//标识cm1月邮件 使用此队列
				map.put("status", "cm1MonthEmailData");
				String jsonString = JSON.toJSONString(map);
				amqpTemplate.convertAndSend(rabbitmq_risk_exchange, rabbitmq_risk_queueName, jsonString);
			}
		}
		
	}
	
	/**
	 * 
	* @Description: 通过日期 得到批次
	* @param @param day
	* @param @return    
	* @return String
	* @throws
	 */
	public  String getBatchByDay(int day){
		String firstBatch = null;
		String secondBatch = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Calendar c = Calendar.getInstance();
		Calendar c3= Calendar.getInstance();
		c3.add(Calendar.MONTH, -1);
		if(day == 4 || day == 7){
			Calendar c2 = Calendar.getInstance();
			c2.add(Calendar.MONTH, -2);
			firstBatch = format.format(c2.getTime())+"30";
			secondBatch = format.format(c3.getTime())+"30";
			return firstBatch+":"+secondBatch;
		};
		if(day == 14 || day == 17){
			firstBatch = format.format(c3.getTime())+"10";
			secondBatch = format.format(c.getTime())+"10";
			return firstBatch+":"+secondBatch;

		};
		if(day == 24 || day == 27){
			firstBatch = format.format(c3.getTime())+"20";
			secondBatch = format.format(c.getTime())+"20";
			return firstBatch+":"+secondBatch;
		};
		return null;
	}
	
}
