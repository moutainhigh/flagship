package com.hzcf.flagship.web;


import com.hzcf.flagship.baseweb.BaseController;
import com.hzcf.flagship.baseweb.DataMsg;
import com.hzcf.flagship.constant.Constants;
import com.hzcf.flagship.enmu.TemplateConstants;
import com.hzcf.flagship.model.Employee;
import com.hzcf.flagship.service.AssetPersonService;
import com.hzcf.flagship.service.AssetPlanService;
import com.hzcf.flagship.service.Cm1ViewService;
import com.hzcf.flagship.util.*;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;


/**
*<dl>
*<dt>类名：StrategicOperationsController.java</dt>
*<dd>描述: 战略运营部需求</dd>
*<dd>创建时间： 2017年11月13日 下午1:41:35</dd>
*<dd>创建人：Tieguowei</dd>
*<dt>版本历史: </dt>
* <pre>
* Date Author Version Description
* ------------------------------------------------------------------
* 2017年11月13日 下午1:41:35 Tieguowei 1.0 1.0 Version
* </pre>
*</dl>
*/
@Controller  
@RequestMapping("/stratage")
public class StrategicOperationsController extends BaseController{

	@Autowired
	private Cm1ViewService cm1ViewService;

	@Autowired
	private AssetPlanService assetPlanService;

	@Autowired
	private AssetPersonService assetPersonService;

	private static final String PLAN_FILE_TABLE_TITLE = "[业务编号, 业务, 部门编号, 部门, 年份, 年度总目标, 1月, 2月, 3月, 4月, 5月, 6月, 7月, 8月, 9月, 10月, 11月, 12月]";
	private static final String PERSON_FILE_TABLE_TITLE = "[部门编号, 部门, 姓名, 大区编号（非必填）, 大区（非必填）, 营业部编号（非必填）, 营业部（非必填）, 团队（非必填）, 小团（非必填）, 员工编号, 手机号, 离职时间(YYYY/MM/DD)(非必填)]";


	/**
	 * 
	 * Description:单日CM1查询列表
	 * 
	 * @param
	 * @return String
	 * @throws
	 * @Author
	 */
	@ResponseBody
	@RequestMapping(value="/everyDayView")
	public DataMsg orgMapPageList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			String orgNo = StringUtil.trim(request.getParameter("orgNo"));// 部门编号
			if (StringUtil.isNotBlank(orgNo)) {
				if("0".equals(orgNo)){
					paramsCondition.put("orgNo", null);
				}else{
					paramsCondition.put("orgNo", orgNo);
				}
			}
			String yesterday = DateUtil.getYesterdayBythisDayT(new Date());
			String start_time = request.getParameter("start_time");
			if(StringUtil.isNotBlank(start_time)) {
				paramsCondition.put("start_time", start_time);
			}else{
				paramsCondition.put("start_time", yesterday);
			}
			String end_time = request.getParameter("end_time");
			if(StringUtil.isNotBlank(end_time)) {
				paramsCondition.put("end_time", end_time);
			}else{
				paramsCondition.put("end_time", yesterday);
			}
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			PageModel pageModel = cm1ViewService.everyDayCm1View(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}

	/**
	 * 导出单日C-M1 Excel
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/everyDayView/exportExcel")
	public void exportExcelEveryDayView(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			// 部门编号
			String orgNo = StringUtil.trim(request.getParameter("orgNo"));
			if (StringUtil.isNotBlank(orgNo)) {
				if ("0".equals(orgNo)) {
					paramsCondition.put("orgNo", null);
				} else {
					paramsCondition.put("orgNo", orgNo);
				}
			}
			String yesterday = DateUtil.getYesterdayBythisDayT(new Date());
			String start_time = request.getParameter("start_time");
			if (StringUtil.isNotBlank(start_time)) {
				paramsCondition.put("start_time", start_time);
			} else {
				paramsCondition.put("start_time", yesterday);
			}
			String end_time = request.getParameter("end_time");
			if (StringUtil.isNotBlank(end_time)) {
				paramsCondition.put("end_time", end_time);
			} else {
				paramsCondition.put("end_time", yesterday);
			}
			cm1ViewService.exportExcelEveryDayView(paramsCondition, response);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Description: 跳转至月度C-M1查看 分页查询页面
	 * @param refreshTag
	 * @param messageCode
	 * @param model
	 * @return
	 * @author guodong
	 */
	@RequestMapping("toMonthCM1list")
	public String toEmpList(String refreshTag,String messageCode,Model model) {
		showMessageAlert(refreshTag,messageCode,model);
		return "app/strategicOperating/monthCM1list";
	}
	/**
	 * Description: 月度cm1 查看
	 * @param request
	 * @param dataMsg
	 * @return
	 * @author guodong
	 */
	@ResponseBody
	@RequestMapping(value="/monthCM1list")
	public DataMsg pageList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			getSelectCM1Params(request, paramsCondition);
			PageModel pageModel = cm1ViewService.findAllByPage(paramsCondition);
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}

	/**
	 * 导出月度C-M1 Excel
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/monthCM1list/exportExcel")
	public void exportExcelMonthCM1list(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			getSelectCM1Params(request, paramsCondition);
			cm1ViewService.exportExcelMonthCM1list(paramsCondition,response);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	/**
	 * Description: 组装 月度C-M1查看list动态查询条件
	 * @param request 年 月 机构
	 * @param paramsCondition 
	 * @author guodong
	 */
	public void getSelectCM1Params(HttpServletRequest request, Map<String, Object> paramsCondition) {
		String year = request.getParameter("years");
		String month = request.getParameter("months");
		if (StringUtil.isNotBlank(year) &&  StringUtil.isNotBlank(month)) {
			Integer valueOf = Integer.valueOf(month.split("月")[0]);
			if(valueOf<10){
				month = "0" + valueOf;
			}else{
				month = valueOf+"";
			}
			paramsCondition.put("day",  year + "-" + month + "-26");
			paramsCondition.put("month",year+month);
		}else{
			String day = DateTimeUtil.getPreviousMonthFirst();
			paramsCondition.put("month",day.substring(0, 4)+ day.substring(5, 7));
			paramsCondition.put("day",  day);
		}
		String orgNo = request.getParameter("orgNo");
		if (StringUtil.isNotBlank(orgNo)) {
			if(!orgNo.equals("0")){
				paramsCondition.put("orgNo", orgNo);
			}
		}
	}
	/**
	 * Description: 获取年份 
	 * @param request
	 * @return
	 * @author guodong
	 */
	@ResponseBody
	@RequestMapping(value="/getYears")
	public JSONArray getYears(HttpServletRequest request) {
		List<?> list=cm1ViewService.getYears(request.getParameter("flag"));
		return JSONArray.fromObject(list);
	}
	/**
	 * Description: 获取月份
	 * @param request
	 * @return
	 * @author guodong
	 */
	@ResponseBody
	@RequestMapping(value="/getMonths")
	public JSONArray getMonths(HttpServletRequest request) {
		List<?> list=cm1ViewService.getMonths(request.getParameter("years"),request.getParameter("flag"));
		return JSONArray.fromObject(list);
	}
	/**
	 * Description: 获取日
	 * @param request
	 * @return
	 * @author guodong
	 */
	@ResponseBody
	@RequestMapping(value="/getDays")
	public JSONArray getDays(HttpServletRequest request) {
		String months = request.getParameter("months");
		String years = request.getParameter("years");
		List<Map<String, Object>> list = new ArrayList<>();
		if(!StringUtil.isBlank(months)&& !StringUtil.isBlank(years)){
			Integer valueOf = Integer.valueOf(months);
			if(valueOf<10){
				months = "0" + valueOf;
			}
			list = cm1ViewService.getDays(years+"-"+months+"-");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("days", "全部");
		list.add(0, map);
		return JSONArray.fromObject(list);
	}
	/**
	 * Description: 获取事业部名称(C-M1查看)
	 * @param request
	 * @return
	 * @author guodong
	 */
	@ResponseBody
	@RequestMapping(value="/getBusinessName")
	public JSONArray getBusinessName(HttpServletRequest request) {
		List<?> list=cm1ViewService.getBusinessName();
		return JSONArray.fromObject(list);
	}
	
	/**
	 * Description: 获取事业部名称 (业绩查看)
	 * @param request
	 * @return
	 * @author tgw
	 */
	@ResponseBody
	@RequestMapping(value="/getBusinessNameWithResultsView")
	public JSONArray getBusinessNameWithResultsView(HttpServletRequest request) {
		List<?> list=cm1ViewService.getBusinessNameWithResultsView();
		return JSONArray.fromObject(list);
	}
	
	/**
	 * Description: 历史业绩list
	 * @param request
	 * @param dataMsg
	 * @return
	 * @author guodong
	 */
	@ResponseBody
	@RequestMapping(value="/historyPerformanceList")
	public DataMsg historyPerformanceList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			getSelectHistoryListParams(request, paramsCondition);
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			PageModel pageModel = cm1ViewService.findHistoryPerformanceList(paramsCondition);
			dataMsg.setRows(pageModel.getList());
			dataMsg.setTotal(pageModel.getTotalRecords());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}


	/**
	 * Description: 组装历史业绩查看查询数据
	 * @param request
	 * @param paramsCondition
	 * @author guodong
	 */
	public void getSelectHistoryListParams(HttpServletRequest request, Map<String, Object> paramsCondition) {
		String months = request.getParameter("months");
		String years = request.getParameter("years");
		String days = request.getParameter("days");
		String orgNo = StringUtil.trim(request.getParameter("orgNo"));
		if(StringUtil.isNotBlank(days) && StringUtil.isNotBlank(months)
				&& StringUtil.isNotBlank(years)){
			Integer valueOf = Integer.valueOf(months.split("月")[0]);
			if(valueOf<10){
				months = "0" + valueOf;
			}else{
				months = valueOf+"";
			}
			if("全部".equals(days)){
				paramsCondition.put("beginDate", years+"-"+months+"-"+"01");
				paramsCondition.put("endDate", years+"-"+months+"-"+"31");
				paramsCondition.put("all", "all");
			}else{
				paramsCondition.put("beginDate", years+"-"+months+"-"+days);
				
			}
		}else{
			paramsCondition.put("beginDate", DateTimeUtil.getLastMonthFirstDays());
			paramsCondition.put("endDate", DateTimeUtil.getLastMonthLastDays());
			paramsCondition.put("all", "all");
		}
		if (StringUtil.isNotBlank(orgNo)) {
			if(!"0".equals(orgNo)){
				paramsCondition.put("orgNo", orgNo);
			}
		}
	}
	
	/**
	 * 
	 * Description:本月业绩查看列表
	 * 
	 * @param
	 * @return String
	 * @throws
	 * @Author tgw
	 */
	@ResponseBody
	@RequestMapping(value="/thisMonthView")
	public DataMsg thisMonthView(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			thisMonthViewParameter(request, paramsCondition);
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			PageModel pageModel = cm1ViewService.findHistoryPerformanceList(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	
	/**
	 * 
	 * Description:本月业绩查看列表 导出
	 * @throws
	 * @Author tgw
	 */
	@ResponseBody
	@RequestMapping(value="/exportThisMonthView")
	public void exportThisMonthView(HttpServletRequest request,HttpServletResponse response) {
		try {
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			String state = StringUtil.trim(request.getParameter("state"));// 导出标识
			if(StringUtil.isNotBlank(state)) {
				paramsCondition.put("state", 1);
			}
			String sheetName;
	    	String titleName;
	    	if("1".equals(state)){
	    		 sheetName = "本月业绩";
		    	 titleName = simple.format(new Date())+" 本月业绩";
		    	 thisMonthViewParameter(request, paramsCondition);
	    	}else{
	    		sheetName = "历史业绩";
		    	 titleName = simple.format(new Date())+" 历史业绩";
		    	 getSelectHistoryListParams(request, paramsCondition);
	    	}
			
			PageModel pageModel = cm1ViewService.findHistoryPerformanceList(paramsCondition);
			List<Map<String, Object>> result = pageModel.getList();
			// 1:表头名称  2：表头索引 3：表头层级   4：父表头索引 
	    	String[][] header = {
	    			 { "NO","0","1","-1"},
					 {"日期","1","1","-1"},
					 {"部门","2","1","-1"},
					 {"信贷业务","3","1","-1"},
					 {"月度目标（元）","4","2","3"},
					 {"单日业绩（元）","5","2","3"},
					 {"累计业绩（元）","6","2","3"},
					 {"达成率","7","2","3"},
					 {"借款人意外险","8","1","-1"},
					 {"月度目标（元）","9","2","8"},
					 {"单日业绩（元）","10","2","8"},
					 {"累计业绩（元）","11","2","8"},
					 {"达成率","12","2","8"},
					 {"车险（台）","13","1","-1"},
					 {"月度目标（台）","14","2","13"},
					 {"单日业绩（台）","15","2","13"},
					 {"累计业绩（台）","16","2","13"},
					 {"达成率","17","2","13"},
					 {"车险（保费）","18","1","-1"},
					 {"月度目标（元）","19","2","18"},
					 {"单日业绩（元）","20","2","18"},
					 {"累计业绩（元）","21","2","18"},
					 {"达成率","22","2","18"},
					 {"寿险","23","1","-1"},
					 {"月度目标（元）","24","2","23"},
					 {"单日业绩（元）","25","2","23"},
					 {"累计业绩（元）","26","2","23"},
					 {"达成率","27","2","23"}
				
	    	};
	    	int length = CustomHeaderExport.getAllOverNodeCount(header);
	    	
	    	
	        List<Object[]>  dataList = new ArrayList<Object[]>();
	        Object[] objs = null;
	        Map<String, Object> promotionMap = null;
	        for (int i = 0; i < result.size(); i++) {
	        	promotionMap = result.get(i);
	            objs = new Object[length];
	            objs[0] = i+1;
	            objs[1] = promotionMap.get("recordDate");
	            objs[2] = promotionMap.get("orgName");
	            objs[3] = promotionMap.get("creditPlan");
	            objs[4] = promotionMap.get("creditDailyVal");
	            objs[5] = promotionMap.get("creditTotalVal");
	            objs[6] = promotionMap.get("creditRate");
	            objs[7] = promotionMap.get("accidentPlan");
	            objs[8] = promotionMap.get("accidentDailyVal");
	            objs[9] = promotionMap.get("accidentTotalVal");
	            objs[10] = promotionMap.get("accidentRate");
	            
	            objs[11] = promotionMap.get("carInsurancePlan");
	            objs[12] = promotionMap.get("carInsuranceDailyVal");
	            objs[13] = promotionMap.get("carInsuranceTotalVal");
	            objs[14] = promotionMap.get("carInsuranceRate");
	            
	            objs[15] = promotionMap.get("carInsuranceNumPlan");
	            objs[16] = promotionMap.get("carInsuranceNumDailyVal");
	            objs[17] = promotionMap.get("carInsuranceNumTotalVal");
	            objs[18] = promotionMap.get("carInsuranceNumRate");
	            
	            objs[19] = promotionMap.get("lifePlan");
	            objs[20] = promotionMap.get("lifeDailyVal");
	            objs[21] = promotionMap.get("lifeTotalVal");
	            objs[22] = promotionMap.get("lifeRate");
	            dataList.add(objs);
	        }
	        CustomHeaderExport.createHeaderAndExport(header, sheetName, titleName, 0, dataList, response);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	

	/**
	 * 
	 * Description:本月业绩查看列表 参数
	 */
	private void thisMonthViewParameter(HttpServletRequest request, Map<String, Object> paramsCondition) {
		String orgNo = StringUtil.trim(request.getParameter("orgNo"));// 部门编号
		if (StringUtil.isNotBlank(orgNo)) {
			if("0".equals(orgNo)){
				paramsCondition.put("orgNo", null);
			}else{
				paramsCondition.put("orgNo", orgNo);
			}
		}
		String yesterday = DateUtil.getYesterdayBythisDayT(new Date());
		String start_time = request.getParameter("start_time");
		if(StringUtil.isNotBlank(start_time)) {
			paramsCondition.put("beginDate", start_time);
		}else{
			paramsCondition.put("beginDate", yesterday);
		}
		String end_time = request.getParameter("end_time");
		if(StringUtil.isNotBlank(end_time)) {
			paramsCondition.put("endDate", end_time);
		}else{
			paramsCondition.put("endDate", yesterday);
		}
		paramsCondition.put("all", 1);
	}
	
	/**
	 * 
	 * Description: 组织机构编码对应列表
	 * 
	 * @Author
	 */
	@ResponseBody
	@RequestMapping(value="/structMapping")
	public DataMsg structMapping(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			PageModel pageModel = cm1ViewService.findStructMappingAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 业务编码对应列表
	 * 
	 * @Author
	 */
	@ResponseBody
	@RequestMapping(value="/businessMapping")
	public DataMsg businessMapping(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			PageModel pageModel = cm1ViewService.findBusinessMappingAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}

	/**
	 * Description: 融资营业部对应表查询列表
	 *
	 * @param
	 * @return String
	 * @throws
	 * @Author
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value = "/assetPlan")
	public DataMsg assetPlanList(HttpServletRequest request, DataMsg dataMsg,
								 @RequestParam(defaultValue = "1",required = false) int page,
								 @RequestParam(defaultValue = Constants.PAGE_SIZE+"",required = false) int rows,
								 String orgNo, String businessNo, String year) {
		try {
			PageModel pageModel = assetPlanService.queryAssetPlanList(page, rows, orgNo, businessNo, year);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dataMsg;
	}

	/**
	 * 风控目标数据导入
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/assetPlan/assetPlanUpLoad", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> assetPlanUpLoad(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		Integer errorLineNo = null;
		try {
			if (file != null && !file.isEmpty()) {
				// 文件导入
				File file2 = DataUtil.dataUpload(file, request, response);
				List<List<Object>> list = ImportRiskPlanUtil.readExcel(file2);
				// 校验文件正确
				if (!checkTableTitle(list, PLAN_FILE_TABLE_TITLE)) {
					map.put("error", TemplateConstants.FILENAMEERRORMESSAGE);
					map.put("status", TemplateConstants.UPLOAD_FAILURE);
					return map;
				}

				// 得到当前登录用户id
				Integer userId = null;
				Employee employee = getSystemCurrentUser(request.getSession());
				if (null != employee) {
					userId = employee.getEmployeeId();
				}
				logger.debug("批量插入业绩目标assetPlan数据,操作人userId:" + userId);
				errorLineNo = assetPlanService.insertAssetPlanBatch(list, userId);
				logger.debug("批量插入业绩目标assetPlan数据,errorCount:" + errorLineNo);
				if (errorLineNo == 0) {
					map.put("count", list.size() - 1);
					map.put("status", TemplateConstants.UPLOAD_SUCCESS);
				} else {
					map.put("status", TemplateConstants.UPLOAD_FAILURE);
					map.put("error", errorLineNo + TemplateConstants.CONTENTERRORMESSAGE);
				}
			} else {
				map.put("status", TemplateConstants.UPLOAD_FAILURE);
				map.put("error", "上传文件为空，请选择文件！");
			}
			return map;
		} catch (Exception e) {
			map.put("error", TemplateConstants.SYSTEMERRORMESSAGE);
			map.put("status", TemplateConstants.UPLOAD_FAILURE);
			logger.error(e);
			return map;
		}
	}

	/**
	 * 校验表头是否正确
	 * @param list
	 * @return
	 */
	private boolean checkTableTitle(List<List<Object>> list, String tableTitle) {
		if (list == null || list.isEmpty()) {
			return false;
		}
		String s = list.get(0).toString();
		if (!StringUtil.equals(s, tableTitle)) {
			return false;
		}
		return true;
	}

	/**
	 * Description: 获取组织机构(下拉框)
	 *
	 * @return
	 * @author tgw
	 */
	@ResponseBody
	@RequestMapping(value = "/assetPlan/selectAssetBusiness")
	public JSONArray selectAssetBusiness() {
		List<?> list = assetPlanService.selectAssetBusiness();
		return JSONArray.fromObject(list);
	}

	/**
	 * Description: 获取有效的年份(下拉框)
	 *
	 * @return
	 * @author tgw
	 */
	@ResponseBody
	@RequestMapping(value = "/assetPlan/selectUsefulYear")
	public JSONArray selectUsefulYear() {
		List<?> list = assetPlanService.selectUsefulYear();
		return JSONArray.fromObject(list);
	}

	/**
	 * Description: 战略运营中心-花名册列表查询
	 *
	 * @param
	 * @return String
	 * @throws
	 * @Author
	 */
	@ResponseBody
	@RequestMapping(value = "/assetPerson")
	public DataMsg assetPersonList(HttpServletRequest request, DataMsg dataMsg,
								   @RequestParam(defaultValue = "1", required = false) int page,
								   @RequestParam(defaultValue = Constants.PAGE_SIZE + "", required = false) int rows,
								   String depNo, String name, String mobile) {
		try {
			logger.debug("战略运营中心-花名册列表条件查询 name:"+name);
			PageModel pageModel = assetPersonService.queryAssetPersonList(page, rows, depNo, name, mobile);
			dataMsg.setTotal(pageModel == null ? 0L : pageModel.getTotalRecords());
			dataMsg.setRows(pageModel == null ? Collections.emptyList() : pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dataMsg;
	}

	/**
	 *花名册数据导入
	 *
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/assetPerson/assetPersonUpLoad", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> assetPersonUpLoad(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		Integer errorLineNo = null;
		try {
			if (file != null && !file.isEmpty()) {
				// 文件导入
				File file2 = DataUtil.dataUpload(file, request, response);
				List<List<Object>> list = ImportRiskPlanUtil.readExcel(file2);

				// 校验文件正确
				if (!checkTableTitle(list, PERSON_FILE_TABLE_TITLE)) {
					map.put("error", TemplateConstants.FILENAMEERRORMESSAGE);
					map.put("status", TemplateConstants.UPLOAD_FAILURE);
					return map;
				}
				// 得到当前登录用户id
				Integer userId = null;
				Employee employee = getSystemCurrentUser(request.getSession());
				if (null != employee) {
					userId = employee.getEmployeeId();
				}
				logger.debug("批量插入业绩目标assetPerson数据,操作人userId:" + userId);
				errorLineNo = assetPersonService.insertassetPersonBatch(list, userId);
				logger.debug("批量插入业绩目标assetPerson数据,errorCount:" + errorLineNo);
				if (errorLineNo == 0) {
					map.put("count", list.size() - 1);
					map.put("status", TemplateConstants.UPLOAD_SUCCESS);
				} else {
					map.put("status", TemplateConstants.UPLOAD_FAILURE);
					map.put("error", errorLineNo + TemplateConstants.CONTENTERRORMESSAGE);
				}
			} else {
				map.put("status", TemplateConstants.UPLOAD_FAILURE);
				map.put("error", "上传文件为空，请选择文件！");
			}
			return map;
		} catch (DuplicateKeyException e) {
			String eMessage = e.getCause().getMessage();
			String depKey = eMessage.substring(eMessage.indexOf("key '") + 5, eMessage.length() - 1);
			String dupValue = eMessage.substring(eMessage.indexOf("entry '")+7, eMessage.indexOf("' for"));
			if ("mobile_union".equalsIgnoreCase(depKey)){
				depKey = "手机号";
			}else if("person_no_union".equalsIgnoreCase(depKey)){
				depKey = "员工编号";
			}
			map.put("error", String.format(TemplateConstants.DUPLICATE_KEY_EXCEPTION_MESSAGE, depKey, dupValue));
			map.put("status", TemplateConstants.UPLOAD_FAILURE);
			logger.error(e);
			return map;
		} catch (Exception e) {
			map.put("error", TemplateConstants.SYSTEMERRORMESSAGE);
			map.put("status", TemplateConstants.UPLOAD_FAILURE);
			logger.error(e);
			return map;
		}
	}
}
