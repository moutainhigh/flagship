package com.hzcf.flagship.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.flagship.baseweb.BaseController;
import com.hzcf.flagship.dao.AppPushMessageMapper;
import com.hzcf.flagship.enmu.UserConstants;
import com.hzcf.flagship.form.PerformanceParm;
import com.hzcf.flagship.service.AppPushMessageService;
import com.hzcf.flagship.service.ImageService;
import com.hzcf.flagship.service.SystemService;
import com.hzcf.flagship.util.JedisClientUtil;
import com.hzcf.flagship.vo.ReturnMsgData;


/**
 *<dl>
 *<dt>类名：SystemController.java</dt>
 *<dd>描述: 系统模块</dd> 
 *<dd>创建时间： 2017年6月14日 下午5:41:17</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年6月14日 下午5:41:17    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController{
	
	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private JedisClientUtil jedisClient;
	@Autowired
	private AppPushMessageMapper appPushMessageMapper;
	@Autowired
	private SystemService systemService;
	@Autowired
	private ImageService imageService;
	@Autowired AppPushMessageService appPushMessageService;
	
	/**
	 *  查询app系统版本和下载地址
	 * @param params
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/getVersion",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMsgData getVersion(@RequestBody Map<String, String> params, HttpServletRequest request) throws UnsupportedEncodingException{
		String systemType = params.get("systemType");
		try {
			
			HashMap<String, String> data = new HashMap<>();
			if ("ios".equals(systemType)) {
				data.put("version", systemService.getPropertyStringValueByName("iosVersion"));
				data.put("newContent", systemService.getPropertyStringValueByName("iosNewContent").replace("\\n", "\n"));
				data.put("downloadUrl", systemService.getPropertyStringValueByName("iosDownloadUrl"));
			}else if ("android".equals(systemType)) {
				data.put("version", systemService.getPropertyStringValueByName("androidVersion"));
				data.put("newContent", systemService.getPropertyStringValueByName("androidNewContent").replace("\\n", "\n"));
				data.put("downloadUrl", systemService.getPropertyStringValueByName("androidDownloadUrl"));
			}else {
				logger.error("请求参数错误,请求参数为:"+systemType);
				return new ReturnMsgData("9999", "调用失败");
			}
			logger.info(data);
			return new ReturnMsgData("0000", "调用成功",data);
		} catch (Exception e) {
			logger.error("接口调用失败,请求参数为:"+systemType);
			logger.error(e.getMessage());
			return new ReturnMsgData("9999", "调用失败");
		}
	
	}
	
	/**
	 * 获取用户推送记录
	 * @param performanceParm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getMessages",method= RequestMethod.POST)
	public ReturnMsgData getMessages(@RequestBody PerformanceParm performanceParm ){
		try {
			//验证是否登录
			if (!checkIsLogin(performanceParm)) {
				return new ReturnMsgData("2001", "用户未登录", null);
			}
			String userIdStr = jedisClient.get(UserConstants.USER_TOKEN + performanceParm.getToken());
			List<Map<String, String>> messages = appPushMessageMapper.getMessagesByUserId(Integer.parseInt(userIdStr));
			Map<String, Object> data = new HashMap<>();
			data.put("messages", messages);
			return new ReturnMsgData("0000", "调用成功", data);
			
		} catch (Exception e) {
			logger.error("接口调用失败,请求参数为:"+performanceParm.getToken());
			logger.error(e.getMessage());
			return new ReturnMsgData("9999", "调用失败");
		}
	}
	
	
	
	/**
	 * 是否显示注册入口
	 */
	@ResponseBody
	@RequestMapping(value="/getIsShowRegister",method= RequestMethod.POST)
	public ReturnMsgData getIsShowRegister(){
		try {
			Map<Object, Object> data = new HashMap<>();
			String propertyStringValueByName = systemService.getPropertyStringValueByName("isShowRegister");
			if ("true".equals(propertyStringValueByName)) {
				data.put("isShowRegister", true);
			}else {
				data.put("isShowRegister", false);
			}
			return new ReturnMsgData("0000", "调用成功", data);
		} catch (Exception e) {
			return new ReturnMsgData("9999", "调用失败");
		}
	}
	
	/**
	 * 删除消息记录
	 */
	@ResponseBody
	@RequestMapping(value="/delMessages",method= RequestMethod.POST)
	public ReturnMsgData delMessages(@RequestBody PerformanceParm performanceParm){
		try {
			//验证是否登录
			if (!checkIsLogin(performanceParm)) {
				return new ReturnMsgData("2001", "用户未登录", null);
			}
			String type = performanceParm.getType();
			String userIdStr = jedisClient.get(UserConstants.USER_TOKEN + performanceParm.getToken());
			Integer userId = Integer.parseInt(userIdStr);
			if ("clean".equals(type)) {
				appPushMessageService.deleteAllMessagesByUserId(userId);
				return new ReturnMsgData("0000", "调用成功");
			}
			if ("del".equals(type)) {
				Long messageId = performanceParm.getMessageId();
				if (messageId==null) {
					return new ReturnMsgData("1001", "消息Id不能为空");
				}
				appPushMessageService.deleteMessageByMessageId(userId, messageId);
				return new ReturnMsgData("0000", "调用成功");
			}
			return new ReturnMsgData("9999", "调用失败");
		} catch (Exception e) {
			logger.error("接口调用失败,请求参数为:"+performanceParm.getToken());
			e.printStackTrace();
			return new ReturnMsgData("9999", "调用失败");
		}
	}
	
	/**
	 * 查看图片
	 * @param picName
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/showImage/{picName:.+}")  
    @ResponseBody
    public void imageUpload(@PathVariable("picName") String picName,HttpServletResponse response) throws IOException{  
    	byte[] image = imageService.getImageByName(picName);
    	if (image==null){
    		ServletOutputStream outputStream = response.getOutputStream();
        	outputStream.write("图片不存在".getBytes());
    	}else {
    		ServletOutputStream outputStream = response.getOutputStream();
    		outputStream.write(image);
		}
    }
}
