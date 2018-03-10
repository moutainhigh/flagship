

package com.hzcf.flagship.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.hzcf.flagship.dao.FinanceRiskMapper;
import com.hzcf.flagship.dao.IndexMapper;
import com.hzcf.flagship.model.FinanceRisk;
import com.hzcf.flagship.model.FinanceRiskExample;
import com.hzcf.flagship.model.FinanceRiskExample.Criteria;
import com.hzcf.flagship.model.Index;
import com.hzcf.flagship.model.IndexExample;
import com.hzcf.flagship.service.FinanceRiskService;
import com.hzcf.flagship.util.DateTimeUtil;
import com.hzcf.flagship.util.DateUtil;
import com.hzcf.flagship.util.ImportUtil;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.RegExpUtil;
import com.hzcf.flagship.util.StringUtil;

/**
 * 
 * 类名：ManpowerRosterServiceImpl.java</dt> 
 * 功能描述：融资风险表
 * 创建时间： 2017年5月10日 上午11:52:02
 * </dd> 创建人：TieGuoWei</dd>
 */
@Service
public class FinanceRiskServiceImpl implements FinanceRiskService{

	@Autowired
	private  FinanceRiskMapper financeRiskMapper;
	@Autowired
	private IndexMapper indexMapper;
	
	private final Log logger = LogFactory.getLog(getClass());
	/**
	 * 
	 * Description: 融资风险表分页查询列表
	 */
	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = financeRiskMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = financeRiskMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}


	/**
	 * 融资风险表 数据导入
	 * @throws Exception 
	 */
	@Override
	public int insertFinanceRisk(List<List<Object>> list, Integer id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<FinanceRisk> resultList = new ArrayList<FinanceRisk>();
		for (int i = 1; i < list.size(); i++) {
			try {
				FinanceRisk financeRisk = new FinanceRisk();
				if(String.valueOf(list.get(i).get(0)).length() <= 20){
					financeRisk.setManageOffice(String.valueOf(list.get(i).get(0)));//大区
				}else{
					throw new Exception();
				}
				
				if(String.valueOf(list.get(i).get(1)).length() <= 50
						&& !StringUtil.isEmpty(String.valueOf(list.get(i).get(1)))){
					financeRisk.setOrgName(String.valueOf(list.get(i).get(1)));//机构名称
				}else{
					throw new Exception();
				}
				
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(2)))
						&& RegExpUtil.checkIsDecimalsOrInt(String.valueOf(list.get(i).get(2)))){
					financeRisk.setcM1Numerator(new BigDecimal(String.valueOf(list.get(i).get(2))));//C-M1回款率分子
				}else{
					throw new Exception();
				}
				
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(3)))
						&& RegExpUtil.checkIsDecimalsOrInt(String.valueOf(list.get(i).get(3)))){
					financeRisk.setcM1Denominator(new BigDecimal(String.valueOf(list.get(i).get(3))));//C-M1回款率分母
				}else{
					throw new Exception();
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(4)))
						&& RegExpUtil.checkIsDecimalsOrInt(String.valueOf(list.get(i).get(4)))){
					financeRisk.setcM1Value(new BigDecimal(String.valueOf(list.get(i).get(4))).multiply(new BigDecimal(100)));//C-M1回款率数值
				}else{
					throw new Exception();
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(5)))
						&& RegExpUtil.checkIsDecimalsOrInt(String.valueOf(list.get(i).get(5)))){
					financeRisk.setLossRateNumerator(new BigDecimal(String.valueOf(list.get(i).get(5))));//年化新增损失率分子
				}else{
					throw new Exception();
				}
				
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(6)))
						&& RegExpUtil.checkIsDecimalsOrInt(String.valueOf(list.get(i).get(6)))){
					financeRisk.setLossRateDenominator(new BigDecimal(String.valueOf(list.get(i).get(6))));//年化新增损失率分母
				}else{
					throw new Exception();
				}
				
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(7)))
						&& RegExpUtil.checkIsDecimalsOrInt(String.valueOf(list.get(i).get(7)))){
					financeRisk.setLossRate(new BigDecimal(String.valueOf(list.get(i).get(7))).multiply(new BigDecimal(100)));//年化新增损失率数值
				}else{
					throw new Exception();
				}
				
				Date date =null;
				if(!StringUtils.isEmpty((String) list.get(i).get(8))
						&& RegExpUtil.checkIsDate(String.valueOf(list.get(i).get(8)))){
					date = sdf.parse(String.valueOf(list.get(i).get(8)));
					financeRisk.setRecordDate(date);//date
				}else{
					throw new Exception();
				}
				//得到当前登录用户id
				financeRisk.setCreator(id);//创建人id
				financeRisk.setCreateTime(new Date());
				resultList.add(financeRisk);
			} catch (Exception e) {
				e.printStackTrace();
				if(0 != i ){
					return i+1;
				}
			}
		}
		if( 0 != resultList.size()){
			FinanceRisk financeRisk = resultList.get(0);
			if (null != financeRisk) {
			Map<String, Object> financeRiskMap = new HashMap<String, Object>();
			String str = sdf.format(financeRisk.getRecordDate());
			Map<String, Object> map = DateUtil.getMonthFirstDayAndLastDay(str);
			financeRiskMap.put("firstDay", map.get("firstDay"));
			financeRiskMap.put("lastDay", map.get("lastDay"));
			financeRiskMapper.deleteFinanceRiskByFirstDayAndLastDay(financeRiskMap);
			List<List<?>> splitList = ImportUtil.splitList(resultList,100);
			for (List<?> list2 : splitList) {
				financeRiskMapper.insertFinanceRisk(list2);
			}
		 }
		}
		return 0;
	}

	/**
	 * 融资风险查询
	 */
	@Override
	public Map<String, Object> apiGetFinanceRisk() {
		Map<String, Object> data= new HashMap<>();
		FinanceRiskExample financeRiskExample = new FinanceRiskExample();
		Criteria financeRiskCriteria = financeRiskExample.createCriteria();
		financeRiskCriteria.andManageOfficeEqualTo("整体");
		financeRiskExample.setOrderByClause("record_date DESC");
		List<FinanceRisk> list = financeRiskMapper.selectByExample(financeRiskExample);
		if (list!=null && list.size() >0) {
			data.put("CM1Value",list.get(0).getcM1Value());
			data.put("lossValue", list.get(0).getLossRate());
			data.put("month", DateTimeUtil.getDateChinaStringYearMonthSimple(list.get(0).getRecordDate()));
		}else {
			data.put("CM1Value",-1);
			data.put("lossValue", -1);
			data.put("month",-1);
		}
		data.put("CM1MinIndex", getIndexValue("financeMgr_month_CM1Rate_minValue"));
		data.put("CM1WarningIndex", getIndexValue("financeMgr_month_CM1Rate_warning"));
		data.put("CM1WellIndex", getIndexValue("financeMgr_month_CM1Rate_well"));
		//data.put("CM1MaxIndex", value);
		//data.put("lossMinIndex", value);
		data.put("lossWarningIndex", getIndexValue("financeMgr_month_lossRate_warning"));
		data.put("lossWellIndex", getIndexValue("financeMgr_month_lossRate_well"));
		data.put("lossMaxIndex", getIndexValue("financeMgr_month_lossRate_maxValue"));
		//查新CM1回款率列表
		List<Map<String, Object>> CM1List = financeRiskMapper.findCM1ListOfThisYear();
		//查询新增损失率列表
		List<Map<String, Object>> lossList = financeRiskMapper.findLossListOfThisYear();
		//计算起始终止时间
		if (CM1List!=null && CM1List.size()>0) {
			String year = DateTimeUtil.getYearByDate(list.get(0).getRecordDate());
			String endMonth = (String)(CM1List.get(0).get("month"));
			String startMonth = (String)(CM1List.get(CM1List.size()-1).get("month"));
			if (year!=null && endMonth!=null && startMonth!=null) {
				if (startMonth.equals(endMonth)) {
					data.put("recordMonth", year+startMonth+"月");
				}else {
					data.put("recordMonth", year+startMonth+"~"+endMonth+"月");
				}
				
			}
			data.put("CM1List", CM1List);
			data.put("lossList", lossList);
			
		}
		
		//删掉返回值中为null的字段
		Set<String> keys = data.keySet();
		for (String key : keys) {
			Object object = data.get(key);
			if (object==null) {
				data.remove(key);
			}
		}
		return data;
	}
	
	/**
	 * 查询对应指标值
	 * @param indexCode
	 * @return
	 */
	private double getIndexValue(String indexCode) {
		IndexExample indexExample = new IndexExample();
		com.hzcf.flagship.model.IndexExample.Criteria indexCriteria = indexExample.createCriteria();
		indexCriteria.andCodeEqualTo(indexCode);
		List<Index> list = indexMapper.selectByExample(indexExample);
		return list.get(0).getValue();
	}
	
		
}
