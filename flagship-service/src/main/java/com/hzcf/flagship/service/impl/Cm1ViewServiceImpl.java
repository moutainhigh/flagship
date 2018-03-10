package com.hzcf.flagship.service.impl;

import com.hzcf.flagship.dao.AssetBusinessMapper;
import com.hzcf.flagship.dao.AssetDailyPerformanceMapper;
import com.hzcf.flagship.dao.AssetOrgStructMapper;
import com.hzcf.flagship.dao.RiskLoanCm1Mapper;
import com.hzcf.flagship.service.Cm1ViewService;
import com.hzcf.flagship.util.DateTimeUtil;
import com.hzcf.flagship.util.ExportExcel;
import com.hzcf.flagship.util.PageModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class Cm1ViewServiceImpl implements Cm1ViewService{

	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private RiskLoanCm1Mapper  riskLoanCm1Mapper;
	@Autowired
	private AssetDailyPerformanceMapper  assetDailyPerformanceMapper;
	@Autowired
	private AssetOrgStructMapper assetOrgStructMapper;
	@Autowired
	private AssetBusinessMapper assetBusinessMapper;
	/**
	 * 
	 * Description: 单日C-M1查看
	 * @return PageModel
	 */
	@Override
	public PageModel everyDayCm1View(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = riskLoanCm1Mapper.findEveryDayCm1View(paramsCondition);
		List <Map<String, Object>>  totalRecords= riskLoanCm1Mapper.findEveryDayCm1ViewCount(paramsCondition);
		pageModel.setList(data);
		Integer size = totalRecords.size();
		pageModel.setTotalRecords(size.longValue());
		return pageModel;
	}
	
	/**
	 * Description: 月度C-M1查看
	 * @param paramsCondition
	 * @return
	 * @author guodong
	 */
	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		List<Map<String, Object>> data = riskLoanCm1Mapper.findAllRetMapByPage(paramsCondition);
		pageModel.setList(data);
		return pageModel;
	}
	/**
	 * Description: 获取年份 
	 * @return
	 * @author guodong
	 */
	@Override
	public List<?> getYears(String flag) {
		if("cm1".equals(flag)){
			return riskLoanCm1Mapper.getYears();
		}else{
			return assetDailyPerformanceMapper.getYears();
		}
	}
	/**
	 * Description: 根据年获取月份
	 * @return
	 * @author guodong
	 */
	@Override
	public List<?> getMonths(String years,  String flag) {
		if("cm1".equals(flag)){
			return riskLoanCm1Mapper.getMonths(years);
		}else{
			return assetDailyPerformanceMapper.getMonths(years);
		}
	}
	/**
	 * Description: 根据月获取日
	 * @return
	 * @author guodong
	 */
	@Override
	public List<Map<String, Object>> getDays(String months) {
		return assetDailyPerformanceMapper.getDays(months);
	}
	/**
	 * Description: 获取事业部名称
	 * @return
	 * @author guodong
	 */
	@Override
	public List<?> getBusinessName() {
		return riskLoanCm1Mapper.getBusinessName();
	}
	/**
	 * Description: 查找历史业绩
	 * @param paramsCondition
	 * @return
	 * @author guodong
	 */
	@Override
	public PageModel findHistoryPerformanceList(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		if(null != paramsCondition.get("pageNo")){
			pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		}
		if(null != paramsCondition.get("pageSize")){
			pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		}
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = assetDailyPerformanceMapper.findAllRetMapByPage(paramsCondition);
		List<Map<String, Object>> totalRecords = assetDailyPerformanceMapper.findAllByPageCount(paramsCondition);
		Integer size = totalRecords.size();
		pageModel.setList(data);
		pageModel.setTotalRecords(size.longValue());
		return pageModel;
	}

	@Override
	public List<?> getBusinessNameWithResultsView() {
		return assetOrgStructMapper.getBusinessNameWithResultsView();
	}

	@Override
	public PageModel findStructMappingAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = assetOrgStructMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = assetOrgStructMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public PageModel findBusinessMappingAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = assetBusinessMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = assetBusinessMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public void exportExcelEveryDayView(Map<String, Object> paramsCondition, HttpServletResponse response) {
		List<Object[]> dataList = new ArrayList<Object[]>();
		List<Map<String, Object>> result = riskLoanCm1Mapper.findEveryDayCm1View(paramsCondition);
		String title = "单日C-M1数据导出-" + DateTimeUtil.getNowDateChinaString();
		String[] rowsName = new String[]{"序号", "日期", "部门", "当月目标", "C-M1"};
		Object[] objs = null;
		Map<String, Object> map = null;
		for (int i = 0; i < result.size(); i++) {
			map = result.get(i);
			objs = new Object[rowsName.length];
			objs[0] = i + 1;
			objs[1] = map.get("record_date");
			objs[2] = map.get("orgName");
			objs[3] = map.get("planValue");
			objs[4] = map.get("actualValue");
			dataList.add(objs);
		}
		ExportExcel ex = new ExportExcel(title, rowsName, dataList);
		ex.export(response);
	}

	@Override
	public void exportExcelMonthCM1list(Map<String, Object> paramsCondition, HttpServletResponse response) {
		List<Object[]> dataList = new ArrayList<Object[]>();
		List<Map<String, Object>> result = riskLoanCm1Mapper.findAllRetMapByPage(paramsCondition);
		String title = "月度C-M1数据导出-" + DateTimeUtil.getNowDateChinaString();
		String[] rowsName = new String[]{"序号", "日期", "部门", "当月目标", "C-M1"};
		Object[] objs = null;
		Map<String, Object> map = null;
		for (int i = 0; i < result.size(); i++) {
			map = result.get(i);
			objs = new Object[rowsName.length];
			objs[0] = i + 1;
			objs[1] = map.get("recordDate");
			objs[2] = map.get("orgName");
			objs[3] = map.get("planVal");
			objs[4] = map.get("actualVal");
			dataList.add(objs);
		}
		ExportExcel ex = new ExportExcel(title, rowsName, dataList);
		ex.export(response);
	}

}
