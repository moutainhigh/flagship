package com.hzcf.flagship.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.flagship.baseweb.BaseController;
import com.hzcf.flagship.form.PerformanceParm;
import com.hzcf.flagship.service.AppHelpService;
import com.hzcf.flagship.util.StringUtil;
import com.hzcf.flagship.vo.ReturnMsgData;

/**
 * 
 *<dl>
 *<dt>类名：AppHelpController.java</dt>
 *<dd>描述: ~App帮助与反馈</dd> 
 *<dd>创建时间： 2017年8月1日 上午11:24:52</dd>
 *<dd>创建人： GuoDong</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年8月1日 上午11:24:52    GuoDong       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Controller
@RequestMapping("/help")
public class AppHelpController extends BaseController{
	
	@Autowired
	AppHelpService appHelpService;
	/**
	 * 用户帮助列表
	 * @param performanceParm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/useHelp" ,method= RequestMethod.POST)
	public ReturnMsgData performanceForDays(@RequestBody PerformanceParm performanceParm) {
		try {
			//校验是否登录
			if(!checkIsLogin(performanceParm)){
				return new ReturnMsgData("2001", "用户未登录");
			}
			if(StringUtil.isBlank(performanceParm.getSortName())){
				return new ReturnMsgData("3001", "请选择帮助分类名称");
			}
			return appHelpService.findAnswerListBySortName(performanceParm.getSortName());
		} catch (Exception e) {
			e.printStackTrace();
			return new ReturnMsgData("9999", "调用失败");
		}
		
	}
	/**
	 * 跳转至帮助页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/helpInfo")
	public String helpInfo(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("id", request.getParameter("id"));
		return "help/help";
	}
	
	/**
	 * 根据id查找问答
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getHelpInfo")
	@ResponseBody
	public Object getHelpInfo(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if(StringUtil.isNotBlank(id)){
			jsonMap = appHelpService.helpInfo(id);
			jsonMap.put("success", true);
		}else{
			jsonMap.put("success", false);
		}
		return jsonMap;
	}
}
