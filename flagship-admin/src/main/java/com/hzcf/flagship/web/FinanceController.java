package com.hzcf.flagship.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hzcf.flagship.baseweb.BaseController;
import com.hzcf.flagship.baseweb.DataMsg;
import com.hzcf.flagship.enmu.TemplateConstants;
import com.hzcf.flagship.model.Employee;
import com.hzcf.flagship.model.Properties;
import com.hzcf.flagship.service.FinanceOrgMapService;
import com.hzcf.flagship.service.FinancePeopleInfoService;
import com.hzcf.flagship.service.FinancePerformanceService;
import com.hzcf.flagship.service.FinanceRiskService;
import com.hzcf.flagship.service.OrgMapLogService;
import com.hzcf.flagship.service.PropertiesService;
import com.hzcf.flagship.util.DataUtil;
import com.hzcf.flagship.util.ImportUtil;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.StringUtil;



/**
 *<dl>
 *<dt>类名：FinanceController.java</dt>
 *<dd>描述: 融资元数据</dd> 
 *<dd>创建时间： 2017年5月23日 下午4:48:18</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月23日 下午4:48:18    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Controller
@RequestMapping("/finance")
public class FinanceController extends BaseController{

	@Autowired
	private FinanceOrgMapService financeOrgMapService;
	@Autowired
	private FinancePeopleInfoService financePeopleInfoService;
	@Autowired
	private FinanceRiskService financeRiskService;
	@Autowired
	private FinancePerformanceService financePerformanceService;
	@Autowired
	private OrgMapLogService orgMapLogService;
	@Autowired
	private PropertiesService propertiesService;
	/**
	 * 融资营业部对应表上传 数据导入
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/orgMap/doUpload")
	@ResponseBody
	public Map<String,Object> orgMapDoUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		long begin = System.currentTimeMillis();
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
				int errorCount = financeOrgMapService.insertFinanceOrgMap(list, id);
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
			logger.error(e.getMessage());
			return map;
		}

	}

	
	/**
	 * 
	 * Description: 融资营业部对应表查询列表
	 * 
	 * @param
	 * @return String
	 * @throws
	 * @Author
	 */
	@ResponseBody
	@RequestMapping(value="/orgMap/pageList")
	public DataMsg orgMapPageList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			PageModel pageModel = financeOrgMapService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	/**
	 * 融资人员信息表上传 数据导入
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/peopleInfo/doUpload")
	@ResponseBody
	public Map<String,Object> peopleInfoDoUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
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
				int errorCount = financePeopleInfoService.insertFinancePeopleInfo(list, id);
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
			e.printStackTrace();
			map.put("error", TemplateConstants.SYSTEMERRORMESSAGE);
			map.put("status", 1);// 1 - 插入失败！
			logger.error(e.getMessage()); 
			return map;
		}

	}

	/**
	 * 
	 * Description: 融资人员信息表查询列表
	 * 
	 * @param
	 * @return String
	 * @throws
	 * @Author
	 */
	@ResponseBody
	@RequestMapping(value="/peopleInfo/pageList")
	public DataMsg peopleInfoPageList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			String orgName = StringUtil.trim(request.getParameter("orgName"));// 营业部
			if (StringUtil.isNotBlank(orgName)) {
				paramsCondition.put("orgName", orgName);
			}
			String districtName = StringUtil.trim(request.getParameter("districtName"));//管办
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
			PageModel pageModel = financePeopleInfoService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	
	/**
	 * 融资风险表上传 数据导入
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/risk/doUpload")
	@ResponseBody
	public Map<String,Object> riskDoUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
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
				int errorCount = financeRiskService.insertFinanceRisk(list, id);
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
	 * Description: 融资风险表查询列表
	 * 
	 * @param
	 * @return String
	 * @throws
	 * @Author
	 */
	@ResponseBody
	@RequestMapping(value="/risk/pageList")
	public DataMsg riskPageList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			String orgName = StringUtil.trim(request.getParameter("orgName"));// 营业部
			if (StringUtil.isNotBlank(orgName)) {
				paramsCondition.put("orgName", orgName);
			}
			String districtName = StringUtil.trim(request.getParameter("districtName"));//大区
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
			PageModel pageModel = financeRiskService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
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
	 * 初始化融资部分IndexPage数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/initializePage")
	public String initializeIndexPage(){
		try {
			financePerformanceService.initializeIndexPageData();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("初始化融资部分IndexPage数据失败");
		}
		return "初始化数据结束";
	}
	
	/**
	 * 
	 * Description: 跳转到融资机构日志信息列表页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author tieguowei
	 * Create Date:  2017-07-03
	 */
	@RequestMapping("toOrgLogInfo")
	public String toOrgLogInfo(String refreshTag,String messageCode,Model model) {
		showMessageAlert(refreshTag,messageCode,model);
		return "app/data/financeOrgLogInfo_list";
	}
	
	/**
	 * 
	 * Description: 融资机构日志信息列表
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
			paramsCondition.put("type", "rongzi");
			PageModel pageModel = orgMapLogService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dataMsg;
	}
	
	/**
	 * 
	 * Description: 跳转到系统属性配置列表页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author tieguowei
	 * Create Date: 2017-07-04
	 */
	@RequestMapping("toPropertyConfigList")
	public String toPropertyConfigList(String refreshTag,String messageCode,Model model) {
		showMessageAlert(refreshTag,messageCode,model);
		return "app/data/propertyConfig_list";
	}
	
	/**
	 * 
	 * Description: 系统属性配置列表
	 * 
	 * @param
	 * @return String
	 * @throws
	 * @Author
	 */
	@ResponseBody
	@RequestMapping(value="/propertyConfig/pageList")
	public DataMsg propertyConfigList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			PageModel pageModel = propertiesService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	/**
	 * 
	 * Description: 跳转到修改系统属性配置页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author tieguowei
	 */
	@RequestMapping(value="/toEditProperty/{Id}")
	public String toEditEmp(@PathVariable Integer Id,Model model) {
		try {
			Properties properties = propertiesService.selectByPrimaryKey(Id);
			model.addAttribute("properties", properties);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return "common/exception";
		}
		return "app/data/property_edit";
	}
	
	/**
	 * 
	 * Description: 修改系统属性配置
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author tieguowei
	 */
	@RequestMapping(value="/doEditProperty")
	@ResponseBody
	public boolean doEditProperty(Properties properties,HttpServletRequest request) {
		try {
			Employee employee = getSystemCurrentUser(request.getSession());
			Integer id = null;
			if (null != employee) {
				id = employee.getEmployeeId();// 创建人id
			}
			propertiesService.doUpdateProperty(properties,id);
		    return true;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}
	
}
