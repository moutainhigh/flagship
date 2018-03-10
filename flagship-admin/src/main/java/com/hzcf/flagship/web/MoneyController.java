package com.hzcf.flagship.web;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.hzcf.flagship.baseweb.BaseController;
import com.hzcf.flagship.baseweb.DataMsg;
import com.hzcf.flagship.enmu.TemplateConstants;
import com.hzcf.flagship.model.Employee;
import com.hzcf.flagship.model.MoneymgrOrgData;
import com.hzcf.flagship.service.FinanceJobService;
import com.hzcf.flagship.service.MoneyCompareService;
import com.hzcf.flagship.service.MoneyManpowerRosterService;
import com.hzcf.flagship.service.MoneyMonthSumService;
import com.hzcf.flagship.service.MoneyOrgMapService;
import com.hzcf.flagship.service.MoneyPerformanceExcludeService;
import com.hzcf.flagship.service.MoneymgrJobService;
import com.hzcf.flagship.service.OrgMapLogService;
import com.hzcf.flagship.service.TotalMonthPlanService;
import com.hzcf.flagship.util.DataUtil;
import com.hzcf.flagship.util.ImportUtil;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.RegExpUtil;
import com.hzcf.flagship.util.StringUtil;


/**
 *<dl>
 *<dt>类名：MoneyController.java</dt>
 *<dd>描述: 理财元数据</dd> 
 *<dd>创建时间： 2017年5月17日 下午3:16:39</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月17日 下午3:16:39    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Controller
@RequestMapping("/money")
public class MoneyController extends BaseController{

	@Autowired
	private MoneyManpowerRosterService manpowerRosterservice;
	@Autowired
	private MoneyCompareService moneyCompareService;
	@Autowired
	private TotalMonthPlanService totalMonthPlanService;
	@Autowired
	private MoneymgrJobService moneymgrJobService;
	@Autowired
	private FinanceJobService financeJobService;
	@Autowired
	private MoneyMonthSumService monthSumService;
	@Autowired
	private MoneyOrgMapService orgMapService;
	@Autowired
	private MoneyPerformanceExcludeService performanceExcludeService;
	@Autowired
	private OrgMapLogService orgMapLogService;
	private final static Logger logger = Logger.getLogger(MoneyController.class);

	/**
	 * 模板下载 通用
	 * @param filePath
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/downLoad")
	@ResponseBody
	public void downLoad(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileName = java.net.URLDecoder.decode(request.getParameter("fileName"), "utf-8");
		//String fileName = new String(request.getParameter("fileName").getBytes("ISO-8859-1"),"utf-8");
		String realPath = request.getSession().getServletContext().getRealPath(TemplateConstants.DOWNLOAD)+File.separator+fileName;
		DataUtil.downLoadTemplate(realPath, response);
	}
	
	
	/**
	 * 理财人力花名册上传 数据导入
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/manpowerRoster/doUpload")
	@ResponseBody
	public Map<String,Object> dataUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer j = null;
		String ruleName  = request.getParameter("ruleName");
		try {
			if (!file.isEmpty()) {
				//校验文件名是否一致
				if(!checkFileName(file,ruleName)){
					map.put("error", TemplateConstants.FILENAMEERRORMESSAGE);
					map.put("status", 1);//上传失败
					return map;
				};
				//文件上传
				File file2 = DataUtil.dataUpload(file, request, response);
				// 文件导入 
				List<List<Object>> list = ImportUtil.readExcel(file2);
				// 得到当前登录用户id
				Integer id = null;
				Employee employee = getSystemCurrentUser(request.getSession());
				if (null != employee) {
					id = employee.getEmployeeId();// 创建人id
				}
				//批量插入
				int errorCount = manpowerRosterservice.insertMoneymgrRoster(list,id);
				if(0 != errorCount){
					j = errorCount;
					map.put("status", 1);// 1 - 插入失败！
					map.put("error", j + TemplateConstants.CONTENTERRORMESSAGE);
				   return map;
				}else{
					map.put("count", list.size() - 1);
					map.put("status", 0);// 0 - 插入成功！
				}
			}
			return map;
		} catch (Exception e) {
			map.put("error", TemplateConstants.SYSTEMERRORMESSAGE);
			map.put("status", 1);// 1 - 插入失败！
			logger.error(e.getMessage()); 
			return map;
		}

	}
	
	/**
	 * 
	 * Description: 理财花名册分页查询列表
	 * 
	 * @param
	 * @return String
	 * @throws
	 * @Author
	 */
	@ResponseBody
	@RequestMapping(value="/manpowerRoster/pageList")
	public DataMsg pageList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			PageModel pageModel = manpowerRosterservice.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	
	/**
	 * 理财对比表上传 数据导入
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/compare/doUpload")
	@ResponseBody
	public Map<String,Object> compareDoUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer j = null;
		String ruleName  = request.getParameter("ruleName");
		try {
			if (!file.isEmpty()) {
				//校验文件名是否一致
				if(!checkFileName(file,ruleName)){
					map.put("error", TemplateConstants.FILENAMEERRORMESSAGE);
					map.put("status", 1);//上传失败
					return map;
				};
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				File file2 = DataUtil.dataUpload(file, request, response);

				// 文件导入 
				List<List<Object>> list = ImportUtil.readExcel(file2);
				List<MoneymgrOrgData> resultList = new ArrayList<MoneymgrOrgData>();
				Integer employeeId =null;
				for (int i = 1; i < list.size(); i++) {
					try {
						MoneymgrOrgData moneymgrOrgData = new MoneymgrOrgData();
						if (((String) list.get(i).get(0)).length() <= 50 && !StringUtils.isEmpty((String)list.get(i).get(0))) {
							moneymgrOrgData.setOrgName((String) list.get(i).get(0));// 机构名称
						} else {
							throw new RuntimeException();
						}
						if (((String) list.get(i).get(1)).length() <= 20) {
							moneymgrOrgData.setDistrictName((String) list.get(i).get(1));// 区域名称
						} else {
							throw new RuntimeException();
						}
						if (((String) list.get(i).get(2)).length() <= 20) {
							moneymgrOrgData.setDistrictPrincipal((String) list.get(i).get(2));// 区域负责人
						} else {
							throw new RuntimeException();
						}
						// 本月计划任务无数据时，默认是0
						if (StringUtils.isEmpty((String)list.get(i).get(3))) {
							moneymgrOrgData.setMonthPlan(new BigDecimal(0));// 本月计划
						} else {
							if (RegExpUtil.checkIsDecimalsOrInt(String.valueOf(list.get(i).get(3)))) {
								moneymgrOrgData.setMonthPlan(new BigDecimal(String.valueOf(list.get(i).get(3))));// 本月计划
							} else {
								throw new RuntimeException();
							}
						}
						if (!StringUtils.isEmpty((String) list.get(i).get(4))) {
							if (RegExpUtil.checkIsInt((String) list.get(i).get(4))) {
								moneymgrOrgData.setCounselorNumLastMonth(Integer.valueOf(String.valueOf(list.get(i).get(4))));// 上月咨询师人数
							} else {
								throw new RuntimeException();
							}
						}
						Date date = null;
						if (!StringUtils.isEmpty((String) list.get(i).get(5))
								&& RegExpUtil.checkIsDate((String) list.get(i).get(5))) {
							date = sdf.parse(String.valueOf(list.get(i).get(5)));// 月份
						} else {
							throw new RuntimeException();
						}
						moneymgrOrgData.setRecordDate(date);
						// 得到当前登录用户id
						Employee employee = getSystemCurrentUser(request.getSession());
						if (null != employee) {
							employeeId = employee.getEmployeeId();
							moneymgrOrgData.setCreatorId(employee.getEmployeeId());// 创建人id
						}
						moneymgrOrgData.setCreateTime(new Date());
						resultList.add(moneymgrOrgData);
					} catch (Exception e) {
						e.printStackTrace();
						if (0 != i) {
							j = i;
							map.put("error", (j + 1) + TemplateConstants.CONTENTERRORMESSAGE);
							map.put("status", 1);// 1 - 插入失败！
							return map;
						}
					}

				}
				moneyCompareService.insertMoneyCompare(resultList);
				totalMonthPlanService.insertTotalMonthPlan(resultList, employeeId);
				map.put("count", list.size() - 1);
				map.put("status", 0);// 0 - 插入成功！
			}
			return map;
		} catch (Exception e) {
			map.put("error", TemplateConstants.SYSTEMERRORMESSAGE);
			map.put("status", 1);// 1 - 插入失败！
			logger.error(e.getMessage()); 
			return map;
		}

	}
	
	/**
	 * 
	 * Description: 理财对比表查询列表
	 * 
	 * @param
	 * @return String
	 * @throws
	 * @Author
	 */
	@ResponseBody
	@RequestMapping(value="/compare/pageList")
	public DataMsg comparePageList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			String orgName = StringUtil.trim(request.getParameter("orgName"));// 机构名称
			if (StringUtil.isNotBlank(orgName)) {
				paramsCondition.put("orgName", orgName);
			}
			String districtName = StringUtil.trim(request.getParameter("districtName"));//区域名称
			if (StringUtil.isNotBlank(districtName)) {
				paramsCondition.put("districtName", districtName);
			}
			String start_time = request.getParameter("start_time");
			if(StringUtil.isNotBlank(start_time)) {
				paramsCondition.put("start_time", start_time);
			}
			String end_time = request.getParameter("end_time");
			if(StringUtil.isNotBlank(end_time)) {
				paramsCondition.put("end_time", end_time);
			}
			PageModel pageModel = moneyCompareService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	@ResponseBody
	@RequestMapping("/timedTask")
	public String timedTask(){
		try {
			moneymgrJobService.updateDailyPerformancePageData();
			
		} catch (Exception e) {
			logger.error("更新日业绩结果页数据出错");
			e.printStackTrace();
		}
		try {
			moneymgrJobService.myInsertAllOrgAccumuPerformanceData();
		} catch (Exception e) {
			logger.error("更新机构月累计数据出错");
			e.printStackTrace();
		}
		try {
			moneymgrJobService.currentMonthInsertMoneymgrDistrictPage();
		} catch (Exception e) {
			logger.error(" 更新当月区域结果数据出错");
			//logger.error(e.getMessage()); 
			e.printStackTrace();
		}
		try {
			moneymgrJobService.updateMoneymgrEfficiencyPage();
		} catch (Exception e) {
			logger.error(" 更新理财人员人效结果数据出错");
			e.printStackTrace();
		}
		try {
			financeJobService.insertFinanceDailyPage();
		} catch (Exception e) {
			logger.error("插入融资日结果数据出错");
			e.printStackTrace();
		}
		return "刷新数据结束";
	}
	
	/**
	 * 理财月度累计表上传 数据导入
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/monthSum/doUpload")
	@ResponseBody
	public Map<String, Object>monthSumDoUpload(MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer j = null;
		String ruleName  = request.getParameter("ruleName");
		try {
			if (!file.isEmpty()) {
				//校验文件名是否一致
				if(!checkFileName(file,ruleName)){
					map.put("error", TemplateConstants.FILENAMEERRORMESSAGE);
					map.put("status", 1);//上传失败
					return map;
				};
				// 文件上传
				File file2 = DataUtil.dataUpload(file, request, response);
				// 文件导入
				List<List<Object>> list = ImportUtil.readExcel(file2);
				// 得到当前登录用户id
				Integer id = null;
				Employee employee = getSystemCurrentUser(request.getSession());
				if (null != employee) {
					id = employee.getEmployeeId();// 创建人id
				}
				// 批量插入
				int errorCount = monthSumService.insertMoneymgrMonthHistory(list, id);
				if(0 != errorCount){
					j = errorCount;
					map.put("status", 1);// 1 - 插入失败！
					map.put("error", j + TemplateConstants.CONTENTERRORMESSAGE);
				   return map;
				}else{
					map.put("count", list.size() - 1);
					map.put("status", 0);// 0 - 插入成功！
				}
			}
			return map;
		} catch (Exception e) {
			map.put("error", TemplateConstants.SYSTEMERRORMESSAGE);
			map.put("status", 1);// 1 - 插入失败！
			logger.error(e.getMessage()); 
			return map;
		}

	}

	/**
	 * 
	 * Description: 理财月度累计查询列表
	 * 
	 * @param
	 * @return String
	 * @throws @Author
	 */
	@ResponseBody
	@RequestMapping(value = "/monthSum/pageList")
	public DataMsg monthPageList(HttpServletRequest request, DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			String orgName = StringUtil.trim(request.getParameter("orgName"));// 机构名称
			if (StringUtil.isNotBlank(orgName)) {
				paramsCondition.put("orgName", orgName);
			}
			String start_time = request.getParameter("start_time");
			if(StringUtil.isNotBlank(start_time)) {
				paramsCondition.put("start_time", start_time);
			}
			String end_time = request.getParameter("end_time");
			if(StringUtil.isNotBlank(end_time)) {
				paramsCondition.put("end_time", end_time);
			}
			PageModel pageModel = monthSumService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dataMsg;
	}
	
	/**
	 * 理财机构名称对应表上传 数据导入
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/orgMap/doUpload")
	@ResponseBody
	public Map<String, Object> orgMapDoUpload(MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer j = null;
		String ruleName  = request.getParameter("ruleName");
		try {
			if (!file.isEmpty()) {
				//校验文件名是否一致
				if(!checkFileName(file,ruleName)){
					map.put("error", TemplateConstants.FILENAMEERRORMESSAGE);
					map.put("status", 1);//上传失败
					return map;
				};
				// 文件上传
				File file2 = DataUtil.dataUpload(file, request, response);
				// 文件导入
				List<List<Object>> list = ImportUtil.readExcel(file2);
				// 得到当前登录用户id
				Integer id = null;
				Employee employee = getSystemCurrentUser(request.getSession());
				if (null != employee) {
					id = employee.getEmployeeId();// 创建人id
				}
				// 批量插入
				int errorCount = orgMapService.insertOrgMap(list, id);
					if(0 != errorCount){
						j = errorCount;
						map.put("status", 1);// 1 - 插入失败！
						map.put("error", j + TemplateConstants.CONTENTERRORMESSAGE);
					   return map;
					}else{
						map.put("count", list.size() - 1);
						map.put("status", 0);// 0 - 插入成功！
					}
			}
			return map;
		} catch (Exception e) {
			map.put("error", TemplateConstants.SYSTEMERRORMESSAGE);
			map.put("status", 1);// 1 - 插入失败！
			logger.error(e.getMessage()); 
			return map;
		}

	}


	/**
	 * 
	 * Description:理财机构名称对应表查询列表
	 * 
	 * @param
	 * @return String
	 * @throws @Author
	 */
	@ResponseBody
	@RequestMapping(value = "/orgMap/pageList")
	public DataMsg orgMapPageList(HttpServletRequest request, DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			PageModel pageModel = orgMapService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dataMsg;
	}
	/**
	 * 理财业绩排除表上传 数据导入
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/performance/doUpload")
	@ResponseBody
	public Map<String, Object> performanceDoUpload(MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer j = null;
		String ruleName  = request.getParameter("ruleName");
		try {
			if (!file.isEmpty()) {
				//校验文件名是否一致
				if(!checkFileName(file,ruleName)){
					map.put("error", TemplateConstants.FILENAMEERRORMESSAGE);
					map.put("status", 1);//上传失败
					return map;
				};
				// 文件上传
				File file2 = DataUtil.dataUpload(file, request, response);
				// 文件导入
				List<List<Object>> list = ImportUtil.readExcel(file2);
				// 得到当前登录用户id
				Integer id = null;
				Employee employee = getSystemCurrentUser(request.getSession());
				if (null != employee) {
					id = employee.getEmployeeId();// 创建人id
				}
				// 批量插入
				int errorCount = performanceExcludeService.insertPerformanceExclude(list, id);
				if(0 != errorCount){
					j = errorCount;
					map.put("status", 1);// 1 - 插入失败！
					map.put("error", j + TemplateConstants.CONTENTERRORMESSAGE);
				   return map;
				}else{
					map.put("count", list.size() - 1);
					map.put("status", 0);// 0 - 插入成功！
				}
			}
			return map;
		} catch (Exception e) {
			map.put("error", TemplateConstants.SYSTEMERRORMESSAGE);
			map.put("status", 1);// 1 - 插入失败！
			logger.error(e.getMessage()); 
			return map;
		}

	}



	/**
	 * 
	 * Description: 理财业绩排除查询列表
	 * 
	 * @param
	 * @return String
	 * @throws @Author
	 */
	@ResponseBody
	@RequestMapping(value = "/performance/pageList")
	public DataMsg performancePageList(HttpServletRequest request, DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			PageModel pageModel = performanceExcludeService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dataMsg;
	}
	
	/**
	 * 校验上传文件名是否一致
	 * @param file
	 * @param ruleName 
	 */

	public boolean checkFileName(MultipartFile file, String ruleName) {
		// 得到上传文件名
		String fileName = file.getOriginalFilename();
		String fileNamet =fileName.substring(0, fileName.indexOf("."));
		//得到规定文件名
		String ruleNamet = ruleName.substring(0,ruleName.indexOf("."));
		if(fileNamet.equals(ruleNamet)){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * Description: 跳转到理财机构日志信息列表页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author tieguowei
	 * Create Date: 2017-07-03
	 */
	@RequestMapping("toOrgLogInfo")
	public String toOrgLogInfo(String refreshTag,String messageCode,Model model) {
		showMessageAlert(refreshTag,messageCode,model);
		return "app/data/moneyOrgLogInfo_list";
	}
	
	/**
	 * 
	 * Description: 理财机构日志信息列表
	 * 
	 * @param
	 * @return String
	 * @throws @Author
	 */
	@ResponseBody
	@RequestMapping(value = "/orgLogInfo/pageList")
	public DataMsg orgLogInfoPageList(HttpServletRequest request, DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			paramsCondition.put("type", "licai");
			PageModel pageModel = orgMapLogService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dataMsg;
	}
}
