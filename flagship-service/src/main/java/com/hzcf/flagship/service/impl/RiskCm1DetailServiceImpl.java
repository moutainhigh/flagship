package com.hzcf.flagship.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.EmployeePermissionRelationMapper;
import com.hzcf.flagship.dao.RiskCm1DetailMapper;
import com.hzcf.flagship.dao.RiskSeparateMapper;
import com.hzcf.flagship.service.RiskCm1DetailService;
import com.hzcf.flagship.util.DateTimeUtil;
import com.hzcf.flagship.util.ExportExcel;
import com.hzcf.flagship.util.PageModel;
@Service
public class RiskCm1DetailServiceImpl implements RiskCm1DetailService{

	@Autowired
	private RiskCm1DetailMapper riskCm1DetailMapper;
	@Autowired
	private RiskSeparateMapper riskSeparateMapper;
	@Autowired
	private  EmployeePermissionRelationMapper employeePermissionRelationMapper;
	@Override
	public List<?> getCm1ComboBoxName(Map<String, Object> map) {
		String status  = (String) map.get("status");
		//查询事业部 或者 根据事业部查询大区
		if("1".equals(status) || "2".equals(status)){
			return riskCm1DetailMapper.getbusinessOrDistrictName(map);
		}else{
			//根据事业部 和大区编号 查询营业部
			return riskCm1DetailMapper.getOrgNameOrBatch(map);
		}
	}
	
	@Override
	public Map<String, Object> findPermissionByEmpId(Integer employeeId) {
		return employeePermissionRelationMapper.findPermissionByEmpId(employeeId);
	}

	@Override
	public Map<String, Object> findSeparate(String yesterday) {
		return riskSeparateMapper.selectSeparate(yesterday);
	}

	@Override
	public PageModel getCm1OverDetailList(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = riskCm1DetailMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = riskCm1DetailMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}


	@Override
	public List<?> getCm1MonthTime() {
		return riskCm1DetailMapper.getCm1MonthTime();
	}

	@Override
	public void exportRiskBasicDataExcel(Map<String, Object> paramsCondition, HttpServletResponse response) {
		List<Map<String, Object>> result = riskCm1DetailMapper.findAllRetMapByPage(paramsCondition);
		List<Object[]> dataList = new ArrayList<Object[]>();
		String  isExport = (String) paramsCondition.get("isExport");
		//月数据导出
		String title;
		String field;
		if("month".equals(isExport)){
			title = "风控中心-月数据"+ DateTimeUtil.getNowDateChinaString();
			field="月份";
		}else{
			title = "风控中心-日数据"+ DateTimeUtil.getNowDateChinaString();
			field="日期";
		}
		Object[] objs = null;
		Map<String, Object> map = null;
		String[] rowsName = new String[]{"序号","合同编码", field, "批次", "是否逾期", "分子","分母","事业部","大区","营业部","客户经理","客户","分中心"};
		for (int i = 0; i < result.size(); i++) {
			map = result.get(i);
			objs = new Object[rowsName.length];
			objs[0] = i + 1;
			objs[1] = map.get("contract_no");
			objs[2] = map.get("record_date");
			objs[3] = map.get("batch");
			objs[4] = map.get("is_overdue");
			objs[5] = map.get("payed_money");
			objs[6] = map.get("repayment_money");
			objs[7] = map.get("business_unit_name");
			objs[8] = map.get("district_name");
			objs[9] = map.get("org_name");
			objs[10] = map.get("client_manager");
			objs[11] = map.get("client_name");
			objs[12] = map.get("name");
			dataList.add(objs);
		}
		ExportExcel ex = new ExportExcel(title, rowsName, dataList);
		ex.export(response);
	}

}
