package com.hzcf.flagship.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.flagship.baseweb.BaseController;
import com.hzcf.flagship.baseweb.DataMsg;
import com.hzcf.flagship.service.CheckDataService;
import com.hzcf.flagship.service.CheckJobService;
import com.hzcf.flagship.util.DateTimeUtil;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.StringUtil;

import net.sf.json.JSONArray;

/**
 * 
 *<dl>
 *<dt>类名：CheckDataController.java</dt>
 *<dd>描述: ~数据校验列表controller</dd> 
 *<dd>创建时间： 2017年9月14日 下午4:14:31</dd>
 *<dd>创建人： GuoDong</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年9月14日 下午4:14:31    GuoDong       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Controller
@RequestMapping(value="/checkData")
public class CheckDataController extends BaseController{ 
	@Autowired
	private CheckDataService checkDataService;
	@Autowired
	private CheckJobService checkJobService;
	
	/**
	 * 分页查询页面
	 * @param refreshTag
	 * @param messageCode
	 * @param model
	 * @return
	 */
	@RequestMapping("toCheckDataInfoList")
	public String toEmpList(String refreshTag,String messageCode,Model model) {
		showMessageAlert(refreshTag,messageCode,model);
		return "app/data/check/checkInfo_list";
	}
	
	/**
	 * 分页查询数据
	 * @param request
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkInfoList")
	public DataMsg pageList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			getSelectParams(request, paramsCondition);
			paramsCondition.put("limit", "limit");
			PageModel pageModel = checkDataService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	
	
	/**
	 * 获取类型
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getTypes")
	public JSONArray getTypes(HttpServletRequest request) {
		List<?> list=checkDataService.getTypes();
		return JSONArray.fromObject(list);
	}
	
	/**
	 * 获取结果
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getResult")
	public JSONArray getResult(HttpServletRequest request) {
		List<?> list=checkDataService.getResult();
		return JSONArray.fromObject(list);
	}
	
	/**
	 * 获取次数
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getSort")
	public JSONArray getSort(HttpServletRequest request) {
		Map<String, Object> paramsCondition = new HashMap<String, Object>();
		getReqDate(request, paramsCondition);
		List<?> list=checkDataService.getSort(paramsCondition);
		return JSONArray.fromObject(list);
	}
	
	/**
	 * 导出Excel 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramsCondition = new HashMap<String, Object>();
		getSelectParams(request, paramsCondition);
		checkDataService.exportExcel(paramsCondition,response);
	}
	
	/**
	 * 检查数据
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("checkDate")
	public Map<String, Object> checkData(HttpServletRequest request, HttpServletResponse response) {
		checkJobService.checkData();
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("messageCode", "0029");
		return jsonMap;
	}
	
	/**
	 * 组装查询条件
	 * @param request
	 * @return
	 */
	private void getSelectParams(HttpServletRequest request, Map<String, Object> paramsCondition) {
		String type = StringUtil.trim(request.getParameter("type"));// 接口类型
		if (StringUtil.isNotBlank(type)) {
			paramsCondition.put("type", type);
		}
		getReqDate(request, paramsCondition);
		String result = StringUtil.trim(request.getParameter("result"));// 结果
		if (StringUtil.isNotBlank(result)) {
			paramsCondition.put("result", result);
		}
		String times = StringUtil.trim(request.getParameter("times"));// 次数
		if (StringUtil.isNotBlank(times)) {
			paramsCondition.put("times", request.getParameter("times"));
		}
		String sort = StringUtil.trim(request.getParameter("sort"));// 次数排序
		if (StringUtil.isNotBlank(sort)) {
			paramsCondition.put("sort", sort + " " + request.getParameter("order"));
		}
	}
	
	/**
	 * 获取开始日期和结束日期并返回正确顺序
	 * @param request
	 * @param paramsCondition
	 */
	private void getReqDate(HttpServletRequest request, Map<String, Object> paramsCondition) {
		String beginDate = StringUtil.trim(request.getParameter("beginDate"));// 开始日期
		String endDate = StringUtil.trim(request.getParameter("endDate"));// 结束日期
		if(StringUtil.isNotBlank(beginDate)){
			paramsCondition.put("date", beginDate);
			paramsCondition.put("dateFlag", "1");
		}
		if(StringUtil.isNotBlank(endDate)){
			paramsCondition.put("date", endDate);
			paramsCondition.put("dateFlag", "1");
		}
		if(StringUtil.isNotBlank(beginDate) && StringUtil.isNotBlank(endDate)){
			Map<String, Object> date = DateTimeUtil.compare_date_map(beginDate, endDate);
			paramsCondition.put("beginDate", date.get("beginDate"));
			paramsCondition.put("endDate", date.get("endDate"));
			paramsCondition.put("dateFlag", "2");
		}
	}
	
}
