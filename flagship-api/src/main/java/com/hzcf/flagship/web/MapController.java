package com.hzcf.flagship.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hzcf.flagship.util.StringUtil;

/**
 * 
 *<dl>
 *<dt>类名：MapController.java</dt>
 *<dd>描述: ~地图</dd> 
 *<dd>创建时间： 2017年5月19日 下午2:20:53</dd>
 *<dd>创建人： GuoDong</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月19日 下午2:20:53    GuoDong       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Controller
public class MapController {
	/**
	 * 跳转至 各机构当月累计业绩地图
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping( value = "/doMap")
	public String doMap(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String parameter = request.getParameter("orgNames");
		if(StringUtil.notBlank(parameter)){
			
			parameter = new String(parameter.getBytes("ISO-8859-1"),"utf-8");
		}
		request.setAttribute("token",request.getParameter("token"));
		request.setAttribute("orgNames",parameter);
		request.setAttribute("days",request.getParameter("days"));
		return "echarts/echarts";
	}
}
