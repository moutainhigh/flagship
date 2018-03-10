package com.hzcf.flagship.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.flagship.baseweb.BaseController;
import com.hzcf.flagship.baseweb.DataMsg;
import com.hzcf.flagship.model.AppHelpContent;
import com.hzcf.flagship.service.HelpService;
import com.hzcf.flagship.util.Base64Util;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.StringUtil;

import net.sf.json.JSONArray;


/**
 *<dl>
 *<dt>类名：HelpController.java</dt>
 *<dd>描述: app用户帮助管理</dd> 
 *<dd>创建时间： 2017年8月1日 上午10:38:08</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年8月1日 上午10:38:08    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Controller
@RequestMapping(value="/help")
public class HelpController extends BaseController{ 
	
	@Autowired
	private HelpService helpService;
	
	/**
	 * 分页查询数据
	 * @param request
	 * @param dataMsg
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/helpList")
	public DataMsg pageList(HttpServletRequest request,DataMsg dataMsg) {
		try {
			Map<String, Object> paramsCondition = new HashMap<String, Object>();
			paramsCondition.put("pageNo", Integer.valueOf(request.getParameter("page")));
			paramsCondition.put("pageSize", Integer.valueOf(request.getParameter("rows")));
			String question = StringUtil.trim(request.getParameter("question"));// 问题名称
			if (StringUtil.isNotBlank(question)) {
				paramsCondition.put("question", question);
			}
			String sortId = request.getParameter("sortId");
			if (StringUtil.isNotBlank(sortId)) {
				//12 查询全部
				if(sortId.equals("0")){
					paramsCondition.put("sortId", null);
				}else{
					paramsCondition.put("sortId", sortId);
				}
			}
			PageModel pageModel = helpService.findAllByPage(paramsCondition);
			dataMsg.setTotal(pageModel.getTotalRecords());
			dataMsg.setRows(pageModel.getList());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return dataMsg;
	}
	/**
	 * 获取所有分类
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getAllSorts")
	public JSONArray getRoles(HttpServletRequest request) {
		List<?> list=helpService.getAllSorts();
		return JSONArray.fromObject(list);
	}
	
	/**
	 * 客户端帮助问题新增页面
	 * @param refreshTag
	 * @param messageCode
	 * @param model
	 * @return
	 */
	@RequestMapping("toAdd")
	public String toAdd(String refreshTag,String messageCode,Model model) {
		showMessageAlert(refreshTag,messageCode,model);
		return "app/data/help/help_add";
	}
	
	/**
	 * 新增问题
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doAdd")
	public DataMsg doAdd(HttpServletRequest request, AppHelpContent appHelpContent,DataMsg dataMsg,HttpSession session) {
		try {
			
			String answer;
			byte[] decode = Base64Util.decode(request.getParameter("answer"));
			answer  =new String(decode); 
			appHelpContent.setAnswer(answer);
			appHelpContent.setBrowseNum(0);
			appHelpContent.setIsHot(0);
			appHelpContent.setCreator(getSystemCurrentUser(session).getEmployeeId());
			appHelpContent.setCreateTime(new Date());
			helpService.doAddHelpContent(appHelpContent);
			dataMsg.setMessageCode("0001");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0002");
		}
		return dataMsg;
	}
	
	/**
	 * 跳转到帮助问题修改页面
	 * @param refreshTag
	 * @param messageCode
	 * @param model
	 * @return
	 */
	@RequestMapping("toEdit/{id}")
	public String toEdit(@PathVariable Integer id,Model model) {
		Map<String,Object> obj = helpService.findByContentID(id);
		model.addAttribute("helpContent", obj);
		model.addAttribute("hotid", obj.get("isHot"));
		return "app/data/help/help_edit";
	}
	
	/**
	 * 跳转到帮助问题修改页面
	 * @param refreshTag
	 * @param messageCode
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("doEdit")
	public DataMsg doEdit(HttpServletRequest request,AppHelpContent appHelpContent,DataMsg dataMsg,HttpSession session) {
		try {
			String answer;
			byte[] decode = Base64Util.decode(request.getParameter("answer"));
			answer  =new String(decode); 
			appHelpContent.setAnswer(answer);
			appHelpContent.setCreator(getSystemCurrentUser(session).getEmployeeId());
			appHelpContent.setCreateTime(new Date());
			helpService.doUpdateHelpContent(appHelpContent);
			dataMsg.setMessageCode("0001");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			dataMsg.setMessageCode("0002");
		}
		return dataMsg;
	}
	/**
	 * 删除帮助问题
	 * @return
	 */
	@ResponseBody
	@RequestMapping("delete")
	public boolean  delete(HttpServletRequest request,AppHelpContent appHelpContent,DataMsg dataMsg,HttpSession session) {
		try {
			//根据类别id,判断此类问题是否少于一个
			long count = helpService.getCountBySortId(appHelpContent);
			
			if(count <= 1){
				return false;
			}else{
				//执行删除操作
				helpService.deleteById(appHelpContent);
				return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return false;
		}
	}
}
