package com.hzcf.flagship.web;

import java.io.File;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.hzcf.flagship.baseweb.BaseController;
import com.hzcf.flagship.baseweb.DataMsg;
import com.hzcf.flagship.constant.Constants;
import com.hzcf.flagship.enmu.TemplateConstants;
import com.hzcf.flagship.model.AppPageModel;
import com.hzcf.flagship.model.Employee;
import com.hzcf.flagship.model.RiskEmail;
import com.hzcf.flagship.model.RiskOrgData;
import com.hzcf.flagship.model.RiskProduct;
import com.hzcf.flagship.model.RiskProductWarning;
import com.hzcf.flagship.model.RiskSeparate;
import com.hzcf.flagship.model.RiskSubcenter;
import com.hzcf.flagship.service.EmployeeService;
import com.hzcf.flagship.service.ManualAdjustmentService;
import com.hzcf.flagship.service.RiskCm1DetailService;
import com.hzcf.flagship.service.RiskEmailService;
import com.hzcf.flagship.service.RiskOldLoanService;
import com.hzcf.flagship.service.RiskOldOverdueService;
import com.hzcf.flagship.service.RiskParamsService;
import com.hzcf.flagship.service.RiskPlanService;
import com.hzcf.flagship.service.RiskService;
import com.hzcf.flagship.service.RiskSubcenterService;
import com.hzcf.flagship.util.DataUtil;
import com.hzcf.flagship.util.DateTimeUtil;
import com.hzcf.flagship.util.ImportRiskPlanUtil;
import com.hzcf.flagship.util.ImportUtil;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.PropertyUtil;
import com.hzcf.flagship.util.StringUtil;

import net.sf.json.JSONArray;

/**
*<dl>
*<dt>类名：RiskController.java</dt>
*<dd>描述: 风控中心管理</dd>
*<dd>创建时间： 2017年10月16日 下午3:44:55</dd>
*<dd>创建人：Tieguowei</dd>
*<dt>版本历史: </dt>
* <pre>
* Date Author Version Description
* ------------------------------------------------------------------
* 2017年10月16日 下午3:44:55 Tieguowei 1.0 1.0 Version
* </pre>
*</dl>
*/
@Controller
@RequestMapping("/risk")
public class RiskController extends BaseController{

	@Autowired
	private RiskEmailService riskEmailService;
    @Autowired
    private RiskOldOverdueService riskOldOverdueService;
    @Autowired
    private RiskOldLoanService riskOldLoanService;
	@Autowired
	private ManualAdjustmentService manualAdjustmentService;
	@Autowired
	private RiskParamsService riskParamsService;
	@Autowired
	private RiskPlanService riskPlanService;
	@Autowired
	private RiskSubcenterService riskSubcenterService;
	@Autowired
	private RiskCm1DetailService riskCm1DetailService;
	//营业部负责人，风控经理发送邮件 队列名
	private String rabbitmq_riskSendEmail_queueName = PropertyUtil.getInfo("rabbitmq.riskSendEmail.queueName");
	//交换机
    private String rabbitmq_risk_exchange = PropertyUtil.getInfo("rabbitmq.risk.exchange");
	@Autowired  
	private AmqpTemplate amqpTemplate; 
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private RiskService riskService;
	/**
	 * 
	 * Description: 邮件发送详情查询列表
	 * @Author 
	 */
	@ResponseBody
	@RequestMapping(value="/findEmailList")
	public DataMsg orgMapPageList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			String result = StringUtil.trim(request.getParameter("result"));//发送状态
			if (StringUtil.isNotBlank(result)) {
				paramsCondition.put("result", result);
			}
			String start_time = request.getParameter("start_time");
			if(StringUtil.isNotBlank(start_time)) {
				paramsCondition.put("start_time", start_time);
			}
			String end_time = request.getParameter("end_time");
			if(StringUtil.isNotBlank(end_time)) {
				paramsCondition.put("end_time", end_time);
			}
			PageModel pageModel = riskEmailService.findEmailList(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	/**
	 * 老系统逾期表上传 
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/uploadOverdue")
	@ResponseBody
	public Map<String,Object> orgMapDoUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer j = null;
		String ruleName  = request.getParameter("overdueName");
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
				int errorCount = riskOldOverdueService.insertOldOverdue(list, id);
				if (0 != errorCount) {
					j = errorCount;
					map.put("status", 1);// 1 - 插入失败！
					map.put("error", j + TemplateConstants.CONTENTERRORMESSAGE);
					return map;
				} else {
					map.put("count", list.size() - 1);
					map.put("status", 0);// 0 - 插入成功！
				}
			}
			return map;
		} catch (Exception e) {
			map.put("error", TemplateConstants.SYSTEMERRORMESSAGE);
			map.put("status", 1);// 1 - 插入失败！
			e.printStackTrace();
			return map;
		}
	}

	/**
	 * 
	 * Description: 老系统逾期表查询列表
	 * 
	 * @param
	 * @return String
	 * @throws
	 * @Author
	 */
	@ResponseBody
	@RequestMapping(value="/findOldOverdueList")
	public DataMsg findOldOverdueList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			String clientName = StringUtil.trim(request.getParameter("clientName"));// 客户
			if (StringUtil.isNotBlank(clientName)) {
				paramsCondition.put("clientName", clientName);
			}
			String oldOrgName = StringUtil.trim(request.getParameter("oldOrgName"));// 营业部
			if (StringUtil.isNotBlank(oldOrgName)) {
				paramsCondition.put("oldOrgName", oldOrgName);
			}
			String start_time = request.getParameter("start_time");
			if(StringUtil.isNotBlank(start_time)) {
				paramsCondition.put("start_time", start_time);
			}
			String end_time = request.getParameter("end_time");
			if(StringUtil.isNotBlank(end_time)) {
				paramsCondition.put("end_time", end_time);
			}
			PageModel pageModel = riskOldOverdueService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	/**
	 * 查找最新的贷后分割点
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getSeparate")
	public Map<String, Object> getSeparate(Map<String, Object> json) {
		RiskSeparate riskSeparate = riskParamsService.getSeparate();
		Integer value = 0;
		if(null != riskSeparate){
			value = riskSeparate.getValue();
		}
		json.put("val", value);
		
		return json;
	}
	
	/**
	 * 老系统放款数据上传
	 * @return
	 */
	@RequestMapping("/uploadOldLoan")
	@ResponseBody
	public Map<String,Object> uploadOldLoan(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer j = null;
		String ruleName  = request.getParameter("loanName");
		try {
			if (!file.isEmpty()) {
				//校验文件名是否一致
			/*	if(!checkFileName(file,ruleName)){
					map.put("error", TemplateConstants.FILENAMEERRORMESSAGE);
					map.put("status", 1);//上传失败
					return map;
				};*/
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
				int errorCount = riskOldLoanService.insertOldLoan(list, id);
				if (0 != errorCount) {
					j = errorCount;
					map.put("status", 1);// 1 - 插入失败！
					map.put("error", j + TemplateConstants.CONTENTERRORMESSAGE);
					return map;
				} else {
					map.put("count", list.size() - 1);
					map.put("status", 0);// 0 - 插入成功！
				}
			}
			return map;
		} catch (Exception e) {
			map.put("error", TemplateConstants.SYSTEMERRORMESSAGE);
			map.put("status", 1);// 1 - 插入失败！
			e.printStackTrace();
			return map;
		}

	}

	/**
	 * 
	 * Description: 老系统放款查询列表
	 */
	@ResponseBody
	@RequestMapping(value="/findOldLoanList")
	public DataMsg findOldLoanList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			String oldLoanClientName = StringUtil.trim(request.getParameter("oldLoanClientName"));// 客户
			if (StringUtil.isNotBlank(oldLoanClientName)) {
				paramsCondition.put("clientName", oldLoanClientName);
			}
			String oldLoanOrgName = StringUtil.trim(request.getParameter("oldLoanOrgName"));// 营业部
			if (StringUtil.isNotBlank(oldLoanOrgName)) {
				paramsCondition.put("oldOrgName", oldLoanOrgName);
			}
			String oldLoan_start_time = request.getParameter("oldLoan_start_time");
			if(StringUtil.isNotBlank(oldLoan_start_time)) {
				paramsCondition.put("start_time", oldLoan_start_time);
			}
			String oldLoan_end_time = request.getParameter("oldLoan_end_time");
			if(StringUtil.isNotBlank(oldLoan_end_time)) {
				paramsCondition.put("end_time", oldLoan_end_time);
			}
			PageModel pageModel = riskOldLoanService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	/**
	 * 手工调整表上传 
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/uploadHandAdjust")
	@ResponseBody
	public Map<String,Object> uploadHandAdjust(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer j = null;
		String ruleName  = request.getParameter("handAdjustName");
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
				int errorCount = manualAdjustmentService.insertManualAdjustment(list, id);
				if (0 != errorCount) {
					j = errorCount;
					map.put("status", 1);// 1 - 插入失败！
					map.put("error", j + TemplateConstants.CONTENTERRORMESSAGE);
					return map;
				} else {
					map.put("count", list.size() - 1);
					map.put("status", 0);// 0 - 插入成功！
				}
			}
			return map;
		} catch (Exception e) {
			map.put("error", TemplateConstants.SYSTEMERRORMESSAGE);
			map.put("status", 1);// 1 - 插入失败！
			e.printStackTrace();
			return map;
		}
	}
	/**
	 * 
	 * Description: 老系统放款查询列表
	 */
	@ResponseBody
	@RequestMapping(value="/findHandAdjustList")
	public DataMsg findHandAdjustList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			PageModel pageModel = manualAdjustmentService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	/** 
	 * 新增贷后分割点
	 * @param separate
	 * @param session
	 */
	@ResponseBody
	@RequestMapping(value="/insertSeparate")
	public void insertSeparate(String separate, HttpSession session) {
		Integer employeeId = getSystemCurrentUser(session).getEmployeeId();
		riskParamsService.insertSeparate(separate,employeeId);
	}
	
	/**
	 * 产品预警列表
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getPorductWarList")
	public Map<String, Object> getPorductWarList(Map<String, Object> json) {
		List<Map<String, Object>> list = riskParamsService.getPorductWarList();
		json.put("list", list);
		return json;
	}
	
	/**
	 * 新增产品预警
	 * @param product
	 * @param warningValue
	 * @param session
	 */
	@ResponseBody
	@RequestMapping(value="/insertWaringVal")
	public void insertWaringVal(String product,String warningValue, HttpSession session) {
		RiskProduct riskProduct =  riskParamsService.findProductNumByProduct(product);
		RiskProductWarning riskProductWarning = new RiskProductWarning();
		riskProductWarning.setProductNo(riskProduct.getProductNo());
		riskProductWarning.setWarningValue(new BigDecimal(warningValue));
		riskProductWarning.setCreator(getSystemCurrentUser(session).getEmployeeId());
		riskProductWarning.setCreateTime(new Date());
		riskParamsService.insertWaringVal(riskProductWarning);
	}
	/**
	 * 风控目标列表
	 * @param request
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/riskPlanList")
	public DataMsg riskPlanList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			PageModel pageModel = riskPlanService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	/**
	 * 查看每月风控目标的详情
	 * @param request
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getRiskPlanListByRecordDate")
	public DataMsg getRiskPlanListByRecordDate(HttpServletRequest request,DataMsg dataMsg) {
		PageModel pageModel = riskPlanService.getRiskPlanListByRecordDate(request.getParameter("monthDate"));
		dataMsg.setRows(pageModel.getList());
		return dataMsg;
	}
	
	/**
	 * 模板下载
	 * @param request
	 * @param response
	 */
	@RequestMapping("/riskPlanDownLoad")
	@ResponseBody
	public void riskPlanDownLoad(HttpServletRequest request, HttpServletResponse response) {
		try {
			riskPlanService.riskPlanDownLoad(request,response);
		} catch (Exception e) {
			logger.error(e);
			logger.error("导出风控目标模板出错");
		}
	}
	/**
	 * 风控目标数据导入
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/riskPlanUpLoad")
	@ResponseBody
	public Map<String, Object> riskPlanUpLoad(MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer j = null;
		try {
			if (!file.isEmpty()) {
				// 文件导入
				String fileName = (file).getOriginalFilename();
				String[] split = fileName.split("风");
				String nowDateNormalString = DateTimeUtil.getNowDateNormalString();
				if(split[0].equals(nowDateNormalString)){ //判断是否是当天的模板
					File file2 = DataUtil.dataUpload(file, request, response);
					List<List<Object>> list = ImportRiskPlanUtil.readExcel(file2);
					// 得到当前登录用户id
					Integer id = null;
					Employee employee = getSystemCurrentUser(request.getSession());
					if (null != employee) {
						id = employee.getEmployeeId();// 创建人id
					}
					// 批量插入
					int errorCount = riskPlanService.insertRiskPlan(list, id);
					if(0 != errorCount){
						j = errorCount;
						map.put("status", 1);// 1 - 插入失败！
						map.put("error", j + TemplateConstants.CONTENTERRORMESSAGE);
					   return map;
					}else{
						map.put("count", list.size() - 1);
						map.put("status", 0);// 0 - 插入成功！
					}
				}else{
					map.put("status", 2);
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
	 * 按月上传风控目标
	 * @param fileAgain
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/riskPlanUpLoadByRecordDate")
	@ResponseBody
	public Map<String, Object> riskPlanUpLoadByRecordDate(MultipartFile fileAgain, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer j = null;
		try {
			if (!fileAgain.isEmpty()) {
					// 文件导入
					String fileName = (fileAgain).getOriginalFilename();
					String[] split = fileName.split("风");
					if(split[0].equals(request.getParameter("date"))){ //判断是否是当月的模板
					File file2 = DataUtil.dataUpload(fileAgain, request, response);
					List<List<Object>> list = ImportRiskPlanUtil.readExcel(file2);
					// 得到当前登录用户id
					Integer id = null;
					Employee employee = getSystemCurrentUser(request.getSession());
					if (null != employee) {
						id = employee.getEmployeeId();// 创建人id
					}
					// 批量插入
					int errorCount = riskPlanService.insertRiskPlanByRecordDate(list, id, split[0]);
					if(0 != errorCount){
						j = errorCount;
						map.put("status", 1);// 1 - 插入失败！
						map.put("error", j + TemplateConstants.CONTENTERRORMESSAGE);
					   return map;
					}else{
						map.put("count", list.size() - 1);
						map.put("status", 0);// 0 - 插入成功！
					}
					}else{
						map.put("status", 2);// 2 - 不是当月模板
					}
				
			}
			return map;
		} catch (Exception e) {
			map.put("error", TemplateConstants.SYSTEMERRORMESSAGE);
			map.put("status", 1);// 1 - 插入失败！
			logger.error(e); 
			return map;
		}
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
	 * Description: 查找组织机构tree
	 *
	 * @param 
	 * @return List<Map<String,Object>>
	 * @throws 
	 */
	@ResponseBody
	@RequestMapping(value="/findOrgStructTree")
	public List<Map<String, Object>> findOrgStructTree(){
		List<Map<String, Object>> orgStructLists = riskEmailService.findOrgStructTree();
		return orgStructLists;
	}
	
	
	/**
	 * 
	 * Description: 查找组织机构营业部列表
	 */
	@ResponseBody
	@RequestMapping(value="/findChildListByOrgNo")
	public DataMsg findChildListByOrgNo(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			String org_no = request.getParameter("orgNo");
			String rank = request.getParameter("rank");
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			paramsCondition.put("org_no", org_no);
			paramsCondition.put("rank", rank);
			PageModel pageModel = riskEmailService.childListByOrgNo(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	/**
	 * 修改负责人
	 * @param request
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editOrgData")
	public boolean editOrgData(HttpServletRequest request, @ModelAttribute RiskOrgData orgData,HttpSession session,DataMsg dataMsg){
		try {
			riskEmailService.updateOrgDataByOrgNo(orgData);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * Description:查找负责人
	 */
	@ResponseBody
	@RequestMapping(value="/findPrincipalName")
	public DataMsg findPrincipalName(HttpServletRequest request,DataMsg dataMsg) {
		try {
			String name= URLDecoder.decode(request.getParameter("name"), "UTF-8");
			PageModel pageModel = riskEmailService.findPrincipalName(name);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description:查找邮箱地址
	 */
	@ResponseBody
	@RequestMapping(value="/findEmailAddressByList")
	public DataMsg findEmailAddressByList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			String json = java.net.URLDecoder.decode(request.getParameter("json"), "utf-8");			
			JSONArray jsonArray=JSONArray.fromObject(json);
			List<RiskOrgData> list=(List)JSONArray.toCollection(jsonArray,RiskOrgData.class);
			PageModel pageModel = riskEmailService.findEmailAddressByList(list);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}

	
	/**
	 * 
	 * Description:发送邮件
	 */
	@ResponseBody
	@RequestMapping(value="/sendEmail")
	public boolean sendEmail(HttpServletRequest request,DataMsg dataMsg,@ModelAttribute RiskEmail riskEmail) {
		try {
			//邮箱为空 或者已离职("0":离职)的员工 插入失败记录
			if(StringUtil.isEmpty(riskEmail.getReceiverAddress()) 
					|| "0".equals(riskEmail.getIsLeave())){
				riskEmail.setResult("0");//1成功 0失败
				riskEmailService.insertRecord(riskEmail);
				return true;
			}else{
				//进入队列  发送邮件
				String jsonString = JSON.toJSONString(riskEmail);
				amqpTemplate.convertAndSend(rabbitmq_risk_exchange, rabbitmq_riskSendEmail_queueName, jsonString);
				return true;
			}
		} catch (Exception e) {
			riskEmail.setResult("0");//1成功 0失败
			riskEmailService.insertRecord(riskEmail);
			e.printStackTrace();
			return false;
		}
	}

    /**
     * Description: 跳转到分中心列表页面
     *
     * @param messageCode {@linkplain property/message_code.properties}
     * @return String
     * @Author liuxianfa
     * Create Date: 2014-12-3 下午03:57:18
     */
    @RequestMapping(value = "/toSubcenterList")
    public String toSubcenterList(String refreshTag, String messageCode, Model model) {
        super.showMessageAlert(refreshTag, messageCode, model);
        return "app/risk/riskSubcenter";
    }

    /**
     * Description:分中心列表
     */
    @ResponseBody
    @RequestMapping(value = "/subcenterList")
    public DataMsg subcenterList(HttpServletRequest request, DataMsg dataMsg,
                                 @RequestParam(defaultValue = "1", required = false) int page,
                                 @RequestParam(defaultValue = Constants.PAGE_SIZE + "", required = false) int rows) {
        try {
            PageModel pageModel = riskSubcenterService.subcenterList(page, rows);

            dataMsg.setTotal(pageModel.getTotalRecords());
            dataMsg.setRows(pageModel.getList());

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return dataMsg;
    }

    /**
     * Description: 跳转到分中心录入页面
     *
     * @return String
     * @Author liuxianfa
     */
    @RequestMapping(value = "/toAddSubCenter")
    public String toAddRiskSubcenter() {
        return "app/risk/addSubCenter";
    }

    /**
     * Description: 跳转到分中心修改页面
     *
     * @param id 分中心主键id
     * @return String
     * @Author liuxianfa
     */
    @RequestMapping(value = "/toEditSubCenter/{id:\\d+}")
    public String toEditSubCenter(@PathVariable Long id, Model model,HttpServletRequest request) {
        try {
            RiskSubcenter riskSubcenter = riskSubcenterService.selectByPrimaryKey(id);

            String managementOrgData = riskSubcenterService.selectManagementOrgDataBySubcenterNo(riskSubcenter.getSubcenterNo());
            model.addAttribute("managementOrgData", managementOrgData);
            request.setAttribute("subcenter", riskSubcenter);
           /* model.addAttribute("subCenter", riskSubcenter);*/
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "common/exception";
        }
        return "app/risk/editSubCenter";
    }

    /**
     * Description: 添加分中心
     *
     * @param riskSubcenter {@link RiskSubcenter}
     * @return DataMsg
     * @Author liuxianfa
     */
    @ResponseBody
    @RequestMapping(value = "/doAddSubCenter")
    public DataMsg doAddRiskSubcenter(@ModelAttribute RiskSubcenter riskSubcenter, DataMsg dataMsg, HttpSession session) {
        try {
            riskSubcenterService.insertSelective(riskSubcenter, getSystemCurrentUser(session).getEmployeeId());
            dataMsg.setMessageCode("0001");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            dataMsg.setMessageCode("0002");
        }
        return dataMsg;
    }

    /**
     * Description: 修改分中心
     *
     * @param riskSubcenter {@link RiskSubcenter}
     * @return DataMsg
     * @Author liuxianfa
     */
    @ResponseBody
    @RequestMapping(value = "/doEditSubCenter")
    public DataMsg doEditSubCenter(@ModelAttribute RiskSubcenter riskSubcenter, DataMsg dataMsg, HttpSession session) {
        try {
            riskSubcenterService.updateBySubcenterNoSelective(riskSubcenter, getSystemCurrentUser(session).getEmployeeId());
            dataMsg.setMessageCode("0003");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            dataMsg.setMessageCode("0004");
        }
        return dataMsg;
    }

    /**
     * Description: 删除分中心
     *
     * @param id 分中心主键id
     * @return DataMsg
     * @Author liuxianfa
     */
    @ResponseBody
    @RequestMapping(value = "/deleteSubCenter/{subcenterNo:^\\w{32}$}")
    public DataMsg deleteSubCenter(@PathVariable String subcenterNo,
                                   @RequestParam(required = false, defaultValue = "") String name,
                                   DataMsg dataMsg,
                                   HttpSession session) {
        try {
            // 判断是否有关联营业部
            if (this.hasManagementOrgData(subcenterNo)){
                dataMsg.setMessageCode("0030");
                return dataMsg;
            }
            riskSubcenterService.delete(subcenterNo, name, getSystemCurrentUser(session).getEmployeeId());
            dataMsg.setMessageCode("0005");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            dataMsg.setMessageCode("0006");
        }
        return dataMsg;
    }

    /**
     * 判断是否有关联营业部
     * @param subcenterNo 分中心编号
     * @return true:此分中心有关联营业部。
     */
    private boolean hasManagementOrgData(String subcenterNo) {
        String orgDataNames = riskSubcenterService.selectManagementOrgDataBySubcenterNo(subcenterNo);
        return StringUtil.isNotBlank(orgDataNames);
    }
    
	
	/**
	 * 查询所有分中心数据
	 * @Name:queryAll
	 * @Author:YuanGuoLei
	 * @CreateTime:2017年11月27日下午4:03:57
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryAllRiskSubcenter")
	public List<RiskSubcenter> queryAll(HttpServletRequest request){
		List<RiskSubcenter> riskSubcenters = riskSubcenterService.selectForDistinct();
		return riskSubcenters;
	}
	
	/**
	 * 跳转分中心 模块管理 界面
	 * @param refreshTag
	 * @param messageCode
	 * @param model
	 * @return
	 */
	@RequestMapping("toRiskModuleManage")
	public String toShowPage(Model model,HttpServletRequest request) {
		
		//查询执委模块
		String zwPage = "risk_zw";
		List<AppPageModel> zwList = riskSubcenterService.findModelByPage(zwPage);
		//查询市场风险管理部模块
		String scPage = "risk_sc";
		List<AppPageModel> scList =riskSubcenterService.findModelByPage(scPage);
		model.addAttribute("zwList", zwList);
		model.addAttribute("scList", scList);
		String flag = request.getParameter("flag");
		if(null != flag){
			model.addAttribute("flag", flag);
		}
		return "app/risk/riskModuleManage_list";
	}
	
	/**
	 * 修改 执委 或市场风险管理部 模块
	 */
	 @RequestMapping(value = "/updatePage") 
	 @ResponseBody
	public boolean updatePage(HttpServletRequest request) {
		 try {
			// 得到当前登录用户id
			Integer id = null;
			Employee employee = getSystemCurrentUser(request.getSession());
			if (null != employee) {
				id = employee.getEmployeeId();// 创建人id
			}
			 String data = request.getParameter("data");
			 riskSubcenterService.updatePage(data,id);
			 return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	 
	 /**
		 * 
		 * Description: 查询队列异常列表
		 * @Author 
		 */
		@ResponseBody
		@RequestMapping(value="/mqExceptionList")
		public DataMsg mqExceptionList(HttpServletRequest request,DataMsg dataMsg) {
			try {
				Map<String, Object> paramsCondition = new HashMap<String, Object>();
				paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
				paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
				String queue = StringUtil.trim(request.getParameter("queue"));//发送状态
				if (StringUtil.isNotBlank(queue)) {
					paramsCondition.put("queue", queue);
				}
				String start_time = request.getParameter("start_time");
				if(StringUtil.isNotBlank(start_time)) {
					paramsCondition.put("start_time", start_time);
				}
				String end_time = request.getParameter("end_time");
				if(StringUtil.isNotBlank(end_time)) {
					paramsCondition.put("end_time", end_time);
				}
				PageModel pageModel = riskEmailService.findMQExceptionList(paramsCondition);
				dataMsg.setTotal(pageModel.getTotalRecords());
				dataMsg.setRows(pageModel.getList());
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
			return dataMsg;
		}
		
		
		/**
		 * 风控中心-基础数据-日数据页面
		 */
		@RequestMapping("/toDailyBasicDataList")
		public String toDailyBasicDataList(HttpServletRequest request) {
			//得到当前用户的id
			Employee employee = getSystemCurrentUser(request.getSession());
			//查询当前用户拥有的权限
			Map<String,Object> result = riskCm1DetailService.findPermissionByEmpId(employee.getEmployeeId());
			request.setAttribute("result", result);
			return "app/risk/basicData/dailyBasicData_list";
		}
		/**
		 * 获取t-1日 风控基础数据CM1(事业部 大区 营业部 批次)下拉框内容
		 * @param request
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value="/getCm1ComboBoxName")
		public JSONArray getCm1ComboBoxName(HttpServletRequest request) {
			String status = request.getParameter("status");
			Map<String,Object> map = new HashMap<String,Object>();
			String business_unit_no = request.getParameter("business_unit_no");
			String district_no = request.getParameter("district_no");
			String recordDate = request.getParameter("recordDate");
			if(null != business_unit_no){
				map.put("business_unit_no", business_unit_no);
			}
			if(null != district_no){
				map.put("district_no", district_no);
			}
			if(null != recordDate && "" != recordDate){
				map.put("recordDate", recordDate);
			}else{
				String yesterday = DateTimeUtil.getYesterdayDateString();
				map.put("recordDate", yesterday);
			}
			map.put("status", status);
			List<?> list=riskCm1DetailService.getCm1ComboBoxName(map);
			return JSONArray.fromObject(list);
		}
		
		
		/**
		 * 跳转到风控日总计数据页面
		 */
		
		@RequestMapping(value="/toCm1GradingDailyPage")
		public String tokCm1GradingDailyPage(Model model,HttpServletRequest request){
			Employee currentUser = getSystemCurrentUser(request.getSession());
			//确定用户权限
			Map<String, Object> permission = employeeService.getPermissionByEmployee(currentUser.getEmployeeId());
			if (permission!=null ) {
				//permission.get("")
				//根据权限确定菜单可选项中的内容
				String permissionNo = (String)permission.get("no");
				Integer rank = (Integer)permission.get("rank");
				ArrayList<Object> rankList = new ArrayList<>();
				Map<String, Object> rank3 = new HashMap<>();
				rank3.put("rank", 3);
				rank3.put("rankDesc", "事业部");
				Map<String, Object> rank4 = new HashMap<>();
				rank4.put("rank", 4);
				rank4.put("rankDesc", "大区 ");
				Map<String, Object> rank5 = new HashMap<>();
				rank5.put("rank", 5);
				rank5.put("rankDesc", "营业部 ");
				
				ArrayList<Object> typeList = new ArrayList<>();
				Map<String, Object> type1 = new HashMap<>();
				type1.put("type", 1);
				type1.put("typeDesc", "整体 ");
				Map<String, Object> type2 = new HashMap<>();
				type2.put("type", 2);
				type2.put("typeDesc", "6期前 ");
				Map<String, Object> type3 = new HashMap<>();
				type3.put("type", 3);
				type3.put("typeDesc", "6期后 ");
				
				//1.如果是执委权限,组织级别中选项为(3.事业部 4.大区 5.营业部),统计周期为(1.整体 2.n期前 3.n期后)
				if (permissionNo != null && "fengkongzw".equals(permissionNo)) {
					
					rankList.add(rank3);
					rankList.add(rank4);
					rankList.add(rank5);
					
					typeList.add(type1);
					typeList.add(type2);
					typeList.add(type3);
					
				}else if (permissionNo != null && "fengkongglb".equals(permissionNo)) {
					//2.如果是风控管理部权限,组织级别中选项为(3.事业部 4.大区 5.营业部),统计周期为(2.n期前 )
					rankList.add(rank3);
					rankList.add(rank4);
					rankList.add(rank5);
					typeList.add(type2);
				}else if (permissionNo != null && "fengkongdh".equals(permissionNo)) {
					//如果是贷后权限,则组织级别中选项为(3.事业部 4.大区 5.营业部),统计周期为(3.n期后)
					rankList.add(rank3);
					rankList.add(rank4);
					rankList.add(rank5);
					typeList.add(type3);
				}else if (rank!=null && rank==6) {
					//如果是分中心权限,则组织级别中选项为(5.营业部),统计周期为(2.n期前)
//					rankList.add(rank3);
//					rankList.add(rank4);
					rankList.add(rank5);
					typeList.add(type2);
				}else if (rank!=null && rank==3) {
					//如果是事业部权限,则组织级别中选项为(4.大区 5.营业部),统计周期为(2.n期前)
					rankList.add(rank4);
					rankList.add(rank5);
					typeList.add(type2);
				}else if (rank!=null && rank==4) {
					//如果是大区权限,则组织级别中选项为(5.营业部),统计周期为(2.n期前)
					rankList.add(rank5);
					typeList.add(type2);
				}
//				request.setAttribute("rankList",JSON.toJSONString(rankList));
				request.setAttribute("rankList", JSON.toJSONString(rankList));
				request.setAttribute("typeList",JSON.toJSONString(typeList));
//				model.addAttribute("rankList", rankList);
//				request.setAttribute("typeList",typeList);
			}
			return "app/risk/riskCm1GradingDaily";
		}
		
		/**
		 * 获得cm1日合计数据列表
		 * @param request
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value="/getCm1GradingDailyList")
		public DataMsg getRiskCm1GradingDailyList(HttpServletRequest request){
			Employee currentUser = getSystemCurrentUser(request.getSession());
			//确定用户权限
			
			Map<String, Object> permission = employeeService.getPermissionByEmployee(currentUser.getEmployeeId());
			Map<String, Object> params = new HashMap<>();
			Integer permissinRank = null;
			String permissinNo = null;
			if (permission != null) {
				permissinRank = (Integer)permission.get("rank");
				permissinNo = (String)permission.get("no");
				params.put("permissinRank", permissinRank);
				params.put("permissinNo", permissinNo);
				params.put("recordDate", request.getParameter("recordDate"));
				params.put("rank", request.getParameter("rank"));
				params.put("type", request.getParameter("type"));
				params.put("orgName", request.getParameter("orgName"));
				params.put("pageNo", request.getParameter("page"));
				params.put("pageSize", request.getParameter("rows"));
			}
			PageModel pageModel = riskService.cm1DailyList(params);
			DataMsg dataMsg = new DataMsg();
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
			return dataMsg;
			
		}
		
		
		/**
		 * 跳转到风控月总计数据页面
		 */
		
		@RequestMapping(value="/toCm1GradingMonthPage")
		public String tokCm1GradingMonthPage(Model model,HttpServletRequest request){
			Employee currentUser = getSystemCurrentUser(request.getSession());
			//确定用户权限
			Map<String, Object> permission = employeeService.getPermissionByEmployee(currentUser.getEmployeeId());
			if (permission!=null ) {
				//permission.get("")
				//根据权限确定菜单可选项中的内容
				String permissionNo = (String)permission.get("no");
				Integer rank = (Integer)permission.get("rank");
				ArrayList<Object> rankList = new ArrayList<>();
				Map<String, Object> rank3 = new HashMap<>();
				rank3.put("rank", 3);
				rank3.put("rankDesc", "事业部");
				Map<String, Object> rank4 = new HashMap<>();
				rank4.put("rank", 4);
				rank4.put("rankDesc", "大区 ");
				Map<String, Object> rank5 = new HashMap<>();
				rank5.put("rank", 5);
				rank5.put("rankDesc", "营业部 ");
				
				ArrayList<Object> typeList = new ArrayList<>();
				Map<String, Object> type1 = new HashMap<>();
				type1.put("type", 1);
				type1.put("typeDesc", "整体 ");
				Map<String, Object> type2 = new HashMap<>();
				type2.put("type", 2);
				type2.put("typeDesc", "6期前 ");
				Map<String, Object> type3 = new HashMap<>();
				type3.put("type", 3);
				type3.put("typeDesc", "6期后 ");
				
				//1.如果是执委权限,组织级别中选项为(3.事业部 4.大区 5.营业部),统计周期为(1.整体 2.n期前 3.n期后)
				if (permissionNo != null && "fengkongzw".equals(permissionNo)) {
					
					rankList.add(rank3);
					rankList.add(rank4);
					rankList.add(rank5);
					
					typeList.add(type1);
					typeList.add(type2);
					typeList.add(type3);
					
				}else if (permissionNo != null && "fengkongglb".equals(permissionNo)) {
					//2.如果是风控管理部权限,组织级别中选项为(3.事业部 4.大区 5.营业部),统计周期为(2.n期前 )
					rankList.add(rank3);
					rankList.add(rank4);
					rankList.add(rank5);
					typeList.add(type2);
				}else if (permissionNo != null && "fengkongdh".equals(permissionNo)) {
					//如果是贷后权限,则组织级别中选项为(3.事业部 4.大区 5.营业部),统计周期为(3.n期后)
					rankList.add(rank3);
					rankList.add(rank4);
					rankList.add(rank5);
					typeList.add(type3);
				}else if (rank!=null && rank==6) {
					//如果是分中心权限,则组织级别中选项为(5.营业部),统计周期为(2.n期前)
//					rankList.add(rank3);
//					rankList.add(rank4);
					rankList.add(rank5);
					typeList.add(type2);
				}else if (rank!=null && rank==3) {
					//如果是事业部权限,则组织级别中选项为(4.大区 5.营业部),统计周期为(2.n期前)
					rankList.add(rank4);
					rankList.add(rank5);
					typeList.add(type2);
				}else if (rank!=null && rank==4) {
					//如果是大区权限,则组织级别中选项为(5.营业部),统计周期为(2.n期前)
					rankList.add(rank5);
					typeList.add(type2);
				}
//				request.setAttribute("rankList",JSON.toJSONString(rankList));
				request.setAttribute("rankList", JSON.toJSONString(rankList));
				request.setAttribute("typeList",JSON.toJSONString(typeList));
				//月份下拉框选项
				List<Map<String, Object>> monthList = riskService.getMonthList();
				request.setAttribute("monthList",JSON.toJSONString(monthList));
//				model.addAttribute("rankList", rankList);
//				request.setAttribute("typeList",typeList);
			}
			return "app/risk/riskCm1GradingMonth";
		}
		
		
		/**
		 * 获得cm1月合计数据列表
		 * @param request
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value="/getCm1GradingMonthList")
		public DataMsg getRiskCm1GradingMonthList(HttpServletRequest request){
			Employee currentUser = getSystemCurrentUser(request.getSession());
			//确定用户权限
			Map<String, Object> permission = employeeService.getPermissionByEmployee(currentUser.getEmployeeId());
			Map<String, Object> params = new HashMap<>();
			Integer permissinRank = null;
			String permissinNo = null;
			if (permission != null) {
				permissinRank = (Integer)permission.get("rank");
				permissinNo = (String)permission.get("no");
				params.put("permissinRank", permissinRank);
				params.put("permissinNo", permissinNo);
				params.put("recordDate", request.getParameter("recordDate"));
				params.put("rank", request.getParameter("rank"));
				params.put("type", request.getParameter("type"));
				params.put("orgName", request.getParameter("orgName"));
				params.put("pageNo", request.getParameter("page"));
				params.put("pageSize", request.getParameter("rows"));
			}
			PageModel pageModel = riskService.cm1MonthList(params);
			DataMsg dataMsg = new DataMsg();
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
			return dataMsg;
		}
		 /**
		 * 
		 * Description: 查询风控基础数据 日(月)数据列表
		 * @Author 
		 */
		@ResponseBody
		@RequestMapping(value="/getCm1OverDetailList")
		public DataMsg getCm1OverDetailList(HttpServletRequest request,DataMsg dataMsg) {
			try {
				Map<String, Object> paramsCondition = new HashMap<String, Object>();
				paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
				paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
				getSelectRiskBasicDataParams(request,paramsCondition);
				PageModel pageModel = riskCm1DetailService.getCm1OverDetailList(paramsCondition);
				dataMsg.setTotal(pageModel.getTotalRecords());
				dataMsg.setRows(pageModel.getList()); 
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dataMsg;	
		}
		
		/**
		 * 风控中心-基础数据-日数据页面-查找最新的贷后分割点
		 */
		@ResponseBody
		@RequestMapping("/findSeparate")
		public Map<String,Object> findSeparate(HttpServletRequest request) {
			String recordDate = request.getParameter("recordDate");
			return  riskCm1DetailService.findSeparate(recordDate);
		}
		
		/**
		 * 风控中心-基础数据-月数据页面
		 */
		@RequestMapping("/toMonthlyBasicDataList")
		public String toMonthlyBasicDataList(HttpServletRequest request) {
			//得到当前用户的id
			Employee employee = getSystemCurrentUser(request.getSession());
			//查询当前用户拥有的权限
			Map<String,Object> result = riskCm1DetailService.findPermissionByEmpId(employee.getEmployeeId());
			request.setAttribute("result", result);
			return "app/risk/basicData/monthBasicData_list";
		}
		
		/**
		 * 风控中心-基础数据-月数据页面-初始化月份
		 */
		@ResponseBody
		@RequestMapping("/getCm1MonthTime")
		public JSONArray getCm1MonthTime(HttpServletRequest request) {
			List<?> list=riskCm1DetailService.getCm1MonthTime();
			return JSONArray.fromObject(list);
		}
		
		/**
		 * Description: 组装 风控中心-基础数据 动态查询条件
		 * @param paramsCondition 
		 */
		public void getSelectRiskBasicDataParams(HttpServletRequest request, Map<String, Object> paramsCondition) {
			//标识月还是日列表
			String status = StringUtil.trim(request.getParameter("status"));
			if(StringUtil.isNotBlank(status)) {
				paramsCondition.put("status", status);
			}
			//日期
			String cm1_time = StringUtil.trim(request.getParameter("cm1_time"));
			if (StringUtil.isNotBlank(cm1_time) && !"all".equals(cm1_time)) {
				paramsCondition.put("cm1_time", cm1_time);
			}else{
				//月数据 列表初始化 日期为空
				List<?> list=riskCm1DetailService.getCm1MonthTime();
				Map<String,Object> map = (Map<String, Object>) list.get(0);
				paramsCondition.put("cm1_time", map.get("dateValue"));
			}
			
			//批次
			String cm1_batch = request.getParameter("cm1_batch");
			if(StringUtil.isNotBlank(cm1_batch)) {
				paramsCondition.put("cm1_batch", cm1_batch);
			}
			//合同编号
			String cm1_contract_no = StringUtil.trim(request.getParameter("cm1_contract_no"));
			if (StringUtil.isNotBlank(cm1_contract_no)) {
				paramsCondition.put("cm1_contract_no", cm1_contract_no);
			}
			//x期 前（后）
			String cm1_type =StringUtil.trim(request.getParameter("cm1_type"));
			if(StringUtil.isNotBlank(cm1_type)){
				paramsCondition.put("cm1_type", cm1_type);
			}
			
			
			//事业部
			String cm1_business_unit_name = request.getParameter("cm1_business_unit_name");
			if(StringUtil.isNotBlank(cm1_business_unit_name)) {
				paramsCondition.put("cm1_business_unit_name", cm1_business_unit_name);
			}
			//大区
			String cm1_district_name = request.getParameter("cm1_district_name");
			if(StringUtil.isNotBlank(cm1_district_name)) {
				paramsCondition.put("cm1_district_name", cm1_district_name);
			}
			//营业部
			String cm1_org_name = request.getParameter("cm1_org_name");
			if(StringUtil.isNotBlank(cm1_org_name)) {
				paramsCondition.put("cm1_org_name", cm1_org_name);
			}
			
			//是否逾期
			String cm1_is_overdue = request.getParameter("cm1_is_overdue");
			if(StringUtil.isNotBlank(cm1_is_overdue)) {
				paramsCondition.put("cm1_is_overdue", cm1_is_overdue);
			}
		}
		
		/**
		 * 导出风控基础数据
		 * @param request
		 * @param response
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/riskBasicDataExport")
		public void exportExcelMonthCM1list(HttpServletRequest request, HttpServletResponse response) {
			try {
				Map<String, Object> paramsCondition = new HashMap<String, Object>();
				getSelectRiskBasicDataParams(request, paramsCondition);
				//标识是导出日数据 还是月数据
				paramsCondition.put("isExport",request.getParameter("isExport"));
				//标识 显示月份 还是 日期
				paramsCondition.put("status", request.getParameter("status"));
				riskCm1DetailService.exportRiskBasicDataExcel(paramsCondition,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
