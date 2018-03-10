package com.hzcf.flagship.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.RiskOrgStructMapper;
import com.hzcf.flagship.dao.RiskPlanMapper;
import com.hzcf.flagship.model.RiskPlan;
import com.hzcf.flagship.model.RiskPlanExample;
import com.hzcf.flagship.model.RiskPlanExample.Criteria;
import com.hzcf.flagship.service.RiskPlanService;
import com.hzcf.flagship.util.DateTimeUtil;
import com.hzcf.flagship.util.ExportExcel;
import com.hzcf.flagship.util.ImportUtil;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.RegExpUtil;
import com.hzcf.flagship.util.StringUtil;

/**
 * 
 *<dl>
 *<dt>类名：RiskPlanServiceImpl.java</dt>
 *<dd>描述: ~风控目标service的实现类</dd> 
 *<dd>创建时间： 2017年10月17日 上午11:10:58</dd>
 *<dd>创建人： GuoDong</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年10月17日 上午11:10:58    GuoDong       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Service
public class RiskPlanServiceImpl implements RiskPlanService{

	@Autowired
	private RiskPlanMapper riskPlanMapper;
	@Autowired
	private RiskOrgStructMapper riskOrgStructMapper;
	/**
	 *  风控目标列表
	 */
	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = riskPlanMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = riskPlanMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	/**
	 *  查看每月风控目标的详情
	 */
	@Override
	public PageModel getRiskPlanListByRecordDate(String monthDate) {
		PageModel pageModel = new PageModel();
		RiskPlanExample riskPlanExample = new RiskPlanExample();
		Criteria createCriteria = riskPlanExample.createCriteria();
		createCriteria.andRecordDateEqualTo(DateTimeUtil.convertAsDateString(monthDate));
		List<RiskPlan> selectByExample = riskPlanMapper.selectByExample(riskPlanExample);
		pageModel.setList(selectByExample);
		return pageModel;
	}

	/**
	 * 模板下载
	 */
	@Override
	public void riskPlanDownLoad(HttpServletRequest request,HttpServletResponse response) {
		String date = request.getParameter("date");
		List<Map<String, Object>> data;
		String title ;
		if(!StringUtil.isNotBlank(date)){ //下载最新模板
			String dateNormalString = DateTimeUtil.getNowDateNormalString();
			data = riskOrgStructMapper.findOrgList();
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> map2 = new HashMap<String, Object>();
			Map<String, Object> map3 = new HashMap<String, Object>();
			/*市场风险管理部*/
			map.put("orgNo", "company");
			map.put("orgName", "公司");
			data.add(0, map);
			map2.put("orgNo", "fengkongglb");
			map2.put("orgName", "市场风险管理部");
			data.add(1,map2);
			map3.put("orgNo", "fengkongdh");
			map3.put("orgName", "贷后");
			data.add(2,map3);
			title = dateNormalString + "风控目标表";
		}else{ //按月下载模板
			title = date + "风控目标表";
			data = riskPlanMapper.findOrgListByDate(date);
		}
		String rowsName[] = new String[] {"机构编号","机构名称","上级机构编号","上级机构名称","C-M1回款率",
				"M2压降率","M3压降率"};  
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] obj;
		for(int i=0;i<data.size();i++){
			Map<String, Object> map = data.get(i); 
			obj = new Object[rowsName.length];
			obj[0] = map.get("orgNo");
			obj[1] = map.get("orgName");
			obj[2] = map.get("parentNo");
			obj[3] = map.get("parentName");
			dataList.add(obj);
		}
		ExportExcel exportExcel = new ExportExcel(title, rowsName, dataList);
		exportExcel.export(response);
	}
	
	/**
	 * 风控目标数据导入
	 */
	@Override
	public int insertRiskPlan(List<List<Object>> list, Integer id) {
		Date nextRecord = riskPlanMapper.selectNextRecord();
		if(null == nextRecord){
			nextRecord = DateTimeUtil.convertAsDate(DateTimeUtil.getFirstDayOfMonth(DateTimeUtil.getDateNowStr()));
		}
		return insertPlan(list, id, nextRecord);
	}
	
	/**
	 * 按月上传风控目标
	 */
	@Override
	public int insertRiskPlanByRecordDate(List<List<Object>> list, Integer id, String date) {
		riskPlanMapper.deleteByRecordDate(date);
		return insertPlan(list, id, DateTimeUtil.convertAsDateString(date));
	}
	
	/**
	 * 风控目标导入公用方法
	 * @param list
	 * @param id
	 * @param nextRecord
	 * @return
	 */
	private int insertPlan(List<List<Object>> list, Integer id, Date nextRecord) {
		List<RiskPlan> resultList = new ArrayList<RiskPlan>();
		BigDecimal bigDecimal = new BigDecimal(100);
		for (int i = 1; i < list.size(); i++) {
		try {
			RiskPlan riskPlan = new RiskPlan();
			Object cell0 = list.get(i).get(0);
			if(!StringUtil.isEmpty(String.valueOf(cell0))){
				riskPlan.setOrgNo(String.valueOf(cell0));
			}
			Object cell1 = list.get(i).get(1);
			if(!StringUtil.isEmpty(String.valueOf(cell1))){
				riskPlan.setOrgName(String.valueOf(cell1));
			}
			Object cell2 = list.get(i).get(2);
			if(!StringUtil.isEmpty(String.valueOf(cell2))){
				riskPlan.setParentOrgNo(String.valueOf(cell2));
			}
			Object cell3 = list.get(i).get(3);
			if(!StringUtil.isEmpty(String.valueOf(cell3))){
				riskPlan.setParentOrgName(String.valueOf(cell3));
			}
			Object cell4 = list.get(i).get(4);
			if(!StringUtil.isEmpty(String.valueOf(cell4))){
				String str4 = String.valueOf(cell4);
				String[] split4 = str4.split("%");
				if(RegExpUtil.checkIsDecimalsOrInt(split4[0])){
					riskPlan.setCm1Rate(new BigDecimal(split4[0]).divide(bigDecimal));//C-M1回款率分母
				}else{
					throw new Exception();
				}
			}
			Object cell5 = list.get(i).get(5);
			if(!StringUtil.isEmpty(String.valueOf(cell5))){
				String str5 = String.valueOf(cell5);
				String[] split5 = str5.split("%");
				if(RegExpUtil.checkIsDecimalsOrInt(split5[0])){
					riskPlan.setM2PressureRate(new BigDecimal(split5[0]).divide(bigDecimal));//C-M1回款率分母
				}else{
					throw new Exception();
				}
			}
			Object cell6 = list.get(i).get(6);
			if(!StringUtil.isEmpty(String.valueOf(cell6))){
				String str6 = String.valueOf(cell6);
				String[] split6 = str6.split("%");
				if(RegExpUtil.checkIsDecimalsOrInt(split6[0])){
					riskPlan.setM3PressureRate(new BigDecimal(split6[0]).divide(bigDecimal));//C-M1回款率分母
				}else{
					throw new Exception();
				}
			}
			riskPlan.setRecordDate(nextRecord);
			riskPlan.setCreator(id);
			riskPlan.setCreateTime(new Date());
			resultList.add(riskPlan);
		} catch (Exception e) {
			e.printStackTrace();
			if(0 != i ){
				return i+1;
			}
		}
	}
		if( 0 != resultList.size()){
			RiskPlan riskPlan = resultList.get(0);
			if (null != riskPlan) {
			List<List<?>> splitList = ImportUtil.splitList(resultList,100);
			for (List<?> list2 : splitList) {
				riskPlanMapper.insertRiskPlan(list2);
			}
		 }
		}
		return 0;
	}
}
